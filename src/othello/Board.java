package othello;

import ai.MinMax;
import ai.Naif;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Board
 */
public class Board implements Serializable {
    private Color currentPlayer; // Le joueur courant
    private final int SIZE; // La taille du plateau de jeu
    private final Color[][] board; // Le plateau de jeu
    private int numberOfMoves; // Nombre de coups joués
    private Point lastMove; // Le dernier coup joué
    private boolean isIA; // Indique si l'IA est présente
    private Naif naif; // L'IA si elle est présente
    private MinMax minMax; // L'IA si elle est présente

    /**
     * Constructeur de copie de la classe Board
     * @param board le plateau de jeu à copier
     */
    public Board(Board board) {
        this.SIZE = board.getSize();
        this.board = new Color[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board.getBoard()[i][j];
            }
        }
        this.numberOfMoves = board.getNumberOfMoves();
        this.currentPlayer = board.getCurrentPlayer();
        this.lastMove = board.getLastMove();
        if (board.isIA() && board.getNaif() != null) {
            this.naif = new Naif(board.getNaif());
            this.isIA = true;
        } else if (board.isIA() && board.getMinMax() != null) {
            this.minMax = new MinMax(board.getMinMax());
            this.isIA = true;
        } else {
            this.isIA = false;
        }
    }

    /**
     * Constructeur de la classe Board sans IA
     * @param SIZE la taille du plateau de jeu
     */
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
        this.currentPlayer = Color.BLACK;
        this.lastMove = null;
        this.isIA = false;
        this.naif = null;
        this.minMax = null;
    }

    /**
     * Retourne la taille du plateau de jeu
     * @return la taille du plateau de jeu
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Retourne le nombre de coups joués
     * @return le nombre de coups joués
     */
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /**
     * Retourne le plateau de jeu
     * @return le plateau de jeu
     */
    public Color[][] getBoard() {
        return board;
    }

    /**
     * Retourne le joueur courant
     * @return le joueur courant
     */
    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Change le joueur courant
     * @param currentPlayer le nouveau joueur courant
     */
    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Retourne le dernier coup joué
     * @return le dernier coup joué
     */
    public Point getLastMove() {
        return lastMove;
    }

    /**
     * Change le dernier coup joué
     * @param lastMove le nouveau dernier coup joué
     */
    public void setLastMove(Point lastMove) {
        this.lastMove = lastMove;
    }

    /**
     * Indique si l'IA est présente
     * @return true si l'IA est présente, false sinon
     */
    public boolean isIA() {
        return isIA;
    }

    /**
     * Retourne l'IA naif
     * @return l'IA naif
     */
    public Naif getNaif() {
        return naif;
    }

    /**
     * Retourne l'IA MinMax
     * @return l'IA MinMax
     */
    public MinMax getMinMax() {
        return minMax;
    }

    /**
     * Change le plateau pour une IA minmax
     * @param minMax l'IA minmax
     */
    public void setMinMax(MinMax minMax) {
        this.isIA = true;
        this.minMax = minMax;
    }

    /**
     * Change le plateau pour une IA naif
     * @param naif l'IA naif
     */
    public void setNaif(Naif naif) {
        this.isIA = true;
        this.naif = naif;
    }

    /**
     * Joue un coup
     * @param r la ligne du coup
     * @param c la colonne du coup
     * @param player le joueur qui joue le coup
     */
    public void move(int r, int c, Color player) {
        if (r < SIZE && r >= 0 && c < SIZE && c >= 0 && player != Color.EMPTY) {
            lastMove = new Point(r, c);
            numberOfMoves++;
            board[r][c] = player;
            flip(r, c, player);
            currentPlayer = currentPlayer.getOpponent();
        }
    }

    /**
     * Retourne les cases qui peuvent être jouées
     * @param r la ligne du coup
     * @param c la colonne du coup
     * @param color le joueur qui joue le coup
     */
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

    /**
     * Met à jour le plateau de jeu avec les coups possibles
     * @param color le joueur dont on veut les coups possibles
     */
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

    /**
     * Retourne une liste des coups possibles
     * @param player le joueur dont on veut les coups possibles
     * @return une liste des coups possibles
     */
    public ArrayList<Point> getPossibleMoves(Color player) {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Color.EMPTY && isAdjacentOppositeColor(i, j, player)
                        || board[i][j] == Color.POSSIBLE_MOVE && isAdjacentOppositeColor(i, j, player)) { // si la case est vide et qu'il y a une case de la couleur adverse adjacente
                    if(isPossibleMoveInDirection(i, j, player, -1, 0) // haut
                            || isPossibleMoveInDirection(i, j, player, 1, 0) // bas
                            || isPossibleMoveInDirection(i, j, player, 0, -1) // gauche
                            || isPossibleMoveInDirection(i, j, player, 0, 1) // droite
                            || isPossibleMoveInDirection(i, j, player, -1, -1) // haut gauche
                            || isPossibleMoveInDirection(i, j, player, -1, 1) // haut droite
                            || isPossibleMoveInDirection(i, j, player, 1, -1) // bas gauche
                            || isPossibleMoveInDirection(i, j, player, 1, 1)) { // bas droite
                        list.add(new Point(i, j));
                    }
                }
            }
        }
        return list;
    }

    /**
     * Retourne une liste des coups possibles du plateau de jeu
     * @return une liste des coups possibles du plateau de jeu
     */
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

    /**
     * Retourne vrai si le pion joué est adjacent à une case de la couleur adverse
     * @param i la ligne du pion
     * @param j la colonne du pion
     * @param player le joueur qui joue le pion
     * @return vrai si le pion joué est adjacent à une case de la couleur adverse
     */
    private boolean isAdjacentOppositeColor(int i, int j, Color player) {
        Color oppositeColor = player.getOpponent();
        return (i > 0 && board[i - 1][j] == oppositeColor) // haut
                || (i < SIZE - 1 && board[i + 1][j] == oppositeColor) // bas
                || (j > 0 && board[i][j - 1] == oppositeColor) // gauche
                || (j < SIZE - 1 && board[i][j + 1] == oppositeColor) // droite
                || (i > 0 && j > 0 && board[i - 1][j - 1] == oppositeColor) // haut gauche
                || (i > 0 && j < SIZE - 1 && board[i - 1][j + 1] == oppositeColor) // haut droite
                || (i < SIZE - 1 && j > 0 && board[i + 1][j - 1] == oppositeColor) // bas gauche
                || (i < SIZE - 1 && j < SIZE - 1 && board[i + 1][j + 1] == oppositeColor); // bas droite
    }

    /**
     * Retourne vrai si le coup est possible dans la direction donnée
     * @param i la ligne du coup
     * @param j la colonne du coup
     * @param player le joueur qui joue le coup
     * @param r la direction de la ligne
     * @param c la direction de la colonne
     * @return vrai si le coup est possible dans la direction donnée
     */
    private boolean isPossibleMoveInDirection(int i, int j, Color player, int r, int c) {
        int x = i + r;
        int y = j + c;
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (board[x][y] == player) return false;
        Color oppositeColor = player.getOpponent();
        while (x >= 0 && x < SIZE && y >= 0 && y < SIZE && board[x][y] == oppositeColor) {
            x += r;
            y += c;
        }
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return board[x][y] == player;
    }

    /**
     * Retourne le nombre de pions retournés par un coup
     * @param x la ligne du coup
     * @param y la colonne du coup
     * @param color la couleur du coup
     * @return le nombre de pions retournés par un coup
     */
    public int getNbFlip(int x, int y, Color color) {
        int nbFlipped = 0;
        if (isAdjacentOppositeColor(x, y, color)) {
            if (isPossibleMoveInDirection(x, y, color, -1, 0)) { // haut
                int i = x - 1;
                while (i > 0 && board[i][y] == color.getOpponent()) {
                    nbFlipped++;
                    i--;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, 1, 0)) { // bas
                int i = x + 1;
                while (i < SIZE - 1 && board[i][y] == color.getOpponent()) {
                    nbFlipped++;
                    i++;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, 0, -1)) { // gauche
                int j = y - 1;
                while (j > 0 && board[x][j] == color.getOpponent()) {
                    nbFlipped++;
                    j--;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, 0, 1)) { // droite
                int j = y + 1;
                while (j < SIZE - 1 && board[x][j] == color.getOpponent()) {
                    nbFlipped++;
                    j++;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, -1, -1)) { // haut gauche
                int i = x - 1;
                int j = y - 1;
                while (i > 0 && j > 0 && board[i][j] == color.getOpponent()) {
                    nbFlipped++;
                    i--;
                    j--;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, -1, 1)) { // haut droite
                int i = x - 1;
                int j = y + 1;
                while (i > 0 && j < SIZE - 1 && board[i][j] == color.getOpponent()) {
                    nbFlipped++;
                    i--;
                    j++;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, 1, -1)) { // bas gauche
                int i = x + 1;
                int j = y - 1;
                while (i < SIZE - 1 && j > 0 && board[i][j] == color.getOpponent()) {
                    nbFlipped++;
                    i++;
                    j--;
                }
            }
            if (isPossibleMoveInDirection(x, y, color, 1, 1)) { // bas droite
                int i = x + 1;
                int j = y + 1;
                while (i < SIZE - 1 && j < SIZE - 1 && board[i][j] == color.getOpponent()) {
                    nbFlipped++;
                    i++;
                    j++;
                }
            }
        }
        return nbFlipped;
    }
}
