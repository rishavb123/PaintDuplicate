package io.bhagat.paint.modes;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class PaintMode implements MouseMotionListener, MouseListener, KeyListener {

    public static String[] modes = { "FreeStroke", "Line", "Rectangle", "Oval", "Eraser", "Snowflake", "Floating", "Jittering" };

    @Override
    public void mouseMoved(MouseEvent e){}
    @Override
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void keyPressed(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}

}
