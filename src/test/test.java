package test;

import java.io.IOException;
import java.util.ArrayList;

import utilitaires.Horaire;
import utilitaires.TrancheHoraire;
import application.Avion;
import application.ErreurLignesSuccessivesVols;
import application.Parking;
import application.ParkingContact;
import application.ParkingSansContact;
import application.Passage;
import application.PorteContact;
import application.PorteSansContact;
import application.Vol;
import application.VolArrivee;
import application.VolDepart;
import application.Zone;
import application.Hall;
import application.Porte;

public class test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Avion.initialise();
		//	System.out.println(Avion.afficherLesAvions());
			
			
			Zone.initialise();
		//	System.out.println(Zone.afficherLesZones());
			
			Hall.initialise();
		//	System.out.println(Hall.afficherLesHalls());
			
			Porte.initialise();
		//	System.out.println(Porte.afficherLesPortes());
		//	System.out.println(PorteContact.afficherLesPortesContact());
		//	System.out.println(PorteSansContact.afficherLesPortesSansContact());
			
			Parking.initialise();
		
			System.out.println(Parking.afficherLesParkings());
			System.out.println(ParkingContact.afficherLesParkingsContact());
			System.out.println(ParkingSansContact.afficherLesParkingsSansContact());
			
			Vol.initialise();
		//	VolArrivee.afficherLesVolsArrivee();
		//	VolDepart.afficherLesVolsDepart();
		//	Vol.afficherLesVols();
			
			Hall leHall = Hall.find("3");
			Porte laPorte = Porte.find("10C");
			Parking leParking = Parking.find("S4");
			Zone laZone = Zone.find("Sierra");
			
		/*	Test Amaury */
			ArrayList<Passage> lesPassages = new ArrayList<Passage>();
			lesPassages = Passage.lesPassages;
			Passage monPassage = lesPassages.get(5);
			TrancheHoraire math = monPassage.getTrancheHoraire();
			TrancheHoraire math2 = new TrancheHoraire(new Horaire(10, 00), new Horaire(11, 30));
			System.out.println(math2.getFinTrancheHoraire().compareTo(math.getDebutTrancheHoraire()));
			System.out.println("breakpoint");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErreurLignesSuccessivesVols e){
			System.out.println(e);
		}
	}
	
	/*test commit*/

}
