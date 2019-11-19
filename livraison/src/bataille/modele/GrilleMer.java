package bataille.modele;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import bataille.ecouteur.*;

public class GrilleMer extends AbstractModeleEcoutable{
	private Case[][] grille1;
	private int m;
	private int n;
	public List<Bateau> listBateau = new ArrayList<>();
	public List<Integer> placeBateau = new ArrayList<>();
	private List<couple> placepossible = new ArrayList<>();
	Random rn = new Random();

	/**
	 * Constructeur de la classe GrilleMer
	 * 
	 * @param m l'axes y de notre grille
	 * @param n l'axes x de notre grille
	 */
	public GrilleMer(int m, int n) {
		this.grille1 = new Case[m][n];
		this.m = m;
		this.n = n;
		init();
	}

	/**
	 * Méthode qui va initialiser une case a chaque emplacement de notre grille
	 */
	public void init() {
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				this.grille1[i][j] = new Case(i, j);
			}
		}
	}

	/**
	 * Méthode pour savoir si la partie est fini
	 * 
	 * @return si la partie est fini par un booleen
	 */
	public boolean isTerminal() {
		boolean fini = true;
		for (Bateau bateau : listBateau) {
			if (bateau.fin() != false) {
				fini = false;
			}
		}
		return fini;
	}
	
	/**
	 * Méthode pour retourner la situation de la grille
	 * @param a la valeur que nous voulons modifier en caractère
	 * @return le String de la situation de la grille
	 */
	public char convertisseur(int a){
		a = a +65;
		char b = (char)a;
		return b;
	}
	
	/**
	 * Méthode pour retourner la situation de la grille
	 * 
	 * @return le String de la situation de la grille
	 */
	public String afficheGrille() {
		String affiche = "";
		for (int i = 0; i < this.m; i++) {
			if (i==0){
				affiche +="  ";
				for (int k =0;k<10;k++){
					affiche += " "+convertisseur(k)+" ";
				}
				affiche += System.lineSeparator();
			}
			affiche += (i+1)+" ";
			for (int j = 0; j < this.n; j++) {
				affiche += " " + this.grille1[i][j].setAffiche() + " ";
			}
			affiche += System.lineSeparator();
		}
		affiche += System.lineSeparator();
		return (affiche);
	}

	/**
	 * Méthode pour obtenir une liste de coupValide
	 */
	public void coupValid() {
		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				if (this.grille1[i][j].setAffiche() == "-") {
					this.placepossible.add(new couple(i, j));
				}
			}
		}
	}

	/**
	 * Méthode pour savoir si un coup est valide
	 * 
	 * @param x      la valeur x choisi par l'utilisateur
	 * @param y      la valeur y choisi par l'utilisateur
	 * @return retourne si le coup est possible
	 */
	public boolean possible(int x, int y){
		if (x > this.m || y > this.n) {
			return false;
		}
		for (couple c : this.placepossible) {
			if (x == c.getx() && y == c.gety()) {
				this.placepossible.remove(c);
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode pour ajouter un nouveau bateau
	 * 
	 * @param taille    la taille du bateau
	 * @param x         l'axes x du premier point du bateau
	 * @param y         l'axes y du premier point du bateau
	 * @param direction le sens du bateau
	 */
	public void ajoutBateau(int taille, int x, int y, boolean direction) {
		Bateau nouveau;
		if(direction==true){
			nouveau = new Bateau(taille,x*10+y,(x+taille-1)*10+y,direction);
		}else{
			nouveau = new Bateau(taille,x*10+y,x*10+y+taille-1,direction);
		}
		listBateau.add(nouveau);
		placeBateau.add(x*10+y);
		for (int i = 0; i < taille; i++) {
			if (direction == true) {
				this.grille1[x + i][y].placerBateau(nouveau);
			} else {
				this.grille1[x][y + i].placerBateau(nouveau);
			}
		}
		if(direction==true){
			placeBateau.add((x+taille-1)*10+y);
		}else{
			placeBateau.add(x*10+y+taille-1);
		}
	}

	/**
	 * Méthode pour savoir si le bateau peut être posé
	 * 
	 * @param taille    la taille du bateau
	 * @param x         l'axes x du premier point du bateau
	 * @param y         l'axes y du premier point du bateau
	 * @param direction le sens du bateau
	 * @return retourne si le bateau peut être posé
	 */
	public boolean placementPossible(int taille, int x, int y, boolean direction) {
		if (x >= m || y >= n) {
			return false;
		}
		if (direction == true) {
			if (x + taille >= m || y >= n) {
				return false;
			}
		} else {
			if (y + taille >= n || x >= m) {
				return false;
			}
		}
		for (int i = 0; i < taille; i++) {
			if (direction == true) {
				if (this.grille1[x + i][y].presentBateau() != null) {
					return false;
				}
			} else {
				if (this.grille1[x][y + i].presentBateau() != null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Méthode pour jouer son coup en (x, y)
	 * 
	 * @param x         l'axes x
	 * @param y         l'axes y
	 */
	public void play(int x, int y) {
		this.grille1[x][y].toucher();
		this.fireChangement();
	}

	/**
	 * Méthode qui retourne la grille
	 * 
	 * @return retourne la grille de jeu
	 */
	public Case[][] getGrille() {
		return this.grille1;
	}

	/**
	 * Méthode de placement aléatoire de bateau
	 * 
	 * @param taille         taille du bateau à placer
	 */
	public void placementaleatoire(int taille) {
		int choix1 = 10;
		int choix2 = 10;
		boolean choix3 = true;
		while (placementPossible(taille, choix1, choix2, choix3) != true) {
			choix1 = (int) (Math.random() * 10);
			choix2 = (int) (Math.random() * 10);
			if ((int) (Math.random() * 2) == 1) {
				choix3 = true;
			} else {
				choix3 = false;
			}
		}
		ajoutBateau(taille, choix1, choix2, choix3);
	}
	
	/**
	 * Méthode de placement aléatoire de bateau
	 * 
	 * @param i         la case dont on veut connaitre la situation
	 * @return          retourne l'état de la case
	 */
	public String situation(int i){
		if(i<100 && i>-1){
			return(grille1[i/10][i%10].setAffiche());
		}
		return("-");
	}

	/**
	 * Méthode pour jouer un coup aléatoirement
	 * 
	 * @return          la case à jouer
	 */
	public int joueraleatoire() {
		int choix = rn.nextInt(this.placepossible.size());
		couple choisi = this.placepossible.get(choix);
		int a1 = choisi.getx();
		int b1 = choisi.gety();
		play(b1, a1);
		this.placepossible.remove(choisi);
		return (a1+b1*10);
	}

}
