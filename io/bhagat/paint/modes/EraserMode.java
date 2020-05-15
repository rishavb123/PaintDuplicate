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

}