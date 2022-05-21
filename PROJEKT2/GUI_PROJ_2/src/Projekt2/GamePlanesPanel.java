package Projekt2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class GamePlanesPanel extends JPanel {
    Image map;
    BufferedImage plane;
    BufferedImage planeinfected;
    LinkedList<Course> flightsList = new LinkedList<>();
    HashMap<String,Country> countries;
    GamePlanesPanel(Values values, HashMap<String,Country> countries){
        this.countries=countries;
        setBackground(Color.BLACK);
        map=new ImageIcon("images/map.png").getImage();
        try {
            plane= ImageIO.read(new File("images/plane4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            planeinfected= ImageIO.read(new File("images/planeinfected.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread flights = new Thread(()->{

            Object[] countriesArray =  countries.values().toArray();
            while(!values.Ended) {
                synchronized (countries) {
                    for (Country country : countries.values()) {
                        if (!country.airportClosed)
                            for (int i = 0; i < (int) (Math.random() * 10); i++) {
                                Country dest = (Country) countriesArray[(int) (Math.random() * countriesArray.length)];
                                boolean infected = (Math.random() / 20 < ((double) country.infected / (double) country.population));
                                if (country != dest && !dest.airportClosed)
                                    flightsList.add(new Course(country, dest, infected, country.airportCoordX, country.airportCoordY));
                            }
                    }
                }
                for (int i = 0; i <= values.Tempo; i = i + 10) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for(Course flight : flightsList){

                        if(flight.start.airportCoordX<flight.destination.airportCoordX){
                            flight.currentX=flight.start.airportCoordX+(int)(((double)flight.destination.airportCoordX-(double)flight.start.airportCoordX)*((double)i/(double)values.Tempo));
                        }else{
                            flight.currentX=flight.start.airportCoordX-(int)(((double)flight.start.airportCoordX-(double)flight.destination.airportCoordX)*((double)i/(double)values.Tempo));
                        }

                        if(flight.start.airportCoordY<flight.destination.airportCoordY){
                            flight.currentY=flight.start.airportCoordY+(int)((flight.destination.airportCoordY-flight.start.airportCoordY)*((double)i/(double)values.Tempo));
                        }else{
                            flight.currentY=flight.start.airportCoordY-(int)((flight.start.airportCoordY-flight.destination.airportCoordY)*((double)i/(double)values.Tempo));
                        }
                    }

                    repaint();

                }
                for(Course flight : flightsList) {
                    synchronized (countries) {
                        if (flight.infected && ((flight.destination.population - flight.destination.infected - flight.destination.vaccinated) > 5)) {
                            flight.destination.infected += 5;
                        }
                    }
                }
                flightsList.clear();
            }
        });
        flights.start();
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(map, 0, 0, null);
        for(Country country : countries.values()){
            if(country.airportClosed) g.setColor(Color.YELLOW);
            else if(country.infected>0) g.setColor(Color.RED);
            else g.setColor(Color.BLACK);

            g.fillOval(country.airportCoordX,country.airportCoordY,10,10);
        }

        for(Course flight: flightsList){
            if(flight.infected) g.drawImage(planeinfected,flight.currentX,flight.currentY,15,15,this);
            else g.drawImage(plane,flight.currentX,flight.currentY,15,15,this);
        }
    }
}
