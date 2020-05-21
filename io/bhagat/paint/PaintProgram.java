package io.bhagat.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.time.Instant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.items.ImageItem;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class PaintProgram extends JPanel {

    private static final long serialVersionUID = 2243648409907110137L;
    public static PaintProgram instance;

    private JFrame frame;
    private JMenuBar menuBar;
    private JPanel sliderPanel;
    private JPanel labelsPanel;
    private JPanel textFieldsPanel;
    private JLabel instructions;

    private int numOfSliderParams = SliderDefinition.values().length;

    private PaintManager pm = PaintManager.instance;

    private Point mousePosition;

    private JFileChooser fileChooser;

    public PaintProgram() {

        instance = this;

        frame = new JFrame("Paint");
        frame.setSize(1200, 800);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        instructions = new JLabel();
        menuBar = new JMenuBar();

        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg"));

        sliderPanel = new JPanel(new GridLayout(numOfSliderParams, 1));
        labelsPanel = new JPanel(new GridLayout(numOfSliderParams, 1));
        textFieldsPanel = new JPanel(new GridLayout(numOfSliderParams, 1));

        for (MenuDefinition menuInfo : MenuDefinition.values()) {
            JMenu menu = new JMenu(menuInfo.getName());
            for (int i = 0; i < menuInfo.getSize(); i++) {
                JMenuItem item = new JMenuItem(menuInfo.getItem(i));
                if (menuInfo.getName().equals("Color")) {
                    Color c = (Color) Util.staticCallFromString("java.awt.Color", menuInfo.getItem(i));
                    item.setBackground(c);
                    if (c.getRed() + c.getGreen() * 2 + c.getBlue() < 300)
                        item.setForeground(Color.WHITE);
                }
                final int j = i;
                item.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuInfo.callback(menuInfo.getItem(j));
                    }

                });
                menu.add(item);
            }

            if (menuInfo.getName().equals("Color")) {
                JColorChooser colorChooser = new JColorChooser();
                colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        pm.setParam("color", colorChooser.getColor());
                    }

                });
                menu.add(colorChooser);
            } else {
                JTextField textField = new JTextField("");
                textField.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        menuInfo.callback(e.getActionCommand());
                        textField.setText("");
                    }

                });
                menu.add(textField);
            }
            menuBar.add(menu);
        }

        final JPanel root = this;

        for (SliderDefinition sliderInfo : SliderDefinition.values()) {
            JLabel label = new JLabel("    " + sliderInfo.getName() + " :     ");
            JTextField textField = new JTextField(Double.toString(sliderInfo.getDefaultValue()), 10);
            JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL,
                    (int) (sliderInfo.getDefaultValue() * sliderInfo.getPrecision()), 0,
                    (int) (sliderInfo.getStart() * sliderInfo.getPrecision()),
                    (int) (sliderInfo.getEnd() * sliderInfo.getPrecision()));
            scrollBar.addAdjustmentListener(new AdjustmentListener() {

                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    double val = (double) e.getValue() / sliderInfo.getPrecision();
                    sliderInfo.callback(val);
                    textField.setText(Double.toString(val));
                }

            });
            textField.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double value = (int) (Double.parseDouble(e.getActionCommand()) * sliderInfo.getPrecision())
                                / (double) sliderInfo.getPrecision();
                        if (value < sliderInfo.getStart())
                            value = sliderInfo.getStart();
                        else if (value > sliderInfo.getEnd())
                            value = sliderInfo.getEnd();
                        sliderInfo.callback(value);
                        textField.setText(Double.toString(value));
                        scrollBar.setValue((int) (value * sliderInfo.getPrecision()));
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(root, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
                        textField.setText("");
                    }
                }

            });

            labelsPanel.add(label);
            textFieldsPanel.add(textField);
            sliderPanel.add(scrollBar);
        }

        setFocusable(true);
        addKeyListener(pm.getKeyListener());
        addMouseListener(pm.getMouseListener());
        addMouseMotionListener(pm.getMouseMotionListener());

        JPanel leftPanel = new JPanel(new GridLayout(1, 2));
        leftPanel.add(labelsPanel);
        leftPanel.add(textFieldsPanel);

        JPanel panelsPanel = new JPanel(new BorderLayout());
        panelsPanel.add(leftPanel, BorderLayout.WEST);
        panelsPanel.add(sliderPanel, BorderLayout.CENTER);

        frame.add(panelsPanel, BorderLayout.SOUTH);
        frame.add(menuBar, BorderLayout.NORTH);

        frame.setVisible(true);

        mousePosition = new Point(0, 0);

        add(instructions, BorderLayout.SOUTH);

        new Thread(new Runnable() {

            @Override
            public void run() {
                long lastTime = Instant.now().toEpochMilli();
                while (true) {
                    long curTime = Instant.now().toEpochMilli();

                    instructions.setText(pm.getParam("mode").toString());
                    Color backgroundColor = (Color) pm.getParam("backgroundcolor");
                    Color complement = new Color(255 - backgroundColor.getRed(), 255 - backgroundColor.getGreen(),
                            255 - backgroundColor.getBlue());
                    instructions.setForeground(complement);

                    instructions.setVisible((boolean) pm.getParam("showinstructions"));

                    if ((boolean) pm.getParam("playing"))
                        for (DrawableItem item : pm.getItems())
                            item.update(curTime - lastTime);

                    repaint();

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lastTime = curTime;

                }
            }

        }).start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        g.setColor((Color) pm.getParam("backgroundcolor"));
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        for (DrawableItem item : pm.getItems())
            item.draw(g);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Point getMousePoint() {
        return mousePosition;
    }

    public BufferedImage createImage() {
        instructions.setVisible(false);

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();
        paint(g);
        g.dispose();

        return image;
    }

    public void export() {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            String extension;
            String[] arr = path.split(".");

            if (arr.length < 2) {
                extension = "png";
                path += ".png";
            } else
                extension = arr[1];

            try {
                ImageIO.write(createImage(), extension, new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void importImage() {
        BufferedImage image = openImage();
        if(image != null) {
            pm.clear();
            pm.add(new ImageItem(image, 0, 0, getWidth(), getHeight()));
        }
    }

    public BufferedImage openImage() {
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        new PaintProgram();
    }
}