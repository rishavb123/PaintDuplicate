package io.bhagat.paint.modes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.event.MouseListener;

public abstract class PaintMode implements MouseMotionListener, MouseListener {

    public static String[] modes = { "Line" };

    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}