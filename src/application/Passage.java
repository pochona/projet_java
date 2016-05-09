package application;

import utilitaires.Duree;

public class Passage {
/**
 * Duree de temps entre le départ et l'arrivée d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}
	
}
