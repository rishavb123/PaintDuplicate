package io.bhagat.paint.modes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.Stroke;

public class FreeStrokeMode extends PaintMode {

    public static FreeStrokeMode instance = new FreeStrokeMode();

    protected Stroke curStroke;

    @Override
    public void mouseDragged(MouseEvent e) {
        curStroke.add(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        curStroke = new Stroke((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
        curStroke.add(e.getX(), e.getY());
        PaintManager.instance.add(curStroke);
    }
    
}