package ai;

import othello.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe MinMax
 */
public class MinMax {
    Color player; // La couleur du joueur

    /**
     * Constructeur de la classe MinMax
     * @param player la couleur du joueur
     */
    public MinMax(Color player) {
        this.player = player;
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
            int value = minMax(child, child.getPlayer());
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
        return bestMove;
    }

    /**
     * Calcule le max d'un état
     * @param state l'état
     * @param player le joueur
     * @return le max d'un état
     */
    private int minMax(State state, Color player) {
        if (state.isTerminal()) { // Si l'état est terminal on retourne sa valeur
            return state.getValue();
        }
        int bestValue = (player == Color.BLACK) ? Integer.MIN_VALUE : Integer.MAX_VALUE; // On initialise la meilleure valeur (MIN ou MAX)
        ArrayList<State> children = state.getChildren();
        for (State child : children) { // Parcours de la liste des états enfants de l'état courant
            int value = minMax(child, player.getOpponent());
            if (player == Color.BLACK) {
                bestValue = Math.max(bestValue, value);
            } else {
                bestValue = Math.min(bestValue, value);
            }
        }
        return bestValue;
    }

}
