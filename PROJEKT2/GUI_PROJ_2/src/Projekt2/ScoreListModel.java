package Projekt2;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class ScoreListModel extends AbstractListModel {

    LinkedList<Score> scores= new LinkedList<>();

    ScoreListModel(){
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
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        return scores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return scores.get(index);
    }
}
