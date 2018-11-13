package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Graphe {
    protected final int n;
    protected ArrayList<LinkedList<Arete>> a;
    protected static final int INFINITE= Integer.MAX_VALUE/2;


    public Graphe(int n) {
        this.n = n;
        this.a =  new ArrayList<LinkedList<Arete>>();
        for(int i =0 ; i<n; i++){
            a.add(new LinkedList<Arete>());
        }
    }

    public void ajourteArete(int i, int j){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).add(new Arete(j));
                this.a.get(j).add(new Arete(i));
            }
        }
    }

    public void ajourteArete(int i, int j, int p){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).add(new Arete(j,p));
                this.a.get(j).add(new Arete(i,p));
            }
        }
    }



    public boolean verifieSommet(int v){
        if(v>=this.getN()||v<0)
            return false;
        return true;
    }

    public int getN(){
        return this.n;
    }

    public int degre(int v){
        return this.a.get(v).size();
    }

    public static int nbConnexesGraphes(int n) {
        if (n <= 0)
            return 0;
        return (int) Math.pow(2, ((n * (n - 1)) / 2));
    }


    public List<Arete> voisins (int v){
        if(verifieSommet(v))
            return Collections.unmodifiableList(this.a.get(v));
        return null;
    }

    public boolean sontVoisins(int i, int j){
        return (this.a.get(i).contains(new Arete(j))||this.a.get(j).contains(new Arete(i)));
    }


    public Resultat parcoursLargeur(int v){
        Resultat res = new Resultat((new String [this.n]),(new int [this.n]),(new int [this.n]));
        String s="";
        //Initialisation des tableaux Couleurs, Pi et Dist
        for(int i=0; i<this.n; i++){
            if(i==v){
                res.getCouleurs()[v]="gris";
                res.getPi()[v]=-1;
                res.getDist()[v]=0;
            }
            else{
                res.getCouleurs()[i]="blanc";
                res.getPi()[i]=-1;
                res.getDist()[i]=0;
            }

        }
        //Parcours en largeur sur chaque sommet du Graphe G
        for (int j = 0; j <this.n ; j++) {
            this.pl(j,res);
        }
        return res;
    }


    public void pl(int v, Resultat res){
        Stack<Integer> f= new Stack<Integer>(); //La file vide
        f.add(v);
        int x=-1;
        int i=0;
        while(!f.empty()){
            x=f.pop();
            for (Arete a:this.a.get(x)
            ) {
                i=a.getN();
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
        for (Arete av:this.a.get(v)
            ) {
            if(res.getCouleurs()[av.getN()].equals("blanc")){
                res.getPi()[av.getN()]=v;
                this.pp(av.getN(),res);
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
    public String printArc(int i, int j, int p){
        return  "(q"+i+"->q"+j+","+p+")";
    }

    public String toString() {
        String s="A= {";
        for(int i = 0; i<this.n; i++){
            for (Arete num : this.a.get(i)
            ) {
                s+= this.printArc(i, num.getN(), num.getP())+" ";
            }
        }
        s+="}";
        return s;
    }



    public Resultat bellmanFord(int s){
        Resultat res = new Resultat(new int[this.n], new int[this.n], true);
        for(int i=0; i<this.n; i++){
            res.getPi()[i]=-1;
            if(i==s)
                res.getDist()[i]=0;
            else
                res.getDist()[i]=INFINITE;
        }

        for(int u=0; u<this.n; u++){
            for (Arete av : this.a.get(u)){
                if(res.getDist()[av.getN()]>res.getDist()[u]+ av.getP()){
                    res.getDist()[av.getN()]=res.getDist()[u]+av.getP();
                    res.getPi()[av.getN()]=u;
                }
            }
        }
        for (int u = 0; u <this.n ; u++) {
            for (Arete av : this.a.get(u)){
                if(res.getDist()[av.getN()]>res.getDist()[u]+ av.getP()){
                    res.bool = false;
                    return res;
                }
            }
        }
        res.bool=true;
        return res;
    }


    public String plusCourtsChemin(int w, Resultat res) {
        String s = "" + w + "->";
        int i, j = 0;
        while (w != -1) {
            i = res.getPi()[w];
            w = i;
            s += i + "->";
            j++;
        }
        System.out.println("Taille du plus court chemin = "+ (j-1));
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
        for (Arete u : this.a.get(s)
        ) {
            if (coul[u.getN()].equals("blanc")) {
                if (coul[s].equals("rouge"))
                    this.testCouleur(u.getN(), "vert", bool, coul);
                else
                    this.testCouleur(u.getN(), "rouge", bool, coul);
            } else if (coul[u.getN()].equals(coul[s]))
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

    public ArrayList<List<Integer>> tousLesChemins(int s, int d){
        boolean[] estVisite = new boolean[this.n];
        ArrayList<Integer> chemins = new ArrayList<>();
        ArrayList<List<Integer>> c=new ArrayList<>();
        chemins.add(s);
        this.trouveChemins(s,d,estVisite, chemins, c);
        return c;
    }

    public void trouveChemins(Integer u, Integer d, boolean[] sommetsVu, List<Integer> chemin, ArrayList<List<Integer>> c){
        sommetsVu[u]=true;
        if(u.equals(d)){
            boolean b= false;
            for (List<Integer> ch:c
                 ) {
                if(ch.equals(chemin))
                    b=true;
            }
            if(b==false){
                c.add(new ArrayList<>(chemin));
                System.out.println(chemin + " T : "+ chemin.size());
            }
        }
        for (Arete av: this.a.get(u)
             ) {
                if(!sommetsVu[av.getN()]){
                    chemin.add(av.getN());
                    trouveChemins(av.getN(), d, sommetsVu, chemin, c);
                    chemin.remove(Integer.valueOf(av.getN()));
                }
        }
        sommetsVu[u]=false;
    }


    /////////////////////////////// Grille ////////////////////////////////////////////

    /**
     * Construire unr grille
     * @param h hauteur
     * @param l largeur
     */
    public static Graphe construireGrille(int h, int l){
        Graphe g = new Graphe(h*l);
        int x,y;
        for (int i = 0; i <h*l ; i++) {
            for (int j = 0; j <h*l ; j++) {
                if(i!=j&&(j==i+1||j==i+l)){
                    x=i;
                    y=j;
                    g.ajourteArete(i,j);
                }
            }
        }
        //Supprimer les arêtes genantes
        for (int i = l-1; i <h*l ; i+=l) {
            if(i!=h*l-1)
                g.ajouterMur(i, i+1);
        }
        return g;
    }
    /**
     * Ajouter un mur entre i et j
     *
     * @param i case i
     * @param j case j
     */
    public void ajouterMur(int i, int j) {
        System.out.println(this);
        ListIterator<Arete> iterator = this.a.get(i).listIterator();
        ListIterator<Arete> iterator1 = this.a.get(j).listIterator();
        while(iterator.hasNext()){
            if(iterator.next().getN()==j)
                iterator.remove();
        }
        while(iterator1.hasNext()){
            if(iterator1.next().getN()==i)
                iterator1.remove();
        }
        System.out.println(this);
    }

    public void afficherGrille(int i, int j){
        int e=0;
        for (int k = 0; k <i ; k++) {
            for (int l = 0; l <j ; l++) {
                System.out.print(e+" ");
                e++;
            }
            System.out.println();
        }
    }

    ///////////////////////////// Grille ////////////////////////////////////////////////



    ///////////////////////////// Arbre //////////////////////////////////////////////////

    public static Graphe construireArbre(int[] arbre){
        return null;
    }

    public boolean estbinaire(){
        for (LinkedList<Arete> la:this.a
             ) {
            if(la.size()>2)
                return false;
        }
        return true;
    }





    ///////////////////////////// Arbre ///////////////////////////////////////////////////


}
