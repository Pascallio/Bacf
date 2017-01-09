import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class speelController implements Initializable {
    public MainController main;
    public GridPane speelGridPane;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Gridpane");
        Solver control = new Solver(new User[]{
                new User("naam1", "avatar1", "X"),
                new User("naam2", "avatar2", "O")}, this.speelGridPane,
                "speelscherm");
        System.out.println(this.speelGridPane.getChildren().get(0));
    }

    public void initialize(){

    }


    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
}
