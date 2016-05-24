package graphiques;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;

import application.Vol;
import application.VolArrivee;
import application.VolDepart;


public class ecranAerogare extends JFrame{


	//declarations
	private JList <VolArrivee> jlistArrivees;
	private JList <VolDepart> jlistDeparts;
	private JPanel jpane0;
	private JScrollPane scrollPane1,scrollPane2,scrollPane3,scrollPane4;
	private JTextArea jtxtarea,jtxtarea1;

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
		this.getContentPane().add(jpane0);
		jpane0.setLayout(new BorderLayout(0 ,0));
		JPanel jpane1 = new JPanel();
		jpane0.add(jpane1, BorderLayout.NORTH);




		//Instancie jtextarea
		jtxtarea= new JTextArea();
		jtxtarea.setText("NUM VOL-----TYPE VOL----DESTINATION----HEURE----PORTE EMBRAQUEMENT----HALL DE DEPART");
		jtxtarea.setFont(jtxtarea.getFont().deriveFont(Font.BOLD)); 
		jtxtarea.setEditable(false);
		jtxtarea1= new JTextArea();
		jtxtarea1.setText("NUM VOL-----TYPE VOL----PROVENANCE----HEURE----PORTE DEBRAQUEMENT----HALL D'ARRIVEE");
		jtxtarea1.setFont(jtxtarea.getFont().deriveFont(Font.BOLD)); 
		jtxtarea1.setEditable(false);




		//On récupère les vols arrivees et departs dans un tableau puis dans une JList
		jlistArrivees= new JList(VolArrivee.getlesVolsA().values().toArray());
		jlistDeparts= new JList(VolDepart.getlesVolsD().values().toArray());

		//On affecte les jlist d'arrivées et départs à la jpane, qui est lui affecté au jscrollpane
		scrollPane1 = new JScrollPane(jpane1.add(jlistArrivees));
		add(scrollPane1, BorderLayout.EAST);
		scrollPane2 = new JScrollPane(jpane1.add(jlistDeparts));
		add(scrollPane2, BorderLayout.WEST);

		add(addNorthPanel(),"North");
	}

	/**
	 * 
	 * Cette mï¿½thode retourne le Jpanel avec les titres des listes
	 * @author lb
	 * @version 1.0 - 18/05/2016
	 * @
	 */
	JPanel addNorthPanel()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));

		p.add(jtxtarea);
		p.add(jtxtarea1);

		return p;
	}

}


