package io.bhagat.paint.transforms;

import io.bhagat.math.linearalgebra.Matrix;
import io.bhagat.math.linearalgebra.Vector;

public class MatrixTransform extends Matrix implements Transform<Vector> {

    private static final long serialVersionUID = 4967687953493892868L;

    public MatrixTransform(double[][] data) {
        super(data);
    }

    @Override
    public Vector map(Vector inp) {
        return Matrix.multiply(this, inp).toVector();
    }
    
}