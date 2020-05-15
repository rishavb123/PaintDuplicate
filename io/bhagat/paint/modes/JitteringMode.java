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

    @Override
    public String toString() {
        return "<html><center>This is another extension of the free stroke mode where everything each point jitters randomly as you are drawing. <br /> Increase the magnitude of A to speed up or slow down the jittering. <br />Remember the configurations menu can pause and play the animations.</center></html>";
    }
}