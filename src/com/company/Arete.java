package com.company;

import java.util.LinkedList;

public class Arete {
    public LinkedList<Integer> getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer> adj) {
        this.adj = adj;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    private LinkedList<Integer> adj;
    private int poids;

    public Arete(LinkedList<Integer> adj, int poids) {
        this.adj = adj;
        this.poids = poids;
    }

    public Arete(LinkedList<Integer> adj) {
        this.adj = adj;
    }
}
