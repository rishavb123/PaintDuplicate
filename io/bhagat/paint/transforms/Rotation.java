package io.bhagat.paint.transforms;

public class Rotation extends MatrixTransform {

    private static final long serialVersionUID = -6581564235109009600L;

    public Rotation(double theta) {
        super(new double[][] {
            {Math.cos(theta), -Math.sin(theta)},
            {Math.sin(theta), Math.cos(theta)}
        });
    }

    public static Rotation fromDegrees(double theta) {
        return new Rotation(theta * Math.PI / 180);
    }

}