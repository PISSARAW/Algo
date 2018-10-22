package com.company;

import java.util.*;

/**
 * Algotithme de Graphe
 */
public class Graphe {
    protected final int n;
    protected ArrayList<Arete> a;

    /**
     * Constructeur de Graphe
     * @param n Nombre de sommets
     */
    public Graphe(int n) {
        this.n = n;
        this.a =  new ArrayList<Arete>();
        for(int i =0 ; i<n; i++){
            a.add(new Arete(new LinkedList<Integer>(),0));
        }
    }

    /**
     * Ajouter une arête au Graphe G
     * @param i 1er Sommet
     * @param j 2eme Sommet
     */
    public void ajourteArete(int i, int j){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).getAdj().add(j);
                this.a.get(j).getAdj().add(i);
            }
        }
    }

    /**
     * Ajouter une arête valuée au Graphe G
     * @param i 1er Sommet
     * @param j 2eme Sommet
     * @param poids Poids de l'arête
     */
    public void ajourteArete(int i, int j, int poids){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).getAdj().add(j);
                this.a.get(j).getAdj().add(i);
                this.a.get(i).setPoids(poids);
                this.a.get(j).setPoids(poids);

            }
        }
    }

    /**
     * Verifier la présence d'un sommet dans le Graphe G
     * @param v Sommet à vérifier
     * @return
     */
    public boolean verifieSommet(int v){
        if(v>=this.getN()||v<0)
            return false;
        return true;
    }

    /**
     * Longueur du Graphe G
     * @return n
     */
    public int getN(){
        return this.n;
    }

    /**
     * Liste des voisins d'un Sommet
     * @param v Un sommet
     * @return Liste de voisins
     */
    public List<Integer> voisins (int v){
        if(verifieSommet(v))
            return Collections.unmodifiableList(this.a.get(v).getAdj());
        return null;
    }

    /**
     * Vérifier si deux sommets sont voisins
     * @param i 1er sommet
     * @param j 2eme sommet
     * @return booléen
     */
    public boolean sontVoisins(int i, int j){
        return (this.a.get(i).getAdj().contains(j)||this.a.get(j).getAdj().contains(i));
    }

    public int degre(int v){
        return this.a.get(v).getAdj().size();
    }


    public int maxDegre(){
        return this.n-1;
    }



    /**
     * Parcours en largeur sur tous les sommets d'un graphe
     * @return Resultat du parcours en largeur
     */
    public Resultat parcoursLargeur(){
        Resultat res = new Resultat((new String [this.getN()]),(new int [this.getN()]),(new int [this.getN()]));
        String s="";
        //Initialisation des tableaux Couleurs, Pi et Dist
        for(int i=0; i<this.getN(); i++){
            res.getCouleurs()[i]="blanc";
            res.getPi()[i]=-1;
            res.getDist()[i]=0;
        }
        //Parcours en largeur sur chaque sommet du Graphe G
        for (int j = 0; j <this.getN() ; j++) {
            this.pl(j,res);
        }
        return res;
    }

    /**
     * Parcours en largeur sur un sommet v
     * @param v un sommet de G
     * @param res Resultat du parcours en largeur
     */
    public void pl(int v, Resultat res){
        Stack<Integer> f= new Stack<Integer>(); //La file vide
        if(res.getCouleurs()[v].equals("blanc")){
            res.getCouleurs()[v]="gris";
            res.getPi()[v]=-1;
            res.getDist()[v]=0;
        }
        f.add(v);
        int x=-1;
        while(!f.empty()){
            x=f.pop();
            for (int i:this.a.get(x).getAdj()
                 ) {
                if(res.getCouleurs()[i].equals("blanc")){
                    res.getCouleurs()[i]="gris";
                    res.getDist()[i]=res.getDist()[x]+1;
                    res.getPi()[i]=x;
                    f.push(i);
                }
            }
        }
        res.getCouleurs()[x]="noir";
    }

    public void pp(int v, Resultat res){
        res.getCouleurs()[v]="gris";
        res.setTemps(res.getTemps()+1);
        res.getD()[v]=res.getTemps();
        for (int i = 0; i <this.getN() ; i++) {
            if(this.sontVoisins(v,i)){
                if(res.getCouleurs()[i].equals("blanc")){
                    res.getPi()[i]=v;
                    this.pp(i,res);
                }
            }
        }
        res.getCouleurs()[v]="noir";
        res.setTemps(res.getTemps()+1);
        res.getF()[v]=res.getTemps();
    }

    public Resultat parcoursProfondeur(){
        Resultat res = new Resultat((new String [this.getN()]),(new int [this.getN()]),(new int [this.getN()]),(new int [this.getN()]),0);
        String s="";
        for(int i=0; i<this.getN(); i++){
            res.getCouleurs()[i]="blanc";
            res.getPi()[i]=-1;
            res.getD()[i]=-1;
            res.getF()[i]=-1;
        }

        for (int i = 0; i <this.getN() ; i++) {
            if(res.getCouleurs()[i].equals("blanc")){
                this.pp(i, res);
            }
        }
        return res;
    }

    public String printArc(int i, int j){
        return  "(q"+i+"->q"+j+")";
    }

    public String toString() {
        String s="A= {";
        for(int i = 0; i<this.n; i++){
            for (Integer num : this.a.get(i).getAdj()
                    ) {
                s+= this.printArc(i, num)+" ";
            }
        }
        s+="}";
        return s;
    }

    public String par(Resultat res, ArrayList<String> chemins, int w){
        while(w!=0){
            for (int j:this.a.get(w).getAdj()
                 ) {
                if(res.getDist()[j]==res.getDist()[w]-1){
                    chemins.add("->"+j+""+par(res, chemins, j));
                }
            }
        }
        return "";
    }
    public void plusCourtschemins(){
        Resultat res = this.parcoursLargeur();
        ArrayList<String> chemins = new ArrayList<String>();
        System.out.println(par(res,chemins, this.n-1));
    }

}
