package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Hall {
	/**
	 * désignation
	 */
	private String nom ;
	private ArrayList lesZones;
	
	/**
	 * ArrayList qui contient tous les halls 
	 */
	private static ArrayList lesHalls = new ArrayList();

	public Hall(String n){
		this.nom = n;
		this.lesZones = new ArrayList();
		lesHalls.add(this);
	}
	
	/**
	 * Méthode initialise
	 * 
	 * Cette méthode static permet d'ouvrir le fichier des halls et d'initialiser les halls.
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\halls.txt"));
			String line;
			StringTokenizer st;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				new Hall(line);
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des halls introuvables");
			System.out.println(e);
		}
	}

	/**
	 * Méthode afficherLesHalls.
	 * Cette méthode permet de parcourir les instances de halls pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesHalls(){
		Iterator<Hall> it = lesHalls.iterator();
		String str = "------ Les halls ------" + " \n";
		while(it.hasNext()){
			Hall monHall = it.next();
			str += "Hall : " + monHall.getNom() + " \n";
		}
		return str;
	}

	/**
	 * Méthode getNom.
	 * Cette méthode permet de retourner le nom du hall courant
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
}

