package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ActionMenuRefresh extends AbstractAction{

	//Declaration
	EcranMenu ecranMenu;
	EcranAerogare ecranAerogare;
	
	
	
	/**
	 * Constructeur ActionMenuRefresh.
	 * @author lb
	 * @params EcranMenu ecranMenu
	 * @version 1.0 - 27/05/2016
	 */
	public ActionMenuRefresh(EcranMenu ecranMenu, EcranAerogare ecranAerogare) {
		this.ecranMenu=ecranMenu;
		this.ecranAerogare=ecranAerogare;
	
	
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
		ecranAerogare.actualiserListe();

	}

}
