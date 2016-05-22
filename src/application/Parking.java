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
	 * d�signation
	 */
	private String nom ;
	
	/**
	 * zone associ�e au parking
	 */
	private Zone zone;
	
	/**
	 * porte associ�e au parking
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
	 * @param z : Zone associ�e
	 * @param p : Porte associ�e
	 */
	public Parking(String n, Zone z, Porte p){
		this.nom = n;
		this.zone = z;
		this.porte = p;
		this.lesPassages = new ArrayList<Passage>();
		lesParkings.add(this);
	}
	
	/**
	 * M�thode initialise
	 * 
	 * Cette m�thode static permet d'ouvrir le fichier des portes et d'initialiser les parkings.
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
				// je v�rifie que ma ligne soit bien �gale � 3 pour �tre sur de mon fichier d'entr�e

				
				if(st.countTokens() == 3){
					nom = st.nextToken();
					porte = st.nextToken();	
					zone = st.nextToken();
					laZone = Zone.find(zone);
					laPorte = Porte.find(porte);
					// je check si la porte associ�e au parking est une porte contact ou sans contact
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
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des parkings introuvables");
			System.out.println(e);
		}
	}

	/**
	 * M�thode afficherLesParkings.
	 * Cette m�thode permet de parcourir les instances de parkings pour les afficher
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
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le nom du parking courant
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * M�thode find.
	 * Cette m�thode permet de retourner le parking ayant le nom pass� en param�tre.
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
	 * M�thode toString.
	 * Cette m�thode permet d'afficher le parking courant
	 * @author ap
	 * @return : la chaine de caract�re a afficher
	 * @version 1.0 - 01/05/2016
	 */
	public String toString(){
		return "Le parking " + this.getNom() + (this.getClass().equals(ParkingSansContact.class)?", Parking sans contact":", Parking contact");
	}
	
	/**
	 * M�thode getParkingDispo.
	 * Cette m�thode static permet de retrourner un parking dispo aux horaires communiqu�es
	 * @author ap
	 * @param TrancheHoraire th : horaire du vol depart et arriv�
	 * @return : un parking dispo
	 * @version 1.0 - 11/05/2016
	 * @version 2.0 - 20/05/2016 by ap : Modif de la version de base
	 */
	public static Parking getParkingDispo(TrancheHoraire th){
		Parking leParking = null;
		/* On checkera apr�s le trairement grande taille ou non 
		 if(a.isGrandeTaille()){
			
		} else {
			
		}*/


		Iterator it = lesParkings.iterator();
		while(it.hasNext() && leParking == null){
			Parking monParking = (Parking) it.next();
			monParking.afficherLesPassages();
			if(monParking.isDispo(th)){
				leParking = monParking;
			}
		}
		return leParking;
	}
	
	/**
	 * M�thode isDispo.
	 * Cette m�thode permet de verifier si un parking est dispo aux horaires communiqu�es ou non.
	 * @author ap
	 * @param TrancheHoraire th : la tranche horaire qu'on veut placer
	 * @return true or false.
	 * @version 1.0 - 20/05/2016
	 */
	public boolean isDispo(TrancheHoraire th){
		boolean dispo = false;
		Passage monPassage, lastPassage = null;
		TrancheHoraire maTh;
		// Je vais devoir parcourir la liste des passages du parking
		Iterator it = this.lesPassages.iterator();
		// si la liste de mes passages est vide, je peux directement prendre le parking
		if(!it.hasNext()){
			System.out.println("dispo direct");
			dispo = true;
		}

		// Je continue tant que j'ai pas prouv� que le parking �tait dispo, et que j'ai des horaires passages suivant
		while (dispo == false && it.hasNext()){
			// Je recupere la trancheHoraire de mon passage courant
			monPassage = (Passage) it.next();
System.out.println(monPassage.getHeureDepart());
System.out.println(th.getDebutTrancheHoraire());
System.out.println(monPassage.getHeureDepart().compareTo(th.getDebutTrancheHoraire()));
			// J'avance jusqu'a trouver le moment ou le d�part de mon passage sera plus grand que l'arriv�e du courant
			if(monPassage.getHeureDepart().compareTo(th.getDebutTrancheHoraire()) >= 0){
				// Si je le trouve, je fais une tranche horaire entre la d�part du last, et l'arriv�e du courant
				
				// Pour ca, je regade d'abord si mon dernier passage est bien rempli
				if(lastPassage != null){
					maTh = new TrancheHoraire(monPassage.getHeureDepart(), lastPassage.getHeureArrivee());
					// maTh contient la tranche Horaire dispo la plus grande avec au moins le d�part de mon vol dedans
					// je regarde maintenant si mon horaire rentre en entier dans maTh ou non
					if(maTh.contient(th)){
						dispo = true;
					}
				} else {
					// S'il est nul c'est que le premier passage a au moins l'arriv� apr�s le d�part du courant
					// Je regarde donc si l'arriv�e de mon courant est avant l'arriv� de ce passage
					System.out.println(monPassage.getHeureArrivee());
					System.out.println(th.getFinTrancheHoraire());
					System.out.println(th.getFinTrancheHoraire().compareTo(monPassage.getHeureArrivee()));
					if(th.getFinTrancheHoraire().compareTo(monPassage.getHeureArrivee()) <= 0){
						System.out.println("ok");
						dispo = true;
					}
				}
			} else {
				// je n'ai rien ensuite, et mon arriv�e courante est apr�s le d�part du dernier test�
				// donc je v�rifie si j'en ai ensuite
				if(!it.hasNext()){
					// je n'ai rien ensuite, donc mon vol peut etre plac�
					System.out.println("dispo after");
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
	 * M�thode addPassage.
	 * Cette m�thode permet d'ajouter un passage dans la liste des passages du parking
	 * @author ap
	 * @param Passage p : le passage � ajouter
	 * @version 1.0 - 21/05/2016
	 * @version 2.0 - /05/2016 by ap : l'ajout s'effectue maintenant dans l'ordre
	 */
	public void addPassage(Passage p){
		this.lesPassages.add(p);
	}
	
	
	/**
	 * @todo : la doc
	 * @return
	 */
	public Porte getLaPorte(){return this.porte;}

	
	/**
	 * M�thode afficherLesPassages.
	 * Cette m�thode permet d'afficher tous les passages d'un parking
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
	
}
