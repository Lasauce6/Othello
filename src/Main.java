import othello.Actions;
import othello.Board;
import othello.Color;

public class Main {
    /**
     * Lancement du menu principal
     */
    public static void main(String[] args) {
        Board board = new Board(4);
        Actions actions = new Actions(board);
        actions.play(Color.BLACK);
    }
}