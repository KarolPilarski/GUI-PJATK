package Projekt2;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameLeftPanel extends JPanel {
    JLabel leftPanelContent = new JLabel();
    LinkedList<String> panelContentList = new LinkedList<>();
    GameLeftPanel(){
        add(leftPanelContent);
        leftPanelContent.setForeground(Color.WHITE);

        setBackground(new Color(90,90,90));
    }
    void addPanelContent(String s){
        panelContentList.add(s);

        leftPanelContent.setText("<html>");
        for(String content : panelContentList){
            leftPanelContent.setText(leftPanelContent.getText()+content+"<br>");
        }

        leftPanelContent.setText(leftPanelContent.getText()+"</html>");
    }
}