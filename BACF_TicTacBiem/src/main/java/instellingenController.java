import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener ;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class instellingenController implements Initializable {
    public MainController main;

    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }
    public void initialize() {
        cb_avatarSpeler1.getItems().addAll("X", "O");
        cb_avatarSpeler2.getItems().addAll("X", "O");
        cb_startingPlayer.getItems().addAll("1", "2");
        cb_startingPlayer.getSelectionModel().selectFirst();

        cb_bombs.getItems().addAll("On", "Off");
        cb_bombs.getSelectionModel().selectFirst();
        cb_bombsPerPlayer.getItems().addAll(1, 2, 3, 4);
        cb_lifesPerPlayer.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        cb_maxPerField.getItems().addAll(1, 2, 3, 4, 5, 6);
        cb_timeLimit.getItems().addAll("On", "Off");
        cb_timeLimit.getSelectionModel().selectLast();
        timeLabel.setText("35");
        timeSlider.valueProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                timeLabel.textProperty().setValue(
                        String.valueOf((int) timeSlider.getValue()));
            }
        });
    }

    public void init(MainController mainController) {
        main = mainController;
    }
    @FXML private TextField tf_naamSpeler1;
    @FXML private TextField tf_naamSpeler2;
    @FXML private Label errorLabel;
    @FXML private Label timeLabel;
    @FXML private Slider timeSlider;
    @FXML private ComboBox cb_avatarSpeler1;
    @FXML private ComboBox cb_avatarSpeler2;
    @FXML private ComboBox cb_startingPlayer;
    @FXML private ComboBox cb_bombs;
    @FXML private ComboBox cb_bombsPerPlayer;
    @FXML private ComboBox cb_lifesPerPlayer;
    @FXML private ComboBox cb_maxPerField;
    @FXML private ComboBox cb_timeLimit;

    public void backKlikken (ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("beginscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void nextKlikken (ActionEvent event) throws IOException {
        System.out.println("CHECKS TOEVOEGEN");
        System.out.println("ook eigenlijk eerst naar initiatiescherm");
        System.out.println("bij speelscherm een initialize meegeven met waar de bommen liggen + gelijk plaatsen");

        System.out.println(tf_naamSpeler1.getText());
        System.out.println(tf_naamSpeler2.getText());

        System.out.println(cb_avatarSpeler1.getSelectionModel().getSelectedItem());
        System.out.println(cb_avatarSpeler2.getSelectionModel().getSelectedItem());

        System.out.println(cb_startingPlayer.getSelectionModel().getSelectedItem());
        System.out.println(cb_bombs.getSelectionModel().getSelectedItem());
        System.out.println(cb_bombsPerPlayer.getSelectionModel().getSelectedItem());
        System.out.println(cb_lifesPerPlayer.getSelectionModel().getSelectedItem());
        System.out.println(cb_maxPerField.getSelectionModel().getSelectedItem());
        System.out.println(cb_timeLimit.getSelectionModel().getSelectedItem());

        System.out.println(timeSlider.getValue());

        Double d = new Double(timeSlider.getValue());
        int i = d.intValue();
        System.out.println(i);
        System.out.println(timeLabel.getText());

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("speelscherm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
