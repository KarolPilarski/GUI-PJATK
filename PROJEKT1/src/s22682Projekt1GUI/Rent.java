package s22682Projekt1GUI;

class Rent{
    int cost;
    int id;
    Person person;
    Rentable thing;
    Date BeginningDate;
    Date ExpirationDate;

    Rent(int id,Person person,Rentable thing,Date BeginningDate,Date ExpirationDate,int cost){
        this.id=id;
        this.person=person;
        this.thing=thing;
        this.BeginningDate=BeginningDate;
        this.ExpirationDate=ExpirationDate;
        this.cost=cost;
    }

    @Override
    public String toString() {
        return "(Wynajem nr." +id+
                ": osoba: " + person +
                ", wynajety obiekt: " + thing +
                ", data rozpoczecia: " + BeginningDate +
                ", data wygasniecia: " + ExpirationDate +
                ", koszt: " + cost +
                ')';
    }
}