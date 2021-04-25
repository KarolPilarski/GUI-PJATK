package s22682Projekt1GUI;

import java.util.LinkedList;

class Person{
    protected int id;
    protected String imie,nazwisko,pesel,adress;
    protected Date birthday;
    protected Date FirstRent;
    protected LinkedList<TenantAlert> info = new LinkedList<TenantAlert>();
    protected LinkedList<TenantAlert> tenantalert = new LinkedList<TenantAlert>();

    Person(int id,String imie,String nazwisko,String pesel,String adress,Date birthday){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.pesel=pesel;
        this.adress=adress;
        this.birthday=birthday;
    }

    public Date getFirstRent() throws NeverRentException{
        return FirstRent;
    }

    @Override
    public String toString() {
        return  "(Osoba nr."+id+
                ": imie: " + imie +
                ", nazwisko: " + nazwisko +
                ", pesel: " + pesel +
                ", adres: " + adress +
                ", urodziny: " + birthday +
                ", data pierwszego wynajmu: " + FirstRent+")";
    }
}
