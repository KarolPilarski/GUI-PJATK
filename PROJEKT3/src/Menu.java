import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class Menu {

    Stage stage;

    Menu(Stage stage){
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerMenu controller = (ControllerMenu)loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root,800,450);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {

            public void handle(KeyEvent ke) {
                if(ke.getCode() == KeyCode.Q){
                    controller.newGame();
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.W){
                    controller.highScores();
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.A){
                    controller.exit();
                    ke.consume();
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
