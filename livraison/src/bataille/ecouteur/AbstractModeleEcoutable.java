package bataille.ecouteur;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractModeleEcoutable{

    protected List<EcouteurModele> ecouteurs;

	/**Constructeur de la classe AbstractModeleEcoutable
	 */
    public AbstractModeleEcoutable(){
        this.ecouteurs = new ArrayList<>();
    }

	/**Méthode d'ajout à l'écouteur
	 * @param e le modele à écouté
	 */
    public void ajoutEcouteur(EcouteurModele e){
        this.ecouteurs.add(e);
    }

	/**Méthode pour retirer un élément de l'écoute
	 * @param e l'élément à retirer de l'écoute
	 */
    public void retraitEcouteur(EcouteurModele e){
        this.ecouteurs.remove(e);
    }

	/**Méthode qui mets a jour les models
	 */
    protected void fireChangement(){
        for(EcouteurModele e : ecouteurs){
            e.modeleMisAJour(this);
        }
    }
}
