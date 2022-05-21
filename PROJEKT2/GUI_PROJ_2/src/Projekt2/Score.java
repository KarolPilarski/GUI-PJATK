package Projekt2;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class Score implements Serializable {
    String type;
    LocalDate endDate;
    String player;
    String difficulty;
    long score;
    Score(String type,LocalDate endDate, String player, String difficulty){
        this.type=type;
        this.endDate=endDate;
        this.player=player;
        this.difficulty=difficulty;
        if(type.equals("vaccinated")||type.equals("cured")){
            score=10000000/DAYS.between(LocalDate.of(2021,1,1),endDate);
        }else if(type.equals("infected")){
            score=10000/DAYS.between(LocalDate.of(2021,1,1),endDate);
        }else{
            score=0;
        }
        if(difficulty.equals("Normal")) score=(long)(score*1.5);
        else if(difficulty.equals("Hard")) score=(long)(score*2);
    }

    public String toString(){
        return "Player: "+player+"        Result: "+type+"        Score: "+score+"        Difficulty: "+difficulty;
    }
}
