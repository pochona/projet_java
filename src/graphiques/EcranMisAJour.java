package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.Vol;


public class EcranMisAJour extends JFrame{
	
	//Déclaration
	private JPanel jpane0, jpaneBouton1,jpaneTexte, jpaneBouton2;
	private JButton boutonValider, boutontest;
	
	
	
	/**
	 * Constructeur EcranMisAJour.
	 * Ce constructeur appel la méthode init()
	 * @author lb
	 * @version 1.0 - 25/05/2016
	 * @
	 */
	public EcranMisAJour(){
		init();
	}

	/**
	 * Méthode init()
	 * 
	 * Cette méthode instancie tous les composants de la fenêtre.
	 * @author lb
	 * @version 1.0 - 25/05/2016
	 * @
	 */
	public void init() {
		
		Object[][] tableVols = new Object[Vol.getlesVols().keySet().size()][4];


		int index = 0;
		for (String keyH : Vol.getlesVols().keySet())
		{
			Vol vol = Vol.getlesVols().get(keyH);
			tableVols[index][0] = vol.getLaPorte().getNom();
			tableVols[index][1] = vol.getLAvion().getImmat();
			tableVols[index][2] = vol.getLAvion().getType();
			tableVols[index][3] = vol.getHoraire();
			
		
			index++;
		}

		//Les titres des colonnes du tableau
		String  titre[] = {"Porte","Avion","Type","Heure"};
		JTable tableau = new JTable(tableVols, titre);
		
		//instancie le panel "choisir vol" 
		Font font = new Font("Courier",Font.BOLD,16);
		Label texte= new Label("Choisir un vol :");
		texte.setFont(font);
		texte.setBackground(Color.WHITE);
		
		// instance bouton valider
		boutonValider = new JButton("Valider");
		//ajout image au bouton
		boutonValider.setIcon(new ImageIcon(getClass().getResource("/images/valider.png")));
		//j'associe une action a mon bouton valider (fais reference a la classe ActionValider)
		ActionValider ecouteurValider = new ActionValider(this);
		boutonValider.addActionListener(ecouteurValider);
		
		//disposition des composants dans la fenetre
		this.setLayout(new BorderLayout());
		this.getContentPane().add(new JScrollPane(tableau),BorderLayout.CENTER);
		this.getContentPane().add(texte,BorderLayout.NORTH);
		this.getContentPane().add(boutonValider,BorderLayout.SOUTH);

	}

}
