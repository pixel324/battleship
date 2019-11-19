package bataille.modele;

public class couple{
	private int x;
	private int y;

	/**Constructeur de la classe couple
	 * @param x l'axes x
	 * @param y l'axes y
	 */
	public couple(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**Méthode getx pour obtenir la valeur de x
	 * @return la valeur de x
	 */
	public int getx(){
		return this.x;
	}

	/**Méthode gety pour obtenir la valeur de y
	 * @return la valeur de y
	 */
	public int gety(){
		return this.y;
	}
	
}
