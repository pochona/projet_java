package graphiques;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EcranMenu extends JFrame{
	
	//Declarations
	JButton boutonAnnuler, boutonModifier, boutonRefresh;
	JPanel jpane0,jpaneBouton1,jpaneBouton2,jpaneBouton3;


	public EcranMenu(){
		init();
	}

	public void init(){
		
		//instancie les boutons
		boutonAnnuler = new JButton("Annuler vol");
		boutonModifier = new JButton("Modifier vol");
		boutonRefresh = new JButton("Rafra�chir �crans");
		
		
		ActionMenuAnnuler ecouteurAnnuler = new ActionMenuAnnuler(this);
		boutonAnnuler.addActionListener(ecouteurAnnuler);
		
		ActionMenuModifier ecouteurModifier = new ActionMenuModifier(this);
		boutonModifier.addActionListener(ecouteurModifier);
		
		ActionMenuRefresh ecouteurRefresh = new ActionMenuRefresh(this);
		boutonRefresh.addActionListener(ecouteurRefresh);
		
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
