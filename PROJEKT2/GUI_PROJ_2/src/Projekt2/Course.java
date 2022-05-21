package Projekt2;

public class Course {
    Country start;
    Country destination;
    Boolean infected;

    int currentX,currentY;

    Course(Country start,Country destination,Boolean infected,int currentX,int currentY){
        this.start=start;
        this.destination=destination;
        this.infected=infected;
        this.currentX=currentX;
        this.currentY=currentY;
    }
}
