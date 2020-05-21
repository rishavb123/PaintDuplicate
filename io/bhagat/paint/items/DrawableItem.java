package io.bhagat.paint.items;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class DrawableItem implements Serializable {
    
    private static final long serialVersionUID = -9038222142350414986L;

    public abstract void draw(Graphics2D g);

    public void update(long dt) {}

}