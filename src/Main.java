import othello.graphics.Client;
import othello.Board;

public class Main {
    /**
     * Lancement du menu principal
     */
    public static void main(String[] args) {
        Board board = new Board();
        Client client = new Client();
        client.menu(board);
    }


}