package application;

@SuppressWarnings("serial")
/**
 * Cette exception est lev�e si un vol est modifi�, et qu'un parking n'est disponible pour lui
 *
 */
public class ParkingIndispoException extends Exception {
	
	/**
	 * Num�ro du vol en erreur
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
		return "Aucun parking disponible pour r�attribuer un parking au vol " + this.leVol.getNumVol();
	}

}
