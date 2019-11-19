package bataille.modele;

public class Bateau{
	private int taille;
	private int impact = 0;
	private boolean enVie = true;
	public int debut;
	public int fin;
	public boolean position;

	/**Constructeur de la classe bateau
	 * @param taille la taille du bateau
	 * @param debut la case de debut du bateau
	 * @param fin la case de fin du bateau
	 * @param position la position du bateau
	 */
	public Bateau(int taille,int debut, int fin,boolean position){
		this.taille = taille;
		this.debut = debut;
		this.fin = fin;
		this.position = position;
	}

	/**Méthode permettant d'ajouter un malus de dommage au bâteau touché, et permet de savoir quand il est coulé.
	*/
	public void toucher(){
		this.impact+=1;
		if(this.impact==this.taille){
			this.enVie = false;
		}
	}

	/**Méthode permettant de savoir si le bateau est coulé ou pas
	 * @return un booleen qui indique si la bateau existe encore
	 */
	public boolean fin(){
		return this.enVie;
	}

}
