package application;

import java.util.ArrayList;
import java.util.Iterator;

public class ParkingSansContact extends Parking {

	private static ArrayList<ParkingSansContact> lesParkingsSansContact = new ArrayList<ParkingSansContact>();

	/**
	 * Constructeur de parking sans contact
	 * @param n : nom du parking
	 */
	public ParkingSansContact(String n){
		super(n);
		lesParkingsSansContact.add(this);
	}

	/**
	 * Constructeur de parking sans contact
	 * @param n : nom du parking
	 * @param z : Zone associée
	 * @param p : Porte associée
	 */
	public ParkingSansContact(String n, Zone z, Porte p){
		super(n, z, p);
		lesParkingsSansContact.add(this);
	}

	/**
	 * Méthode afficherLesParkingsSansContact.
	 * Cette méthode permet de parcourir les instances de parkings sans contact pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesParkingsSansContact(){
		Iterator<ParkingSansContact> it = lesParkingsSansContact.iterator();
		String str = "------ Les parkings sans contact ------" + " \n";
		while(it.hasNext()){
			Parking monParking = it.next();
			str += monParking.toString() + " \n";
		}
		return str;
	}
	
	/**
	 * 
	 * @return ArrayList<ParkingSansContact> : la liste des parkings sans contact (pour les avions grande taille)
	 * @author : np
	 * @version 1.0 - 29/05/2016 
	 */
	public static ArrayList<ParkingSansContact> getLesParkingsSansContact(){
		return lesParkingsSansContact;
	}
}
