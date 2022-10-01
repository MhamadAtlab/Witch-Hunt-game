package Modele;

import java.util.*;
/**
 * <b>Partie est la classe représentant une partie de jeu.</b>
 * @author mhamad
 *
 */
public class Partie extends Observable {
	
	/**
	 * premieur joueur
	 */
	private Player preJoueur;
    private int nbrDeJoueurMin;
    private int nbrDeJoueurMax;
    /**
    * le score à gagner
    */
    private int scoreGagne;
    private int nbrDeJoueur=6;
    private ArrayList<Player> joueursGagnes;
    private ArrayList<Player> joueurs;
    private ArrayList<Player> joueursPhysique;
    private int nbDeTour;
    private Player joueurGagnes;
    
    /**
     * methode donne les score de tous joueur sous forme d'array of String
     */
    
    public String[][] getResultat(){
    	int n=this.joueurs.size();
    	String[][] s=new String[n][2];
    	for(int i=0;i<n;i++) {
    		s[i][0]=this.joueurs.get(i).getusername();
    		s[i][1]=Integer.toString(this.joueurs.get(i).getScore());
    	}
    	return s;
    }
    
    public int getNbDeTour() {
    	return this.nbDeTour;
    }
    public void setNbDeTour(int n) {
    	this.nbDeTour=n;
    }
    public Partie() {
    	 this.joueurs=new ArrayList<Player> ();
    	 this.joueursGagnes=new ArrayList<Player> ();
    	 this.joueursPhysique=new ArrayList<Player> ();
	     this.nbrDeJoueurMax=SettingManage.getNbrJoueurMax();
	     this.nbrDeJoueurMin=SettingManage.getNbrJoueurMin();
	     this.scoreGagne=SettingManage.getScoreGagne();
	     this.nbDeTour=1;
    }

	public ArrayList<Player> getJoueurs() {
		return this.joueurs;
	}

	public void setJoueurs(ArrayList<Player> joueurs) {
		this.joueurs = joueurs;
	}
	/**
	 * méthode pour savoir si le jeu est terminé
	 * @return
	 */
    public boolean estFin() {
    	int i=0;
    	boolean b=false;
    	while(i<this.joueurs.size()&&!b) {
    		if (this.joueurs.get(i).getScore()>=this.scoreGagne) b=true;
    		i++;
    	}
        return b;
    }
    /**
     * méthode donne la liste des joueurs 
     */
    public String getJoueurString() {
    	int z=this.joueurs.size();
    	int i=0;
    	String s="<html>";
    	while( i<z){
    		s += this.joueurs.get(i).getId()+" - "+ this.joueurs.get(i).getusername()+"<br>";
    		i++;
    		}
    	s +="</html>";
    	return s;
    }
    /**
     * methode donne la liste des id du joueurs 
     */
    public String[] getJoueursId() {
    	int z=this.joueurs.size();
    	String[] s = new String[z];
    	int i=0;
    	while( i<z){
    		s[i]=Integer.toString( this.joueurs.get(i).getId());
    		i++;
    	}
    	return s;
    }
    /**
     * methode pour choisir le premier joueur 
     */
    public Player PremieurJoueur() {
    	int z=this.joueurs.size();
    	Scanner kbhit = new Scanner(System.in);
    	int str=0;
    	boolean onContinue = true;
    	int i=0;
    	while( i<z){
    		System.out.println(this.joueurs.get(i).getId()+" - "+ this.joueurs.get(i).getusername()+"\n");
    		i++;
    		}
    	while(onContinue) {
    	System.out.println("Choisir le primiere joueur (par ID) :");
        str = Integer.parseInt( kbhit.next())  ;
		if(str>0&&str<z+1) onContinue=false;
		else onContinue=true;
    	}
    	return this.joueurs.get(str-1);
    }
    /**
     * methode pour ajouter joueur physique 
     */
    public boolean ajouterJoueur(String username) {
    	if(this.joueurs.size()<this.nbrDeJoueurMax) {
    		Player p=new JoueurPhysique(username);
    		this.joueurs.add(p);
    		this.joueursPhysique.add(p);
    		return true;
    	}
    	else return false;
    }
    /**
     * methode pour ajouter Bot
     */
    public boolean AjouterBot() {
    	if(this.joueurs.size()<this.nbrDeJoueurMax) {
    		Player p=new Bot();
        	this.joueurs.add(p);
    		return true;
    	}
    	return false;
    }
    /**
     * méthode pour créer des Bots par défaut
     */
    public void creerjoueur() {
    	while(this.joueurs.size()<this.nbrDeJoueur) {
    		this.AjouterBot();
    	}
    }
    /**
     * commencer la partie
     */
    public void commencer() {
    	

    		System.out.println("-----------------------------------------------------------------");
    		System.out.println("-----------------------------------------------------------------\n");
    		System.out.println("-----------------------------------------------------------------");
    		System.out.println("-----------------------------------------------------------------\n");
    		System.out.println("Resultat jusqu'a maintenant : \n");
    		for(int i=0;i<this.joueurs.size();i++) {
    			System.out.println(this.joueurs.get(i).getId()+" - "+this.joueurs.get(i).getusername()+" : "+this.joueurs.get(i).getScore());
    		}

    		System.out.println("\n-----------------------------------------------------------------");
    		System.out.println("-----------------------------------------------------------------\n");

    		this.nbDeTour ++;
    	
    }
    /**
     * methode pour calculer le score 
     */
    public void cummuleScore() {
    	for(int i=0;i<this.joueurs.size();i++) {
    		if(this.joueurs.get(i).getScore()>=this.scoreGagne) this.joueursGagnes.add(this.joueurs.get(i));
    	}
    	Iterator<Player> It=this.joueursGagnes.iterator();
    	while(It.hasNext()) {
    		this.joueurGagnes=It.next();
    		System.out.println("Joueur gagnant : "+this.joueurGagnes.getusername()+"       apres "+this.nbDeTour+" tours !!!");
    		
    	}
    }

	public int getNbrDeJoueur() {
		return nbrDeJoueur;
	}

	public void setNbrDeJoueur(int nbrDeJoueur) {
		if(nbrDeJoueur>=this.nbrDeJoueurMin&&nbrDeJoueur<=this.nbrDeJoueurMax) this.nbrDeJoueur = nbrDeJoueur;
	}
	

	public Player getPreJoueur() {
		return preJoueur;
	}

	public void setPreJoueur(Player preJoueur) {
		this.preJoueur = preJoueur;
		this.setChanged();
		this.notifyObservers(5);
	}
	public ArrayList<Player> getJoueursPhysique() {
		return joueursPhysique;
	}
	public void setJoueursPhysique(ArrayList<Player> joueursPhysique) {
		this.joueursPhysique = joueursPhysique;
	}

	public Player getJoueurGagnes() {
		return joueurGagnes;
	}

	public void setJoueurGagnes(Player joueurGagnes) {
		this.joueurGagnes = joueurGagnes;
	}


}
