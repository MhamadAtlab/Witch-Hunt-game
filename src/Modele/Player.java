package Modele;

import java.util.*;

/**
 * <b>Player est la classe mere de Bot et JoueurPhysique.</b>
 * <p>
 */
public class Player extends Observable{
	/**
	 * score de joueur
	 */
    protected int score = 0;
    /**
	 * si joueur virtuel ou non
	 */
    protected boolean bot=false;
    /**
     * nombre de joueurs
     */
    private static int nbrdeJoueur = 0;
    /**
     * si le joueur gagne
     */
    protected boolean isgagne = false;
    /**
	 * l'identite de joueur
	 */
    protected Identite identite;
    /**
	 * si le joueur a levé une carte de rumeur
	 */
    protected boolean releverRumeur = false;
    /**
	 * si le joueur a levé son identité
	 */
    protected boolean releverIdentite = false;
    /**
	 * id de joueur
	 */
    protected int id;
    /**
	 * username de joueur
	 */
    protected String username;
    protected Condition condition = Condition.NON;
    protected ArrayList<Carte> main;
    protected boolean horTour=false;
    protected ArrayList<Carte> cartesRelever=new ArrayList<Carte>() ;

    public Player(String nom) {
        this.username=nom;
        nbrdeJoueur++;
        this.id=nbrdeJoueur;
        this.main=new ArrayList<Carte>();
    }

    public Player() {
        nbrdeJoueur++;
        this.id=nbrdeJoueur;
        this.main=new ArrayList<Carte>();
        StringBuffer s=new StringBuffer();
        s.append("Bot_");
        s.append(this.id);
        this.bot=true;
        this.username=s.toString();
    }
    public boolean isBot() {
    	return this.bot;
    }
    public int getId() {
    	return this.id;
    }


    public String getusername() {
        return this.username;
    }
    public ArrayList<Carte> getmain() {
        return this.main;
    }

    public void setmain(ArrayList<Carte> arr) {
        this.main=arr;
    }

    public boolean getreleverRumeur() {
        return this.releverRumeur;
    }
    
    public void setreleverRumeur(boolean b) {
        this.releverRumeur=b;
    }
    
    public boolean getreleverIdentite() {
        return this.releverIdentite;
    }
    public Identite getIdentite() {
    	this.releverIdentite=true;
        return this.identite;
    }
    public void setIdentite(Identite i) {
        this.identite=i;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int x) {
        this.score+=x;
    }
    /**
	 * methode pour ajouter une carte
	 */
    public void ajouterCarte(Carte c) {
        this.main.add(c);
    }

    /**
	 * methode pour relever une carte
	 */
    public void releverCarte(Carte c) {
        this.main.remove(c);
        this.cartesRelever.add(c);
        this.releverRumeur=true;
    }
    public void setisgagne() {
        this.isgagne=true;
    }
    public boolean getisgagne() {
        return this.isgagne;
    }
    public String toString() {
        StringBuffer s=new StringBuffer();
        s.append(this.id+" - "+this.username+" : ");
        s.append("\n");
        s.append("Score = "+this.score);
        s.append("\n");
        s.append("Identite : "+this.identite);
        s.append("\n");
        s.append("estGagne : "+this.isgagne);
        s.append("\n");
        s.append("main : ");
        s.append(this.main);
        return s.toString();
    }
    /**
	 * methode pour initialiser le joueur (après chaque tour)
	 */
    public void initialis() {
        this.releverIdentite=false;
        this.releverRumeur=false;
        this.condition=Condition.NON;
        this.cartesRelever.clear();
        this.setHorTour(false);
        this.main.clear();
    }
    /**
	 * methode donne le condition si on peut accuser le joueur ou non
	 */
    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

	public boolean isHorTour() {
		return horTour;
	}

	public void setHorTour(boolean horTour) {
		this.horTour = horTour;
	}

	public ArrayList<Carte> getCartesRelever() {
		return cartesRelever;
	}
	/**
	 * methode pour lever une carte parmi les cartes levees
	 */
	public void releverCarteDeCartesDelever(Carte c) {
		this.cartesRelever.remove(c);
	}
	/**
	 * methode static donner directement nombre aleatoir entre 0 et la valeur input
	 */
	public static int random(int x) {
    	Random rand = new Random(); //instance of random class
		return rand.nextInt(x);
    }

	public void choisirIdentite() {
		// TODO Auto-generated method stub
		
	}

	public Player accuser(ArrayList<Player> x) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carte choisirCarte(Player courJoueur) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carte reprenerCarte(ArrayList<Carte> arr) {
		// TODO Auto-generated method stub
		return null;
	}





}
