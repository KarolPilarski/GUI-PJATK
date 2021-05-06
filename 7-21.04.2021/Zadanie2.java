import javax.swing.*;
import java.awt.*;

public class Zadanie2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Kalkulator1 kalkulator1 = new Kalkulator1();
        });
    }
}

class Kalkulator1 extends JFrame{
    Kalkulator1(){

        JPanel jpanel = new JPanel();
        jpanel.setPreferredSize(new Dimension(300,100));
        jpanel.setLayout(new GridLayout(2,5));

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

        JLabel jl3=new JLabel("wynik");
        jpanel.add(jl3);

        JButton jbDodawanie = new JButton("a+b");
        jbDodawanie.setText("+");
        jbDodawanie.addActionListener((e)->{
            int result=Integer.parseInt(jtf1.getText())+Integer.parseInt(jtf2.getText());
            jl3.setText("Wynik: "+result);
        });
        jpanel.add(jbDodawanie);

        JButton jbOdejmowanie = new JButton("a+b");
        jbOdejmowanie.setText("-");
        jbOdejmowanie.addActionListener((e)->{
            int result=Integer.parseInt(jtf1.getText())-Integer.parseInt(jtf2.getText());
            jl3.setText("Wynik: "+result);
        });
        jpanel.add(jbOdejmowanie);

        JButton jbMnozenie = new JButton("a+b");
        jbMnozenie.setText("*");
        jbMnozenie.addActionListener((e)->{
            int result=Integer.parseInt(jtf1.getText())*Integer.parseInt(jtf2.getText());
            jl3.setText("Wynik: "+result);
        });
        jpanel.add(jbMnozenie);

        JButton jbDzielenie = new JButton("a+b");
        jbDzielenie.setText("/");
        jbDzielenie.addActionListener((e)->{
            int result=Integer.parseInt(jtf1.getText())/Integer.parseInt(jtf2.getText());
            jl3.setText("Wynik: "+result);
        });
        jpanel.add(jbDzielenie);



        add(jpanel);

        setVisible(true);
        setTitle("Kalkulator 1");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
