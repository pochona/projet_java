package application;

import java.util.HashMap;
import java.util.Iterator;
import utilitaires.Horaire;

public class VolArrivee extends Vol {
	/**
	 * Ville de provenance du vol
	 */
	private String provenance;
	/**
	 * Heure d'arrivée du vol
	 */
	private Horaire heureArrivee;
	/**
	 * Toutes les instances des vols d'arrivée
	 */
	private static HashMap<String, VolArrivee> lesVolsArrivee = new HashMap<String, VolArrivee>();
	/**
	 * Constructeur avec porte et hall
	 * @param num : string numéro du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param v : string ville de provenance
	 * @param h : Horaires heures
	 * @param avion : string numéro de l'avion
	 */
	public VolArrivee(String num, Parking p, Hall ha, Horaire ho, String v, Avion avion){
		super(num, p, ha, avion);
		this.heureArrivee=ho;
		this.provenance=v;
		lesVolsArrivee.put(num, this);
	}
	/**
	 * Constructeur sans porte et hall
	 * @param num : string numéro du vol
	 * @param v : string ville de provenance
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string numéro de l'avion
	 */
	public VolArrivee(String num, Horaire ho, String v, Avion avion){
		super(num, avion);
		this.heureArrivee=ho;
		this.provenance=v;
		lesVolsArrivee.put(num, this);
	}
	/**
	 * Cette méthode retourne une chaine de caractères pour permettre d'afficher la liste des vols d'arrivée
	 * @return
	 */
	public static String builtChaineVolsArrivee(){
		Iterator<VolArrivee> val = lesVolsArrivee.values().iterator();
		String str = "------ Les vols arrivés ------" + " \n";
		while(val.hasNext()){
			VolArrivee monVolArrivee = val.next();
			if (monVolArrivee.getVolAnnule()==true) {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : oui, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " \n";
			} else {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : non, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " \n";}
			}
		return str;
	}
	/**
	 * Affiche tout les vols arrivé.
	 */
	public static void afficherLesVolsArrivee(){System.out.println(VolArrivee.builtChaineVolsArrivee());}
	/**
	 * 
	 * @return l'heure d'arrivée du vol.
	 */
	public Horaire getHeureArrivee(){return this.heureArrivee;}
	/**
	 * 
	 * @return la provenance du vol
	 */
	public String getProvenance(){return this.provenance;}
	
	
}
