package io.bhagat.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.modes.PaintMode;

import javax.swing.JMenuItem;

public class PaintProgram extends JPanel {

    private static final long serialVersionUID = 2243648409907110137L;
    public static PaintProgram instance;

    // TODO: convert into enum; loop through with enum.values(); ex: public enum Color { BLACK("bad"); public Color(String good){} }
    private static final String colors = "BLUE\tRED\tBLACK\tGREEN\tYELLOW\tWHITE";
    private static final String[] menuBarDefinitions = { "Color\t" + colors, "Modes\t" + String.join("\t", PaintMode.modes), "Thickness\t1\t5\t10\t20\t50\t100" };
    private static final String[] menuClassDefinitions = { "java.awt.Color", "io.bhagat.paint.modes.{val}Mode", "java.lang.Integer" };
    private static final String[] menuFieldDefinitions = { "{val}", "instance", "parseInt({val})" };

    private JFrame frame;
    private JMenuBar menuBar;

    private PaintManager pm = PaintManager.instance;

    public PaintProgram() {

        instance = this;

        frame = new JFrame("Paint");
        frame.setSize(1200, 800);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();

        for (int k = 0; k < menuBarDefinitions.length; k++) {
            String menuInfo = menuBarDefinitions[k];
            String[] arr = menuInfo.split("\t");
            JMenu menu = new JMenu(arr[0]);
            final int l = k;
            for (int i = 1; i < arr.length; i++) {
                JMenuItem item = new JMenuItem(arr[i]);
                if(arr[0].equals("Color")) {
                    Color c = (Color) Util.staticCallFromString("java.awt.Color", arr[i]);
                    item.setBackground(c);
                    if(c.getRed() + c.getGreen() * 2 + c.getBlue() < 300) item.setForeground(Color.WHITE);
                }
                final int j = i;
                item.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object obj = Util.staticCallFromString(menuClassDefinitions[l].replaceAll("\\{val\\}", arr[j]), menuFieldDefinitions[l].replaceAll("\\{val\\}", arr[j]));
                        pm.setParam(arr[0].toLowerCase(), obj);
                    }
                    
                });
                menu.add(item);
            }
            menuBar.add(menu);
        }

        addMouseListener(pm.getMouseListener());
        addMouseMotionListener(pm.getMouseMotionListener());
        frame.add(menuBar, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        for(DrawableItem item: pm.getItems())
            item.draw(g);
        // if(pm.getItems().size() > 0)
        //     System.out.println("hi " + pm.getItems().size() + " " + pm.getItems().get(pm.getItems().size() - 1));
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        new PaintProgram();
    }
}