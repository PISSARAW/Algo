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
     * @return présence du sommet
     */
    public boolean verifieSommet(int v){
        return v < this.getN() && v >= 0;
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

    public static int nbConnexesGraphes(int n) {
        if (n <= 0)
            return 0;
        return (int) Math.pow(2, new Double((n * (n - 1)) / 2));
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

    public String plusCourtsChemin(int w) {
        Resultat res = this.parcoursLargeur();
        String s = "" + w + "->";
        int i, j = w;
        while (j != -1) {
            i = res.getPi()[j];
            j = i;
            s += i + "->";
        }
        return s;
    }

    /***
     * Matrice d'adjacence du graphe
     * @return matrice
     */
    public int[][] listToMatrice() {
        int[][] matrice = new int[this.n][this.n];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.sontVoisins(i, j)) {
                    matrice[i][j] = 1;
                } else
                    matrice[i][j] = 0;
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
        return matrice;
    }

    /**
     * Racine d'un sommet S du Graphe
     *
     * @param pi Table des prédécéseurs
     * @param x  Sommet x
     * @return Racine de x
     */
    public int racine(int[] pi, int x) {
        int i = 0;
        while (i != -1) {
            i = pi[x];
            if (i != -1)
                x = i;
        }
        return x;
    }

    /**
     * Vérifie si deux sommets ont la même  source
     *
     * @param x
     * @param y
     * @return Valeur vérité
     */
    public boolean memeSource(int x, int y) {
        Resultat res = this.parcoursProfondeur();
        return this.racine(res.getPi(), x) == this.racine(res.getPi(), y);
    }

    /**
     * Vérifie si deux sommets ont la même couleur
     *
     * @param s    Sommet s
     * @param c    Couleur de S
     * @param bool Valeur vérité de S par rapport à ses voisins
     * @param coul
     * @return
     */
    private boolean testCouleur(int s, String c, Boolean bool, String[] coul) {
        coul[s] = c;
        for (Integer u : this.a.get(s).getAdj()
                ) {
            if (coul[u].equals("blanc")) {
                if (coul[s].equals("rouge"))
                    this.testCouleur(u, "vert", bool, coul);
                else
                    this.testCouleur(u, "rouge", bool, coul);
            } else if (coul[u].equals(coul[s]))
                return false;
        }
        return true;
    }

    /**
     * Nombre d'arêtes d'un Graphe
     *
     * @return
     */
    public int nbAretes() {
        int j = 0;
        for (int i = 0; i < this.n; i++) {
            j += this.degre(i);
        }
        return j / 2;
    }

    /**
     * Détermine si un graphe est biparti
     *
     * @return Valeur vérité
     */
    public boolean biparti() {
        Resultat res = new Resultat(new String[this.n]);
        for (int i = 0; i < this.n; i++) {
            res.getCouleurs()[i] = "blanc";
        }
        Boolean bool = true;
        for (int i = 0; i < this.n; i++) {
            if (res.getCouleurs()[i].equals("blanc")) {
                bool = this.testCouleur(i, "vert", bool, res.getCouleurs());
            }
        }
        return bool;
    }


    /////////////  Graphes valués  /////////////

    /**
     * Détermine si le Graphe G est connexe
     *
     * @return Valeur vérité
     */
    public boolean connexe() {
        Resultat res = this.parcoursProfondeur();
        int j = 0;
        for (int i = 0; i < res.getPi().length; i++) {
            if (res.getPi()[i] == -1)
                j += 1;
            if (j == 2)
                return false;
        }
        return true;
    }


    ////////////////////////////////////////////


    ///////////// Formule de récurrence ///////
    //Nombre de graphes connexes à n sommets

    public Resultat bellmanFord(int s) {
        Resultat res = new Resultat(new int[this.n], new int[this.n], true);
        for (int i = 0; i < this.n; i++) {  //Pour chaque i de S faire
            res.getPi()[i] = -1; // pi de i = nil
            if (i == s) // d de i
                res.getDist()[i] = 0;
            else // Sinon
                res.getDist()[i] = -1;
        }

        for (int i = 0; i < this.n; i++) { //Pour i = 0 à |S| - 1 faire
            for (Integer v : this.a.get(i).getAdj()
                    ) {
                if (res.getDist()[v] > res.getDist()[s]) {

                }
            }
        }
        return res;
    }

}
