package test;

import java.io.IOException;
import utilitaires.Duree;
import utilitaires.Horaire;
import application.Avion;
import application.ErreurLignesSuccessivesVols;
import application.Parking;
import application.ParkingIndispo;
import application.Passage;
import application.RetardTropTard;
import application.Vol;
import application.Zone;
import application.Hall;
import application.Porte;

public class test {
	/**
	 * @param args
	 * @throws ParkingIndispo 
	 * @throws RetardTropTard 
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
		
		//	System.out.println(Parking.afficherLesParkings());
		//	System.out.println(ParkingContact.afficherLesParkingsContact());
		//	System.out.println(ParkingSansContact.afficherLesParkingsSansContact());
			
			Vol.initialise();
		//	VolArrivee.afficherLesVolsArrivee();
		//	VolDepart.afficherLesVolsDepart();
		//	Vol.afficherLesVols();
			
			Hall leHall = Hall.find("3");
			Porte laPorte = Porte.find("10C");
			Parking leParking = Parking.find("S4");
			Zone laZone = Zone.find("Sierra");
			
		/*	Test Amaury */
		/*	ArrayList<Passage> lesPassages = new ArrayList<Passage>();
			lesPassages = Passage.getlesPassages();
			Passage monPassage = lesPassages.get(5);
			TrancheHoraire math = monPassage.getTrancheHoraire();
	
			TrancheHoraire math2 = new TrancheHoraire(new Horaire(18, 00), new Horaire(19, 30));
			
			TrancheHoraire mathparam = new TrancheHoraire(new Horaire(10, 00), new Horaire(11, 30));

			System.out.println(mathparam.getFinTrancheHoraire().compareTo(math2.getDebutTrancheHoraire()));
			*/
			Vol monVol = Vol.getLeVol("TWA019");
			Duree temps = new Duree(300);
			Horaire monHA = monVol.getLePassage().getHeureArrivee();
			System.out.println("monHA : " + monHA);
			Horaire monHD = monVol.getLePassage().getHeureDepart();
			System.out.println("monHD : " + monHD);

			Horaire newHA = monHA.ajout(temps);
			System.out.println("newHA : " +newHA);
			if(monHD.compareTo(newHA) <= Passage.getEcart().dureeEnMinutes()){
				Horaire newHD = newHA.ajout(Passage.getEcart());
				System.out.println("pas bien");
				System.out.println("newHD : " + newHA.ajout(Passage.getEcart()));
			} else {
				System.out.println("newHD : " + monHD);
			}
			//System.out.println(newHA.compareTo(monHA));
			
			Vol.retarder("300", "TWA019");
			
			
			System.out.println("breakpoint");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErreurLignesSuccessivesVols e){
			System.out.println(e);
		} catch (RetardTropTard e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (ParkingIndispo e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	/*test commit*/

}
