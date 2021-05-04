public class Zadanie2 {
    public static void main(String[] args) {
        StringTask task = new StringTask("A", 70000);
        System.out.println("Task " + task.getState());
        task.start();
        if (args.length > 0 && args[0].equals("abort")){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.abort();
        }
        while (!task.isDone()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch(task.getState()){
                case RUNNING:
                    System.out.print("R.");
                    break;
                case ABORTED:
                    System.out.println(" ... aborted.");
                    break;
                case READY:
                     System.out.println(" ... ready.");
                     break;
                default:
                     System.out.println("uknown state");
            }
        }
        System.out.println("Task " + task.getState());
        System.out.println(task.getResult().length());
    }
}

class StringTask implements Runnable{
    enum TaskState {RUNNING,ABORTED,READY,CREATED};
    String s,result="";
    int m;
    TaskState current=TaskState.CREATED;
    Thread t;

    StringTask(String s,int m){
        t=new Thread(this,"StringTask");
        this.m=m;
        this.s=s;
    }

    @Override
    public void run() {
        for(int i=0;i<m;i++){
            result=result+s;
        }
        current=TaskState.READY;
    }
    public String getResult(){
        return result;
    }
    public TaskState getState(){
        return current;
    }
    public void start(){
        t.start();
        current=TaskState.RUNNING;
    }
    public void abort(){
        t.stop();
        current=TaskState.ABORTED;

    }
    public boolean isDone(){
        return current==TaskState.READY;
    }

}
