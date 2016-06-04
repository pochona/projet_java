package graphiques;

import application.App;


public class EcranHall1 extends EcranHall{

	private App application;

	/**
	 * Constructeur ecranHall1.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @version 2.0 - 25/05/2016 - reprise du constructeur avec la classe mère
	 * 
	 */
	public EcranHall1(App a){
		this.application = a;
		this.leHall = "1";
		super.init();
	}
}



