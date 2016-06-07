package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.App;

public class ZzzEcranMenu extends JFrame{
	
	
	//Declarations
	private App application;
	JButton boutonAnnuler, boutonModifier, boutonRefresh;
	JPanel jpane0,jpaneBouton1,jpaneBouton2,jpaneBouton3;
	EcranAerogare ecranAerogare;
	EcranHall2 ecranHall2;


	public ZzzEcranMenu(App a){
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
	public void init(){
		
		//instancie les boutons
		boutonAnnuler = new JButton("Annuler vol");
		boutonAnnuler.setIcon(new ImageIcon(getClass().getResource("/images/back.png")));
		boutonModifier = new JButton("Modifier vol");
		boutonModifier.setIcon(new ImageIcon(getClass().getResource("/images/clock.png")));
		boutonRefresh = new JButton("Rafraîchir écrans");
		boutonRefresh.setIcon(new ImageIcon(getClass().getResource("/images/refresh.png")));
		
		
		ZzzActionMenuAnnuler ecouteurAnnuler = new ZzzActionMenuAnnuler(this);
		boutonAnnuler.addActionListener(ecouteurAnnuler);
		
		ZzzActionMenuModifier ecouteurModifier = new ZzzActionMenuModifier(this);
		boutonModifier.addActionListener(ecouteurModifier);
		
		/*ActionMenuRefresh ecouteurRefresh = new ActionMenuRefresh(this, this.application.getEcranAerogare(), this.application.getEcranHall1(),this.application.getEcranHall2(),this.application.getEcranHall3(),this.application.getEcranHall4(), this.application.getEcranAerogare2());
		boutonRefresh.addActionListener(ecouteurRefresh);
		*/
		//instancie le panel "Que voulez-vous faire ?" 
		Font font = new Font("Arial",Font.BOLD,16);
		Label texte= new Label("Que voulez-vous faire ?");
		texte.setFont(font);
		texte.setBackground(Color.WHITE);
		
		
		//disposition des composants dans la fenetre
		this.setLayout(new BorderLayout());
		this.getContentPane().add(texte, BorderLayout.NORTH);
		this.getContentPane().add(addSouthPanel(),"South");
	}


	JPanel addSouthPanel()
	{
		
		jpane0 = new JPanel(new GridLayout(1,3));
		jpaneBouton1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
	    jpaneBouton2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
	    jpaneBouton3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
	    
	    jpane0.add(jpaneBouton1);
	    jpane0.add(jpaneBouton2);
	    jpane0.add(jpaneBouton3);
	    jpaneBouton3.add(boutonRefresh);
	    jpaneBouton2.add(boutonModifier);
	    jpaneBouton1.add(boutonAnnuler);
		return jpane0;
	}

}
