import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zadanie1 {
    public static void main(String[] args) {
        BufferedImage image=null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
            File dir = fileChooser.getSelectedFile();
            File[] files = dir.listFiles();
            while(true){
                for (File fl : files) {
                    if (fl.getName().matches(".*\\.(png|jpg)")) {
                        System.out.println(fl.getName());
                        try {
                            image = ImageIO.read(fl);
                            PhotoFrame photoFrame = new PhotoFrame(image);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            photoFrame.dispose();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
class PhotoFrame extends JFrame{
    PhotoFrame(BufferedImage image){
        setVisible(true);
        setLayout(new BorderLayout());
        add(new PhotoPanel(image));
        setSize(image.getWidth(),image.getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class PhotoPanel extends JPanel{
    BufferedImage image;
    PhotoPanel(BufferedImage image){
        this.image=image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}