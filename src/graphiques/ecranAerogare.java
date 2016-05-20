package graphiques;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.Vol;
import application.VolArrivee;
import application.VolDepart;


public class ecranAerogare extends JFrame{
	

	//declarations
	private JList <VolArrivee> jlistArrivees;
	private JList <VolDepart> jlistDeparts;
	private JPanel jpane0;
	private JScrollPane scrollPane1,scrollPane2;
	
	/**
	 * Constructeur ecranAerogare.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 18/05/2016
	 * @
	 */
	public ecranAerogare(){
	init();
	}

	/**
	 * Méthode qui initialise
	 * 
	 * Cette méthode instancie tous les composants de la fenêtre.
	 * @author lb
	 * @version 1.0 - 18/05/2016
	 * @
	 */
	public void init() {
				
	//Instancie les panels
	jpane0 = new JPanel();
	//jlistArrivees= new JList(lesVols.keySet().toArray());
	jlistDeparts= new JList();


			 
	//ScrollPanes et disposition de type BorderLayout
	scrollPane1 = new JScrollPane(jlistArrivees);
	add(scrollPane1, BorderLayout.EAST);
	scrollPane2 = new JScrollPane(jlistDeparts);
	add(scrollPane2, BorderLayout.WEST);
	add(jpane0, BorderLayout.SOUTH);
	}
}
