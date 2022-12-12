package othello;

public class Board {
    private static final int SIZE = 8;
    private int[][] board;
    private int numberOfMoves;
    private Cell[][] cellBoard = new Cell[SIZE][SIZE];

    public Board(int[][] board, int numberOFMoves) {
        this.board = board;
        this.numberOfMoves = numberOFMoves;
        for (int c = 0; c < SIZE; c++) {
            for (int r = 0; r < SIZE; r++) {
                    cellBoard[r][c] = new Cell(r, c);
            }
        }
    }

    public Board(Board board) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board.board[i], 0, this.board[i], 0, SIZE);
        }
        this.numberOfMoves = board.numberOfMoves;
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board.cellBoard[i], 0, this.cellBoard[i], 0, SIZE);
        }
    }

    public Board() {
        this.board = new int[SIZE][SIZE];
        board[3][3] = 1;
        board[3][4] = -1;
        board[4][3] = -1;
        board[4][4] = 1;
        for (int c = 0; c < SIZE; c++) {
            for (int r = 0; r < SIZE; r++) {
                cellBoard[r][c] = new Cell(r, c);
            }
        }
        cellBoard[3][3] = new Cell(3, 3, 1);
        cellBoard[3][4] = new Cell(3, 4, -1);
        cellBoard[4][3] = new Cell(4, 3, -1);
        cellBoard[4][4] = new Cell(4, 4, 1);
        this.numberOfMoves = 0;

    }

    public static int getSIZE() {
        return SIZE;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public int[][] getBoard() {
        return board;
    }

    public Cell[][] getCellBoard() {
        return cellBoard;
    }

    public void move(Cell cell) {
        if (cell != null) {
            numberOfMoves++;
            board[cell.r()][cell.c()] = cell.player();
            cellBoard[cell.r()][cell.c()] = cell;
            for (int i = 0; i < 8; i++) {
//                 TODO : flip les pions dans les 8 directions
            }
        }
    }

    //test
}
