package Projekt2;

import javax.swing.*;

import java.util.HashMap;

public class GameCommunicationsTabbedPanel extends JTabbedPane {
    GameCommunicationsTabbedPanel(Values values, HashMap<String,Country> countries){

        GamePlanesPanel gpp = new GamePlanesPanel(values,countries);
        add("Planes",gpp);

        GameShipsPanel gsp = new GameShipsPanel(values,countries);
        add("Ships", gsp);

        GameBordersPanel gbp = new GameBordersPanel(values,countries);
        add("Borders", gbp);
    }
}
