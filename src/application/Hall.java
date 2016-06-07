package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Hall {
	/**
	 * d�signation
	 */
	private String nom ;
	
	/**
	 * Liste des portes associ�es
	 */
	private ArrayList<Porte> lesPortes;
	
	/**
	 * ArrayList qui contient tous les halls 
	 */
	private static ArrayList<Hall> lesHalls = new ArrayList<Hall>();

	/**
	 * Constructeur de Hall
	 * @param n : nom du hall
	 */
	public Hall(String n){
		this.nom = n;
		this.lesPortes = new ArrayList<Porte>();
		lesHalls.add(this);
	}
	
	/**
	 * M�thode initialise
	 * 
	 * Cette m�thode static permet d'ouvrir le fichier des halls et d'initialiser les halls.
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\halls.txt"));
			String line;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				new Hall(line);
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des halls introuvables");
			System.out.println(e);
		}
	}

	/**
	 * M�thode afficherLesHalls.
	 * Cette m�thode permet de parcourir les instances de halls pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesHalls(){
		Iterator<Hall> it = lesHalls.iterator();
		String str = "------ Les halls ------" + " \n";
		while(it.hasNext()){
			Hall monHall = it.next();
			str += monHall.toString() + " \n";
		}
		return str;
	}

	/**
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le nom du hall courant
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * M�thode find.
	 * Cette m�thode permet de retourner la hall ayant le nom pass� en param�tre.
	 * @author ap
	 * @param String n : nom du hall
	 * @version 1.0 - 01/05/2016
	 * @return Hall leHall
	 */
	public static Hall find(String n){
		Hall leHall = null;
		for (Hall monHall : lesHalls) {
			if(monHall.getNom().equals(n)){
				leHall = monHall;
			}
		}
		return leHall;
	}
	
	/**
	 * M�thode ajouterPorte.
	 * Cette m�thode permet d'ajouter une porte a la liste des portes du hall courant
	 * @author ap
	 * @param Porte p : la porte � ajouter
	 * @version 1.0 - 01/05/2016
	 */
	public void ajouterPorte(Porte p){
		this.lesPortes.add(p);
	}
	
	/**
	 * M�thode toString.
	 * Cette m�thode permet d'afficher le hall courant
	 * @author ap
	 * @return : la chaine de caract�re a afficher
	 * @version 1.0 - 01/05/2016
	 */
	public String toString(){
		return "Le hall " + this.getNom();
	}
	
	/**
	 * M�thode getNomsDesHalls.
	 * Cette m�thode permet de retourner un array avec les noms des halls
	 * @author ap
	 * @return : ArrayList<String> avec les noms
	 * @version 1.0 - 07/06/2016
	 */
	public static ArrayList<String> getNomsDesHalls(){
		Iterator<Hall> it = lesHalls.iterator();
		Hall leHall;
		ArrayList<String> tab = new ArrayList<String>();
		//String[] tab = new String[lesHalls.size()];
		//int cpt = 0;
		while(it.hasNext()){
			leHall = it.next();
			tab.add(leHall.getNom());
		}
		return tab;
	}
	
}

