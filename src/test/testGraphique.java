package test;

import java.io.IOException;

import javax.swing.JFrame;

import application.Avion;
import application.ErreurLignesSuccessivesVols;
import application.Hall;
import application.Parking;
import application.ParkingContact;
import application.ParkingSansContact;
import application.Porte;
import application.Vol;
import application.Zone;
import graphiques.EcranAerogare;
import graphiques.EcranHall1;
import graphiques.EcranHall2;
import graphiques.EcranHall3;
import graphiques.EcranHall4;
import graphiques.EcranMisAJour;

public class testGraphique {
	
	
	public static void main(String[] args) {
				
		try {
			Avion.initialise();
			
			Zone.initialise();

			Hall.initialise();
			
			Porte.initialise();
			
			Parking.initialise();
			
			Vol.initialise();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErreurLignesSuccessivesVols e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//Gestion de l'ecran de l'aerogare
		/*EcranAerogare aerogare = new EcranAerogare();
		aerogare.setSize(700,700);
		aerogare.setLocationRelativeTo(null);
		aerogare.setTitle("Ecran Aerogare");
		aerogare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		aerogare.setVisible(true);
		
		EcranHall1 ecranhall1 = new EcranHall1();
		ecranhall1.setVisible(true);
		ecranhall1.setSize(400,200);
		ecranhall1.setLocation(0,0);
		ecranhall1.setTitle("Ecran Hall 1");
		ecranhall1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		

		EcranHall2 ecranhall2 = new EcranHall2();
		ecranhall2.setVisible(true);
		ecranhall2.setSize(400,200);
		ecranhall2.setLocation(0,200);
		ecranhall2.setTitle("Ecran Hall 2");
		ecranhall2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		EcranHall3 ecranhall3 = new EcranHall3();
		ecranhall3.setVisible(true);
		ecranhall3.setSize(400,200);
		ecranhall3.setLocation(0,400);
		ecranhall3.setTitle("Ecran Hall 3");
		ecranhall3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		EcranHall4 ecranhall4 = new EcranHall4();
		ecranhall4.setVisible(true);
		ecranhall4.setSize(400,200);
		ecranhall4.setLocation(0,600);
		ecranhall4.setTitle("Ecran Hall 4");
		ecranhall4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		
		EcranMisAJour ecranMisAJour = new EcranMisAJour();
		ecranMisAJour.setVisible(true);
		ecranMisAJour.setSize(700,700);
		ecranMisAJour.setLocationRelativeTo(null);
		ecranMisAJour.setTitle("Mis à jour des vols");
		ecranMisAJour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}


}
