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
	public VolArrivee(String num, Parking p, Hall ha, Horaire ho, String v, Avion avion){
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
	/**
	 * Cette m�thode retourne une chaine de caract�res pour permettre d'afficher la liste des vols d'arriv�e
	 * @return
	 */
	public static String builtChaineVolsArrivee(){
		Iterator<VolArrivee> val = lesVolsArrivee.values().iterator();
		String str = "------ Les vols arriv�s ------" + " \n";
		while(val.hasNext()){
			VolArrivee monVolArrivee = val.next();
			if (monVolArrivee.getVolAnnule()==true) {
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : oui, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " \n";
			} else {
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : non, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " \n";}
			}
		return str;
	}
	/**
	 * Affiche tout les vols arriv�.
	 */
	public static void afficherLesVolsArrivee(){System.out.println(VolArrivee.builtChaineVolsArrivee());}
	/**
	 * 
	 * @return l'heure d'arriv�e du vol.
	 */
	public Horaire getHeureArrivee(){return this.heureArrivee;}
	/**
	 * 
	 * @return la provenance du vol
	 */
	public String getProvenance(){return this.provenance;}
	
	
}
