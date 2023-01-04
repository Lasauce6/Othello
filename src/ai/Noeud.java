package ai;

import othello.Color;

/**
 * Classe Noeud
 */
public class Noeud {
    private Color[][] val; // Valeur du nœud
    private Noeud gauche; // Fils gauche
    private Noeud droite; // Fils droit

    /**
     * Constructeur de la classe Nœud
     * @param val la valeur du nœud
     */
    public Noeud(Color[][] val) {
        this.val = val;
        this.gauche = null;
        this.droite = null;
    }

    /**
     * Change la valeur du nœud
     */
    public void setVal(Color[][] val) {
        this.val = val;
    }

    /**
     * Retourne la valeur du nœud
     * @return la valeur du nœud
     */
    public Color[][] getVal() {
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
}
