import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class speelController implements Initializable {
    public MainController main;
    public GridPane speelGridPane;
    @FXML ImageView iv_avatar1;
    @FXML ImageView iv_avatar2;
    @FXML static String inPath1;
    @FXML static String inPath2;
    @FXML BufferedImage bufferedImage1;
    @FXML BufferedImage bufferedImage2;
    @FXML Label lbl_naam1;
    @FXML Label lbl_naam2;
    @FXML Label lbl_naamBeurt;
    @FXML Label lbl_levens1;
    @FXML Label lbl_levens2;
    @FXML Label lbl_timeLimit;
    @FXML Integer totalTime = 0;

    public static void setPaths (String path1, String path2) {
        inPath1 = path1;
        inPath2 = path2;
    }

    public void initialize(URL location, ResourceBundle resources) {
        Solver control = new Solver(new User[]{
                new User("naam1", "avatar1", "X"),
                new User("naam2", "avatar2", "O")}, this.speelGridPane,
                "speelscherm");
        try {
            bufferedImage1 = ImageIO.read(new File(inPath1));
            bufferedImage2 = ImageIO.read(new File(inPath2));
        } catch (IOException e) {
            e.printStackTrace();
            bufferedImage1 = null;
            bufferedImage2 = null;
        }
        iv_avatar1.setImage(SwingFXUtils.toFXImage(bufferedImage1, null));
        iv_avatar2.setImage(SwingFXUtils.toFXImage(bufferedImage2, null));

        lbl_timeLimit.setText("5");
        Timeline initiation = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1),
                (ActionEvent actionEvent) -> {
                    Integer time = Integer.parseInt(lbl_timeLimit.getText());
                    time -= 1;
                    lbl_timeLimit.setText(time.toString());

                    if (time == 0) {
                        if (totalTime > 9) {
                            initiation.stop();
                            startPlay();
                        } else {
                            lbl_timeLimit.setText("");
                            initiation.stop();
                        }
                    }
                });
        initiation.getKeyFrames().add(0, kf1);
        initiation.setCycleCount(Animation.INDEFINITE);
        initiation.play();
    }

    public void startPlay() {
        if (totalTime != 0) {
            lbl_timeLimit.setText(totalTime.toString());
            Timeline animation = new Timeline();
            KeyFrame kf2 = new KeyFrame(Duration.seconds(1),
                    (ActionEvent actionEvent) -> {
                        Integer time = Integer.parseInt(lbl_timeLimit.getText());
                        time -= 1;
                        if (time == 0) {
                            animation.pause();
                            // switch beurt aanroepen
                            // nieuwe speler mag zijn zet op alle mogelijk vlakken doen
                            time = totalTime;
                            animation.play();
                        }
                        lbl_timeLimit.setText(time.toString());
                        // als speler een token heeft neergezet dan ook:
                        // time = 0 zetten?
                        // anders hele riedeltje van binnen de if
                    });
            animation.getKeyFrames().add(0, kf2);
            animation.setCycleCount(Animation.INDEFINITE);
            animation.play();
        }
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
}
