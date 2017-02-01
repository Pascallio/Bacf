import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class regelController implements Initializable {
    public MainController main;
    @FXML Label generalLabel;
    @FXML Label bombsLabel;
    @FXML Label timeLabel;

    public void initialize(URL location, ResourceBundle resources) {

        generalLabel.setText("Player 1 begins with taking a turn. \n" +
        "The first turn has to be done in the middle big grid. \n" +
        "Whenever a token is placed, the turn goes to the other player. \n" +
        "The big grid is won for the player that has 3 tokens in a row. \n" +
        "When a player is sent to a completed big grid, he has to place his token in the upper-left most field. \n" +
        "The game is won for the player that has 3 big grids in a row. \n");

        bombsLabel.setText("When playing with the bombs option on. \n" +
                "==================\n" +
        "When a player places a token on a bomb, he loses 1 life. \n" +
        "When either of the players has 0 lifes left, the other player wins.");

        timeLabel.setText("When playing with the time option on. \n" +
                "==================\n" +
        "When a player places his token within the given time, \n" +
        "the other player will get the same amount of time to play his token. \n" +
        "When a player lets the timer hit 0, \n" +
        "he will lose a life and has to redo the same turn with the same amount of time.");
    }
    public void init(MainController mainController) {
        main = mainController;
    }
    public void backKlikken (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("beginscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacBiem");
        stage.show();
    }
    public void startKlikken (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("instellingenscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game options");
        stage.show();
    }
}
