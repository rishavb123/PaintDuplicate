package io.bhagat.paint;

import java.lang.reflect.InvocationTargetException;

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

}