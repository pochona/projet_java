package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import application.App;
import application.Parking;
import application.Passage;


public class EcranAerogare2 extends JFrame{

		//declaration
		private App application;
		private JTable tableau;
		private JButton boutonModifier;
		private JScrollPane scrollPane;
		private TabModel tabModel;
	
	
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
			String titres[] = {"Vol arrivée", "Provenance","Heure","Vol départ","Destination","Heure","Porte","Hall"};
		
			
			tabModel = new TabModel(tablePassage, titres);
			//tabModel.isCellEditable(0,0);
			this.tableau = new JTable(tabModel);
			
			Font font = new Font("Arial",Font.BOLD,16);
			Label texte= new Label("Séléctionner un passage :");
			texte.setFont(font);
			texte.setBackground(Color.WHITE);
			
			// instance bouton valider
			boutonModifier = new JButton("Modifier");
			//ajout image au bouton
			boutonModifier.setIcon(new ImageIcon(getClass().getResource("/images/valider.png")));
			//j'associe une action a mon bouton valider (fais reference a la classe ActionValider)
			//ActionModif ecouteurModifier = new ActionModif(this);
			//boutonModifier.addActionListener(ecouteurModifier);
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scrol
			this.scrollPane = new JScrollPane(tableau);
			
			this.setLayout(new BorderLayout());
			this.scrollPane = new JScrollPane(this.tableau);
			this.getContentPane().add(this.scrollPane,BorderLayout.CENTER);
			this.getContentPane().add(texte,BorderLayout.NORTH);
			this.getContentPane().add(boutonModifier,BorderLayout.SOUTH);
			

		}   

		/**
		 * Méthode getModel()
		 * Retourne le tableau Model contenant la liste des vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 * 
		 */
		public TabModel getModelA() {
			return tabModel;
		}
		

		
		/**
		 * Méthode getTab()
		 * Retourne le tableau contenant le model contenant les vols
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 */
		public JTable getTabP(){
			return tableau;
		}
	
		
		
}


