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

            // Check if all (big) fields are full of tokens, if not: proceed
            if (!control.isFull()) {

                /* To make a move, select a single cell in [row][column] format:
                 * [0][0] = most upper left
                 * [2][2] = most down right
                 * Replace these values with the values selected on the gridPane.
                 *
                 * BigCell cell = control.getBigCells()[0][0];
                 * Or:
                 */
                BigCell cell = control.getBigCells(0, 0);

                /* Get a small cel within a BigCell object.
                 * Cell smallCell = control.getBigCells()[0][0].getSmallCells()[0][0];
                 * Or somewhat neater syntax:
                 */
                Cell smallCell = control.getBigCells(0, 0).getSmallCells(0, 0);

                // Create a user object for the current player.
                User player = control.getCurrentPlayer();

                // Set token for current player in the smallCel,
                // but only if there isn't a token yet.
                if (!smallCell.hasToken()) {
                    smallCell.setToken(player.getToken());
                }

                // Reduce life if the smallCell contains a bomb.
                if (smallCell.hasBomb()) {
                    player.setNumOfLifes(player.getNumOfLifes() - 1);
                }

                // Add a few more bombs
                cell.getSmallCells(1, 0).setToken(player.getToken());
                cell.getSmallCells(2, 0).setToken(player.getToken());

                // How to check if a big cell has won
                boolean won = cell.isWon(player.getToken());

                // How to check if a big cell is full.
                boolean full = cell.isFull();

                // Show results
                System.out.println(won);
                System.out.println(full);

                // How to show the name of the current player.
                // toString() has been overwritten.
                System.out.println(player);

                // Switch to the other player.
                control.switchPlayer();

                // Show that the player has been switched.
                player = control.getCurrentPlayer();
                System.out.println(player);
        }
    }
}
