package application;

import java.util.ArrayList;
import java.util.Iterator;

public class ParkingContact extends Parking {
	
private static ArrayList<ParkingContact> lesParkingsContact = new ArrayList<ParkingContact>();
	
	/**
	 * Constructeur de parking contact
	 * @param n : nom du parking
	 */
	public ParkingContact(String n){
		super(n);
		lesParkingsContact.add(this);
	}
	
	/**
	 * Constructeur de parking contact
	 * @param n : nom du parking
	 * @param z : Zone associée
	 * @param p : Porte associée
	 */
	public ParkingContact(String n, Zone z, Porte p){
		super(n, z, p);
		lesParkingsContact.add(this);
	}
	
	/**
	 * Méthode afficherLesParkingsContact.
	 * Cette méthode permet de parcourir les instances de parkings avec contact pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesParkingsContact(){
		Iterator<ParkingContact> it = lesParkingsContact.iterator();
		String str = "------ Les parkings avec contact ------" + " \n";
		while(it.hasNext()){
			Parking monParking = it.next();
			str += "Parking :" + monParking.getNom() + " \n";
		}
		return str;
	}
	
}

