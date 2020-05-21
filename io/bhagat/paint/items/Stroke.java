package io.bhagat.paint.items;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

import io.bhagat.paint.Point;

public class Stroke extends DrawableItem {

    private static final long serialVersionUID = 1066391382504640977L;

    private List<Point> points;
    private Color color;
    private float thickness;

    public Stroke() {
        points = new ArrayList<>();
        color = Color.BLACK;
        thickness = 5;
    }

    public Stroke(Color color) {
        points = new ArrayList<>();
        this.color = color;
        thickness = 5;      
    }

    public Stroke(int thickness) {
        points = new ArrayList<>();
        color = Color.BLACK;
        this.thickness = thickness;
    }

    public Stroke(Color color, int thickness) {
        points = new ArrayList<>();
        this.color = color;   
        this.thickness = thickness;     
    }

    public void add(int x, int y) {
        add(new Point(x, y));
    } 

    public void add(Point point) {
        points.add(point);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        if(points.size() > 1)
            for(int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                g.drawLine(p1.getGx(), p1.getGy(), p2.getGx(), p2.getGy());
            }
        else if(points.size() == 1){
            Point p = points.get(0);
            g.fillOval(p.getGx(), p.getGy(), (int)thickness, (int)thickness);
        }
    }

    public float getThickness() {
        return thickness;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Point> getPoints() {
        return points;
    }

}