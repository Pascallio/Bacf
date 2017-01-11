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

public class initiatieController implements Initializable {
    public MainController main;
    @FXML ImageView iv_avatar1;
    @FXML ImageView iv_avatar2;
    @FXML static String inPath1;
    @FXML static String inPath2;
    @FXML BufferedImage bufferedImage1;
    @FXML BufferedImage bufferedImage2;
    @FXML Label lbl_naam1;
    @FXML Label lbl_naam2;
    @FXML Label lbl_naamBeurt;
    @FXML Label lbl_bommen1;
    @FXML Label lbl_bommen2;
    @FXML Label lbl_totaalBommen;

    @FXML String levens1;
    @FXML String levens2;
    @FXML String timeLimit = "";
    @FXML int totalTime;


    public static void setPaths(String path1, String path2) {
        inPath1 = path1;
        inPath2 = path2;
    }

    public void initialize(URL location, ResourceBundle resources) {
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

    public void quitKlikken() {
        System.exit(0);
    }

    public void playKlikken(ActionEvent event) throws IOException {
        System.out.println("Check goedzetten na testen, doorgaan!");

        int bommen = Integer.parseInt(lbl_totaalBommen.getText());
        if (bommen < 3) { //                                < 1 of == 0, dit neergezet voor testen!
            speelController.setPaths(inPath1, inPath2);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("speelscherm.fxml"));
            Parent root = loader.load();
            speelController controller = loader.getController();
            Scene scene = new Scene(root);

            controller.lbl_naam1.setText(lbl_naam1.getText());
            controller.lbl_naam2.setText(lbl_naam2.getText());
            controller.lbl_naamBeurt.setText(lbl_naamBeurt.getText());
            controller.lbl_levens1.setText(levens1);
            controller.lbl_levens2.setText(levens2);

            if (totalTime > 9) {
                controller.totalTime = totalTime;
            }
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Check goedzetten na testen, errorlabel!");
            // errorlabel tekst zetten!
        }
    }
}