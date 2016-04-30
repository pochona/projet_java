package application;

import java.util.ArrayList;
import java.util.Iterator;

public class ParkingSansContact extends Parking {
	
private static ArrayList<ParkingSansContact> lesParkingsSansContact = new ArrayList<ParkingSansContact>();
	
	public ParkingSansContact(String n){
		super(n);
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
			str += "Parking :" + monParking.getNom() + " \n";
		}
		return str;
	}
}
