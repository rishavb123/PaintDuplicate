package io.bhagat.paint.items;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import io.bhagat.paint.Point;

public class ImageItem extends DrawableItem {

    private BufferedImage image;
    private Point position;
    private Point dimension;

    public ImageItem(BufferedImage image, int x, int y, int w, int h) {
        this.image = image;
        position = new Point(x, y);
        dimension = new Point(w, h);
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(resize(image, dimension.getGx(), dimension.getGy()), null, position.getGx(), position.getGy());
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