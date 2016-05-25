package graphiques;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


public class ActionValider extends AbstractAction{
	EcranMisAJour ecr;
	
	public ActionValider (EcranMisAJour ecran){
		this.ecr=ecran;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 System.out.println("ecouteur action valider");
	}

}
