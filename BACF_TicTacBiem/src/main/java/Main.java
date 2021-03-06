import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private static String sound = "Off";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("beginscherm.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        Solver.setStage(primaryStage);
        primaryStage.setTitle("TicTacBiem");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void play(){
        if (sound == "On") {
            String path = System.getProperty("user.dir") + "/src/main/resources/biem_sound.wav";
            Media sound = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }

    public static void setSound () {
        sound = "On";
    }
}