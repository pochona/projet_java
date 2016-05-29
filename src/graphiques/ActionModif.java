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

import application.RetardTropTard;
import application.Vol;


public class ActionModif extends AbstractAction{

	//Declaration
	EcranModif ecranModif;
	
	/**
	 * Constructeur ActionModif.
	 * @author lb
	 * @params EcranModif ecranModif
	 * @version 1.0 - 27/05/2016
	 */

	public ActionModif(EcranModif ecranModif) {
		this.ecranModif=ecranModif;

	}

	/**
	 * Méthode actionPerformed.
	 * Cette méthode se déclenche au click de l'utilisateur et ouvre une pop up pour saisir les minutes de retard
	 * @author lb
	 * @params ActionEvent arg0 : l'action de cliquer
	 * @version 1.0 - 27/05/2016
	 * @version 1.1 - 29/05/2016 - np - Rajout du try/catch pour remonter le message de l'erreur
	 */
	public void actionPerformed(ActionEvent arg0) {
		try {
			//Ouverture de la fenetre EcranModif2

			//récupère la colonne
			//int col=ecr.getTab().getSelectedColumn();
			int col = 0;//ecr.getCol();
			//récupère la ligne
			int row = ecranModif.getLine();
			//affiche la valeur de la cellule selectionnée par l'utilisateur avec en param la ligne et la colonne récupérées avec les getter
			Object key = ecranModif.getModel().getValueAt(row,col);
			//ecranModif.modifHoraire(key);
		
			JOptionPane jop = new JOptionPane();
			String minutes = JOptionPane.showInputDialog(null, "Saisir les minutes de retard");
			//Vol.ModifierHeure(minutes, key);
			Vol.Retarder(minutes, key);
			ecranModif.getModel().fireTableDataChanged();
			JOptionPane.showMessageDialog(null, "Vol "+key+" modifié!", "InfoBox: " + "Modification", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (RetardTropTard e){
			System.out.println(e);
		}
			}
}