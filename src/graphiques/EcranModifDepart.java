package graphiques;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.Vol;
import application.VolDepart;

@SuppressWarnings("serial")
public class EcranModifDepart extends EcranModif {

	/**
	 * Vol de départ qui sera ouvert sur la frame
	 */
	private VolDepart leVol;
	
	/**
	 * Les différents boutons de la frame
	 */
	private JButton boutonAnnuler, boutonRetarder, boutonAnnulerTout, boutonArrivee;
	
	/**
	 * L'ecran aerogare, dont est issu la frame
	 */
	private EcranAerogare ecranAerogare;
	
	/**
	 * Méthode EcranModifDepart
	 * Constructeur de EcranModifDepart.
	 * 
	 * @author ap
	 * @param String n : String du nom de l'avion
	 * @param EcranAerogare ae : ecran aerogare lié à la frame
	 * @version 1.0 - 07/06/2016 
	 */
	public EcranModifDepart(String n, EcranAerogare ae){
		this.leVol = (VolDepart) Vol.getLeVol(n);
		this.ecranAerogare = ae;
		this.init();
	}
	
	/**
	 * Méthode init
	 * Cette méthode va initialiser la frame
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016 
	 */
	private void init(){
		this.setSize(608, 474);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblVol = new JLabel("Vol de départ n°= " + this.leVol.getNumVol());
		lblVol.setFont(new Font("Tahoma", Font.BOLD, 22));
		this.getContentPane().add(lblVol, BorderLayout.NORTH);
		
		if(this.leVol.isAnnule()){
			JLabel lblAnnul = new JLabel("Attention, ce vol est annulé !");
			lblAnnul.setFont(new Font("Tahoma", Font.BOLD, 22));
			this.getContentPane().add(lblAnnul, BorderLayout.CENTER);
		} else {
			JPanel panel = new JPanel();
			this.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 1));
			
			JLabel lblHoraire = new JLabel("Ce vol partira : " + this.leVol.getHoraire());
			lblHoraire.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel.add(lblHoraire);
			
			JLabel lblProvenance = new JLabel("Provenance : " + this.leVol.getDestination());
			panel.add(lblProvenance);
		
			JLabel lblAvion = new JLabel("Avion : " + this.leVol.getLAvion().getImmat());
			panel.add(lblAvion);
			
			JLabel lblHall = new JLabel("Hall : " + this.leVol.getLeHall().getNom());
			panel.add(lblHall);
			
			JLabel lblPorte = new JLabel("Porte : " + this.leVol.getLaPorte().getNom());
			panel.add(lblPorte);
			
			JLabel lblParking = new JLabel("Parking : " + this.leVol.getLeParking().getNom());
			panel.add(lblParking);
			
			
			JLabel lblPassage;
			
			if(this.leVol.getLePassage().getMonVolArrivee() != null){
				lblPassage = new JLabel("Ce vol est associé au vol d'arrivée : " + this.leVol.getLePassage().getMonVolArrivee().getNumVol()
						 + ", qui arrivera à " + this.leVol.getLePassage().getHeureArrivee());
				lblPassage.setFont(new Font("Tahoma", Font.BOLD, 16));
				
			} else {
				lblPassage = new JLabel("Le vol associé est annulé");
			}

			panel.add(lblPassage);
			
			
			JPanel panel_1 = new JPanel();
			this.getContentPane().add(panel_1, BorderLayout.SOUTH);
			
			this.boutonAnnuler = new JButton("Annuler le vol");
			this.boutonAnnuler.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
			ActionSupprimer ecouteurSupp = new ActionSupprimer(this.leVol, this);
			this.boutonAnnuler.addActionListener(ecouteurSupp);
			panel_1.add(boutonAnnuler);
			
			this.boutonRetarder = new JButton("Retarder le vol");
			this.boutonRetarder.setIcon(new ImageIcon(getClass().getResource("/images/clock.png")));
			ActionRetarder ecouteurModifier = new ActionRetarder(this.leVol, this);
			this.boutonRetarder.addActionListener(ecouteurModifier);
			panel_1.add(boutonRetarder);
			
			
			
			// si je vol correspondant existe pas, j'ai pas besoin de ces boutons
			if(this.leVol.getLePassage().getMonVolArrivee() != null){
				this.boutonAnnulerTout = new JButton("Annuler tout le passage");
				this.boutonAnnulerTout.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
				ActionSupprimer ecouteurSuppTout = new ActionSupprimer(this.leVol, this, true);
				this.boutonAnnulerTout.addActionListener(ecouteurSuppTout);
				panel_1.add(boutonAnnulerTout);
				
				
				this.boutonArrivee = new JButton("Vol arrivée");
				this.boutonArrivee.setIcon(new ImageIcon(getClass().getResource("/images/airplane.png")));
				ActionArrivee ecouteurArrivee = new ActionArrivee(this, this.ecranAerogare);
				this.boutonArrivee.addActionListener(ecouteurArrivee);
				panel_1.add(boutonArrivee);
			}
			

			
			
			
			
			
			
			
			
	
			
		
			
			
			
		}
		this.setTitle("Vol de départ " + this.leVol.getNumVol());
		this.setVisible(true);
	}
	
	/**
	 * Méthode actualiser
	 * Cette méthode actualiser la frame
	 * 
	 * @author ap
	 * @version 1.0 - 06/06/2016 
	 */
	public void actualiser(){
		this.getContentPane().removeAll();
		this.init();
		this.ecranAerogare.actualiserTout();
	}

	
	/**
	 * Getter getLeVolArrivee().
	 * @author lb
	 * @return le vol arrivée associé
	 * @version 1.0 - 06/06/2016
	 */
	public String  getLeVolArrivee() {
		// TODO Auto-generated method stub
		
		return this.leVol.getLePassage().getMonVolArrivee().getNumVol();
	}
	
}
