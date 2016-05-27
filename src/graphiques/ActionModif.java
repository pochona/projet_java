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

import application.Vol;


public class ActionModif extends AbstractAction{

	//Declaration
	EcranModif ecranModif;

	public ActionModif(EcranModif ecranModif) {
		this.ecranModif=ecranModif;

	}

	public void actionPerformed(ActionEvent arg0) {
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
		String minutes = JOptionPane.showInputDialog(null, "Saisir les minutes");
		Vol.ModifierHeure(minutes, key);
		ecranModif.getModel().fireTableDataChanged();
		JOptionPane.showMessageDialog(null, "Vol "+key+" modifié!", "InfoBox: " + "Modification", JOptionPane.INFORMATION_MESSAGE);
	}
}