package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class ActionMenuAnnuler extends AbstractAction{

	//Declaration
	EcranMenu ecranMenu;
	
	public ActionMenuAnnuler(EcranMenu ecranMenu) {
		this.ecranMenu=ecranMenu;
	}

	public void actionPerformed(ActionEvent arg0) {
		EcranSupprimer ecranSupprimer = new EcranSupprimer();
		ecranSupprimer.setVisible(true);
		ecranSupprimer.setSize(700,700);
		ecranSupprimer.setLocationRelativeTo(null);
		ecranSupprimer.setTitle("Supprimer un vol");
	}

}
