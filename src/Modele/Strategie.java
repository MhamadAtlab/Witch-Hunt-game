package Modele;

import java.util.ArrayList;

public interface Strategie {
	
	public void choisirIdentite();
	public Player accuser(ArrayList<Player> x);
	public Carte choisirCarte(Player p);
	public Carte reprenerCarte(ArrayList<Carte> arr);
}
