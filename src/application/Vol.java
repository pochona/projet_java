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
	 * Le numéro de l'avion
	 */
	private Avion lAvion;
	
	/**
	 * Passage associé au vol0
	 */
	private Passage lePassage;
	
	/**
	 * Toutes les instances des vols 
	 */
	private static HashMap<String, Vol> lesVols = new HashMap<String, Vol>();
	
	/**
	 * Constructeur de vol 
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
			VolDepart monVolDepart=null;
			VolArrivee monVolArrivee=null;
			Passage monPassage;
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
							monVolArrivee = new VolArrivee(numVol, heureArrivee, ville, Avion.find(numAvion));
						} else {
							//la ligne qui suit le départ de l'avion numAvion n'est pas correcte.
							throw new ErreurLignesSuccessivesVols(2, numAvion);	
						}
					//actuellement il manque la porte et le hall
					}else{ 
					if (tempNumAvion.equals(numAvion)) {
							heureDepart=new Horaire(heures,minutes);
								monVolDepart = new VolDepart(numVol, heureDepart, ville,  Avion.find(numAvion));
								//Contructeur du passage (seuleument apèrs que l'on est construit le vol arrivee et le vol départ
								monPassage = new Passage(monVolArrivee, monVolDepart);
								//On rajoute le passage dans vol arrivée et vol départ
								monVolDepart.setLePassage(monPassage);
								monVolArrivee.setLePassage(monPassage);		
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
	
	/**
	 * 
	 * @return le numéro du vol
	 */
	public String getNumVol(){return this.numVol;}
	
	/**
	 * 
	 * @return le boolean  qui indique si le vol est annulé (true ) ou pas (false)
	 */
	public boolean getVolAnnule(){return this.estAnnule;}
	
	/**
	 * 
	 * @return l'avion associé au vol
	 */
	public Avion getLAvion(){return this.lAvion;}
	
	/**
	 * 
	 * @return le passage associé au vol
	 */
	public Passage getLePassage(){return this.lePassage;}
	
	/**
	 * Rempli le passage de l'avion
	 * @param p lePassage associé au vol
	 */
	public void setLePassage(Passage p){lePassage=p;}
	
	/**
	 * Cette méthode retourne une chaine de caractères pour permettre d'afficher la liste des vols.
	 * @return
	 */
	public static String builtChaineVols(){
		Iterator<Vol> val = lesVols.values().iterator();
		String str = "------ Les vols  ------" + " \n";
		while(val.hasNext()){
			Vol monVol = val.next();
			if (monVol.getVolAnnule()==true){
				str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : oui, Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arrivé.":", vol départ.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : non, Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arrivé.":"vol départ.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}
			/*if (monVol.getVolAnnule()==true){
				str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : oui, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arrivé.":", vol départ.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Numéro du vol : " + monVol.getNumVol() +  ", Vol Annulé : non, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Numéro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arrivé.":"vol départ.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}*/
		}
		return str;
	}
	/**
	 * Retourne la chaine qui contient l'affichage d'un vol
	 */
	public String toString(){
		return  "Numéro du vol : " + this.getNumVol() + (this.getVolAnnule()==true?"vol Annulé : non":"vol Annulé : oui") + " , Numéro de l'avion : " + this.getLAvion().getImmat() +", Type : "+(this.getClass().equals(VolArrivee.class)?"vol arrivé.":"vol départ.")+ " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";
	}
	
	/**
	 * Cette méthode affiche la chaine de caractères qui contient la liste des vols.
	 */
	public static void afficherLesVols(){System.out.println(Vol.builtChaineVols());}
	
	/**
	 * 
	 * @return le parking associé au passage 
	 */
	public Parking getLeParking(){return this.getLePassage().getLeParking();}
	
	/***
	 * 
	 * @return String le nom du parking associé au passage
	 */
	public String getLeNomDuParking(){return this.getLeParking().getNom();}
	
	/**
	 * 
	 * @return la porte associée au vol
	 */
	public Porte getLaPorte(){return this.getLeParking().getLaPorte();}
	
	/**
	 * 
	 * @return le nom associé à la porte
	 */
	public String getLeNomDeLaPorte(){return this.getLaPorte().getNom();}
	
}
