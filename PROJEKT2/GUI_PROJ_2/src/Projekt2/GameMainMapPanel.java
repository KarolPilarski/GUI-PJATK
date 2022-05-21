package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameMainMapPanel extends JPanel{

    Image map;

    GameMainMapPanel(GameRightPanel grp){

        setBackground(Color.BLACK);
        setLayout(null);
        map=new ImageIcon("images/map.png").getImage();


        JButton greatbritainButton = new JButton("Great Britain");
        greatbritainButton.setBounds(210,180,120,30);
        greatbritainButton.setBackground(new Color(90,90,90));
        greatbritainButton.setForeground(Color.WHITE);
        greatbritainButton.addActionListener(grp);
        add(greatbritainButton);

        JButton portugalButton = new JButton("Portugal");
        portugalButton.setBounds(40,480,100,30);
        portugalButton.setBackground(new Color(90,90,90));
        portugalButton.setForeground(Color.WHITE);
        portugalButton.addActionListener(grp);
        add(portugalButton);

        JButton spainButton = new JButton("Spain");
        spainButton.setBounds(170,480,80,30);
        spainButton.setBackground(new Color(90,90,90));
        spainButton.setForeground(Color.WHITE);
        spainButton.addActionListener(grp);
        add(spainButton);

        JButton franceButton = new JButton("France");
        franceButton.setBounds(280,330,80,30);
        franceButton.setBackground(new Color(90,90,90));
        franceButton.setForeground(Color.WHITE);
        franceButton.addActionListener(grp);
        add(franceButton);

        JButton italyButton = new JButton("Italy");
        italyButton.setBounds(420,410,80,30);
        italyButton.setBackground(new Color(90,90,90));
        italyButton.setForeground(Color.WHITE);
        italyButton.addActionListener(grp);
        add(italyButton);

        JButton switzerlandButton = new JButton("Switzerland");
        switzerlandButton.setBounds(390,360,100,30);
        switzerlandButton.setBackground(new Color(90,90,90));
        switzerlandButton.setForeground(Color.WHITE);
        switzerlandButton.addActionListener(grp);
        add(switzerlandButton);

        JButton germanyButton = new JButton("Germany");
        germanyButton.setBounds(420,235,100,30);
        germanyButton.setBackground(new Color(90,90,90));
        germanyButton.setForeground(Color.WHITE);
        germanyButton.addActionListener(grp);
        add(germanyButton);

        JButton belgiumButton = new JButton("Belgium");
        belgiumButton.setBounds(330,240,80,30);
        belgiumButton.setBackground(new Color(90,90,90));
        belgiumButton.setForeground(Color.WHITE);
        belgiumButton.addActionListener(grp);
        add(belgiumButton);

        JButton netherlandsButton = new JButton("Netherlands");
        netherlandsButton.setBounds(350,200,110,30);
        netherlandsButton.setBackground(new Color(90,90,90));
        netherlandsButton.setForeground(Color.WHITE);
        netherlandsButton.addActionListener(grp);
        add(netherlandsButton);

        JButton polandButton = new JButton("Poland");
        polandButton.setBounds(570,200,100,30);
        polandButton.setBackground(new Color(90,90,90));
        polandButton.setForeground(Color.WHITE);
        polandButton.addActionListener(grp);
        add(polandButton);

        JButton czechButton = new JButton("Czechia");
        czechButton.setBounds(500,275,100,30);
        czechButton.setBackground(new Color(90,90,90));
        czechButton.setForeground(Color.WHITE);
        czechButton.addActionListener(grp);
        add(czechButton);

        JButton slovakiaButton = new JButton("Slovakia");
        slovakiaButton.setBounds(600,300,100,30);
        slovakiaButton.setBackground(new Color(90,90,90));
        slovakiaButton.setForeground(Color.WHITE);
        slovakiaButton.addActionListener(grp);
        add(slovakiaButton);

        JButton denmarkButton = new JButton("Denmark");
        denmarkButton.setBounds(390,110,100,30);
        denmarkButton.setBackground(new Color(90,90,90));
        denmarkButton.setForeground(Color.WHITE);
        denmarkButton.addActionListener(grp);
        add(denmarkButton);

        JButton norwayButton = new JButton("Norway");
        norwayButton.setBounds(350,20,100,30);
        norwayButton.setBackground(new Color(90,90,90));
        norwayButton.setForeground(Color.WHITE);
        norwayButton.addActionListener(grp);
        add(norwayButton);

        JButton swedenButton = new JButton("Sweden");
        swedenButton.setBounds(500,50,100,30);
        swedenButton.setBackground(new Color(90,90,90));
        swedenButton.setForeground(Color.WHITE);
        swedenButton.addActionListener(grp);
        add(swedenButton);

    }



    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(map, 0, 0, null);
    }

}
