package application;

import java.util.ArrayList;

import utilitaires.Duree;
import utilitaires.TrancheHoraire;

public class Passage {
	
	/**
	 * vol de d�part du passage
	 */
	private Vol volDepart;
	
	/**
	 * vol d'arriv�e du passage
	 */
	private Vol volArrivee;
	
	/**
	 * parking associ� au passage 
	 */
	private Parking parking;
	
	/**
	 * ArrayList qui contient tous les passages
	 */
	public static ArrayList<Passage> lesPassages = new ArrayList<Passage>();

	/**
	 * Constructeur de Passage.
	 * Construit le passage et trouve un parking dispo
	 * 
	 * @author ap
	 * @params a : Vol d'arriv�e
	 * @params d : Vol de d�part
	 * @version 1.0 - 11/05/2016
	 */
	public Passage(Vol a, Vol d){
		this.volDepart = d;
		this.volArrivee = a;
		this.parking = Parking.getParkingDispo(this.getTrancheHoraire());
		lesPassages.add(this);
	}
	
	/**
	 * getTrancheHoraire
	 * Permet de retourner la tranche horaire d'un passage (entre le vol arriv�e et le vol d�part)
	 * 
	 * @author np
	 * @return TrancheHoraire : la tranche horaire entre les deux vols
	 * @version 1.0 - 11/05/2016
	 */
	public TrancheHoraire getTrancheHoraire(){
		return null;
	}
	
/**
 * Duree de temps entre le d�part et l'arriv�e d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}

	public Vol getMonVolArrivee(){return volArrivee;}
	public Vol getMonVolDepart(){return volDepart;}
	public Parking getLeParking(){return parking;}
	
}
