public class Zadanie1 {
    public static void main(String[] args) {
        FiguraGeometryczna[] tab ={
                new Kwadrat(5,"Niebieski"),new Kwadrat(5,"Czerwony"), new Kwadrat(3,"Fioletowy"),
                new Kolo(4,"Niebieski"),new Kolo(10,"Czarny"),
                new Prostokat(8,4,"Bialy"),new Prostokat(5,2,"Zielony"),
                new Trojkat(5,7,8,"Pomaranczowy"),new Trojkat(1,2,3,"Zielony")
        };
        for(FiguraGeometryczna X: tab){
            System.out.println(X);
        }
    }
}

abstract class FiguraGeometryczna{
    protected String Kolor;

    public abstract double Obwod();
    public abstract double Pole();
}

class Kwadrat extends FiguraGeometryczna{
    protected int bok;

    Kwadrat(int bok, String Kolor){
        this.bok=bok;
        this.Kolor=Kolor;
    }

    public double Obwod(){
        return bok*4;
    }

    public double Pole(){
        return bok*bok;
    }

    public String toString(){
        return ("Kwadrat: bok-"+bok+" obwod-"+Obwod()+" pole-"+Pole()+" kolor-"+Kolor);
    }
}

class Kolo extends FiguraGeometryczna{
    protected int promien;

    Kolo(int promien, String Kolor){
        this.promien=promien;
        this.Kolor=Kolor;
    }

    public double Obwod(){
        return 2*promien*3.14;
    }

    public double Pole(){
        return 3.14*promien*promien;
    }

    public String toString(){
        return ("Kolo: promien-"+promien+" obwod-"+Obwod()+" pole-"+Pole()+" kolor-"+Kolor);
    }
}

class Prostokat extends FiguraGeometryczna{
    protected int A,B;

    Prostokat(int A, int B, String Kolor){
        this.A=A;
        this.B=B;
        this.Kolor=Kolor;
    }

    public double Obwod(){
        return 2*A+2*B;
    }

    public double Pole(){
        return A*B;
    }

    public String toString(){
        return ("Prostokat: Bok A-"+A+" Bok B-"+B+" obwod-"+Obwod()+" pole-"+Pole()+" kolor-"+Kolor);
    }
}

class Trojkat extends FiguraGeometryczna{
    protected int A,B,C;

    Trojkat(int A, int B,int C, String Kolor){
        this.A=A;
        this.B=B;
        this.C=C;
        this.Kolor=Kolor;
    }

    public double Obwod(){
        return A+B+C;
    }

    public double Pole(){
        return (Math.sqrt((Obwod()/2)*((Obwod()/2)-A)*((Obwod()/2)-B)*((Obwod()/2)-C)));
    }

    public String toString(){
        return ("Trojkat: Bok A-"+A+" Bok B-"+B+" Bok C-"+C+" obwod-"+Obwod()+" pole-"+Pole()+" kolor-"+Kolor);
    }
}