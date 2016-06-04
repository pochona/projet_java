package graphiques;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;

import application.App;
import application.Vol;
import application.VolArrivee;
import application.VolDepart;


public class EcranAerogare extends JFrame{

		//declaration
		private App application;
		private JTable tableauA;
		private JTable tableauD;
		private JScrollPane scrollPaneA;
		private JScrollPane scrollPaneD;
		private TabModel tabModelA;
		private TabModel tabModelD;
	
		/**
		 * Constructeur EcranAerogare.
		 * Ce constructeur appel la méthode init()
		 * @author lb
		 * @version 1.0 - 24/05/2016
		 * @
		 */
		public EcranAerogare(App a){
			this.application = a;
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
		
			
			tabModelA = new TabModel(tableVolsA, titresA);
			//tabModel.isCellEditable(0,0);
			this.tableauA = new JTable(tabModelA);
			
			
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
			tabModelD = new TabModel(tableVolsD, titresD);
			//tabModel.isCellEditable(0,0);
			this.tableauD = new JTable(tabModelD);
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scroll
			
			this.scrollPaneA = new JScrollPane(tableauA);
			this.scrollPaneD = new JScrollPane(tableauD);
			
			this.getContentPane().add(scrollPaneA);
			this.getContentPane().add(scrollPaneD);
		}   

		/**
		 * Méthode getModel()
		 * Retourne le tableau Model contenant la liste des vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 * 
		 */
		public TabModel getModelA() {
			return tabModelA;
		}
		
		/**
		 * Méthode getModel()
		 * Retourne le tableau Model contenant la liste des vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 * 
		 */
		public TabModel getModelD() {
			return tabModelD;
		}
		
		/**
		 * Méthode getTab()
		 * Retourne le tableau contenant le model contenant les vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 */
		public JTable getTabA(){
			return tableauA;
		}
		
		public JTable getTabD(){
			return tableauD;
		}
		
		/**
		 * Méthode actualiserListe()
		 * Méthode qui permet d'actualiser la liste après une modification. Le tableau est rechargé depuis la liste des vols.
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 * 
		 */
		public void actualiserListe(){
			
			
			this.getContentPane().remove(this.scrollPaneA);
			this.getContentPane().remove(this.scrollPaneD);
			this.scrollPaneA = null;
			this.scrollPaneD = null;
			this.tabModelA =null;
			this.tabModelD =null;
			this.tableauA= null;
			this.tableauD= null;
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
		
			
			tabModelA = new TabModel(tableVolsA, titresA);
			//tabModel.isCellEditable(0,0);
			this.tableauA = new JTable(tabModelA);
			
			
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
			tabModelD = new TabModel(tableVolsD, titresD);
			
			this.tableauD = new JTable(tabModelD);
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scroll
			
			this.scrollPaneA = new JScrollPane(tableauA);
			this.scrollPaneD = new JScrollPane(tableauD);
			
			this.getContentPane().add(scrollPaneA);
			this.getContentPane().add(scrollPaneD);
			this.getContentPane().revalidate();
			
		}

}


