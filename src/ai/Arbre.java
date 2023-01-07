package ai;

import othello.Color;

/**
 * Classe Arbre
 */
public class Arbre {
    private final Noeud racine;

    /**
     * Constructeur de la classe Arbre
     * @param val la valeur de la racine
     */
    public Arbre(Color[][] val){
        this.racine = new Noeud(val);
    }

    /**
     * Retourne la racine de l'arbre
     * @return la racine de l'arbre
     */
    public Noeud getRacine(){
        return this.racine;
    }

    /**
     * Retourne le nombre de nœuds de l'arbre
     * @return le nombre de nœuds de l'arbre
     */
    public int getNbNoeuds(){
        return getNbNoeuds(this.racine);
    }

    /**
     * Retourne le nombre de nœuds de l'arbre
     * @param n le nœud courant
     * @return le nombre de nœuds de l'arbre
     */
    private int getNbNoeuds(Noeud n){
        if (n == null){
            return 0;
        } else {
            return 1 + getNbNoeuds(n.getGauche()) + getNbNoeuds(n.getDroite());
        }
    }

    /**
     * Retourne la hauteur de l'arbre
     * @return la hauteur de l'arbre
     */
    public int getHauteur(){
        return getHauteur(this.racine);
    }

    /**
     * Retourne la hauteur de l'arbre
     * @param n le nœud courant
     * @return la hauteur de l'arbre
     */
    private int getHauteur(Noeud n){
        if (n == null){
            return 0;
        } else {
            return 1 + Math.max(getHauteur(n.getGauche()), getHauteur(n.getDroite()));
        }
    }
}
