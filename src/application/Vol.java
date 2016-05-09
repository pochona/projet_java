package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import utilitaires.Horaire;

public abstract class Vol {
	/**
	 * Référence du vol
	 */
	private String numVol;
	/**
	 * Indique si le vol est annulé (true), false sinon.
	 */
	private boolean estAnnule;
	/**
	 * La porte associée au vol
	 */
	private Porte laPorte;
	/**
	 * Le hall associé au vol
	 */
	private Hall leHall;
	/**
	 * Le numéro de l'avion
	 */
	private Avion lAvion;
	/**
	 * Toutes les instances des vols 
	 */
	private static HashMap<String, Vol> lesVols = new HashMap<String, Vol>();
	/**
	 * 
	 * @param num : string numero du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param avion : string numéro de l'avion
	 */
	public Vol(String num, Porte p, Hall ha, Avion avion){
		this.numVol =num;
		this.estAnnule=false;
		this.laPorte=p;
		this.leHall=ha;	
		this.lAvion=avion;
		lesVols.put(num, this);
	}
	/**
	 * 
	 * @param num : string numero du vol
	 * @param p : porte
	 * @param ha : hall
	 * @param avion : string numéro de l'avion
	 */
	public Vol(String num, Avion avion){
		this.numVol =num;
		this.estAnnule=false;
		this.lAvion=avion;
		lesVols.put(num, this);
	}
	/**
	 * Cette méthode static permet d'ouvrir le fichier des volset d'initialiser les vols, volsdeparts et volsArrivé.
	 * @throws IOException
	 * @throws ErreurLignesSuccessivesVols
	 */
	public static void initialise() throws IOException, ErreurLignesSuccessivesVols{
		try {
			BufferedReader entree = new BufferedReader(new FileReader("src\\fichiers\\vols.txt"));
			int compteurVols=0;
			String line;
			String numVol;
			String numAvion="";
			String ville;
			int heures;
			int minutes;
			Horaire heureArrivee = null;
			Horaire heureDepart;
			String tempNumAvion="";
			StringTokenizer st;
			// je parcours chaque ligne de mon fichier
			while ((line = entree.readLine()) != null) {
				//J'incrémente le compteur de vol qui va me servir à déterminer si c'est un vol de départ ou d'arrivé
				compteurVols++;
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je vérifie que ma ligne soit bien égale à 5 pour être sur de mon fichier d'entrée
				if(st.countTokens() == 5){
					numVol = st.nextToken();
					heures = Integer.parseInt(st.nextToken());	
					minutes = Integer.parseInt(st.nextToken());
					ville = st.nextToken();	
					numAvion = st.nextToken();
					//si le compteur du vol est impair c'est un vol d'arrivée, sinon c'est un vol de départ
					if (compteurVols%2 != 0){
						if (tempNumAvion != numAvion) {
							//on stocke l'heure d'arrivée pour vérifier les horaires d'écart
							heureArrivee=new Horaire(heures,minutes);
							new VolArrivee(numVol, heureArrivee, ville, Avion.find(numAvion));
						} else {
							//la ligne qui suit le départ de l'avion numAvion n'est pas correcte.
							throw new ErreurLignesSuccessivesVols(2, numAvion);	
						}
					//actuellement il manque la porte et le hall
					}else{ 
					if (tempNumAvion.equals(numAvion)) {
							heureDepart=new Horaire(heures,minutes);
								new VolDepart(numVol, heureDepart, ville,  Avion.find(numAvion));
								//On vérifie l'écart de temps entre 2 avions
								if (heureDepart.retrait(Passage.getDuree()).compareTo(heureArrivee)<0) {
								System.out.println("L'écart minimum entre l'heure d'arrivée et de départ du vol '"+ numVol +"' n'est pas suffisant!") ;}
							}else{
								//il manque le départ de l'avion 
								throw new ErreurLignesSuccessivesVols(1, numAvion);
								}
									
							}	
						}
					tempNumAvion=numAvion;
				}
			
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catché si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des vols introuvable");
			System.out.println(e);
		}
	}

	public String getNumVol(){return this.numVol;}
	public boolean getVolAnnule(){return estAnnule;}
	public Porte getLaPorte(){return laPorte;}
	public Hall getLeHall(){return leHall;}
	public Avion getLAvion(){return lAvion;}
	
	/**
	 * Cette méthode retourne une chaine de caractères pour permettre d'afficher la liste des vols.
	 * @return
	 */
	public static String afficherLesVols(){
		Iterator<Vol> val = lesVols.values().iterator();
		String str = "------ Les vols  ------" + " \n";
		while(val.hasNext()){
			Vol monVol = val.next();
			str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : " + monVol.getVolAnnule() +", Porte :"+ monVol.getLaPorte() + ", Hall : "+monVol.getLeHall() + ", Numéro de l'avion : " + monVol.getLAvion().getImmat() + " \n";
		}
		return str;
	}

}
