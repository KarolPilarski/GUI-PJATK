package s22682Projekt1GUI;

class Date{
    protected int day,month,year;
    Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return day+"."+month+"."+year;
    }

    public void nextDay(){
        if(day==30){
            month++;
            day=1;
            if(month==13){
                month=1;
                year++;
            }
        }else day++;
    }

    public int dateNumber(){
        return day+month*30+year*360;
    }

    Date plusDays(Date date,int x){
        int day=date.day;
        int month=date.month;
        int year=date.year;

        day=day+x;
        while(day>30){
            day=day-30;
            month++;
            if(month>12){
                month=1;
                year++;
            }
        }
        return new Date(day,month,year);
    }
}