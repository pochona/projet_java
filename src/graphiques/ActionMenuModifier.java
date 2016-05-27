package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class ActionMenuModifier extends AbstractAction{

	//Declaration
	EcranMenu ecranMenu;

	public ActionMenuModifier(EcranMenu ecranMenu) {
		this.ecranMenu=ecranMenu;
	}

	public void actionPerformed(ActionEvent e) {
		EcranModif ecranModif = new EcranModif();
		ecranModif.setVisible(true);
		ecranModif.setSize(700,700);
		ecranModif.setLocationRelativeTo(null);
		ecranModif.setTitle("Modifier un vol: ");
	}


}
