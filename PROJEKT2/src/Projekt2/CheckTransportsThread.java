package Projekt2;

import java.time.LocalDate;
import java.util.HashMap;

public class CheckTransportsThread extends Thread{
    HashMap<String,Country> countries;
    Values values;
    GameLeftPanel glp;
    LocalDate ld;
    CheckTransportsThread(HashMap<String,Country> countries, Values values, GameLeftPanel glp, LocalDate ld){
        this.countries=countries;
        this.values=values;
        this.glp=glp;
        this.ld=ld;
    }

    public void run(){
        while(!values.Ended){
            synchronized (countries) {
                for (Country country : countries.values()) {
                    if (!country.airportClosed && (((double) country.infected / (double) country.population)) > country.airportCloseNumber) {
                        country.airportClosed = true;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " closed its airports.");
                            }
                        }).start();

                    } else if (country.airportClosed && (((double) country.infected / (double) country.population)) < country.airportCloseNumber) {
                        country.airportClosed = false;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " opened its airports.");
                            }
                        }).start();
                    }

                    if (!country.bordersClosed && (((double) country.infected / (double) country.population)) > country.borderCloseNumber) {
                        country.bordersClosed = true;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " closed its borders.");
                            }
                        }).start();
                    } else if (country.bordersClosed && (((double) country.infected / (double) country.population)) < country.borderCloseNumber) {
                        country.bordersClosed = false;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " opened its closed.");
                            }
                        }).start();
                    }

                    if(country.shipsCloseNumber!=0.0)
                    if (!country.shipsClosed && (((double) country.infected / (double) country.population)) > country.shipsCloseNumber) {
                        country.shipsClosed = true;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " closed its ports.");
                            }
                        }).start();
                    } else if (country.shipsClosed && (((double) country.infected / (double) country.population)) < country.shipsCloseNumber) {
                        country.shipsClosed = false;
                        new Thread(()->{
                            synchronized (glp) {
                                glp.addPanelContent(ld + ": " + country.name + " opened its ports.");
                            }
                        }).start();
                    }
                }
            }
        }
    }
}
