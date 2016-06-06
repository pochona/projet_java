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
import application.VolArrivee;

public class EcranModifArrivee extends EcranModif {

	private VolArrivee leVol;
	private JButton boutonAnnuler, boutonRetarder;
	private EcranAerogare ecranAerogare;
	
	public EcranModifArrivee(String n, EcranAerogare ae){
		this.leVol = (VolArrivee) Vol.getLeVol(n);
		this.ecranAerogare = ae;
		this.init();
	}
	

	private void init(){
		this.setBounds(100, 100, 608, 474);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblVol = new JLabel("Vol d'arrivée n°= " + this.leVol.getNumVol());
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
			
			
			JLabel lblPassage = new JLabel("Ce vol est associé vol de départ : " + this.leVol.getLePassage().getMonVolDepart().getNumVol()
					 + ", qui partira à " + this.leVol.getLePassage().getHeureDepart());
			lblPassage.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel.add(lblPassage);
			
			
			JPanel panel_1 = new JPanel();
			this.getContentPane().add(panel_1, BorderLayout.SOUTH);
			
	
			this.boutonAnnuler = new JButton("Annuler tout le passage");
			this.boutonAnnuler.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
			this.boutonRetarder = new JButton("Retarder le vol");
			this.boutonRetarder.setIcon(new ImageIcon(getClass().getResource("/images/clock.png")));
			
			
			ActionSupprimer ecouteurSupp = new ActionSupprimer(this.leVol, this);
			this.boutonAnnuler.addActionListener(ecouteurSupp);
			
			ActionRetarder ecouteurModifier = new ActionRetarder(this.leVol, this);
			this.boutonRetarder.addActionListener(ecouteurModifier);
			
			panel_1.add(boutonAnnuler);
	
			panel_1.add(boutonRetarder);
		}
		this.setVisible(true);
	}
	
	public void actualiser(){
		this.getContentPane().removeAll();
		this.init();
		this.ecranAerogare.actualiserListe();
	}
	
}
