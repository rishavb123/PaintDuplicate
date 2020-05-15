package io.bhagat.paint.items.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Block extends Shape {

    private boolean fill;

    public Block(int x, int y, int w, int h, Color color, int thickness, boolean fill) {
        super(x, y, w, h, color, thickness);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(getThickness()));
        if(fill)
            g.fill(getBoundingRect());
        else
            g.draw(getBoundingRect());
    }

}