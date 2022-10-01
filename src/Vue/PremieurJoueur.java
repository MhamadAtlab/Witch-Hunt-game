package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controleur.ControleurGenerale;
import Controleur.ControleurPremieurJoueur;
import Modele.Tour;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
/**
 * <p>InterfaceJoueurs est la classe représentant la 3'eme interface de jeu.</p>
 * <p>à partir de cette classe, on peut choisir le premier joueur</p>
 */
public class PremieurJoueur implements Observer {
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	private JComboBox comboBox;
	public JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public PremieurJoueur() {
		initialize();
		ControleurGenerale.partie.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 50, width-300, height-150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(width-450, height-220, 115, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Liste des Joueurs :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(213, 213, 115, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(ControleurGenerale.partie.getJoueurString());
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(338, 215, 171, 178);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Choisir le primiere joueur (par ID) ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(553, 215, 237, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(ControleurGenerale.partie.getJoueursId()));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(800, 216, 41, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Jouer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ControleurGenerale.partie.setPreJoueur(ControleurGenerale.partie.getJoueurs().get(Integer. parseInt(comboBox.getSelectedItem().toString())-1));
							ControleurGenerale.tour=new Tour(ControleurGenerale.partie.getJoueurs(),ControleurGenerale.partie.getPreJoueur());
							InterfaceTour window = new InterfaceTour();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(664, 370, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(10, 10, 233, 137);
		Image icon= new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel_10.setIcon(new ImageIcon(icon));
		frame.getContentPane().add(lblNewLabel_10);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Integer ) {
    		if(Integer.parseInt(arg.toString())==5)
    		{
    			this.frame.setVisible(false);
				this.frame.dispose();
    		}
    	}
		
	}

}
