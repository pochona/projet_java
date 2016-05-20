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
	 * Constructeur
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
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : oui, Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";
			} else {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : non, Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";}
			}
			/*if (monVolArrivee.getVolAnnule()==true) {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : oui, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";
			} else {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHeureArrivee() + ", Vol Annulé : non, Parking : "+ monVolArrivee.getLeParking() + ", Hall : "+monVolArrivee.getLeHall() + ", Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLeNomDeLaPorte()+", Parking : " + monVolArrivee.getLeNomDuParking()+". \n";}
			}*/
		return str;
	}
	/**
	 * @todo 20/05/2016 : Remettre la bon affichage
	 * return la chaine qui contient l'affichage d'un vol arrivée
	 */
	public String toString(){
		return "Numéro du vol : " + this.getNumVol() + ", Destination : " + this.getProvenance() + ", Heure d'arrivée : " + this.getHeureArrivee() + (this.getVolAnnule()==true?", vol Annulé : non":", vol Annulé : oui") +" Numéro de l'avion : "+ this.getLAvion().getImmat() + ". \n";
	//	return "Numéro du vol : " + this.getNumVol() + ", Destination : " + this.getProvenance() + ", Heure d'arrivée : " + this.getHeureArrivee() + (this.getVolAnnule()==true?", vol Annulé : non":", vol Annulé : oui") +" Numéro de l'avion : "+ this.getLAvion().getImmat() + " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking() + ". \n";
		
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
	
	/**
	 * Methode getlesVolsA
	 * getter de la liste des vols arrivée.
	 * @return hashmap
	 * @author lb
	 * @version 1.0 - 20/05/2016
	 */
	public static HashMap<String, VolArrivee> getlesVolsA(){
		return lesVolsArrivee;
	}
}
