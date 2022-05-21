package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameImprovementsPanel extends JPanel {
    GameImprovementsPanel(Values values){
        setBackground(new Color(90,90,90));
        setLayout( new GridLayout(9,2));

        JButton I1Button = new JButton("Obligation to wear masks");
        I1Button.addActionListener((ActionEvent e)->{
            if(values.Points>=5000){
                values.SpreadConverter=values.SpreadConverter*0.9;
                values.Points-=5000;
                I1Button.setEnabled(false);
            }
        });
        I1Button.setBackground(Color.DARK_GRAY);
        I1Button.setForeground(Color.RED);
        add(I1Button);
        JLabel I1Desc = new JLabel("Reduces infections by 10%. Cost: 5k");
        I1Desc.setForeground(Color.WHITE);
        add(I1Desc);

        JButton I2Button = new JButton("Quarantine I");
        I2Button.addActionListener((ActionEvent e)->{
            if(values.Points>=250000){
                values.SpreadConverter=values.SpreadConverter*0.7;
                values.Points-=250000;
                I2Button.setEnabled(false);
            }
        });
        I2Button.setBackground(Color.DARK_GRAY);
        I2Button.setForeground(Color.RED);
        add(I2Button);
        JLabel I2Desc = new JLabel("Reduces infections by 30%. Cost: 250k");
        I2Desc.setForeground(Color.WHITE);
        add(I2Desc);

        JButton I3Button = new JButton("Quarantine II");
        I3Button.addActionListener((ActionEvent e)->{
            if(values.Points>=1000000){
                values.SpreadConverter=values.SpreadConverter*0.5;
                values.Points-=1000000;
                I3Button.setEnabled(false);
            }
        });
        I3Button.setBackground(Color.DARK_GRAY);
        I3Button.setForeground(Color.RED);
        add(I3Button);
        JLabel I3Desc = new JLabel("Reduces infections by 50%. Cost: 1mln");
        I3Desc.setForeground(Color.WHITE);
        add(I3Desc);

        JButton I4Button = new JButton("Medical equipment");
        I4Button.addActionListener((ActionEvent e)->{
            if(values.Points>=15000){
                values.CurabilityConverter=values.CurabilityConverter*1.1;
                values.Points-=15000;
                I4Button.setEnabled(false);
            }
        });
        I4Button.setBackground(Color.DARK_GRAY);
        I4Button.setForeground(Color.CYAN);
        add(I4Button);
        JLabel I4Desc = new JLabel("Increases curability by 10%. Cost: 15k");
        I4Desc.setForeground(Color.WHITE);
        add(I4Desc);

        JButton I5Button = new JButton("Buy more ambulances");
        I5Button.addActionListener((ActionEvent e)->{
            if(values.Points>=200000){
                values.CurabilityConverter=values.CurabilityConverter*1.2;
                values.Points-=200000;
                I5Button.setEnabled(false);
            }
        });
        I5Button.setBackground(Color.DARK_GRAY);
        I5Button.setForeground(Color.CYAN);
        add(I5Button);
        JLabel I5Desc = new JLabel("Increases curability by 20%. Cost: 200k");
        I5Desc.setForeground(Color.WHITE);
        add(I5Desc);

        JButton I6Button = new JButton("Field hospitals");
        I6Button.addActionListener((ActionEvent e)->{
            if(values.Points>=1000000){
                values.CurabilityConverter=values.CurabilityConverter*1.4;
                values.Points-=1000000;
                I6Button.setEnabled(false);
            }
        });
        I6Button.setBackground(Color.DARK_GRAY);
        I6Button.setForeground(Color.CYAN);
        add(I6Button);
        JLabel I6Desc = new JLabel("Increases curability by 40%. Cost: 1mln");
        I6Desc.setForeground(Color.WHITE);
        add(I6Desc);

        JButton I7Button = new JButton("Vaccination I");
        I7Button.addActionListener((ActionEvent e)->{
            if(values.Points>=10000){
                values.VaccinatedConverter+=0.0001;
                values.Points-=10000;
                I7Button.setEnabled(false);
            }
        });
        I7Button.setBackground(Color.DARK_GRAY);
        I7Button.setForeground(Color.GREEN);
        add(I7Button);
        JLabel I7Desc = new JLabel("0,01% of each country population gets vaccinated every day. Cost: 10k");
        I7Desc.setForeground(Color.WHITE);
        add(I7Desc);

        JButton I8Button = new JButton("Vaccination II");
        I8Button.addActionListener((ActionEvent e)->{
            if(values.Points>=100000){
                values.VaccinatedConverter+=0.0005;
                values.Points-=100000;
                I8Button.setEnabled(false);
            }
        });
        I8Button.setBackground(Color.DARK_GRAY);
        I8Button.setForeground(Color.GREEN);
        add(I8Button);
        JLabel I8Desc = new JLabel("0,05% of each country population gets vaccinated every day. Cost: 100k");
        I8Desc.setForeground(Color.WHITE);
        add(I8Desc);

        JButton I9Button = new JButton("Vaccination III");
        I9Button.addActionListener((ActionEvent e)->{
            if(values.Points>=1000000){
                values.VaccinatedConverter+=0.001;
                values.Points-=1000000;
                I9Button.setEnabled(false);
            }
        });
        I9Button.setBackground(Color.DARK_GRAY);
        I9Button.setForeground(Color.GREEN);
        add(I9Button);
        JLabel I9Desc = new JLabel("0,1% of each country population gets vaccinated every day. Cost: 1mln");
        I9Desc.setForeground(Color.WHITE);
        add(I9Desc);

    }
}
