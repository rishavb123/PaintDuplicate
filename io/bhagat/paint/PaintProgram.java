package io.bhagat.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.modes.PaintMode;

import javax.swing.JMenuItem;

public class PaintProgram extends JPanel {

    private static final long serialVersionUID = 2243648409907110137L;
    public static PaintProgram instance;

    // TODO: convert into enum; loop through with enum.values(); ex: public enum
    // Color { BLACK("bad"); public Color(String good){} }

    private JFrame frame;
    private JMenuBar menuBar;

    private PaintManager pm = PaintManager.instance;

    private Point mousePosition;

    public PaintProgram() {

        instance = this;

        frame = new JFrame("Paint");
        frame.setSize(1200, 800);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();

        for (MenuDefinition menuInfo: MenuDefinition.values()) {
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
            JTextField textField = new JTextField("");
            textField.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menuInfo.callback(e.getActionCommand());
                    textField.setText("");
                }
                
            });
            menu.add(textField);
            menuBar.add(menu);
        }

        addMouseListener(pm.getMouseListener());
        addMouseMotionListener(pm.getMouseMotionListener());
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setVisible(true);

        mousePosition = new Point(0, 0);

        new Thread(new Runnable() {

            @Override
            public void run() {
                long lastTime = Instant.now().toEpochMilli();
                while (true) {

                    long curTime = Instant.now().toEpochMilli();

                    for(DrawableItem item: pm.getItems())
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

        for(DrawableItem item: pm.getItems())
            item.draw(g);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Point getMousePoint() {
        return mousePosition;
    }

    public static void main(String[] args) {
        new PaintProgram();
    }
}