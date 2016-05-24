package graphiques;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import application.Vol;
import application.VolArrivee;

public class ecranHall1 extends JFrame{
//test
	//d�clarations
	private JList <Vol> jlistVols;
	private JTextArea jtxtarea;
	private JPanel jpane0;
	private JScrollPane scrollPane1;
	private JList <VolArrivee> jlistArrivees;

	/**
	 * Constructeur ecranHall1.
	 * Ce constructeur appel la m�thode init()
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @
	 */
	public ecranHall1(){
		init();
	}

	/**
	 * M�thode qui initialise
	 * 
	 * Cette m�thode instancie tous les composants de la fen�tre.
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @
	 */
	public void init() {

		Object[][] tableData = new Object[VolArrivee.getlesVolsA().keySet().size()][2];


		int index = 0;
		for (String key : VolArrivee.getlesVolsA().keySet())
		{
			VolArrivee volA = VolArrivee.getlesVolsA().get(key);
			tableData[index][0] = volA.getHeureArrivee();
			tableData[index][1] = volA.getProvenance();
			index++;
		}

		//Les titres des colonnes
		String  title[] = {"Heure arriv�e", "Provenance"};
		JTable tableau = new JTable(tableData, title);
		//Nous ajoutons notre tableau � notre contentPane dans un scroll
		//Sinon les titres des colonnes ne s'afficheront pas !
		this.getContentPane().add(new JScrollPane(tableau));
	}   


}



