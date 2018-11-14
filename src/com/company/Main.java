package com.company;

public class Main {

    public static void main(String[] args) {



	// Question 5 
        //Plan de Gomath City
        Graphe gomath = Graphe.construireGrille(4, 7);
	/// Mur à droite
        gomath.ajouteMurD(3);
        gomath.ajouteMurD(7);
        gomath.ajouteMurD(9);
        gomath.ajouteMurD(10);
        gomath.ajouteMurD(19);
	/// Mur en bas
        gomath.ajouteMurH(1);
        gomath.ajouteMurH(4);
        gomath.ajouteMurH(7);
        gomath.ajouteMurH(12);
	
        
        System.out.println(gomath);
        System.out.println("Parcours en largeur");
        System.out.println(gomath.parcoursLargeur());
	// Un plus court chemin
        System.out.println(gomath.plusCourtsChemin(27, gomath.parcoursLargeur(0)));
	
	    
	    
	 ArrayList<List<Integer>> chemins= new ArrayList<>();

        ////////////// Question 6 //////////////////

        // Méthode naîve de déduction d'un plus cours chemin
        // Soit un graphe G = (V,E)
        Graphe math =  Graphe.construireGrille(4,4);

        // Trouver tous les chemins entre s et d
        // Complexité O (|S|.|A|)
        chemins = math.tousLesChemins(0, 15);

        // Ajouter les murs
        // Complexité O(|A|)

        math.ajouteMurH(3);
        math.ajouteMurD(5);

        chemins = math.tousLesChemins(0,15);
        int i=0;
        while(i<chemins.size()){
            if(chemins.get(i).size()==i){
                System.out.println(chemins.get(i));
                break;
            }
            i++;
        }

        //////////////  Question 6  //////////////

    }
}
