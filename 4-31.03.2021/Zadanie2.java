import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Zadanie2 {

    public static void main(String[] args) {
        String[] arr = {"office A", "John", "Doe", "office B", "John", "Brown", "office C", "Mary", "Jones", "office B", "Adam", "Rust", "office C", "Cindy", "Frost", "office A", "Kate", "Coe", "office A", "Bill", "Brown"};

        HashMap<String, LinkedList<Person>> mapa=new HashMap<>();

        LinkedList<Person> ofA = new LinkedList<>();
        LinkedList<Person> ofB = new LinkedList<>();
        LinkedList<Person> ofC = new LinkedList<>();

        for(int i=0;i<arr.length;i=i+3){
            if(arr[i].equals("office A")) ofA.add(new Person((arr[i+1]),(arr[i+2])));
            else if(arr[i].equals("office B")) ofB.add(new Person((arr[i+1]),(arr[i+2])));
            else if(arr[i].equals("office C")) ofC.add(new Person((arr[i+1]),(arr[i+2])));
        }

        mapa.put("office A",ofA);
        mapa.put("office B",ofB);
        mapa.put("office C",ofC);


        int MaxSize=0;
        String MaxKey="";
        for(Map.Entry<String, LinkedList<Person>> office : mapa.entrySet()){
            System.out.println(office.getKey()+"="+office.getValue());
            if(office.getValue().size()>MaxSize){
                MaxSize=office.getValue().size();
                MaxKey=office.getKey();
            }
        }
        System.out.println(MaxKey+": "+MaxSize);
    }
}

class Person {
    protected String imie;
    protected String nazwisko;

    Person(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return  imie+" "+nazwisko;
    }
}