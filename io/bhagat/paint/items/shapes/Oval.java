package io.bhagat.paint.items.shapes;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Oval extends Shape {

    private static final long serialVersionUID = 1197219833391020575L;

    private boolean fill;

    public Oval(int x, int y, int w, int h, Color color, int thickness, boolean fill) {
        super(x, y, w, h, color, thickness);
        this.fill = fill;
    }

    public Ellipse2D getEllipse() {
        Rectangle rect = getBoundingRect();
        return new Ellipse2D.Double(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setStroke(new BasicStroke(getThickness()));
        
        if(fill)
            g.fill(getEllipse());
        else
            g.draw(getEllipse());
    }
    
}