package io.bhagat.paint.items.shapes;

import java.awt.Color;
import java.awt.Rectangle;

import io.bhagat.paint.Point;
import io.bhagat.paint.items.DrawableItem;

public abstract class Shape extends DrawableItem {

    private static final long serialVersionUID = 4181503817993265800L;

    private Point position;
    private Point dimensions;
    private Color color;
    private int thickness;
    

    public Shape(int x, int y, int w, int h, Color color, int thickness) {
        this.position = new Point(x, y);
        this.dimensions = new Point(w, h);
        this.color = color;
        this.thickness = thickness;
    }


    public Shape() {
    }

    public Shape(Point position, Point dimensions, Color color, int thickness) {
        this.position = position;
        this.dimensions = dimensions;
        this.color = color;
        this.thickness = thickness;
    }

    public Rectangle getBoundingRect() {
        int x = getX();
        int w = getWidth();
        if(getWidth() < 0) {
            x += w;
            w *= -1;
        }
        int y = getY();
        int h = getHeight();
        if(getHeight() < 0) {
            y += h;
            h *= -1;
        }
        return new Rectangle(x, y, w, h);
    }

    public int getX() {
        return this.position.getGx();
    }

    public int getY() {
        return this.position.getGy();
    }

    public int getWidth() {
        return this.dimensions.getGx();
    }

    public int getHeight() {
        return this.dimensions.getGy();
    }

    public void setX(int x) {
        this.position.setGx(x);
    }

    public void setY(int y) {
        this.position.setGy(y);
    }

    public void setWidth(int w) {
        this.dimensions.setGx(w);
    }

    public void setHeight(int h) {
        this.dimensions.setGy(h);
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(Point dimensions) {
        this.dimensions = dimensions;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getThickness() {
        return this.thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

}