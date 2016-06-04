package application;

import java.util.ArrayList;
import java.util.Iterator;

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
	private static ArrayList<Passage> lesPassages = new ArrayList<Passage>();

	/**
	 * Duree de temps entre le départ et l'arrivée d'un avion 
	 */
	private static Duree ecart=new Duree(20);
	
	/**
	 * int compteur de passage (ap : principalement utilisé pour des tests)
	 */
	private static int nbPassage = 0;
	
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
		nbPassage++;
	//	System.out.println(nbPassage);
		this.parking = Parking.getParkingDispo(this.getTrancheHoraire(), this.getMonVolArrivee().getLAvion());
		this.parking.addPassage(this);
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
		return this.getMonVolArrivee().getHoraire();
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
		return this.getMonVolDepart().getHoraire();
	}
	
	/**
	 * getHeureDepart
	 * Permet de retourner la liste static des passages 
	 * 
	 * @author ap
	 * @return ArrayList : tous les passages
	 * @version 1.0 - 21/05/2016
	 */
	public static ArrayList<Passage> getlesPassages(){
		return lesPassages;
	}

	/**
	 * toString
	 * Méthode d'affichage d'un passage
	 * 
	 * @author ap
	 * @return String : la chaine à afficher
	 * @version 1.0 - 22/05/2016
	 */
	public String toString(){
		return "Vol de " + this.volArrivee.getHoraire() + " à " + this.volDepart.getHoraire() + " (" + this.volArrivee.getLAvion() + ")" +". \n";
	}
	/**
	 * 
	 * @author np
	 * @return Duree : l'ecart min entre vol arrrivée et vol départ
	 * @version 1.0 - 25/05/2016
	 */
	public static Duree getEcart(){
		return ecart;
	}
	/**
	 * On met un nouveau parking dans le passage
	 * @param p Parking
	 * @author np
	 * @version 1.0 - 25/05/2016
	 */
	public void setLeParking(Parking p){
		this.parking=p;
	}
	
	/**
	 * Méthode afficherLesPassages.
	 * Cette méthode static permet de d'afficher (sur la console) les parkings, avec les passages 
	 * 
	 * @author ap
	 * @version 1.0 - 04/06/2016
	 */
	public static void afficherLesPassages(){
		Iterator<Parking> it = Parking.getLesParkings().iterator();
		while(it.hasNext()){
			Parking p = it.next();
			System.out.println("--------------- " + p.getNom() + " ---------------");
			Iterator<Passage> itPassage = p.getLesPassages().iterator();
			while(itPassage.hasNext()){
				Passage pa = itPassage.next();
				System.out.println("-- " + pa.getHeureArrivee() + " -- " + pa.getMonVolArrivee().getNumVol());
				System.out.println("-->");
				System.out.println("-- " + pa.getHeureDepart() + " -- " + pa.getMonVolDepart().getNumVol());
				System.out.println('\n');
			}
		}
	}
}
