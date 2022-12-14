package othello;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final int SIZE;
    private Color[][] board;
    private int numberOfMoves;

    public Board(Color[][] board, int numberOFMoves, int SIZE) {
        this.SIZE = SIZE;
        this.board = board;
        this.numberOfMoves = numberOFMoves;
    }

    public Board(Board board) {
        this.SIZE = board.getSize();
        this.board = new Color[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board.board[i], 0, this.board[i], 0, SIZE);
        }
        this.numberOfMoves = board.numberOfMoves;
    }

    public Board(int SIZE) {
        this.SIZE = SIZE;
        this.board = new Color[SIZE][SIZE];
        board[3][3] = Color.WHITE;
        board[3][4] = Color.BLACK;
        board[4][3] = Color.BLACK;
        board[4][4] = Color.WHITE;
        this.numberOfMoves = 0;

    }

    public int getSize() {
        return SIZE;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public Color[][] getBoard() {
        return board;
    }

    public void setBoard(Color[][] board) {
        this.board = board;
    }

    public void move(int r, int c, Color player) {
        if (r < SIZE && r >= 0 && c < SIZE && c>= 0 && player != Color.EMPTY) {
            numberOfMoves++;
            board[r][c] = player;
        }
    }

    public ArrayList<Point> getPossibleMoves(Color player) {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Color.EMPTY) {
                    if (isPossibleMove(i, j, player)) {
                        list.add(new Point(i, j));
                    }
                }
            }
        }
        return list;
    }

    private boolean isPossibleMove(int r, int c, Color player) {
        // TODO : retourner si un coup est possible
        return false;
    }
}
