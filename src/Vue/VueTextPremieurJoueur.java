package Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Controleur.ControleurGenerale;


public class VueTextPremieurJoueur implements Observer, Runnable {

	private Thread t;
	boolean b=false;
    public VueTextPremieurJoueur( ){
    	

	ControleurGenerale.partie.addObserver(this);
	t = new Thread(this);
	t.start();
    }

    public void run() {

	int saisie = 0;
	boolean quitter = false;

	int z=ControleurGenerale.partie.getJoueurs().size();
	int i=0;
	while( i<z){
		System.out.println(ControleurGenerale.partie.getJoueurs().get(i).getId()+" - "+ ControleurGenerale.partie.getJoueurs().get(i).getusername()+"\n");
		i++;
		}
	    saisie = this.lireChaine();

		if(saisie>0&&saisie<z+1&&!b) {
	    	InterfaceTour window2 = new InterfaceTour();
			window2.frame.setVisible(true);
	    	ControleurGenerale.partie.setPreJoueur(ControleurGenerale.partie.getJoueurs().get(saisie-1));
			}
    }

    private int lireChaine() {
	BufferedReader br;

	int str=0;
		System.out.println("Choisir le primiere joueur (par ID) :");
		br= new BufferedReader (new InputStreamReader(System.in));
		String resultat = null;
		try {
		    resultat = br.readLine();
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		}
		
    	
        str=Integer.parseInt(resultat);
	return str;	
    }

    @Override
    public void update(Observable arg0, Object arg1) {
    	if(arg1 instanceof Integer ) {
    		if(Integer.parseInt(arg1.toString())==5)
        		{
    			this.b=true;
    			t.interrupt();
        		}
    	}
    }

}
