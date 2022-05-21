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

public class GameShipsPanel extends JPanel {
    LinkedList<Course> coursesList = new LinkedList<>();

    Image map;
    BufferedImage ship;
    BufferedImage infectedship;

    HashMap<String,Country> countries;

    GameShipsPanel(Values values, HashMap<String,Country> countries){

        this.countries=countries;
        setBackground(Color.BLACK);
        map=new ImageIcon("images/map.png").getImage();

        try {
            ship= ImageIO.read(new File("images/ship.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            infectedship= ImageIO.read(new File("images/shipinfected.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread flights = new Thread(()->{

            Object[] seaNeigboursArray;
            while(!values.Ended) {
                synchronized (countries) {
                    for (Country country : countries.values()) {
                        if (!country.shipsClosed && country.portCoordY != 0 && country.portCoordX != 0) {
                            seaNeigboursArray = country.seaNeighbours.toArray();
                            for (int i = 0; i < (int) (Math.random() * 10); i++) {
                                Country dest = (Country) seaNeigboursArray[(int) (Math.random() * seaNeigboursArray.length)];
                                boolean infected = (Math.random() / 20 < ((double) country.infected / (double) country.population));
                                if (country != dest && !dest.shipsClosed)
                                    coursesList.add(new Course(country, dest, infected, country.portCoordX, country.portCoordY));
                            }
                        }
                    }
                }
                for (int i = 0; i <= values.Tempo; i = i + 10) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for(Course course : coursesList){

                        if(course.start.airportCoordX<course.destination.airportCoordX){
                            course.currentX=course.start.portCoordX+(int)(((double)course.destination.portCoordX-(double)course.start.portCoordX)*((double)i/(double)values.Tempo));
                        }else{
                            course.currentX=course.start.portCoordX-(int)(((double)course.start.portCoordX-(double)course.destination.portCoordX)*((double)i/(double)values.Tempo));
                        }

                        if(course.start.airportCoordY<course.destination.airportCoordY){
                            course.currentY=course.start.portCoordY+(int)((course.destination.portCoordY-course.start.portCoordY)*((double)i/(double)values.Tempo));
                        }else{
                            course.currentY=course.start.portCoordY-(int)((course.start.portCoordY-course.destination.portCoordY)*((double)i/(double)values.Tempo));
                        }
                    }

                    repaint();

                }
                for(Course course : coursesList) {
                    synchronized (countries) {
                        if (course.infected && ((course.destination.population - course.destination.infected - course.destination.vaccinated) > 200)) {
                            course.destination.infected += 200;
                        }
                    }
                }
                coursesList.clear();
            }
        });
        flights.start();
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(map, 0, 0, null);

        for(Country country : countries.values()){
            if(country.portCoordX!=0&&country.portCoordY!=0) {
                if (country.shipsClosed) g.setColor(Color.YELLOW);
                else if (country.infected > 0) g.setColor(Color.RED);
                else g.setColor(Color.BLACK);

                g.fillOval(country.portCoordX, country.portCoordY, 10, 10);
            }
        }

        for(Course course: coursesList){
            if(course.infected) g.drawImage(infectedship,course.currentX,course.currentY,20,20,this);
            else g.drawImage(ship,course.currentX,course.currentY,20,20,this);
        }
    }

}
