import java.util.ArrayList;

/**
 * Created by pascal on 3-1-17.
 */
public class TicTacSolver {

    public ArrayList<Cell> tictac = new ArrayList<Cell>();
    private Cell[][] cell = new Cell[3][3];

    public TicTacSolver(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tictac.add(cell[i][j] = new Cell());
            }
        }
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

    private boolean isDiagonalWon(String token){
        for (int i = 0; i < 3; i++){
            if (cell[0][0].getToken().equals(token) &&
                    cell[1][1].getToken().equals(token) &&
                    cell[2][2].getToken().equals(token)){
                return true;
            }
        }
        for (int i = 0; i < 3; i++){
            if (cell[0][2].getToken().equals(token) &&
                    cell[1][1].getToken().equals(token) &&
                    cell[2][0].getToken().equals(token)){
                return true;
            }
        }

        return false;
    }

}

class Cell extends TicTacSolver{

    private String token = "";

    public Cell(){

    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }



}
