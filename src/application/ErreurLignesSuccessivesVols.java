package application;

public class ErreurLignesSuccessivesVols extends Exception {
	
	/**
	 * constante de l'erreur du vol de départ
	 */
	public static final int VOL_DEPART = 1;
	/**
	 * constante de l'erreur du vol d'arrivée
	 */
	public static final int VOL_ARRIVEE = 2;
	
	/**
	 * Mode de l'erreur, départ ou arrivé selon la constante
	 */
	public int mode;
	
	/**
	 * Numéro de l'avion en erreur
	 */
	public String numAvion;

	/**
	 * Constructeur de ErreurLignesSuccessivesVols.
	 * 
	 * @author np, ap
	 * @param mode : mode de l'erreur (voir les int constantes)
	 * @param numAvion : numéro de l'avion en erreur
	 * @version 1.0 - 09/05/2016 by np 
	 * @version 2.0 - 18/05/2016 by ap : correction du constructeur
	 */
	public ErreurLignesSuccessivesVols (int mode, String numAvion) {
		this.mode = mode;
		this.numAvion = numAvion;
	}
	
	/**
	 * toString
	 * Affichage de l'erreur
	 * 
	 * @author np, ap
	 * @version 1.0 - 09/05/2016 by np 
	 * @version 2.0 - 18/05/2016 by ap : correction de l'affichage en une méthode toString
	 */
	public String toString(){
		if(this.mode==VOL_DEPART){
			return "Attention il manque le départ de l'avion : " + this.numAvion;
		} else {
			return "Attention la ligne qui suit le départ de l'avion : " + this.numAvion+ " n'est pas correcte.";
		}
	}

}
