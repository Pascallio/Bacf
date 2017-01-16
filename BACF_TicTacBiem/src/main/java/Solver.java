import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pascal on 3-1-17.
 */
public class Solver {

    private int lifes;
    private int bombs;
    private String scherm;
    private int currentPlayer = 1;
    private User winner;
    public User[] players;
    private BigCell[][] totalSolver = new BigCell[3][3];
    public GridPane pane;
    private Integer new_pos = 4;
    private ArrayList<int[]> bomb_list = new ArrayList<>();
    private String path = System.getProperty("user.dir") + "/src/main/resources/bomb_play.png";

    public Solver(User[] players, GridPane pane, String scherm){
        this.players = players;
        this.scherm = scherm;
        this.pane = pane;
        int count = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                pane.add(totalSolver[i][j] = new BigCell(count), j, i);
                count += 1;
            }
        }
    }

    public Solver(User[] players, GridPane pane, String scherm, int lifes, int bombs) {
        this.players = players;
        this.scherm = scherm;
        this.lifes = lifes;
        this.bombs = bombs;
        this.pane = pane;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(totalSolver[i][j] = new BigCell(count), j, i);
                count += 1;
            }
        }
    }

    public ArrayList<int[]> getBomb_list(){
        return this.bomb_list;
    }

    public void setBigBorder(int old){
        this.pane.setStyle("-fx-border-color: red;-fx-border-width: 5px;");
        this.pane.getChildren().get(old).setStyle("-fx-border-color: white");
    }

    public void setScherm(String scherm){
        this.scherm = scherm;
    }

    public String getScherm(){
        return this.scherm;
    }

    public User getCurrentPlayer(){
        switch (currentPlayer){
            default: return this.players[0];
            case 2: return this.players[1];
        }
    }

    public void switchPlayer(){
        switch (currentPlayer){
            case 1: currentPlayer = 2;
                break;
            case 2: currentPlayer = 1;
                break;
        }
    }

    public BigCell getBigCells(int row, int column){
        return this.totalSolver[row][column];
    }

    public boolean isFullyFull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!totalSolver[i][j].isFull()){
                    return false;
                }
            }
        }
        return true;
    }

    public void setWinner(User player){
        this.winner = player;
    }

    public User getWinner(){
        return this.winner;
    }

    public boolean isWon(String token){
        return isRowWon(token) || isColumnWon(token) || isDiagonalWon(token);
    }

    private boolean isRowWon(String token){
        for (int i = 0; i < 3; i++){
            if (totalSolver[i][0].isSmallWon(token) &&
                    totalSolver[i][1].isSmallWon(token) &&
                    totalSolver[i][2].isSmallWon(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWon(String token){
        for (int i = 0; i < 3; i++){
            if (totalSolver[0][i].isSmallWon(token) &&
                    totalSolver[1][i].isSmallWon(token) &&
                    totalSolver[2][i].isSmallWon(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWon(String token) {
        return totalSolver[0][0].isSmallWon(token) && totalSolver[1][1].isSmallWon(token) &&
                totalSolver[2][2].isSmallWon(token) || totalSolver[0][2].isSmallWon(token) &&
                totalSolver[1][1].isSmallWon(token) && totalSolver[2][0].isSmallWon(token);
    }


    public boolean hasTotalBombsLeft(){
        return bombs > 0;
    }

    public class BigCell extends GridPane {

        private Cell[][] cell = new Cell[3][3];

        public BigCell(int counting){
            int count = 0;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    this.add(cell[i][j] = new Cell(counting, count), j, i);
                    count += 1;
                }
            }
            setStyle("-fx-border-color: white");
            if (counting == 4 && scherm.equals("speelscherm")){
                setStyle("-fx-border-color: red;-fx-border-width: 5px");
            }
        }

        public void setCustomBorder(String css, int big, int old){
            if (big != old) {
                getParent().getChildrenUnmodifiable().get(big).setStyle(css);
                getParent().getChildrenUnmodifiable().get(big).setStyle(css +";-fx-border-width: 5px;");
                getParent().getChildrenUnmodifiable().get(old).setStyle("-fx-border-color: white");
            }
        }

        public int setFirstReady(String css, int old){
            boolean set = false;
            int big = 0;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (!totalSolver[i][j].isFull()) {
                        big = i*3+j;
                        setCustomBorder(css, big, old);
                        set=true;
                    }
                    if (set){break;}
                }
                if (set){break;}
            }
            return big;
        }

        public Cell getSmallCells(int row, int column){
            return this.cell[row][column];
        }

        public boolean isFull(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cell[i][j].getToken().equals("")){
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isSmallWon(String token){
            return isRowWon(token) || isColumnWon(token) || isDiagonalWon(token);
        }

        private boolean isRowWon(String token){
            for (int i = 0; i < 3; i++){
                if (cell[i][0].getToken().equals(token) &&
                        cell[i][1].getToken().equals(token) &&
                        cell[i][2].getToken().equals(token)){
                    return true;
                }
            }
            return false;
        }

        private boolean isColumnWon(String token){
            for (int i = 0; i < 3; i++){
                if (cell[0][i].getToken().equals(token) &&
                        cell[1][i].getToken().equals(token) &&
                        cell[2][i].getToken().equals(token)){
                    return true;
                }
            }
            return false;
        }

        private boolean isDiagonalWon(String token) {
            return cell[0][0].getToken().equals(token) && cell[1][1].getToken().equals(token) &&
                    cell[2][2].getToken().equals(token) || cell[0][2].getToken().equals(token) &&
                    cell[1][1].getToken().equals(token) && cell[2][0].getToken().equals(token);

        }

        public class Cell extends Pane {

            private Boolean bomb;
            private String token = "";
            private int position = 4;
            private int bigPosition;
            private Image image;
            private ImageView view;

            public Cell(int groot, int klein){
                this.position = klein;
                this.bigPosition = groot;
                setPrefSize(Integer.MAX_VALUE,Integer.MAX_VALUE);
                setStyle("-fx-border-color: black");
                setOnMouseClicked(e -> onMouseClick(e));
            }

            public void onMouseClick(MouseEvent e){
                if (scherm.equals("speelscherm")) {
                    speelscherm();
                } else {
                    initiatiescherm();
                }
            }

            private void initiatiescherm(){
                if (!hasBomb() && bombs > 0){
                    setBomb();
                } else {
                    System.out.println("Already has a bomb!");
                }
            }

            private void speelscherm(){
                //if (!isWon("X") && !isWon("O")){
                if (!hasToken()) {  //heeft geen token?
                    if (new_pos == this.bigPosition) { //is in het juiste vakje?
                        setToken(getCurrentPlayer().getToken());
                        checkBombs(getCurrentPlayer());
                        switchPlayer();
                        setCorrectBorder();
                    }
                } else {
                    System.out.println("Already has a token!");
                }
            }

            private void checkBombs(User player){
                if (hasBomb()) {
                    Main.play();
                    view.setImage(null);
                    player.setNumOfLifes(player.getNumOfLifes() - 1);
                }
            }

            private void setCorrectBorder(){
                if(!totalSolver[this.position/3][this.position%3].isFull()) {
                    new_pos = this.position;
                    setCustomBorder("-fx-border-color: red", this.position, this.bigPosition);
                } else {
                    new_pos = setFirstReady("-fx-border-color: red", this.bigPosition);
                }
            }

            public void setToken(String token){
                this.token = token;
                if (token.equals("X")) {
                    Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                    line1.endXProperty().bind(this.widthProperty().subtract(10));
                    line1.endYProperty().bind(this.heightProperty().subtract(10));

                    Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                    line2.endXProperty().bind(this.widthProperty().subtract(10));
                    line2.startYProperty().bind(this.heightProperty().subtract(10));

                    this.getChildren().addAll(line1, line2);
                }
                else{
                    Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2,
                            this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                    ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                    ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                    ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                    ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));

                    ellipse.setStroke(Color.BLACK);
                    ellipse.setFill(Color.WHITE);

                    this.getChildren().add(ellipse);

                }
            }

            public String getToken(){
                return this.token;
            }

            public boolean hasToken(){
                return this.token.length() > 0;
            }

            public void setBomb(){
                this.bomb = true;
                getCurrentPlayer().setBombs();
                //initiatiescherm().update(getCurrentPlayer(), getCurrentPlayer().getBombs(), hasTotalBombsLeft());
                bomb_list.add(new int[]{this.bigPosition, this.position});
                try {
                    image = SwingFXUtils.toFXImage(ImageIO.read(new File(path)), null);
                    view = new ImageView(image);
                    view.fitHeightProperty().bind(this.heightProperty());
                    view.fitWidthProperty().bind(this.widthProperty());
                    this.getChildren().add(view);
                } catch (IOException e) {
                    System.out.println(e.getMessage() + "asdasdsad");
                    bufferedImage = null;
                } catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }

            public boolean getBomb(){
                return this.bomb;
            }

            public boolean hasBomb(){
                return this.bomb != null;
            }


        }

    }




}
