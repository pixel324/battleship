package bataille.vue;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mouse extends JPanel implements MouseListener {
	private int name;
	private int color1 = 255;
	private int color2 = 255;
	private int color3 = 255;


	/**
	 * Constructeur de la classe
	 * @param ecoute qui est un boolean
	 * @param i      qui est un entier
	 */
	public Mouse(boolean ecoute, int i) {
		this.name = i;
		if (ecoute == true) {
			this.addMouseListener(this);
		}

	}

	/**
	 *  Autre constructeur de Mouse
	 * @param mot Prenant un mot.
	 */
	public Mouse(String mot){
		color1 = 0; color2 = 0; color3 = 0;
		JLabel l1;
		l1=new JLabel(mot);
		Font font = new Font("Arial",Font.BOLD,20);
		l1.setFont(font);
		l1.setBounds(this.getWidth()/4,this.getHeight()/2, this.getWidth()/4,this.getHeight()/2);
		this.add(l1);
	}

	/**
	 * Permet de changer les couleurs
	 * @param a un entier pour une première couleur
	 * @param b un entier pour une seconde couleur
	 * @param c un entier pour une troisième couleur
	 */
	public void change(int a, int b, int c){
		this.color1 = a;
		this.color2 = b;
		this.color3 = c;
	}
	/**
	 * Permet de peindre un composant
	 * @param g qui est un objet de type Graphics qui nous permet de peindre.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(color1!=0 || color2!=0 || color3!=0){
			if(this.color2==255){
				g2.setPaint(Color.black);
				g2.fillRect(0,0,this.getWidth(),this.getHeight());
			}
			g2.setPaint(Color.white);
			g2.fillRect(1,1,this.getWidth()-2,this.getHeight()-2);
			if(this.color2!=255){
				g2.setPaint(new Color(this.color1,this.color2,this.color3));
				g2.fillRect(0,0,this.getWidth(),this.getHeight());
			}
		}
		else{
			g2.setPaint(Color.white);
			g2.fillRect(0,0,this.getWidth(),this.getHeight());
		}
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Redéfinition de la méthode toString
	 * @return un string
	 */
	@Override
	public String toString(){
		return Integer.toString(this.name);
	}

}
