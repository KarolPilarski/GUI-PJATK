public class Zadanie2 {
    public  static  void  main(String [] args){
        Spiewak  s1 = new  Spiewak("Eminem"){
            @Override
            public String Spiewaj() {
                return "You own it , you  better  never  let it go";
            }
        };
        Spiewak  s2 = new  Spiewak("Eagles"){
            @Override
            public String Spiewaj() {
                return "Hotel  California";
            }
        };
        Spiewak  s3 = new  Spiewak("Dżem"){
            @Override
            public String Spiewaj() {
                return "Chwila , która trwa może być najlepszą z Twoich  chwil ...";
            }
        };
        Spiewak  sp[] = {s1 , s2 , s3};
        for (Spiewak s : sp)System.out.println(s);
        System.out.println("\n" + Spiewak.najglosniej(sp));
    }
}

abstract class Spiewak{
    protected String nazwa;
    protected int numer;
    protected static int counter=1;

    public abstract String Spiewaj();

    Spiewak(String nazwa){
        this.nazwa=nazwa;
        numer=counter++;
    }

    public static Spiewak najglosniej(Spiewak[] tab){
        int najglosniej=-1;
        Spiewak tmp=null;

        for (Spiewak x : tab){
            int count = 0;

            for (int i = 0; i < x.Spiewaj().length(); i++)
                if (Character.isUpperCase(x.Spiewaj().charAt(i)))
                    count++;

            if (count > najglosniej){
                najglosniej = count;
                tmp = x;
            }
        }
        return tmp;
    }

    public String toString(){
        return "("+numer+") "+nazwa+": "+Spiewaj();
    }
}
