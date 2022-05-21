package Projekt2;

import java.util.HashMap;

public class CureThread extends Thread{

    HashMap<String,Country> countries;
    Values values;

    CureThread(HashMap<String,Country> countries, Values values){
        this.countries=countries;
        this.values=values;
    }

    public void run(){
        synchronized (countries){
            for(Country country : countries.values()){
                int tmp= (int)(country.infected*values.CurabilityConverter)+1;
                if((tmp/15)<0)values.Points+=tmp/15;
                else values.Points+=1;
                country.infected-=tmp;
                if(country.infected<0) country.infected=0;
            }
        }
    }
}
