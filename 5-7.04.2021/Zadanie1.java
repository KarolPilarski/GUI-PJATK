import java.util.LinkedList;

public class Zadanie1 {
    public static void main(String[] args) {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters.getThreads())
            System.out.println(t.getName());
        for (Thread t : letters.getThreads())
            t.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Thread t : letters.getThreads()) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\nProgram skończył działanie");
    }

}

class Letters{
    LinkedList<Thread> threads = new LinkedList<>();
    Letters(String s){
        for(char c : s.toCharArray()){
            Thread t = new Thread(()->{
                for(int i=0;i<5;i++) System.out.print(c);
            });
            t.setName("Thread "+Character.toString(c));
            threads.add(t);
        }
    }
    public LinkedList<Thread> getThreads(){
        return threads;
    }
}
