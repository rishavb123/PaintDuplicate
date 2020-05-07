package io.bhagat.paint;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Util {

    public static Object staticCallFromString(String c, String field) {
        if (field.contains("(") && field.contains(")")) {
            try {
                return Class.forName(c).getMethod(field.split("\\(")[0], String.class).invoke(null, field.split("\\(")[1].split("\\)")[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return Class.forName(c).getField(field).get(null);
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T, E extends T> List<T> copyTo(List<E> inp, List<T> out) {
        for(E i: inp) out.add((T) i);
        return out;
    }

    public static String[] strRange(int min, int max, int step) {
        int len = (int) Math.ceil((double)(max - min) / step);
        String[] arr = new String[len];
        int cur = min;
        for(int i = 0; i < len; i++) {
            arr[i] = Integer.toString(cur);
            cur += step;
        }
        return arr;
    }

}