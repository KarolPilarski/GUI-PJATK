import javax.swing.*;
import java.awt.*;

public class Zadanie3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            Kalkulator2 kalkulator = new Kalkulator2();
        });
    }
}

class Kalkulator2 extends JFrame{
    Kalkulator2(){
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.ORANGE);

        JPanel resultPanel = new JPanel();
        resultPanel.setPreferredSize(new Dimension(400,100));
        JTextField result = new JTextField();
        result.setPreferredSize(new Dimension(380,90));
        resultPanel.setBackground(Color.ORANGE);
        resultPanel.add(result);

        JPanel typePanel = new JPanel();
        typePanel.setPreferredSize(new Dimension(400,50));
        JLabel jl1 = new JLabel("System liczbowy:");
        typePanel.add(jl1);
        JRadioButton r1=new JRadioButton("szesnastkowy");
        r1.setBackground(Color.ORANGE);
        JRadioButton r2=new JRadioButton("dziesietny");
        r2.setBackground(Color.ORANGE);
        JRadioButton r3=new JRadioButton("binarny");
        r3.setBackground(Color.ORANGE);
        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        typePanel.add(r1);
        typePanel.add(r2);
        typePanel.add(r3);
        typePanel.setBackground(Color.ORANGE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400,350));
        buttonPanel.setBackground(Color.ORANGE);
        buttonPanel.setLayout(new GridLayout(5,4));
        JButton b0 = new JButton("0");
        buttonPanel.add(b0);
        JButton b1 = new JButton("1");
        buttonPanel.add(b1);
        JButton b2 = new JButton("2");
        buttonPanel.add(b2);
        JButton b3 = new JButton("3");
        buttonPanel.add(b3);
        JButton b4 = new JButton("4");
        buttonPanel.add(b4);
        JButton b5 = new JButton("5");
        buttonPanel.add(b5);
        JButton b6 = new JButton("6");
        buttonPanel.add(b6);
        JButton b7 = new JButton("7");
        buttonPanel.add(b7);
        JButton b8 = new JButton("8");
        buttonPanel.add(b8);
        JButton b9 = new JButton("9");
        buttonPanel.add(b9);
        JButton bA = new JButton("A");
        buttonPanel.add(bA);
        JButton bB = new JButton("B");
        buttonPanel.add(bB);
        JButton bC = new JButton("C");
        buttonPanel.add(bC);
        JButton bD = new JButton("D");
        buttonPanel.add(bD);
        JButton bE = new JButton("E");
        buttonPanel.add(bE);
        JButton bF = new JButton("F");
        buttonPanel.add(bF);
        JButton bCE = new JButton("CE");
        buttonPanel.add(bCE);
        JButton bMinus = new JButton("-");
        buttonPanel.add(bMinus);
        JButton bPlus = new JButton("+");
        buttonPanel.add(bPlus);
        JButton bEquals = new JButton("=");
        buttonPanel.add(bEquals);




        add(resultPanel);
        add(typePanel);
        add(buttonPanel);

        setVisible(true);
        setTitle("Kalkulator");
        setSize(400,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}