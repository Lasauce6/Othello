package ai;

import othello.Board;
import othello.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Naif
 */
public class Naif {
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
        Random random = new Random();
        return moves.get(random.nextInt(moves.size()));
    }

    /**
     * Retourne le meilleur coup à jouer au niveau 2
     * @return le meilleur coup à jouer au niveau 2
     */
    private Point getBestMoveLevel2() {
        return null; // TODO: a faire avec un arbre de recherche
    }

}
