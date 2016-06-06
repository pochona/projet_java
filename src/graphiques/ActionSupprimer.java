package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import application.Vol;
import application.VolArrivee;


public class ActionSupprimer extends AbstractAction{
	private Vol leVol;
	private EcranModif ecranModif;
	private boolean deleteAll = false;

	/**
	 * Constructeur ActionSupprimer.
	 * @author lb
	 * @params EcranSupprimer ecran
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - 06/06/2016 by ap : changement du constructeur avec les nouvelles intefaces
	 */
	public ActionSupprimer (Vol leVol, EcranModif ecran){
		this.leVol = leVol;
		this.ecranModif = ecran;
	}
	
	/**
	 * Constructeur ActionSupprimer.
	 * @author ap
	 * @params EcranSupprimer ecran
	 * @version 1.0 - 06/06/2016
	 */
	public ActionSupprimer (Vol leVol, EcranModif ecran, boolean b){
		this.leVol = leVol;
		this.ecranModif = ecran;
		this.deleteAll = b;
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et supprime le vol selectionné dans la liste
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 * @version 1.1 - 06/06/2016 by ap : Modification de l'action, refonte totale a cause des nouvelles interfaces
	 */
	public void actionPerformed(ActionEvent arg0) {
		int option;
 
		if(this.deleteAll || this.leVol.getClass().equals(VolArrivee.class)){
			option = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer les vols numéro: "+this.leVol.getNumVol()+ " et, " +this.leVol.getLePassage().getMonVolDepart().getNumVol(), "Supprimer vol", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);       
		} else {
			option = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le vol numéro: "+this.leVol.getNumVol(), "Supprimer vol", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);       
		}
		
		if(option == JOptionPane.OK_OPTION){
			if(this.deleteAll){
				Vol.supprimerVol(leVol.getNumVol());
				Vol.supprimerVol(leVol.getLePassage().getMonVolArrivee().getNumVol());
			} else {
				Vol.supprimerVol(leVol.getNumVol());
			}
			//appel de la methode recharge de la classe ecranSupprimer
			this.ecranModif.actualiser();

			JOptionPane.showMessageDialog(null, "Vol annulé!", "InfoBox: " + "Annulation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}



