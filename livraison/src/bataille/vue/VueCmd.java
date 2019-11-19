package bataille.vue;

import bataille.ecouteur.*;
import bataille.modele.*;
import java.util.Scanner;

public class VueCmd implements EcouteurModele {
    public GrilleMer jeu;
    public GrilleMer jeu2;
    Scanner scan = new Scanner(System.in);

	/**Méthode pour retourner le bateau présent sur la case
	 * 
	 * @param a			la grille du joueur humain
	 * @param b			la grille du joueur aléatoire
	 */
    public VueCmd(GrilleMer a, GrilleMer b){
        super();
        this.jeu = a;
        this.jeu2 = b;
        this.jeu.ajoutEcouteur(this);
        this.jeu2.ajoutEcouteur(this);
        this.build();
    }

	/**Méthode modifié la situation de la case
	 * 
	 * @param a			un booolean pour choisir la grille a modifié
	 * @param position	case a modifier
	 */
    public void modification(boolean a,int position){
        GrilleMer cases;
        if(a==true){
            cases = this.jeu;
        }else{
            cases = this.jeu2;
        }
        if(cases.situation(position) =="!"){
            changementcase(position,a);
        }
        if(cases.situation(position)== "X"){
            changementcase(position,a);
        }
    }

	/**Méthode de changement de la case
	 * 
	 * @param position		la position à changer
	 * @param a				savoir quel grille est modifié
	 */
    public void changementcase(int position,boolean a){
        GrilleMer cases;
        if(a == true){
            cases = this.jeu;
            cases.situation(position);
        }else{
            cases = this.jeu2;
            cases.situation(position);
        }
    }

	/**Méthode pour savoir si la partie est fini ou pas
	 * 
	 * @return			retourne si la partie est finie
	 */
    public boolean isTerminal(){
        return this.jeu.isTerminal() || this.jeu2.isTerminal();
    }

	/**Méthode pour afficher les grilles
	 * 
	 */
    public void build() {
        System.out.println(this.jeu.afficheGrille());
        System.out.println(this.jeu2.afficheGrille());

    }
    
    /**Méthode pour mettre à jour
     * @param src L'objet à mettre à jour
	 * 
	 */
    @Override
    public void modeleMisAJour(Object src) {
    }


	/**Méthode de conversion du caractère en entier
	 * 
	 * @param a			caractère initiale
	 * @return 			retourne le numéro indiqué par ce caractère
	 */
    public int convertisseur(char a){
        int k = a;
		return k-65;
    }
    
    /**Méthode pour le choix du joueur humain
	 * 
	 * @return 			la valeur de la case à jouer
	 */
    public int appuie(){
        int pushe=10;int pushe3 = 10;
        boolean act = false;
        while(act==false){
               try{
                        System.out.println("Choix du nombre");
                        String pushe4 = scan.next();
                        pushe = Integer.parseInt(pushe4);
                        System.out.println("Choix de la lettre");
                        char pushe2 = scan.next().charAt(0);
                        pushe3 = convertisseur(pushe2);
                }catch(Exception e){
                        System.out.println("erreur de saisie");
                }
                if(this.jeu.possible(pushe-1,pushe3)==true){
                        act=true;
                }else{
                       System.out.println("recommence ...");
                }
        }
                return(10*(pushe-1)+pushe3);

    }
}
