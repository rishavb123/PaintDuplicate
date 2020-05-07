package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.FloatingStroke;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class FloatingMode extends LineMode {

    public static FloatingMode instance = new FloatingMode();

    @Override
    public void mousePressed(MouseEvent e) {
        curStroke = new FloatingStroke((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
        PaintManager.instance.add(curStroke);
    }
}