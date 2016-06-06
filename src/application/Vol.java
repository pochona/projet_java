package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSpinnerUI;

import utilitaires.Duree;
import utilitaires.Horaire;

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
	private static HashMap<String, Vol> lesVols = new HashMap<String, Vol>();

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
		lesVols.put(num, this);
	}

	/**
	 * Cette m�thode static permet d'ouvrir le fichier des volset d'initialiser les vols, volsdeparts et volsArriv�.
	 * @throws IOException
	 * @throws ErreurLignesSuccessivesVols
	 * 
	 * @author np
	 * @version 1.0 - 24/05/2016
	 */
	public static void initialise() throws IOException, ErreurLignesSuccessivesVols{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\vols.txt"));
			int compteurVols=0;
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
								throw new ErreurLignesSuccessivesVols(ErreurLignesSuccessivesVols.VOL_DEPART, numAvion);
							}
						}
					} else {
						//il manque le d�part du dernier avion
						throw new ErreurLignesSuccessivesVols(ErreurLignesSuccessivesVols.VOL_DEPART, numAvion);
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
			/* 20/05/2016 by ap : version valide � remettre lorsque la m�thode d'attribution du parking sera ok
			if (monVol.getVolAnnule()==true){
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}*/
			if (monVol.isAnnule()){
				str += " Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+". \n";
			}
			/*if (monVol.getVolAnnule()==true){
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}*/
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
	public static HashMap<String, Vol> getVolsByHall(Hall h){
		HashMap maHashMap = new HashMap<String, Vol>();
		Iterator it = lesVols.keySet().iterator();
		Vol monVol;
		while(it.hasNext()){
			Object key = it.next();
			monVol = lesVols.get(key);
			if(monVol.getLeHall().equals(h)){
				maHashMap.put(key, monVol);
			}
		}
		return maHashMap;
	}

	public Horaire getHoraire() {
		Horaire h = null;
		if(this.getClass().equals(VolArrivee.class)){
			VolArrivee v = (VolArrivee) this;
			h = v.getHoraire();
		} else {
			VolDepart v = (VolDepart) this;
			h = v.getHoraire();
		}
		return h;

	}

	/**
	 * M�thode supprimerVol.
	 * Cette m�thode supprime le vol par la cl� pass�e en parametre
	 * @author lb
	 * @params Object key : la cl� du vol � supprimer
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - 06/06/2016 by np : Suppresion d'un vol - 2 cas : arriv�e ou d�part, si on annule juste un vol d�part, on met l'avion dans un hangar
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
				//annuler vol d�part
				monVol.getLePassage().getMonVolDepart().annulerLeVol();
			} else {
				//if vol d�part on met just le vol d�part � annul�, 
				monVol.annulerLeVol();
			}
			
	}


	/**
	 * M�thode modifierHeure.
	 * Cette m�thode retarde le vol par la cl� pass�e en parametre
	 * @author lb
	 * @params String m les minutes de retard, Object key la cl� du vol a retarder
	 * @version 1.0 - 27/05/2016
	 */
	public static void modifierHeure(String m, Object key){
		int minutes = Integer.parseInt(m);
		//System.out.println("Minutes: "+minutes+" Cl�: "+key);
		//TODO 
	}

	/**
	 * 
	 * @param temps : Duree de retard du vol
	 * @throws RetardTropTard (indique un msg d'erreur qui dit qu'un avion ne peux pas partir apres 23h59
	 * @author np
	 * @version 1.0 - 28/05/2016
	 * @version 1.1 - 29/05/2016 by np : changement des param�tres pris en compte pour le graphique
	 * @throws ParkingIndispo 
	 */
	public static void retarder(String min, Object key) throws RetardTropTard, ParkingIndispo{
		int m;
		Parking parkingLibre;
		int minutes = Integer.parseInt(min);
		
		String numVol = (String) key;
		Vol monVol = Vol.getLeVol(numVol);
		monVol.ajouterRetard(minutes);
		Duree temps = new Duree(minutes);
	//	System.out.println(monVol.getClass().equals(VolArrivee.class));
		
		
		
		
		
		if(monVol.getClass().equals(VolArrivee.class)==true){
			System.out.println(monVol.getClass().equals(VolArrivee.class));
			// c'est un vol d'arriv�e
			Horaire monHeureDepart = monVol.getLePassage().getMonVolDepart().getHoraire();
			Horaire monHeureArrivee = monVol.getLePassage().getMonVolArrivee().getHoraire();
			Horaire nouvelleHArrivee=monHeureArrivee.ajout(temps);
			Duree ecartnew = nouvelleHArrivee.retrait(monHeureDepart);
			m=monHeureDepart.horaireEnMinutes()-nouvelleHArrivee.horaireEnMinutes();

			if (m>=monVol.getLePassage().getEcart().dureeEnMinutes()){
				//l'�cart est suffisant (VOIR AVEC LAURA ET AMAURY si on dit que c'est ok si ecart = 0)
				// on d�cale juste l'heure d'arrivee
				monVol.getLePassage().getMonVolArrivee().decalerHeureArrivee(nouvelleHArrivee);
			} else {
				// l'ecart n'est pas suffisant, il faut alors d�caler les heures des vols arriv�e + d�part
				//mais d'abord on regarde que le d�part ne d�passe pas 23h59
				if (nouvelleHArrivee.compareTo(nouvelleHArrivee.ajout(temps))<0){
					monVol.getLePassage().getMonVolArrivee().decalerHeureArrivee(nouvelleHArrivee);
					int tempInt = m + monVol.getLePassage().getEcart().dureeEnMinutes();
					Duree tempDuree=new Duree(tempInt);
					monVol.getLePassage().getMonVolDepart().decalerHeureDepart(monVol.getLePassage().getMonVolDepart().getHoraire().ajout(tempDuree));
					//monVol.getLePassage().getMonVolDepart().decalerHeureDepart(nouvelleHArrivee.ajout(temps));
					//On regarde si le parking est toujours OK
					if (monVol.getLePassage().getLeParking().parkingTjrsOk(monVol.getLePassage())==false){
						//Il y a un probl�me d'horaire avec le parking suivant, il faut donc trouver un nouveau parking pour ce passage.
						parkingLibre = Parking.getParkingDispo(monVol.getLePassage().getTrancheHoraire(), monVol.getLAvion());
						//on supprime le passage de la liste des passages de l'ancien parking 
						monVol.getLePassage().getLeParking().supprimerPassage(monVol.getLePassage());
						//On met ce parking dans le passage
						monVol.getLePassage().setLeParking(parkingLibre);
						//on stocke le passage sur ce parking
						parkingLibre.addPassage(monVol.getLePassage());
						}
				} else {
					//sinon on d�clanche l'exception qui dit que le vol depart est trop tard
					throw new RetardTropTard(RetardTropTard.VOL_ARRIVEE);
				}
			}
		} else {
			//c'est un vol d�part
			Horaire monHeureDepart = monVol.getLePassage().getMonVolDepart().getHoraire();
			Horaire nouvelleHDepart=monHeureDepart.ajout(temps);
			if (monVol.getLePassage().getMonVolArrivee().getHoraire().compareTo(nouvelleHDepart)<0){
				monVol.getLePassage().getMonVolDepart().decalerHeureDepart(monHeureDepart.ajout(temps));
				//On regarde si le parking est toujours OK
				if (monVol.getLePassage().getLeParking().parkingTjrsOk(monVol.getLePassage())==false){
					Parking parkTemp=monVol.getLePassage().getLeParking();
					//Il y a un probl�me d'horaire avec le parking suivant, il faut donc trouver un nouveau parking pour ce passage.
					parkingLibre = Parking.getParkingDispo(monVol.getLePassage().getTrancheHoraire(), monVol.getLAvion());
					//on supprime le passage de la liste des passages de l'ancien parking 
					monVol.getLePassage().getLeParking().supprimerPassage(monVol.getLePassage());
					//On met ce parking dans le passage
					monVol.getLePassage().setLeParking(parkingLibre);
					if(parkingLibre != null){
						//on stocke le passage sur ce parking
						parkingLibre.addPassage(monVol.getLePassage());
					} else {
						throw new ParkingIndispo(monVol);
					}
				}
			} else {
				//sinon on d�clanche l'exception qui dit que le vol depart est trop tard
				throw new RetardTropTard(RetardTropTard.VOL_DEPART);
			}
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
}
