package application;

import java.util.ArrayList;
import java.util.Iterator;

public class PorteSansContact extends Porte {
	
	private static ArrayList<PorteSansContact> lesPortesSansContact = new ArrayList<PorteSansContact>();
	
	public PorteSansContact(String n){
		super(n);
		lesPortesSansContact.add(this);
	}
	
	/**
	 * Méthode afficherLesPortesSansContact.
	 * Cette méthode permet de parcourir les instances de portes sans contact pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesPortesSansContact(){
		Iterator<PorteSansContact> it = lesPortesSansContact.iterator();
		String str = "------ Les portes sans contact ------" + " \n";
		while(it.hasNext()){
			Porte maPorte = it.next();
			str += "Porte : " + maPorte.getNom() + " \n";
		}
		return str;
	}
}
