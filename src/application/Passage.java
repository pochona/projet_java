package application;

import utilitaires.Duree;

public class Passage {
	
	/**
	 * vol de d�part du passage
	 */
	private Vol volDepart;
	
	/**
	 * vol d'arriv�e du passage
	 */
	private Vol volArrivee;
	
	/**
	 * parking associ� au passage 
	 */
	private Parking leParking;
	
	/**
	 * Constructeur de Passage.
	 * 
	 * @author ap
	 * @params a : Vol d'arriv�e
	 * @params d : Vol de d�part
	 * @version 1.0 - 11/05/2016
	 */
	public Passage(Vol a, Vol d){
		this.volDepart = d;
		this.volArrivee = a;
	}
	
/**
 * Duree de temps entre le d�part et l'arriv�e d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}

	
}
