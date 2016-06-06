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
		
	/**
	 * Constructeur app.
	 * Constructeur de la classe App
	 * 
	 * @author ap
	 * @version 1.0 - 04/06/2016
	 */
	public App(){
		try {
			Avion.initialise();
			
			Zone.initialise();

			Hall.initialise();
			
			Porte.initialise();
			
			Parking.initialise();
			
			Vol.initialise();
			//Passage.afficherLesPassages();
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

	
	/**
	 * Méthode getEcranAerogare.
	 * Retourne l'écran aerogare.
	 * 
	 * @author ap
	 * @return EcranAerogare ecranAerogare.
	 * @version 1.0 - 04/06/2016
	 */
	public EcranAerogare getEcranAerogare() {
		return this.ecranAerogare;
	}
	
	
	/**
	 * Méthode getEcranHall1()
	 * Retourne l'ecran du hall1
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall1 getEcranHall1() {
		return this.ecranhall1;
	}
	
	/**
	 * Méthode getEcranHall2()
	 * Retourne l'ecran du hall2
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall2 getEcranHall2() {
		return this.ecranhall2;
	}
	
	/**
	 * Méthode getEcranHall3()
	 * Retourne l'ecran du hall3
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall3 getEcranHall3() {
		return this.ecranhall3;
	}
	
	/**
	 * Méthode getEcranHall4()
	 * Retourne l'ecran du hall4
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall4 getEcranHall4() {
		return this.ecranhall4;
	}
}
