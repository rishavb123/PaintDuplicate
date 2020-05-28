package io.bhagat.paint;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JOptionPane;

import io.bhagat.paint.items.DrawableItem;
import io.bhagat.paint.modes.PaintMode;

public class PaintManager {

    
    public static PaintManager instance = new PaintManager();

    private Stack<DrawableItem> items;
    private Stack<DrawableItem> removedItems;
    private Map<Object, Object> params;

    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private KeyListener keyListener;

    public PaintManager() {
        items = new Stack<>();
        removedItems = new Stack<>();
        params = new HashMap<>();

        params.put("showinstructions", true);

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
        keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				((PaintMode) params.get("mode")).keyTyped(e);
                PaintProgram.instance.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				((PaintMode) params.get("mode")).keyPressed(e);
                PaintProgram.instance.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				((PaintMode) params.get("mode")).keyReleased(e);
                PaintProgram.instance.repaint();
			}

        };

    }

    public SaveObject makeSaveObject() {
        return new SaveObject(items, removedItems);
    }

    public void loadSaveObject(SaveObject obj) {
        items = obj.itms;
        removedItems = obj.rmvdItms;
    }

    public void add(DrawableItem item) {
        items.push(item);
        if(removedItems.size() > 0) removedItems.clear();
    }

    public void undo() {
        if(items.size() > 0) {
            removedItems.push(items.pop());
        }
    }

    public void clear() {
        items.clear();
        removedItems.clear();
    }

    public void redo() {
        if(removedItems.size() > 0) {
            items.push(removedItems.pop());
        }
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public Stack<DrawableItem> getItems() {
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

    public static class SaveObject implements Serializable {

        private static final long serialVersionUID = 7660179388808091718L;

        public Stack<DrawableItem> itms;
        public Stack<DrawableItem> rmvdItms;

        public SaveObject(Stack<DrawableItem> items, Stack<DrawableItem> removedItems) {
            this.itms = items;
            this.rmvdItms = removedItems;
        }

    }

}