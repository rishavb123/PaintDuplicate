package io.bhagat.paint;

import javax.swing.JPanel;

import io.bhagat.math.linearalgebra.Vector;

public class Point extends Vector {

    private static final long serialVersionUID = 6961128081816895929L;

    public static JPanel canvas = PaintProgram.instance;
    
    public Point(double x, double y) {
        super(2);
        set(0, x);
        set(1, y);
    }

    public Point(int x, int y) {
        this((double) x / canvas.getWidth() - 0.5, (double) y / canvas.getHeight() - 0.5);
    }

    public Point(Vector vec) {
        this(vec.get(0), vec.get(1));
    }

    public double getX() {
        return get(0);
    }

    public double getY() {
        return get(1);
    }

    public void setX(double x) {
        set(0, x);
    }

    public void setY(double y) {
        set(1, y);
    }

    public int getGx() {
        return (int) (canvas.getWidth() * (getX() + 0.5));
    }

    public int getGy() {
        return (int) (canvas.getHeight() * (getY() + 0.5));
    }

    public void setGx(int x) {
        setX((double) x / canvas.getWidth() - 0.5);
    }

    public void setGy(int y) {
        setY((double) y / canvas.getHeight() - 0.5);
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}