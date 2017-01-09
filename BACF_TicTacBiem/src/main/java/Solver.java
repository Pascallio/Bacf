import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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

    public Solver(User[] players, GridPane pane, String scherm){
        this.players = players;
        this.scherm = scherm;
        this.pane = pane;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                pane.add(totalSolver[i][j] = new BigCell(pane), j, i);
            }
        }
    }

    public Solver(User[] players, GridPane pane, String scherm, boolean lifes) {
        this.players = players;
        this.scherm = scherm;
        this.lifes = lifes;
        this.bombs = lifes;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pane.add(totalSolver[i][j] = new BigCell(pane), j, i);
            }
        }
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
            if (totalSolver[i][0].isWon(token) &&
                    totalSolver[i][1].isWon(token) &&
                    totalSolver[i][2].isWon(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWon(String token){
        for (int i = 0; i < 3; i++){
            if (totalSolver[0][i].isWon(token) &&
                    totalSolver[1][i].isWon(token) &&
                    totalSolver[2][i].isWon(token)){
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWon(String token) {
        return totalSolver[0][0].isWon(token) && totalSolver[1][1].isWon(token) &&
                totalSolver[2][2].isWon(token) || totalSolver[0][2].isWon(token) &&
                totalSolver[1][1].isWon(token) && totalSolver[2][0].isWon(token);
    }

    public class BigCell extends GridPane {

        private Cell[][] cell = new Cell[3][3];

        public BigCell(GridPane smallpane){
            setStyle("-fx-border-color: black");
            setStyle("-fx-background-color: transparent;");
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    smallpane.add(cell[i][j] = new Cell(), j, i);
                }
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

        public boolean isWon(String token){
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

            public Cell(){
                setStyle("-fx-border-color: black");
                setOnMouseClicked(e -> onMouseClick());
            }

            public void onMouseClick(){
                if (scherm.equals("speelscherm")) {
                    if (!hasToken()) {
                        User player = getCurrentPlayer();
                        setToken(player.getToken());
                        System.out.println("Token placed!");
                        if (hasBomb()){
                            player.setNumOfLifes(player.getNumOfLifes() - 1);
                        }
                    } else {
                        System.out.println("Already has a token!");
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
