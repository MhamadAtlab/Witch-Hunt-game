package Vue;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controleur.ControleurGenerale;
import Controleur.ControleurStart;
import Modele.Carte;
import Modele.Hunt;
import Modele.Identite;
import Modele.Player;
import Modele.Tour;
import Modele.Witch;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

/**
 * <p>InterfaceTour est la classe représentant l'interface tour de jeu.</p>
 * <p>à partir de cette classe, on joue jusqu'à la fin du jeu</p>
 */
public class InterfaceTour implements Observer{

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public JFrame frame;
	private JButton btnNewButton_1;
	private JComboBox comboBox;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JTable table;
	private JLabel lblNewLabel_4;
	private JLabel username;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private ArrayList<JLabel> Icartes;
	private ArrayList<JButton> IcarteChoisit;
	private JComboBox comboBoxchoisirp;
	private JButton btnchoisirp;
	private JButton releverIdentite;
	private JButton utiliserHunt;
	private JButton utiliserWitch;
	private ArrayList<Witch> eWitch;
	private ArrayList<Hunt> eHunt;
	private Witch w;;
	private Hunt h;;
	private boolean caseRLIOD=false;
	private Iterator<Witch> It;
	private Iterator<Hunt> It_h;
	private boolean finWitch=true;
	private boolean finHunt=true;
	private ArrayList<Integer> tabelWitch=new ArrayList<Integer>();
	private JButton btnOK ;
	private String s="";
	
	private JLabel choisirJoueur;
	
	private JLabel listp;
	
	private JLabel listText;
	
	private boolean peutaccuser=true;
	private boolean peuthunt=true;
	private boolean peutwitch=true;
	private JLabel identite;
	private JLabel message;
	
	public InterfaceTour() {
		for(int i=10;i<18;i++) tabelWitch.add(i);
		initialize();
		ControleurGenerale.tour.addObserver(this);
		jouer();
	}
	/**
	 * méthode pour jouer une nouvelle tour
	 */
	public void jouer() {
				ControleurGenerale.tour.initialisJoueurs();
				ControleurGenerale.tour.distribuerCartes();
				ControleurGenerale.tour.choisirIdentite();
				if(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur()).isEmpty()) {
					System.out.println("peutaccuser=false");
					peutaccuser=false;
				}
				else {
					System.out.println("peutaccuser=true");
					peutaccuser=true;
				}
				if(ControleurGenerale.partie.getJoueursPhysique().size()!=0) {
					
					int z=ControleurGenerale.partie.getJoueursPhysique().size();
					int i=0;
					while( i<z){
						ControleurGenerale.partie.getJoueursPhysique().get(i).addObserver(this);
						i++;
						}
					
					ControleurGenerale.i=0;
					choisirIdentite();
					new VueTextChoisirIdentite();
				}
	}
	/**
	 * <p>méthode qui décide si la tour est terminée ou si le jeu est terminé...</p>
	 * 
	 * 
	 */
	public void continuer() {
		this.finHunt=true;
		this.finWitch=true;
		if(!ControleurGenerale.tour.estTerminee()) {
			updateScores();
			username.setText(ControleurGenerale.tour.getcourJoueur().getusername());
			ControleurGenerale.tour.jouer();
			this.AccuserouHuntsetVisible(ControleurGenerale.tour.getcourJoueur());
		}
		else {
			System.out.println("tour est terminé");
			this.username.setVisible(false);
			updateScores();
			if(!ControleurGenerale.partie.estFin()) {
				System.out.println("partie non terminé");
				ControleurGenerale.partie.commencer();
				jouer();
			}
			else {
				System.out.println("partie est terminé");
				ControleurGenerale.partie.cummuleScore();
				message.setText("Joueur gagnant : "+ControleurGenerale.partie.getJoueurGagnes().getusername()+" apres "+ControleurGenerale.partie.getNbDeTour()+" tours !!!");
				message.setVisible(true);
			}
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 50, width-300, height-150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Quitter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		btnNewButton.setBounds(916, 548, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		table = new JTable();
		table.setEnabled(false);
		table.setBackground(new Color(211, 211, 211));
		table.setModel(new DefaultTableModel(
				ControleurGenerale.partie.getResultat(),
			new String[] {
				"Player", "Score"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setBounds(885, 43, 146, 16*ControleurGenerale.partie.getJoueurs().size());
		frame.getContentPane().add(table);
		table.setVisible(false);
		
		lblNewLabel_4 = new JLabel("Player              Score");
		lblNewLabel_4.setEnabled(false);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(885, 20, 146, 23);
		lblNewLabel_4.setVisible(false);
		frame.getContentPane().add(lblNewLabel_4);
		

		identite = new JLabel("test");
		identite.setBounds(542, 174, 220, 300);
		identite.setVisible(false);
		frame.getContentPane().add(identite);
		
		message = new JLabel("");
		message.setBounds(311, 178, 300, 178);
		message.setVisible(false);
		frame.getContentPane().add(message);
		
		username = new JLabel("");
		username.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		username.setBounds(386, 11, 128, 62);
		username.setVisible(false);
		frame.getContentPane().add(username);
		

		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOK.setVisible(false);
				message.setVisible(false);
				identite.setVisible(false);
				continuer();
			}
		});
		
		btnOK.setBounds(161, 393, 89, 23);
		btnOK.setVisible(false);
		frame.getContentPane().add(btnOK);
		
		listJoueur();
		afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),1);
		releverIdentiterOuWitch();
		AccuserouHunt();
	}
	
	public void updateScores() {
		table.setModel(new DefaultTableModel(
				ControleurGenerale.partie.getResultat(),
			new String[] {
				"Player", "Score"
			}
		));
	}
	/**
	 * méthode initialise l'interface pour choisir l'identité
	 */
	public void choisirIdentite() {
		lblNewLabel = new JLabel("choisissez votre identite");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblNewLabel.setBounds(260, 230, 157, 23);
		frame.getContentPane().add(lblNewLabel);
		
		 lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(607, 137, 230, 323);
		Image witch= new ImageIcon(this.getClass().getResource("/witch.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(witch));
		frame.getContentPane().add(lblNewLabel_1);
		
		
		 lblNewLabel_2 = new JLabel(ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i).getusername());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(260, 163, 176, 56);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 10, 233, 137);
		Image icon= new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(icon));
		frame.getContentPane().add(lblNewLabel_3);
		

		comboBox = new JComboBox(Identite.values());
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(427, 231, 99, 22);
		frame.getContentPane().add(comboBox);
		
		btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleurGenerale.i++;
				if(ControleurGenerale.i<ControleurGenerale.partie.getJoueursPhysique().size()) {
					lblNewLabel_2.setText(ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i).getusername());
					ControleurGenerale.finChoisireIdentite=false;
				}
				else {

				ControleurGenerale.finChoisireIdentite=true;
				setUnvisible_CD();
				}
				ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i-1).setIdentite((Identite)comboBox.getSelectedItem());
			}
		});
		btnNewButton_1.setBounds(427, 291, 65, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	/**
	 * methode affiche l'interface choisir identite
	 */
	public void setUnvisible_CD() {
		btnNewButton_1.setVisible(false);
		comboBox.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_1.setVisible(false);
		lblNewLabel.setVisible(false);
	}
	/**
	 * methode affiche les options du joueur pour accuser ou relever une carte de rumeur
	 */
	public void AccuserouHuntsetVisible(Player p) {
		if(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur()).isEmpty()) {
			System.out.println("peutaccuser=false");
			peutaccuser=false;
		}
		else {
			System.out.println("peutaccuser=true");
			peutaccuser=true;
		}
		if(ControleurGenerale.tour.cartesJouableHunt(p).isEmpty()) {
			System.out.println("peuthunt=false");
			peuthunt=false;
		}
		else {
			System.out.println("peuthunt=true");
			peuthunt=true;
		}
		if(!peuthunt&&!peutaccuser) {
			ControleurGenerale.tour.getcourJoueur().setHorTour(true);
			this.continuer();
		}
		else
		{
			lblNewLabel_4.setVisible(true);
			table.setVisible(true);
			
			username.setText(ControleurGenerale.tour.getcourJoueur().getusername());
			username.setVisible(true);
			btnNewButton_2.setVisible(true);
			btnNewButton_3.setVisible(true);
			btnNewButton_3.setEnabled(peuthunt);
			btnNewButton_2.setEnabled(peutaccuser);
		}
	}
	/**
	 * méthode initialise l'interface pour accuser ou relever une carte de rumeur
	 */
	public void AccuserouHunt() {
		btnNewButton_2 = new JButton("Accuser ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setVisible(false);
				btnNewButton_3.setVisible(false);
				listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur())));
				comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur()))));
				listJoueursetVisible();
			}
		});
		btnNewButton_2.setBounds(311, 174, 300, 23);
		btnNewButton_2.setVisible(false);
		btnNewButton_2.setEnabled(peutaccuser);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Relever carte Rumeur -- effet Hunt");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setVisible(false);
				btnNewButton_3.setVisible(false);
				afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),2);
				setVisibleCartes();
			}
		});
		btnNewButton_3.setBounds(311, 227, 300, 23);
		btnNewButton_3.setVisible(false);
		btnNewButton_3.setEnabled(peuthunt);
		frame.getContentPane().add(btnNewButton_3);
		
	}
	
	/**
	 * méthode initialise l'interface pour afficher les cartes de jouer
	 */
	public void afficherCartes(ArrayList<Carte> arr, int c) { // c=1 si on utilise effet witch, et =2 si l'effet hunt ,et =3 pour deffaus.
																// et 4 pour reprener CArte
		
		
		int i;
		JLabel lblNewLabel_7;
		JButton btnNewButton_7;
		Image icon;
		this.Icartes=new ArrayList<JLabel>();
		this.IcarteChoisit=new ArrayList<JButton>();
		for(i=0;i<arr.size();i++) {
			
			btnNewButton_7 = new JButton("Choisir");
			int j=i;
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setunVisibleCartes();
					if(c==4) {
						ControleurGenerale.tour.getcourJoueur().ajouterCarte(arr.get(j));
						arr.remove(j);
						if(!finHunt) {
							h=It_h.next();
							ControleurGenerale.tour.hunt(h);
						}
						if(!finWitch) {
							System.out.println("effet witch is using");
							w=It.next();
							ControleurGenerale.tour.witch(w);
						}
						
					}
					if(c==3) {
						ControleurGenerale.tour.discardCartes.add(arr.get(j));
						arr.remove(j);
						if(!finWitch&&finHunt) {
							w=It.next();
							ControleurGenerale.tour.witch(w);
						}
						if(!finHunt){
							h=It_h.next();
							ControleurGenerale.tour.hunt(h);
						}
						if(caseRLIOD) {
							caseRLIOD=false;
							continuer();
						}
					}
					else {
						ControleurGenerale.tour.setcartejoue(arr.get(j));
						ControleurGenerale.tour.getcourJoueur().releverCarte(arr.get(j));
						if(c==1) {
							finWitch=false;
							finHunt=true;
							eWitch=ControleurGenerale.tour.getcartejoue().getWitch();
							It=eWitch.iterator();
							w=It.next();
							ControleurGenerale.tour.witch(w);
						}
						if(c==2) {
							finHunt=false;
							finWitch=true;
							eHunt=ControleurGenerale.tour.getcartejoue().getHunt();
							It_h=eHunt.iterator();
							h=It_h.next();
							ControleurGenerale.tour.hunt(h);
						}
					}
				}
			});
			btnNewButton_7.setBounds(10+225*i, 410, 100, 23);
			if(c==1&&finWitch) btnNewButton_7.setEnabled(arr.get(j).isJouableWitch(ControleurGenerale.tour.getcourJoueur()));
			if(c==2&&finHunt) btnNewButton_7.setEnabled(arr.get(j).isJouableHunt(ControleurGenerale.tour.getcourJoueur()));
			btnNewButton_7.setVisible(false);
			frame.getContentPane().add(btnNewButton_7);
			
			IcarteChoisit.add(btnNewButton_7);
			
			lblNewLabel_7 = new JLabel("");
			icon= new ImageIcon(this.getClass().getResource("/carte_"+arr.get(i).getId()+".png")).getImage();
			lblNewLabel_7.setIcon(new ImageIcon(icon));
			lblNewLabel_7.setBounds(10+225*i, 100, 220, 300);
			lblNewLabel_7.setVisible(false);
			frame.getContentPane().add(lblNewLabel_7);
			Icartes.add(lblNewLabel_7);
		}
	}
	/**
	 * méthode affiche les cartes de joueur
	 */
	public void setVisibleCartes() {
		for(int i=0;i<Icartes.size();i++) {
			Icartes.get(i).setVisible(true);
			IcarteChoisit.get(i).setVisible(true);
		}
	}
	/**
	 * méthode masque les cartes de joueur
	 */
	public void setunVisibleCartes() {
		for(int i=0;i<Icartes.size();i++) {
			Icartes.get(i).setVisible(false);
			IcarteChoisit.get(i).setVisible(false);
		}
	}
	/**
	 * méthode initialise l'interface afficher la liste des joueurs
	 */
	public void listJoueur() {
		listText = new JLabel("Liste des Joueurs :");
		listText.setFont(new Font("Tahoma", Font.BOLD, 12));
		listText.setBounds(213, 213, 115, 16);
		listText.setVisible(false);
		frame.getContentPane().add(listText);
		
		listp = new JLabel();
		listp.setVerticalAlignment(SwingConstants.TOP);
		listp.setBounds(338, 215, 171, 178);
		listp.setVisible(false);
		frame.getContentPane().add(listp);
		
		choisirJoueur = new JLabel("Choisir joueur (par ID) ");
		choisirJoueur.setFont(new Font("Tahoma", Font.BOLD, 12));
		choisirJoueur.setBounds(553, 215, 237, 23);
		choisirJoueur.setVisible(false);
		frame.getContentPane().add(choisirJoueur);
		
		comboBoxchoisirp = new JComboBox();
		comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.partie.getJoueursId()));
		comboBoxchoisirp.setSelectedIndex(0);
		comboBoxchoisirp.setBounds(800, 216, 41, 22);
		comboBoxchoisirp.setVisible(false);
		frame.getContentPane().add(comboBoxchoisirp);
		
		btnchoisirp = new JButton("accuser");
		btnchoisirp.setVisible(false);
		btnchoisirp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							listJoueursetunVisivble();
							if(!finWitch) {
								finWitch=true;
								btnchoisirp.setText("accuser");
								ControleurGenerale.tour.setderJoueur(ControleurGenerale.tour.getcourJoueur());
								ControleurGenerale.tour.setcourJoueur(ControleurGenerale.partie.getJoueurs().get(Integer. parseInt(comboBoxchoisirp.getSelectedItem().toString())-1));
								username.setText(ControleurGenerale.tour.getcourJoueur().getusername());
								
								if(!finHunt) {
									h=It_h.next();
				    				ControleurGenerale.tour.hunt(h);
				    			}
								else
										continuer();
							}
							else{
								ControleurGenerale.tour.setproJoueur(ControleurGenerale.partie.getJoueurs().get(Integer. parseInt(comboBoxchoisirp.getSelectedItem().toString())-1));
								username.setText(ControleurGenerale.tour.getproJoueur().getusername());

								if(!finHunt) {
									btnchoisirp.setText("accuser");
									if(ControleurGenerale.tour.getproJoueur().getIdentite()==Identite.Villager) {
									ControleurGenerale.tour.getcourJoueur().setScore(-2);
									s+= "<html>Le joueur "+ControleurGenerale.tour.getcourJoueur().getusername()+" : -2 </html>";
									ControleurGenerale.tour.setderJoueur(ControleurGenerale.tour.getcourJoueur());
									ControleurGenerale.tour.setcourJoueur(ControleurGenerale.tour.getproJoueur());
									}
									else {
										ControleurGenerale.tour.getcourJoueur().setScore(2);
										ControleurGenerale.tour.getproJoueur().setHorTour(true);
										s="<html> "+ControleurGenerale.tour.getproJoueur().getusername()+" hors tour ! <br>";
										s+= "Le joueur "+ControleurGenerale.tour.getcourJoueur().getusername()+" : +2 </html>";
									}
									updateScores();
									releverIdentite();
									btnOK.setVisible(true);
								}
								else {
									releverIdentiterOuWitchsetVisible(ControleurGenerale.tour.getproJoueur());
								}
								
								
							}
					
			}
		});
		btnchoisirp.setBounds(664, 370, 89, 23);
		btnchoisirp.setVisible(false);
		frame.getContentPane().add(btnchoisirp);
	}
	/**
	 * méthode affiche la liste des joueurs
	 */
	public void listJoueursetVisible() {
		listText.setVisible(true);
		choisirJoueur.setVisible(true);
		listp.setVisible(true);
		comboBoxchoisirp.setVisible(true);
		btnchoisirp.setVisible(true);
	}
	/**
	 * méthode masque la liste des joueurs
	 */
	public void listJoueursetunVisivble() {
		listText.setVisible(false);
		choisirJoueur.setVisible(false);
		listp.setVisible(false);
		comboBoxchoisirp.setVisible(false);
		btnchoisirp.setVisible(false);
	}
	
	public void releverIdentite() {
		Image icon;
		
		if(ControleurGenerale.tour.getproJoueur().getIdentite()==Identite.Witch) {
			icon= new ImageIcon(this.getClass().getResource("/witch.png")).getImage();
			if(caseRLIOD) {
				caseRLIOD=false;
			}
			else if(finHunt) {
					s="<html> "+ControleurGenerale.tour.getproJoueur().getusername()+" hors tour ! <br>";
					s+= "Le joueur "+ControleurGenerale.tour.getcourJoueur().getusername()+" : +1 </html>";
				}
				else finHunt=true;
		}
		else {
			icon= new ImageIcon(this.getClass().getResource("/villager.png")).getImage();
		}
		message.setText(s);
		message.setVisible(true);
		identite.setIcon(new ImageIcon(icon));
		identite.setVisible(true);
	}
	
	/**
	 * methode affiche les options du joueur pour relever identite ou relever une carte de rumeur
	 */
	public void releverIdentiterOuWitchsetVisible(Player p) {
		if(ControleurGenerale.tour.cartesJouableWitch(p).isEmpty()) {
			System.out.println("peutwitch=false");
			peutwitch=false;
		}
		else {
			System.out.println("peutwitch=true");
			peutwitch=true;
		}
		releverIdentite.setVisible(true);
		utiliserWitch.setVisible(true);
		utiliserWitch.setEnabled(peutwitch);
	}
	
	/**
	 * méthode initialise l'interface pour relever identité ou relever une carte de rumeur
	 */
	public void releverIdentiterOuWitch() {
		releverIdentite = new JButton("relever votre identite");
		releverIdentite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				releverIdentite.setVisible(false);
				utiliserWitch.setVisible(false);
				
				if(caseRLIOD) {
					utiliserWitch.setText("Relever carte Rumeur -- effet Witch");
					
					System.out.println("L'identite de "+ControleurGenerale.tour.getcourJoueur().getusername()+" est --> "+ControleurGenerale.tour.getcourJoueur().getIdentite());
  				    if(ControleurGenerale.tour.getcourJoueur().getIdentite()==Identite.Villager) {
						    System.out.println("\n Le joueur _"+ControleurGenerale.tour.getderJoueur().getusername()+"_ : -1\n" );
						    s ="<html>"+"Le joueur "+ControleurGenerale.tour.getderJoueur().getusername()+" : -1</html>";
						    ControleurGenerale.tour.getderJoueur().setScore(-1);
  				    }
  					  else {
  						ControleurGenerale.tour.getderJoueur().setScore(1);
  						ControleurGenerale.tour.getcourJoueur().setHorTour(true);
  						System.out.println(ControleurGenerale.tour.getcourJoueur().getusername()+" hors tour !");
  						System.out.println("\n Le joueur _"+ControleurGenerale.tour.getderJoueur().getusername()+"_ : +1\n" );
  						s="<html>"+ControleurGenerale.tour.getcourJoueur().getusername()+" hors tour !<br>";
  						s+="Le joueur _"+ControleurGenerale.tour.getderJoueur().getusername()+"_ : +1</html>";
  						ControleurGenerale.tour.setcourJoueur(ControleurGenerale.tour.getderJoueur());
  						}
				}
				else ControleurGenerale.tour.releveIdentite();
				
				updateScores();
				releverIdentite();
				btnOK.setVisible(true);
			}
		});
		releverIdentite.setBounds(311, 174, 300, 23);
		releverIdentite.setVisible(false);
		frame.getContentPane().add(releverIdentite);
		
		utiliserWitch = new JButton("Relever carte Rumeur -- effet Witch");
		utiliserWitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				releverIdentite.setVisible(false);
				utiliserWitch.setVisible(false);
				
				if(caseRLIOD) {
					utiliserWitch.setText("Relever carte Rumeur -- effet Witch");
					afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),3);
    				setVisibleCartes();
				}
				else {
					ControleurGenerale.tour.setderJoueur(ControleurGenerale.tour.getcourJoueur());
					ControleurGenerale.tour.setcourJoueur(ControleurGenerale.tour.getproJoueur());
					afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),1);
					setVisibleCartes();
				}
			}
		});
		utiliserWitch.setBounds(311, 227, 300, 23);
		utiliserWitch.setEnabled(peutwitch);
		utiliserWitch.setVisible(false);
		frame.getContentPane().add(utiliserWitch);
		
		
		
		
		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if(arg instanceof Integer ) {
    		if(Integer.parseInt(arg.toString())==6)
    		{
    			if(ControleurGenerale.finChoisireIdentite) {

    				this.setUnvisible_CD();
    				this.AccuserouHuntsetVisible(ControleurGenerale.tour.getcourJoueur());
				}
				else {
					this.lblNewLabel_2.setText(ControleurGenerale.partie.getJoueursPhysique().get(ControleurGenerale.i).getusername());
				}
    		}
    		
    		//effet Witch
//    		if(tabelWitch.contains(Integer.parseInt(arg.toString()))&&!finWitch) {
    			
	    		if(Integer.parseInt(arg.toString())==10) {//JES
	    			this.finWitch=true;
	    			this.continuer();
	    		}
	    		if(Integer.parseInt(arg.toString())==11) {//DCV
	    			if(ControleurGenerale.tour.getcourJoueur().getmain().isEmpty()) {
	    				w=It.next();
						ControleurGenerale.tour.witch(w);
						
	    			}
	    			else	{
	    				afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),3);
	    				setVisibleCartes();
	    			}
	    		}
	    		if(Integer.parseInt(arg.toString())==12) {//RVC
	    			if(ControleurGenerale.tour.getcourJoueur().getCartesRelever().isEmpty()) {
	    				System.out.println("pas des cartes !!");
	    				w=It.next();
						ControleurGenerale.tour.witch(w);
	    				}
	    			else 
	    				{
	    				afficherCartes(ControleurGenerale.tour.getcourJoueur().getCartesRelever(),4);
	    				setVisibleCartes();
	    				}
	    		}
	    		if(Integer.parseInt(arg.toString())==13) {//PCA
	    			w=It.next();
					ControleurGenerale.tour.witch(w);
	    		}
	    		if(Integer.parseInt(arg.toString())==14) {//CPJ
	    				btnchoisirp.setText("Choisir");
	    				listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur())));
						comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur()))));
						listJoueursetVisible();
	    			
	    			
	    		}
	    		if(Integer.parseInt(arg.toString())==15) {//JAD
	    			w=It.next();
					ControleurGenerale.tour.witch(w);
	    		}
	    		if(Integer.parseInt(arg.toString())==16) {//AAP
	    			if(!ControleurGenerale.tour.getJoueursActivesI_C_SansP1P2(ControleurGenerale.tour.getderJoueur(),ControleurGenerale.tour.getcourJoueur()).isEmpty()) {
	    				listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesI_C_SansP1P2(ControleurGenerale.tour.getderJoueur(),ControleurGenerale.tour.getcourJoueur())));
						comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesI_C_SansP1P2(ControleurGenerale.tour.getderJoueur(),ControleurGenerale.tour.getcourJoueur()))));
						
	    			}
	    			else {
						  if(!ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur()).isEmpty()) {
							  listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur())));
								comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesI_C_SansP(ControleurGenerale.tour.getcourJoueur()))));
								
						  	}
	    				}
	    			listJoueursetVisible();
	    		}
	    		
	    		if(Integer.parseInt(arg.toString())==20) {//RIA
	    			btnchoisirp.setText("Choisir");
    				listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur())));
					comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur()))));
					listJoueursetVisible();
	    		}
	    		if(Integer.parseInt(arg.toString())==21) {//CPJ
	    			
    				finWitch=false;
    				if(!It_h.hasNext()) {
	    				finHunt=true;
	    			}
    				System.out.println("choirsir prochain");
    				btnchoisirp.setText("Choisir");
    				listp.setText(ControleurGenerale.tour.getJoueurString(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur())));
					comboBoxchoisirp.setModel(new DefaultComboBoxModel(ControleurGenerale.tour.getJoueursId(ControleurGenerale.tour.getJoueursActivesSansP(ControleurGenerale.tour.getcourJoueur()))));
					listJoueursetVisible();
					
	    		}
	    		if(Integer.parseInt(arg.toString())==22) {//RSI
	    			finHunt=true;
	    			this.continuer();
	    		}
	    		if(Integer.parseInt(arg.toString())==23) {//RVC
	    			if(ControleurGenerale.tour.getcourJoueur().getCartesRelever().isEmpty()) {
	    				System.out.println("pas des cartes !!");
	    				h=It_h.next();
						ControleurGenerale.tour.hunt(h);
	    				}
	    			else 
	    				{
	    				afficherCartes(ControleurGenerale.tour.getcourJoueur().getCartesRelever(),4);
	    				setVisibleCartes();
	    				}
	    		}
	    		if(Integer.parseInt(arg.toString())==24) {//PCL
	    			finHunt=true;
	    			this.continuer();
	    		}
	    		if(Integer.parseInt(arg.toString())==25) {//RLIOD
	    			finHunt=true;
	    		   caseRLIOD=true;
				    ControleurGenerale.tour.setproJoueur(ControleurGenerale.tour.getcourJoueur());
	    		utiliserWitch.setText("deffausser une carte");
	    		releverIdentiterOuWitchsetVisible(  ControleurGenerale.tour.getproJoueur());
	    		}
	    		if(Integer.parseInt(arg.toString())==26) {//RVI
	    			finHunt=true;
	    			 if(ControleurGenerale.tour.getcourJoueur().getIdentite()==Identite.Villager) {
	    				 ControleurGenerale.tour.hunt(Hunt.CPJ);
	    			 }
	    			 else continuer();
	    		}
	    		if(Integer.parseInt(arg.toString())==27) {//ADC
	    			System.out.println(ControleurGenerale.tour.getDiscardCartes());
	    			if(ControleurGenerale.tour.getDiscardCartes().isEmpty()) {
	    				h=It_h.next();
						ControleurGenerale.tour.hunt(h);
	    				}
	    			else 
	    				{
	    				setunVisibleCartes();
	    				afficherCartes(ControleurGenerale.tour.getDiscardCartes(),4);
	    				setVisibleCartes();
	    				}
	    		}
	    		
	    		if(Integer.parseInt(arg.toString())==28) {//DVC
	    			if(ControleurGenerale.tour.getcourJoueur().getmain().isEmpty()) {
	    				h=It_h.next();
						ControleurGenerale.tour.hunt(h);
	    			}
	    			else	{
	    				setunVisibleCartes();
	    				afficherCartes(ControleurGenerale.tour.getcourJoueur().getmain(),3);
	    				setVisibleCartes();
	    			}
	    		}
	    		
	    		if(Integer.parseInt(arg.toString())==29) {//ACA
	    			if(ControleurGenerale.tour.getDiscardCarteSansCourJoueur().isEmpty()) {
	    				h=It_h.next();
						ControleurGenerale.tour.hunt(h);
	    				}
	    			else 
	    				{
	    				setunVisibleCartes();
	    				afficherCartes(ControleurGenerale.tour.getDiscardCarteSansCourJoueur(),4);
	    				setVisibleCartes();
	    				}
	    		}
	    		
	    		if(Integer.parseInt(arg.toString())==30) {//JES
	    			this.finHunt=true;
	    			this.continuer();
	    		}
    	}
		
	}
}
