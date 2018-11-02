package com.company;

public class GraphePlanaire extends Graphe {
    /**
     * Constructeur de Graphe
     *
     * @param n Nombre de sommets
     */
    public GraphePlanaire(int n) {
        super(n);
    }

    public int faces() {
        return this.n - this.nbAretes() - 2;
    }

    public boolean estPlanaireConnexe() {
        return (this.nbAretes() <= 3 * (this.n) - 6) || (this.nbAretes() <= (2 * (this.n) - 4));
    }


}
