import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class initiatieController implements Initializable {
    public MainController main;
    @FXML public GridPane speelGridPane;
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
    @FXML Label lbl_error;


    @FXML String levens1;
    @FXML String levens2;
    @FXML String timeLimit = "";
    @FXML int totalTime;

    @FXML static Solver solve;

    static Text test;

    public static void setPaths(String path1, String path2) {
        inPath1 = path1;
        inPath2 = path2;
    }

    private void update(Solver.BigCell.Cell veld) {
        final Label test;
        if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())){
            test = lbl_bommen1;
        } else {
            test = lbl_bommen2;
        }
        Runnable first = () -> {
            if (!veld.hasBomb()) {
                if (solve.getPlayers()[0].getBombs() + solve.getPlayers()[1].getBombs() > 0) {
                    veld.setBomb();
                    lbl_error.setText("");
                    test.setText("Bommen: " + String.valueOf(solve.getCurrentPlayer().getBombs()));
                    solve.switchPlayer();
                    lbl_naamBeurt.setText("Turn of: " + solve.getCurrentPlayer().toString());
                    lbl_totaalBommen.setText("Totaal bommen: " +
                            String.valueOf(solve.getPlayers()[0].getBombs() + solve.getPlayers()[1].getBombs()));
                    if (solve.getPlayers()[0].getBombs() + solve.getPlayers()[1].getBombs() < 1) {
                        lbl_totaalBommen.setText("No bombs left!");
                        lbl_naamBeurt.setText("");
                    }
                }
            }
        };
        Task taak = new Task<Void>() {
            @Override
            public Void call() {
                Platform.runLater(first);
                return null;
            }
        };
        Thread volgorde = new Thread(taak);
        volgorde.start();
    }

    public void setSolver(Solver solver) {
        solve = solver;
        for (int i =0; i< 9; i++){
            int bigrow = i / 3;
            int bigColumn = i % 3;
            for (int j = 0; j < 9; j++){
                int smallrow = j / 3;
                int smallColumn = j % 3;
                Solver.BigCell.Cell SBC = solve.getBigCells(bigrow, bigColumn).getSmallCells(smallrow, smallColumn);
                SBC.getPane().setOnMouseClicked(e -> update(SBC));
            }
        }
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
        int bommen = solve.getPlayers()[0].getBombs() + solve.getPlayers()[1].getBombs();
        if (bommen == 0) {
            speelController.setPaths(inPath1, inPath2);

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("speelscherm.fxml"));
            Parent root = loader.load();
            speelController controller = loader.getController();
            Scene scene = new Scene(root);

            controller.setSolver(solve);

            controller.lbl_naam1.setText(lbl_naam1.getText());
            controller.lbl_naam2.setText(lbl_naam2.getText());
            controller.lbl_naamBeurt.setText("Turn of: " + lbl_naam1.getText());
            controller.lbl_levens1.setText("Aantal levens: " + levens1);
            controller.lbl_levens2.setText("Aantal levens: " + levens2);

            if (totalTime > 9) {
                controller.totalTime = totalTime;
            }
            stage.setScene(scene);
            stage.setTitle("TicTacBiem Biem Biem");
            stage.show();
        } else {
            lbl_error.setTextFill(Color.RED);
            lbl_error.setText("Er moet nog een bom worden geplaatst.");
        }
    }
}