package graphiques;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import application.Vol;
import application.VolDepart;

public class EcranModifDepart extends EcranModif {

	private VolDepart leVol;
	private JButton boutonAnnuler, boutonRetarder, boutonAnnulerTout;
	private EcranAerogare ecranAerogare;
	
	public EcranModifDepart(String n, EcranAerogare ae){
		this.leVol = (VolDepart) Vol.getLeVol(n);
		this.ecranAerogare = ae;
		this.init();
	}
	
	private void init(){
		this.setBounds(100, 100, 608, 474);
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
			
			
			JLabel lblPassage = new JLabel("Ce vol est associé vol de d'arrivée : " + this.leVol.getLePassage().getMonVolArrivee().getNumVol()
					 + ", qui arrivera à " + this.leVol.getLePassage().getHeureArrivee());
			lblPassage.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel.add(lblPassage);
			
			
			JPanel panel_1 = new JPanel();
			this.getContentPane().add(panel_1, BorderLayout.SOUTH);
			
			this.boutonAnnuler = new JButton("Annuler le vol");
			this.boutonAnnuler.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
			this.boutonRetarder = new JButton("Retarder le vol");
			this.boutonRetarder.setIcon(new ImageIcon(getClass().getResource("/images/clock.png")));
			this.boutonAnnulerTout = new JButton("Annuler tout le passage");
			this.boutonAnnulerTout.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
			
			ActionSupprimer ecouteurSupp = new ActionSupprimer(this.leVol, this);
			this.boutonAnnuler.addActionListener(ecouteurSupp);
			
			ActionRetarder ecouteurModifier = new ActionRetarder(this.leVol, this);
			this.boutonRetarder.addActionListener(ecouteurModifier);
			
			ActionSupprimer ecouteurSuppTout = new ActionSupprimer(this.leVol, this, true);
			this.boutonAnnulerTout.addActionListener(ecouteurSuppTout);
			
			panel_1.add(boutonAnnuler);
	
			panel_1.add(boutonRetarder);
		
			panel_1.add(boutonAnnulerTout);
		}
		this.setVisible(true);
	}
	
	public void actualiser(){
		this.getContentPane().removeAll();
		this.init();
		this.ecranAerogare.actualiserListe();
	}
	
}
