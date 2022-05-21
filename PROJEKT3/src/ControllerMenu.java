import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControllerMenu {

    Stage stage;

    @FXML
    public void newGame() {
        new Game(stage);
    }
    @FXML
    public void highScores() {
        new HighScores(stage);
    }
    @FXML
    public void exit() {
        System.exit(0);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
