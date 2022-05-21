package Projekt2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExitListener implements KeyListener {

    Values values;

    ExitListener(Values values){
        this.values=values;
    }

    boolean ctrl=false;
    boolean shift=false;
    boolean q=false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) ctrl = true;

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) shift = true;

        if (e.getKeyCode() == KeyEvent.VK_Q) q = true;

        if(ctrl&&shift&&q){
            values.EndText="paused";
            values.Ended=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) ctrl = false;
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) shift = false;
        if (e.getKeyCode() == KeyEvent.VK_Q) q = false;
    }
}
