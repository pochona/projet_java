package graphiques;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import application.Vol;


public class ActionSupprimer extends AbstractAction{
	EcranSupprimer ecr;

	
	/**
	 * Constructeur ActionSupprimer.
	 * @author lb
	 * @params EcranSupprimer ecran
	 * @version 1.0 - 27/05/2016
	 */
	public ActionSupprimer (EcranSupprimer ecran){
		this.ecr=ecran;
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et supprime le vol selectionné dans la liste
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent arg0) {

		//récupère la colonne
		//int col=ecr.getTab().getSelectedColumn();
		int col = 0;//ecr.getCol();
		//récupère la ligne
		int row = ecr.getLine();
		//affiche la valeur de la cellule selectionnée par l'utilisateur avec en param la ligne et la colonne récupérées avec les getter
		Object key = ecr.getModel().getValueAt(row,col);
		//pop-up 
		JOptionPane jop = new JOptionPane();            
		int option = jop.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le vol numéro: "+key, "Supprimer vol", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);       
		if(option == JOptionPane.OK_OPTION){
			//si l'utilisateur click sur oui on supprime le vol selectionné
			Vol.supprimerVol(key);
			//appel de la methode init de la classe ecranSupprimer
			ecr.init();
		}
	}
}



