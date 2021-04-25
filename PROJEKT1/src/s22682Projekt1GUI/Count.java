package s22682Projekt1GUI;

class Count{
    int count;
    Count(){
        this.count=0;
    }

    public int getCount() {
        return count;
    }

    public int getNext(){
        return count++;
    }
}