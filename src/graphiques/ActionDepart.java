package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ActionDepart extends AbstractAction {

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
		public ActionDepart(EcranModifArrivee ecrModifArrivee, EcranAerogare ecranAerogare) {
			this.ecrModifArrivee=ecrModifArrivee;
			this.ecrAerogare=ecranAerogare;
		}


		/**
		 * Méthode actionPerformed.
		 * Cette méthode se déclenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle du vol arrivée associé
		 * @author lb
		 * @params ActionEvent arg0 : l'action de cliquer
		 * @version 1.0 - 06/06/2016
		 */
		public void actionPerformed(ActionEvent e) {
			EcranModifDepart ecrModifDepart = new EcranModifDepart(ecrModifArrivee.getLeVolDepart(),ecrAerogare);
			ecrModifDepart.setVisible(true);
			ecrModifDepart.setSize(700,700);
			ecrModifDepart.setLocationRelativeTo(null);
			ecrModifDepart.setTitle("Modifier un vol: ");
			
		}

		
	
}
