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
	 * d�signation
	 */
	private String nom ;
	
	/**
	 * parking associ�
	 */
	private Parking parking;
	
	/**
	 * Hall associ�
	 */
	private Hall hall;
	
	/**
	 * ArrayList qui contient toutes les portes 
	 */
	private static ArrayList<Porte> lesPortes = new ArrayList<Porte>();

	/**
	 * Constructeur de porte
	 * @param n : nom de la porte
	 */
	public Porte(String n){
		this.nom = n;
		lesPortes.add(this);
	}
	
	/**
	 * Constructeur de porte
	 * @param n : nom de la porte
	 * @param h : hall associ�
	 */
	public Porte(String n, Hall h){
		this.nom = n;
		this.hall = h;
		lesPortes.add(this);
	}
	
	/**
	 * M�thode initialise
	 * 
	 * Cette m�thode static permet d'ouvrir le fichier des portes et d'initialiser les portes.
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
				// je v�rifie que ma ligne soit bien �gale � 3 pour �tre sur de mon fichier d'entr�e

				if(st.countTokens() == 3){
					nom = st.nextToken();
					type = Integer.parseInt(st.nextToken());	
					hall = st.nextToken();
					// On recup�re le Hall avec son ID
					Hall leHall = Hall.find(hall);
					Porte laPorte = null;
					if(type == PORTE_CONTACT){
						laPorte = new PorteContact(nom, leHall);
					} else if(type == PORTE_SANS_CONTACT){
						laPorte = new PorteSansContact(nom, leHall);
					}
					leHall.ajouterPorte(laPorte);
				}
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des portes introuvables");
			System.out.println(e);
		}
	}

	/**
	 * M�thode afficherLesPortes.
	 * Cette m�thode permet de parcourir les instances de portes pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesPortes(){
		Iterator<Porte> it = lesPortes.iterator();
		String str = "------ Les portes ------" + " \n";
		while(it.hasNext()){
			Porte maPorte = it.next();
			str += maPorte.toString() + " \n";
		}
		return str;
	}

	/**
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le nom de la porte courante
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le type (contact, sans contact) de la porte courante
	 * @author ap
	 * @return : le type de l'objet courant
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public int getType(){
		return this.getClass().equals(PorteSansContact.class)?PORTE_SANS_CONTACT:PORTE_CONTACT;
	}
	
	/**
	 * M�thode getNom.
	 * Cette m�thode permet de retourner le type (contact, sans contact) de la porte dont le nom est pass� en param�tre
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
	 * M�thode toString.
	 * Cette m�thode permet d'afficher la porte courante
	 * @author ap
	 * @return : la chaine de caract�re a afficher
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public String toString(){
		return "La porte : " + this.getNom() + (this.getClass().equals(PorteSansContact.class)?", Porte sans contact":", Porte contact");
	}
	
	/**
	 * M�thode find.
	 * Cette m�thode permet de retourner la porte ayant le nom pass� en param�tre.
	 * @author ap
	 * @param String n : nom de la porte
	 * @version 1.0 - 01/05/2016
	 * @return Porte laPorte
	 */
	public static Porte find(String n){
		Porte laPorte = null;
		for (Porte maPorte : lesPortes) {
			if(maPorte.getNom().equals(n)){
				laPorte = maPorte;
			}
		}
		return laPorte;
	}
	
	/**
	 * M�thode ajouterParking.
	 * Cette m�thode permet d'ajouter le parking a la porte courante (assoc 1-1)
	 * @author ap
	 * @param Porte p : la porte � ajouter
	 * @version 1.0 - 01/05/2016
	 */
	public void ajouterParking(Parking p){
		this.parking = p;
	}
}
