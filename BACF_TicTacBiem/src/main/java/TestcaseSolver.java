import com.sun.imageio.plugins.gif.GIFImageReader;
import javafx.scene.layout.GridPane;

/**
 * Created by pascal on 3-1-17.
 * Een testcase om te laten zien hoe de solver van tictactoe werkt.
 */
public class TestcaseSolver {

    // Create Solver Object with each player in an User array object.
    private static Solver control = new Solver(new User[]{
                    new User("naam1", "avatar1", "X"),
                    new User("naam2", "avatar2", "O")}, new GridPane(), "speelscherm");

    public static void main(String args[]) {
            // Check if all (big) fields are full of tokens, if not: proceed
            if (!control.isFull()) {

                /* To make a move, select a single cell in (column, row) format:
                 * (0, 0) = most upper left
                 * (2, 2) = most down right
                 * Replace these values with the values selected on the gridPane.
                 */
                Solver.BigCell bigCell = control.getBigCells(0, 0);

                // Get a small cel within a BigCell object.
                Solver.BigCell.Cell smallCell = control.getBigCells(0, 0).getSmallCells(0, 0);

                // Create a user object for the current player.
                User player = control.getCurrentPlayer();

                // Set action on mouseclick of cell. If the cell already contains
                // a token, a error message will be displayed.
                // This doesn't have to be called, but calling the method will
                // demonstrate its function.
                smallCell.onMouseClick();

                // Add a few more tokens
                bigCell.getSmallCells(1, 0).onMouseClick();
                bigCell.getSmallCells(2, 0).onMouseClick();
                bigCell.getSmallCells(2, 0).onMouseClick();


                // How to check if a big cell has won
                boolean won = bigCell.isWon(player.getToken());

                // How to check if a big cell is full.
                boolean full = bigCell.isFull();

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
