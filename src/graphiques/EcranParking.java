package graphiques;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.App;
import application.Hall;
import application.Parking;
import application.Passage;
import application.Vol;
import application.VolArrivee;
import application.VolDepart;

@SuppressWarnings("serial")
public class EcranParking extends JFrame {

	/**
	 * Les différents boutons de la frame
	 */
	private JButton boutonAnnuler, boutonRetarder, boutonAnnulerTout, boutonArrivee;
	
	/**
	 * L'application
	 */
	private App application;
	
	private JComboBox boxHall, boxParking;
	
	private TabModel tabModel;
	
	private JTable jTable;
	
	private JScrollPane scrollPane;
	
	private JPanel panel_scroll;
	
	/**
	 * Méthode EcranModifDepart
	 * Constructeur de EcranModifDepart.
	 * 
	 * @author ap
	 * @param String n : String du nom de l'avion
	 * @param EcranAerogare ae : ecran aerogare lié à la frame
	 * @version 1.0 - 07/06/2016 
	 */
	public EcranParking(App app){
		this.application = app;
		this.init();
	}
	
	/**
	 * Méthode init
	 * Cette méthode va initialiser la frame
	 * 
	 * @author ap
	 * @version 1.0 - 07/06/2016 
	 */
	private void init(){
		this.setBounds(100, 100, 500, 300);

		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 3));
		
		ArrayList<String> lesHalls = Hall.getNomsDesHalls();
		
		// On initialise la liste des parkings avec le hall e, premier position
		ArrayList<String> lesParkings = Parking.getLesParkings(Hall.find(lesHalls.get(0)));
		JLabel lblNewLabel = new JLabel("Hall :");
		panel.add(lblNewLabel);
		
		this.boxHall = new JComboBox(lesHalls.toArray());
		
		this.boxHall.addActionListener(new ActionListener(){
			private EcranParking parking;
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox)event.getSource();

				this.parking.loadParking(Hall.find((String)cb.getSelectedItem()));
			}
			
			private ActionListener init(EcranParking var){
				parking = var;
				return this;
			}
		}.init(this));
		
		panel.add(boxHall);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		JLabel lblNewLabel_1 = new JLabel("Parking :");
		panel.add(lblNewLabel_1);
		
		this.boxParking = new JComboBox(lesParkings.toArray());
		this.boxParking.addActionListener(new ActionListener(){
			private EcranParking ecranParking;
			public void actionPerformed(ActionEvent event) {
				JComboBox cb = (JComboBox)event.getSource();
				// je suis obligé de faire un verifi de null, parce que quand la liste reload, la valeur est null un court instant
				if((String)cb.getSelectedItem() != null){
					this.ecranParking.editTable(Parking.find((String)cb.getSelectedItem()));
				}
			}
			
			private ActionListener init(EcranParking var){
				ecranParking = var;
				return this;
			}
		}.init(this));
		
		panel.add(boxParking);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel.add(horizontalGlue_1);
		
		// J'initialiste ma premiere table au premier parking de ma jcombobox
		ArrayList<Passage> lesPassages = Parking.find(lesParkings.get(0)).getLesPassages();
		
		Object[][] maTable = new Object[lesPassages.size()][4];
		//parcours de la HashMap lesVolsArrivee de la classe VolArrivee et affectation des valeurs dans chaque colonne de chaque ligne de vol d'arrivée.
		int index = 0;
		Iterator<Passage> itTable = lesPassages.iterator();
		Passage monPassage;
		while(itTable.hasNext())
		{
			monPassage = itTable.next();
			
			maTable[index][0] = monPassage.getHeureArrivee() + " - " + monPassage.getHeureDepart();
			maTable[index][1] = monPassage.getMonVolArrivee().getNumVol();
			maTable[index][2] = monPassage.getMonVolDepart().getNumVol();
			maTable[index][3] = monPassage.getMonVolArrivee().getLAvion().getImmat();
			index++;
		}
		//Les titres des colonnes
		String  titres[] = {"Horaires", "Vol arrivée","Vol départ","Avion"};

	
		
		this.tabModel = new TabModel(maTable, titres);
		//tabModel.isCellEditable(0,0);
		this.jTable = new JTable(this.tabModel);
		
		this.scrollPane = new JScrollPane(this.jTable);

		this.panel_scroll = new JPanel();
		panel_1.add(this.panel_scroll, BorderLayout.SOUTH);
		
		this.panel_scroll.add(this.scrollPane);
		
		this.setTitle("Visualiser un parking");
		this.setVisible(true);
	}

	/**
	 * Méthode loadParking.
	 * Cette méthode est appelé pour recharger la jbox des parkings au changement sur hall
	 * 
	 * @author ap
	 * @version 1.0 - 07/06/2016 
	 */
	private void loadParking(Hall h) {
		ArrayList<String> lesParkings = Parking.getLesParkings(h);
		this.boxParking.removeAllItems();
		Iterator<String> it = lesParkings.iterator();
		while(it.hasNext()){
			String nom = it.next();
			this.boxParking.addItem(nom);
		}
		boxParking.revalidate();
	}
	
	/**
	 * Méthode editTable.
	 * Cette méthode est appelé pour recharger la table au changement de parking
	 * 
	 * @param Parking p : le parking selectionné
	 * @author ap
	 * @version 1.0 - 07/06/2016 
	 */
	private void editTable(Parking p) {

		
		this.tabModel = null;
		this.scrollPane.removeAll();
	
		
		ArrayList<Passage> lesPassages = p.getLesPassages();
		
		Object[][] maTable = new Object[lesPassages.size()][4];
		//parcours de la HashMap lesVolsArrivee de la classe VolArrivee et affectation des valeurs dans chaque colonne de chaque ligne de vol d'arrivée.
		int index = 0;
		Iterator<Passage> itTable = lesPassages.iterator();
		Passage monPassage;
		while(itTable.hasNext())
		{
			monPassage = itTable.next();
			
			maTable[index][0] = monPassage.getHeureArrivee() + " - " + monPassage.getHeureDepart();
			maTable[index][1] = monPassage.getMonVolArrivee().getNumVol();
			maTable[index][2] = monPassage.getMonVolDepart().getNumVol();
			maTable[index][3] = monPassage.getMonVolArrivee().getLAvion().getImmat();
			index++;
		}
		//Les titres des colonnes
		String  titres[] = {"Horaires", "Vol arrivée", "Vol départ", "Avion"};

	
		
		this.tabModel = new TabModel(maTable, titres);
		//tabModel.isCellEditable(0,0);
		this.jTable = new JTable(this.tabModel);
		
		this.scrollPane = new JScrollPane(this.jTable);
		
		this.panel_scroll.removeAll(); 
		this.panel_scroll.add(this.scrollPane);
		this.revalidate();
	}
	
	/**
	 * Méthode editTable.
	 * Cette méthode est appelé pour recharger la table, lors d'une actualisation de frame
	 * 
	 * @author ap
	 * @version 1.0 - 07/06/2016 
	 */
	public void editTable(){
		this.editTable(Parking.find((String)boxParking.getSelectedItem()));
	}
}
