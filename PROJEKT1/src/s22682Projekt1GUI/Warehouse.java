package s22682Projekt1GUI;

import java.util.LinkedList;

class Warehouse{
    protected int id;
    public double StorageSpace;
    protected double OccupiedSpace=0;
    protected LinkedList<Thing> Content=new LinkedList<>();

    public void add(Thing thing) throws TooManyThingsException{
        if(OccupiedSpace+thing.getSpace()>StorageSpace) throw new TooManyThingsException("Remove some old items to insert a new one");
        else{
            Content.add(thing);
            System.out.println(thing.getName()+" zostal pomyslnie dodany do magazynu o id "+id);
        }
    }

    public void remove(Thing thing){
        Content.remove(thing);
    }


    public int compareTo(Object o) {
        Warehouse z =(Warehouse)o;
        return (int)(z.StorageSpace-this.StorageSpace);
    }

    public String DisplayContent() {
        return (Content.toString());
    }
}


class PrivateWarehouse extends Warehouse implements Rentable,Comparable{
    boolean occupied=false;
    public LinkedList<Person>Entitled=new LinkedList<>();

    PrivateWarehouse(double StorageSpace,int id){
        this.StorageSpace=StorageSpace;
        this.id=id;
    }

    @Override
    public String toString() {
        return "(Magazyn prywatny nr." + id +
                ": Laczna powierzchnia: " + StorageSpace +
                ", Wynajmowany: " + occupied +
                ", Uprawnieni: " + Entitled +
                ", Zajeta powierzchnia: " + OccupiedSpace +
                ", Zawartosc: " + Content +
                ')';
    }

}

class ServiceWarehouse extends Warehouse implements Comparable{
    protected double OccupiedSpace=0;
    protected LinkedList<Thing>Content=new LinkedList<>();

    ServiceWarehouse(double StorageSpace,int id){
        this.StorageSpace=StorageSpace;
        this.id=id;
    }

    @Override
    public String toString() {
        return "(Magazyn serwisowy nr." + id +
                ": Laczna powierzchnia: " + StorageSpace +
                ", Zajmowana powierzchnia: " + OccupiedSpace +
                ", Zawartosc: " + Content +
                ')';
    }
}