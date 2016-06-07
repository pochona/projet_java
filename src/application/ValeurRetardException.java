package application;

@SuppressWarnings("serial")
/**
 * Cette exception est levée si la valeur saisie pour retarder le vol est mauvaise : inferieur à zero ou supérieur à 1 jour
 * Cette exception ne prend pas en compte les erreurs de format
 * @see java.lang.NumberFormatException
 *
 */
public class ValeurRetardException extends Exception {
	
	/**
	 * NNombre de minute de retard
	 */
	public int n;

	/**
	 * Constructeur de ValeurRetardException.
	 * 
	 * @author ap
	 * @param int n : nombre de minutes de retard
	 * @version 1.0 - 07/06/2016
	 */
	public ValeurRetardException (int n) {
		this.n = n;
	}
	
	/**
	 * toString
	 * Affichage de l'erreur
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public String toString(){
		if(n > 1440){
			return "Le retard ne peut pas dépasser 24h";
		} else {
			return "Le retard ne peut pas être négatif";
		}
	}

}
