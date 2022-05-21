package Projekt2;

public class Point extends Course{
    double i;
    Point(Country start,Country destination,Boolean infected,int currentX,int currentY,double i){
        super(start,destination,infected,currentX,currentY);
        this.i=i;
    }
}
