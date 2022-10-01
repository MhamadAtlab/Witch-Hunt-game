package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import Controleur.ControleurStart;
/**
 * <b>InterfaceStart est la classe représentant la première interface de jer.</b>
 */
public class InterfaceStart {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceStart window = new InterfaceStart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public InterfaceStart() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 50, width-300, height-150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new ControleurStart();
							InterfaceJoueurs window = new InterfaceJoueurs();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds((width-300-100)/2-50, 200, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("A propo de jeu");
		
		btnNewButton_1.setBounds((width-300-100)/2-50, 300, 115, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	
		
		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(width-450, height-220, 115, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("");
		Image jeu_info= new ImageIcon(this.getClass().getResource("/info_jeu.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(jeu_info));
		
		lblNewLabel.setBounds(30, 10, width-450, height-205);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.setBounds(width-450, height-260, 115, 23);
		frame.getContentPane().add(btnNewButton_3);
		btnNewButton_3.setVisible(false);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 10, 233, 137);
		Image icon= new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(icon));
		frame.getContentPane().add(lblNewLabel_3);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setVisible(false);
				btnNewButton_1.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel.setVisible(true);
				btnNewButton_3.setVisible(true);
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setVisible(true);
				btnNewButton_1.setVisible(true);
				lblNewLabel_3.setVisible(true);
				lblNewLabel.setVisible(false);
				btnNewButton_3.setVisible(false);
			}
		});
	}
}
