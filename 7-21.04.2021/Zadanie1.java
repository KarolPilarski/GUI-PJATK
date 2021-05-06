import javax.swing.*;
import java.awt.*;

public class Zadanie1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Sumator sumator = new Sumator();
        });
    }
}

class Sumator extends JFrame{
    Sumator(){
        setLayout(new GridLayout(1,5));

        JPanel jpanel = new JPanel();
        jpanel.setPreferredSize(new Dimension(300,100));

        JLabel jl1=new JLabel("a:");
        jpanel.add(jl1);

        JTextField jtf1 = new JTextField("0");
        jtf1.setPreferredSize(new Dimension(100,20));
        jpanel.add(jtf1);

        JLabel jl2=new JLabel("b:");
        jpanel.add(jl2);

        JTextField jtf2 = new JTextField("0");
        jtf2.setPreferredSize(new Dimension(100,20));
        jpanel.add(jtf2);

        JButton jb = new JButton("a+b");
        jb.addActionListener((e)->{
            int result=Integer.parseInt(jtf1.getText())+Integer.parseInt(jtf2.getText());
            jb.setText(""+result);
        });
        jpanel.add(jb);

        add(jpanel);

        setVisible(true);
        setTitle("Sumator");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}