package application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
	private static LinkedHashMap<String, VolArrivee> lesVolsArrivee = new LinkedHashMap<String, VolArrivee>();
	
	/**
	 * Constructeur
	 * @author np
	 * @param num : string numéro du vol
	 * @param v : string ville de provenance
	 * @param h : int heures
	 * @param m : int minutes
	 * @param avion : string numéro de l'avion
	 * @version 1.0 - 24/05/2016
	 */
	public VolArrivee(String num, Horaire ho, String v, Avion avion){
		super(num, avion);
		this.heureArrivee=ho;
		this.provenance=v;
		VolArrivee.ajouterOrdre(this);
		Vol.ajouterOrdre(this);
	}
	
	/**
	 * Méthode builtChaineVolsArrivee
	 * Cette méthode construit une chaine pour l'affichage de tous les vols d'arrivée
	 * @author np
	 * @return String : la chaine a afficher
	 * @version 1.0 - 24/05/2016
	 */
	public static String builtChaineVolsArrivee(){
		Iterator<VolArrivee> val = lesVolsArrivee.values().iterator();
		String str = "------ Les vols arrivés ------" + " \n";
		while(val.hasNext()){
			VolArrivee monVolArrivee = val.next();
			if (monVolArrivee.isAnnule()) {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHoraire() + ", Vol Annulé : oui, Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLaPorte().getNom()+", Parking : " + monVolArrivee.getLeParking().getNom()+". \n";
			} else {
				str += "Numéro du vol : " + monVolArrivee.getNumVol() + ", Destination : " + monVolArrivee.getProvenance() + ", Heure d'arrivée : " + monVolArrivee.getHoraire() + ", Vol Annulé : non, Numéro de l'avion : "+monVolArrivee.getLAvion().getImmat() + " Porte : "+ monVolArrivee.getLaPorte().getNom()+", Parking : " + monVolArrivee.getLeParking().getNom()+". \n";}
		}
		return str;
	}
	

	/**
	 * Méthode toString
	 * Méthode d'affichage d'un vol d'arrivé
	 * @author np
	 * @return String : la chaine a afficher
	 * @version 1.0 - 24/05/2016
	 */
	public String toString(){
		return "Numéro du vol : " + this.getNumVol() + ", Destination : " + this.getProvenance() + ", Heure d'arrivée : " + this.getHoraire() + (this.isAnnule()?", vol Annulé : non":", vol Annulé : oui") +" Numéro de l'avion : "+ this.getLAvion().getImmat() + " Porte : "+ this.getLaPorte().getNom()+", Parking : " + this.getLeParking().getNom() + ". \n";

	}

	/**
	 * Méthode afficherLesVolsArrivee
	 * Méthode permettant d'afficher tous les vols d'arrivé directement (sysout)
	 * @author np
	 * @version 1.0 - 24/05/2016
	 */
	public static void afficherLesVolsArrivee(){System.out.println(VolArrivee.builtChaineVolsArrivee());}
	
	/**
	 * Méthode getHoraire
	 * Méthode permettant de retourner l'horaire d'arrivé du vol
	 * @author np
	 * @return Horaire: l'horaire du vol
	 * @version 1.0 - 25/05/2016
	 */
	public Horaire getHoraire(){return this.heureArrivee;}

	/**
	 * Méthode getProvenance
	 * Méthode permettant de retourner la provenance du vol.
	 * @author np
	 * @return String: la provenance
	 * @version 1.0 - 25/05/2016
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
	
	/**
	 * 
	 * @param newHeureArr : Horaire, nouvelle heure d'arrivée du vol
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public void decalerHeureArrivee(Horaire newHeureArr){
		this.heureArrivee=newHeureArr;
		// je le réinsere dans la hashmap pour conserver l'ordre
		VolArrivee.lesVolsArrivee.remove(this.getNumVol());
		VolArrivee.ajouterOrdre(this);
		
		// ca doit aussi le déplacer dans les Vol
		Vol.getlesVols().remove(this.getNumVol());
		Vol.ajouterOrdre(this);
	}
	
	/**
	 * méthode ajouterOrdre.
	 * Cette méthode va ajouter dans l'ordre le vol, dans la hashmap
	 * (static void).
	 * @author ap
	 * @param Vol v: le vol a ajouter
	 * @version 1.0 - 07/06/2016
	 */
	public static void ajouterOrdre(VolArrivee v){
		boolean find = false;
		int cpt = 0;
		Horaire monHoraire = v.getHoraire();
		Horaire horaireCourant= null;
		// Je cherche l'indice ou ajouter mon vol avant de l'ajouter
		Iterator<String> it = lesVolsArrivee.keySet().iterator();
		while(it.hasNext() && !find){
			String key = it.next();
			 horaireCourant = lesVolsArrivee.get(key).getHoraire();
			if(horaireCourant.compareTo(monHoraire) > 0){
				find = true;
			} else {
				cpt++;
			}
		}
		int cptControl = 0;

		HashMap<String, VolArrivee> copieHashMap = (HashMap<String, VolArrivee>) lesVolsArrivee.clone();
		lesVolsArrivee.clear();
		Iterator<String> itInsert = copieHashMap.keySet().iterator();
		// j'insere les premiers vols
		while(itInsert.hasNext() && cptControl < cpt){
			String key = itInsert.next();
			lesVolsArrivee.put(key, copieHashMap.get(key));
			cptControl++;
		}
		// J'insere mon nouveau vol
		lesVolsArrivee.put(v.getNumVol(), v);
		// j'insere le reste de mes vols
		while(itInsert.hasNext()){
			String key = itInsert.next();
			lesVolsArrivee.put(key, copieHashMap.get(key));
		}
	}

}
