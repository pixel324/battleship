package bataille.vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;
import java.util.Collections;
import java.util.Random;
import bataille.ecouteur.*;
import bataille.modele.*;

public class Orchestrator2{
	private Hashtable<String, Integer> bato;
    private VueCmd interfa;

	/**
	* Construction de l'orchestrator pour l'affichage cmd
	* 
	* @param batoent		le dictionnaire java qui comprend les bateaux à placer
	* @param a 			la grille de l'humain
	* @param b				la grille du random
	* @param vision		la vue pour l'affichage du cmd
	* 
	*/
     public Orchestrator2(Hashtable<String, Integer> batoent,GrilleMer a, GrilleMer b,VueCmd vision){
        this.bato = batoent;
		this.interfa = vision;
		Set keys = batoent.keySet();
		Iterator itr = keys.iterator();
		String key = "";
		while (itr.hasNext()) {
			key = (String) itr.next();
			a.placementaleatoire(batoent.get(key));
			b.placementaleatoire(batoent.get(key));
		}
		this.interfa.jeu.coupValid();
		this.interfa.jeu2.coupValid();
		orchestration();
	}

	/**
	* Méthode qui lance la boucle du jeu 
	*/
    public void orchestration(){
         while(interfa.isTerminal()==false){
            int press = interfa.appuie();
            if(interfa.jeu.situation(press)=="-"){
                interfa.jeu.play(press/10,press%10);
                int press2 = interfa.jeu2.joueraleatoire();
                interfa.modification(true,press);
			    interfa.modification(false,press2);
			    interfa.build();
            }
		}
        System.out.println("partie finie");
    }
}
        
