package Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import Controleur.ControleurGenerale;
import Modele.Identite;

public class VueTextChoisirIdentite implements Observer, Runnable {

	private Thread t;

    public VueTextChoisirIdentite() {
    	

	int z=ControleurGenerale.partie.getJoueursPhysique().size();
	int i=0;
	while( i<z){
		ControleurGenerale.partie.getJoueursPhysique().get(i).addObserver(this);
		i++;
		}
	t = new Thread(this);
	t.start();
    }

    public void run() {

    	int saisie;

    	while(!ControleurGenerale.finChoisireIdentite) {
	    
	    System.out.println("Joueuer _"+ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i).getusername()+"_ choisissez votre identite ( 1 si witch et 0 si villager ) :");
	    saisie = this.lireChaine();
		ControleurGenerale.i ++;
		if(saisie==0){

					if(ControleurGenerale.i<ControleurGenerale.partie.getJoueursPhysique().size()) {
						ControleurGenerale.finChoisireIdentite=false;
					}
					else {
		
					ControleurGenerale.finChoisireIdentite=true;
					System.out.println("OK KHELASET!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i-1).setIdentite(Identite.Villager);
					}
				else if(saisie==1)	{
						if(ControleurGenerale.i<ControleurGenerale.partie.getJoueursPhysique().size()) {
							ControleurGenerale.finChoisireIdentite=false;
						}
						else {
			
						ControleurGenerale.finChoisireIdentite=true;
						}
						ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i-1).setIdentite(Identite.Witch);
					}
    	}
	    	
    }

    private int lireChaine() {
	BufferedReader br;

	int str=0;
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
    		if(Integer.parseInt(arg1.toString())==6)
        		{
	    			if(ControleurGenerale.finChoisireIdentite) {
	    				t.interrupt();
					}
					else {
						System.out.println("Joueuer _"+ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i).getusername()+"_ choisissez votre identite ( 1 si witch et 0 si villager ) :");
						
					}
        		}
    	}
    }

}
