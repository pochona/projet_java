package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

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
	private static LinkedHashMap<String, VolDepart> lesVolsDepart = new LinkedHashMap<String, VolDepart>();
	
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
		VolDepart.ajouterOrdre(this);
		Vol.ajouterOrdre(this);
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
		// je le réinsere dans la hashmap pour conserver l'ordre
		VolDepart.lesVolsDepart.remove(this.getNumVol());
		VolDepart.ajouterOrdre(this);
		
		// ca doit aussi le déplacer dans les Vol
		Vol.getlesVols().remove(this.getNumVol());
		Vol.ajouterOrdre(this);
	}
	
	/**
	 * méthode ajouterOrdre.
	 * Cette méthode va ajouter dans l'ordre le vol, dans la hashmap
	 * (static void).
	 * @author ap
	 * @param VolDepart v: le vol a ajouter
	 * @version 1.0 - 07/06/2016
	 */
	public static void ajouterOrdre(VolDepart v){
		boolean find = false;
		int cpt = 0;
		Horaire monHoraire = v.getHoraire();
		Horaire horaireCourant= null;
		// Je cherche l'indice ou ajouter mon vol avant de l'ajouter
		Iterator<String> it = lesVolsDepart.keySet().iterator();
		while(it.hasNext() && !find){
			String key = it.next();
			horaireCourant = lesVolsDepart.get(key).getHoraire();
			if(horaireCourant.compareTo(monHoraire) > 0){
				find = true;
			} else {
				cpt++;
			}
		}
		int cptControl = 0;
		//System.out.println("2 :" + copieHashMap);
		HashMap<String, VolDepart> copieHashMap = (HashMap<String, VolDepart>) lesVolsDepart.clone();
		lesVolsDepart.clear();
		Iterator<String> itInsert = copieHashMap.keySet().iterator();
		// j'insere les premiers vols
		while(itInsert.hasNext() && cptControl < cpt){
			String key = itInsert.next();
			lesVolsDepart.put(key, copieHashMap.get(key));
			cptControl++;
		}
		// J'insere mon nouveau vol
		lesVolsDepart.put(v.getNumVol(), v);
		// j'insere le reste de mes vols
		while(itInsert.hasNext()){
			String key = itInsert.next();
			lesVolsDepart.put(key, copieHashMap.get(key));
		}
	}
}
