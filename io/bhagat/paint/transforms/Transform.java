package io.bhagat.paint.transforms;

import java.io.Serializable;
import java.util.List;

import io.bhagat.math.linearalgebra.Vector;

public interface Transform<E> extends Serializable {
    public E map(E inp);
    
    public static <E> List<E>  transformList(List<E> list, Transform<E> transform) {
        for(int i = 0; i < list.size(); i++)
        list.set(i, transform.map(list.get(i)));
        return list;
    }
    
    public static Vector elementWiseTransformVector(Vector vector, Transform<Double> transform) {
        for(int i = 0; i < vector.getSize(); i++)
            vector.set(i, transform.map(vector.get(i)));
        return vector;
    }
}