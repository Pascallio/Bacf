/**
 * Created by pascal on 3-1-17.
 * Een testcase om te laten zien hoe de solver van tictactoe werkt.
 */
public class TestcaseSolver {

    public static void main(String args[]){

        BigTicTacSolver check = new BigTicTacSolver();

        check.getTotalSolver()[0][0].getCells()[0][0].setToken("x"); //[0][0] zijn coordinaten, linksboven.
        System.out.println(check.getTotalSolver()[0][0].getCells()[0][0].getToken());

        check.getTotalSolver()[0][0].getCells()[0][1].setBomb(); //Zet een bom in deze cel.
        System.out.println(check.getTotalSolver()[0][0].getCells()[0][1].getBomb());


    }
}
