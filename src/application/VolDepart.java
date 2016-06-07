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
	 * Heure de d�part du vol
	 */
	private Horaire heureDepart;
	/**
	 * Toutes les instances des vols d'arriv�e
	 */
	private static LinkedHashMap<String, VolDepart> lesVolsDepart = new LinkedHashMap<String, VolDepart>();
	
	/**
	 * Constructeur 
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
		VolDepart.ajouterOrdre(this);
		Vol.ajouterOrdre(this);
	}
	/**
	 * Cette m�thode permet de retourner sous forme de string les vols de d�part pour les afficher.
	 * @return
	 */
	public static String builtChaineVolsDepart(){
		Iterator<VolDepart> val = lesVolsDepart.values().iterator();
		String str = "------ Les vols d�part ------" + " \n";
		while(val.hasNext()){
			VolDepart monVolDepart = val.next();
			if (monVolDepart.isAnnule()) {
				str += "Num�ro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de d�part : " + monVolDepart.getHoraire() + ", Vol Annul� : oui, Num�ro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVolDepart.getNumVol() + ", Destination : " + monVolDepart.getDestination() + ", Heure de d�part : " + monVolDepart.getHoraire() + ", Vol Annul� : non, Num�ro de l'avion : "+monVolDepart.getLAvion().getImmat() + " Porte : "+ monVolDepart.getLeNomDeLaPorte()+", Parking : " + monVolDepart.getLeNomDuParking()+". \n";}
		}	
		return str;
	}
	/**
	 * @todo 20/05/2016 : Remettre la bon affichage
	 * return la chaine qui contient l'affichage d'un vol arriv�e
	 */
	public String toString(){
		//return  "Num�ro du vol : " + this.getNumVol() + ", Destination : " + this.getDestination() + ", Heure de d�part : " + this.getHoraire() + (this.getVolAnnule()==true?", vol Annul� : non":", vol Annul� : oui") + ", Num�ro de l'avion : "+ this.getLAvion().getImmat() +". \n";
		return  "Num�ro du vol : " + this.getNumVol() + ", Destination : " + this.getDestination() + ", Heure de d�part : " + this.getHoraire() + (this.isAnnule()?", vol Annul� : non":", vol Annul� : oui") + ", Num�ro de l'avion : "+ this.getLAvion().getImmat() + " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";
	}
	
	/**
	 * Affiche tout les vols d�part.
	 */
	public static void afficherLesVolsDepart(){System.out.println(VolDepart.builtChaineVolsDepart());}
	/**
	 * 
	 * @return l'heure de d�part
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
	 * @param newHeureDep : Horaire, nouvelle heure de d�part du vol
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public void decalerHeureDepart(Horaire newHeureDep){
		this.heureDepart=newHeureDep;
		// je le r�insere dans la hashmap pour conserver l'ordre
		VolDepart.lesVolsDepart.remove(this.getNumVol());
		VolDepart.ajouterOrdre(this);
		
		// ca doit aussi le d�placer dans les Vol
		Vol.getlesVols().remove(this.getNumVol());
		Vol.ajouterOrdre(this);
	}
	
	/**
	 * m�thode ajouterOrdre.
	 * Cette m�thode va ajouter dans l'ordre le vol, dans la hashmap
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
