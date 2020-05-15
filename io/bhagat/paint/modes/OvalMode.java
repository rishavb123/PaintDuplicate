package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.shapes.Oval;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class OvalMode extends PaintMode {

    public static OvalMode instance = new OvalMode();

    private Oval curOval;

    @Override
    public void mouseDragged(MouseEvent e) {
        curOval.setWidth(e.getX() - curOval.getX());
        curOval.setHeight(e.getY() - curOval.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        curOval = new Oval(e.getX(), e.getY(), 0, 0, (Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"), (boolean) PaintManager.instance.getParam("fill"));
        PaintManager.instance.add(curOval);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 32) {
            int cx = curOval.getX() + curOval.getWidth() / 2;
            int cy = curOval.getY() + curOval.getHeight() / 2;
            int w = curOval.getWidth();
            int h = curOval.getHeight();
            int newDim = Math.max(Math.abs(w), Math.abs(h));
            curOval.setWidth(w * newDim / Math.abs(w));
            curOval.setHeight(h * newDim / Math.abs(h));
            curOval.setX(cx - curOval.getWidth() / 2);
            curOval.setY(cy - curOval.getHeight() / 2);
        }
    }

}