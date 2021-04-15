public class Zadanie3 {
    public static void main(String[] args) {

    }
}

interface Dzwonienie{
    default void zadzwon(int nr){
        System.out.println("Dzwonienie do: "+nr);
    }
}

interface Pisanie{
    default void napisz(int nr,String wiadomosc){
        System.out.println("Wysladno wiadomosc :\n"+wiadomosc+"\n do: "+nr);
    }
}

interface Internet{
    default void wyszukaj(String x){
        System.out.println("Wyszukiwanie:  "+x+"  w internecie");
    }
}

interface Granie{
    default void graj(String x){
        System.out.println("Uruchamianie gry: "+x);
    }
}

abstract class Urzadzenie{
    String nazwa;
    int cena;
}

class TelefonKomorkowy extends Urzadzenie implements Dzwonienie,Pisanie{

}

class TelefonStacjonarny extends Urzadzenie implements Dzwonienie{

}

class Konsola extends Urzadzenie implements Internet,Granie{

}

class Smartfon extends Urzadzenie implements Dzwonienie,Pisanie,Internet,Granie{

}
