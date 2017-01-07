/**
 * Created by pascal on 3-1-17.
 */
public class Solver {

    private boolean lifes = false;
    private boolean bombs = false;
    private int currentPlayer = 1;
    private User winner;
    private User[] players;
    private BigCell[][] totalSolver = new BigCell[3][3];

    public Solver(User[] players){
        this.players = players;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                totalSolver[i][j] = new BigCell();
            }
        }
    }

    public Solver(User[] players, boolean lifes) {
        this.players = players;
        this.lifes = lifes;
        this.bombs = lifes;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                totalSolver[i][j] = new BigCell();
            }
        }
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

    public BigCell[][] getBigCells(){
        return this.totalSolver;
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

}
