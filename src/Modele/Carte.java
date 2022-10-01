package Modele;

import java.util.*;

/**
 * <b>Carte est la classe représentant une Carte.</b>
 */

public class Carte {
	/**
	 * Nom de carte
	 */
    private String nom;
    /**
	 * Nombre de cartes : pour savoir combien de cartes ont été créées (le joueur peut ajouter avec de nouvelles fonctions).
	 */
    private static int nbrCartes = 0;
    /**
	 * effet witch de carte
	 */
    private ArrayList<Witch> E_witch;
    /**
	 * effet hunt de carte
	 */
    private ArrayList<Hunt> E_hunt;
    /**
	 *id de carte
	 */
    private int Id;
    /**
	 * condition pour utiliser effet witch
	 */
    private Condition c_witch = Condition.NON;
    /**
	 * condition pour utiliser effet hunt
	 */
    private Condition c_hunt = Condition.NON;
    /**
	 * condition pour jouer la carte
	 */
    private Condition c_general = Condition.NON;
    
    /** 
     * <b>Constructeur de Carte</b> 
     * 
     * input le nom de carte
     * nombre des cartes creees ++
     * 
     */ 
    public Carte(String name) {
        this.E_witch = new ArrayList<Witch>();
        this.E_hunt = new ArrayList<Hunt>();
        nbrCartes++;
        this.nom=name;
        this.Id=nbrCartes;
    }
    
    public ArrayList<Witch> getWitch() {
        return E_witch;
    }

    public ArrayList<Hunt> getHunt() {
        return E_hunt;
    }

    public int getId() {
        return this.Id;
    }

    

    public void write_witch(Witch x) {
        this.E_witch.add(x);
    }

    public void write_hunt(Hunt x) {
        this.E_hunt.add(x);
    }
    public Condition getc_witch() {
        return this.c_witch;
    }
    public Condition getc_hunt() {
        return this.c_hunt;
    }

    public Condition getc_general() {
        return this.c_general;
    }

    public void setc_witch(Condition c) {
        this.c_witch=c;
    }

    public void setc_hunt(Condition c) {
        this.c_hunt=c;
    }

    public void setc_general(Condition c) {
        this.c_general=c;
    }
    public static int getnbrCartes() {
        return nbrCartes;
    }
    public String getNom() {
        return this.nom;
    }
    public String toString() {
        StringBuffer s=new StringBuffer();
        s.append(this.Id + " -  "+this.nom);
        s.append("\n");
        Iterator<Witch> It=this.E_witch.iterator();
        while(It.hasNext()) {
            s.append("   "+It.next()+"  ");
        }
        s.append("\n");
        Iterator<Hunt> Iht=this.E_hunt.iterator();
        while(Iht.hasNext()) {
            s.append("   "+Iht.next()+"  ");
        }
        s.append("\n");
        return s.toString();
    }

	public boolean isJouableWitch(Player p) {
		if(this.c_witch!=Condition.NON) {
			if(!p.getreleverRumeur()) return false;
		}
		return true;
	}
	public boolean isJouableHunt(Player p) {
		if(this.c_hunt!=Condition.NON) {
			if(this.c_hunt==Condition.RCR) {
				if(!p.getreleverRumeur()) return false;
				}
			else if(this.c_hunt==Condition.RAV) {
				if(!p.getreleverIdentite()) return false;
			}
		}
		return true;
	}


	
	
}
