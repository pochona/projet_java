package application;

@SuppressWarnings("serial")
public class ParkingIndispo extends Exception {
	
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
	public ParkingIndispo (Vol v) {
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
