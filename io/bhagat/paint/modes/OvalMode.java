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
        // System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 32) {
            int w = curOval.getWidth();
            int h = curOval.getHeight();
            int newDim = (w + h) / 2;
            curOval.setWidth(newDim);
            curOval.setHeight(newDim);
        }
    }

}