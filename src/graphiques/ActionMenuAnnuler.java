package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class ActionMenuAnnuler extends AbstractAction{

	//Declaration
	EcranMenu ecranMenu;
	
	/**
	 * Constructeur ActionMenuAnnuler.
	 * @author lb
	 * @params EcranMenu ecranMenu
	 * @version 1.0 - 27/05/2016
	 */
	public ActionMenuAnnuler(EcranMenu ecranMenu) {
		this.ecranMenu=ecranMenu;
	}

	/**
	 * M�thode actionPerformed.
	 * Cette m�thode se d�clenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle des annulations vols
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent arg0) {
		EcranSupprimer ecranSupprimer = new EcranSupprimer();
		ecranSupprimer.setVisible(true);
		ecranSupprimer.setSize(700,700);
		ecranSupprimer.setLocationRelativeTo(null);
		ecranSupprimer.setTitle("Supprimer un vol");
		
		}

}
