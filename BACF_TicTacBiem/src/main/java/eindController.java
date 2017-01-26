import javafx.embed.swing.SwingFXUtils;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class eindController implements Initializable {
    public MainController main;
    @FXML public Label lbl_winner;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @FXML public ImageView iv_winner;
=======
=======
>>>>>>> Stashed changes
    @FXML public Label lbl_gratz;
    @FXML public Label lbl_won;
    @FXML public ImageView iv_winner;
    @FXML static String inPath;
    @FXML BufferedImage bufferedImage;
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

    public void initialize(URL location, ResourceBundle resources) {
        try {
            bufferedImage = ImageIO.read(new File(inPath));
        } catch (IOException e) {
            e.printStackTrace();
            bufferedImage = null;
        }
        iv_winner.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public static void setPath (String path) {
        inPath = path;
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
