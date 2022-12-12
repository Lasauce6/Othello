package othello;

import java.io.IOException;
import java.util.ArrayList;

public class Actions {
    private int player = -1;
    private Board board;

    public Actions(Board board) {
        this.board = board;
    }

    public void printBoard() {
        System.out.println("   A  B  C  D  E  F  G  H");
        for (int i = 0; i < Board.getSIZE(); i++) {
            System.out.print(i + 1 + "  ");
            for (int j = 0; j < Board.getSIZE(); j++) {
                if (board.getBoard()[i][j] == 1) {
                    System.out.print("B");
                } else if (board.getBoard()[i][j] == -1) {
                    System.out.print("W");
                } else {
                    System.out.print("X");
                }
                System.out.print("  ");
            }
            System.out.println(i + 1);
        }
        System.out.println("   A  B  C  D  E  F  G  H");
    }

    public ArrayList<Cell> getMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        for (int i = 0; i < Board.getSIZE(); i++) {
            for (int j = 0; j < Board.getSIZE(); j++) {
                if (board.getBoard()[i][j] == 0) {
//                    TODO : verifier si le coup est valide
                }
            }
        }
        return moves;
    }
}
