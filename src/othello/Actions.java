package othello;

import java.io.IOException;

public class Actions {
    private Board board;

    public Actions(Board board) {
        this.board = board;
    }

    public void printBoard() {
        System.out.println("  A  B  C  D  E  F  G  H");
        for (int i = 0; i < 8; i++) {
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
            System.out.println();
        }
    }
}
