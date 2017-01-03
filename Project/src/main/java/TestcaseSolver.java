/**
 * Created by pascal on 3-1-17.
 * Een testcase om te laten zien hoe de solver van tictactoe werkt.
 */
public class TestcaseSolver {

    private static BigTicTacSolver check = new BigTicTacSolver();
    private static TicTacSolver[][] total = check.getTotalSolver();

    public static void main(String args[]){
        if (!check.isFull()) {
            Cell[][] smallCel = total[0][0].getCells();
            smallCel[0][0].setToken("x");

            System.out.println(smallCel[0][0].getToken());

            smallCel[0][1].setBomb(); //Zet een bom in deze cel.
            System.out.println(smallCel[0][1].getBomb());

            System.out.println(smallCel[0][1].hasBomb());
        }
    }
}
