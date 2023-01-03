package ai;

public class arbre{
    private final Noeud racine;
 
    public arbre(Noeud racine) {
        this.racine = racine;
    }

    public arbre(int val){
        racine=new Noeud();
                         }

    public void ajouter(int val){

        Noeud n = new Noeud();
        Noeud courant=racine;
        Noeud pere=null;
        while(courant!=null){
            pere=courant;
            if(val<racine.getVal()){
                courant=courant.getGauche();
            }else{
                courant=courant.getDroite();
            }
        }
        if(val<pere.getVal()){
            pere.setGauche(n);
        }else{
            pere.setDroite(n);
        }
    }

    public void afficher(){
        afficher(racine);
    }

    /**
     * @param n
     */
    private void afficher(Noeud n){
        if(n!=null){
            afficher(n.getGauche());
            System.out.println(n.getVal());
            afficher(n.getDroite());
        }
    }
}

