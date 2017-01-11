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

public class beginController implements Initializable {
    public MainController main;
    @FXML Label beginLabel;

    public void initialize(URL location, ResourceBundle resources) {
        beginLabel.setText("                           Everyone knows the classic game of TicTacToe... \n" +
        "    But what happens when you add Inception, Bombs, Lifes and Timers to it?!?! \n\n" +
        "Challenge your friends to the ULTIMATE clash and become a glorious mastermind!");
    }
    public void init(MainController mainController) {
        main = mainController;
    }
    public void quitKlikken () {
        System.exit(0);
    }
    public void rulesKlikken (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("regelscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void startKlikken (ActionEvent event) throws  IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("instellingenscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
