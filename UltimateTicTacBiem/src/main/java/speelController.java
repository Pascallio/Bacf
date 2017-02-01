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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML Label lbl_limitTime;
    @FXML Integer totalTime = 0;

    @FXML static Solver solve;
    private Timeline animation = new Timeline();
    private Label lbl_levens = new Label();

    public static void setPaths (String path1, String path2) {
        inPath1 = path1;
        inPath2 = path2;
    }

    private void update(Solver.BigCell.Cell veld) {
        if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())){
            lbl_levens = lbl_levens1;
        } else {
            lbl_levens = lbl_levens2;
        }

        Runnable first = () -> {
            if (solve.new_pos == veld.bigPosition) {
                if (!veld.hasToken()) {
                    veld.onMouseClick();
                    if (!lbl_levens1.getText().equals("")) {
                        lbl_levens.setText("Aantal levens: " + String.valueOf(solve.getCurrentPlayer().getNumOfLifes()));
                        try {
                            if(solve.getCurrentPlayer().getNumOfLifes() < 1) {
                                setWinconditie("levens");
                            }
                        } catch (IOException ignored) {}
                    }
                    try {
                        if (solve.isWon(solve.getCurrentPlayer().getToken())) {
                            setWinconditie("winnen");
                        }
                        if (solve.isFullyFull()) {
                            setWinconditie("gelijkspel");
                        }
                    } catch (IOException ignored) {}

                    solve.switchPlayer();
                    lbl_naamBeurt.setText("Turn of: " + solve.getCurrentPlayer().toString());
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

        if (!veld.hasToken()) {
            if (solve.new_pos == veld.bigPosition){
                if (lbl_levens1.getText() != "") {
                }
                startPlay();
            }
        }
    }

    private void setWinconditie(String check) throws IOException {
        animation.stop();
        switch (check) {
            case "levens":
                if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())) {
                    eindController.setPath(inPath2);
                } else {
                    eindController.setPath(inPath1);
                }
                break;
            case "winnen":
                if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())) {
                    eindController.setPath(inPath1);
                } else {
                    eindController.setPath(inPath2);
                }
                break;
            case "gelijkspel":
                eindController.setPath(inPath1);
                break;
        }

        Stage stage = (Stage) lbl_levens1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("eindscherm.fxml"));
        Parent root = loader.load();
        eindController controller = loader.getController();

        switch (check) {
            case "levens":
                if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())){
                    lbl_levens = lbl_naam2;
                } else {
                    lbl_levens = lbl_naam1;
                }
                break;
            case "winnen":
                if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())){
                    lbl_levens = lbl_naam1;
                } else {
                    lbl_levens = lbl_naam2;
                }
                break;
            case "gelijkspel":
                controller.lbl_gratz.setText("Ultimate TicTacDraw");
                lbl_levens.setText("nobody has won this time..");
                controller.lbl_won.setText("game ends in a draw");
                break;
        }
        controller.lbl_winner.setText(lbl_levens.getText());
        controller.lbl_winner.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacBiem");
        stage.show();
    }

    public void setSolver(Solver solver) {
        User[] users = solver.players;
        solve = new Solver(users, speelGridPane, "speelscherm");
        ArrayList<int[]> bommen = solver.getBomb_list();
        for (int[] coordinaten : bommen) {
            int bigColumn = coordinaten[0] % 3;
            int bigRow = coordinaten[0] / 3;
            int smallColumn = coordinaten[1] % 3;
            int smallRow = coordinaten[1] / 3;
            solve.getBigCells(bigRow, bigColumn).getSmallCells(smallRow, smallColumn).setBomb();
        }
    }

    public void setSolver(Solver solver, boolean test) {
        solve = solver;
    }
    

    private void setFunctions() {
        for (int i =0; i< 9; i++){
            int bigRow = i / 3;
            int bigColumn = i % 3;
            for (int j = 0; j < 9; j++) {
                int smallRow = j / 3;
                int smallColumn = j % 3;
                Solver.BigCell.Cell SBC = solve.getBigCells(bigRow, bigColumn).getSmallCells(smallRow, smallColumn);
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

        lbl_limitTime.setText("Tijd tot starten spel:");
        lbl_timeLimit.setText("5");
        Timeline initiation = new Timeline();
        KeyFrame kf1 = new KeyFrame(Duration.seconds(1),
                (ActionEvent actionEvent) -> {
                    Integer time = Integer.parseInt(lbl_timeLimit.getText());
                    time -= 1;
                    lbl_timeLimit.setText(time.toString());
                    if (time == 0) {
                        initiation.stop();
                        setFunctions();

                        if (totalTime != 0) {
                            startPlay();
                        } else {
                            lbl_limitTime.setText("");
                            lbl_timeLimit.setText("");
                        }
                    }
                });
        initiation.getKeyFrames().add(0, kf1);
        initiation.setCycleCount(Animation.INDEFINITE);
        initiation.play();
    }
    

    public void startPlay() {

        if (totalTime != 0) {
            lbl_limitTime.setText("Tijdslimiet:");
            lbl_timeLimit.setText(totalTime.toString());
            KeyFrame kf2 = new KeyFrame(Duration.seconds(1),
                    (ActionEvent actionEvent) -> {
                        Integer time = Integer.parseInt(lbl_timeLimit.getText());
                        time -= 1;
                        if (time == 0) {
                            solve.getCurrentPlayer().setNumOfLifes(solve.getCurrentPlayer().getNumOfLifes() - 1);
                            if (solve.getCurrentPlayer().toString().equals(solve.getPlayers()[0].toString())){
                                lbl_levens = lbl_levens1;
                            } else {
                                lbl_levens = lbl_levens2;
                            }
                            lbl_levens.setText("Aantal levens: " + String.valueOf(solve.getCurrentPlayer().getNumOfLifes()));
                            animation.stop();
                            if(solve.getCurrentPlayer().getNumOfLifes() < 1) {
                                try {
                                    setWinconditie("levens");
                                } catch (IOException ignored) {
                                }
                            }
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
            if (animation.getStatus().toString().equals("STOPPED")) {
                animation = new Timeline();
                animation.getKeyFrames().add(0, kf2);
                animation.setCycleCount(Animation.INDEFINITE);
                animation.play();

            }
        }
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    public void quitKlikken () {
        System.exit(0);
    }
}