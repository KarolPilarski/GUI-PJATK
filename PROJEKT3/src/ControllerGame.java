import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.Optional;

import static java.lang.Thread.sleep;

public class ControllerGame {
    Stage stage;
    String wolfPosition;
    int points=3;
    int score=0;
    boolean fail=false;

    @FXML
    Pane gamePane;

    @FXML
    Label wolfLabel;

    @FXML
    Label points1;

    @FXML
    Label points2;

    @FXML
    Label points3;

    @FXML
    Label scoreLabel;

    @FXML
    Label rabbitLabel;

    @FXML
    public void menu(ActionEvent event){
        event.consume();
        fail=true;
    }

    @FXML
    public void leftBottom(ActionEvent event){
        event.consume();
        setWolfPosition("images/wolfLeftBottom.png","leftBottom");
    }

    @FXML
    public void leftTop(ActionEvent event){
        event.consume();
        setWolfPosition("images/wolfLeftTop.png","leftTop");
    }

    @FXML
    public void rightBottom(ActionEvent event){
        event.consume();
        setWolfPosition("images/wolfRightBottom.png","rightBottom");
    }

    @FXML
    public void rightTop(ActionEvent event){
        event.consume();
        setWolfPosition("images/wolfRightTop.png","rightTop");
    }


    public void init(Stage stage){
        this.stage = stage;
        setWolfPosition("images/wolfRightTop.png","rightTop");
        drawPoints();
        displayScore();

        new Thread(()->{
            String[] positions = {"leftBottom","leftTop","rightBottom","rightTop"};
            String randomPosition;
            while(!fail){
                try {
                    sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Math.random()>0.5){
                    randomPosition=positions[(int)(Math.random()*4)];
                    final String str=randomPosition;
                    Platform.runLater(()-> {
                        newEgg(str);
                    });
                }
            }

            Platform.runLater(()-> {
                String name="Player";
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Nu magodi");
                dialog.setHeaderText("Game ended");
                dialog.setContentText("Enter your name:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                     name=result.get();
                }
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/iUEy20mOAN", "iUEy20mOAN", "TA73CeakgP");
                    String query = "INSERT INTO scores (name, points) VALUES (?, ?)";
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    preparedStmt.setString (1, name);
                    preparedStmt.setInt(2, score);

                    preparedStmt.execute();

                    connection.close();

                }catch(SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                new Menu(stage);
            });
        }).start();

        new Thread(()->{
            Image rabbitHidden = new Image("images/rabbitHidden.png");
            ImageView rabbitHiddenView = new ImageView(rabbitHidden);
            Image rabbitShown1 = new Image("images/rabbitShown1.png");
            ImageView rabbitShown1View = new ImageView(rabbitShown1);
            Image rabbitShown2 = new Image("images/rabbitShown2.png");
            ImageView rabbitShown2View = new ImageView(rabbitShown2);
            while(!fail) {
                Platform.runLater(()-> {
                    rabbitLabel.setGraphic(rabbitHiddenView);
                });
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<3;i++){
                    Platform.runLater(()-> {
                        rabbitLabel.setGraphic(rabbitShown1View);
                    });
                    try {
                        sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(()-> {
                        rabbitLabel.setGraphic(rabbitShown2View);
                    });
                    try {
                        sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void setWolfPosition(String path,String pos){
        Image img = new Image(path);
        ImageView image = new ImageView(img);
        wolfLabel.setGraphic(image);
        wolfPosition=pos;
    }

    public void drawPoints(){
        if(points==3){
            Image imgTrue1 = new Image("images/blank.png");
            ImageView imageTrue1 = new ImageView(imgTrue1);
            Image imgTrue2 = new Image("images/blank.png");
            ImageView imageTrue2 = new ImageView(imgTrue2);
            Image imgTrue3 = new Image("images/blank.png");
            ImageView imageTrue3 = new ImageView(imgTrue3);

            points1.setGraphic(imageTrue1);
            points2.setGraphic(imageTrue2);
            points3.setGraphic(imageTrue3);
        }else if(points==2){
            Image imgTrue1 = new Image("images/blank.png");
            ImageView imageTrue1 = new ImageView(imgTrue1);
            Image imgTrue2 = new Image("images/blank.png");
            ImageView imageTrue2 = new ImageView(imgTrue2);
            Image imgFalse1 = new Image("images/crackedEgg.png");
            ImageView imageFalse1 = new ImageView(imgFalse1);

            points1.setGraphic(imageTrue1);
            points2.setGraphic(imageTrue2);
            points3.setGraphic(imageFalse1);

        }else if(points==1){
            Image imgTrue1 = new Image("images/blank.png");
            ImageView imageTrue1 = new ImageView(imgTrue1);
            Image imgFalse1 = new Image("images/crackedEgg.png");
            ImageView imageFalse1 = new ImageView(imgFalse1);
            Image imgFalse2 = new Image("images/crackedEgg.png");
            ImageView imageFalse2 = new ImageView(imgFalse2);

            points1.setGraphic(imageTrue1);
            points2.setGraphic(imageFalse1);
            points3.setGraphic(imageFalse2);
        }else if(points==0){
            Image imgFalse1 = new Image("images/crackedEgg.png");
            ImageView imageFalse1 = new ImageView(imgFalse1);
            Image imgFalse2 = new Image("images/crackedEgg.png");
            ImageView imageFalse2 = new ImageView(imgFalse2);
            Image imgFalse3 = new Image("images/crackedEgg.png");
            ImageView imageFalse3 = new ImageView(imgFalse3);

            points1.setGraphic(imageFalse1);
            points2.setGraphic(imageFalse2);
            points3.setGraphic(imageFalse3);
        }
    }

    public void pointLost(){
        points--;
        drawPoints();
        if(points==0)fail=true;
    }

    public void gainScore(){
        score++;
        displayScore();
    }

    public void displayScore(){
        if(score<10){
            scoreLabel.setText("000"+score);
        }else if(score<100){
            scoreLabel.setText("00"+score);
        }else if(score<1000){
            scoreLabel.setText("0"+score);
        }else{
            scoreLabel.setText(""+score);
        }

    }

    public void newEgg(String pos){
        MoveTo moveTo;
        LineTo lineTo;
        if(pos.equals("leftTop")) {
            moveTo = new MoveTo(230,177);
            lineTo = new LineTo(280,205);
        }else if(pos.equals("rightTop")){
            moveTo = new MoveTo(570,174);
            lineTo = new LineTo(510,207);
        }
        else if(pos.equals("rightBottom")){
            moveTo = new MoveTo(570,234);
            lineTo = new LineTo(510,267);
        }else{
            moveTo = new MoveTo(230,237);
            lineTo = new LineTo(290,270);
        }

        Image egg = new Image("images/egg.png");
        Rectangle rect = new Rectangle(20,20);
        rect.setFill(new ImagePattern(egg));
        gamePane.getChildren().add(rect);
        ParallelTransition parallelTransition = new ParallelTransition();
        PathTransition pathTransition = new PathTransition(Duration.seconds(2),rect);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1),rect);
        rotateTransition.setFromAngle(0);
        if(pos.equals("rightBottom")||pos.equals("rightTop")) rotateTransition.setToAngle(-360);
        else rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        Path path = new Path();
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);

        pathTransition.setNode(rect);
        pathTransition.setPath(path);
        parallelTransition.getChildren().addAll(pathTransition, rotateTransition);
        parallelTransition.play();


        Thread check = new Thread(()->{
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(()->{
                    gamePane.getChildren().remove(rect);
                    if(pos!=wolfPosition){
                        pointLost();
                    }else{
                        gainScore();
                    }
                });
        });
        check.start();
    }
}
