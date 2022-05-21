package Projekt2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class GameRightPanel extends JPanel implements ActionListener {
    JLabel content = new JLabel();
    JLabel flagLabel = new JLabel();
    HashMap<String,Country> countries;
    Country country=null;
    GameRightPanel(HashMap<String,Country> countries){
        this.countries=countries;

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(250,500));
        setBackground(new Color(90,90,90));
        content.setForeground(Color.WHITE);
        add(flagLabel);
        add(content);

        Thread RightThread = new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(country!=null) {
                    String result = "<html><center><br><h1><b>" + country.name +
                            "</b></h1><br><br>Total polulation: " + country.population +
                            "<br>Healthy population: " + (country.population - country.infected - country.vaccinated) +
                            "<br>Infected: " + country.infected +
                            "<br>Vaccinated: " + country.vaccinated+
                            "<br><br><br>Borders: "+((country.bordersClosed)?"Closed":"Open")+
                            "<br>Airports: "+((country.airportClosed)?"Closed":"Open")+
                            "<br>Ports: "+((country.shipsClosed)?"Closed":"Open");

                    content.setText(result);
                }
            }
        });
        RightThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        synchronized (countries){
            country=countries.get(e.getActionCommand());
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(country.flagImage));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Image dimg = img.getScaledInstance(150, 80, Image.SCALE_SMOOTH);
        Icon flag=new ImageIcon(dimg);
        flagLabel.setIcon(flag);
        flagLabel.setPreferredSize(new Dimension(150,90));
    }

}