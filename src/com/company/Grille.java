package com.company;

public interface Grille {
    /**
     * Construire unr grille
     * @param h hauteur
     * @param l largeur
     */
    public void construireGrille(int h, int l);

    /**
     * Ajouter un mur entre i et j
     * @param i case i
     * @param j case j
     */
    public void ajouterMur(int i, int j);
}

