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
	 * R�f�rence du vol
	 */
	private String numVol;
	
	/**
	 * Indique si le vol est annul� (true), false sinon.
	 */
	private boolean estAnnule;
	
	/**
	 * Le num�ro de l'avion
	 */
	private Avion lAvion;
	
	/**
	 * Passage associ� au vol0
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
	 * @param avion : string num�ro de l'avion
	 */
	public Vol(String num, Avion avion){
		this.numVol =num;
		this.estAnnule=false;
		this.lAvion=avion;
		lesVols.put(num, this);
	}
	
	/**
	 * Cette m�thode static permet d'ouvrir le fichier des volset d'initialiser les vols, volsdeparts et volsArriv�.
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
				//J'incr�mente le compteur de vol qui va me servir � d�terminer si c'est un vol de d�part ou d'arriv�
				compteurVols++;
				// je stocke les mots de la ligne 
				st = new StringTokenizer(line);
				// je v�rifie que ma ligne soit bien �gale � 5 pour �tre sur de mon fichier d'entr�e
				if(st.countTokens() == 5){
					numVol = st.nextToken();
					heures = Integer.parseInt(st.nextToken());	
					minutes = Integer.parseInt(st.nextToken());
					ville = st.nextToken();	
					numAvion = st.nextToken();
					//si le compteur du vol est impair c'est un vol d'arriv�e, sinon c'est un vol de d�part
					if (compteurVols%2 != 0){
						if (tempNumAvion != numAvion) {
							//on stocke l'heure d'arriv�e pour v�rifier les horaires d'�cart
							heureArrivee=new Horaire(heures,minutes);
							monVolArrivee = new VolArrivee(numVol, heureArrivee, ville, Avion.find(numAvion));
						} else {
							//la ligne qui suit le d�part de l'avion numAvion n'est pas correcte.
							throw new ErreurLignesSuccessivesVols(2, numAvion);	
						}
					//actuellement il manque la porte et le hall
					}else{ 
					if (tempNumAvion.equals(numAvion)) {
							heureDepart=new Horaire(heures,minutes);
								monVolDepart = new VolDepart(numVol, heureDepart, ville,  Avion.find(numAvion));
								//Contructeur du passage (seuleument ap�rs que l'on est construit le vol arrivee et le vol d�part
								monPassage = new Passage(monVolArrivee, monVolDepart);
								//On rajoute le passage dans vol arriv�e et vol d�part
								monVolDepart.setLePassage(monPassage);
								monVolArrivee.setLePassage(monPassage);		
								//On v�rifie l'�cart de temps entre 2 avions
								if (heureDepart.retrait(Passage.getDuree()).compareTo(heureArrivee)<0) {
								System.out.println("L'�cart minimum entre l'heure d'arriv�e et de d�part du vol '"+ numVol +"' n'est pas suffisant!") ;}
							}else{
								//il manque le d�part de l'avion 
								throw new ErreurLignesSuccessivesVols(1, numAvion);
								}
									
							}	
						}
					tempNumAvion=numAvion;
					}
			// je ferme mon fichier
			entree.close();
		} 
		// erreur catch� si le fichier n'existe pas
		catch (java.io.FileNotFoundException e){
			System.out.println("Fichier des vols introuvable");
			System.out.println(e);
		}
	}
	
	/**
	 * 
	 * @return le num�ro du vol
	 */
	public String getNumVol(){return this.numVol;}
	
	/**
	 * 
	 * @return le boolean  qui indique si le vol est annul� (true ) ou pas (false)
	 */
	public boolean getVolAnnule(){return this.estAnnule;}
	
	/**
	 * 
	 * @return l'avion associ� au vol
	 */
	public Avion getLAvion(){return this.lAvion;}
	
	/**
	 * 
	 * @return le passage associ� au vol
	 */
	public Passage getLePassage(){return this.lePassage;}
	
	/**
	 * Rempli le passage de l'avion
	 * @param p lePassage associ� au vol
	 */
	public void setLePassage(Passage p){lePassage=p;}
	
	/**
	 * Cette m�thode retourne une chaine de caract�res pour permettre d'afficher la liste des vols.
	 * @return
	 */
	public static String builtChaineVols(){
		Iterator<Vol> val = lesVols.values().iterator();
		String str = "------ Les vols  ------" + " \n";
		while(val.hasNext()){
			Vol monVol = val.next();
			if (monVol.getVolAnnule()==true){
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}
			/*if (monVol.getVolAnnule()==true){
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : oui, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?", vol arriv�.":", vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			} else {
				str += "Num�ro du vol : " + monVol.getNumVol() +  ", Vol Annul� : non, Porte :"+ monVol.getLeParking() + ", Hall : "+monVol.getLeHall() + ", Num�ro de l'avion : " + monVol.getLAvion().getImmat() +", Type : "+(monVol.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ monVol.getLeNomDeLaPorte()+", Parking : " + monVol.getLeNomDuParking()+". \n";
			}*/
		}
		return str;
	}
	/**
	 * Retourne la chaine qui contient l'affichage d'un vol
	 */
	public String toString(){
		return  "Num�ro du vol : " + this.getNumVol() + (this.getVolAnnule()==true?"vol Annul� : non":"vol Annul� : oui") + " , Num�ro de l'avion : " + this.getLAvion().getImmat() +", Type : "+(this.getClass().equals(VolArrivee.class)?"vol arriv�.":"vol d�part.")+ " Porte : "+ this.getLeNomDeLaPorte()+", Parking : " + this.getLeNomDuParking()+". \n";
	}
	
	/**
	 * Cette m�thode affiche la chaine de caract�res qui contient la liste des vols.
	 */
	public static void afficherLesVols(){System.out.println(Vol.builtChaineVols());}
	
	/**
	 * 
	 * @return le parking associ� au passage 
	 */
	public Parking getLeParking(){return this.getLePassage().getLeParking();}
	
	/***
	 * 
	 * @return String le nom du parking associ� au passage
	 */
	public String getLeNomDuParking(){return this.getLeParking().getNom();}
	
	/**
	 * 
	 * @return la porte associ�e au vol
	 */
	public Porte getLaPorte(){return this.getLeParking().getLaPorte();}
	
	/**
	 * 
	 * @return le nom associ� � la porte
	 */
	public String getLeNomDeLaPorte(){return this.getLaPorte().getNom();}
	
}
