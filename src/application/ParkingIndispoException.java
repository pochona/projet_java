package application;

@SuppressWarnings("serial")
/**
 * Cette exception est levée si un vol est modifié, et qu'un parking n'est disponible pour lui
 *
 */
public class ParkingIndispoException extends Exception {
	
	/**
	 * Numéro du vol en erreur
	 */
	public Vol leVol;

	/**
	 * Constructeur de ParkingIndispo.
	 * 
	 * @author ap
	 * @param Vol v : vol en erreur
	 * @version 1.0 - 06/06/2016
	 */
	public ParkingIndispoException (Vol v) {
		this.leVol = v;
	}
	
	/**
	 * toString
	 * Affichage de l'erreur
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public String toString(){
		return "Aucun parking disponible pour réattribuer un parking au vol " + this.leVol.getNumVol();
	}

}
