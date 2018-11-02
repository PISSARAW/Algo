package com.company;

public class Grille {
    Graphe g;
    int x;
    int y;

    public Grille(int i, int j) {
        this.g = new Graphe(i * j);
        this.x = i;
        this.y = j;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("-------------------------\n");
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                if (j == 0) sb.append("| ");
                sb.append(" ");
                if ((j == 2) || (j == 5) || (j == 8)) sb.append("| ");
            }
            if ((i == 2) || (i == 5))
                sb.append("\n|-----------------------|");
            if (i == 8) sb.append("\n-------------------------");
            sb.append("\n");
        }
        return sb.toString();
    }

    public void creerGrilleParfaite() {
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.y; j++) {
                if ((j == i + 1 && j < this.y) || (j == i + j))
                    this.g.ajourteArete(i, j);
            }
        }
        System.out.println(this.g);
    }


}
