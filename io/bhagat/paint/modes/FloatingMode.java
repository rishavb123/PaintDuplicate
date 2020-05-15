package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.FloatingStroke;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class FloatingMode extends FreeStrokeMode {

    public static FloatingMode instance = new FloatingMode();

    @Override
    public void mousePressed(MouseEvent e) {
        curStroke = new FloatingStroke((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
        PaintManager.instance.add(curStroke);
    }

    @Override
    public String toString() {
        return "<html><center>Draw whatever you like just like in Free Stroke Mode, but these items will start to float around as you are drawing. <br /> Remember the configurations menu can pause and play the animations.</center><html>";
    }
}