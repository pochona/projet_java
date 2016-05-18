package application;

import java.util.ArrayList;

import utilitaires.Duree;
import utilitaires.TrancheHoraire;

public class Passage {
	
	/**
	 * vol de départ du passage
	 */
	private Vol volDepart;
	
	/**
	 * vol d'arrivée du passage
	 */
	private Vol volArrivee;
	
	/**
	 * parking associé au passage 
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
	 * @params a : Vol d'arrivée
	 * @params d : Vol de départ
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
	 * Permet de retourner la tranche horaire d'un passage (entre le vol arrivée et le vol départ)
	 * 
	 * @author np
	 * @return TrancheHoraire : la tranche horaire entre les deux vols
	 * @version 1.0 - 11/05/2016
	 */
	public TrancheHoraire getTrancheHoraire(){
		return null;
	}
	
/**
 * Duree de temps entre le départ et l'arrivée d'un avion 
 */
	private static Duree ecart=new Duree(20);
	
	public static Duree getDuree(){
		return ecart;
	}

	public Vol getMonVolArrivee(){return volArrivee;}
	public Vol getMonVolDepart(){return volDepart;}
	public Parking getLeParking(){return parking;}
	
}
