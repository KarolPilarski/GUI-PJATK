package s22682Projekt1GUI;

class Thing{
    protected int id;
    protected String name;
    protected double space;

    Thing(int id,String name,double space){
        this.id=id;
        this.name=name;
        this.space=space;
    }
    Thing(int id,String name,double x,double y, double z){
        this.id=id;
        this.name=name;
        this.space=(x*y*z);
    }

    public double getSpace() {
        return space;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return "(Przedmiot nr." + id +
                ": nazwa: " + name + '\'' +
                ", objetosc: " + space +
                ')';
    }
}