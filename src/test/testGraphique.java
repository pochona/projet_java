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
		ecranAerogare frame = new ecranAerogare();
		frame.setVisible(true);
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Ecran Aerogare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);
	}


}
