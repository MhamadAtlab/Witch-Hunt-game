package Controleur;

import Modele.Partie;
/**
 * <b>ControleurGenerale est la classe qui contient des attributs statique</b>
 * <p>GeneralController nous permet de partager des informations entre différentes classes et packages</p>
 */
public class ControleurStart {

	public ControleurStart() {
		ControleurGenerale.partie=new Partie();
	}
}
