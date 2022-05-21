import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.*;

public class ControllerHighScores {
    Stage stage;

    @FXML
    ListView scoresList;

    @FXML
    public void menu(ActionEvent event){
        event.consume();
        new Menu(stage);
    }

    public void loadData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/iUEy20mOAN", "iUEy20mOAN", "TA73CeakgP");
            String query = "SELECT name,points FROM scores ORDER BY points DESC";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name=rs.getString("name");
                int points = rs.getInt("points");

                scoresList.getItems().add(name+"    "+points);
            }
            connection.close();

        }catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
        loadData();
    }
}
