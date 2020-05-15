package io.bhagat.paint.items;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.bhagat.math.linearalgebra.LinearEquationSolver;
import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.paint.Point;

public class Curve extends Stroke {
    
    private ArrayList<String> variableNames;
    private Vector theta;

    double minX;
    double maxX;

    public Curve(Color color, int thickness) {
        super(color, thickness);
        variableNames = new ArrayList<>();
    }
    
    @Override
    public void add(Point point) {
        List<Point> points = getPoints();
        points.add(point);
        int N = points.size();
        variableNames.add(Integer.toString(N - 1));
        Matrix X = new Matrix(N, N);
        Vector y = new Vector(N);
        theta = new Vector(N);
        for(int i = 0; i < N; i++) {
            double x_point = points.get(i).getX();
            double y_point = points.get(i).getY();
            Vector x = new Vector(N);
            for(int j = 0; j < N; j++)
                x.set(j, Math.pow(x_point, j));
            X.setRow(x, i);
            y.set(i, y_point);
        }
        HashMap<String, Double> map = LinearEquationSolver.gaussMethod(X, y, variableNames);
        for(int i = 0; i < N; i++) {
            theta.set(i, map.get(Integer.toString(i)));
        }
        minX = points.get(0).getX();
        maxX = points.get(0).getX();
        for(int i = 1; i < points.size(); i++) {
            double x = points.get(i).getX();
            if(x < minX) {
                minX = x;
            }
            if(x > maxX) {
                maxX = x;
            }
        }
    }

	@Override
    public void draw(Graphics2D g) {
        List<Point> points = getPoints();
        int N = points.size();
        if(N == 0) return;
        float thickness = getThickness();
        g.setStroke(new BasicStroke(thickness));
        g.setColor(getColor());
        if(N > 1) {
            Point lastPoint = null;
            for(double x_point = minX; x_point < maxX + 0.01; x_point += 0.01) {
                Vector x = new Vector(N);
                for(int j = 0; j < N; j++)
                    x.set(j, Math.pow(x_point, j));
                Point point = new Point(x_point, theta.dot(x));
                if(lastPoint != null) {
                    g.drawLine(lastPoint.getGx(), lastPoint.getGy(), point.getGx(), point.getGy());
                }
                lastPoint = point;
            }
        }
        for(Point p: points)
            g.fillOval(p.getGx() - (int) thickness, p.getGy() - (int) thickness, (int) thickness * 2, (int) thickness * 2);
    }

}