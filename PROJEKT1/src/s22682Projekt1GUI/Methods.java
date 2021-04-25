package s22682Projekt1GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Methods {
    static void EmptyWarehouses(LinkedList<Service> services) {
        int i=0;
        for(Service s : services) {
            System.out.println("Serwis nr:"+i+"   -   "+s.name+": \n");
            for (PrivateWarehouse pw : s.pwarehouses) {
                if (!pw.occupied) System.out.println(pw);
            }
        }
    }

    static void StartRepair(Person CurrentUser, LinkedList<Service>services, HashMap<Person, LinkedList<Rent>> people, Date TodayDate, LinkedList<Repair> queueRepair, LinkedList<Repair> currentRepair, Count IdRepair, LinkedList<Vehicle> cars) {
        System.out.println("Chcesz rozpoczac naprawe indywidualna czy serwisowa?(indywidualna-0/serwisowa-1)");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        System.out.println("Wpisz numer serwisu");
        int service = scanner.nextInt();
        ServiceSpot sSpot=null;
        if(type==0){
            for(IndependentCarServiceSpot iss : services.get(service).indcarservice){
                if(!iss.occupied){
                    sSpot=iss;
                    sSpot.occupied=true;
                    break;
                }
            }

        }else if(type==1){
            for(CarServiceSpot css : services.get(service).carservice){
                if(!css.occupied){
                    sSpot=css;
                    sSpot.occupied=true;
                    break;
                }
            }
        }

        System.out.println("Wybierz miejsce postojowe: \n");
        for (ParkingSpot ps : services.get(service).parkingspots) {
            if(ps.CurrentParking==null)System.out.println(ps.id + "   " + ps);
        }
        int parkingspot = scanner.nextInt();

        ParkingSpot pSpot = null;
        for (ParkingSpot ps : services.get(service).parkingspots) {
            if (parkingspot == ps.id) pSpot = ps;
        }

        System.out.println("Wybierz pojazd ktory chcesz oodac do serwisu: ");
        Vehicle vehicle=null;
        for (Vehicle vc : cars) {
            System.out.println(vc.id + "   " + vc.manufacturer + "  - " + vc.color);
        }
        int car = scanner.nextInt();
        for (Vehicle vc : cars) {
            if(car==vc.id) vehicle=vc;
        }

        if(sSpot!=null){
            System.out.println("Naprawa zostala rozpoczeta\n");
            currentRepair.add(new Repair(IdRepair.getNext(),CurrentUser,vehicle,TodayDate,sSpot,pSpot));
        }else{
            System.out.println("Naprawa zostala dodana do kolejki\n");
            queueRepair.add(new Repair(IdRepair.getNext(),CurrentUser,vehicle,null,null,pSpot));
            pSpot.CurrentParking=vehicle;
        }

    }

    static void SaveToFile(LinkedList<Service>services,LinkedList<Repair> queueRepair,LinkedList<Repair> currentRepair,LinkedList<Repair> historyRepair) {
        try{
            FileWriter FileWarehouses = new FileWriter("warehouses.txt");
            FileWarehouses.write("");
            for(Service s : services){
                FileWarehouses.write(s.name+":\n");
                s.pwarehouses.sort((o1, o2) -> (int)(o1.StorageSpace-o2.StorageSpace));
                FileWarehouses.write("\tMagazyny prywatne:\n");
                for(PrivateWarehouse pw : s.pwarehouses){
                    pw.Content.sort((o1, o2) -> (int)(o1.space+o2.space));
                    FileWarehouses.write("\t\t"+pw.toString()+"\n");
                }
                FileWarehouses.write("\tMagazyny serwisowe:\n");
                s.servicewarehouses.sort((o1, o2) -> (int)(o1.StorageSpace-o2.StorageSpace));
                for(ServiceWarehouse sw : s.servicewarehouses){
                    sw.Content.sort((o1, o2) -> (int)(o1.space+o2.space));
                    FileWarehouses.write("\t\t"+sw.toString()+"\n");
                }
            }
            FileWarehouses.flush();



            FileWriter FileServices = new FileWriter("services.txt");

            FileServices.write("Kolejka napraw:\n");
            for(Repair r : queueRepair){
                FileServices.write("\t"+r.toString()+"\n");
            }

            FileServices.write("\n\nObecne naprawy:\n");
            for(Repair r : currentRepair){
                FileServices.write("\t"+r.toString()+"\n");
            }

            FileServices.write("\n\nZakonczone naprawy:\n");
            for(Repair r : historyRepair){
                FileServices.write("\t"+r.toString()+"\n");
            }

            FileServices.flush();


        }catch (IOException e) {
            System.err.println("Wystapil blad podczas zapisu do pliku");
            e.printStackTrace();
        }
    }

    static void RentWarehouse(Person CurrentUser,LinkedList<Service> services,HashMap<Person, LinkedList<Rent>> people,Date TodayDate,Count IdRent) throws PromlematicTenantException{
        int c=0;
        if(CurrentUser.tenantalert.size()>3){
            int cost=0;
            String alert = "Osoba "+CurrentUser.imie+" "+CurrentUser.nazwisko+" posiada juz najem pomieszczen: ";
            for(TenantAlert ta : CurrentUser.tenantalert){
                alert=alert+" "+ta.rent.thing+", ";
                cost+=ta.rent.cost;
            }
            alert=alert+"  -  koszt: "+cost;
            throw new PromlematicTenantException(alert);
        }else{
            int cost=0;
            for(Rent r : people.get(CurrentUser)){
                cost+=r.cost;
            }
            if(cost<1250) {
                for (Service s : services) {
                    System.out.println("id:" + c + " " + s.name + ": \n");
                    for (PrivateWarehouse pw : s.pwarehouses) {
                        if (!pw.occupied) System.out.println(pw.id + " - " + pw);
                    }
                    System.out.println("Podaj numer serwisu: ");
                    Scanner scanner = new Scanner(System.in);
                    int service = scanner.nextInt();
                    System.out.println("Podaj numer magazynu: ");
                    int wareouse = scanner.nextInt();

                    for (PrivateWarehouse pw : services.get(service).pwarehouses)
                        if (wareouse == pw.id) {
                            people.get(CurrentUser).add(new Rent(IdRent.getNext(), CurrentUser, pw, TodayDate, TodayDate.plusDays(TodayDate, 30), 150));
                            pw.occupied = true;
                        }
                }
            }else{
                System.out.println("Wartosc wynajmowanych pomieszczen jest zbyt duza");
            }
        }
    }

    static void GivePermission(Person CurrentUser,HashMap<Person, LinkedList<Rent>> people) {
        System.out.println("Wybierz obiekt do ktorego chcesz nadac uprawnienia:\n");
        for(Rent rent :people.get(CurrentUser)){
            System.out.println(rent.id+"   "+rent.thing);
        }
        Scanner scanner = new Scanner(System.in);
        int object = scanner.nextInt();
        System.out.println("Wybierz osobe ktorej chcesz nadac uprawnienia:\n");
        for(Person p : people.keySet()){
            if(p!=CurrentUser) System.out.println(p.id+"   "+p.imie+" "+p.nazwisko);
        }
        int person = scanner.nextInt();

        Person tmp;
        for(Person p : people.keySet()){
            if(p.id==person){
                tmp=p;
                for(Rent rent :people.get(CurrentUser)){
                    if(object==rent.id) rent.thing.Entitled.add(tmp);
                }
            }
        }
    }

    static void RemovePermission(Person CurrentUser,HashMap<Person, LinkedList<Rent>> people){
        System.out.println("Wybierz obiekt dla ktorego chcesz odjac uprawnienia:\n");
        for(Rent rent :people.get(CurrentUser)){
            System.out.println(rent.id+"   "+rent.thing);
        }
        Scanner scanner = new Scanner(System.in);
        int object = scanner.nextInt();
        System.out.println("Wybierz osobe ktorej chcesz odjac uprawnienia:\n");
        for(Rent rent :people.get(CurrentUser)){
            if(object==rent.id){
                for(Person p : rent.thing.Entitled)
                    System.out.println(p.id+"   "+p.imie+" "+p.nazwisko);
                int person = scanner.nextInt();
                for(Person p : rent.thing.Entitled){
                    if(p.id==person)rent.thing.Entitled.remove(p);
                }
            }
        }
    }

    static void PutItem(Person CurrentUser,LinkedList<Service> services,HashMap<Person, LinkedList<Rent>> people,LinkedList<Thing> things,LinkedList<Vehicle> cars){
        int c=0;
        System.out.println("Podaj numer serwisu: ");

        Scanner scanner = new Scanner(System.in);
        int service = scanner.nextInt();

        System.out.println("Chcesz umiescic przedmiot w magazynie prywatnym, serwisowym czy zaparkowac auto? (Prywatny-0/Serwisowy-1/Parking-2): ");
        int type = scanner.nextInt();
        if(type==0) {
            System.out.println("Wybierz magazyn: \n");
            for (PrivateWarehouse pw : services.get(service).pwarehouses) {
                System.out.println(pw.id + "   " + pw);
            }
            int warehouse = scanner.nextInt();

            PrivateWarehouse tmp = null;
            for (PrivateWarehouse pw : services.get(service).pwarehouses) {
                if (warehouse == pw.id) tmp = pw;
            }

            boolean permission = false;

            for (Rent r : people.get(CurrentUser)) {
                if (r.thing == tmp) permission = true;
            }

            if (!permission) {
                for (Person p : tmp.Entitled) {
                    if (p == CurrentUser) permission = true;
                }
            }

            if(permission) {
                System.out.println("Wybierz przedmiot ktory chcesz dodac: ");
                for (Thing tg : things) {
                    System.out.println(tg.id + "   " + tg.name + "  objetosc: " + tg.space);
                }
                int thing = scanner.nextInt();
                for (Thing tg : things) {
                    if (tg.id == thing) {
                        try {
                            tmp.add(tg);
                        } catch (TooManyThingsException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                System.out.println("Nie masz uprawnien do tego magazynu");
            }
        } else if (type == 1) {
            System.out.println("Wybierz magazyn: \n");
            for (ServiceWarehouse sw : services.get(service).servicewarehouses) {
                System.out.println(sw.id + "   " + sw);
            }
            int warehouse = scanner.nextInt();

            ServiceWarehouse tmp = null;
            for (ServiceWarehouse sw : services.get(service).servicewarehouses) {
                if (warehouse == sw.id) tmp = sw;
            }

            System.out.println("Wybierz przedmiot ktory chcesz dodac: ");
            for (Thing tg : things) {
                System.out.println(tg.id + "   " + tg.name + "  objetosc: " + tg.space);
            }
            int thing = scanner.nextInt();
            for (Thing tg : things) {
                if (tg.id == thing) {
                    try {
                        tmp.add(tg);
                    } catch (TooManyThingsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(type==2){
            System.out.println("Wybierz miejsce postojowe: \n");
            for (ParkingSpot ps : services.get(service).parkingspots) {
                if(ps.CurrentParking==null)System.out.println(ps.id + "   " + ps);
            }
            int parkingspot = scanner.nextInt();

            ParkingSpot tmp = null;
            for (ParkingSpot ps : services.get(service).parkingspots) {
                if (parkingspot == ps.id) tmp = ps;
            }

            boolean permission = false;

            for (Rent r : people.get(CurrentUser)) {
                if (r.thing == tmp) permission = true;
            }

            if (!permission) {
                for (Person p : tmp.Entitled) {
                    if (p == CurrentUser) permission = true;
                }
            }

            if(permission){
                System.out.println("Wybierz pojazd ktory chcesz zaparkowac: ");
                for (Vehicle vc : cars) {
                    System.out.println(vc.id + "   " + vc.manufacturer + "  - " + vc.color);
                }
                int car = scanner.nextInt();
                for (Vehicle vc : cars) {
                    if(car==vc.id) tmp.CurrentParking=vc;
                }

            }
        }
    }

    static void RemoveItem(Person CurrentUser,LinkedList<Service> services,HashMap<Person, LinkedList<Rent>> people){
        int c=0;
        System.out.println("Podaj numer serwisu: ");

        Scanner scanner = new Scanner(System.in);
        int service = scanner.nextInt();

        System.out.println("Chcesz usunac przedmiot z magazynu prywatnego, serwisowego czy z miejsca parkingowego? (Prywatny-0/Serwisowy-1/Parking-2): ");
        int type = scanner.nextInt();
        if(type==0){
            System.out.println("Wybierz magazyn: \n");
            for (PrivateWarehouse pw : services.get(service).pwarehouses){
                System.out.println(pw.id+"   "+pw);
            }
            int warehouse = scanner.nextInt();

            PrivateWarehouse tmp = null;
            for (PrivateWarehouse pw : services.get(service).pwarehouses) {
                if (warehouse == pw.id) tmp = pw;
            }

            boolean permission = false;

            for (Rent r : people.get(CurrentUser)) {
                if (r.thing == tmp) permission = true;
            }

            if (!permission) {
                for (Person p : tmp.Entitled) {
                    if (p == CurrentUser) permission = true;
                }
            }

            if(permission) {
                System.out.println("Wybierz przedmiot ktory chcesz usunac: ");
                for (Thing tg : tmp.Content) {
                    System.out.println(tg.id + "   " + tg.name + "  objetosc: " + tg.space);
                }
                int thing = scanner.nextInt();
                for (Thing tg : tmp.Content) {
                    if (tg.id == thing) {
                        tmp.remove(tg);
                    }
                }
            }else{
                System.out.println("Nie masz uprawnien do tego magazynu");
            }
        }else if(type==1){
            System.out.println("Wybierz magazyn: \n");
            for (ServiceWarehouse sw : services.get(service).servicewarehouses) {
                System.out.println(sw.id + "   " + sw);
            }
            int warehouse = scanner.nextInt();

            ServiceWarehouse tmp = null;
            for (ServiceWarehouse sw : services.get(service).servicewarehouses) {
                if (warehouse == sw.id) tmp = sw;
            }
            System.out.println("Wybierz przedmiot ktory chcesz usunac: ");
            for (Thing tg : tmp.Content) {
                System.out.println(tg.id + "   " + tg.name + "  objetosc: " + tg.space);
            }
            int thing = scanner.nextInt();
            for (Thing tg : tmp.Content) {
                if (tg.id == thing) {
                    tmp.remove(tg);
                }
            }
        }else if(type==2){
            System.out.println("Wybierz miejsce postojowe: \n");
            for (ParkingSpot ps : services.get(service).parkingspots) {
                if(ps!=null)System.out.println(ps.id + "   " + ps);
            }
            int parkingspot = scanner.nextInt();

            ParkingSpot tmp = null;
            for (ParkingSpot ps : services.get(service).parkingspots) {
                if (parkingspot == ps.id) tmp = ps;
            }

            boolean permission = false;

            for (Rent r : people.get(CurrentUser)) {
                if (r.thing == tmp) permission = true;
            }

            if (!permission) {
                for (Person p : tmp.Entitled) {
                    if (p == CurrentUser) permission = true;
                }
            }

            if(permission){
                tmp.CurrentParking=null;
            }
        }
    }

    static void ShowContent(Person CurrentUser,HashMap<Person, LinkedList<Rent>> people){
        System.out.println("Wybierz obiekt ktorego zawartosc chcesz wyswietlic:\n");
        for(Rent rent :people.get(CurrentUser)){
            System.out.println(rent.id+"   "+rent.thing);
        }
        Scanner scanner = new Scanner(System.in);
        int object = scanner.nextInt();
        for(Rent rent :people.get(CurrentUser)){
            if(object==rent.id) System.out.println(rent.thing.DisplayContent());
        }
    }

    static void UserInfo(Person CurrentUser, HashMap<Person, LinkedList<Rent>> list) {
        System.out.println(CurrentUser+"\n"+list.get(CurrentUser));
    }

    static Person ChangeUser(HashMap<Person, LinkedList<Rent>> list) {
        System.out.println("Wybierz osobe: ");
        for(Person p : list.keySet()){
            System.out.println(p.id+": "+p.imie+" "+ p.nazwisko);
        }
        while(true) {
            System.out.println("Podaj liczbe: ");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            for (Person p : list.keySet())
                if (p.id == i){
                    System.out.println("Obecny uzytkownik: "+p.imie+" "+p.nazwisko+"\n\n");
                    return p;
                }
            System.err.println("Niepoprawne id");
        }
    }

    static void PayOffTheDebt(Person CurrentUser){
        if(!CurrentUser.info.isEmpty()){
            System.out.println("Wybierz numer dlugu ktory chcesz oplacic: ");
            for(TenantAlert ta : CurrentUser.info){
                System.out.println(ta.id+"  "+ta.toString());
            }
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            for(TenantAlert ta : CurrentUser.info){
                if(ta.id==i){
                    CurrentUser.info.remove(ta);
                    System.out.println("Dlug o id "+ta.id+" zostal przez ciebie oplacony.\n");
                }
            }
        }else System.out.println("Nie masz zadnych dlugow do oplacenia");
    }
}
