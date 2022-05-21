package Projekt2;

import java.util.HashMap;

public class VaccinesThread extends Thread{
    HashMap<String,Country> countries;
    Values values;
    VaccinesThread(HashMap<String,Country> countries,Values values){
        this.countries=countries;
        this.values=values;
    }

    public void run(){
        synchronized (countries){
            for(Country country : countries.values()){
                int tmp= (int)(country.population*values.VaccinatedConverter);
                if(tmp>(country.population-country.infected-country.vaccinated)) tmp=(country.population-country.infected-country.vaccinated);
                country.vaccinated+=tmp;
                values.Points+=tmp/45;

            }
        }
    }
}
