import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game {
    Game(Stage stage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerGame controller = (ControllerGame)loader.getController();
        controller.init(stage);
        Scene scene = new Scene(root,800,450);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {
                if (keyCombination.match(ke)) {
                    controller.fail=true;
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.Q){
                    controller.setWolfPosition("images/wolfLeftTop.png","leftTop");
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.W){
                    controller.setWolfPosition("images/wolfRightTop.png","rightTop");
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.A){
                    controller.setWolfPosition("images/wolfLeftBottom.png","leftBottom");
                    ke.consume();
                }
                if(ke.getCode() == KeyCode.S){
                    controller.setWolfPosition("images/wolfRightBottom.png","rightBottom");
                    ke.consume();
                }
            }
        });

        stage.setScene(scene);
    }
}
