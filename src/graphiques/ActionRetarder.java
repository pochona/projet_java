package graphiques;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import application.ParkingIndispo;
import application.RetardTropTard;
import application.Vol;



@SuppressWarnings("serial")
public class ActionRetarder extends AbstractAction{

	//Declaration
	private Vol leVol;
	private EcranModif ecranModif;

	/**
	 * Constructeur ActionModif.
	 * @author lb
	 * @params EcranModif ecranModif
	 * @version 1.0 - 27/05/2016
	 * @version 2.0 - by ap : changement de l'attribution de la frame associé
	 */

	public ActionRetarder(Vol leVol, EcranModif ecran){
		this.leVol = leVol;
		this.ecranModif = ecran;
	}

	/**
	 * Méthode actionPerformed.
	 * @author ap
	 * @params ActionEvent
	 * @version 1.0 - 06/06/2016
	 */
	public void actionPerformed(ActionEvent arg0) {
		try {
			String minutes = JOptionPane.showInputDialog(null, "Saisir les minutes de retard :");
			if(minutes != null && !minutes.equals("")){
				//Vol.ModifierHeure(minutes, key);
				Vol.retarder(minutes, this.leVol.getNumVol());
				
				JOptionPane.showMessageDialog(null, "Vol "+this.leVol.getNumVol()+" modifié!", "InfoBox: " + "Modification", JOptionPane.INFORMATION_MESSAGE);
				this.ecranModif.actualiser();
			}
		} catch (RetardTropTard e){
			System.out.println(e);
		} catch (ParkingIndispo e) {
			System.out.println(e);
		}
	}
}