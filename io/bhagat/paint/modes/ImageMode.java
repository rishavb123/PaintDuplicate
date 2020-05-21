package io.bhagat.paint.modes;

import io.bhagat.paint.PaintManager;
import io.bhagat.paint.PaintProgram;
import io.bhagat.paint.items.ImageItem;

import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ImageMode extends PaintMode {

    public static ImageMode instance = new ImageMode();

    private ImageItem curImageItem;
    private BufferedImage image;

    @Override
    public void mouseDragged(MouseEvent e) {
        if(image != null) {
            int w = Math.max(10, e.getX() - curImageItem.getX());
            int h = Math.max(10, e.getY() - curImageItem.getY());
            curImageItem.setWidth(w);
            curImageItem.setHeight(h);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(image != null) {
            curImageItem = new ImageItem(image, e.getX(), e.getY(), 10, 10);
            PaintManager.instance.add(curImageItem);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 32) {
            image = PaintProgram.instance.openImage();
        }
    }

    @Override
    public String toString() {
        return "<html><center>Draw images by dragging your mouse from one corner to another. Press space to choose which image to draw. <br /> Note: too many images will slow the program</center></html>";
    }

}