package ai;

import java.awt.*;

/**
 * Classe Noeud
 */
public class Noeud {
    private Point val; // Valeur du nœud
    int nbFlips; // Nombre de pions retournés
    private Noeud gauche; // Fils gauche
    private Noeud droite; // Fils droit

    /**
     * Constructeur de la classe Nœud
     * @param val la valeur du nœud
     */
    public Noeud(Point val, int nbFlips) {
        this.val = val;
        this.nbFlips = nbFlips;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Change la valeur du nœud
     */
    public void setVal(Point val) {
        this.val = val;
    }

    /**
     * Retourne la valeur du nœud
     * @return la valeur du nœud
     */
    public Point getVal() {
        return this.val;
    }

    /**
     * Change le fils gauche du nœud
     */
    public void setGauche(Noeud n) {
        this.gauche = n;
    }

    /**
     * Retourne le fils gauche du nœud
     * @return le fils gauche du nœud
     */
    public Noeud getGauche() {
        return this.gauche;
    }

    /**
     * Change le fils droit du nœud
     */
    public void setDroite(Noeud n) {
        this.droite = n;
    }

    /**
     * Retourne le fils droit du nœud
     * @return le fils droit du nœud
     */
    public Noeud getDroite() {
        return this.droite;
    }

    /**
     * Retourne le nombre de retournements de pions
     * @return le nombre de retournements de pions
     */
    public int getNbFlips() {
        return this.nbFlips;
    }
}
