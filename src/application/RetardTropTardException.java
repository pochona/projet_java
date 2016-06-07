package application;

@SuppressWarnings("serial")
/**
 * Cette exception est levée lorsqu'un vol est retardé trop tard, et qu'i ldepasse sur le lendemain
 *
 */
public class RetardTropTardException extends Exception {

	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * constante de l'erreur du vol de départ
	 */
	public static final int VOL_DEPART = 1;
	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * constante de l'erreur du vol d'arrivée
	 */
	public static final int VOL_ARRIVEE = 2;
	
	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * Mode de l'erreur, départ ou arrivé selon la constante
	 */
	public int mode;

	/**
	 * Constructeur de RetardTropTard.
	 * 
	 * @author np
	 * @param mode : mode de l'erreur (voir les int constantes)
	 * @version 1.0 - 28/05/2016
	 */
	public RetardTropTardException (int mode) {
		this.mode = mode;
	}
	
	/**
	 * toString
	 * Affichage de l'erreur
	 * 
	 * @author np
	 * @version 1.0 - 28/05/2016
	 */
	public String toString(){
		if(this.mode==VOL_DEPART){
			return "Le retard de ce vol départ ne peut pas dépasser 23h59!";
		} else {
			return "Le retard de ce vol arrivée est trop grand, car le vol départ ne peut pas partir de l'aéroport après 23h59!";
		}
	}
	
}
