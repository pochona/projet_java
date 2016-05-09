package application;

public class ErreurLignesSuccessivesVols extends Exception {
	public ErreurLignesSuccessivesVols (int mode, String numAvion) {
	if(mode==1){System.out.println(this.afficherManqueDepart(numAvion));}
	else{System.out.println(this.afficherManqueArrivee(numAvion));}
	}
	
	public String afficherManqueDepart(String numAvion) {
		return "Attention il manque le départ de l'avion : " + numAvion;
	}
	
	public String afficherManqueArrivee(String numAvion) {
		return "Attention la ligne qui suit le départ de l'avion : " + numAvion+ " n'est pas correcte.";
	}
	
}
