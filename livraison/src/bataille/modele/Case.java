package bataille.modele;

public class Case{
	private Bateau bateau = null;
	private String affiche = "-";
	private int x;
	private int y;

	/**Constructeur de la classe Case
	 * @param x l'axe x
	 * @param y l'axe y
	 */
	public Case(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/**Méthode quand on touche le bateau
	 */
	public void toucher(){
		if(this.bateau==null){
			this.affiche = "!";
		}
		else{
			this.bateau.toucher();
			this.affiche = "x";
		}
	}
	
	/**Méthode qui retourne la situation de la case
	 * @return la situation de la case
	 */
	public String setAffiche(){
		return this.affiche;
	}
	
	/**Méthode pour mettre le bateau sur la case
	 * @param b le bateau que nous ajoutons
	 */
	public void placerBateau(Bateau b){
		this.bateau = b;
	}

	/**Méthode pour retourner le bateau présent sur la case
	 * @return retourne le bateau présent
	 */
	public Bateau presentBateau(){
		return this.bateau;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
