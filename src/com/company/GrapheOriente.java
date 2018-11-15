package com.company;

public class GrapheOriente extends Graphe{

    public GrapheOriente(int n) {
        super(n);
    }

    public void ajourteArete(int i, int j){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).add(new Arete(j));
            }
        }
    }

    public void ajourteArete(int i, int j, int p){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).add(new Arete(j,p));
            }
        }
    }


}
