package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GrapheOriente extends Graphe {

    public GrapheOriente(int n) {
        super(n);
        this.a =  new ArrayList<Arete>();
        for(int i =0 ; i<n; i++){
            a.add(new Arete(new LinkedList<Integer>(),0));
        }
    }

    public void ajourteArete(int i, int j){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).getAdj().add(j);
            }
        }
    }

    public void ajourteArete(int i, int j, int poids){
        if(verifieSommet(i)){
            if(verifieSommet(j)){
                this.a.get(i).getAdj().add(j);
                this.a.get(i).setPoids(poids);

            }
        }
    }

    public int degre(int v){
        int degre=this.a.get(v).getAdj().size();
        return degre;
    }

    public boolean pere(int i, int j){
        return this.a.get(i).getAdj().contains(j);
    }

    public int nbAretes() {
        int j = 0;
        for (int i = 0; i < this.n; i++) {
            j += this.degre(i);
        }
        return j;
    }


    public String parcoursLargeur(int v){
        int []  pis = new int [this.getN()];
        int [] dist = new int [this.getN()];
        String [] couleur = new String [this.getN()];
        String s="";
        Stack<Integer> file = new Stack<Integer>();
        for(int i=0; i<this.getN(); i++){
            if(i==v)
                i++;
            couleur[i]="blanc";
            pis[i]=-1;
            dist[i]=-1;
        }
        couleur[v]="gris";
        pis[v]=-1;
        dist[v]=0;
        file.push(v);
        System.out.println(file);
        int x=v;
        while(!file.empty()){
            x=file.pop();
            for (int i = 0; i <this.getN() ; i++) {
                if(this.pere(x,i)){
                    if(couleur[i].equals("blanc")){
                        couleur[i]="gris";
                        dist[i]=dist[x]+1;
                        pis[i]=x;
                        file.push(i);
                        System.out.println(file);
                    }
                }
            }
            couleur[x]="noir";
        }
        for (int i = 0; i <pis.length ; i++) {
            s+= " "+pis[i] + " ";
        }
        return s+file.toString();
    }

    public void pp(int v, Resultat res){
        res.getCouleurs()[v]="gris";
        res.setTemps(res.getTemps()+1);
        res.getD()[v]=res.getTemps();
        for (int i = 0; i <this.getN() ; i++) {
            if(this.pere(v,i)){
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

    /**
     * Transposé d'un Graphe G
     *
     * @return transposé G'
     */
    public GrapheOriente transpose() {
        GrapheOriente graphe = new GrapheOriente(this.n);
        int i = 0;
        for (Arete a : this.a
                ) {
            for (Integer j : a.getAdj()
                    ) {
                graphe.ajourteArete(j, i);
            }
            i++;
        }
        return graphe;
    }



}
