package application;

@SuppressWarnings("serial")
/**
 * Cette exception est lev�e lorsqu'un vol est retard� trop tard, et qu'i ldepasse sur le lendemain
 *
 */
public class RetardTropTardException extends Exception {

	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * constante de l'erreur du vol de d�part
	 */
	public static final int VOL_DEPART = 1;
	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * constante de l'erreur du vol d'arriv�e
	 */
	public static final int VOL_ARRIVEE = 2;
	
	/**
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * Mode de l'erreur, d�part ou arriv� selon la constante
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
			return "Le retard de ce vol d�part ne peut pas d�passer 23h59!";
		} else {
			return "Le retard de ce vol arriv�e est trop grand, car le vol d�part ne peut pas partir de l'a�roport apr�s 23h59!";
		}
	}
	
}
