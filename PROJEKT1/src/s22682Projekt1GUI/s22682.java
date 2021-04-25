package s22682Projekt1GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static s22682Projekt1GUI.Methods.*;

public class s22682 {
    public static void main(String[] args) {

        Date TodayDate = new Date(1, 1, 2021);

        Count IdPrivateWarehouse = new Count();
        Count IdServiceWarehouse = new Count();
        Count IdParkingSpot = new Count();
        Count IdServiceSpot = new Count();
        Count IdPerson = new Count();
        Count IdThing = new Count();
        Count IdRent = new Count();
        Count IdVehicle = new Count();
        Count IdRepair = new Count();
        Count IdTenantAlert = new Count();



        LinkedList<Service> services = new LinkedList<>();
        services.add(new Service("Serwis 1"));

        services.get(0).carservice.add(new CarServiceSpot(IdServiceSpot.getNext(), 100));
        services.get(0).carservice.add(new CarServiceSpot(IdServiceSpot.getNext(), 120));
        services.get(0).carservice.add(new CarServiceSpot(IdServiceSpot.getNext(), 90));
        services.get(0).carservice.add(new CarServiceSpot(IdServiceSpot.getNext(), 115));
        services.get(0).carservice.add(new CarServiceSpot(IdServiceSpot.getNext(), 160));

        services.get(0).indcarservice.add(new IndependentCarServiceSpot(IdServiceSpot.getNext(), 90));
        services.get(0).indcarservice.add(new IndependentCarServiceSpot(IdServiceSpot.getNext(), 115));
        services.get(0).indcarservice.add(new IndependentCarServiceSpot(IdServiceSpot.getNext(), 160));

        services.get(0).pwarehouses.add(new PrivateWarehouse(200, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(100, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(300, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(250, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(50, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(320, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(150, IdPrivateWarehouse.getNext()));
        services.get(0).pwarehouses.add(new PrivateWarehouse(70, IdPrivateWarehouse.getNext()));

        services.get(0).servicewarehouses.add(new ServiceWarehouse(320, IdServiceWarehouse.getNext()));
        services.get(0).servicewarehouses.add(new ServiceWarehouse(150, IdServiceWarehouse.getNext()));
        services.get(0).servicewarehouses.add(new ServiceWarehouse(70, IdServiceWarehouse.getNext()));

        services.get(0).parkingspots.add(new ParkingSpot(10, IdParkingSpot.getNext()));
        services.get(0).parkingspots.add(new ParkingSpot(15, IdParkingSpot.getNext()));
        services.get(0).parkingspots.add(new ParkingSpot(12, IdParkingSpot.getNext()));
        services.get(0).parkingspots.add(new ParkingSpot(8, IdParkingSpot.getNext()));
        services.get(0).parkingspots.add(new ParkingSpot(10, IdParkingSpot.getNext()));

        HashMap<Person, LinkedList<Rent>> people = new HashMap<>();

        Person p1 = (new Person(IdPerson.getNext(),"Karol","Pilarski", "7832877328", "Koszykowa 123, Warszawa", (new Date(9, 12, 2000))));
        Person p2 = (new Person(IdPerson.getNext(),"Dawid", "Suski", "1100110011", "Prosta 3, Legionowo", (new Date(13, 2, 1980))));
        Person p3 = (new Person(IdPerson.getNext(),"Albert", "Szczesny", "4321110011", "Fioletowa 6, Legionowo", (new Date(13, 2, 1980))));
        Person p4 = (new Person(IdPerson.getNext(),"Papiez", "Franciszek", "8398239222", "Zielona 24, Wroclaw", (new Date(10, 7, 1985))));
        Person p5 = (new Person(IdPerson.getNext(),"Kinga", "Gorzkowska", "0032018392", "Aluzyjna 67, Warszawa", (new Date(25, 9, 1968))));

        people.put(p1, new LinkedList<>());
        people.put(p2, new LinkedList<>());
        people.put(p3, new LinkedList<>());
        people.put(p4, new LinkedList<>());
        people.put(p5, new LinkedList<>());

        Person CurrentUser = p1;

        LinkedList<Thing> things = new LinkedList<>();

        things.add(new Thing(IdThing.getNext(),"Opony",8));
        things.add(new Thing(IdThing.getNext(),"Stare czesci",30));
        things.add(new Thing(IdThing.getNext(),"Biurko",12));
        things.add(new Thing(IdThing.getNext(),"Wiedza dr.Tomaszewskiego z zakresu jezyka Java",1000));
        things.add(new Thing(IdThing.getNext(),"Ksiazki",10));
        things.add(new Thing(IdThing.getNext(),"Helikopter",80));
        things.add(new Thing(IdThing.getNext(),"Meble",30));
        things.add(new Thing(IdThing.getNext(),"Sadzonki krzewow brzozy",45));
        things.add(new Thing(IdThing.getNext(),"Lodowki",25));

        LinkedList<Vehicle> cars = new LinkedList<>();
        cars.add(new CityCar(IdVehicle.getNext(),2,"bialy","Skoda",3.5));
        cars.add(new CityCar(IdVehicle.getNext(),3,"zielony","Fiat",4.0));
        cars.add(new CityCar(IdVehicle.getNext(),1,"niebieski","Hyundai",5.0));
        cars.add(new OffRoadCar(IdVehicle.getNext(),4,"bordowy","Jeep",35));
        cars.add(new OffRoadCar(IdVehicle.getNext(),3,"piaskowy","Volvo",40));
        cars.add(new Motorcycle(IdVehicle.getNext(),2,"czarny","Honda",20));
        cars.add(new Motorcycle(IdVehicle.getNext(),3,"fioletowy","Yamaha",28));
        cars.add(new Amphibious(IdVehicle.getNext(),5,"niebieski","Dutton",200));

        LinkedList<Repair> queueRepair = new LinkedList<>();
        LinkedList<Repair> currentRepair = new LinkedList<>();
        LinkedList<Repair> historyRepair = new LinkedList<>();

        currentRepair.add(new Repair(IdRepair.getNext(),p3,cars.get(3),TodayDate,services.get(0).carservice.get(1),services.get(0).parkingspots.get(1)));
        currentRepair.add(new Repair(IdRepair.getNext(),p1,cars.get(1),TodayDate,services.get(0).carservice.get(2),services.get(0).parkingspots.get(2)));


        people.get(p1).add(new Rent(IdRent.getNext(),p1, services.get(0).pwarehouses.get(2), TodayDate, TodayDate.plusDays(TodayDate, 14),200));
        services.get(0).pwarehouses.get(2).occupied=true;
        people.get(p3).add(new Rent(IdRent.getNext(),p3, services.get(0).pwarehouses.get(3), TodayDate, TodayDate.plusDays(TodayDate, 14),300));
        services.get(0).pwarehouses.get(3).occupied=true;
        people.get(p5).add(new Rent(IdRent.getNext(),p5, services.get(0).pwarehouses.get(0), TodayDate, TodayDate.plusDays(TodayDate, 14),400));
        services.get(0).pwarehouses.get(0).occupied=true;

        people.get(p2).add(new Rent(IdRent.getNext(),p2, services.get(0).parkingspots.get(1), TodayDate, TodayDate.plusDays(TodayDate, 14),300));
        services.get(0).parkingspots.get(1).CurrentParking=cars.get(1);
        people.get(p4).add(new Rent(IdRent.getNext(),p4, services.get(0).parkingspots.get(3), TodayDate, TodayDate.plusDays(TodayDate, 14),300));
        services.get(0).parkingspots.get(3).CurrentParking=cars.get(5);


        LinkedList<Rent> historyRent = new LinkedList<>();


        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TodayDate.nextDay();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TodayDate.nextDay();
                //Koniec wynajmow
                for(LinkedList<Rent> p : people.values())
                    for(Rent rent : p){
                        if(rent.ExpirationDate.dateNumber()<TodayDate.dateNumber()){
                            System.out.println("Wynajem o id "+rent.id+" wygasl.");
                            historyRent.add(rent);
                            p.remove(rent);
                            rent.person.info.add(new TenantAlert(IdTenantAlert.getNext(),rent));
                        }
                    }
                for(Person p : people.keySet())
                    for(TenantAlert info : p.info){
                        if(info.ExpirationDate.dateNumber()<TodayDate.dateNumber()) {
                            p.info.remove(info);
                            p.tenantalert.add(info);
                        }
                    }
                //Koniec napraw
                for(Repair repair : currentRepair)
                    if(repair.EndDate.dateNumber()<TodayDate.dateNumber()){
                        System.out.println("naprawa o id "+repair.id+" zostala zakonczona.");
                        historyRepair.add(repair);
                        currentRepair.remove(repair);
                        repair.servicespot.occupied=false;
                    }
                //Rozpoczecie napraw
                for(Repair qr : queueRepair)
                    for(CarServiceSpot cs : services.get(0).carservice)
                        if(!cs.occupied){
                            currentRepair.add(new Repair(qr.id,qr.client,qr.car,TodayDate,cs,qr.parkingspot));
                            cs.occupied=true;
                            queueRepair.remove(qr);
                        }
            }
        });
        thread.start();

        int chosen = 0;
        Scanner scanner = new Scanner(System.in);
        while (chosen != 1) {
            System.out.println("\n\nWybierz operacje ktora chcesz wykonac:\n" +
                    "1. Zakoncz dzialanie programu\n" +
                    "2. Zmien uzytkownika\n" +
                    "3. Wypisz swoje dane\n" +
                    "4. Wyswietl wolne magazyny\n" +
                    "5. Wynajmij nowe pomieszczenie\n" +
                    "6. Wybierz swoje pomieszczenie i pokaz jego zawartosc\n" +
                    "7. Zaparkuj/Wloz nowe przedmioty do magazynu\n" +
                    "8. Wyjmij przedmiot/Pojazd\n" +
                    "9. Nadaj uprawnienia do obiektu\n" +
                    "10. Rozpocznij zgloszenie serwisowe\n" +
                    "11. Zapisz stan aplikacji\n" +
                    "12. Odejmuj uprawnienia do obiektu\n" +
                    "13. Zaplac dlug\n"+
                    "(Wpisz odpowiednie id operacji)");

            chosen = scanner.nextInt();

            switch (chosen) {
                case 1:
                    break;
                case 2: {
                    CurrentUser=ChangeUser(people);
                    break;
                }
                case 3: {
                    UserInfo(CurrentUser,people);
                    break;
                }
                case 4: {
                    EmptyWarehouses(services);
                    break;
                }
                case 5: {
                    try {
                        RentWarehouse(CurrentUser,services,people,TodayDate,IdRent);
                    } catch (PromlematicTenantException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 6: {
                    ShowContent(CurrentUser,people);
                    break;
                }
                case 7: {
                    PutItem(CurrentUser,services,people,things,cars);
                    break;
                }
                case 8: {
                    RemoveItem(CurrentUser,services,people);
                    break;
                }
                case 9: {
                    GivePermission(CurrentUser,people);
                    break;
                }
                case 10: {
                    StartRepair(CurrentUser,services,people,TodayDate,queueRepair,currentRepair,IdRepair,cars);
                    break;
                }
                case 11: {
                    SaveToFile(services,queueRepair,currentRepair,historyRepair);
                    break;
                }
                case 12: {
                    RemovePermission(CurrentUser,people);
                    break;
                }
                case 13: {
                    PayOffTheDebt(CurrentUser);
                    break;
                }
                default: {
                    System.out.println("Niepoprawne dane wejsciowe");
                    break;
                }
            }
        }
    }
}