package application;

import utilitaires.Duree;

public class Passage {
/**
 * Duree de temps entre le d�part et l'arriv�e d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}
	
}
