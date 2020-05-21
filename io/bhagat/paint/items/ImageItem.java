package io.bhagat.paint.items;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import io.bhagat.paint.items.shapes.Shape;

public class ImageItem extends Shape {

    private static final long serialVersionUID = 3471374941867374357L;

    private BufferedImage image;

    public ImageItem(BufferedImage image, int x, int y, int w, int h) {
        super(x, y, w, h, null, 0);
        this.image = image;
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(resize(image, getWidth(), getHeight()), null, getX(), getY());
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }  
    
}