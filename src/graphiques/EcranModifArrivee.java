package graphiques;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.Vol;
import application.VolArrivee;

@SuppressWarnings("serial")
public class EcranModifArrivee extends EcranModif {

	/**
	 * Vol d'arriv� qui sera ouvert sur la frame
	 */
	private VolArrivee leVol;
	
	/**
	 * Les diff�rents boutons de la frame
	 */
	private JButton boutonAnnuler, boutonRetarder, boutonDepart;
	
	/**
	 * L'ecran aerogare, dont est issu la frame
	 */
	private EcranAerogare ecranAerogare;
	
	/**
	 * M�thode EcranModifArrivee
	 * Constructeur de EcranModifArrivee.
	 * 
	 * @author ap
	 * @param String n : String du nom de l'avion
	 * @param EcranAerogare ae : ecran aerogare li� � la frame
	 * @version 1.0 - 07/06/2016 
	 */
	public EcranModifArrivee(String n, EcranAerogare ae){
		this.leVol = (VolArrivee) Vol.getLeVol(n);
		this.ecranAerogare = ae;
		this.init();
	}
	
	/**
	 * M�thode init
	 * Cette m�thode va initialiser la frame
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016 
	 */
	private void init(){
		this.setBounds(100, 100, 608, 474);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblVol = new JLabel("Vol d'arriv�e n�= " + this.leVol.getNumVol());
		lblVol.setFont(new Font("Tahoma", Font.BOLD, 22));
		this.getContentPane().add(lblVol, BorderLayout.NORTH);
		
		
		if(this.leVol.isAnnule()){
			JLabel lblAnnul = new JLabel("Attention, ce vol est annul� !");
			lblAnnul.setFont(new Font("Tahoma", Font.BOLD, 22));
			this.getContentPane().add(lblAnnul, BorderLayout.CENTER);
		} else {
			JPanel panel = new JPanel();
			this.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 1));
			
			JLabel lblHoraire = new JLabel("Ce vol partira : " + this.leVol.getHoraire());
			lblHoraire.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(lblHoraire);
			
			JLabel lblProvenance = new JLabel("Provenance : " + this.leVol.getProvenance());
			panel.add(lblProvenance);
		
			JLabel lblAvion = new JLabel("Avion : " + this.leVol.getLAvion().getImmat());
			panel.add(lblAvion);
			
			JLabel lblHall = new JLabel("Hall : " + this.leVol.getLeHall().getNom());
			panel.add(lblHall);
			
			JLabel lblPorte = new JLabel("Porte : " + this.leVol.getLaPorte().getNom());
			panel.add(lblPorte);
			
			JLabel lblParking = new JLabel("Parking : " + this.leVol.getLeParking().getNom());
			panel.add(lblParking);
			
			
			JLabel lblPassage = new JLabel("Ce vol est associ� vol de d�part : " + this.leVol.getLePassage().getMonVolDepart().getNumVol()
					 + ", qui partira � " + this.leVol.getLePassage().getHeureDepart());
			lblPassage.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel.add(lblPassage);
			
			
			JPanel panel_1 = new JPanel();
			this.getContentPane().add(panel_1, BorderLayout.SOUTH);
			
	
			this.boutonAnnuler = new JButton("Annuler tout le passage");
			this.boutonAnnuler.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
			this.boutonRetarder = new JButton("Retarder le vol");
			this.boutonRetarder.setIcon(new ImageIcon(getClass().getResource("/images/clock.png")));
			this.boutonDepart = new JButton("Vol d�part");
			this.boutonDepart.setIcon(new ImageIcon(getClass().getResource("/images/airplane.png")));
			
			
			ActionSupprimer ecouteurSupp = new ActionSupprimer(this.leVol, this);
			this.boutonAnnuler.addActionListener(ecouteurSupp);
			
			ActionRetarder ecouteurModifier = new ActionRetarder(this.leVol, this);
			this.boutonRetarder.addActionListener(ecouteurModifier);
			
			ActionDepart ecouteurDepart = new ActionDepart(this, this.ecranAerogare);
			this.boutonDepart.addActionListener(ecouteurDepart);
			
			panel_1.add(boutonAnnuler);
	
			panel_1.add(boutonRetarder);
			
			panel_1.add(boutonDepart);
		}
		this.setTitle("Vol d'arriv�e " + this.leVol.getNumVol());
		this.setVisible(true);
	}
	
	/**
	 * M�thode actualiser
	 * Cette m�thode actualiser la frame
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016 
	 */
	public void actualiser(){
		this.getContentPane().removeAll();
		this.init();
		this.ecranAerogare.actualiserListe();
	}
	
	/**
	 * Getter getLeVolDepart().
	 * @author lb
	 * @return le vol depart associ�
	 * @version 1.0 - 06/06/2016
	 */
	public String  getLeVolDepart() {
		// TODO Auto-generated method stub
		
		return this.leVol.getLePassage().getMonVolDepart().getNumVol();
	}
	
}
