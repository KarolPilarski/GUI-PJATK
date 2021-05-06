import javax.swing.*;
import java.awt.*;

public class Zadanie2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            TextEditorFrame tef = new TextEditorFrame();
            tef.options(Color.BLACK,new Font("Arial", Font.ITALIC, 18), Color.GREEN);
        });
    }
}

class TextEditorFrame extends JFrame {
    JTextArea jta= new JTextArea();

    TextEditorFrame(){
        jta.setPreferredSize(new Dimension(400,400));

        add(jta);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    void options(Color bg,Font f,Color fg){
        jta.setBackground(bg);
        jta.setFont(f);
        jta.setForeground(fg);
    }
}
