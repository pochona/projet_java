package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.App;
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
		private JButton boutonModifier;
		private JTable tableauSelected;
		private TabModel tabSelected;
	
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
			this.setSize(700,700);
			this.setLocationRelativeTo(null);
			this.setTitle("Ecran Aerogare");
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		/**
		 * Méthode init()
		 * 
		 * Cette méthode instancie tous les composants de la fenêtre.
		 * @author lb
		 * @version 1.0 - 24/05/2016
		 * @version 2.0 - 06/06/2016 by ap : reprise avec les nouvelles intefaces
		 * 
		 */
		public void init() {

			//On crée l'objet tableVolsA (vols d'arrivées)
			Object[][] tableVolsA = new Object[VolArrivee.getlesVolsA().keySet().size()][8];

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
				tableVolsA[index][6] = volA.getLeParking().getNom();
				tableVolsA[index][7] = volA.isAnnule()?"Vol annulé":volA.getRetard();
				index++;
			}

			//Les titres des colonnes
			String  titresA[] = {"Vol", "Provenance","Heure","Porte","Hall","Avion", "Parking" ,"Retard"};
		
			
			tabModelA = new TabModel(tableVolsA, titresA);
			//tabModel.isCellEditable(0,0);
			this.tableauA = new JTable(tabModelA);


			
			
			//On crée l'objet tableVolsD (vols de départ)
			Object[][] tableVolsD = new Object[VolDepart.getlesVolsD().keySet().size()][8];

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
				tableVolsD[indexD][6] = volD.getLeParking().getNom();
				tableVolsD[indexD][7] = volD.isAnnule()?"Vol annulé":volD.getRetard();
				indexD++;
			}

			//Les titres des colonnes
			String  titresD[] = {"Vol", "Destination","Heure","Porte","Hall","Avion", "Parking" ,"Retard"};
			tabModelD = new TabModel(tableVolsD, titresD);
			//tabModel.isCellEditable(0,0);
			this.tableauD = new JTable(tabModelD);
			
			
			tableauA.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				private EcranAerogare aerogare;
				public void valueChanged(ListSelectionEvent arg0) {
					this.aerogare.getTabD().clearSelection();
					this.aerogare.setTabSelected(this.aerogare.getModelA());
					this.aerogare.setTableauSelected(this.aerogare.getTabA());
				}
				private ListSelectionListener init(EcranAerogare var){
					aerogare = var;
					return this;
				}
			}.init(this));
			
			tableauD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				private EcranAerogare aerogare;
				public void valueChanged(ListSelectionEvent arg0) {
					this.aerogare.getTabA().clearSelection();
					this.aerogare.setTabSelected(this.aerogare.getModelD());
					this.aerogare.setTableauSelected(this.aerogare.getTabD());
				}
				private ListSelectionListener init(EcranAerogare var){
					aerogare = var;
					return this;
				}
			}.init(this));
			
			Font font = new Font("Arial",Font.BOLD,16);
			Label texte= new Label("Choisir un vol à modifier :");
			texte.setFont(font);
			texte.setBackground(Color.WHITE);
			
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scroll
			
			this.scrollPaneA = new JScrollPane(tableauA);

			this.scrollPaneD = new JScrollPane(tableauD);

			// instance bouton valider
			this.boutonModifier = new JButton("Modifier");
			//ajout image au bouton
			this.boutonModifier.setIcon(new ImageIcon(getClass().getResource("/images/valider.png")));
			//j'associe une action a mon bouton valider (fais reference a la classe ActionValider)
			ActionModif ecouteurModifier = new ActionModif(this);
			this.boutonModifier.addActionListener(ecouteurModifier);

			//disposition des composants dans la fenetre
			
			JPanel panel_base = new JPanel();
			this.getContentPane().add(panel_base);
			panel_base.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_scroll = new JPanel();
			panel_scroll.setLayout(new GridLayout(1, 1));
			panel_base.add(panel_scroll);
			
			panel_scroll.add(this.scrollPaneA);
			
			panel_scroll.add(this.scrollPaneD);
			
			panel_base.add(this.boutonModifier, BorderLayout.SOUTH);
			panel_base.add(texte, BorderLayout.NORTH);
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
		 * Méthode getTabA()
		 * Retourne le tableau contenant le model contenant les vols arrive
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 */
		public JTable getTabA(){
			return tableauA;
		}
		
		/**
		 * Méthode getTabD()
		 * Retourne le tableau contenant le model contenant les vols depart
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 */
		public JTable getTabD(){
			return tableauD;
		}
		
		/**
		 * Méthode setTabSelected()
		 * Permet de retenir la tabModel selectionné
		 * @author ap
		 * @version 1.0 - 06/06/2016
		 */
		public void setTabSelected(TabModel tm){
			this.tabSelected = tm;
		}
		
		/**
		 * Méthode getTabSelected()
		 * Permet de retourner la tabModel selectionné
		 * @author ap
		 * @return TabModel : la tab model selectionné
		 * @version 1.0 - 06/06/2016
		 */
		public TabModel getTabSelected(){
			return this.tabSelected;
		}
		
		/**
		 * Méthode setTabSelected()
		 * Permet de retenir la tabModel selectionné
		 * @author ap
		 * @version 1.0 - 06/06/2016
		 */
		public void setTableauSelected(JTable jt){
			this.tableauSelected = jt;
		}
		
		/**
		 * Méthode getTabSelected()
		 * Permet de retourner la tabModel selectionné
		 * @author ap
		 * @return TabModel : la tab model selectionné
		 * @version 1.0 - 06/06/2016
		 */
		public JTable getTableauSelected(){
			return this.tableauSelected;
		}
		
		
		/**
		 * Méthode getLine()
		 * Retourne la ligne selectionnée par l'utilisateur
		 * @author lb
		 * @version 1.0 - 27/05/2016
		 */
		public int getLine(){
			return this.getTableauSelected().getSelectedRow();
		}

		/**
		 * Méthode getCol()
		 * Retourne la colonne selectionnée par l'utilisateur
		 * @author lb
		 * @version 1.0 - 27/05/2016
		 */
		public int getCol(){
			return this.getTableauSelected().getSelectedColumn();
		}
		
		/**
		 * Méthode actualiserListe()
		 * Méthode qui permet d'actualiser la liste après une modification. Le tableau est rechargé depuis la liste des vols.
		 * @author lb
		 * @version 1.0 - 04/06/2016
		 * 
		 */
		public void actualiserListe(){
			
			
			this.getContentPane().removeAll();
			this.scrollPaneA = null;
			this.scrollPaneD = null;
			this.tabModelA = null;
			this.tabModelD = null;
			this.tableauA = null;
			this.tableauD = null;
			this.tableauSelected = null;
			this.tabSelected = null;
			//On crée l'objet tableVolsA (vols d'arrivées)
			Object[][] tableVolsA = new Object[VolArrivee.getlesVolsA().keySet().size()][8];

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
				tableVolsA[index][6] = volA.getLeParking().getNom();
				tableVolsA[index][7] = volA.isAnnule()?"Vol annulé":volA.getRetard();
				index++;
			}

			//Les titres des colonnes
			String  titresA[] = {"Vol", "Provenance","Heure","Porte","Hall","Avion", "Parking" ,"Retard"};
		
			tabModelA = new TabModel(tableVolsA, titresA);
			//tabModel.isCellEditable(0,0);
			this.tableauA = new JTable(tabModelA);
			
			//On crée l'objet tableVolsD (vols de départ)
			Object[][] tableVolsD = new Object[VolDepart.getlesVolsD().keySet().size()][8];

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
				tableVolsD[indexD][6] = volD.getLeParking().getNom();
				tableVolsD[indexD][7] = volD.isAnnule()?"Vol annulé":volD.getRetard();
				indexD++;
			}

			//Les titres des colonnes
			String  titresD[] = {"Vol", "Destination","Heure","Porte","Hall","Avion", "Parking" ,"Retard"};
			tabModelD = new TabModel(tableVolsD, titresD);
			//tabModel.isCellEditable(0,0);
			this.tableauD = new JTable(tabModelD);
			
			
			tableauA.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				private EcranAerogare aerogare;
				public void valueChanged(ListSelectionEvent arg0) {
					this.aerogare.getTabD().clearSelection();
					this.aerogare.setTabSelected(this.aerogare.getModelA());
					this.aerogare.setTableauSelected(this.aerogare.getTabA());
				}
				private ListSelectionListener init(EcranAerogare var){
					aerogare = var;
					return this;
				}
			}.init(this));
			
			tableauD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				private EcranAerogare aerogare;
				public void valueChanged(ListSelectionEvent arg0) {
					this.aerogare.getTabA().clearSelection();
					this.aerogare.setTabSelected(this.aerogare.getModelD());
					this.aerogare.setTableauSelected(this.aerogare.getTabD());
				}
				private ListSelectionListener init(EcranAerogare var){
					aerogare = var;
					return this;
				}
			}.init(this));
			
			Font font = new Font("Arial",Font.BOLD,16);
			Label texte= new Label("Choisir un vol à modifier :");
			texte.setFont(font);
			texte.setBackground(Color.WHITE);
			
			
			this.setLayout(new GridLayout(1,1));
			//ajout de contentPane dans un scroll
			
			this.scrollPaneA = new JScrollPane(tableauA);

			this.scrollPaneD = new JScrollPane(tableauD);

			// instance bouton valider
			this.boutonModifier = new JButton("Modifier");
			//ajout image au bouton
			this.boutonModifier.setIcon(new ImageIcon(getClass().getResource("/images/valider.png")));
			//j'associe une action a mon bouton valider (fais reference a la classe ActionValider)
			ActionModif ecouteurModifier = new ActionModif(this);
			this.boutonModifier.addActionListener(ecouteurModifier);

			//disposition des composants dans la fenetre
			
			JPanel panel_base = new JPanel();
			this.getContentPane().add(panel_base);
			panel_base.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_scroll = new JPanel();
			panel_scroll.setLayout(new GridLayout(1, 1));
			panel_base.add(panel_scroll);
			
			panel_scroll.add(this.scrollPaneA);
			
			panel_scroll.add(this.scrollPaneD);
			
			panel_base.add(this.boutonModifier, BorderLayout.SOUTH);
			panel_base.add(texte, BorderLayout.NORTH);
			
			this.getContentPane().revalidate();
	
		}

}


