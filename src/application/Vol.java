package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import utilitaires.Duree;
import utilitaires.Horaire;
import utilitaires.TrancheHoraire;

public abstract class Vol {
	/**
	 * Référence du vol
	 */
	private String numVol;

	/**
	 * Indique si le vol est annulé (true), false sinon.
	 */
	private boolean estAnnule;

	/**
	 * Le numéro de l'avion
	 */
	private Avion lAvion;

	/**
	 * Passage associé au vol0
	 */
	private Passage lePassage;
	
	/**
	 * Nombre de minute de retard
	 */
	private int retard;

	/**
	 * Toutes les instances des vols 
	 */
	private static LinkedHashMap<String, Vol> lesVols = new LinkedHashMap<String, Vol>();

	private static BufferedReader entree;

	/**
	 * Constructeur de vol 
	 * @param num : string numero du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param avion : string numéro de l'avion
	 */
	public Vol(String num, Avion avion){
		this.numVol =num;
		this.estAnnule=false;
		this.lAvion=avion;
		this.retard=0;	
		// On ajoutera a la fin dans la hashmap, car actuellement les horaires ne sont pas renseignes, car elles sont dans les classes spécialisées
	}

	/**
	 * Cette méthode static permet d'ouvrir le fichier des volset d'initialiser les vols, volsdeparts et volsArrivé.
	 * @throws IOException
	 * @throws ErreurLignesSuccessivesVolsException
	 * 
	 * @author np
	 * @version 1.0 - 24/05/2016
	 */
	public static void initialise() throws IOException, ErreurLignesSuccessivesVolsException{
		try {
			entree = new BufferedReader(new FileReader("src\\fichiers\\vols.txt"));
			String line;
			String numVol;
			String numAvion="";
			String ville;
			int heures;
			int minutes;
			Horaire heureArrivee = null;
			Horaire heureDepart;
			String tempNumAvion="";
			StringTokenizer st;
			VolDepart monVolDepart=null;
			VolArrivee monVolArrivee=null;
			Passage monPassage;

			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je vérifie que ma ligne soit bien égale à 5 pour être sur de mon fichier d'entrée
				if(st.countTokens() == 5){
					numVol = st.nextToken();
					heures = Integer.parseInt(st.nextToken());	
					minutes = Integer.parseInt(st.nextToken());
					ville = st.nextToken();	
					numAvion = st.nextToken();
					//on stocke l'heure d'arrivée pour vérifier les horaires d'écart
					heureArrivee=new Horaire(heures,minutes);
					monVolArrivee = new VolArrivee(numVol, heureArrivee, ville, Avion.find(numAvion));
					//je stocke le nom de l'avion pour pouvoir vérifier que son vol départ est présent
					tempNumAvion=numAvion;
					//On lit la ligne suivante, qui doit être un vol départ
					//On vérifie que la ligne suivante n'est pas vide
					if((line = entree.readLine()) != null) {
						// je stocke les mots de la ligne 
						st = new StringTokenizer(line);
						// je vérifie que ma ligne soit bien égale à 5 pour être sur de mon fichier d'entrée
						if(st.countTokens() == 5){
							numVol = st.nextToken();
							heures = Integer.parseInt(st.nextToken());	
							minutes = Integer.parseInt(st.nextToken());
							ville = st.nextToken();	
							numAvion = st.nextToken();
							//je Vérifie que c'est bien le départ du vol précédant
							if (tempNumAvion.equals(numAvion)) {
								heureDepart = new Horaire(heures,minutes);
								monVolDepart = new VolDepart(numVol, heureDepart, ville,  Avion.find(numAvion));
								//Contructeur du passage (seuleument apèrs que l'on est construit le vol arrivee et le vol départ
								monPassage = new Passage(monVolArrivee, monVolDepart);
								//On rajoute le passage dans vol arrivée et vol départ
								monVolDepart.setLePassage(monPassage);
								monVolArrivee.setLePassage(monPassage);		
								//On vérifie l'écart de temps entre 2 avions
								if (heureDepart.retrait(Passage.getDuree()).compareTo(heureArrivee)<0) {
									System.out.println("L'écart minimum entre l'heure d'arrivée et de départ du vol '"+ numVol +"' n'est pas suffisant!") ;
								}
							} else {
								//il manque le départ de l'avion 
								throw new ErreurLignesSuccessivesVolsException(ErreurLignesSuccessivesVolsException.VOL_DEPART, numAvion);
							}
						}
					} else {
						//il manque le départ du dernier avion
						throw new ErreurLignesSuccessivesVolsException(ErreurLignesSuccessivesVolsException.VOL_DEPART, numAvion);
					}

				}
			}
			// je ferme mon fichier
			entree.close();
		}
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des vols introuvable");
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @return le numéro du vol
	 */
	public String getNumVol(){return this.numVol;}

	/**
	 * 
	 * @return le boolean  qui indique si le vol est annulé (true ) ou pas (false)
	 */
	public boolean isAnnule(){return this.estAnnule;}

	/**
	 * 
	 * @return l'avion associé au vol
	 */
	public Avion getLAvion(){return this.lAvion;}

	/**
	 * 
	 * @return le passage associé au vol
	 */
	public Passage getLePassage(){return this.lePassage;}

	/**
	 * Rempli le passage de l'avion
	 * @param p lePassage associé au vol
	 */
	public void setLePassage(Passage p){lePassage=p;}

	/**
	 * Cette méthode retourne une chaine de caractères pour permettre d'afficher la liste des vols.
	 * @return
	 */
	public static String builtChaineVols(){
		Iterator<Vol> val = lesVols.values().iterator();
		String str = "------ Les vols  ------" + " \n";
		while(val.hasNext()){
			Vol monVol = val.next();

			if (monVol.isAnnule()){
				str += " Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : oui, Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arrivé.":", vol départ.")+". \n";
			} else {
				str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : non, Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arrivé.":"vol départ.")+". \n";
			}

		}
		return str;
	}
	/**
	 * Retourne la chaine qui contient l'affichage d'un vol
	 */
	public String toString(){
		return  "Numéro du vol : " + this.getNumVol() + (this.isAnnule()?"vol Annulé : non":"vol Annulé : oui") + " , Numéro de l'avion : " + this.getLAvion().getImmat() +", Type : "+(this.getClass().equals(VolArrivee.class)?"vol arrivé.":"vol départ.")+ " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";

	}


	/**
	 * Cette méthode affiche la chaine de caractères qui contient la liste des vols.
	 */
	public static void afficherLesVols(){System.out.println(Vol.builtChaineVols());}

	/**
	 * 
	 * @return le parking associé au passage 
	 */
	public Parking getLeParking(){return this.getLePassage().getLeParking();}

	/***
	 * 
	 * @return String le nom du parking associé au passage
	 */
	public String getLeNomDuParking(){return this.getLeParking().getNom();}

	/**
	 * 
	 * @return la porte associée au vol
	 */
	public Porte getLaPorte(){return this.getLeParking().getLaPorte();}

	/**
	 * 
	 * @return le nom associé à la porte
	 */
	public String getLeNomDeLaPorte(){return this.getLaPorte().getNom();}



	/**
	 * Méthode getLeHall.
	 * Cette méthode récupère le hall associé à la porte
	 * @return le hall associé à la porte
	 * @author lb
	 * @version 1.0 - 23/05/2016
	 */
	public Hall getLeHall(){return this.getLaPorte().getLeHall();}

	/**
	 * Méthode getLeNomDuHall.
	 * Cette méthode récupère le nom du hall
	 * @return le nom du hall associé à la porte
	 * @author lb
	 * @version 1.0 - 23/05/2016
	 */
	public String getLeNomDuHall(){return this.getLeHall().getNom();}

	/**
	 * Methode getlesVols
	 * getter de la liste des vols.
	 * @return hashmap
	 * @author lb
	 * @version 1.0 - 20/05/2016
	 */
	public static HashMap<String, Vol> getlesVols(){
		return lesVols;
	}

	/**
	 * Méthode getVolsByHall.
	 * Cette méthode retourner hashMap des vols filtré selon le nom du hall passé en paramètre
	 * @author ap
	 * @params String n : le nom du hall, en string
	 * @return : la map des vols filtrés
	 * @version 1.0 - 24/05/2016
	 */
	public static HashMap<String, Vol> getVolsByHall(String n){
		Hall monHall = Hall.find(n);
		return getVolsByHall(monHall);
	}

	/**
	 * Méthode getVolsByHall.
	 * Cette méthode retourner hashMap des vols filtré selon le hall passé en paramètre
	 * @author ap
	 * @params Hall h : l'objet hall sur lequel on filtre
	 * @return : la map des vols filtrés
	 * @version 1.0 - 24/05/2016
	 */
	public static LinkedHashMap<String, Vol> getVolsByHall(Hall h){
		LinkedHashMap<String, Vol> maHashMap = new LinkedHashMap<String, Vol>();
		Iterator<String> it = lesVols.keySet().iterator();
		Vol monVol;
		while(it.hasNext()){
			String key = (String) it.next();
			monVol = lesVols.get(key);
			if(monVol.getLeHall().equals(h)){
				maHashMap.put(key, monVol);
			}
		}
		return maHashMap;
	}

	/**
	 * Méthode getHoraire.
	 * Cette méthode retourner l'horaire du vol.
	 * @author ap
	 * @return Horaire h : l'horaire du vol actuel
	 * @version 1.0 - 24/05/2016
	 */
	public Horaire getHoraire() {
		return this.getHoraire();
	}

	/**
	 * Méthode supprimerVol.
	 * Cette méthode supprime le vol par la clé passée en parametre
	 * @author lb
	 * @params Object key : la clé du vol à supprimer
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - 06/06/2016 by np : Suppresion d'un vol - 2 cas : arrivée ou départ, si on annule juste un vol départ, on met l'avion dans un hangar
	 * @version 3.0 - 07/06/2016 by ap : Modification pour coller avec les modifs 
	 */
	public static void supprimerVol(Object key) {
		
			String numVol = (String) key;
			Vol monVol = Vol.getLeVol(numVol);
			//if vol d'arrivée
			if(monVol.getClass().equals(VolArrivee.class)){
				// on supprime l'arrive et le depart
				//supprimer le passage sur la liste des passages d'un parking
				monVol.getLePassage().getLeParking().supprimerPassage(monVol.getLePassage());
				//supprimer le passage
				monVol.getLePassage().supprimerLePassage();
				//annuler vol arrivée
				monVol.annulerLeVol();
				//annuler vol départ s'il existe encore
				if(monVol.getLePassage() .getMonVolDepart()!= null){
					monVol.getLePassage().getMonVolDepart().annulerLeVol();
				}
			} else {
				//if vol départ on met just le vol départ à annulé, 
				monVol.annulerLeVol();
				monVol.getLePassage().annulerDepart();
			}
			//Passage.afficherLesPassages();
			
	}


	/**
	 * 
	 * @param temps : Duree de retard du vol
	 * @throws RetardTropTardException (indique un msg d'erreur qui dit qu'un avion ne peux pas partir apres 23h59
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * @version 1.1 - 29/05/2016 by np : changement des paramètres pris en compte pour le graphique
	 * @version 2.0 - 07/06/2016 by ap : reprise de la méthode
	 * @throws ParkingIndispoException 
	 */
	public static void retarder(String min, Object key) throws RetardTropTardException, ParkingIndispoException, ValeurRetardException, NumberFormatException{
		int minutes = Integer.parseInt(min);
		if(minutes > 1440 || minutes < 0){
			throw new ValeurRetardException(minutes);
		}
		Vol monVol = Vol.getLeVol((String) key);
		Duree temps = new Duree(minutes);
		int minuteDepart = 0;

	//	System.out.println(monVol.getClass().equals(VolArrivee.class));
		// Je recupere mes horaires actuels
		Horaire monHD = monVol.getLePassage().getHeureDepart();
		Horaire monHA = monVol.getLePassage().getHeureArrivee();
		Horaire newHA, newHD;
		// Je suis dans un vol d'arrivée
		if(monVol.getClass().equals(VolArrivee.class)==true){
			// Je commence par calculer mes nouveaux horaires
			newHA = monHA.ajout(temps);
			// Je verifie si j'ai pas sauté un jour avec la durée rentrée 
			if(newHA.compareTo(monHA) < 0){
				throw new RetardTropTardException(RetardTropTardException.VOL_ARRIVEE);
			} else {
				// ok je peux continuer
				// je regarde si mon nouvel horaire d'arrivé est après mon ancien horaire de départ
				if(monHD.compareTo(newHA) <= Passage.getEcart().dureeEnMinutes()){
					// ici, mon équart est insuffisant, donc je dois repousser le vol de départ
					// Mon nouvel horaire de départ sera : 
					newHD = newHA.ajout(Passage.getEcart());
					// Je recup le nombre de minutes de décalle que je viens de provoquer sur le vol de départ
					minuteDepart = newHD.compareTo(monHD);
				} else {
					// Pas de soucis, l'horaire de départ peut rester le même
					newHD = monHD;
				}
			}
		// Je suis dans un vol de départ
		} else {
			// Je dois juste modifier l'horaire de départ
			newHD = monVol.getLePassage().getHeureDepart().ajout(temps);
			// celui la ne change pas
			newHA = monVol.getLePassage().getHeureArrivee();
		}
		// Je regarde maintenant si j'ai pas sauté une journée sur le vol de départ aussi
		if(newHD.compareTo(monHD) < 0){
			throw new RetardTropTardException(RetardTropTardException.VOL_DEPART);
		} 
		// Je vais maintenant chercher un parking disponible pour ces nouveaux horaires
		boolean find = false;
		if(monVol.getLePassage().getLeParking().parkingTjrsOk(monVol.getLePassage(), newHA, newHD)){
//			System.out.println("tjrs dispo");
			find = true;
			// Je rajoute mon passage dans l'ordre du coup : il a ete suppr de l'arraylist dans parkingTjrsDispo
				// mais le lien dans l'autre sens n'a pas été rompu
			monVol.getLePassage().getLeParking().addPassage(monVol.getLePassage());
		} else {
//			System.out.println("non, plus dispo !");
			// Je cherche un parking de libre
			Parking parkingLibre = Parking.getParkingDispo(new TrancheHoraire(newHA, newHD), monVol.getLAvion());
//			System.out.println("le nouveau sera " + parkingLibre);
			if(parkingLibre != null){
				find = true;
				parkingLibre.addPassage(monVol.getLePassage());
				monVol.getLePassage().setLeParking(parkingLibre);
			}
		}
		
		
		// Si j'ai trouvé mon nouveau parking, bingo, je peux changer mes horaires, sinon, je leve une exception
		if(find){
			monVol.getLePassage().getMonVolArrivee().decalerHeureArrivee(newHA);
			monVol.getLePassage().getMonVolDepart().decalerHeureDepart(newHD);
			
			// je peux mettre les retards aussi
			monVol.ajouterRetard(minutes);
			// Je regarde si minuteDepart != 0 : dans ce cas, je suis dans un vol d'arrivée, et j'ai du aussi poussée mon vol de départ, donc j'ajoute le retard
			if(minuteDepart != 0){
				monVol.getLePassage().getMonVolDepart().ajouterRetard(minuteDepart);
			}
		} else {
			// Je lève une exception, mais je ne touche pas a mon passage : je garantie l'intégrité de mes données
			throw new ParkingIndispoException(monVol);
		}
	}


	/**
	 * 
	 * @param num : String qui correspond au numéro de l'avion
	 * @author np
	 * @version 1.0 - 25/05/2016
	 * @return Le vol qui correspond à l'ID passé en paramètres
	 */
	public static Vol getLeVol(String num){
		return lesVols.get(num);
	}
	
	/**
	 * Passe l'état d'un vol comme annulé
	 * @version 1.0 - 06/06/2016
	 * @author np
	 */
	public void annulerLeVol(){
		this.estAnnule=true;
	}
	
	/**
	 * Méthode getRetard.
	 * Méthode qui retourne le retard du vol
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public int getRetard() {
		return this.retard;
	}
	
	/**
	 * Méthode ajouterRetard.
	 * Méthode qui ajoute du retard au vol
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public void ajouterRetard(int m) {
		this.retard = this.retard + m;
	}
	
	/**
	 * méthode ajouterOrdre.
	 * Cette méthode va ajouter dans l'ordre le vol, dans la hashmap
	 * (static void).
	 * @author ap
	 * @param Vol v: le vol a ajouter
	 * @version 1.0 - 07/06/2016
	 */
	public static void ajouterOrdre(Vol v){
		boolean find = false;
		int cpt = 0;
		Horaire monHoraire = v.getHoraire();
		Horaire horaireCourant= null;
		// Je cherche l'indice ou ajouter mon vol avant de l'ajouter
		Iterator<String> it = lesVols.keySet().iterator();
		while(it.hasNext() && !find){
			String key = it.next();
			 horaireCourant = lesVols.get(key).getHoraire();
			if(horaireCourant.compareTo(monHoraire) > 0){
				find = true;
			} else {
				cpt++;
			}
		}
		int cptControl = 0;
		HashMap<String, Vol> copieHashMap = (HashMap<String, Vol>) lesVols.clone();
		lesVols.clear();
		Iterator<String> itInsert = copieHashMap.keySet().iterator();
		// j'insere les premiers vols
		while(itInsert.hasNext() && cptControl < cpt){
			String key = itInsert.next();
			lesVols.put(key, copieHashMap.get(key));
			cptControl++;
		}
		// J'insere mon nouveau vol
		lesVols.put(v.getNumVol(), v);
		// j'insere le reste de mes vols
		while(itInsert.hasNext()){
			String key = itInsert.next();
			lesVols.put(key, copieHashMap.get(key));
		}
	}
}
