package io.bhagat.paint;

import javax.swing.JFrame;

public class Point {

    public static JFrame frame = PaintProgram.instance.getFrame();
    
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y) {
        this.x = (double) x / frame.getWidth();
        this.y = (double) y / frame.getHeight();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getGx() {
        return (int) (frame.getWidth() * x);
    }

    public int getGy() {
        return (int) (frame.getHeight() * y);
    }

    public void setGx(int x) {
        this.x = (double) x / frame.getWidth();
    }

    public void setGy(int y) {
        this.y = (double) y / frame.getHeight();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}