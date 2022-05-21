package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameBottomPanel extends JPanel {
    GameBottomPanel(Values values, HashMap<String,Country> countries){
        setPreferredSize(new Dimension(1200,50));
        setBackground(new Color(60,60,60));

        setLayout(new GridLayout(1,4));

        JLabel healthy = new JLabel();
        healthy.setForeground(Color.CYAN);
        add(healthy);

        JLabel infected = new JLabel();
        infected.setForeground(Color.RED);
        add(infected);

        JLabel vaccinated = new JLabel();
        vaccinated.setForeground(Color.GREEN);
        add(vaccinated);

        JLabel points = new JLabel();
        points.setForeground(Color.YELLOW);
        add(points);

        JButton exit = new JButton("Main Menu");
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.DARK_GRAY);
        exit.addKeyListener(new ExitListener(values));
        exit.addActionListener((ae)->{
            values.EndText="paused";
            values.Ended=true;
        });
        add(exit);


        Thread BottomThread = new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(values.Tempo/2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int infectedC=0, healthyC=0, vaccinatedC=0;
                synchronized (countries) {
                    for (Country country : countries.values()) {
                        infectedC += country.infected;
                        healthyC += (country.population - country.infected - country.vaccinated);
                        vaccinatedC += country.vaccinated;
                    }
                }

                if(infectedC==0&&healthyC==0){
                    values.EndText="vaccinated";
                    values.Ended=true;
                }
                if(vaccinatedC==0&&healthyC==0){
                    values.EndText="infected";
                    values.Ended=true;
                }
                if(infectedC==0&&vaccinatedC==0){
                    values.EndText="cured";
                    values.Ended=true;
                }

                infected.setText("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp Infected: "+infectedC+"</html>");
                healthy.setText("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp Healthy: "+healthyC+"</html>");
                vaccinated.setText("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp Vaccinated: "+vaccinatedC+"</html>");
                points.setText("<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp Points: "+values.Points+"</html>");
            }
        });
        BottomThread.start();
    }
}
