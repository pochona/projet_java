package graphiques;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.Vol;

public class ecranHall4 extends JFrame{


	/**
	 * Constructeur ecranHall3.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @
	 */
	public ecranHall4(){
		init();
	}

	/**
	 * Méthode qui initialise
	 * 
	 * Cette méthode instancie tous les composants de la fenêtre.
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @
	 */
	public void init() {

		
		Object[][] tableHall = new Object[Vol.getVolsByHall("4").keySet().size()][4];


		int index = 0;
		for (String keyH : Vol.getVolsByHall("4").keySet())
		{
			Vol vol = Vol.getVolsByHall("4").get(keyH);
			tableHall[index][0] = vol.getLaPorte();
			tableHall[index][1] = vol.getLAvion();
			tableHall[index][2] = vol.getLAvion().getType();
			tableHall[index][3] = vol.getLePassage();
			
		
			index++;
		}

		//Les titres des colonnes
		String  titre[] = {"Porte","Avion","Type","heure"};
		JTable tableau = new JTable(tableHall, titre);
		//ajout d'un contentPane dans un scroll
		this.getContentPane().add(new JScrollPane(tableau));
	}   



}
