package Modele;

import java.util.*;
/**
 * <b>JoueurPhysique est la classe représentant un Joueur physique.</b>
 * <p>
 */
public class JoueurPhysique extends Player implements Strategie{
	
	public JoueurPhysique(String nom) {
		super(nom);
	}

	/** 
     * methode permet le joueurphysique d'accuser un joueur parmi les listes des joueur (input)
     */ 
	@Override
	public Player accuser(ArrayList<Player> x) {
		Player p=null;
		int y;
		System.out.println("----------------------------------------------------");
		for(y=0;y<x.size();y++) {
			System.out.println(y+1+" - "+x.get(y).getusername());
		}
		Scanner kbhit = new Scanner(System.in);
    	int str;
    	boolean onContinue = true;
    	while(onContinue) {
			System.out.println("Choisissez un joueur (par numero)");
            str = Integer.parseInt( kbhit.next());
            if(str>0&&str<=x.size()) {onContinue=false;
            p=x.get(str-1);}
			else onContinue=true;
			}
    	return p;
		
	}
	/** 
     * methode permet le joueurphysique de choisir une carte
     * 
     */ 
	@Override
	public Carte choisirCarte(Player p) {
		int i;
		Carte c=null;
		System.out.println("------------------------------------------------\n");
		System.out.println("Vous avez : \n");
		for(i=0;i<main.size();i++) {
			System.out.println(i+1+" - " +this.main.get(i).getNom());
		}
		System.out.println("------------------------------------------------\n");
		Scanner kbhit = new Scanner(System.in);
    	int str;
    	boolean onContinue = true;
    	while(onContinue) {
			System.out.println("Choisissez une carte (par numero) : ");

            str = Integer.parseInt( kbhit.next());
			if(str<=this.main.size()&&str>0) {onContinue=false;
			c=main.get(str-1);}
			else onContinue=true;
			}
    	
    	this.setreleverRumeur(true);
		return c;
	}
	/** 
     * methode permet le joueurphysique de reprenez une carte parmi arrayListe de cartes
     * 
     */
	@Override
	public Carte reprenerCarte(ArrayList<Carte> arr) {
		int i;
		Carte c=null;
		System.out.println("------------------------------------------------\n");
		System.out.println("il y a : \n");
		for(i=0;i<arr.size();i++) {
			System.out.println(i+1+" - " +arr.get(i).getNom());
		}
		System.out.println("------------------------------------------------\n");
		Scanner kbhit = new Scanner(System.in);
    	int str;
    	boolean onContinue = true;
    	while(onContinue) {
			System.out.println("Choisissez une carte (par numero) : ");

            str = Integer.parseInt( kbhit.next());
			if(str<=arr.size()&&str>0) {onContinue=false;
			c=arr.get(str-1);}
			else onContinue=true;
			}
    	
		return c;
	}
	/** 
     * methode permet le joueurphysique de choisir identite
     * 
     */
	public void setIdentite(Identite i) {
        this.identite=i;
    	this.setChanged();
    	this.notifyObservers(6);
    }
	
	

}
