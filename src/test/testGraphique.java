package test;

import javax.swing.JFrame;

import graphiques.ecranAerogare;

public class testGraphique {
	
	
	public static void main(String[] args) {
		
		//Gestion de l'ecran de l'aerogare
		ecranAerogare frame = new ecranAerogare();
		frame.setVisible(true);
		frame.setSize(550,550);
		frame.setLocation(300,300);
		frame.setTitle("Ecran Aerogare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);
	}


}
