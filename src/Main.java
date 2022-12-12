
import othello.Actions;
import othello.Board;

import java.io.IOException;

public class Main {
    /**
     * Lancement du menu principal
     */
    public static void main(String[] args) {
        Board board = new Board();
        Actions actions = new Actions(board);
        actions.printBoard();
    }
}