package bataille;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;

import bataille.modele.*;
import bataille.vue.*;

public class Demo {

	public static void main(String[] args) {
		if(args.length <= 1 ||args.length >= 3){
			System.out.println("Erreur lors du chargement des parametres, veuillez proceder comme ceci: java -cp build bataille.Demo <commande ou graphique> <France ou Belgique> ");
		}
		Hashtable<String, Integer> batoent = new Hashtable<String, Integer>();
		if (args[1].equals("France")) {
			batoent.put("porte-avions", 5);
			batoent.put("croiseur", 4);
			batoent.put("contre-torpilleur", 3);
			batoent.put("sous-marin", 3);
			batoent.put("torpilleur", 2);
		} else {
			batoent.put("cuirasse", 4);
			batoent.put("croiseur", 3);
			batoent.put("croiseur2", 3);
			batoent.put("torpilleur", 2);
			batoent.put("torpilleur2", 2);
			batoent.put("torpilleur3", 2);
			batoent.put("sous-marin", 1);
			batoent.put("sous-marin", 1);
			batoent.put("sous-marin", 1);
			batoent.put("sous-marin", 1);
		}
		if (args[0].equals("commande")) {
			GrilleMer model1 = new GrilleMer(10, 10);
			GrilleMer model2 = new GrilleMer(10, 10);
			VueCmd vue = new VueCmd(model1, model2);
			Orchestrator2 jeu = new Orchestrator2(batoent, model1, model2, vue);
		} else if(args[0].equals("graphique")){
			GrilleMer model1 = new GrilleMer(10, 10);
			GrilleMer model2 = new GrilleMer(10, 10);
			VueBataille vue = new VueBataille(model1, model2);
			Orchestrator jeu = new Orchestrator(batoent, model1, model2, vue);
		}
	}
}
