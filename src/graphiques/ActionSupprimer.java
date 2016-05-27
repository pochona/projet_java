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
	 * M�thode actionPerformed.
	 * Cette m�thode se d�clenche au click de l'utilisateur et supprime le vol selectionn� dans la liste
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 */
	public void actionPerformed(ActionEvent arg0) {

		//r�cup�re la colonne
		//int col=ecr.getTab().getSelectedColumn();
		int col = 0;//ecr.getCol();
		//r�cup�re la ligne
		int row = ecr.getLine();
		//affiche la valeur de la cellule selectionn�e par l'utilisateur avec en param la ligne et la colonne r�cup�r�es avec les getter
		Object key = ecr.getModel().getValueAt(row,col);
		//pop-up 
		JOptionPane jop = new JOptionPane();            
		int option = jop.showConfirmDialog(null, "Etes-vous s�r de vouloir supprimer le vol num�ro: "+key, "Supprimer vol", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);       
		if(option == JOptionPane.OK_OPTION){
			//si l'utilisateur click sur oui on supprime le vol selectionn�
			Vol.supprimerVol(key);
			//appel de la methode init de la classe ecranSupprimer
			ecr.init();
		}
	}
}



