package application;

import utilitaires.Duree;

public class Passage {
	
	/**
	 * vol de départ du passage
	 */
	private Vol volDepart;
	
	/**
	 * vol d'arrivée du passage
	 */
	private Vol volArrivee;
	
	/**
	 * parking associé au passage 
	 */
	private Parking leParking;
	
	/**
	 * Constructeur de Passage.
	 * 
	 * @author ap
	 * @params a : Vol d'arrivée
	 * @params d : Vol de départ
	 * @version 1.0 - 11/05/2016
	 */
	public Passage(Vol a, Vol d){
		this.volDepart = d;
		this.volArrivee = a;
	}
	
/**
 * Duree de temps entre le départ et l'arrivée d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}

	
}
