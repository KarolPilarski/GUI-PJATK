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

public class HighScores {

    Stage stage;

    HighScores(Stage stage){
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highScores.fxml"));
        Pane root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerHighScores controller = (ControllerHighScores)loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root,800,450);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN);
            public void handle(KeyEvent ke) {
                if (keyCombination.match(ke)) {
                    new Menu(stage);
                    ke.consume();
                }
            }
        });

        stage.setScene(scene);
    }
}
