package io.bhagat.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.modes.LineMode;
import io.bhagat.paint.modes.PaintMode;

public class PaintManager {

    public static PaintManager instance = new PaintManager();

    private List<DrawableItem> items;
    private Map<Object, Object> params;

    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;

    public PaintManager() {
        items = new ArrayList<>();
        params = new HashMap<>();

        params.put("color", Color.WHITE);
        params.put("mode", LineMode.instance);
        params.put("thickness", 5);
        params.put("backgroundcolor", Color.BLACK);

        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ((PaintMode) params.get("mode")).mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseEntered(e);
                Point p = PaintProgram.instance.getMousePoint();
                p.setGx(e.getX());
                p.setGy(e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseExited(e);
            }
            
        };
        mouseMotionListener = new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseDragged(e);
                PaintProgram.instance.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                ((PaintMode) params.get("mode")).mouseMoved(e);
                PaintProgram.instance.repaint();
            }

        };

    }
        
    public void add(DrawableItem item) {
        items.add(item);
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    public List<DrawableItem> getItems() {
        return items;
    }

    public Object getParam(Object key) {
        return params.get(key);
    }

    public void setParam(Object key, Object value) {
        if(value == null)
            JOptionPane.showMessageDialog(PaintProgram.instance, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
        else
            params.put(key, value);
    }

}