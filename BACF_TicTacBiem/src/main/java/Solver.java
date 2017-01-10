import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * Created by pascal on 3-1-17.
 */
public class Solver {

    private boolean lifes = false;
    private boolean bombs = false;
    private String scherm;
    private int currentPlayer = 1;
    private User winner;
    private User[] players;
    private BigCell[][] totalSolver = new BigCell[3][3];
    private GridPane pane;
    private Integer new_pos = 4;

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

    public Solver(User[] players, GridPane pane, String scherm, boolean lifes) {
        this.players = players;
        this.scherm = scherm;
        this.lifes = lifes;
        this.bombs = lifes;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(totalSolver[i][j] = new BigCell(count), j, i);
                count += 1;
            }
        }
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

    public boolean hasLifesOn(){
        return this.lifes;
    }

    public boolean hasBombsOn(){
        return this.bombs;
    }

    public BigCell getBigCells(int row, int column){
        return this.totalSolver[row][column];
    }

    public boolean isFull(){
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

    public class BigCell extends GridPane {

        private Cell[][] cell = new Cell[3][3];

        public BigCell(int counting){

            //setStyle("-fx-background-color: transparent;");
            int count = 0;
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    this.add(cell[i][j] = new Cell(counting, count), j, i);
                    count += 1;
                }
            }
            setStyle("-fx-border-color: white");
            if (counting == 4){
                setStyle("-fx-border-color: red;-fx-border-width: 5px");
            }
            //setStyle("-fx-border-radius: 5;");
        }

        public void setCustomBorder(String css, int big, int old){
            if (big != old) {
                getParent().getChildrenUnmodifiable().get(big).setStyle(css);
                getParent().getChildrenUnmodifiable().get(big).setStyle(css +";-fx-border-width: 5px;");
                getParent().getChildrenUnmodifiable().get(old).setStyle("-fx-border-color: white");
            }
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

            public Cell(int groot, int klein){
                this.position = klein;
                this.bigPosition = groot;
                setPrefSize(Integer.MAX_VALUE,Integer.MAX_VALUE);
                setStyle("-fx-border-color: black");
                setOnMouseClicked(e -> onMouseClick(e));
            }

            public void onMouseClick(MouseEvent e){
                System.out.println(this.bigPosition);
                System.out.println(new_pos);
                if (scherm.equals("speelscherm")) {
                    //if (!isWon("X") && !isWon("O")){
                    if (!isSmallWon("X") && !isSmallWon("O")){
                        if (!hasToken()) {
                            if (new_pos == this.bigPosition) {
                                User player = getCurrentPlayer();
                                setToken(player.getToken());
                                new_pos = this.position;
                                System.out.println(this.position);

                                System.out.println("Token placed!");
                                switchPlayer();
                                System.out.println(isSmallWon(player.getToken()));
                                setCustomBorder("-fx-border-color: red", this.position, this.bigPosition);
                                if (hasBomb()) {
                                    player.setNumOfLifes(player.getNumOfLifes() - 1);
                                }
                            }

                        } else {
                            System.out.println("Already has a token!");
                        }
                    } else {
                        setBigBorder(this.bigPosition);
                    }


                } else {
                    if (!hasBomb()){
                        System.out.println("Bomb placed!");
                        setBomb();
                    } else {
                        System.out.println("Already has a bomb!");
                    }
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
