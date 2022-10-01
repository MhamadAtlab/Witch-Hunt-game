package Modele;

import java.util.ArrayList;

/**
 * <b>CartesRumeur est la classe qui nous permet de creer une carte.</b>
 * <p>
 *
 */

public class CartesRumeur {
	/**
	 * initialiser pour eviter la creation une carte plusieur fois.
	 *
	 */
    private static boolean initialiser = false;

    private static ArrayList<Carte> cartes = new ArrayList<Carte> ();

    /**
     * methode pour creer une carte
     * @param c
     */
    public static void creationCarteRumeur(Carte c) {
        cartes.add(c);
    }

    public static ArrayList<Carte> getCartes() {
        cartesPrincipales();
        return cartes;
    }

    /**
     * methode pour creer les 12 cartes principales
     */
    private static void cartesPrincipales() {
        if(!initialiser) {
            initialiser=true;
            // carte 1 : 
            Carte c;
            c=new Carte("Angry Mob");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.RIA);
            c.setc_hunt(Condition.RAV);
            cartes.add(c);
            // carte 2 :
            c=new Carte("The Inquisition");
            c.write_witch(Witch.DCV);
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.CPJ);
            c.write_hunt(Hunt.RSI);
            c.setc_hunt(Condition.RAV);
            cartes.add(c);
            // carte 3 :
            c=new Carte("Pointed Hai");
            c.write_witch(Witch.RVC);
            c.write_witch(Witch.JES);
            c.setc_witch(Condition.RCR);
            c.write_hunt(Hunt.RVC);
            c.write_hunt(Hunt.CPJ);
            c.setc_hunt(Condition.RCR);
            cartes.add(c);
            // carte 4 :
            c=new Carte("Hooked Noze");
            c.write_witch(Witch.PCA);
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.CPJ);
            c.write_hunt(Hunt.PCL);
            cartes.add(c);
            // carte 5 :
            c=new Carte("Broomstick");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.CPJ);
            c.setc_general(Condition.DC1);
            cartes.add(c);
            // carte 6 :
            c=new Carte("Wart");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.CPJ);
            c.setc_general(Condition.DC7);
            cartes.add(c);
            // carte 7 :
            c=new Carte("Ducking Stool");
            c.write_witch(Witch.CPJ);
            c.write_hunt(Hunt.CPJ);
            c.write_hunt(Hunt.RLIOD);
            cartes.add(c);
            // carte 8 :
            c=new Carte("Pauldron");
            c.write_witch(Witch.JAD);
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.RVI);
            cartes.add(c);
            // carte 9 :
            c=new Carte("Evil Eve");
            c.write_witch(Witch.CPJ);
            c.write_hunt(Hunt.CPJ);
            cartes.add(c);
            // carte 10 :
            c=new Carte("Toad");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.RVI);
            cartes.add(c);
            // carte 11 :
            c=new Carte("Black Pat");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.ADC);
            c.write_hunt(Hunt.DVC);
            c.write_hunt(Hunt.JES);
            cartes.add(c);
            // carte 12 :
            c=new Carte("Pet Newt");
            c.write_witch(Witch.JES);
            c.write_hunt(Hunt.ACA);
            c.write_hunt(Hunt.CPJ);
            cartes.add(c);
            
            
        }
    }

}
