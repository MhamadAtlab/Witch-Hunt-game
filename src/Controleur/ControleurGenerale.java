package Controleur;
import java.util.*;

import Modele.Partie;
import Modele.Player;
import Modele.Tour;

/**
 * <b>GeneralController contient des attributs statiques</b>
 * <p>GeneralController nous permet de partager des informations entre différentes classes et packages</p>
 */

public class ControleurGenerale {
	public static Partie partie;
	public static Tour tour;
	public static Iterator<Player> It;
	public static Player player;
	public static int i;

	public static boolean finChoisireIdentite;
}