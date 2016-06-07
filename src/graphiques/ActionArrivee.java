package graphiques;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


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
		 * Méthode actionPerformed.
		 * Cette méthode se déclenche au click de l'utilisateur et ouvre une nouvelle fenetre, celle du vol arrivée associé
		 * @author lb
		 * @params ActionEvent arg0 : l'action de cliquer
		 * @version 1.0 - 06/06/2016
		 */
		public void actionPerformed(ActionEvent e) {
			EcranModifArrivee ecranModifArrivee = new EcranModifArrivee(ecrModifDepart.getLeVolArrivee(),ecrAerogare);
			ecranModifArrivee.setVisible(true);
			ecranModifArrivee.setSize(700,700);
			ecranModifArrivee.setLocationRelativeTo(null);
			ecranModifArrivee.setTitle("Modifier un vol: ");
			
		}

		
	
}
