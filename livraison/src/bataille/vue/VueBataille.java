package bataille.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import bataille.ecouteur.*;
import bataille.modele.*;

import javax.swing.JPanel;

public class VueBataille extends JPanel implements EcouteurModele {

    public GrilleMer jeu;
    public GrilleMer jeu2;
    private BatailleGUI control;

    /**
    * Constructeur de la class
    * @param a, qui est la grille d'un des joueurs.
    * @param b, qui est la grille du second joueur.
    */
    public VueBataille(GrilleMer a, GrilleMer b) {
        super();
        this.jeu = a;
        this.jeu2 = b;
        this.control = new BatailleGUI(b);
        this.jeu.ajoutEcouteur(this);
        this.jeu2.ajoutEcouteur(this);
    }

    /**
    * Méthode permettant de modifier
    * @param a qui est un booléan et qui permet de savoir qui est le joueur
    * @param position qui est la position de la case
    */
    public void modification(boolean a,int position){
		GrilleMer cases;
		if(a==true){
			cases = this.jeu;
		}else{
			cases = this.jeu2;
		}
		if (cases.situation(position) == "!") {
			control.changementcouleur(position,a,0, 0, 255);
		}
		if(cases.situation(position) == "x") {
			control.changementcouleur(position,a,255, 0, 0);
		}
		if(a==true){
			apprentissage(position);
		}
	}

    /**
    * Méthode permettant d'enregistrer les coordonnées d'un bateau couler pour l'afficher
    * 
    * @param pos		la position que nous venons de jouer
    */
	public void apprentissage(int pos){
		Bateau bat = this.jeu.getGrille()[pos/10][pos%10].presentBateau();
		if(bat!=null){
			if(bat.fin() == false){
				int x = 0;
				int y = 0;
				int ecart = 0;
				for(int i=0;i<this.jeu.listBateau.size();i++){
					if(this.jeu.listBateau.get(i).position == false){
						ecart = 1;
					}else{
						ecart = 10;
					}
					int placea = this.jeu.listBateau.get(i).debut;
					int placeb = this.jeu.listBateau.get(i).fin;
					
					for(int j=placea;j<=placeb;j+=ecart){
						if (pos == j){
							x = this.jeu.listBateau.get(i).debut;
							y = this.jeu.listBateau.get(i).fin;
						}
					}
				}
				this.control.pan.ajout(x,y);
			}
		}
		
	}

    /**
    * Permet de savoir si la partie est terminée.
    * 
    * @return si la partie est finie
    */
    public boolean isTerminal() {
        return this.jeu.isTerminal() || this.jeu2.isTerminal();
    }

    /**
    * Méthode permettant de remettre à jour le JPanel.
    * @param src prend un objet source.
    */
    @Override
    public void modeleMisAJour(Object src) {
        this.repaint();
    }

    /**
    * Méthode qui permet de peindre un composant de la fenêtre.
    */
    @Override
    public void paintComponent(Graphics a) {
        super.paintComponent(a);
    }

    /**
    * Méthode permettant de connaitre le numéro de la case appuyée.
    * @return int renvoie le numéro de la case.
    */
    public int getappuie(){
		return control.appuie;
	}

    /**
    * Méthode qui permet de remettre à -1 une case.
    */
    public void setappuie(){
		this.control.setappuie();
	}
	
	/**
    * Méthode qui permet la destruction de la fenêtre après 5 secondes quand la partie est finie
    */
	public void destruction(){
		try {
				Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		//destruction de la fenêtre
		this.control.dispose();
	}

}
