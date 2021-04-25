package s22682Projekt1GUI;

abstract class Vehicle{
    int id;
    protected int EngineCapacity;
    protected String color,manufacturer;

    Vehicle(int id,int EngineCapacity,String color,String manufacturer){
        this.id=id;
        this.EngineCapacity=EngineCapacity;
        this.color=color;
        this.manufacturer=manufacturer;
    }
}

class OffRoadCar extends Vehicle{
    protected double SuspensionHeight;

    OffRoadCar(int id,int EngineCapacity, String color, String manufacturer,double SuspensionHeight) {
        super(id,EngineCapacity, color, manufacturer);
        this.SuspensionHeight=SuspensionHeight;
    }

    public String toString() {
        return "(Samochod terenowy nr." +id+
                ": pojemnosc silnika: "+EngineCapacity+
                ", kolor: "+color+
                ", producent: "+manufacturer+
                ", wysokosc zawieszenia: " + SuspensionHeight +
                ')';
    }
}

class CityCar extends Vehicle{
    protected double lenght;

    CityCar(int id,int EngineCapacity, String color, String manufacturer,double lenght) {
        super(id,EngineCapacity, color, manufacturer);
        this.lenght=lenght;
    }

    public String toString() {
        return "(Samochod miejski nr." +id+
                ": pojemnosc silnika: "+EngineCapacity+
                ", kolor: "+color+
                ", producent: "+manufacturer+
                ", dlugosc: " + lenght +
                ')';
    }
}

class Motorcycle extends Vehicle{
    protected int volume;

    Motorcycle(int id,int EngineCapacity, String color, String manufacturer,int volume) {
        super(id,EngineCapacity, color, manufacturer);
        this.volume=volume;
    }

    public String toString() {
        return "(Motocykl nr." +id+
                ": pojemnosc silnika: "+EngineCapacity+
                ", kolor: "+color+
                ", producent: "+manufacturer+
                ", glosnosc: " + volume +
                ')';
    }
}

class Amphibious extends Vehicle{
    protected int range;

    Amphibious(int id,int EngineCapacity, String color, String manufacturer,int range) {
        super(id,EngineCapacity, color, manufacturer);
        this.range=range;
    }

    public String toString() {
        return "(Amfibia nr." +id+
                ": pojemnosc silnika: "+EngineCapacity+
                ", kolor: "+color+
                ", producent: "+manufacturer+
                ", zasieg: " + range +
                ')';
    }
}
