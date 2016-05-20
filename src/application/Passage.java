package application;

import java.util.ArrayList;

import utilitaires.Duree;
import utilitaires.Horaire;
import utilitaires.TrancheHoraire;

public class Passage {
	
	/**
	 * vol de départ du passage
	 */
	private VolDepart volDepart;
	
	/**
	 * vol d'arrivée du passage
	 */
	private VolArrivee volArrivee;
	
	/**
	 * parking associé au passage 
	 */
	private Parking parking;
	
	/**
	 * ArrayList qui contient tous les passages
	 */
	public static ArrayList<Passage> lesPassages = new ArrayList<Passage>();

	/**
	 * Duree de temps entre le départ et l'arrivée d'un avion 
	 */
	private static Duree ecart=new Duree(20);
	
	/**
	 * Constructeur de Passage.
	 * Construit le passage et trouve un parking dispo
	 * 
	 * @author ap
	 * @params a : Vol d'arrivée
	 * @params d : Vol de départ
	 * @version 1.0 - 11/05/2016
	 */
	public Passage(VolArrivee a, VolDepart d){
		this.volDepart = d;
		this.volArrivee = a;
		this.parking = Parking.getParkingDispo(this.getTrancheHoraire());
		lesPassages.add(this);
	}
	
	/**
	 * getTrancheHoraire
	 * Permet de retourner la tranche horaire d'un passage (entre le vol arrivée et le vol départ)
	 * 
	 * @author ap
	 * @return TrancheHoraire : la tranche horaire entre les deux vols
	 * @version 1.0 - 20/05/2016
	 */
	public TrancheHoraire getTrancheHoraire(){
		TrancheHoraire maTh = new TrancheHoraire(this.getHeureArrivee(), this.getHeureDepart());
		return maTh;
	}

	/**
	 * getDuree
	 * Permet de retourner la durée static qu'il doit y avoir entre deux vols.
	 * 
	 * @author np
	 * @return Durée : écart
	 * @version 1.0 - 04/2016
	 */
	public static Duree getDuree(){
		return ecart;
	}

	/**
	 * getMonVolArrivee
	 * Permet de retourner le vol d'arrivée du passage
	 * 
	 * @author np
	 * @return VolArrivee : le vol d'arrivée
	 * @version 1.0 - 04/2016
	 */
	public VolArrivee getMonVolArrivee(){return volArrivee;}
	
	/**
	 * getMonVolDepart
	 * Permet de retourner le vol de départ du passage
	 * 
	 * @author np
	 * @return  VolDepart : le vol de départ
	 * @version 1.0 - 04/2016
	 */
	public VolDepart getMonVolDepart(){return volDepart;}
	
	/**
	 * getTrancheHoraire
	 * Permet de retourner la tranche horaire d'un passage (entre le vol arrivée et le vol départ)
	 * 
	 * @author ap
	 * @return TrancheHoraire : la tranche horaire entre les deux vols
	 * @version 1.0 - 20/05/2016
	 */
	public Parking getLeParking(){return parking;}
	
	/**
	 * getHeureArrivee
	 * Permet de retourner l'horaire du vol d'arrivé du passage
	 * 
	 * @author ap
	 * @return Horaire : l'horaire de l'arrivé
	 * @version 1.0 - 20/05/2016
	 */
	public Horaire getHeureArrivee(){
		return this.getMonVolArrivee().getHeureArrivee();
	}
	
	/**
	 * getHeureDepart
	 * Permet de retourner l'horaire du vol de départ du passage
	 * 
	 * @author ap
	 * @return Horaire : l'horaire du départ
	 * @version 1.0 - 20/05/2016
	 */
	public Horaire getHeureDepart(){
		return this.getMonVolDepart().getHeureDepart();
	}
	
}
