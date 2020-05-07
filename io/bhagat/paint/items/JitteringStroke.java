package io.bhagat.paint.items;

import java.awt.Color;

import io.bhagat.math.linearalgebra.Vector;
import io.bhagat.paint.PaintManager;
import io.bhagat.paint.Point;
import io.bhagat.paint.transforms.Transform;

public class JitteringStroke extends Stroke {

    private Transform<Point> transform;
    private long dt;

    public JitteringStroke(Color color, int thickness) {
        super(color, thickness);
        dt = 1l;
        transform = new Transform<Point>() {

            @Override
            public Point map(Point inp) {
                Vector velocity = new Vector(2);
                velocity.randomize();
                Object a = PaintManager.instance.getParam("a");
                int scale = a == null? 1 : (int) a;
                velocity.divide(100000).multiply(dt * scale);
                return new Point(inp.add(velocity));
            }

        };
    }

    @Override
    public void update(long dt) {
        this.dt = dt;
        Transform.transformList(getPoints(), transform);
    }


}