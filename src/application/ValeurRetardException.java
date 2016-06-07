package application;

@SuppressWarnings("serial")
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
