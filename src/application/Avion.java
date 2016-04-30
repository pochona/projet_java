package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	 * M�thode initialise
	 * 
	 * Cette m�thode static permet d'ouvrir le fichier des avions et d'initialiser les avions.
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
				// je v�rifie que ma ligne soit bien �gale � 2 pour �tre sur de mon fichier d'entr�e
				if(st.countTokens() == 2){
					immat = st.nextToken();
					type = st.nextToken();	
					new Avion(immat, type);
				}
			}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des avions introuvables");
			System.out.println(e);
		}
	}

	/**
	 * M�thode afficherLesAvions.
	 * Cette m�thode permet de parcourir les instances d'avions pour les afficher
	 * @author ap
	 * @version 1.0 - 05/04/2016
	 * @return String
	 */
	public static String afficherLesAvions(){
		Iterator<Avion> val = lesAvions.values().iterator();
		String str = "------ Les avions ------" + " \n";
		while(val.hasNext()){
			Avion monAvion = val.next();
			str += "Num�ro : " + monAvion.getImmat() + " Type : " + monAvion.getType() + " \n";
		}
		return str;
	}


}
