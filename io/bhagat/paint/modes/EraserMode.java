package io.bhagat.paint.modes;

import java.awt.event.MouseEvent;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.EraserMark;

public class EraserMode extends FreeStrokeMode {

    public static EraserMode instance = new EraserMode();

    @Override
    public void mousePressed(MouseEvent e) {
        curStroke = new EraserMark((int) PaintManager.instance.getParam("thickness"));
        PaintManager.instance.add(curStroke);
    }

    @Override
    public String toString() {
        return "Use in a similar manner as Free Stroke tool except instead of drawing, this will erase. Make sure to up the thickness for large areas.";
    }

}