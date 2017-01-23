import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class eindController implements Initializable {
    public MainController main;
    @FXML public Label lbl_winner;
    @FXML public ImageView iv_winner;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
    public void replayKlikken (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("beginscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacBiem");
        stage.show();
    }
}
