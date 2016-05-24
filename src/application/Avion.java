package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Avion {

	/**
	 * Immatriculation unique d'un avion
	 */
	private String immat;

	/**
	 * Type de l'avion 
	 */
	private String type;

	/**
	 * Toutes les instances d'avion
	 */
	private static HashMap<String, Avion> lesAvions = new HashMap<String, Avion>();

	private static ArrayList<String> grandeTaille = new ArrayList<String>(){{
		add("AIRBUS-A380");
	}};
	
	/**
	 * Constructeur d'un avion.
	 * 
	 * @param i : String immatriculation de l'avion
	 * @param t : String type de l'avion
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 * @
	 */
	public Avion (String i, String t){
		this.immat = i;
		this.type = t;
		lesAvions.put(immat, this);
	}

	/**
	 * Accesseur de l'immatriculation d'un avion.
	 * 
	 * @return String immat
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 */
	public String getImmat() {
		return immat;
	}

	/**
	 * Accesseur du type d'un avion.
	 * 
	 * @return String type
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 */
	public String getType() {
		return type;
	}

	/**
	 * Méthode initialise
	 * 
	 * Cette méthode static permet d'ouvrir le fichier des avions et d'initialiser les avions.
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 * @version 2.0 - 30/04/2016 - reprise de l'exception, ajout d'un message utilisateur
	 * @throws IOException 
	 */
	public static void initialise() throws IOException{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\avions.txt"));
			String line;
			String immat;
			String type;
			StringTokenizer st;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je vérifie que ma ligne soit bien égale à 2 pour être sur de mon fichier d'entrée
				if(st.countTokens() == 2){
					immat = st.nextToken();
					type = st.nextToken();	
					new Avion(immat, type);
				}
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des avions introuvables");
			System.out.println(e);
		}
	}

	/**
	 * Méthode afficherLesAvions.
	 * Cette méthode permet de parcourir les instances d'avions pour les afficher
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 * @return String
	 */
	public static String afficherLesAvions(){
		Iterator<Avion> val = lesAvions.values().iterator();
		String str = "------ Les avions ------" + " \n";
		while(val.hasNext()){
			Avion monAvion = val.next();
			str += monAvion.toString() + " \n";
		}
		return str;
	}

	/**
	 * Méthode isGrandeTaille.
	 * Cette méthode permet de retourner vrai ou faux si l'avion est de type grande taille ou pas
	 * @author ap
	 * @version 1.0 - 09/05/2016
	 * @return bool true or false
	 */
	public boolean isGrandeTaille(){
		return grandeTaille.contains(this.type);
	}
	
	/**
	 * Méthode find.
	 * Cette méthode permet de retourner l'avion ayant le nom passé en paramètre.
	 * @author ap
	 * @param String n : nom de l'avion
	 * @version 1.0 - 09/05/2016
	 * @return Avion lAvion
	 */
	public static Avion find(String n){
		Avion lAvion = null;
		lAvion = lesAvions.get(n);
		return lAvion;
	}
	
	/**
	 * Méthode toString.
	 * Cette méthode permet d'afficher l'avion
	 * @author ap
	 * @return : la chaine de caractère a afficher
	 * @version 1.0 - 18/05/2016
	 * @version 1.1 - 24/05/2016 par lb pour suppression du String "num:...Type..." pour soucis d'affichage dans ecranHall1 
	 */
	public String toString(){
		//return "Numéro : " + this.getImmat() + " Type : " + this.getType();
		return this.getImmat() ;
	}
	
}
