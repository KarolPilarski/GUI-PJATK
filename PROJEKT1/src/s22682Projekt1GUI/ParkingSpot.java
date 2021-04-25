package s22682Projekt1GUI;

import java.util.LinkedList;

class ParkingSpot implements Rentable{
    protected int id;
    protected double space;
    protected Vehicle CurrentParking=null;
    protected LinkedList<Person> Entitled=new LinkedList<>();

    ParkingSpot(double space,int id){
        this.space=space;
        this.id=id;
    }

    @Override
    public String DisplayContent() {
        if(CurrentParking!=null) return (CurrentParking.toString());
        else return ("Miejsce parkingowe jest puste");
    }

    @Override
    public String toString() {
        return "(Miejsce parkingowe nr." + id +
                ": przestrzen: " + space +
                ", Obecnie zaparkowany pojazd: " + CurrentParking +
                ", Uprawnieni: " + Entitled +
                ')';
    }
}
