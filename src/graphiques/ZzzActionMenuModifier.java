package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ZzzActionMenuModifier extends AbstractAction{

	//Declaration
	ZzzEcranMenu ecranMenu;


	/**
	 * Constructeur ActionMenuModifier.
	 * @author lb
	 * @params EcranMenu ecranMenu
	 * @version 1.0 - 27/05/2016
	 */
	public ZzzActionMenuModifier(ZzzEcranMenu ecranMenu) {
		this.ecranMenu=ecranMenu;
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle de modification
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent e) {
		Zzz_EcranModif ecranModif = new Zzz_EcranModif();
		ecranModif.setVisible(true);
		ecranModif.setSize(700,700);
		ecranModif.setLocationRelativeTo(null);
		ecranModif.setTitle("Modifier un vol: ");
	}


}
