package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import application.Passage;
import application.RetardTropTard;
import application.Vol;
import application.VolArrivee;
import application.VolDepart;


public class ActionModif extends AbstractAction{

	//Declaration
	EcranAerogare ecranAerogare;

	/**
	 * Constructeur ActionModif.
	 * @author lb
	 * @params EcranModif ecranModif
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - by ap : changement de l'attribution de la frame associé 
	 */
	public ActionModif(EcranAerogare ecranAerogare) {
		this.ecranAerogare=ecranAerogare;
	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et ouvre une pop up pour saisir les minutes de retard
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 * @version 1.1 - 29/05/2016 - np - Rajout du try/catch pour remonter le message de l'erreur
	 * @version 1.2 - 04/06/2016 - ap : Modification de l'actualisation 
	 * @version 1.3 - 04/06/2016 - ap : Modif du traitement du retour de pop-up (prise en compte de l'annulation/valeur Null)
	 * @version 1.4 - 06/06/2016 - ap : Modif du traitement : affichage d'une frame pour les options 
	 */
	public void actionPerformed(ActionEvent act) {
		//récupère la colonne
		//int col=ecr.getTab().getSelectedColumn();

		try {
			int col = 0;//ecr.getCol();
			//récupère la ligne
	
			int row = ecranAerogare.getLine();
			//affiche la valeur de la cellule selectionnée par l'utilisateur avec en param la ligne et la colonne récupérées avec les getter
			Object key = ecranAerogare.getTabSelected().getValueAt(row,col);
	
			if(Vol.getLeVol((String) key).getClass().equals(VolDepart.class)){
				EcranModifDepart ecran = new EcranModifDepart((String) key, this.ecranAerogare);
			} else if(Vol.getLeVol((String) key).getClass().equals(VolArrivee.class)){
				EcranModifArrivee ecran = new EcranModifArrivee((String) key, this.ecranAerogare);
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {System.out.println("aucune ligne sélectionnée");}
		catch(NullPointerException e){System.out.println("Selectionnez un vol.");}
	}
}