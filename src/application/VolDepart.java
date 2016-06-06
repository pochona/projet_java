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
	 * Constructeur 
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
	public static String builtChaineVolsDepart(){
		Iterator<VolDepart> val = lesVolsDepart.values().iterator();
		String str = "------ Les vols départ ------" + " \n";
		while(val.hasNext()){
			VolDepart monVolDepart = val.next();
			if (monVolDepart.isAnnule()) {
				str += "Numéro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de départ : " + monVolDepart.getHoraire() + ", Vol Annulé : oui, Numéro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";
			} else {
				str += "Numéro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de départ : " + monVolDepart.getHoraire() + ", Vol Annulé : non, Numéro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";}
		}	
		/*if (monVolDepart.getVolAnnule()==true) {
			str += "Numéro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de départ : " + monVolDepart.getHoraire() + ", Vol Annulé : oui, Parking :"+ monVolDepart.getLeParking() + ", Hall : "+monVolDepart.getLeHall() + ", Numéro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";
			} else {
			str += "Numéro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de départ : " + monVolDepart.getHoraire() + ", Vol Annulé : non, Parking :"+ monVolDepart.getLeParking() + ", Hall : "+monVolDepart.getLeHall() + ", Numéro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";}
			}	*/
		return str;
	}
	/**
	 * @todo 20/05/2016 : Remettre la bon affichage
	 * return la chaine qui contient l'affichage d'un vol arrivée
	 */
	public String toString(){
		//return  "Numéro du vol : " + this.getNumVol() + ", Destination : " + this.getDestination() + ", Heure de départ : " + this.getHoraire() + (this.getVolAnnule()==true?", vol Annulé : non":", vol Annulé : oui") + ", Numéro de l'avion : "+ this.getLAvion().getImmat() +". \n";
		return  "Numéro du vol : " + this.getNumVol() + ", Destination : " + this.getDestination() + ", Heure de départ : " + this.getHoraire() + (this.isAnnule()?", vol Annulé : non":", vol Annulé : oui") + ", Numéro de l'avion : "+ this.getLAvion().getImmat() + " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";
	}
	
	/**
	 * Affiche tout les vols départ.
	 */
	public static void afficherLesVolsDepart(){System.out.println(VolDepart.builtChaineVolsDepart());}
	/**
	 * 
	 * @return l'heure de départ
	 */
	public Horaire getHoraire(){return this.heureDepart;}
	/**
	 * 
	 * @return la destination
	 */
	public String getDestination(){return this.destination;}


	/**
	 * Methode getlesVolsD
	 * getter de la liste des vols depart.
	 * @return hashmap
	 * @author lb
	 * @version 1.0 - 20/05/2016
	 */

	public static HashMap<String, VolDepart> getlesVolsD(){
		return lesVolsDepart;
	}
	/**
	 * 
	 * @param newHeureDep : Horaire, nouvelle heure de départ du vol
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public void decalerHeureDepart(Horaire newHeureDep){
		this.heureDepart=newHeureDep;
	}
}
