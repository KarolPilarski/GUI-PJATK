package Projekt2;

public class Values {
    public double SpreadConverter;
    public double CurabilityConverter;
    public double VaccinatedConverter;
    public int Tempo;
    public int Points;
    public boolean Ended;
    public String EndText;

    Values(String difficulty){
        Tempo=200;
        Ended=false;
        VaccinatedConverter=0;
        if(difficulty.equals("Easy")){
            SpreadConverter=0.015;
            CurabilityConverter=0.010;
        }else if(difficulty.equals("Normal")){
            SpreadConverter=0.03;
            CurabilityConverter=0.01;
        }else{
            SpreadConverter=0.075;
            CurabilityConverter=0.025;
        }
    }
}
