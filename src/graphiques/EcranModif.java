package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.Vol;


public class EcranModif extends JFrame{

	//Déclaration
	private JTable tableau;
	private JButton boutonModifier;
	private TabModel tabModel;
	protected HashMap<String, Vol> vol;


	/**
	 * Constructeur EcranSupprimer.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 25/05/2016
	 * 
	 */
	public EcranModif(){
		init();

	}

	/**
	 * Méthode init()
	 * Cette méthode instancie tous les composants de la fenêtre.
	 * @author lb
	 * @version 1.0 - 25/05/2016
	 * 
	 */
	public void init() {

		vol = Vol.getlesVols();

		Object[][] tableVols = new Object[vol.keySet().size()][5];

		Iterator it = this.vol.keySet().iterator();

		int index = 0;
		while(it.hasNext()){
			String key = (String) it.next();

			tableVols[index][0] = this.vol.get(key).getNumVol();
			tableVols[index][1] = this.vol.get(key).getLaPorte().getNom();
			tableVols[index][2] = this.vol.get(key).getLAvion().getImmat();
			tableVols[index][3] = this.vol.get(key).getLAvion().getType();
			tableVols[index][4] = this.vol.get(key).getHoraire();


			index++;
		}

		//Les titres des colonnes du tableau
		String  titre[] = {"Vol","Porte","Avion","Type","Heure"};

		tabModel = new TabModel(tableVols, titre);
		//tabModel.isCellEditable(0,0);
		this.tableau = new JTable(tabModel);

		//instancie le panel "choisir vol" 
		Font font = new Font("Arial",Font.BOLD,16);
		Label texte= new Label("Choisir un vol à Modifier:");
		texte.setFont(font);
		texte.setBackground(Color.WHITE);

		// instance bouton valider
		boutonModifier = new JButton("Modifier");
		//ajout image au bouton
		boutonModifier.setIcon(new ImageIcon(getClass().getResource("/images/valider.png")));
		//j'associe une action a mon bouton valider (fais reference a la classe ActionValider)
		ActionModif ecouteurModifier = new ActionModif(this);
		boutonModifier.addActionListener(ecouteurModifier);

		//disposition des composants dans la fenetre
		this.setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tableau),BorderLayout.CENTER);
		this.getContentPane().add(texte,BorderLayout.NORTH);
		this.getContentPane().add(boutonModifier,BorderLayout.SOUTH);

		//On recharge la fenetre (modif, suppression)
		//this.revalidate();

	}

	/**
	 * Méthode getLine()
	 * Retourne la ligne selectionnée par l'utilisateur
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getLine(){
		return tableau.getSelectedRow();
	}

	/**
	 * Méthode getCol()
	 * Retourne la colonne selectionnée par l'utilisateur
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getCol(){
		return tableau.getSelectedColumn();
	}

	/**
	 * Méthode getTab()
	 * Retourne le tableau contenant le model contenant les vols
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public JTable getTab(){
		return tableau;
	}

	/**
	 * Méthode getModel()
	 * Retourne le tableau Model contenant la liste des vols
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 * 
	 */
	public TabModel getModel() {
		return tabModel;
	}
	
	/**
	 * Méthode actualiserListe()
	 * Méthode qui permet d'actualiser la liste après une modification. Le tableau est rechargé depuis la liste des vols.
	 * @author ap
	 * @version 1.0 - 04/06/2016
	 * 
	 */
	public void actualiserListe(){
		
		vol = Vol.getlesVols();

		Object[][] tableVols = new Object[vol.keySet().size()][5];

		Iterator it = this.vol.keySet().iterator();

		int index = 0;
		while(it.hasNext()){
			String key = (String) it.next();

			tableVols[index][0] = this.vol.get(key).getNumVol();
			tableVols[index][1] = this.vol.get(key).getLaPorte().getNom();
			tableVols[index][2] = this.vol.get(key).getLAvion().getImmat();
			tableVols[index][3] = this.vol.get(key).getLAvion().getType();
			tableVols[index][4] = this.vol.get(key).getHoraire();


			index++;
		}
		//Les titres des colonnes du tableau
		String  titre[] = {"Vol","Porte","Avion","Type","Heure"};

		this.tabModel = new TabModel(tableVols, titre);
		this.tableau = new JTable(tabModel);
		this.getContentPane().add(new JScrollPane(tableau),BorderLayout.CENTER);
		this.getContentPane().revalidate();
	}



}


