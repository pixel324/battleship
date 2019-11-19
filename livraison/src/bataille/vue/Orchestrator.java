package bataille.vue;

import java.util.*;
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

public class Orchestrator {

	private VueBataille interfa;
	private Hashtable<String, Integer> bato;

	/**
	 * Constructeur de la classe Orchestrator
	 * 
	 * @param batoent le dictionnaire des bateaux
	 * @param a la grille de l'humain
	 * @param b la grille du random
	 * @param vision la vue terminal
	 */
	public Orchestrator(Hashtable<String, Integer> batoent,GrilleMer a, GrilleMer b,VueBataille vision) {
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
	 * Méthode de lancement du jeu
	 */
	public void orchestration() {
		while (interfa.isTerminal() == false || interfa.getappuie()!=-1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			if (interfa.getappuie()!=-1) {
				int a = interfa.getappuie();
				if(interfa.jeu.situation(a)=="-"){
					interfa.jeu.play(a/10,a%10);
					int b = interfa.jeu2.joueraleatoire();
					interfa.modification(true,a);
					interfa.modification(false,b);
					interfa.setappuie();
				}
			}
		}
		interfa.destruction();
		System.out.println("partie finie");
	}
}
