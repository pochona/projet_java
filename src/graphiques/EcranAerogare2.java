package graphiques;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import application.App;
import application.Parking;
import application.Passage;


public class EcranAerogare2 extends JFrame{

		//declaration
		private App application;
		private JTable tableauA;
		private JScrollPane scrollPaneA;
		private TabModel tabModelA;
	
	
		/**
		 * Constructeur EcranAerogare.
		 * Ce constructeur appel la méthode init()
		 * @author lb
		 * @version 1.0 - 24/05/2016
		 * @
		 */
		public EcranAerogare2(App a){
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

			
			ArrayList<Passage> list = Passage.getlesPassages();
			//On crée l'objet tableVolsA (vols d'arrivées)
			Object[][] tablePassage = new Object[list.size()][8];

			//parcours de l'arraylist et affectation des valeurs dans chaque colonne de chaque ligne du passage.
			Iterator<Passage> it = list.iterator();
			int index = 0;
			while(it.hasNext()){
				Passage p = it.next();
				tablePassage[index][0] = p.getMonVolArrivee().getNumVol();
				tablePassage[index][1] = p.getMonVolArrivee().getProvenance();
				tablePassage[index][2] = p.getMonVolArrivee().getHoraire();
				tablePassage[index][3] = p.getMonVolDepart().getNumVol();
				tablePassage[index][4] = p.getMonVolDepart().getDestination();
				tablePassage[index][5] = p.getMonVolDepart().getHoraire();
				tablePassage[index][6] = p.getMonVolDepart().getLeNomDeLaPorte();
				tablePassage[index][7] = p.getMonVolDepart().getLeNomDuHall();

				index ++;
			}

			//Les titres des colonnes
			String  titresA[] = {"Vol arrivée", "Provenance","Heure","Vol départ","Destination","Heure","Porte","Hall"};
		
			
			tabModelA = new TabModel(tablePassage, titresA);
			//tabModel.isCellEditable(0,0);
			this.tableauA = new JTable(tabModelA);
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scrol
			this.scrollPaneA = new JScrollPane(tableauA);
			this.getContentPane().add(scrollPaneA);
			
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
		 * Méthode getTab()
		 * Retourne le tableau contenant le model contenant les vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 */
		public JTable getTabP(){
			return tableauA;
		}
	
		
		
}


