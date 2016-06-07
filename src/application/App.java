package application;

import graphiques.EcranAerogare;
import graphiques.EcranHall1;
import graphiques.EcranHall2;
import graphiques.EcranHall3;
import graphiques.EcranHall4;
import graphiques.EcranModif;
import graphiques.EcranParking;

import java.io.IOException;

/**
 * La classe App est la classe qui lance l'application
 * Elle initialise les objets via les fichiers, et elle connait tous les écrans
 *
 */
public class App {

	/**
	 * L'écran aerogare de l'application
	 */
	private EcranAerogare ecranAerogare;
	
	/**
	 * L'ecran contenant les infos du Hall numéro 1
	 */
	private EcranHall1 ecranhall1;
	
	/**
	 * L'ecran contenant les infos du Hall numéro 2
	 */
	private EcranHall2 ecranhall2;
	
	/**
	 * L'ecran contenant les infos du Hall numéro 3
	 */
	private EcranHall3 ecranhall3;
	
	/**
	 * L'ecran contenant les infos du Hall numéro 4
	 */
	private EcranHall4 ecranhall4;
	
	/**
	 * L'ecran contenant les infos des parkings
	 */
	private EcranParking ecranParking;
	
	/**
	 * L'ecran de modification et de consultation
	 */
	private EcranModif ecranModif;
	
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
		} catch (ErreurLignesSuccessivesVolsException e) {
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
	 * Méthode setEcranModif
	 * Actualise l'ecran de modif, et supprime l'ancien s'il en existe un ouvert
	 * @author ap
	 * @param em : le nouveau ecranModif
	 * @version 1.0 - 07/06/2016
	 */
	public void setEcranModif(EcranModif em){
		if(this.ecranModif != null){
			this.ecranModif.dispose();
		}
		this.ecranModif = em;
	}
	
	/**
	 * Méthode getEcranParking
	 * retourne l'ecran des parkings.
	 * @author ap
	 * @return EcranParking : l'ecran des parkings
	 * @version 1.0 - 07/06/2016
	 */
	public EcranParking getEcranParking(){
		return this.ecranParking;
	}
	
}
