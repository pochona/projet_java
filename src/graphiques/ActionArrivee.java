package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class ActionArrivee extends AbstractAction {

	//Declaration
	EcranModifDepart ecrModifDepart;
	EcranModifArrivee ecrModifArrivee;
	EcranAerogare ecrAerogare;


		/**
		 * Constructeur ActionMenuModifier.
		 * @author lb
		 * @params EcranMenu ecranMenu
		 * @version 1.0 - 06/06/2016
		 */
		public ActionArrivee(EcranModifDepart ecrModifDepart, EcranAerogare ecranAerogare) {
			this.ecrModifDepart=ecrModifDepart;
			this.ecrAerogare=ecranAerogare;
		}


		/**
		 * M�thode actionPerformed.
		 * Cette m�thode se d�clenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle du vol arriv�e associ�
		 * @author lb
		 * @params ActionEvent arg0 : l'action de cliquer
		 * @version 1.0 - 06/06/2016
		 */
		public void actionPerformed(ActionEvent e) {
			this.ecrAerogare.getAppli().setEcranModif(new EcranModifArrivee(ecrModifDepart.getLeVolArrivee(),ecrAerogare));
		}

		
	
}
