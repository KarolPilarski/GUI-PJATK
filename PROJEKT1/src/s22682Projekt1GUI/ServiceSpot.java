package s22682Projekt1GUI;

abstract class ServiceSpot{
    protected double space;
    protected int id;
    boolean occupied=false;

    ServiceSpot(int id,double space){
        this.space=space;
    }
    ServiceSpot(int id,double x,double y, double z){
        this.space=(x*y*z);
    }

}

class CarServiceSpot extends ServiceSpot{

    CarServiceSpot(int id,double space) {
        super(id,space);
    }
    CarServiceSpot(int id,double x, double y, double z) {
        super(id,x, y, z);
    }


    public String toString() {
        return "(Stanowisko serwisowe nr."+id+" powierzchnia: "+space+")";
    }
}

class IndependentCarServiceSpot extends ServiceSpot{

    IndependentCarServiceSpot(int id,double space) {
        super(id,space);
    }
    IndependentCarServiceSpot(int id,double x, double y, double z) {
        super(id,x, y, z);
    }
    public String toString() {
        return "(Indywidualne stanowisko serwisowe nr."+id+" powierzchnia: "+space+")";
    }
}