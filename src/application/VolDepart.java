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
	 * Heure de d�part du vol
	 */
	private Horaire heureDepart;
	/**
	 * Toutes les instances des vols d'arriv�e
	 */
	private static HashMap<String, VolDepart> lesVolsDepart = new HashMap<String, VolDepart>();
	/**
	 * Constructeur avec porte et hall
	 * @param num : string num�ro du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param v : string ville de destination
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string num�ro de l'avion
	 */
	public VolDepart(String num, Porte p, Hall ha, Horaire ho, String v, Avion avion){
		super(num, p, ha, avion);
		this.heureDepart=ho;
		this.destination=v;
		lesVolsDepart.put(num, this);
	}
	/**
	 * Constructeur sans porte et hall
	 * @param num : string num�ro du vol
	 * @param v : string ville de destination
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string num�ro de l'avion
	 */
	public VolDepart(String num, Horaire ho, String v, Avion avion){
		super(num, avion);
		this.heureDepart=ho;
		this.destination=v;
		lesVolsDepart.put(num, this);
	}
	/**
	 * Cette m�thode permet de retourner sous forme de string les vols de d�part pour les afficher.
	 * @return
	 */
	public static String toStriing(){
		Iterator<VolDepart> val = lesVolsDepart.values().iterator();
		String str = "------ Les vols d�part ------" + " \n";
		while(val.hasNext()){
			VolDepart monVolDepart = val.next();
			if (monVolDepart.getVolAnnule()==true) {
			str += "Num�ro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de d�part : " + monVolDepart.getHeureDepart() + ", Vol Annul� : oui, Porte :"+ monVolDepart.getLaPorte() + ", Hall : "+monVolDepart.getLeHall() + ", Num�ro de l'avion : "+monVolDepart.getLAvion().getImmat() + " \n";
		}else {str += "Num�ro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de d�part : " + monVolDepart.getHeureDepart() + ", Vol Annul� : non, Porte :"+ monVolDepart.getLaPorte() + ", Hall : "+monVolDepart.getLeHall() + ", Num�ro de l'avion : "+monVolDepart.getLAvion().getImmat() + " \n";}
		}	
			
		return str;
	}
	public static void afficherLesVolsDepart(){System.out.println(VolDepart.toStriing());}

	public Horaire getHeureDepart(){return this.heureDepart;}
	public String getDestination(){return this.destination;}
}
