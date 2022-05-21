package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class GameTopPanel extends JPanel {
    JLabel date = new JLabel("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp 2021-01-01</html>");
    GameTopPanel(Values values){
        setLayout(new BorderLayout());
        date.setForeground(Color.WHITE);
        add(date,BorderLayout.WEST);

        JPanel tempo = new JPanel();
        tempo.setBackground(new Color(60,60,60));
        JLabel tempoLabel = new JLabel("Game tempo: ");
        tempoLabel.setForeground(Color.WHITE);
        JRadioButton t1 = new JRadioButton("x1");
        t1.setBackground(new Color(60,60,60));
        t1.setForeground(Color.WHITE);
        t1.addActionListener((ae)-> {
            values.Tempo=1000;
        });
        JRadioButton t2 = new JRadioButton("x2");
        t2.setBackground(new Color(60,60,60));
        t2.setForeground(Color.WHITE);
        t2.addActionListener((ae)-> {
            values.Tempo=500;
        });
        JRadioButton t3 = new JRadioButton("x3",true);
        t3.setBackground(new Color(60,60,60));
        t3.setForeground(Color.WHITE);
        t3.addActionListener((ae)-> {
                values.Tempo=200;
        });
        JRadioButton t4 = new JRadioButton("x4");
        t4.setBackground(new Color(60,60,60));
        t4.setForeground(Color.WHITE);
        t4.addActionListener((ae)-> {
            values.Tempo=80;
        });
        JRadioButton t5 = new JRadioButton("x5");
        t5.setBackground(new Color(60,60,60));
        t5.setForeground(Color.WHITE);
        t5.addActionListener((ae)-> {
            values.Tempo=20;
        });
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(t1);
        buttonGroup.add(t2);
        buttonGroup.add(t3);
        buttonGroup.add(t4);
        buttonGroup.add(t5);
        tempo.add(tempoLabel);
        tempo.add(t1);
        tempo.add(t2);
        tempo.add(t3);
        tempo.add(t4);
        tempo.add(t5);

        add(tempo,BorderLayout.EAST);

        setPreferredSize(new Dimension(1200,50));
        setBackground(new Color(60,60,60));
    }

    void changeDate(LocalDate ld){
        date.setText("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp "+ld.toString()+"</html>");
        repaint();
    }

}
