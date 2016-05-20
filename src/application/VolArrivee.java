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
	 * Constructeur
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
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : oui, Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : non, Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";}
			}
			/*if (monVolArrivee.getVolAnnule()==true) {
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : oui, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arriv�e : " + monVolArrivee.getHeureArrivee() + ", Vol Annul� : non, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Num�ro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";}
			}*/
		return str;
	}
	/**
	 * @todo 20/05/2016 : Remettre la bon affichage
	 * return la chaine qui contient l'affichage d'un vol arriv�e
	 */
	public String toString(){
		return "Num�ro du vol : " + this.getNumVol() + ", Destination : " + this.getProvenance() + ", Heure d'arriv�e : " + this.getHeureArrivee() + (this.getVolAnnule()==true?", vol Annul� : non":", vol Annul� : oui") +" Num�ro de l'avion : "+ this.getLAvion().getImmat() + ". \n";
	//	return "Num�ro du vol : " + this.getNumVol() + ", Destination : " + this.getProvenance() + ", Heure d'arriv�e : " + this.getHeureArrivee() + (this.getVolAnnule()==true?", vol Annul� : non":", vol Annul� : oui") +" Num�ro de l'avion : "+ this.getLAvion().getImmat() + " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking() + ". \n";
		
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
	
	/**
	 * Methode getlesVolsA
	 * getter de la liste des vols arriv�e.
	 * @return hashmap
	 * @author lb
	 * @version 1.0 - 20/05/2016
	 */
	public static HashMap<String, VolArrivee> getlesVolsA(){
		return lesVolsArrivee;
	}
}
