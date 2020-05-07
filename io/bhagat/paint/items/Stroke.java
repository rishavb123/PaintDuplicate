package io.bhagat.paint.items;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.List;

import io.bhagat.paint.Point;

public class Stroke extends DrawableItem {
    
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
        points.add(new Point(x, y));
    } 

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(thickness));
        g.setColor(color);
        for(int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g.drawLine(p1.getGx(), p1.getGy(), p2.getGx(), p2.getGy());
        }
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