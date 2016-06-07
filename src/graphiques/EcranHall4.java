package graphiques;

import application.App;


@SuppressWarnings("serial")
public class EcranHall4 extends EcranHall {
	
	/**
	 * Constructeur ecranHall3.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @version 2.0 - 25/05/2016 - reprise du constructeur avec la classe mèe
	 */
	public EcranHall4(App a){
		this.leHall = "4";
		super.init(a);
		this.setVisible(true);
		this.setLocation(0,600);
	}
}
