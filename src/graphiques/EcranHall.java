package graphiques;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.App;
import application.Vol;

public class EcranHall extends JFrame{

	protected String leHall;
	protected HashMap<String, Vol> lesVols;
	private TabModel tabModel;
	private JScrollPane scrollPane;
	private JTable tableau;
	private App application;
	
	/**
	 * Méthode qui initialise
	 * 
	 * Cette méthode instancie tous les composants de la fenêtre.
	 * @author lb
	 * @version 1.0 - 24/05/2016
	 * @version 2.0 - 25/05/2016 - by ap : modif des méthodes de recupération des valeurs (vol.getLavion() -> vol.getLAvion().getImmat() )
	 * et changement du foreach par un iterator 
	 * @
	 */
	public void init(App a) {
		this.application = a;
		
		this.lesVols = Vol.getVolsByHall(this.leHall);

		Object[][] tableHall = new Object[this.lesVols.keySet().size()][4];

		Iterator it = this.lesVols.keySet().iterator();

		int index = 0;
		while(it.hasNext()){
			String key = (String) it.next();

			tableHall[index][0] = this.lesVols.get(key).getLaPorte().getNom();
			tableHall[index][1] = this.lesVols.get(key).getLAvion().getImmat();
			tableHall[index][2] = this.lesVols.get(key).getLAvion().getType();
			tableHall[index][3] = this.lesVols.get(key).getHoraire();


			index++;
			
		}
		
		

		//Les titres des colonnes
		String  titre[] = {"Porte","Avion","Type","heure"};

		tabModel = new TabModel(tableHall, titre);
		this.tableau = new JTable(tabModel);


		//ajout d'un contentPane dans un scroll
		this.scrollPane = new JScrollPane(tableau);
		this.getContentPane().add(scrollPane);
		this.setTitle("Ecran Hall "+this.leHall);
		this.setSize(400,200);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Méthode actualiserListe()
	 * 
	 * Méthode qui permet d'actualiser la liste après une modification. Le tableau est rechargé depuis la liste des vols.
	 * @author lb
	 * @version 1.0 - 04/06/2016
	 */
	public void actualiserListe(){
		this.getContentPane().remove(this.scrollPane);
		this.scrollPane = null;
		this.tabModel =null;
		this.tableau= null;

		this.lesVols = Vol.getVolsByHall(this.leHall);

		Object[][] tableHall = new Object[this.lesVols.keySet().size()][4];

		Iterator it = this.lesVols.keySet().iterator();

		int index = 0;
		while(it.hasNext()){
			String key = (String) it.next();

			tableHall[index][0] = this.lesVols.get(key).getLaPorte().getNom();
			tableHall[index][1] = this.lesVols.get(key).getLAvion().getImmat();
			tableHall[index][2] = this.lesVols.get(key).getLAvion().getType();
			tableHall[index][3] = this.lesVols.get(key).getHoraire();


			index++;
		}
		
		//Les titres des colonnes
		String  titre[] = {"Porte","Avion","Type","heure"};

		tabModel = new TabModel(tableHall, titre);
		this.tableau = new JTable(tabModel);
	

		//ajout d'un contentPane dans un scroll
		this.scrollPane = new JScrollPane(tableau);
		this.getContentPane().add(scrollPane);
		this.setTitle("Ecran Hall "+this.leHall);
		this.setSize(400,200);
		this.getContentPane().revalidate();

	}
}
