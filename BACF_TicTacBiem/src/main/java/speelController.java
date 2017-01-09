import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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

    public static void setPaths (String path1, String path2) {
        inPath1 = path1;
        inPath2 = path2;
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Gridpane");
        Solver control = new Solver(new User[]{
                new User("naam1", "avatar1", "X"),
                new User("naam2", "avatar2", "O")}, this.speelGridPane,
                "speelscherm");
        System.out.println(this.speelGridPane.getChildren().get(0));

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
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
}
