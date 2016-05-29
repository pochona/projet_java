package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import utilitaires.Horaire;
import utilitaires.TrancheHoraire;

public class Parking {

	/**
	 * désignation
	 */
	private String nom ;
	
	/**
	 * zone associée au parking
	 */
	private Zone zone;
	
	/**
	 * porte associée au parking
	 */
	private Porte porte;
	
	/**
	 * ArrayList qui contient les passages 
	 */
	private ArrayList<Passage> lesPassages;
	
	/**
	 * ArrayList qui contient toutes les parkings 
	 */
	private static ArrayList<Parking> lesParkings = new ArrayList<Parking>();
	
	public static final int PARKING_CONTACT = 1;
	public static final int PARKING_SANS_CONTACT = 2;

	/**
	 * Constructeur de parking
	 * @param n : nom du parking
	 */
	public Parking(String n){
		this.nom = n;
		this.lesPassages = new ArrayList<Passage>();
		lesParkings.add(this);
		
	}
	
	/**
	 * Constructeur de parking 
	 * @param n : nom du parking
	 * @param z : Zone associée
	 * @param p : Porte associée
	 */
	public Parking(String n, Zone z, Porte p){
		this.nom = n;
		this.zone = z;
		this.porte = p;
		this.lesPassages = new ArrayList<Passage>();
		lesParkings.add(this);
	}
	
	/**
	 * Méthode initialise
	 * 
	 * Cette méthode static permet d'ouvrir le fichier des portes et d'initialiser les parkings.
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\parkings.txt"));
			String line;
			String nom;
			String porte;
			String zone;
			Zone laZone = null;
			Porte laPorte = null;
			Parking leParking = null;
			StringTokenizer st;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je vérifie que ma ligne soit bien égale à 3 pour être sur de mon fichier d'entrée

				
				if(st.countTokens() == 3){
					nom = st.nextToken();
					porte = st.nextToken();	
					zone = st.nextToken();
					laZone = Zone.find(zone);
					laPorte = Porte.find(porte);
					// je check si la porte associée au parking est une porte contact ou sans contact
					// cela permettra de determiner si je dois faire un parking contact ou sans contact
					if(Porte.getType(porte) == Porte.PORTE_CONTACT){
						leParking = new ParkingContact(nom, laZone, laPorte);
					} else {
						leParking = new ParkingSansContact(nom, laZone, laPorte);
					}
					laPorte.ajouterParking(leParking);
					laZone.ajouterParking(leParking);
				}
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des parkings introuvables");
			System.out.println(e);
		}
	}

	/**
	 * Méthode afficherLesParkings.
	 * Cette méthode permet de parcourir les instances de parkings pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesParkings(){
		Iterator<Parking> it = lesParkings.iterator();
		String str = "------ Les parkings ------" + " \n";
		while(it.hasNext()){
			Parking monParking = it.next();
			str += monParking.toString() + " \n";
		}
		return str;
	}

	/**
	 * Méthode getNom.
	 * Cette méthode permet de retourner le nom du parking courant
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Méthode find.
	 * Cette méthode permet de retourner le parking ayant le nom passé en paramètre.
	 * @author ap
	 * @param String n : nom du parking
	 * @version 1.0 - 01/05/2016
	 * @return Parking leParking
	 */
	public static Parking find(String n){
		Parking leParking = null;
		for (Parking monParking : lesParkings) {
			if(monParking.getNom().equals(n)){
				leParking = monParking;
			}
		}
		return leParking;
	}
	
	/**
	 * Méthode toString.
	 * Cette méthode permet d'afficher le parking courant
	 * @author ap
	 * @return : la chaine de caractère a afficher
	 * @version 1.0 - 01/05/2016
	 */
	public String toString(){
		return "Le parking " + this.getNom() + (this.getClass().equals(ParkingSansContact.class)?", Parking sans contact":", Parking contact");
	}
	
	/**
	 * Méthode getParkingDispo.
	 * Cette méthode static permet de retrourner un parking dispo aux horaires communiquées
	 * @author ap
	 * @param TrancheHoraire th : horaire du vol depart et arrivé
	 * @return : un parking dispo
	 * @version 1.0 - 11/05/2016
	 * @version 2.0 - 20/05/2016 by ap : Modif de la version de base
	 * @version 3.0 - 29/05/2016 by np : Traite les avions de grande taille
	 */
	public static Parking getParkingDispo(TrancheHoraire th, Avion a){
		Parking leParking = null;
		// On checkera après le trairement grande taille ou non 
		 if(a.isGrandeTaille()){
				ArrayList<ParkingSansContact> listeParkingSSContact = ParkingSansContact.getLesParkingsSansContact();
				Iterator iterat = listeParkingSSContact.iterator();
				while(iterat.hasNext() && leParking == null){
					Parking monParking = (Parking) iterat.next();
					if(monParking.isDispo(th)){
						leParking = monParking;
					}
				}
		} else {
		Iterator it = lesParkings.iterator();
		while(it.hasNext() && leParking == null){
			Parking monParking = (Parking) it.next();
			if(monParking.isDispo(th)){
				leParking = monParking;
			}
		}
		}
		return leParking;
	}
	
	/**
	 * Méthode isDispo.
	 * Cette méthode permet de verifier si un parking est dispo aux horaires communiquées ou non.
	 * @author ap
	 * @param TrancheHoraire th : la tranche horaire qu'on veut placer
	 * @return true or false.
	 * @version 1.0 - 20/05/2016
	 * @version 1.2 - 22/05/2016 by ap : correction de la création de la tranche horaire, petite erreur 
	 */
	public boolean isDispo(TrancheHoraire th){
		System.out.println("+++++ Tranche a caser : " + th + " +++ sur le parking : " + this.getNom() + "++++++");
		boolean dispo = false;
		boolean stop = false;
		Passage monPassage, lastPassage = null;
		TrancheHoraire maTh;
		// Je vais devoir parcourir la liste des passages du parking
		Iterator it = this.lesPassages.iterator();
		// si la liste de mes passages est vide, je peux directement prendre le parking
		if(!it.hasNext()){
//System.out.println("dispo direct");
			dispo = true;
		}

		// Je continue tant que j'ai pas prouvé que le parking était dispo, et que j'ai des passages suivant
		while (dispo == false && it.hasNext() && stop == false){
			// Je recupere la trancheHoraire de mon passage courant
			monPassage = (Passage) it.next();
//System.out.println(monPassage.getHeureDepart());
//System.out.println(th.getDebutTrancheHoraire());
//System.out.println(monPassage.getHeureDepart().compareTo(th.getDebutTrancheHoraire()));
			// J'avance jusqu'a trouver le moment ou le départ de mon passage sera plus grand que l'arrivée du courant
			if(monPassage.getHeureDepart().compareTo(th.getDebutTrancheHoraire()) >= 0){
				// je suis dans le bon créneaux, donc même si j'arrive pas a caser mon parking
				// , je peux arreter ma boucle
				stop = true;
//System.out.println("if 1");
				// Si je le trouve, je fais une tranche horaire entre la départ du last, et l'arrivée du courant
				
				// Pour ca, je regade d'abord si mon dernier passage est bien rempli
				if(lastPassage != null){
//System.out.println("if 2");
					maTh = new TrancheHoraire(lastPassage.getHeureDepart(), monPassage.getHeureArrivee());
					// maTh contient la tranche Horaire dispo la plus grande avec au moins le départ de mon vol dedans
					// je regarde maintenant si mon horaire rentre en entier dans maTh ou non
//System.out.println("maTh : " + maTh);
//System.out.println("th : " + th);
//System.out.println("contient : " + maTh.contient(th));
					if(maTh.contient(th)){
//System.out.println("if 3");
						dispo = true;
					}
				} else {
//System.out.println("else 2");
					// S'il est nul c'est que le premier passage a au moins l'arrivé après le départ du courant
					// Je regarde donc si l'arrivée de mon courant est avant l'arrivé de ce passage
 //System.out.println(monPassage.getHeureArrivee());
 //System.out.println(th.getFinTrancheHoraire());
 //System.out.println(th.getFinTrancheHoraire().compareTo(monPassage.getHeureArrivee()));
					if(th.getFinTrancheHoraire().compareTo(monPassage.getHeureArrivee()) <= 0){
//System.out.println("ok if 4");
						dispo = true;
					}
				}
			} else {
//System.out.println("else 1");
				// je n'ai rien ensuite, et mon arrivée courante est après le départ du dernier testé
				// donc je vérifie si j'en ai ensuite
				if(!it.hasNext()){
					// je n'ai rien ensuite, donc mon vol peut etre placé
//System.out.println("dispo after if 5");
					dispo = true;
				}
			}
			
			// Je sauvegarde le dernier passage pour pouvoir le manipuler ensuite
			lastPassage = monPassage;
		}
System.out.println(this + " est dispo ? " + dispo);
		return dispo;
	}
	
	
	/**
	 * Méthode addPassage.
	 * Cette méthode permet d'ajouter un passage dans la liste des passages du parking
	 * @author ap
	 * @param Passage p : le passage à ajouter
	 * @version 1.0 - 21/05/2016
	 * @version 2.0 - 22/05/2016 by ap : l'ajout s'effectue maintenant dans l'ordre
	 */
	public void addPassage(Passage p){
	
		int cpt = 0;
		boolean find = false;
		
		Iterator it = this.lesPassages.iterator();
		Horaire monHoraire = p.getHeureArrivee();
		Passage lePassage;
		Horaire testHoraire;

		while(it.hasNext() && !find){
			
			lePassage = (Passage) it.next();
			testHoraire = lePassage.getHeureArrivee();

			if(testHoraire.compareTo(monHoraire) > 0){
				find = true;
			} else {
				cpt++;
			}
		}
		this.lesPassages.add(cpt, p);
		System.out.println("Apres tri : ");
		this.afficherLesPassages();
	}
	
	
	/**
	 * @todo : la doc
	 * @return
	 */
	public Porte getLaPorte(){return this.porte;}

	
	/**
	 * Méthode afficherLesPassages.
	 * Cette méthode permet d'afficher tous les passages d'un parking
	 * @author ap
	 * @version 1.0 - 22/05/2016
	 */
	public void afficherLesPassages(){
		System.out.println("Parking : " + this.getNom());
		Iterator it = this.lesPassages.iterator();
		Passage monPassage;
		while(it.hasNext()){
			monPassage = (Passage) it.next();
			System.out.println(monPassage);
		}
	}
	
	/**
	 * 
	 * @return ArrayList<Passage> : retourne les passages d'un parking 
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public ArrayList<Passage> getLesPassages(){
		return lesPassages;
	}
	/**
	 * 
	 * @return boolean : Ce booléen indique si, après avoir modifié les horaires d'un passage, il n'y a pas de conflits avec le passage suivant du parking
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public boolean parkingTjrsOk(Passage monPassage){
		boolean ok=true;
		int position, temp=0;
		position=lesPassages.indexOf(monPassage);
		if (lesPassages.size()>position+1){
			//alors ce n'est pas le dernier passage du parking, il faut donc vérifier
			//sinon c'etait le dernier passage, donc pas de soucis 
			Passage passageSuivant=lesPassages.get(position+1);
			Horaire heureArriveDuSuivant=passageSuivant.getMonVolArrivee().getHoraire();
			temp = heureArriveDuSuivant.horaireEnMinutes()-monPassage.getMonVolDepart().getHoraire().horaireEnMinutes();
		  if (temp<=0){
			//l'horaire du vol départ est après l'heure d'arrivée du suivant --> Parking n'est plus OK !
			  ok = false;
		  } 
		}
		return ok;
	}
}
