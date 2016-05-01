package application;

import java.util.ArrayList;
import java.util.Iterator;

public class PorteContact extends Porte {
	
	private static ArrayList<PorteContact> lesPortesContact = new ArrayList<PorteContact>();
	
	/**
	 * Constructeur de porte contact 
	 * @param n : nom de la porte
	 */
	public PorteContact(String n){
		super(n);
		lesPortesContact.add(this);
	}
	
	/**
	 * Constructeur de porte contact
	 * @param n : nom de la porte
	 * @param h : hall associé
	 */
	public PorteContact(String n, Hall h){
		super(n, h);
		lesPortesContact.add(this);
	}
	
	/**
	 * Méthode afficherLesPortesSansContact.
	 * Cette méthode permet de parcourir les instances de portes avec contact pour les afficher
	 * @author ap
	 * @version 1.0 - 30/04/2016
	 * @return String
	 */
	public static String afficherLesPortesContact(){
		Iterator<PorteContact> it = lesPortesContact.iterator();
		String str = "------ Les portes avec contact ------" + " \n";
		while(it.hasNext()){
			Porte maPorte = it.next();
			str += "Porte : " + maPorte.getNom() + " \n";
		}
		return str;
	}
}
