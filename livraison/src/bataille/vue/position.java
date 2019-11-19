package bataille.vue;

import javax.swing.JPanel;
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;

public class position extends JPanel {
	private List<Integer> emplacement = new ArrayList<>();
	private List<Integer> emplacement2 = new ArrayList<>();
	private List<List> duo = new ArrayList<>();

/**
 * Constructeur de la classe
 * @param empla qui est une liste contenant les emplacements des bateaux.
 */
	public position(List empla) {
		this.emplacement = empla;
		this.duo.add(this.emplacement);
		this.duo.add(this.emplacement2);
	}

	/**
	 * Méthode permettant d'ajouter un x et y à un bateau afin de connaitre sa position.
	 * @param x un entier.
	 * @param y un entier
	 */
	public void ajout(int x , int y){
		emplacement2.add(x);
		emplacement2.add(y);
		this.repaint();
	}

	/**
	 * Permet de mettre les différents éléments graphique,
	 * @param g Un objet de type Graphics qui nous sert ensuite à peindre les différents éléments.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.white);
		g2.fillRect(0,0,this.getWidth(),this.getHeight());

		int initx = this.getWidth() / 26;
		int inity = this.getHeight() / 12;
		int a,b,c,d,e,f;
		int debut = initx + this.getWidth()/2;
		for(int j=0; j<this.duo.size();j++){
			for (int i = 0; i < this.duo.get(j).size(); i += 2) {
				if(j==1){
					debut = initx;
				}
				a = (int) this.duo.get(j).get(i);
				b = (int) this.duo.get(j).get(i+1);
				c = a % 10;
				d = a / 10;
				e = b % 10;
				f = b / 10;
				g2.setPaint(Color.green);
				g2.fillRect(debut+c * initx +(c+1)*5,inity+d*inity+d, (e - c + 1) * initx+(e-c+1)*4, (f - d + 1) * inity+(f-d));
				g2.setPaint(Color.white);
				g2.fillRect(debut+c * initx +(c+1)*5+4,inity+d*inity+d+4, (e - c + 1) * initx+(e-c+1)*4-8, (f - d + 1) * inity+(f-d)-8);

			}
		}
		g2.setPaint(Color.black);
		g2.fillRect(this.getWidth()/2 - 2,0,4,this.getHeight());
	}
}
