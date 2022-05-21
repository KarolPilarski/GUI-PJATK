package Projekt2;

import javax.swing.*;
import java.awt.*;

public class HighScoresFrame extends JFrame {

    HighScoresFrame(){

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(Color.BLACK);

        JButton backtomenu = new JButton("Back to Main Menu");
        backtomenu.setForeground(Color.WHITE);
        backtomenu.setBackground(new Color(90,90,90));
        backtomenu.addActionListener((e)->{
            SwingUtilities.invokeLater(()->{
                new MainMenu();
                dispose();
            });
        });
        top.add(backtomenu, BorderLayout.EAST);

        JLabel text = new JLabel("Results:");
        text.setForeground(Color.WHITE);
        top.add(text, BorderLayout.WEST);
        add(top, BorderLayout.NORTH);


        JList jList = new JList();
        jList.setBackground(Color.DARK_GRAY);
        jList.setForeground(Color.WHITE);
        ScoreListModel scoreListModel = new ScoreListModel();
        jList.setModel(scoreListModel);

        JScrollPane jScrollPane = new JScrollPane(jList);
        add(jScrollPane);

        setVisible(true);
        ImageIcon icon = new ImageIcon("images/icon.jpg");
        setIconImage(icon.getImage());
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
