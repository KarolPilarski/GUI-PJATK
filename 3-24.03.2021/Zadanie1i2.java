import java.util.Arrays;

public class Zadanie1i2 {
    public static void main(String[] args) {
        Square[] tab = {new Square(2),new Square(5),new Square(4),new Square(1),new Square(3)};
        for(Square x: tab) System.out.print(x+"  ");
        Arrays.sort(tab);
        System.out.println();
        for(Square x: tab) System.out.print(x+"  ");
    }
}

interface Figure{
    int max=6;

    public int getArea();
    public int getPerimeter();
}

class Square implements Comparable,Figure{
    int length, number;
    static int counter;

        Square( int length) {
            try {
                if (length <= max) {
                    this.length = length;
                    number = counter;
                    counter++;
                } else throw new TooBigSquareException();
            }catch(Exception TooBigSquareException){
                System.err.println("Maximum length is "+ max);
            }
    }

    @Override
    public int getPerimeter(){
        return length;
    }

    @Override
    public int getArea(){
        return this.length*this.length;
    }

    @Override
    public String toString(){
        return "("+number+"): "+this.getArea();
    }

    @Override
    public int compareTo(Object o) {
        Square z = (Square)o;
        return this.length-z.length;
    }
}

class TooBigSquareException extends Exception{
}