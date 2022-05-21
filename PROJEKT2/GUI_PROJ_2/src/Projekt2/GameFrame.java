package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class GameFrame extends JFrame {
    public LocalDate CurrentDate = LocalDate.of(2021,1,1);
    GameFrame(){

        String[] difficultyArray={"Easy","Normal","Hard"};
        Object difficultyObject=JOptionPane.showInputDialog(null,"Choose difficulty level:","Koronawirus AntiPlague", JOptionPane.QUESTION_MESSAGE,null,difficultyArray,difficultyArray[1]);
        String dft=null;
        for(String s : difficultyArray){
            if(difficultyObject.equals(s)) dft=s;
        }
        final String difficulty=dft;
        Values values = new Values(difficulty);


        HashMap<String,Country> countries = new HashMap<>();
        initializeCountries(countries);


        getContentPane().setLayout(new BorderLayout());


        GameTopPanel gtp = new GameTopPanel(values);
        add(gtp,BorderLayout.PAGE_START);

        GameLeftPanel glp = new GameLeftPanel();
        JScrollPane jsp = new JScrollPane(glp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(250,500));
        add(jsp,BorderLayout.WEST);
        glp.addPanelContent(CurrentDate+": The game has started");
        glp.addPanelContent(CurrentDate+": First infection in Sweden");
        GameRightPanel grp = new GameRightPanel(countries);
        add(grp,BorderLayout.EAST);

        UIManager.put("TabbedPane.selected", Color.WHITE);
        UIManager.put("TabbedPane.contentAreaColor", Color.WHITE);
        UIManager.put("TabbedPane.unselectedTabForeground", Color.WHITE);
        UIManager.put("TabbedPane.background", new Color(90,90,90));

        JTabbedPane gmtp = new JTabbedPane();
        add(gmtp,BorderLayout.CENTER);
        GameMainMapPanel gmmp = new GameMainMapPanel(grp);
        gmtp.add("Main",gmmp);
        GameCommunicationsTabbedPanel gctp = new GameCommunicationsTabbedPanel(values,countries);
        gmtp.add("Communications",gctp);
        GameImprovementsPanel gip = new GameImprovementsPanel(values);
        gmtp.add("Improvements",gip);

        GameBottomPanel gbp = new GameBottomPanel(values, countries);
        add(gbp,BorderLayout.PAGE_END);

        setVisible(true);
        setResizable(false);
        ImageIcon icon = new ImageIcon("images/icon.jpg");
        setIconImage(icon.getImage());
        setTitle("Koronawirus AntiPlague");
        setSize(1400,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        Thread Timethread = new Thread(() -> {
            while(!values.Ended){
                try {
                    Thread.sleep(values.Tempo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CurrentDate=CurrentDate.plusDays(1);
                gtp.changeDate(CurrentDate);
                new SpreadThread(countries,values,glp,CurrentDate).start();
                new CureThread(countries,values).start();
                new VaccinesThread(countries,values).start();
                new CheckTransportsThread(countries,values,glp,CurrentDate).start();
            }
            System.out.println(values.EndText);
            String endComuciate;
            if(values.EndText.equals("paused")) endComuciate="Game paused. Player's name:";
            else endComuciate="Whole population "+values.EndText+". Player's name";
            String player = JOptionPane.showInputDialog(endComuciate);

            Score score = new Score(values.EndText,CurrentDate,player,difficulty);

            LinkedList<Score> scores= new LinkedList<>();
            scores.add(score);

            try {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores.txt"));
                    boolean nl=true;
                    while(nl){
                        try {
                            Object obj = ois.readObject();
                            scores.add((Score) obj);
                        }catch(EOFException a){
                            nl=false;
                        }
                    }
                    ois.close();
                }catch(FileNotFoundException a){
                    a.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException e ) {
                e.printStackTrace();
            }

            scores.sort(new CompareScores());

            try {
                ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("scores.txt"));
                for(Score scr : scores)objectOut.writeObject(scr);
                objectOut.flush();
                objectOut.close();
                System.out.println("Game score saved");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(MainMenu::new);
            dispose();

        });
        Timethread.start();
    }
    void initializeCountries(HashMap<String,Country> countries){
        countries.put("Germany",new Country("Germany",83019200,0,0,1,"images/germany.jpg",new LinkedList<Country>(),470,235,0.40,0.70,0.80,500,150,new LinkedList<Country>()));
        countries.put("Great Britain",new Country("Great Britain",66796800,0,0,1,"images/greatbritain.png",new LinkedList<Country>(),260,180,0.50,0.50,0.90,310,190,new LinkedList<Country>()));
        countries.put("France",new Country("France",67320216,0,0,1,"images/france.jpg",new LinkedList<Country>(),330,330,0.45,0.50,0.70,200,290,new LinkedList<Country>()));
        countries.put("Italy",new Country("Italy",59641488,0,0,1,"images/italy.jpg",new LinkedList<Country>(),470,410,0.60,0.00,0.85,0,0,new LinkedList<Country>()));
        countries.put("Spain",new Country("Spain",47332614,0,0,1,"images/spain.png",new LinkedList<Country>(),220,480,0.70,0.50,0.60,73,420,new LinkedList<Country>()));
        countries.put("Portugal",new Country("Portugal",10295909,0,0,1,"images/portugal.jpg",new LinkedList<Country>(),90,480,0.80,0.50,0.750,70,480,new LinkedList<Country>()));
        countries.put("Switzerland",new Country("Switzerland",8667100,0,0,1,"images/switzerland.jpg",new LinkedList<Country>(),420,360,0.70,0.00,0.50,0,0,new LinkedList<Country>()));
        countries.put("Czechia",new Country("Czechia",10693939,0,0,1,"images/czechia.jpg",new LinkedList<Country>(),540,275,0.750,0.00,0.550,0,0,new LinkedList<Country>()));
        countries.put("Slovakia",new Country("Slovakia",5457873,0,0,1,"images/slovakia.png",new LinkedList<Country>(),620,300,0.9,0.00,0.90,0,0,new LinkedList<Country>()));
        countries.put("Poland",new Country("Poland",37958138,0,0,1,"images/poland.png",new LinkedList<Country>(),620,200,0.85,0.50,0.70,590,140,new LinkedList<Country>()));
        countries.put("Denmark",new Country("Denmark",5822763,0,0,1,"images/denmark.jpeg",new LinkedList<Country>(),440,110,0.75,0.50,0.650,430,90,new LinkedList<Country>()));
        countries.put("Sweden",new Country("Sweden",10327589,1,0,1000,"images/sweden.png",new LinkedList<Country>(),500,50,0.90,0.50,0.80,480,40,new LinkedList<Country>()));
        countries.put("Norway",new Country("Norway",5391369,0,0,1,"images/norway.png",new LinkedList<Country>(),430,20,0.88,0.50,0.650,430,50,new LinkedList<Country>()));
        countries.put("Belgium",new Country("Belgium",11522440,0,0,1,"images/belgium.jpg",new LinkedList<Country>(),365,250,0.70,0.50,0.670,0,0,new LinkedList<Country>()));
        countries.put("Netherlands",new Country("Netherlands",17407585,0,0,1,"images/netherlands.png",new LinkedList<Country>(),390,200,0.8,0.50,0.50,370,200,new LinkedList<Country>()));

        countries.get("Germany").neighbours.add(countries.get("Poland"));
        countries.get("Germany").neighbours.add(countries.get("France"));
        countries.get("Germany").neighbours.add(countries.get("Denmark"));
        countries.get("Germany").neighbours.add(countries.get("Switzerland"));
        countries.get("Germany").neighbours.add(countries.get("Belgium"));
        countries.get("Germany").neighbours.add(countries.get("Netherlands"));
        countries.get("Germany").neighbours.add(countries.get("Czechia"));
        countries.get("Netherlands").neighbours.add(countries.get("Germany"));
        countries.get("Netherlands").neighbours.add(countries.get("Belgium"));
        countries.get("Belgium").neighbours.add(countries.get("France"));
        countries.get("Belgium").neighbours.add(countries.get("Germany"));
        countries.get("Belgium").neighbours.add(countries.get("Netherlands"));
        countries.get("France").neighbours.add(countries.get("Spain"));
        countries.get("France").neighbours.add(countries.get("Italy"));
        countries.get("France").neighbours.add(countries.get("Germany"));
        countries.get("France").neighbours.add(countries.get("Belgium"));
        countries.get("France").neighbours.add(countries.get("Switzerland"));
        countries.get("France").neighbours.add(countries.get("Great Britain"));
        countries.get("Great Britain").neighbours.add(countries.get("France"));
        countries.get("Portugal").neighbours.add(countries.get("Spain"));
        countries.get("Spain").neighbours.add(countries.get("Portugal"));
        countries.get("Spain").neighbours.add(countries.get("France"));
        countries.get("Italy").neighbours.add(countries.get("Switzerland"));
        countries.get("Italy").neighbours.add(countries.get("France"));
        countries.get("Switzerland").neighbours.add(countries.get("Italy"));
        countries.get("Switzerland").neighbours.add(countries.get("Germany"));
        countries.get("Switzerland").neighbours.add(countries.get("France"));
        countries.get("Czechia").neighbours.add(countries.get("Germany"));
        countries.get("Czechia").neighbours.add(countries.get("Poland"));
        countries.get("Czechia").neighbours.add(countries.get("Slovakia"));
        countries.get("Slovakia").neighbours.add(countries.get("Poland"));
        countries.get("Slovakia").neighbours.add(countries.get("Czechia"));
        countries.get("Poland").neighbours.add(countries.get("Slovakia"));
        countries.get("Poland").neighbours.add(countries.get("Germany"));
        countries.get("Poland").neighbours.add(countries.get("Czechia"));
        countries.get("Denmark").neighbours.add(countries.get("Germany"));
        countries.get("Denmark").neighbours.add(countries.get("Sweden"));
        countries.get("Sweden").neighbours.add(countries.get("Norway"));
        countries.get("Sweden").neighbours.add(countries.get("Denmark"));
        countries.get("Norway").neighbours.add(countries.get("Sweden"));

        countries.get("Germany").seaNeighbours.add(countries.get("Poland"));
        countries.get("Germany").seaNeighbours.add(countries.get("Sweden"));
        countries.get("Poland").seaNeighbours.add(countries.get("Germany"));
        countries.get("Sweden").seaNeighbours.add(countries.get("Germany"));
        countries.get("Sweden").seaNeighbours.add(countries.get("Denmark"));
        countries.get("Sweden").seaNeighbours.add(countries.get("Norway"));
        countries.get("Sweden").seaNeighbours.add(countries.get("Great Britain"));
        countries.get("Norway").seaNeighbours.add(countries.get("Sweden"));
        countries.get("Norway").seaNeighbours.add(countries.get("Denmark"));
        countries.get("Norway").seaNeighbours.add(countries.get("Netherlands"));
        countries.get("Norway").seaNeighbours.add(countries.get("Great Britain"));
        countries.get("Denmark").seaNeighbours.add(countries.get("Sweden"));
        countries.get("Denmark").seaNeighbours.add(countries.get("Norway"));
        countries.get("Denmark").seaNeighbours.add(countries.get("Netherlands"));
        countries.get("Denmark").seaNeighbours.add(countries.get("Great Britain"));
        countries.get("Great Britain").seaNeighbours.add(countries.get("Sweden"));
        countries.get("Great Britain").seaNeighbours.add(countries.get("Norway"));
        countries.get("Great Britain").seaNeighbours.add(countries.get("Netherlands"));
        countries.get("Great Britain").seaNeighbours.add(countries.get("Denmark"));
        countries.get("Netherlands").seaNeighbours.add(countries.get("Denmark"));
        countries.get("Netherlands").seaNeighbours.add(countries.get("Norway"));
        countries.get("Netherlands").seaNeighbours.add(countries.get("France"));
        countries.get("Netherlands").seaNeighbours.add(countries.get("Great Britain"));
        countries.get("France").seaNeighbours.add(countries.get("Spain"));
        countries.get("France").seaNeighbours.add(countries.get("Netherlands"));
        countries.get("France").seaNeighbours.add(countries.get("Great Britain"));
        countries.get("Spain").seaNeighbours.add(countries.get("France"));
        countries.get("Spain").seaNeighbours.add(countries.get("Portugal"));
        countries.get("Portugal").seaNeighbours.add(countries.get("Spain"));
    }

}
