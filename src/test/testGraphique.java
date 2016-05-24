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
import graphiques.ecranAerogare;
import graphiques.ecranHall1;

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
	/*	ecranAerogare aerogare = new ecranAerogare();
		aerogare.setSize(800,800);
		aerogare.setLocationRelativeTo(null);
		aerogare.setTitle("Ecran Aerogare");
		aerogare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		aerogare.setVisible(true);*/
		
		ecranHall1 ecranhall1 = new ecranHall1();
		ecranhall1.setVisible(true);
		ecranhall1.setSize(800,800);
		ecranhall1.setLocationRelativeTo(null);
		ecranhall1.setTitle("Ecran Hall 1");
		ecranhall1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

	}


}
