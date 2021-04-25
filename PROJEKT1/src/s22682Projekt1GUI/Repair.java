package s22682Projekt1GUI;

class Repair{
    int id,cost;
    ServiceSpot servicespot;
    Vehicle car;
    Date BeginnigDate, EndDate;
    Person client;
    ParkingSpot parkingspot;

    Repair(int id,Person client,Vehicle car,Date BeginnigDate,ServiceSpot servicespot,ParkingSpot parkingspot){
        int days=(int)(Math.random()*5)+1;
        this.id=id;
        this.BeginnigDate=BeginnigDate;
        this.EndDate=(BeginnigDate.plusDays(BeginnigDate,(days)));
        this.client=client;
        this.servicespot=servicespot;
        this.parkingspot=parkingspot;
        this.car=car;
        this.cost=30*days;
    }

    @Override
    public String toString() {
        return "(Naprawa nr." + id +
                ": koszt: " + cost +
                ", miejsce serwisowe: " + servicespot +
                ", samochod: " + car +
                ", data rozpoczecia: " + BeginnigDate +
                ", data zakonczenia: " + EndDate +
                ", klient: " + client +
                ", miejsce parkingowe: " + parkingspot +
                ')';
    }
}
