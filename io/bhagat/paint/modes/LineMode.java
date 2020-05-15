package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.shapes.Line;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class LineMode extends PaintMode {

    public static LineMode instance = new LineMode();

    private Line curLine;

    @Override
    public void mouseDragged(MouseEvent e) {
        curLine.setWidth(e.getX() - curLine.getX());
        curLine.setHeight(e.getY() - curLine.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        curLine = new Line(e.getX(), e.getY(), 0, 0, (Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
        PaintManager.instance.add(curLine);
    }

}