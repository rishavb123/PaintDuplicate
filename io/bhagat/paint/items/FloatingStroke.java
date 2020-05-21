package io.bhagat.paint.items;

import java.awt.Color;

import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.paint.Point;
import io.bhagat.paint.transforms.Transform;

public class FloatingStroke extends Stroke {

    private static final long serialVersionUID = 3333520019955401992L;

    private Transform<Point> transform;
    private Vector velocity;
    private long dt;

    public FloatingStroke(Color color, int thickness) {
        super(color, thickness);
        velocity = new Vector(2);
        velocity.randomize();
        velocity.divide(10000);
        dt = 1l;
        transform = new Transform<Point>() {

            @Override
            public Point map(Point inp) {
                if(inp.getX() < -0.5 && velocity.get(0) < 0) velocity.multiply(new Vector(-1, 1));
                if(inp.getX() > 0.5 && velocity.get(0) > 0) velocity.multiply(new Vector(-1, 1));
                if(inp.getY() < -0.5 && velocity.get(1) < 0) velocity.multiply(new Vector(1, -1));
                if(inp.getY() > 0.5 && velocity.get(1) > 0) velocity.multiply(new Vector(1, -1));

                return new Point(inp.add(velocity.clone().multiply(dt)));
            }

        };
    }

    @Override
    public void update(long dt) {
        this.dt = dt;
        Transform.transformList(getPoints(), transform);
    }


}