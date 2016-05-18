package application;

public class ErreurLignesSuccessivesVols extends Exception {
	
	/**
	 * constante de l'erreur du vol de d�part
	 */
	public static final int VOL_DEPART = 1;
	/**
	 * constante de l'erreur du vol d'arriv�e
	 */
	public static final int VOL_ARRIVEE = 2;
	
	/**
	 * Mode de l'erreur, d�part ou arriv� selon la constante
	 */
	public int mode;
	
	/**
	 * Num�ro de l'avion en erreur
	 */
	public String numAvion;

	/**
	 * Constructeur de ErreurLignesSuccessivesVols.
	 * 
	 * @author np, ap
	 * @param mode : mode de l'erreur (voir les int constantes)
	 * @param numAvion : num�ro de l'avion en erreur
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
	 * @version 2.0 - 18/05/2016 by ap : correction de l'affichage en une m�thode toString
	 */
	public String toString(){
		if(this.mode==VOL_DEPART){
			return "Attention il manque le d�part de l'avion : " + this.numAvion;
		} else {
			return "Attention la ligne qui suit le d�part de l'avion : " + this.numAvion+ " n'est pas correcte.";
		}
	}

}
