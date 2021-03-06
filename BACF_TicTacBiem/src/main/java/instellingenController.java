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
    @FXML private Label lbl_timeSlider;
    @FXML private Label lbl_bombsPerPlayer;
    @FXML private Label lbl_lifesPerPlayer;
    @FXML private Label lbl_maxPerField;
    @FXML private Label lbl_sound;
    @FXML private Slider timeSlider;
    @FXML private ComboBox cb_avatarSpeler1;
    @FXML private ComboBox cb_avatarSpeler2;
    @FXML private ComboBox cb_bombs;
    @FXML private ComboBox cb_bombsPerPlayer;
    @FXML private ComboBox cb_lifesPerPlayer;
    @FXML private ComboBox cb_maxPerField;
    @FXML private ComboBox cb_timeLimit;
    @FXML private ComboBox cb_sound;

    public void initialize(URL location, ResourceBundle resources) {
        initialize();
    }
    public void initialize() {
        cb_timeLimit.valueProperty().addListener((module, oud, nieuw) -> {
            if (nieuw == "On") {
                lbl_timeSlider.setVisible(true);
                timeSlider.setVisible(true);
                timeLabel.setVisible(true);
                cb_bombs.getSelectionModel().selectFirst();
            } else {
                lbl_timeSlider.setVisible(false);
                timeSlider.setVisible(false);
                timeLabel.setVisible(false);
            }
        });
        cb_bombs.valueProperty().addListener((module, oud, nieuw) -> {
            if (nieuw == "On") {
                lbl_bombsPerPlayer.setVisible(true);
                lbl_lifesPerPlayer.setVisible(true);
                lbl_maxPerField.setVisible(true);
                lbl_sound.setVisible(true);
                cb_bombsPerPlayer.setVisible(true);
                cb_lifesPerPlayer.setVisible(true);
                cb_maxPerField.setVisible(true);
                cb_sound.setVisible(true);
            } else {
                lbl_bombsPerPlayer.setVisible(false);
                lbl_lifesPerPlayer.setVisible(false);
                lbl_maxPerField.setVisible(false);
                lbl_sound.setVisible(false);
                cb_bombsPerPlayer.setVisible(false);
                cb_lifesPerPlayer.setVisible(false);
                cb_maxPerField.setVisible(false);
                cb_sound.setVisible(false);
                cb_timeLimit.getSelectionModel().selectLast();
            }
        });

        cb_sound.getItems().addAll("On", "Off");
        cb_sound.getSelectionModel().selectFirst();
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
        stage.setTitle("TicTacBiem");
        stage.show();
    }
    public void nextKlikken (ActionEvent event) throws IOException {
        naamBeurt = tf_naamSpeler1.getText();

        if (tf_naamSpeler1.getText().length() < 3) {
            errorLabel.setText("Name of player 1 has to be longer than 2 characters.");
        } else if (tf_naamSpeler2.getText().length() < 3) {
            errorLabel.setText("Name of player 2 has to be longer than 2 characters.");
        } else if (!tf_naamSpeler1.getText().matches("[a-zA-Z]+")) {
            errorLabel.setText("Name of player 1 can't contain other characters than letters.");
        } else if (!tf_naamSpeler2.getText().matches("[a-zA-Z]+")) {
            errorLabel.setText("Name of player 2 can't contain other characters than letters.");
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
                Integer bombspp = Integer.parseInt(cb_bombsPerPlayer.getSelectionModel().getSelectedItem().toString());
            } else if (2*Integer.parseInt(cb_bombsPerPlayer.getSelectionModel().getSelectedItem().toString()) < Integer.parseInt(cb_lifesPerPlayer.getSelectionModel().getSelectedItem().toString())) {
                errorLabel.setText("Players can't be beaten by losing lifes now, please increase the amount of bombs or decrease the amount of lifes.");
            } else {
                initiatieController.setPaths(outPath1, outPath2);
                if (cb_sound.getSelectionModel().getSelectedItem() == "On") {
                    Main.setSound();
                }


                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("initiatiescherm.fxml"));
                Parent root = loader.load();
                initiatieController controller = loader.getController();

                int levensps = Integer.parseInt(cb_lifesPerPlayer.getSelectionModel().getSelectedItem().toString());
                int bommenps = Integer.parseInt(cb_bombsPerPlayer.getSelectionModel().getSelectedItem().toString());
                Solver control = new Solver(new User[]{
                        new User(tf_naamSpeler1.getText(), "X"),
                        new User(tf_naamSpeler2.getText(),  "O")}, controller.speelGridPane,
                        "initiatiescherm", levensps, bommenps);
                controller.setSolver(control);
                Scene scene = new Scene(root);
                controller.lbl_naam1.setText(tf_naamSpeler1.getText());
                controller.lbl_naam2.setText(tf_naamSpeler2.getText());
                controller.lbl_naamBeurt.setText("Turn of: " + naamBeurt);
                String bommen = cb_bombsPerPlayer.getSelectionModel().getSelectedItem().toString();
                controller.lbl_bommen1.setText("Bommen: " + bommen);
                controller.lbl_bommen2.setText("Bommen: " + bommen);
                Integer bombs = Integer.parseInt(bommen)*2;
                controller.lbl_totaalBommen.setText("Totaal bommen: " + bombs.toString());

                String levens = cb_lifesPerPlayer.getSelectionModel().getSelectedItem().toString();
                controller.levens1 = levens;
                controller.levens2 = levens;

                if (cb_timeLimit.getSelectionModel().getSelectedItem().equals("On")) {
                    controller.totalTime = Integer.parseInt(timeLabel.getText());
                }

                if (Integer.parseInt(cb_maxPerField.getSelectionModel().getSelectedItem().toString()) > 0){
                    initiatieController.bombPerField = Integer.parseInt(cb_maxPerField.getSelectionModel().getSelectedItem().toString());
                }
                stage.setScene(scene);
                stage.setTitle("TicTac Bomb initiation");
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

            Solver control = new Solver(new User[]{
                    new User(tf_naamSpeler1.getText(), "X"),
                    new User(tf_naamSpeler2.getText(), "O")}, controller.speelGridPane,
                    "speelscherm");
            controller.setSolver(control, true);

            controller.lbl_naam1.setText(tf_naamSpeler1.getText());
            controller.lbl_naam2.setText(tf_naamSpeler2.getText());
            controller.lbl_naamBeurt.setText("Turn of: " + naamBeurt);
            controller.lbl_levens1.setText("");
            controller.lbl_levens2.setText("");
            //if (cb_timeLimit.getSelectionModel().getSelectedItem().equals("On")) {
            //    controller.totalTime = Integer.parseInt(timeLabel.getText());
            //}
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TicTacBiem Biem Biem");
            stage.show();
        }
    }
}