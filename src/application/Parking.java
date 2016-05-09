package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import utilitaires.Horaire;

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
			str += "Parking :" + monParking.getNom() + " \n";
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
		return "Le parking " + this.getNom();
	}
	
	/**
	 * Méthode getParkingDispo.
	 * Cette méthode static permet de retrourner un parking dispo aux horaires communiquées
	 * @author ap
	 * @return : un parking dispo
	 * @version 1.0 - 09/05/2016
	 */
	public static Parking getParkingDispo(Horaire deb, Horaire fin, Avion a){
		Parking leParking = null;
		/* On checkera après le trairement grande taille ou non 
		 if(a.isGrandeTaille()){
			
		} else {
			
		}*/
		boolean find = false;
		
		Iterator it = lesParkings.iterator();
		while(!find){
			Parking monParking = (Parking) it.next();
			if(monParking.isDispo(deb, fin)){
				leParking = monParking;
			}
		}
		
		return null;
	}
	
	/**
	 * Méthode isDispo.
	 * Cette méthode permet de verifier si un parking est dispo aux horaires communiquées ou non.
	 * @author ap
	 * @return true or false.
	 * @version 1.0 - 09/05/2016
	 */
	public boolean isDispo(Horaire deb, Horaire fin){
		return false;
	}
}
