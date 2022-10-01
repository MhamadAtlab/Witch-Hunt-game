package Modele;

import java.util.*;

/**
 * <b>Bot est la classe représentant un Joueur virtuel.</b>
 */

public class Bot extends Player implements Strategie{

	public Bot() {
		super();
	}

	/** 
     * methode permet le Bot de choisir l'identité au hasard
     */ 
	@Override
	public void choisirIdentite() {
    	int int_random;
    	int_random = Player.random(2);
		if(int_random==1) this.setIdentite(Identite.Witch);
		else this.setIdentite(Identite.Villager);
		
	}
	
	/** 
     * methode permet le Bot d'accuser un joueur parmi les listes des joueur (input)
     */ 
	@Override
	public Player accuser(ArrayList<Player> x) {
		int y;
		y=Player.random(x.size());
		return x.get(y);
	}
	
	/** 
     * methode permet le Bot de choisir une carte
     * 
     */ 
	@Override
	public Carte choisirCarte(Player p) {
		int i=0;
			while(i<this.main.size()&&!(this.main.get(i).isJouableHunt(p)||main.get(i).isJouableWitch(p))) {
				i++;
			}
		this.setreleverRumeur(true);
		return this.main.get(i);
	}
	/** 
     * methode permet le Bot de reprenez une carte parmi arrayListe de cartes
     * 
     */ 
	@Override
	public Carte reprenerCarte(ArrayList<Carte> arr) {
			int y;
			y=Player.random(arr.size());
			return arr.get(y);
	}

	

	
	

}
