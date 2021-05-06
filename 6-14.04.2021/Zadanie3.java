import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static java.awt.FlowLayout.LEFT;

public class Zadanie3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer layoutu:");
        int lo=scanner.nextByte();

        SwingUtilities.invokeLater(()->{
            LayoutsFrame lf = new LayoutsFrame(lo);
        });
    }
}

class LayoutsFrame extends JFrame{
    LayoutsFrame(int i){
        switch (i){
            case 1:
                setLayout(new BorderLayout());
                break;
            case 2:
                setLayout(new FlowLayout(FlowLayout.LEFT));
                break;
            case 3:
                setLayout(new FlowLayout(FlowLayout.RIGHT));
                break;
            case 4:
                setLayout(new FlowLayout());
                break;
            case 5:
                setLayout(new GridLayout(1,5));
                break;
            case 6:
                setLayout(new GridLayout(5,1));
                break;
            case 7:
                setLayout(new GridLayout(3,2));
                break;

        }
        JButton j1 = new JButton("Przycisk1");
        add(j1);
        JButton j2 = new JButton("Przycisk 2");
        add(j2);
        JButton j3 = new JButton("P3");
        add(j3);
        JButton j4 = new JButton("P 4");
        add(j4);
        JButton j5 = new JButton("Duzy przycisk o numerze 5");
        add(j5);


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
}