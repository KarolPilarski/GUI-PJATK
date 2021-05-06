import javax.swing.*;
import java.awt.*;

public class Zadanie1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new DrawDiagonals().setVisible(true);


        });
    }
}

class DrawDiagonals extends JFrame{

    DrawDiagonals(){
        setSize(new Dimension(400,400));
        setVisible(true);
    }


    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        Rectangle r = getBounds();
        int h = r.height;
        int w = r.width;

        g2d.drawLine(0,h,w,0);
        g2d.drawLine(0,0,w,h);
    }
}

