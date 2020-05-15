package io.bhagat.paint.items.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {

    public Line(int x, int y, int w, int h, Color color, int thickness, boolean fill) {
        super(x, y, w, h, color, thickness);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(getThickness()));
        g.setColor(getColor());
        g.drawLine(getX(), getY(), getX() + getWidth(), getY() + getHeight());
    }
    
}