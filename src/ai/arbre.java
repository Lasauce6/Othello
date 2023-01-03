package ai;

public class arbre{
    private final Noeud racine;
 
    /**
     * @param racine
     */
    public arbre(Noeud racine) {
        this.racine = racine;
    }

    /**
     * @param val
     */
    public void ajouter(int val){

        Noeud n = new Noeud();
        Noeud crt=racine;
        Noeud pere=null;
        while(crt!=null){
            pere=crt;
            if(val<racine.getVal()){
                crt=crt.getGauche();
            }else{
                crt=crt.getDroite();
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

