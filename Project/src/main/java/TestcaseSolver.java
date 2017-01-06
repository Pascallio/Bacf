/**
 * Created by pascal on 3-1-17.
 * Een testcase om te laten zien hoe de solver van tictactoe werkt.
 */
public class TestcaseSolver {

    // Create Solver Object with each player in an User array object.
    private static Solver control = new Solver(new User[]{
                    new User("naam1", "avatar1", "X"),
                    new User("naam2", "avatar2", "O")
    });

    public static void main(String args[]) {

        // A loop for 6 individual turns
        for(int i = 0; i < 6; i++){

            // Check if all fields are full of tokens, if not: proceed
            if (!control.isFull()) {

                /* To make a move, select a single cell in [row][column] format:
                 * [0][0] = most upper left
                 * [2][2] = most down right
                 * Replace these values with the values selected on the gridPane.
                 */
                BigCell cel = control.getBigCells()[0][0];
                Cell smallCel2 = cel.getSmallCells()[0][0];

                // Or in a single one-liner:
                Cell smallCel = control.getBigCells()[0][0].getSmallCells()[0][0];

                // Create a user object for the current player.
                User player = control.getCurrentPlayer();

                // Set token for current player in the smallCel,
                // but only if there isn't a token yet.
                if (!smallCel.hasToken()) {
                    smallCel.setToken(player.getToken());
                }

                // Reduce life if the smallCell contains a bomb.
                if (smallCel.hasBomb()) {
                    player.setNumOfLifes(player.getNumOfLifes() - 1);
                }

                // How to show the name of the current player.
                // toString() has been overwritten.
                System.out.println(player);

                // Switch to the other player.
                control.switchPlayer();
            }
        }
    }
}
