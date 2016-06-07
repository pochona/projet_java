package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ZzzActionMenuAnnuler extends AbstractAction{

	//Declaration
	ZzzEcranMenu ecranMenu;
	
	/**
	 * Constructeur ActionMenuAnnuler.
	 * @author lb
	 * @params EcranMenu ecranMenu
	 * @version 1.0 - 27/05/2016
	 */
	public ZzzActionMenuAnnuler(ZzzEcranMenu ecranMenu) {
		this.ecranMenu=ecranMenu;
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle des annulations vols
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent arg0) {
		Zzz_EcranSupprimer ecranSupprimer = new Zzz_EcranSupprimer();
		ecranSupprimer.setVisible(true);
		ecranSupprimer.setSize(700,700);
		ecranSupprimer.setLocationRelativeTo(null);
		ecranSupprimer.setTitle("Supprimer un vol");
		
		}

}
