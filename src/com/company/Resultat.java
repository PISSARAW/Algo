package com.company;

import java.util.Arrays;

public class Resultat {
    String[] couleurs;
    int [] pi;
    int [] dist;
    int [] d;
    int [] f;
    int temps;

    public Resultat(String[] couleurs, int[] pi, int[] d, int[] f, int t) {
        this.couleurs = couleurs;
        this.pi = pi;
        this.d = d;
        this.f = f;
        this.temps=t;
    }

    public Resultat(String[] couleurs, int[] pi, int[] dist) {
        this.couleurs = couleurs;
        this.pi = pi;
        this.dist = dist;
    }

    public String[] getCouleurs() {
        return couleurs;
    }

    public int[] getPi() {
        return pi;
    }

    public int[] getDist() {
        return dist;
    }

    public int[] getD() {
        return d;
    }

    public int[] getF() {
        return f;
    }

    public int getTemps() {

        return temps;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "\ncouleurs=" + Arrays.toString(couleurs) +
                "\npi=" + Arrays.toString(pi) +
                "\ndist=" + Arrays.toString(dist) +
                "\nd=" + Arrays.toString(d) +
                "\nf=" + Arrays.toString(f) +
                "\ntemps=" + temps +
                '}';
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}
