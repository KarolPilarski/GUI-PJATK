import java.util.*;

public class Zadanie1 {

    public static void main(String[] args) {
        Osoba kowalski = new Osoba("Jan", "Kowalski");
        Osoba nowak = new Osoba("Adam", "Nowak");
        Osoba krawczyk = new Osoba("Bartosz", "Krawczyk");
        Osoba heniek = new Osoba("Kierownik", "Heniek");
        Samochod skoda1 = new Samochod("WA00001", Samochod.Marka.SKODA);
        Samochod skoda2 = new Samochod("SC36010", Samochod.Marka.SKODA);
        Samochod mazda1 = new Samochod("WA01234", Samochod.Marka.MAZDA);
        Samochod mazda2 = new Samochod("DW01ASD", Samochod.Marka.MAZDA);
        Samochod bmw = new Samochod("WA12690", Samochod.Marka.BMW);
        Samochod volvo = new Samochod("KR60606", Samochod.Marka.VOLVO);

        HashMap<Osoba, LinkedList> mapa=new HashMap<>();
        mapa.put(kowalski,new LinkedList(Arrays.asList(skoda1, bmw)));
        mapa.put(nowak,new LinkedList(Arrays.asList(mazda2)));
        mapa.put(krawczyk,new LinkedList(Arrays.asList(mazda1, skoda2, volvo)));
        mapa.put(heniek, new LinkedList());


        for(Map.Entry<Osoba, LinkedList> osoba : mapa.entrySet())
            if(!osoba.getValue().isEmpty()) System.out.println(osoba.getKey()+" -> " +osoba.getValue().toString());
            else System.out.println(osoba.getKey()+" -> [brak samochodow]");

        for(Map.Entry<Osoba, LinkedList> osoba : mapa.entrySet())
            System.out.println(osoba.getKey()+" posiada "+osoba.getValue().size()+" pojazdy");

        for (LinkedList<Samochod> samochodlist : mapa.values())
            for (Samochod samochod : samochodlist)
                if (samochod.getRej().startsWith("WA")) System.out.println(samochod);

        System.out.println(mapa.get(nowak).get(0));
    }
}

class Osoba{
    protected String imie;
    protected String nazwisko;

    Osoba(String imie,String nazwisko){
        this.imie=imie;
        this.nazwisko=nazwisko;
    }

    @Override
    public String toString(){
        return imie + " " + nazwisko;
    }
}

class Samochod{
    protected String rej;
    protected Marka marka;

    public enum Marka {
        SKODA, MAZDA, BMW, VOLVO;
    }

    Samochod(String rej,Marka marka){
        this.rej=rej;
        this.marka=marka;
    }

    @Override
    public String toString(){
        return marka + " " + rej;
    }

    public String getRej() {
        return rej;
    }
}
