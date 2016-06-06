package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ActionMenuRefresh extends AbstractAction{

	//Declaration
	EcranMenu ecranMenu;
	EcranAerogare ecranAerogare;
	EcranAerogare2 ecranAerogare2;
	EcranHall1 ecranHall1;
	EcranHall2 ecranHall2;
	EcranHall3 ecranHall3;
	EcranHall4 ecranHall4;
	
	
	
	/**
	 * Constructeur ActionMenuRefresh.
	 * @author lb
	 * @params EcranMenu ecranMenu
	 * @version 1.0 - 27/05/2016
	 */
	public ActionMenuRefresh(EcranMenu ecranMenu, EcranAerogare ecranAerogare,EcranHall1 ecranHall1, EcranHall2 ecranHall2, EcranHall3 ecranHall3, EcranHall4 ecranHall4, EcranAerogare2 ecranAerogare2) {
		this.ecranMenu=ecranMenu;
		this.ecranAerogare=ecranAerogare;
		this.ecranAerogare2=ecranAerogare2;
		this.ecranHall2=ecranHall2;
		this.ecranHall1=ecranHall1;
		this.ecranHall3=ecranHall3;
		this.ecranHall4=ecranHall4;
	
	
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et rafraichit lecran aerogare et les halls
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Rafraichir lecran Aerogare et les halls
		//ecranAerogare.actualiserListe();
		//ecranAerogare2.actualiserListe();
		ecranHall1.actualiserListe();
		ecranHall2.actualiserListe();
		ecranHall3.actualiserListe();
		ecranHall4.actualiserListe();




	}

}
