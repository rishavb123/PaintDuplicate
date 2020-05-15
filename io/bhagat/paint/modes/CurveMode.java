package io.bhagat.paint.modes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.items.Curve;

public class CurveMode extends PaintMode {

    public static CurveMode instance = new CurveMode();
    private boolean holding;

    protected Curve curCurve;

    @Override
    public void keyPressed(KeyEvent e) {
        if(!holding && e.getKeyCode() == 32) {
            curCurve = new Curve((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
            holding = true;
            PaintManager.instance.add(curCurve);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 32) holding = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(holding) {
            curCurve.add(e.getX(), e.getY());
        }
    }

}