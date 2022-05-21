package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class GameBordersPanel extends JPanel {
    Image map;
    Values values;
    HashMap<String,Country> countries;
    LinkedList<Point> points = new LinkedList<>();

    GameBordersPanel(Values values, HashMap<String,Country> countries){
        this.countries=countries;
        this.values=values;
        setBackground(Color.BLACK);
        map=new ImageIcon("images/map.png").getImage();



        Thread populationMovement = new Thread(()->{
            while(!values.Ended) {
                    for (Country country : countries.values()) {
                        if (country.infected > 0) {
                            for (Country neighbour : country.neighbours) {
                                synchronized(country) {
                                    if (!country.bordersClosed && !neighbour.bordersClosed)
                                        for (double i = 0.05; i < 0.9; i = i + 0.15) {
                                            int X, Y;
                                            if (country.airportCoordX < neighbour.airportCoordX) {
                                                X = country.airportCoordX + (int) (((double) neighbour.airportCoordX - (double) country.airportCoordX) * i);
                                            } else {
                                                X = country.airportCoordX - (int) (((double) country.airportCoordX - (double) neighbour.airportCoordX) * i);
                                            }

                                            if (country.airportCoordY < neighbour.airportCoordY) {
                                                Y = country.airportCoordY + (int) (((double) neighbour.airportCoordY - (double) country.airportCoordY) * i);
                                            } else {
                                                Y = country.airportCoordY - (int) (((double) country.airportCoordY - (double) neighbour.airportCoordY) * i);
                                            }
                                            points.add(new Point(country, neighbour, null, X, Y, i));
                                        }
                                }
                            }
                        }
                    }

                for(double i=0;i<0.15;i=i+0.015){
                    for(Point point : points){
                        if(point.start.airportCoordX<point.destination.airportCoordX){
                            point.currentX=point.start.airportCoordX+(int)(((double)point.destination.airportCoordX-(double)point.start.airportCoordX)*(i+point.i));
                        }else{
                            point.currentX=point.start.airportCoordX-(int)(((double)point.start.airportCoordX-(double)point.destination.airportCoordX)*(i+point.i));
                        }

                        if(point.start.airportCoordY<point.destination.airportCoordY){
                            point.currentY=point.start.airportCoordY+(int)(((double)point.destination.airportCoordY-(double)point.start.airportCoordY)*(i+point.i));
                        }else{
                            point.currentY=point.start.airportCoordY-(int)(((double)point.start.airportCoordY-(double)point.destination.airportCoordY)*(i+point.i));
                        }
                    }
                    repaint();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                points.clear();
            }
        });
        populationMovement.start();
    }

    public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(map, 0, 0, null);

        g.setColor(Color.RED);
        for(Point point : points){
            g.fillOval(point.currentX,point.currentY,7,7);
        }

        for(Country country : countries.values()){
            if(country.infected>0&&!country.bordersClosed)g.setColor(Color.RED);
            else if(country.infected>0&&country.bordersClosed)g.setColor(Color.YELLOW);
            else g.setColor(Color.BLACK);
            g.fillOval(country.airportCoordX,country.airportCoordY,20,20);
        }
    }
}
