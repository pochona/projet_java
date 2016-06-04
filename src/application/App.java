package application;

import graphiques.EcranAerogare;
import graphiques.EcranHall1;
import graphiques.EcranHall2;
import graphiques.EcranHall3;
import graphiques.EcranHall4;
import graphiques.EcranMenu;

import java.io.IOException;

import javax.swing.JFrame;

public class App {

	private EcranAerogare ecranAerogare;
	private EcranHall1 ecranhall1;
	private EcranHall2 ecranhall2;
	private EcranHall3 ecranhall3;
	private EcranHall4 ecranhall4;
	private EcranMenu ecranMenu;
		
		
	public App(){
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
		this.ecranAerogare = new EcranAerogare(this);
		this.ecranAerogare.setSize(700,700);
		this.ecranAerogare.setLocationRelativeTo(null);
		this.ecranAerogare.setTitle("Ecran Aerogare");
		this.ecranAerogare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.ecranAerogare.setVisible(true);
		
		this.ecranhall1 = new EcranHall1(this);
		this.ecranhall1.setVisible(true);
		this.ecranhall1.setLocation(0,0);
		

		this.ecranhall2 = new EcranHall2(this);
		this.ecranhall2.setVisible(true);
		this.ecranhall2.setLocation(0,200);

		this.ecranhall3 = new EcranHall3(this);
		this.ecranhall3.setVisible(true);
		this.ecranhall3.setLocation(0,400);
		
		this.ecranhall4 = new EcranHall4(this);
		this.ecranhall4.setVisible(true);
		this.ecranhall4.setLocation(0,600);
		

		this.ecranMenu = new EcranMenu(this);
		this.ecranMenu.setVisible(true);
		this.ecranMenu.setSize(700,200);
		this.ecranMenu.setLocationRelativeTo(null);
		this.ecranMenu.setTitle("Menu");
		this.ecranMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public EcranAerogare getEcranAerogare() {
		return this.ecranAerogare;
	}
	
}
