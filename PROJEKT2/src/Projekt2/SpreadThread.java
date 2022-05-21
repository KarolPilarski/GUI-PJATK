package Projekt2;

import java.time.LocalDate;
import java.util.HashMap;

public class SpreadThread extends Thread{
    HashMap<String,Country> countries;
    Values values;
    GameLeftPanel glp;
    LocalDate CurrentDate;
    SpreadThread(HashMap<String,Country> countries, Values values, GameLeftPanel glp, LocalDate CurrentDate){
        this.glp=glp;
        this.countries=countries;
        this.values=values;
        this.CurrentDate=CurrentDate;
    }
    public void run(){
        synchronized (countries){
        for (Country country : countries.values()) {
            if (country.infected != 0) country.infected += (int) (country.infected * values.SpreadConverter) + 2;
            if (country.infected > (country.population - country.vaccinated))
                country.infected = (country.population - country.vaccinated);

            for (Country neighbour : country.neighbours) {
                if ((Math.random() / 10) <= ((double) country.infected / (double) country.population)) {
                    neighbour.infected += 5;
                    if (neighbour.infected > (neighbour.population - neighbour.vaccinated))
                        neighbour.infected = (neighbour.population - neighbour.vaccinated);
                }
            }

            if (country.infected >= country.currentMilestone) {
                new Thread(()->{
                    synchronized (glp) {
                        glp.addPanelContent(CurrentDate + ": " + ((country.currentMilestone == 1) ? ("First infection in ") : (country.currentMilestone + " infections in ")) + country.name + ".");
                    }
                }).start();
                country.currentMilestone = country.currentMilestone * 1000;
            }
        }
        }
    }
}
