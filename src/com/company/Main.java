package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Graphe g = new Graphe(7);
        g.ajourteArete(0,1);
        g.ajourteArete(0,3);
        g.ajourteArete(1,0);
        g.ajourteArete(1,3);
        g.ajourteArete(1,4);
        g.ajourteArete(2,4);
        g.ajourteArete(2,5);
        g.ajourteArete(3,1);
        g.ajourteArete(3,3);
        g.ajourteArete(3,4);
        g.ajourteArete(3,6);
        g.ajourteArete(5,4);
        g.ajourteArete(5,5);
        g.ajourteArete(6,4);
        //System.out.println(g);
        //System.out.println(((GrapheOriente) g).parcoursProfondeur());
        Graphe g1 = new GrapheOriente(12);
        g1.ajourteArete(0,4);
        g1.ajourteArete(0,5);
        g1.ajourteArete(1,1);
        g1.ajourteArete(1,4);
        g1.ajourteArete(1,6);
        g1.ajourteArete(1,7);
        g1.ajourteArete(2,3);
        g1.ajourteArete(3,2);
        g1.ajourteArete(4,8);
        g1.ajourteArete(4,9);
        g1.ajourteArete(5,10);
        g1.ajourteArete(7,3);
        g1.ajourteArete(8,9);
        g1.ajourteArete(9,10);
        g1.ajourteArete(10,5);
        g1.ajourteArete(10,8);
        g1.ajourteArete(11,6);
        g1.ajourteArete(11,7);
        g1.ajourteArete(11,10);
        //System.out.println(g1);

        //System.out.println(g1.parcoursProfondeur());

        Graphe g2 = new Graphe(4);
        g2.ajourteArete(0,2);
        g2.ajourteArete(2,1);
        g2.ajourteArete(3,0);
        g2.ajourteArete(1,3);
        //System.out.println(g);

        //System.out.println(g2.parcoursProfondeur());

        //System.out.println(g.parcoursLargeur());


        //Graphe g3
        Graphe g3 = new Graphe(7);
        g3.ajourteArete(0,1);
        g3.ajourteArete(0,3);
        g3.ajourteArete(1,3);
        g3.ajourteArete(1,4);
        g3.ajourteArete(2,4);
        g3.ajourteArete(2,5);
        g3.ajourteArete(3,4);
        g3.ajourteArete(3,6);
        g3.ajourteArete(3,6);
        g3.ajourteArete(4,5);
        //System.out.println(g3);
        //System.out.println(g3.parcoursLargeur());


        //Plan de Gomath City
        Graphe gomath = new Graphe(4*7);
        gomath.ajourteArete(0,1);
        gomath.ajourteArete(0,7);
        gomath.ajourteArete(1,2);
        gomath.ajourteArete(2, 3);
        gomath.ajourteArete(2,9);
        gomath.ajourteArete(3, 10);
        gomath.ajourteArete(4, 5);
        gomath.ajourteArete(5,6);
        gomath.ajourteArete(5, 12);
        gomath.ajourteArete(6, 13);
        gomath.ajourteArete(8,9);
        gomath.ajourteArete(8,15);
        gomath.ajourteArete(9,16);
        gomath.ajourteArete(10, 17);
        gomath.ajourteArete(11,12);
        gomath.ajourteArete(11,18);
        gomath.ajourteArete(12,13);
        gomath.ajourteArete(13,20);
        gomath.ajourteArete(14, 15);
        gomath.ajourteArete(14, 21);
        gomath.ajourteArete(15,16);
        gomath.ajourteArete(15,22);
        gomath.ajourteArete(16,17);
        gomath.ajourteArete(16, 23);
        gomath.ajourteArete(17,18);
        gomath.ajourteArete(17,24);
        gomath.ajourteArete(18,19);
        gomath.ajourteArete(18,25);
        gomath.ajourteArete(19, 26);
        gomath.ajourteArete(20,27);
        gomath.ajourteArete(21,22);
        gomath.ajourteArete(22,23);
        gomath.ajourteArete(23,24);
        gomath.ajourteArete(24,25);
        gomath.ajourteArete(25,26);
        gomath.ajourteArete(26,27);

        System.out.println(gomath);
        System.out.println("Parcours en largeur");
        System.out.println(gomath.parcoursLargeur());
        System.out.println(gomath.plusCourtsChemin(27));


        Graphe test = new GrapheOriente(8);
        test.ajourteArete(0, 1);
        test.ajourteArete(1, 2);
        test.ajourteArete(2, 5);
        test.ajourteArete(3, 4);
        test.ajourteArete(4, 6);
        test.ajourteArete(6, 7);
        System.out.println("\n\n Même source \n\n");
        System.out.println(test.memeSource(5, 7));

        Graphe circuit = new GrapheOriente(6);
        circuit.ajourteArete(0, 1);
        circuit.ajourteArete(0, 3);
        circuit.ajourteArete(0, 4);
        circuit.ajourteArete(1, 2);
        circuit.ajourteArete(2, 5);
        circuit.ajourteArete(3, 4);
        circuit.ajourteArete(4, 5);
        circuit.ajourteArete(5, 1);
        System.out.println("\n\nCircuit\n\n");
        System.out.println(circuit.parcoursProfondeur());
        System.out.println(circuit);
        System.out.println(((GrapheOriente) circuit).transpose());
        System.out.println(((GrapheOriente) circuit).transpose().parcoursProfondeur());
        System.out.println(((GrapheOriente) circuit).transpose().biparti());
        System.out.println(circuit.biparti());

        GrapheOriente biparti = new GrapheOriente(4);
        biparti.ajourteArete(0, 1);
        biparti.ajourteArete(1, 2);
        biparti.ajourteArete(2, 3);
        biparti.ajourteArete(3, 0);
        System.out.println("\n\nBiparti");
        System.out.println(biparti.biparti());
        System.out.println("\n\nNb Aretes");
        System.out.println(gomath.nbAretes());
        System.out.println(new Grille(4, 4));
        (new Grille(4, 4)).creerGrilleParfaite();
        System.out.println(Graphe.nbConnexesGraphes(4));
        circuit.ajourteArete(2, 0, 12);
    }
}
