package ai;

import othello.Board;
import othello.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe Naif
 */
public class Naif implements Serializable {
    private final Board board; // Le plateau de jeu
    private final Color color; // La couleur du joueur
    private final int level; // Le niveau de l'IA

    /**
     * Constructeur de la classe Naif
     * @param level le niveau de l'IA
     */
    public Naif(int level, Color color, Board board) {
        this.level = level;
        this.color = color;
        this.board = board;
    }

    /**
     * Constructeur de copie de la classe Naif
     * @param naif l'IA à copier
     */
    public Naif(Naif naif) {
        this.level = naif.getLevel();
        this.color = naif.getColor();
        this.board = naif.getBoard();
    }

    /**
     * Retourne le niveau de l'IA
     * @return le niveau de l'IA
     */
    public int getLevel() {
        return level;
    }

    /**
     * Retourne la couleur de l'IA
     * @return la couleur de l'IA
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retourne le plateau de jeu
     * @return le plateau de jeu
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Retourne le meilleur coup à jouer
     * @return le meilleur coup à jouer
     */
    public Point getBestMove() {
        if (level == 1) {
            return getBestMoveLevel1();
        } else {
            return getBestMoveLevel2();
        }
    }

    /**
     * Retourne le meilleur coup à jouer au niveau 1
     * @return le meilleur coup à jouer au niveau 1
     */
        private Point getBestMoveLevel1() {
        ArrayList<Point> moves = board.getPossibleMoves(color);
        int max = 0; // Le nombre de pions retournés par le meilleur coup
        Point bestMove = null;
        for (Point point : moves) {
            int nb = board.getNbFlip(point.x, point.y, color); // Le nombre de pions retournés par le coup
            if (nb > max) { // Si le nombre de pions retournés est supérieur au nombre de pions retournés par le meilleur coup
                max = nb;
                bestMove = point;
            }
        }
        return bestMove; // Retourne le meilleur coup
    }

    /**
     * Retourne le meilleur coup à jouer au niveau 2
     * @return le meilleur coup à jouer au niveau 2
     */
    private Point getBestMoveLevel2() {
        Arbre arbre = new Arbre();
        ArrayList<Point> moves = board.getPossibleMoves(color);
        arbre.createArbre(moves, new State(board));
        arbre.print();
        int max = 0;
        Point bestMove = null;
        for (Point point : moves) {
            int nb = board.getNbFlip(point.x, point.y, color);
            if (nb > max) {
                max = nb;
                bestMove = point;
            }
        }
        return bestMove;
    }

}
