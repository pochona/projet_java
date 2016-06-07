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
	 * R�f�rence du vol
	 */
	private String numVol;

	/**
	 * Indique si le vol est annul� (true), false sinon.
	 */
	private boolean estAnnule;

	/**
	 * Le num�ro de l'avion
	 */
	private Avion lAvion;

	/**
	 * Passage associ� au vol0
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
	 * @param avion : string num�ro de l'avion
	 */
	public Vol(String num, Avion avion){
		this.numVol =num;
		this.estAnnule=false;
		this.lAvion=avion;
		this.retard=0;	
		// On ajoutera a la fin dans la hashmap, car actuellement les horaires ne sont pas renseignes, car elles sont dans les classes sp�cialis�es
	}

	/**
	 * Cette m�thode static permet d'ouvrir le fichier des volset d'initialiser les vols, volsdeparts et volsArriv�.
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
				// je v�rifie que ma ligne soit bien �gale � 5 pour �tre sur de mon fichier d'entr�e
				if(st.countTokens() == 5){
					numVol = st.nextToken();
					heures = Integer.parseInt(st.nextToken());	
					minutes = Integer.parseInt(st.nextToken());
					ville = st.nextToken();	
					numAvion = st.nextToken();
					//on stocke l'heure d'arriv�e pour v�rifier les horaires d'�cart
					heureArrivee=new Horaire(heures,minutes);
					monVolArrivee = new VolArrivee(numVol, heureArrivee, ville, Avion.find(numAvion));
					//je stocke le nom de l'avion pour pouvoir v�rifier que son vol d�part est pr�sent
					tempNumAvion=numAvion;
					//On lit la ligne suivante, qui doit �tre un vol d�part
					//On v�rifie que la ligne suivante n'est pas vide
					if((line = entree.readLine()) != null) {
						// je stocke les mots de la ligne 
						st = new StringTokenizer(line);
						// je v�rifie que ma ligne soit bien �gale � 5 pour �tre sur de mon fichier d'entr�e
						if(st.countTokens() == 5){
							numVol = st.nextToken();
							heures = Integer.parseInt(st.nextToken());	
							minutes = Integer.parseInt(st.nextToken());
							ville = st.nextToken();	
							numAvion = st.nextToken();
							//je V�rifie que c'est bien le d�part du vol pr�c�dant
							if (tempNumAvion.equals(numAvion)) {
								heureDepart = new Horaire(heures,minutes);
								monVolDepart = new VolDepart(numVol, heureDepart, ville,  Avion.find(numAvion));
								//Contructeur du passage (seuleument ap�rs que l'on est construit le vol arrivee et le vol d�part
								monPassage = new Passage(monVolArrivee, monVolDepart);
								//On rajoute le passage dans vol arriv�e et vol d�part
								monVolDepart.setLePassage(monPassage);
								monVolArrivee.setLePassage(monPassage);		
								//On v�rifie l'�cart de temps entre 2 avions
								if (heureDepart.retrait(Passage.getDuree()).compareTo(heureArrivee)<0) {
									System.out.println("L'�cart minimum entre l'heure d'arriv�e et de d�part du vol '"+ numVol +"' n'est pas suffisant!") ;
								}
							} else {
								//il manque le d�part de l'avion 
								throw new ErreurLignesSuccessivesVolsException(ErreurLignesSuccessivesVolsException.VOL_DEPART, numAvion);
							}
						}
					} else {
						//il manque le d�part du dernier avion
						throw new ErreurLignesSuccessivesVolsException(ErreurLignesSuccessivesVolsException.VOL_DEPART, numAvion);
					}

				}
			}
			// je ferme mon fichier
			entree.close();
		}
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des vols introuvable");
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @return le num�ro du vol
	 */
	public String getNumVol(){return this.numVol;}

	/**
	 * 
	 * @return le boolean  qui indique si le vol est annul� (true ) ou pas (false)
	 */
	public boolean isAnnule(){return this.estAnnule;}

	/**
	 * 
	 * @return l'avion associ� au vol
	 */
	public Avion getLAvion(){return this.lAvion;}

	/**
	 * 
	 * @return le passage associ� au vol
	 */
	public Passage getLePassage(){return this.lePassage;}

	/**
	 * Rempli le passage de l'avion
	 * @param p lePassage associ� au vol
	 */
	public void setLePassage(Passage p){lePassage=p;}

	/**
	 * Cette m�thode retourne une chaine de caract�res pour permettre d'afficher la liste des vols.
	 * @return
	 */
	public static String builtChaineVols(){
		Iterator<Vol> val = lesVols.values().iterator();
		String str = "------ Les vols  ------" + " \n";
		while(val.hasNext()){
			Vol monVol = val.next();

			if (monVol.isAnnule()){
				str += " Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+". \n";
			}

		}
		return str;
	}
	/**
	 * Retourne la chaine qui contient l'affichage d'un vol
	 */
	public String toString(){
		return  "Num�ro du vol : " + this.getNumVol() + (this.isAnnule()?"vol Annul� : non":"vol Annul� : oui") + " , Num�ro de l'avion : " + this.getLAvion().getImmat() +", Type : "+(this.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";

	}


	/**
	 * Cette m�thode affiche la chaine de caract�res qui contient la liste des vols.
	 */
	public static void afficherLesVols(){System.out.println(Vol.builtChaineVols());}

	/**
	 * 
	 * @return le parking associ� au passage 
	 */
	public Parking getLeParking(){return this.getLePassage().getLeParking();}

	/***
	 * 
	 * @return String le nom du parking associ� au passage
	 */
	public String getLeNomDuParking(){return this.getLeParking().getNom();}

	/**
	 * 
	 * @return la porte associ�e au vol
	 */
	public Porte getLaPorte(){return this.getLeParking().getLaPorte();}

	/**
	 * 
	 * @return le nom associ� � la porte
	 */
	public String getLeNomDeLaPorte(){return this.getLaPorte().getNom();}



	/**
	 * M�thode getLeHall.
	 * Cette m�thode r�cup�re le hall associ� � la porte
	 * @return le hall associ� � la porte
	 * @author lb
	 * @version 1.0 - 23/05/2016
	 */
	public Hall getLeHall(){return this.getLaPorte().getLeHall();}

	/**
	 * M�thode getLeNomDuHall.
	 * Cette m�thode r�cup�re le nom du hall
	 * @return le nom du hall associ� � la porte
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
	 * M�thode getVolsByHall.
	 * Cette m�thode retourner hashMap des vols filtr� selon le nom du hall pass� en param�tre
	 * @author ap
	 * @params String n : le nom du hall, en string
	 * @return : la map des vols filtr�s
	 * @version 1.0 - 24/05/2016
	 */
	public static HashMap<String, Vol> getVolsByHall(String n){
		Hall monHall = Hall.find(n);
		return getVolsByHall(monHall);
	}

	/**
	 * M�thode getVolsByHall.
	 * Cette m�thode retourner hashMap des vols filtr� selon le hall pass� en param�tre
	 * @author ap
	 * @params Hall h : l'objet hall sur lequel on filtre
	 * @return : la map des vols filtr�s
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
	 * M�thode getHoraire.
	 * Cette m�thode retourner l'horaire du vol.
	 * @author ap
	 * @return Horaire h : l'horaire du vol actuel
	 * @version 1.0 - 24/05/2016
	 */
	public Horaire getHoraire() {
		return this.getHoraire();
	}

	/**
	 * M�thode supprimerVol.
	 * Cette m�thode supprime le vol par la cl� pass�e en parametre
	 * @author lb
	 * @params Object key : la cl� du vol � supprimer
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - 06/06/2016 by np : Suppresion d'un vol - 2 cas : arriv�e ou d�part, si on annule juste un vol d�part, on met l'avion dans un hangar
	 * @version 3.0 - 07/06/2016 by ap : Modification pour coller avec les modifs 
	 */
	public static void supprimerVol(Object key) {
		
			String numVol = (String) key;
			Vol monVol = Vol.getLeVol(numVol);
			//if vol d'arriv�e
			if(monVol.getClass().equals(VolArrivee.class)){
				// on supprime l'arrive et le depart
				//supprimer le passage sur la liste des passages d'un parking
				monVol.getLePassage().getLeParking().supprimerPassage(monVol.getLePassage());
				//supprimer le passage
				monVol.getLePassage().supprimerLePassage();
				//annuler vol arriv�e
				monVol.annulerLeVol();
				//annuler vol d�part s'il existe encore
				if(monVol.getLePassage() .getMonVolDepart()!= null){
					monVol.getLePassage().getMonVolDepart().annulerLeVol();
				}
			} else {
				//if vol d�part on met just le vol d�part � annul�, 
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
	 * @version 1.1 - 29/05/2016 by np : changement des param�tres pris en compte pour le graphique
	 * @version 2.0 - 07/06/2016 by ap : reprise de la m�thode
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
		// Je suis dans un vol d'arriv�e
		if(monVol.getClass().equals(VolArrivee.class)==true){
			// Je commence par calculer mes nouveaux horaires
			newHA = monHA.ajout(temps);
			// Je verifie si j'ai pas saut� un jour avec la dur�e rentr�e 
			if(newHA.compareTo(monHA) < 0){
				throw new RetardTropTardException(RetardTropTardException.VOL_ARRIVEE);
			} else {
				// ok je peux continuer
				// je regarde si mon nouvel horaire d'arriv� est apr�s mon ancien horaire de d�part
				if(monHD.compareTo(newHA) <= Passage.getEcart().dureeEnMinutes()){
					// ici, mon �quart est insuffisant, donc je dois repousser le vol de d�part
					// Mon nouvel horaire de d�part sera : 
					newHD = newHA.ajout(Passage.getEcart());
					// Je recup le nombre de minutes de d�calle que je viens de provoquer sur le vol de d�part
					minuteDepart = newHD.compareTo(monHD);
				} else {
					// Pas de soucis, l'horaire de d�part peut rester le m�me
					newHD = monHD;
				}
			}
		// Je suis dans un vol de d�part
		} else {
			// Je dois juste modifier l'horaire de d�part
			newHD = monVol.getLePassage().getHeureDepart().ajout(temps);
			// celui la ne change pas
			newHA = monVol.getLePassage().getHeureArrivee();
		}
		// Je regarde maintenant si j'ai pas saut� une journ�e sur le vol de d�part aussi
		if(newHD.compareTo(monHD) < 0){
			throw new RetardTropTardException(RetardTropTardException.VOL_DEPART);
		} 
		// Je vais maintenant chercher un parking disponible pour ces nouveaux horaires
		boolean find = false;
		if(monVol.getLePassage().getLeParking().parkingTjrsOk(monVol.getLePassage(), newHA, newHD)){
//			System.out.println("tjrs dispo");
			find = true;
			// Je rajoute mon passage dans l'ordre du coup : il a ete suppr de l'arraylist dans parkingTjrsDispo
				// mais le lien dans l'autre sens n'a pas �t� rompu
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
		
		
		// Si j'ai trouv� mon nouveau parking, bingo, je peux changer mes horaires, sinon, je leve une exception
		if(find){
			monVol.getLePassage().getMonVolArrivee().decalerHeureArrivee(newHA);
			monVol.getLePassage().getMonVolDepart().decalerHeureDepart(newHD);
			
			// je peux mettre les retards aussi
			monVol.ajouterRetard(minutes);
			// Je regarde si minuteDepart != 0 : dans ce cas, je suis dans un vol d'arriv�e, et j'ai du aussi pouss�e mon vol de d�part, donc j'ajoute le retard
			if(minuteDepart != 0){
				monVol.getLePassage().getMonVolDepart().ajouterRetard(minuteDepart);
			}
		} else {
			// Je l�ve une exception, mais je ne touche pas a mon passage : je garantie l'int�grit� de mes donn�es
			throw new ParkingIndispoException(monVol);
		}
	}


	/**
	 * 
	 * @param num : String qui correspond au num�ro de l'avion
	 * @author np
	 * @version 1.0 - 25/05/2016
	 * @return Le vol qui correspond � l'ID pass� en param�tres
	 */
	public static Vol getLeVol(String num){
		return lesVols.get(num);
	}
	
	/**
	 * Passe l'�tat d'un vol comme annul�
	 * @version 1.0 - 06/06/2016
	 * @author np
	 */
	public void annulerLeVol(){
		this.estAnnule=true;
	}
	
	/**
	 * M�thode getRetard.
	 * M�thode qui retourne le retard du vol
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public int getRetard() {
		return this.retard;
	}
	
	/**
	 * M�thode ajouterRetard.
	 * M�thode qui ajoute du retard au vol
	 * @author ap
	 * @version 1.0 - 06/06/2016
	 */
	public void ajouterRetard(int m) {
		this.retard = this.retard + m;
	}
	
	/**
	 * m�thode ajouterOrdre.
	 * Cette m�thode va ajouter dans l'ordre le vol, dans la hashmap
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
