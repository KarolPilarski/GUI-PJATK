package s22682Projekt1GUI;

import java.util.LinkedList;

class Service{
    protected String name;
    public LinkedList<CarServiceSpot> carservice = new LinkedList<>();
    public LinkedList<IndependentCarServiceSpot>indcarservice = new LinkedList<>();
    public LinkedList<PrivateWarehouse>pwarehouses = new LinkedList<>();
    public LinkedList<ParkingSpot>parkingspots = new LinkedList<>();
    public LinkedList<ServiceWarehouse>servicewarehouses = new LinkedList<>();


    Service(String name){
        this.name=name;
    }

}
