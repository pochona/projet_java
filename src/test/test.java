package test;

import java.io.IOException;

import application.Avion;
import application.Parking;
import application.ParkingContact;
import application.ParkingSansContact;
import application.PorteContact;
import application.PorteSansContact;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
