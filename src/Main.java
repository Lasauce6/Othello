import othello.Actions;
import othello.Board;

/**
 * Classe Main
 */
public class Main {

    /**
     * Lancement du menu principal
     */
    public static void main(String[] args) {
        Board board = new Board(8);
        Actions actions = new Actions(board);
        actions.play(board.getCurrentPlayer());
    }
}