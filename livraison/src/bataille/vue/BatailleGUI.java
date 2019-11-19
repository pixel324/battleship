package bataille.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;

import bataille.modele.*;

public class BatailleGUI extends JFrame implements MouseListener {

    private JButton suivant;
    private Mouse grille[] = new Mouse[10 * 20];
    public int appuie = -1;
    public position pan;


	/**
	* Construction de la vue pour l'affichage GUI
	*
	* @param b		la grille du random
	*/
    public BatailleGUI(GrilleMer b) {
        super();
        this.build(b);
    }

	/**
	* Méthode qui construit cette interface graphiques
	*
	* @param b		la grille du random
	*/
    private void build(GrilleMer b) {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(1200,600));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Bataille Naval");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(positionGrille(b), BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

	/**
	* Méthode qui positionne la grille
	*
    * @param ad		la grille du random
    * @return Le panel qui contient les grilles
	*/
    public JPanel positionGrille(GrilleMer ad) {
        pan = new position(ad.placeBateau);
        GridLayout gl = new GridLayout(12, 26, 10, 10);
        pan.setLayout(gl);
        boolean changement = false;

        post(pan,true);
        float compteur = 1;
        for (int i = 0; i<10*20; i++){
            if(i%10==0){
				pan.add(new Mouse(""+((int) compteur)));
				compteur+=0.5;
                if(changement==true){
                    changement = false;
                }else{
                    changement = true;
                }
            }
            Mouse nouveau = new Mouse(changement, i);
            if(changement == true){
                nouveau.addMouseListener(this);
            }
            this.grille[i]=nouveau;
            this.grille[i].setBackground(new Color(175, 175, 175));
            pan.add(this.grille[i]);
            if((i+1)%10==0){
				pan.add(new Mouse(""));
			}
        }
        post(pan,false);
        return pan;
    }

    /**
	* Méthode qui permet de faire de beau espacement pour rendre la vue plus ergonomique
	*
	* @param pan panel ou l'on va ajouter les informations
	* @param action boolean pour savoir si l'on affiche une lettre ou un espace
	*/
    public void post(JPanel pan, boolean action){
		for (int i = 0;i<2;i++){
			for(int y = 0;y<10;y++){
				if(y==0){
					pan.add(new Mouse(""));
				}
				if(action==true){
					pan.add(new Mouse(Character.toString ((char) (y+65))));
				}else{
					pan.add(new Mouse(""));
				}
			}
			pan.add(new Mouse(""));
		}
	}
	/**
	 * Méthode pour reset la valeur de "appuie"
	 */
    public void setappuie() {
        this.appuie = -1;
    }
	/**
	 * Redéfinition d'une méthode qui permet de réagir quand on reste clické
	 * @param e qui est l'évènement où l'on va agir
	 */
    @Override
    public void mousePressed(MouseEvent e) {
    }
	/**
	 * Redéfinition d'une méthode qui permet de réagir à la fin d'un click
	 * @param e qui est l'évènement où l'on va agir
	 */
    @Override
    public void mouseReleased(MouseEvent e) {
        String mot = e.toString();
        String[] sepa = mot.split(" ");
        this.appuie = Integer.parseInt(sepa[sepa.length - 1]);
        int b = this.appuie/10;
        this.appuie = this.appuie - 10*(b/2);
    }
	/**
	 * Redéfinition d'une méthode qui permet de réagir au début du click
	 * @param e qui est l'évènement où l'on va agir
	 */
    @Override
    public void mouseEntered(MouseEvent e) {
    }
	/**
	 * Redéfinition d'une méthode qui permet de réagir au click
	 * @param e qui est l'évènement où l'on va agir
	 */
    @Override
    public void mouseClicked(MouseEvent e) {
    }
	/**
	 * Redéfinition d'une méthode qui permet de réagir à n'importe quel état changeant d'une souris
	 * @param e qui est l'évènement où l'on va agir
	 */
    @Override
    public void mouseExited(MouseEvent e) {
    }
	/**
	 * Méthode permettant de changer la couleur d'une case
	 * @param position qui est l'entier représentant le coup joué
	 * @param quel qui est un booleén permettant de savoir sur quel jeu on va modifier la couleur
	 * @param a qui est un entier représentant la valeur "Rouge" d'une couleur
	 * @param b qui est un entier représentant la valeur "Verte" d'une couleur
	 * @param c qui est un entier réprésentant la valeur "Bleue" d'une couleur
	 */
    public void changementcouleur(int position, boolean quel, int a, int b, int c) {
        if (quel == true){
            int transfert = position/10;
            position = position + 10*transfert;
            this.grille[position].change(a, b, c);
        } else {
            int transfert = position/10;
            position = position+ 10*(transfert+1);
            this.grille[position].change(a, b, c);
        }
    }

}
