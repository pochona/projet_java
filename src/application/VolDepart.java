package application;

import java.util.HashMap;
import java.util.Iterator;
import utilitaires.Horaire;

public class VolDepart extends Vol {
	/**
	 * Ville de destination du vol
	 */
	private String destination;
	/**
	 * Heure de départ du vol
	 */
	private Horaire heureDepart;
	/**
	 * Toutes les instances des vols d'arrivée
	 */
	private static HashMap<String, VolDepart> lesVolsDepart = new HashMap<String, VolDepart>();
	/**
	 * Constructeur avec porte et hall
	 * @param num : string numéro du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param v : string ville de destination
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string numéro de l'avion
	 */
	public VolDepart(String num, Porte p, Hall ha, Horaire ho, String v, Avion avion){
		super(num, p, ha, avion);
		this.heureDepart=ho;
		this.destination=v;
		lesVolsDepart.put(num, this);
	}
	/**
	 * Constructeur sans porte et hall
	 * @param num : string numéro du vol
	 * @param v : string ville de destination
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string numéro de l'avion
	 */
	public VolDepart(String num, Horaire ho, String v, Avion avion){
		super(num, avion);
		this.heureDepart=ho;
		this.destination=v;
		lesVolsDepart.put(num, this);
	}
	/**
	 * Cette méthode permet de retourner sous forme de string les vols de départ pour les afficher.
	 * @return
	 */
	public static String afficherLesVolsDepart(){
		Iterator<VolDepart> val = lesVolsDepart.values().iterator();
		String str = "------ Les vols départ ------" + " \n";
		while(val.hasNext()){
			VolDepart monVolDepart = val.next();
			str += "Numéro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de départ : " + monVolDepart.getHeureDepart() + ", Vol Annulé : " + monVolDepart.getVolAnnule() +", Porte :"+ monVolDepart.getLaPorte() + ", Hall : "+monVolDepart.getLeHall() + ", Numéro de l'avion : "+monVolDepart.getLAvion().getImmat() + " \n";
		}
		return str;
	}

	public Horaire getHeureDepart(){return this.heureDepart;}
	public String getDestination(){return this.destination;}
}
