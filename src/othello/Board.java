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
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board.getBoard()[i][j];
            }
        }
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board.board[i], 0, this.board[i], 0, SIZE);
        }
        this.numberOfMoves = board.numberOfMoves;
    }

    public Board(int SIZE) {
        this.SIZE = SIZE;
        this.board = new Color[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = Color.EMPTY;
            }
        }
        board[(SIZE / 2) - 1 ][(SIZE / 2) - 1] = Color.WHITE;
        board[(SIZE / 2) - 1][SIZE / 2] = Color.BLACK;
        board[SIZE / 2][(SIZE / 2) - 1] = Color.BLACK;
        board[SIZE / 2][SIZE / 2] = Color.WHITE;
        this.numberOfMoves = 4;

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
        if (r < SIZE && r >= 0 && c < SIZE && c >= 0 && player != Color.EMPTY) {
            numberOfMoves++;
            board[r][c] = player;
            flip(r, c, player);
        }
    }

    private void flip(int r, int c, Color color) {
        int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] col = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) {
            if (isPossibleMoveInDirection(r, c, color, row[i], col[i])) {
                int r1 = r + row[i];
                int c1 = c + col[i];
                while (board[r1][c1] != color) {
                    board[r1][c1] = color;
                    r1 += row[i];
                    c1 += col[i];
                }
            }
        }
    }

    public void setPossibleMoves(Color color) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Color.POSSIBLE_MOVE) {
                    board[i][j] = Color.EMPTY;
                }
            }
        }
        ArrayList<Point> possibleMoves = getPossibleMoves(color);
        for (Point point : possibleMoves) {
            board[point.x][point.y] = Color.POSSIBLE_MOVE;
        }
    }

    public ArrayList<Point> getPossibleMoves(Color player) {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Color.EMPTY && isAdjacentOppositeColor(i, j, player)
                        || board[i][j] == Color.POSSIBLE_MOVE && isAdjacentOppositeColor(i, j, player)) { // si la case est vide et qu'il y a une case de la couleur adverse adjacente
                    if(isPossibleMoveInDirection(i, j, player, -1, -1)
                            || isPossibleMoveInDirection(i, j, player, -1, 0)
                            || isPossibleMoveInDirection(i, j, player, -1, 1)
                            || isPossibleMoveInDirection(i, j, player, 0, -1)
                            || isPossibleMoveInDirection(i, j, player, 0, 1)
                            || isPossibleMoveInDirection(i, j, player, 1, -1)
                            || isPossibleMoveInDirection(i, j, player, 1, 0)
                            || isPossibleMoveInDirection(i, j, player, 1, 1)) {
                        list.add(new Point(i, j));
                    }
                }
            }
        }
        return list;
    }

    public ArrayList<Point> getMoves() {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Color.POSSIBLE_MOVE) {
                    list.add(new Point(i, j));
                }
            }
        }
        return list;
    }

    private Color getOppositeColor(Color player) {
        if (player == Color.BLACK) {
            return Color.WHITE;
        } else if (player == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.EMPTY;
        }
    }

    private boolean isAdjacentOppositeColor(int i, int j, Color player) {
        Color oppositeColor = getOppositeColor(player);
        return (i > 0 && board[i - 1][j] == oppositeColor) // haut
                || (i < SIZE - 1 && board[i + 1][j] == oppositeColor) // bas
                || (j > 0 && board[i][j - 1] == oppositeColor) // gauche
                || (j < SIZE - 1 && board[i][j + 1] == oppositeColor) // droite
                || (i > 0 && j > 0 && board[i - 1][j - 1] == oppositeColor) // haut gauche
                || (i > 0 && j < SIZE - 1 && board[i - 1][j + 1] == oppositeColor) // haut droite
                || (i < SIZE - 1 && j > 0 && board[i + 1][j - 1] == oppositeColor) // bas gauche
                || (i < SIZE - 1 && j < SIZE - 1 && board[i + 1][j + 1] == oppositeColor); // bas droite
    }

    private boolean isPossibleMoveInDirection(int i, int j, Color player, int r, int c) {
        int x = i + r;
        int y = j + c;
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (board[x][y] == player) return false;
        Color oppositeColor = getOppositeColor(player);
        while (x >= 0 && x < SIZE && y >= 0 && y < SIZE && board[x][y] == oppositeColor) {
            x += r;
            y += c;
        }
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return board[x][y] == player;
    }
}
