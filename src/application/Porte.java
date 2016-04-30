package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Porte {

	public static final int PORTE_CONTACT = 1;
	public static final int PORTE_SANS_CONTACT = 2;
	
	/**
	 * désignation
	 */
	private String nom ;
	
	/**
	 * ArrayList qui contient toutes les portes 
	 */
	private static ArrayList<Porte> lesPortes = new ArrayList<Porte>();

	
	public Porte(String n){
		this.nom = n;
		lesPortes.add(this);
	}
	
	/**
	 * Méthode initialise
	 * 
	 * Cette méthode static permet d'ouvrir le fichier des portes et d'initialiser les portes.
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\portes.txt"));
			String line;
			String nom;
			int type;
			String hall;
			StringTokenizer st;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je vérifie que ma ligne soit bien égale à 3 pour être sur de mon fichier d'entrée

				if(st.countTokens() == 3){
					nom = st.nextToken();
					type = Integer.parseInt(st.nextToken());	
					hall = st.nextToken();
					
					if(type == PORTE_CONTACT){
						new PorteContact(nom);
					} else if(type == PORTE_SANS_CONTACT){
						new PorteSansContact(nom);
					}
				}
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des portes introuvables");
			System.out.println(e);
		}
	}

	/**
	 * Méthode afficherLesPortes.
	 * Cette méthode permet de parcourir les instances de portes pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesPortes(){
		Iterator<Porte> it = lesPortes.iterator();
		String str = "------ Les portes ------" + " \n";
		while(it.hasNext()){
			Porte maPorte = it.next();
			str += "Porte : " + maPorte.getNom() + (maPorte.getClass().equals(PorteSansContact.class)?", Porte sans contact":", Porte contact") + " \n";
		}
		return str;
	}

	/**
	 * Méthode getNom.
	 * Cette méthode permet de retourner le nom de la porte courante
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * Méthode getNom.
	 * Cette méthode permet de retourner le type (contact, sans contact) de la porte courante
	 * @author ap
	 * @return : le type de l'objet courant
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public int getType(){
		return this.getClass().equals(PorteSansContact.class)?PORTE_SANS_CONTACT:PORTE_CONTACT;
	}
	
	/**
	 * Méthode getNom.
	 * Cette méthode permet de retourner le type (contact, sans contact) de la porte dont le nom est passé en paramètre
	 * @author ap
	 * @param : n le nom de l'objet dont il faut trouver le type
	 * @return : le type de l'objet courant
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static int getType(String n){
		int type = -1;
		for (Porte maPorte : lesPortes) {
			if(maPorte.getNom().equals(n)){
				type = maPorte.getType();
			}
		}
		return type;
	}
	
	/**
	 * Méthode toString.
	 * Cette méthode permet d'afficher la porte courante
	 * @author ap
	 * @return : la chaine de caractère a afficher
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String toString(){
		return "La porte " + this.getNom();
	}
}
