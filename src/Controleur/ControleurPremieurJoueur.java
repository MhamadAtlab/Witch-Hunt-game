package Controleur;

import javax.swing.JComboBox;
/**
 * <b>Carte est la classe représentant une Carte.</b>
 */
public class ControleurPremieurJoueur {
	public ControleurPremieurJoueur(JComboBox comboBox) {
		int n=Integer. parseInt(comboBox.getSelectedItem().toString());
		ControleurGenerale.partie.setNbrDeJoueur(n);
		ControleurGenerale.partie.creerjoueur();
	}
}