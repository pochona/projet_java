package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Parking {

	/**
	 * d�signation
	 */
	private String nom ;
	
	/**
	 * ArrayList qui contient toutes les parkings 
	 */
	private static ArrayList lesParkings = new ArrayList();
	
	public static final int PARKING_CONTACT = 1;
	public static final int PARKING_SANS_CONTACT = 2;

	
	public Parking(String n){
		this.nom = n;
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
					// je check si la porte associ�e au parking est une porte contact ou sans contact
					// cela permettra de determiner si je dois faire un parking contact ou sans contact
					if(Porte.getType(porte) == Porte.PORTE_CONTACT){
						new ParkingContact(nom);
					} else {
						new ParkingSansContact(nom);
					}
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
			str += "Parking :" + monParking.getNom() + " \n";
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
}
