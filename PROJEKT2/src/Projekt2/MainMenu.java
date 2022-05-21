package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    MainMenu(){

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(4,1));


        JLabel titleLabel = new JLabel("<html><h1>Koronawirus AntiPlague</h1></html>");
        titleLabel.setForeground(Color.WHITE);
        mainMenuPanel.setBackground(Color.BLACK);
        mainMenuPanel.add(titleLabel);

        JButton startButton = new JButton("New Game");
        startButton.setPreferredSize(new Dimension(300,50));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    new GameFrame();
                });
                dispose();
            }
        });
        mainMenuPanel.add(startButton);

        JButton scoresButton = new JButton("High Scores");
        scoresButton.setPreferredSize(new Dimension(300,50));
        scoresButton.setBackground(Color.BLACK);
        scoresButton.setForeground(Color.WHITE);
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->{
                    new HighScoresFrame();
                });
                dispose();
            }
        });
        mainMenuPanel.add(scoresButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(300,50));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener((ae)->{
            dispose();
            System.exit(0);
        });
        mainMenuPanel.add(exitButton);

        add(mainMenuPanel);

        ImageIcon icon = new ImageIcon("images/icon.jpg");
        setIconImage(icon.getImage());
        setVisible(true);
        setTitle("Koronawirus AntiPlague");
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
