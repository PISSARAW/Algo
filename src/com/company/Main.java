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
        // Complexité O (|S|+|A|)
        chemins = math.tousLesChemins(0, 15);

        // Ajouter les murs
        // Complexité O(m+n)
        // On parcours tous les chemins d'une liste puis tous les sommets d'un chemin

        for (int i = 0; i <chemins.size() ; i++) {
            for (int j = 0; j <chemins.get(i).size() ; j++) {
                if(((j+1)<(chemins.get(i).size()))&&chemins.get(i).get(j+1)==6&&chemins.get(i).get(j)==5)
                    chemins.remove(i);
                if(((j+1)<(chemins.get(i).size()))&&chemins.get(i).get(j)==3&&chemins.get(i).get(j+1)==3+math.l)
                    chemins.remove(i);
                }
        }
        System.out.println(chemins);
        //math.ajouteMurH(3);
        //math.ajouteMurD(5);



        // Complexité 0(|m|)
        // On parcours tous les chemins d'une liste
        //chemins = math.tousLesChemins(0,15);
        int min = chemins.get(0).size();
        for (List<Integer> i : chemins){
            min = min < i.size() ? min : i.size();
        }
        System.out.println(chemins.get(min));

        //////////////  Question 6  //////////////


    }
}
