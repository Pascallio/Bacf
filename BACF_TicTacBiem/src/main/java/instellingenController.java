import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener ;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class instellingenController implements Initializable {
    public MainController main;
    @FXML BufferedImage bufferedImage;
    @FXML ImageView iv_avatar1;
    @FXML ImageView iv_avatar2;
    @FXML String path;
    @FXML String outPath1;
    @FXML String outPath2;
    @FXML String naamBeurt;
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

    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }
    public void initialize() {
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
        cb_avatarSpeler1.getItems().addAll("Bomb", "Cat", "Devil", "Ninja", "Sheep", "Shroom", "Trippy", "Unknown");
        cb_avatarSpeler2.getItems().addAll("Bomb", "Cat", "Devil", "Ninja", "Sheep", "Shroom", "Trippy", "Unknown");
        cb_avatarSpeler1.valueProperty().addListener((observable, oldValue, newValue) -> {
            String path = System.getProperty("user.dir") + "/src/main/resources/";
            switch (newValue.toString()) {
                case "Devil" : path = path + "devil_icon.png";
                    break;
                case "Bomb" : path = path + "bomb_icon.jpg";
                    break;
                case "Cat" : path = path + "cat_icon.jpg";
                    break;
                case "Ninja" : path = path + "ninja_icon.jpg";
                    break;
                case "Sheep" : path = path + "sheep_icon.png";
                    break;
                case "Shroom" : path = path + "shroom_icon.png";
                    break;
                case "Trippy" : path = path + "trip_icon.png";
                    break;
                case "Unknown" : path = path + "unknown_icon.jpg";
                    break;
                default : bufferedImage = null;
                    break;
            } try {
                bufferedImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
                bufferedImage = null;
            }
            iv_avatar1.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            outPath1 = path;
        });
        cb_avatarSpeler2.valueProperty().addListener((observable, oldValue, newValue) -> {
            String path = System.getProperty("user.dir") + "/src/main/resources/";
            switch (newValue.toString()) {
                case "Devil" : path = path + "devil_icon.png";
                    break;
                case "Bomb" : path = path + "bomb_icon.jpg";
                    break;
                case "Cat" : path = path + "cat_icon.jpg";
                    break;
                case "Ninja" : path = path + "ninja_icon.jpg";
                    break;
                case "Sheep" : path = path + "sheep_icon.png";
                    break;
                case "Shroom" : path = path + "shroom_icon.png";
                    break;
                case "Trippy" : path = path + "trip_icon.png";
                    break;
                case "Unknown" : path = path + "unknown_icon.jpg";
                    break;
                default : bufferedImage = null;
                    break;
            } try {
                bufferedImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
                bufferedImage = null;
            }
            iv_avatar2.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            outPath2 = path;
        });
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
        stage.show();
    }
    public void nextKlikken (ActionEvent event) throws IOException {
        System.out.println("CHECKS TOEVOEGEN");
        System.out.println("ook eigenlijk eerst naar initiatiescherm");
        System.out.println("bij speelscherm een initialize meegeven met waar de bommen liggen + gelijk plaatsen");

        if (cb_startingPlayer.getSelectionModel().getSelectedItem().equals(1)) {
            naamBeurt = tf_naamSpeler1.getText();
        } else {
            naamBeurt = tf_naamSpeler2.getText();
        }

        if (tf_naamSpeler1.getText().length() < 3) {
            errorLabel.setText("Name of player 1 has to be longer than 2 characters.");
        } else if (tf_naamSpeler2.getText().length() < 3) {
            errorLabel.setText("Name of player 2 has to be longer than 2 characters.");
        } else if (tf_naamSpeler1.getText().equals(tf_naamSpeler2.getText())) {
            errorLabel.setText("Names of both players can't be the same.");
        } else if (cb_avatarSpeler1.getSelectionModel().getSelectedItem() == null) {
            errorLabel.setText("Player 1 has to pick an avatar.");
        } else if (cb_avatarSpeler2.getSelectionModel().getSelectedItem() == null) {
            errorLabel.setText("Player 2 has to pick an avatar.");
        } else if (cb_avatarSpeler1.getSelectionModel().getSelectedItem().equals(cb_avatarSpeler2.getSelectionModel().getSelectedItem())) {
            errorLabel.setText("Avatars of both players can't be the same.");
        } else if (cb_bombs.getSelectionModel().getSelectedItem().equals("On")) {
            if (cb_bombsPerPlayer.getSelectionModel().getSelectedItem() == null) {
                errorLabel.setText("The amount of bombs per player has to be chosen.");
            } else if (cb_lifesPerPlayer.getSelectionModel().getSelectedItem() == null) {
                errorLabel.setText("The amount of lifes per player has to be chosen.");
            } else if (cb_maxPerField.getSelectionModel().getSelectedItem() == null) {
                errorLabel.setText("The maximum amount of bombs per field has to be chosen.");
            } else {
                initiatieController.setPaths(outPath1, outPath2);

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("initiatiescherm.fxml"));
                Parent root = loader.load();

                initiatieController controller = loader.getController();
                Scene scene = new Scene(root);
                controller.lbl_naam1.setText(tf_naamSpeler1.getText());
                controller.lbl_naam2.setText(tf_naamSpeler2.getText());
                controller.lbl_naamBeurt.setText("Turn of: " + naamBeurt);
                String bommen = cb_bombsPerPlayer.getSelectionModel().getSelectedItem().toString();
                controller.lbl_bommen1.setText(bommen);
                controller.lbl_bommen2.setText(bommen);
                Integer bombs = Integer.parseInt(bommen)*2;
                controller.lbl_totaalBommen.setText(bombs.toString());

                String levens = cb_lifesPerPlayer.getSelectionModel().getSelectedItem().toString();
                controller.levens1 = levens;
                controller.levens2 = levens;

                if (cb_timeLimit.getSelectionModel().getSelectedItem().equals("On")) {
                    controller.totalTime = Integer.parseInt(timeLabel.getText());
                }
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            speelController.setPaths(outPath1, outPath2);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("speelscherm.fxml"));
            Parent root = loader.load();
            speelController controller = loader.getController();


            controller.lbl_naam1.setText(tf_naamSpeler1.getText());
            controller.lbl_naam2.setText(tf_naamSpeler2.getText());
            controller.lbl_naamBeurt.setText("Turn of: " + naamBeurt);
            controller.lbl_levens1.setText("");
            controller.lbl_levens2.setText("");
            if (cb_timeLimit.getSelectionModel().getSelectedItem().equals("On")) {
                controller.totalTime = Integer.parseInt(timeLabel.getText());
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}