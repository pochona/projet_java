package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Zone {
	/**
	 * d�signation
	 */
	private String nom ;
	
	/**
	 * liste des parkings associ�es
	 */
	private ArrayList<Parking> lesParkings;
	
	/**
	 * ArrayList qui contient toutes les zones 
	 */
	private static ArrayList<Zone> lesZones = new ArrayList<Zone>();

	/**
	 * Constructeur de zone
	 * @param n : nom de la zone
	 */
	public Zone(String n){
		this.nom = n;
		this.lesParkings = new ArrayList<Parking>();
		lesZones.add(this);
	}
	
	/**
	 * M�thode initialise
	 * 
	 * Cette m�thode static permet d'ouvrir le fichier des zones et d'initialiser les zones.
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\zones.txt"));
			String line;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {

				new Zone(line);
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des zones introuvables");
			System.out.println(e);
		}
	}

	/**
	 * M�thode afficherLesZones.
	 * Cette m�thode permet de parcourir les instances de zones pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesZones(){
		Iterator<Zone> it = lesZones.iterator();
		String str = "------ Les zones ------" + " \n";
		while(it.hasNext()){
			Zone maZone = it.next();
			str += "Zone : " + maZone.getNom() + " \n";
		}
		return str;
	}

	/**
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le nom de la zone courante
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * M�thode find.
	 * Cette m�thode permet de retourner la zone ayant le nom pass� en param�tre.
	 * @author ap
	 * @param String n : nom de la zone
	 * @version 1.0 - 01/05/2016
	 * @return Hall leHall
	 */
	public static Zone find(String n){
		Zone laZone = null;
		for (Zone maZone : lesZones) {
			if(maZone.getNom().equals(n)){
				laZone = maZone;
			}
		}
		return laZone;
	}
	
	/**
	 * M�thode ajouterParking.
	 * Cette m�thode permet d'ajouter le parking a la liste des parking de la zone
	 * @author ap
	 * @param Porte p : la porte � ajouter
	 * @version 1.0 - 01/05/2016
	 */
	public void ajouterParking(Parking p){
		this.lesParkings.add(p);
	}
	
	/**
	 * M�thode toString.
	 * Cette m�thode permet d'afficher la zone courante
	 * @author ap
	 * @return : la chaine de caract�re a afficher
	 * @version 1.0 - 01/05/2016
	 * @return String
	 */
	public String toString(){
		return "La zone " + this.getNom();
	}
}
