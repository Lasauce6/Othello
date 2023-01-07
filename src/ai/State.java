package ai;

import othello.Board;
import othello.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe State
 */
public class State {
    private final Board boardCopy; // Le plateau copié de jeu
    private Color[][] board; // Le plateau de jeu
    private Color player; // Le joueur
    private int numberOfMoves; // Le nombre de coups joués
    private Point lastMove; // Le dernier coup joué

    /**
     * Constructeur de la classe State
     * @param board le plateau de jeu
     */
    public State(Board board) {
        this.board = board.getBoard();
        this.player = board.getCurrentPlayer();
        this.numberOfMoves = board.getNumberOfMoves();
        this.boardCopy = new Board(board);
        this.lastMove = board.getLastMove();
    }

    /**
     * Retourne le plateau copié de jeu
     * @return le plateau copié de jeu
     */
    public Board getBoardCopy() {
        return boardCopy;
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
    public Color getPlayer() {
        return player;
    }

    /**
     * Change le joueur courant
     * @param player le nouveau joueur courant
     */
    public void setPlayer(Color player) {
        this.player = player;
    }

    /**
     * Retourne le nombre de coups joués
     * @return le nombre de coups joués
     */
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /**
     * Change le nombre de coups joués
     * @param numberOfMoves le nouveau nombre de coups joués
     */
    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
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
     * Indique si le plateau est plein
     * @return true si le plateau est plein, false sinon
     */
    public boolean isTerminal() {
        return numberOfMoves == board.length * board[0].length
                || boardCopy.getPossibleMoves(player).size() == 0 && boardCopy.getPossibleMoves(player.getOpponent()).size() == 0;
    }

    /**
     * Joue un coup
     * @param move le coup à jouer
     */
    public void applyMove(Point move) {
        boardCopy.move(move.x, move.y, player);
        lastMove = move;
        board = boardCopy.getBoard();
        player = player.getOpponent();
        numberOfMoves++;
    }

    /**
     * @return la valeur d'un joueur
     */
    public int getValue() {
        int value = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == Color.BLACK) value++;
                else if (board[i][j] == Color.WHITE) value--;
            }
        }
        return value;
    }

    /**
     * @return la liste des enfants d'un état
     */
    public ArrayList<State> getChildren() {
        ArrayList<State> children = new ArrayList<>();
        ArrayList<Point> possibleMoves = boardCopy.getPossibleMoves(player);
        for (Point move : possibleMoves) {
            State child = new State(boardCopy);
            child.applyMove(move);
            children.add(child);
        }
        return children;
    }
}
