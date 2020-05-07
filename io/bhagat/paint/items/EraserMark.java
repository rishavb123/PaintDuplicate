package io.bhagat.paint.items;

import java.awt.Color;

import io.bhagat.paint.PaintManager;

public class EraserMark extends Stroke {

    public EraserMark(int thickness) {
        super(thickness);
    }

    @Override
    public void update(long dt) {
        setColor((Color) PaintManager.instance.getParam("backgroundcolor"));
    }
    
}