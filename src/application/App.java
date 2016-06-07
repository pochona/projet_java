package application;

import graphiques.EcranAerogare;
import graphiques.EcranAerogare2;
import graphiques.EcranHall1;
import graphiques.EcranHall2;
import graphiques.EcranHall3;
import graphiques.EcranHall4;
import graphiques.EcranMenu;

import java.io.IOException;

import javax.swing.JFrame;

public class App {

	private EcranAerogare ecranAerogare;
	private EcranAerogare2 ecranAerogare2;
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
			Passage.afficherLesPassages();
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
	
	/**
	 * Méthode getEcranAerogare2()
	 * Retourne l'ecran du hall4
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public EcranAerogare2 getEcranAerogare2() {
		return this.ecranAerogare2;
	}
}
