package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class ControleurJoueurs {

	public ControleurJoueurs(JButton b, JTextPane user) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleurGenerale.partie.ajouterJoueur(user.getText());
			}
		});
	}
}
