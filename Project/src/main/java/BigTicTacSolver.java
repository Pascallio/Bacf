/**
 * Created by pascal on 3-1-17.
 */
public class BigTicTacSolver {

    private TicTacSolver[][] totalSolver = new TicTacSolver[3][3];

    public BigTicTacSolver(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                totalSolver[i][j] = new TicTacSolver();
            }
        }
    }

    public TicTacSolver[][] getTotalSolver(){
        return this.totalSolver;
    }

    public boolean isFull(String token){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!totalSolver[i][j].isWon(token)){
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

    private boolean isDiagonalWon(String token){
        for (int i = 0; i < 3; i++){
            if (totalSolver[0][0].isWon(token) &&
                    totalSolver[1][1].isWon(token) &&
                    totalSolver[2][2].isWon(token)){
                return true;
            }
        }
        for (int i = 0; i < 3; i++){
            if (totalSolver[0][2].isWon(token) &&
                    totalSolver[1][1].isWon(token) &&
                    totalSolver[2][0].isWon(token)){
                return true;
            }
        }

        return false;
    }

}
