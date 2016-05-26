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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;

import application.Vol;
import application.VolArrivee;
import application.VolDepart;


public class EcranAerogare extends JFrame{


		/**
		 * Constructeur EcranAerogare.
		 * Ce constructeur appel la méthode init()
		 * @author lb
		 * @version 1.0 - 24/05/2016
		 * @
		 */
		public EcranAerogare(){
			init();
		}

		/**
		 * Méthode init()
		 * 
		 * Cette méthode instancie tous les composants de la fenêtre.
		 * @author lb
		 * @version 1.0 - 24/05/2016
		 * @
		 */
		public void init() {

			//On crée l'objet tableVolsA (vols d'arrivées)
			Object[][] tableVolsA = new Object[VolArrivee.getlesVolsA().keySet().size()][6];

			//parcours de la HashMap lesVolsArrivee de la classe VolArrivee et affectation des valeurs dans chaque colonne de chaque ligne de vol d'arrivée.
			int index = 0;
			for (String key : VolArrivee.getlesVolsA().keySet())
			{
				VolArrivee volA = VolArrivee.getlesVolsA().get(key);
				tableVolsA[index][0] = volA.getNumVol();
				tableVolsA[index][1] = volA.getProvenance();
				tableVolsA[index][2] = volA.getHoraire();
				tableVolsA[index][3] = volA.getLeNomDeLaPorte();
				tableVolsA[index][4] = volA.getLeNomDuHall();
				tableVolsA[index][5] = volA.getLAvion().getImmat();
				index++;
			}

			//Les titres des colonnes
			String  titresA[] = {"Vol", "Provenance","Heure","Porte","Hall","Avion"};
			JTable tableauA = new JTable(tableVolsA, titresA);
			
			
			
			
			//On crée l'objet tableVolsD (vols de départ)
			Object[][] tableVolsD = new Object[VolDepart.getlesVolsD().keySet().size()][6];

			//parcours de la HashMap lesVolsDepart de la classe VolDepart et affectation des valeurs dans chaque colonne de chaque ligne de vol de départ.
			int indexD = 0;
			for (String keyD : VolDepart.getlesVolsD().keySet())
			{
				VolDepart volD = VolDepart.getlesVolsD().get(keyD);
				tableVolsD[indexD][0] = volD.getNumVol();
				tableVolsD[indexD][1] = volD.getDestination();
				tableVolsD[indexD][2] = volD.getHoraire();
				tableVolsD[indexD][3] = volD.getLeNomDeLaPorte();
				tableVolsD[indexD][4] = volD.getLeNomDuHall();
				tableVolsD[indexD][5] = volD.getLAvion().getImmat();
				indexD++;
			}

			//Les titres des colonnes
			String  titresD[] = {"Vol", "Destination","Heure","Porte","Hall","Avion"};
			JTable tableauD = new JTable(tableVolsD, titresD);
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scroll
			this.getContentPane().add(new JScrollPane(tableauA));
			this.getContentPane().add(new JScrollPane(tableauD));

		}   


}


