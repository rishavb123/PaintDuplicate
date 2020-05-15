package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.Point;
import io.bhagat.paint.items.Stroke;
import io.bhagat.paint.transforms.Rotation;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class SnowflakeMode extends PaintMode {
    
    public static SnowflakeMode instance = new SnowflakeMode();


    protected Stroke[] curStrokes;
    protected Rotation rotation;

    private int numSides;

    public SnowflakeMode() {
        Object a = PaintManager.instance.getParam("a");
        numSides = a != null? (int) Math.abs((int) a) : 6;
        curStrokes = new Stroke[numSides];
        rotation = new Rotation(2 * Math.PI / numSides);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY());
        for(int i = 0; i < numSides; i++) {
            curStrokes[i].add(point);
            point = rotation.map(point);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        Object a = PaintManager.instance.getParam("a");
        int newNumSides = a != null? Math.abs((int) a) : 6;
        if(newNumSides != numSides) {
            numSides = newNumSides;
            rotation = new Rotation(2 * Math.PI / numSides);
            curStrokes = new Stroke[numSides];
        }

        Point point = new Point(e.getX(), e.getY());
        for(int i = 0; i < numSides; i++) {
            curStrokes[i] = new Stroke((Color) PaintManager.instance.getParam("color"), (int) PaintManager.instance.getParam("thickness"));
            curStrokes[i].add(point);
            point = rotation.map(point);
            PaintManager.instance.add(curStrokes[i]);
        }
    }

    @Override
    public String toString() {
        return "<html><center>This mode is another extension of free stroke, but it will apply the snowflake effect on your drawing by copying and rotating your drawing around a circle. <br /> Modify A to change the number of sides on your snowflake </center></html>";
    }

}