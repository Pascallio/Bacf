import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class speelController implements Initializable {
    public MainController main;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
}
