package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.JitteringStroke;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class JitteringMode extends FreeStrokeMode {

    public static JitteringMode instance = new JitteringMode();

    @Override
    public void mousePressed(MouseEvent e) {
        curStroke = new JitteringStroke((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
        PaintManager.instance.add(curStroke);
    }
}