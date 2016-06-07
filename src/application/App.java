package application;

import graphiques.EcranAerogare;
import graphiques.EcranHall1;
import graphiques.EcranHall2;
import graphiques.EcranHall3;
import graphiques.EcranHall4;
import graphiques.EcranParking;

import java.io.IOException;


public class App {

	private EcranAerogare ecranAerogare;
	private EcranHall1 ecranhall1;
	private EcranHall2 ecranhall2;
	private EcranHall3 ecranhall3;
	private EcranHall4 ecranhall4;
	private EcranParking ecranParking;
		
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErreurLignesSuccessivesVols e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//Gestion de l'ecran de l'aerogare
		this.ecranAerogare = new EcranAerogare(this);
		
		this.ecranhall1 = new EcranHall1(this);
	
		this.ecranhall2 = new EcranHall2(this);
	
		this.ecranhall3 = new EcranHall3(this);
		
		this.ecranhall4 = new EcranHall4(this);
		
		this.ecranParking = new EcranParking(this);
	}

	
	/**
	 * M�thode getEcranAerogare.
	 * Retourne l'�cran aerogare.
	 * 
	 * @author ap
	 * @return EcranAerogare ecranAerogare.
	 * @version 1.0 - 04/06/2016
	 */
	public EcranAerogare getEcranAerogare() {
		return this.ecranAerogare;
	}
	
	
	/**
	 * M�thode getEcranHall1()
	 * Retourne l'ecran du hall1
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall1 getEcranHall1() {
		return this.ecranhall1;
	}
	
	/**
	 * M�thode getEcranHall2()
	 * Retourne l'ecran du hall2
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall2 getEcranHall2() {
		return this.ecranhall2;
	}
	
	/**
	 * M�thode getEcranHall3()
	 * Retourne l'ecran du hall3
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall3 getEcranHall3() {
		return this.ecranhall3;
	}
	
	/**
	 * M�thode getEcranHall4()
	 * Retourne l'ecran du hall4
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranHall4 getEcranHall4() {
		return this.ecranhall4;
	}
	
}
