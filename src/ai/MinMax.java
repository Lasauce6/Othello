package ai;

import othello.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe MinMax
 */
public class MinMax implements Serializable {
    private final int level; // Le niveau de l'IA
    private final Color player; // La couleur du joueur

    /**
     * Constructeur de la classe MinMax
     * @param player la couleur du joueur
     */
    public MinMax(Color player, int level) {
        this.player = player;
        this.level = level;
    }

    /**
     * Constructeur de copie de la classe MinMax
     * @param minMax l'IA à copier
     */
    public MinMax(MinMax minMax) {
        this.player = minMax.getPlayer();
        this.level = minMax.getLevel();
    }

    /**
     * Retourne la couleur du joueur
     * @return la couleur du joueur
     */
    public Color getPlayer() {
        return player;
    }

    /**
     * Retourne le niveau de l'IA
     * @return le niveau de l'IA
     */
    public int getLevel() {
        return level;
    }

    /**
     * Retourne le meilleur coup à jouer
     * @param state l'état du jeu
     * @return le meilleur coup à jouer
     */
    public Point getBestMove(State state) {
        int bestValue = (player == Color.BLACK) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Point bestMove = null;
        ArrayList<State> children = state.getChildren();
        for (State child : children) {
            int value;
            if (level == 1) value = minMax(child, child.getPlayer(), 0);
            else value = minMax(child, child.getPlayer(), 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (player == Color.BLACK) {
                if (value > bestValue) {
                    bestValue = value;
                    bestMove = child.getLastMove();
                }
            } else {
                if (value < bestValue) {
                    bestValue = value;
                    bestMove = child.getLastMove();
                }
            }
        }
        if (bestMove == null) return children.get(0).getLastMove(); // Si aucun coup n'est trouvé, on joue le premier coup possible
        else return bestMove;
    }

    /**
     * Calcule le max d'un état
     * @param state l'état
     * @param player le joueur
     * @param depth la profondeur
     * @return le max d'un état
     */
    private int minMax(State state, Color player, int depth) {
        if (state.isTerminal() || depth == 6) { // Si l'état est terminal ou la profondeur est maximale on retourne sa valeur
            return state.getValue();
        }
        int bestValue = (player == Color.BLACK) ? Integer.MIN_VALUE : Integer.MAX_VALUE; // On initialise la meilleure valeur (MIN ou MAX)
        ArrayList<State> children = state.getChildren();
        for (State child : children) { // Parcours de la liste des états enfants de l'état courant
            int value = minMax(child, player.getOpponent(), depth + 1);
            if (player == Color.BLACK) {
                bestValue = Math.max(bestValue, value);
            } else {
                bestValue = Math.min(bestValue, value);
            }
        }
        return bestValue;
    }

    /**
     * Calcule le max d'un état avec alpha-beta
     * @param state l'état
     * @param player le joueur
     * @param alpha alpha
     * @param beta beta
     * @return le max d'un état avec alpha-beta
     */
    private int minMax(State state, Color player, int depth, int alpha, int beta) {
        if (state.isTerminal() || depth == 8) { // Si l'état est terminal ou la profondeur est maximale on retourne sa valeur
            return state.getValue();
        }
        int bestValue = (player == Color.BLACK) ? Integer.MIN_VALUE : Integer.MAX_VALUE; // On initialise la meilleure valeur (MIN ou MAX)
        ArrayList<State> children = state.getChildren();
        for (State child : children) { // Parcours de la liste des états enfants de l'état courant
            int value = minMax(child, player.getOpponent(), depth + 1, alpha, beta);
            if (player == Color.BLACK) {
                bestValue = Math.max(bestValue, value);
                alpha = Math.max(alpha, bestValue);
            } else {
                bestValue = Math.min(bestValue, value);
                beta = Math.min(beta, bestValue);
            }
            if (beta <= alpha) break;
        }
        return bestValue;
    }
}
