package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.shapes.Block;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class RectangleMode extends PaintMode {

    public static RectangleMode instance = new RectangleMode();

    private Block curRectangle;

    @Override
    public void mouseDragged(MouseEvent e) {
        curRectangle.setWidth(e.getX() - curRectangle.getX());
        curRectangle.setHeight(e.getY() - curRectangle.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        curRectangle = new Block(e.getX(), e.getY(), 0, 0, (Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"), (boolean) PaintManager.instance.getParam("fill"));
        PaintManager.instance.add(curRectangle);
    }

}