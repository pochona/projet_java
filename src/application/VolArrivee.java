package application;

import java.util.HashMap;

import utilitaires.Horaire;

public class VolArrivee extends Vol {
	
	/**
	 * Ville de provenance du vol
	 */
	private String provenance;
	/**
	 * Heure d'arriv�e du vol
	 */
	private Horaire heureArrivee;
	/**
	 * Toutes les instances des vols d'arriv�e
	 */
	private static HashMap<String, VolArrivee> lesVolsArrivee = new HashMap<String, VolArrivee>();
	/**
	 * Constructeur avec porte et hall
	 * @param num : string num�ro du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param v : string ville de provenance
	 * @param h : Horaires heures
	 * @param avion : string num�ro de l'avion
	 */
	public VolArrivee(String num, Porte p, Hall ha, Horaire ho, String v, Avion avion){
		super(num, p, ha, avion);
		this.heureArrivee=ho;
		this.provenance=v;
		lesVolsArrivee.put(num, this);
	}
	/**
	 * Constructeur sans porte et hall
	 * @param num : string num�ro du vol
	 * @param v : string ville de provenance
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string num�ro de l'avion
	 */
	public VolArrivee(String num, Horaire ho, String v, Avion avion){
		super(num, avion);
		this.heureArrivee=ho;
		this.provenance=v;
		lesVolsArrivee.put(num, this);
	}
	
	
}
