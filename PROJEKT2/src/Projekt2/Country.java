package Projekt2;

import java.util.LinkedList;

public class Country {
    String name;
    int population;
    int infected;
    int vaccinated;
    int currentMilestone;
    String flagImage;
    LinkedList<Country> neighbours;
    LinkedList<Country> seaNeighbours;

    int airportCoordX, airportCoordY,portCoordX, portCoordY;
    double airportCloseNumber,shipsCloseNumber,borderCloseNumber;

    boolean airportClosed,shipsClosed,bordersClosed;

    Country(String name,int pupulation,int infected,int vaccinated,int currentMilestone,String flagImage,LinkedList<Country> neighbours,int airportCoordX,int airportCoordY, double airportCloseNumber,double shipsCloseNumber,double bordersCloseNumber,int portCoordX, int portCoordY,LinkedList<Country> seaNeighbours){
        this.name=name;
        this.population=pupulation;
        this.infected=infected;
        this.vaccinated=vaccinated;
        this.currentMilestone=currentMilestone;
        this.flagImage=flagImage;
        this.neighbours=neighbours;
        this.seaNeighbours=seaNeighbours;

        this.airportCoordY=airportCoordY;
        this.airportCoordX=airportCoordX;
        this.airportCloseNumber=airportCloseNumber;
        this.shipsCloseNumber=shipsCloseNumber;
        this.borderCloseNumber=bordersCloseNumber;
        this.portCoordX=portCoordX;
        this.portCoordY=portCoordY;

        this.airportClosed=false;
        if(shipsCloseNumber==0.0) this.shipsClosed=true;
        else this.shipsClosed=false;
        this.bordersClosed=false;
    }

}
