package io.bhagat.paint.transforms;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.paint.Point;

public class MatrixTransform extends Matrix implements Transform<Point> {

    private static final long serialVersionUID = 4967687953493892868L;

    public MatrixTransform(double[][] data) {
        super(data);
    }

    @Override
    public Point map(Point inp) {
        return new Point(Matrix.multiply(this, inp).toVector());
    }
    
}