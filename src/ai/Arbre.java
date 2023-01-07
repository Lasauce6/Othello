package ai;

import java.awt.*;
import java.util.ArrayList;

/**
 * Classe Arbre
 */
public class Arbre {
    private Noeud racine;

    /**
     * Constructeur de la classe Arbre
     */
    public Arbre() {
        this.racine = null;
    }

    /**
     * Retourne la racine de l'arbre
     * @return la racine de l'arbre
     */
    public Noeud getRacine(){
        return this.racine;
    }

    /**
     * Ajoute un nœud à l'arbre
     * @param val la valeur du nœud
     * @param nbFlips le nombre de pions retournés
     */
    public void add(Point val, int nbFlips){
        racine = add(racine, val, nbFlips);
    }

    /**
     * Sous-méthode de add
     * @param n le nœud courant
     * @param val la valeur du nœud
     * @param nbFlips le nombre de pions retournés
     */
    private Noeud add(Noeud n, Point val, int nbFlips) {
        if (n == null) return new Noeud(val, nbFlips);

        if (n.getNbFlips() <= n.getNbFlips()) n.setGauche(add(n.getGauche(), val, nbFlips));
        else if (nbFlips > n.getNbFlips()) n.setDroite(add(n.getDroite(), val, nbFlips));
        else return n;

        return n;
    }

    /**
     * Créé un arbre à partir d'une liste de coups
     * @param moves la liste de coups
     * @param state l'état du jeu
     */
    public void createArbre(ArrayList<Point> moves, State state) {
        for (Point move : moves) {
            int nbFlips = state.getBoardCopy().getNbFlip(move.x, move.y, state.getPlayer());
            add(move, nbFlips);
        }
    }

    /**
     * Affiche l'arbre
     */
    public void print(){
        print(racine);
    }

    /**
     * Sous fonction d'affichage de l'arbre
     * @param n le nœud courant
     */
    private void print(Noeud n){
        if (n == null) return;
        print(n.getGauche());
        String move = "" + (char) (n.getVal().y + 'A') + (n.getVal().x + 1);
        System.out.println(move + " " + n.getNbFlips());
        print(n.getDroite());
    }
}
