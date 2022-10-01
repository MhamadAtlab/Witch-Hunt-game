package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import Controleur.ControleurGenerale;
import Controleur.ControleurPremieurJoueur;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JButton;
/**
 * <b>InterfaceJoueurs est la classe représentant la 2'eme interface de jeu.</b>
 * <p>à partir de cette classe, on peut ajouter des joueurs et choisir le nombre de joueurs</p>
 */
public class InterfaceJoueurs{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	private JLabel lblNewLabel_1;
	public JFrame frame;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextPane textPane;
	private JButton btnNewButton;
	private String username;
	private JComboBox comboBox;
	private int n;
	private int id;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public InterfaceJoueurs() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(frame.getContentPane().getFont().deriveFont(frame.getContentPane().getFont().getStyle() | Font.BOLD));
		frame.setBounds(100, 50, width-300, height-150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"3", "4", "5", "6"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(475, 374, 43, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Nombre de joueurs (physique + Bot) :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(213, 372, 252, 22);
		frame.getContentPane().add(lblNewLabel);
		
	    lblNewLabel_1 = new JLabel("Liste des Joueurs physique");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(749, 213, 170, 16);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("Ajouter un joueur (physique) :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(213, 240, 193, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(408, 245, 57, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
	    textPane = new JTextPane();
		textPane.setBounds(475, 242, 103, 20);
		frame.getContentPane().add(textPane);
		
		btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(408, 306, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleurGenerale.partie.ajouterJoueur(textPane.getText());
				textPane.setText(null);
				monupdate();
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Jouer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new ControleurPremieurJoueur(comboBox);
							PremieurJoueur window = new PremieurJoueur();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				new VueTextPremieurJoueur();
			}
		});
		btnNewButton_1.setBounds(408, 448, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(width-450, height-220, 115, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(749, 240, 115, 14);
		frame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setVisible(false);
		
		 lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(749, 265, 115, 14);
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		 lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(749, 290, 115, 14);
		frame.getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		
		 lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(749, 315, 115, 14);
		frame.getContentPane().add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);
		
		 lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(749, 340, 115, 14);
		frame.getContentPane().add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);
		
		 lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setBounds(749, 365, 115, 14);
		frame.getContentPane().add(lblNewLabel_9);
		lblNewLabel_9.setVisible(false);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setBounds(10, 10, 233, 137);
		Image icon= new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel_10.setIcon(new ImageIcon(icon));
		frame.getContentPane().add(lblNewLabel_10);
	}

	
	
	

	/**
	 * méthode pour afficher les joueurs ajoutés par les utilisateurs
	 */
		public void monupdate() {
			lblNewLabel_1.setVisible(true);
			n=ControleurGenerale.partie.getJoueurs().size();
			username=ControleurGenerale.partie.getJoueurs().get(n-1).getusername();
			id =ControleurGenerale.partie.getJoueurs().get(n-1).getId();
			
			if(n==1) {lblNewLabel_4.setText(id+" - "+username);lblNewLabel_4.setVisible(true);}
			if(n==2) {lblNewLabel_5.setText(id+" - "+username);lblNewLabel_5.setVisible(true);}
			if(n==3) {lblNewLabel_6.setText(id+" - "+username);lblNewLabel_6.setVisible(true);}
			if(n==4) {lblNewLabel_7.setText(id+" - "+username);lblNewLabel_7.setVisible(true);}
			if(n==5) {lblNewLabel_8.setText(id+" - "+username);lblNewLabel_8.setVisible(true);}
			if(n==6) {lblNewLabel_9.setText(id+" - "+username);lblNewLabel_9.setVisible(true);}
		
		
	}
}
