package graphiques;

import application.App;


@SuppressWarnings("serial")
public class EcranHall1 extends EcranHall{

	/**
	 * Constructeur ecranHall1.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @version 2.0 - 25/05/2016 - reprise du constructeur avec la classe mère
	 * 
	 */
	public EcranHall1(App a){
		this.leHall = "1";
		super.init(a);
		this.setVisible(true);
		this.setLocation(0,0);
		
	}
}



