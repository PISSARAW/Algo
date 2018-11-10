package com.company;

import java.util.LinkedList;

public class Arete {
    int n;
    int p;

    public Arete(int j, int poids){
        this.n=j;
        this.p=poids;
    }
    public Arete (int j){
        this.n=j;
        this.p=0;
    }

    public int getN(){
        return this.n;
    }

    public int getP(){
        return this.p;
    }
}
