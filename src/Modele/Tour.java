package Modele;

import java.util.*;

//for notification
//JES-->10
/**
 * <b>Tour est la classe représentant une Tour de jeu.</b>
 * @author mhamad
 *
 */
public class Tour extends Observable {
	
    private ArrayList<Player> joueurs;
    /**
	 * le dernier joueur joue
	 */
    private Player derJoueur;
    /**
	 * le joueur actuel
	 */
    private Player courJoueur;
    /**
	 * le prochain joueur joue
	 */
    private Player proJoueur;
    /**
	 * la carte joue
	 */
    private Carte cartejoue;
    /**
	 * Liste des discard cartes
	 */
    public ArrayList<Carte> discardCartes;
    /**
	 * Liste des cartes jouées
	 */
    private ArrayList<Carte> cartesRumeur;
//    private ArrayList<Carte> main;
    
    public Carte getcartejoue() {
    	return this.cartejoue;
    }
    
    public void setcartejoue(Carte c) {
    	this.cartejoue=c;
    }
    
    public Player getcourJoueur() {
		return courJoueur;
	}
    
    public void setcourJoueur(Player courJoueur) {
		this.courJoueur = courJoueur;
	}
    
    public Player getderJoueur() {
		return derJoueur;
	}
    
    public void setderJoueur(Player derJoueur) {
		this.derJoueur = derJoueur;
	}
    
    public Player getproJoueur() {
		return proJoueur;
	}
    public void setproJoueur(Player proJoueur) {
		this.proJoueur = proJoueur;
	}
    
    public Tour(ArrayList<Player> arr,Player preJoueur) {
    	this.joueurs=arr;
        this.cartesRumeur=CartesRumeur.getCartes();
    	this.derJoueur=this.courJoueur=preJoueur;
    	this.discardCartes=new ArrayList<Carte>();
    }

    public ArrayList<Player> getJoueurs() {
		return this.joueurs;
	}
    
	public Player getDerJoueur() {
		return derJoueur;
	}
	/**
	 * méthode pour savoir si la est terminée
	 * @return
	 */
    public boolean estTerminee() {
    	if(this.getJoueursActives().size()==1) {
    		this.cummuleScoretour();
    		return true;
    	}
    	else return false;
    }
    /**
     * methode pour calculer le score 
     */
    public void cummuleScoretour() {
    	Player p=this.getJoueursActives().get(0);
    	System.out.println("Il reste seulement le joueur _"+p.getusername()+"_ -->la tour est terminée d'indentit --> "+p.getIdentite());
    	if(p.getIdentite()==Identite.Witch) p.setScore(2);
    	else p.setScore(1);
    }
    /**
     * methode pour distribuer les cartes
     */
    public void distribuerCartes() {
    	ArrayList<Player> arr=this.joueurs;
    	Collections.shuffle(this.cartesRumeur);
    	Iterator<Carte> It=this.cartesRumeur.iterator();
    	int n=this.cartesRumeur.size()/arr.size();
    	int i,j;
    	for (i=0;i<n;i++) {
    		for(j=0;j<arr.size();j++) {
    			arr.get(j).ajouterCarte(It.next());
    		}
    	}
    	while(It.hasNext()) {
    		this.ajouterDiscardCarte(It.next());
    	}
    	
    }
    /**
     * methode pour ajouter discard carte
     */
    public void ajouterDiscardCarte(Carte c) {
    	this.discardCartes.add(c);
    	Collections.shuffle(this.cartesRumeur);
    }
    public ArrayList<Carte> getDiscardCartes() {
    	return this.discardCartes;
    }
    public boolean DiscardCarteIsVide() {
    	return this.discardCartes.isEmpty();
    }
    public Carte releveDiscardCarte() {
    	Carte c=this.discardCartes.get(0);
    	this.discardCartes.remove(0);
    	return c;
    }
    public void choisirIdentite() {

		System.out.println("-----------------------------------------------------------------");
		System.out.println("----------------------choisir identite----------------------------");
    	int i;
    	for(i=0;i<this.joueurs.size();i++) {
    		this.joueurs.get(i).choisirIdentite();
    	}

		System.out.println("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------\n");
    }

    /**
     * methode pour commencer la tour
     */
    public void jouer(){
    	
    		
    		if(this.courJoueur.isHorTour()) {
    			if(this.courJoueur.isHorTour()&&this.derJoueur.isHorTour()) {
        			int n=Player.random(this.getJoueursActives().size());
        			this.courJoueur=this.getJoueursActives().get(n);
        		}
    			else {
    				this.derJoueur=this.courJoueur;
    				this.courJoueur=this.proJoueur;
    				
    			}
			}
    		System.out.println("------------ "+this.courJoueur.getusername()+" -----------------");
    	
    }
    
    /**
     * methode pour le joueur (input) accuser autre joueur
     */
    private void accuser(Player p) {
    	int y;
    	this.proJoueur=p;
    	System.out.println("--------------------------------------------------");
    	System.out.println("\n");
    	System.out.println("joueur _"+ this.courJoueur.getusername()+"_ a accuse le joueur _"+this.proJoueur.getusername());
    	System.out.println("--------------------------------------------------\n");
    	
    	
    	if(this.proJoueur.isBot()) {
    		if(this.proJoueur.getmain().size()!=0) {
	    		y=Player.random(2);
				if(y==0) {
					this.releveIdentite();
				}
				else {
					System.out.println("Le joueur _"+this.proJoueur.getusername()+"_ utlise l'effet witch !");
			    	System.out.println("--------------------------------------------------\n");
					this.derJoueur=this.courJoueur;
					this.courJoueur=this.proJoueur;
				}
    		}
    		else this.releveIdentite();
		}
		else {
			if(this.proJoueur.getmain().size()!=0) {
				Scanner kbhit = new Scanner(System.in);
		    	int str;
		    	boolean onContinue = true;
				System.out.println("----------------------- _"+this.proJoueur.getusername()+"_ ---------\n");
		    	while(onContinue) {
	    			System.out.println("Pour relever votre identite, tapez 0");
	    			System.out.println("Pour relever un carte rumeur et utiliser effet Wicth, tapez 1\n");
		            str = Integer.parseInt( kbhit.next());
	    			if(str==0) {
	    				this.releveIdentite();
	    				onContinue=false;
	    			}
	    			else 	if(str==1) {
			    				System.out.println("Le joueur _"+this.proJoueur.getusername()+"_ utlise l'effet witch !");
			    		    	System.out.println("--------------------------------------------------\n");
			    				this.derJoueur=this.courJoueur;
			    				this.courJoueur=this.proJoueur;
			    				onContinue=false;
	    					}
	    				else onContinue=true;
    			}
			}
			else this.releveIdentite();
		}
    }
    /**
     * methode pour l'effet hunt 
     */
    public void hunt(Hunt h) {

		System.out.println("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------\n");
		
    			switch(h) {
    			
    			  case RIA:

    				  
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Révélez l’identité d’un autre joueur\n");
    				  this.setChanged();
    				  this.notifyObservers(20);
    			    break;
    			    
    			  case CPJ:
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Choisissez le prochain joueur\n");
    				  this.setChanged();
    				  this.notifyObservers(21);
    			    break;
    			    
    			  case RSI:
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("L'identite de joueur _"+this.courJoueur.getusername()+"_ est --> "+this.courJoueur.getIdentite());
    				  this.setChanged();
    				  this.notifyObservers(22);
      			    break;
      			    
    			  case RVC:
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Reprenez une de vos propre carte rumeur rélevée dans votre main\n");
      			      ArrayList<Carte> arr=this.courJoueur.getCartesRelever();
      			      if (arr.size()<1) {
      			    	System.out.println("Pas des cartes !!!!!!!!!!\n");
      			      }
          			  this.setChanged();
    				  this.notifyObservers(23);
      			    break;
      			    
    			  case PCL:
    				 
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Avant leur tour, prenez une carte de leur main\n");
    				  ArrayList<Carte> arr1=this.courJoueur.getmain();
      			      if (arr1.isEmpty()) {
      			    	System.out.println("Pas des cartes !!!!!!!!!!\n");
      			      }
      			      else {
      			    	  int rand=Player.random(arr1.size());
      			    	  this.derJoueur.ajouterCarte(this.courJoueur.getmain().get(rand));
            			  System.out.println("Le joueur _"+this.derJoueur.getusername()+"_ a pris la carte "+this.courJoueur.getmain().get(rand).getNom());
      			    	  this.courJoueur.getmain().remove(rand);
      			      }
      			      
      			    this.setChanged();
  				  this.notifyObservers(24);
      			    // code block
      			    break;
      			    
      			  
      			    
    			  case RLIOD:
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Ils doivent révéler leur identité ou défausser une carte\n");
    				  System.out.println("------------ "+this.courJoueur.getusername()+" -----------------");


    				  this.setChanged();
    				  this.notifyObservers(25);
      			    break;
      			    
    			  case RVI:
//    				  this.setChanged();
//    				  this.notifyObservers(26);
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Révélez votre identité\n");
    				  System.out.println("L'identite de "+this.courJoueur.getusername()+" est --> "+this.courJoueur.getIdentite());
    				    if(this.courJoueur.getIdentite()==Identite.Villager) {
    				    	System.out.println("Choisissez le prochain joueur\n");
//    	    				  this.proJoueur=this.courJoueur.accuser(this.getJoueursActivesSansP(this.courJoueur)) ;
//    	    				  System.out.println(this.courJoueur.getusername()+" est choisit le joueur "+this.proJoueur.getusername());
//    	    				  this.derJoueur=this.courJoueur;
//    	    				  this.courJoueur=this.proJoueur;
    				    }
    					  else {
    						  ArrayList<Player> arr_p= this.getJoueursActives();
    						  int i=arr_p.indexOf(this.courJoueur);
    	    				  this.derJoueur=this.courJoueur;
    						  if (i!=0) this.courJoueur=arr_p.get(i-1);
    						  else this.courJoueur=arr_p.get(arr_p.size()-1);
    						}
    				    this.setChanged();
      				  this.notifyObservers(26);
        			    break;
        			    
    			  case AAP:
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("le joueur doit accuser une autre personne que vous si possible\n");
    				  Player pp;
    				  if(!this.getJoueursActivesI_C_SansP1P2(this.derJoueur,this.courJoueur).isEmpty()) {
	    					  pp=this.courJoueur.accuser(this.getJoueursActivesI_C_SansP1P2(this.derJoueur,this.courJoueur));
	    				  this.accuser(pp);
    				  }
    				  else {
    					  if(!this.getJoueursActivesI_C_SansP(this.courJoueur).isEmpty()) {
    						  pp=this.courJoueur.accuser(this.getJoueursActivesSansP(this.courJoueur));
    	    				  this.accuser(pp);
    					  }
    					  else System.out.println("Pas de joueur vous pouvez accuser !!!!");
    				  }
        			    break;
        			    
    			  case ADC:
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Ajoutez une carte de la défausse dans votre main \n");
    				  if(this.discardCartes.isEmpty())  System.out.println("Pas des cartes !!!!!!!!");
//    				  else {
//		    				  Carte c=this.courJoueur.reprenerCarte(this.discardCartes);
//		    				  System.out.println("Le joueur _"+this.courJoueur.getusername()+"_ a ajoute la carte "+c.getNom());
//	      			    	  this.discardCartes.remove(c);
//	      			    	  this.courJoueur.ajouterCarte(c);
//    				  }
    				  this.setChanged();
    				  this.notifyObservers(27);
        			    break;
        			    
    			  case DVC:
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("défaussez un carte \n");
    				  if(this.courJoueur.getmain().isEmpty()) System.out.println("Pas des cartes!!!!!!");
    				  this.setChanged();
    				  this.notifyObservers(28);
        			    break;
        			    
    			  case ACA:
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Ajoutez dans votre main une carte Rumeur révélée d’un autre joueur \n");
    				  ArrayList<Carte> arr_c=getDiscardCarteSansCourJoueur();
    				  if(arr_c.isEmpty()) System.out.println("Pas des catres !!!!!!!!!!!!");
    				  this.setChanged();
    				  this.notifyObservers(29);
        			    break;
        			    
    			  case JES:
    				  this.setChanged();
    				  this.notifyObservers(30);
        			    // code block
        			    break;
    			  default:
    			    // code block
    			}
    		System.out.println("-----------------------------------------------------------------");
    		System.out.println("-----------------------------------------------------------------\n");
    }
    
    /**
     * methode pour l'effet witch 
     */
    public void witch(Witch w) {
    	System.out.println("-----------------------------------------------------------------");
		System.out.println("-----------------------------------------------------------------\n");
		
    			switch(w) {
    			
    			  case JES:
    				  this.setChanged();
    				  this.notifyObservers(10);
    				  
    			    break;
    			    
    			  case DCV:
    				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("défaussez un carte \n");
    				  if(this.courJoueur.getmain().isEmpty()) System.out.println("Pas des cartes!!!!!!");

    				  this.setChanged();
    				  this.notifyObservers(11);
    				  
      			    break;
      			  
    			  case RVC:
    				  
      				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Reprenez une de vos propre carte rumeur rélevée dans votre main\n");
    				  
      			      ArrayList<Carte> arr=this.courJoueur.getCartesRelever();
      			      if (arr.size()<1) {
      			    	System.out.println("Pas des cartes !!!!!!!!!!\n");
      			      }
      			      else {
    				  }
    				  this.setChanged();
      				  this.notifyObservers(12);
      			   
      			    break;
      			  
    			  case PCA:

      				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Prenez une carte de la main de celui qui vous a accusé\n");
    				  
    				  ArrayList<Carte> arr1=this.derJoueur.getmain();
      			      if (arr1.isEmpty()) {
      			    	System.out.println("Pas des cartes !!!!!!!!!!\n");
      			      }
      			      else {
      			    	  int rand=Player.random(arr1.size());
      			    	  this.courJoueur.ajouterCarte(arr1.get(rand));
            			  System.out.println("Le joueur _"+this.courJoueur.getusername()+"_ a pris la carte "+arr1.get(rand).getNom());
      			    	  this.derJoueur.getmain().remove(rand);
    				  }

    				  this.setChanged();
      				  this.notifyObservers(13);
      			    break;
      			  
    			  case CPJ:

      				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Choisissez le prochain joueur\n");
    				  

    				  this.setChanged();
      				  this.notifyObservers(14);
      			    break;
      			  
    			  case JAD:

      				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("Le joueur qui vous a accusé défausse une carte aléatoire de sa main \n");
    				  
    				  if(this.derJoueur.getmain().isEmpty()) System.out.println("Pas des cartes!!!!!!");
    				  else {
	    				  Carte cc=this.derJoueur.getmain().get(Player.random(this.derJoueur.getmain().size()));
	    				  this.discardCartes.add(cc);
	      				  this.derJoueur.getmain().remove(cc);
	      				  System.out.println("Le joueur _"+this.derJoueur.getusername()+"_ a deffausse la carte "+cc.getNom());
    				  }
    				  this.setChanged();
      				  this.notifyObservers(15);
    				  
      			    // code block
      			    break;
      			  
    			  case AAP:

      				  
    				  System.out.println("-----------------------------------------------------------------\n");
    				  System.out.println("le joueur doit accuser une autre personne que vous si possible\n");

    				  this.setChanged();
      				  this.notifyObservers(16);
      			    // code block
      			    break;
      			    
  			  default:
  			    // code block
  			}
  		System.out.println("-----------------------------------------------------------------");
  		System.out.println("-----------------------------------------------------------------\n");
	}
    /**
     * methode pour relever identite de joueur actuel
     */
    public void releveIdentite() {
    	System.out.println("Le joueur _"+this.proJoueur.getusername()+"_ est releve sa identite --> "+this.proJoueur.getIdentite());
		if(this.proJoueur.getIdentite()==Identite.Villager) {
			this.derJoueur=this.courJoueur;
			this.courJoueur=this.proJoueur;
		}
		else {
			this.proJoueur.setHorTour(true);
			System.out.println(this.proJoueur.getusername()+" hors tour !");
			this.courJoueur.setScore(1);
			System.out.println("\n Le joueur _"+this.courJoueur.getusername()+"_ : +1" );
			}

		System.out.println("------------------------------------------------");
		System.out.println("------------------------------------------------\n");
		
    }
    
    /**
     * méthode pour choisir une carte à jouer
     */
    public boolean choisirCarte() {
    	
    	if(this.courJoueur.getmain().isEmpty()) {
			this.courJoueur.setHorTour(true);
			System.out.println(this.courJoueur.getusername()+" hors tour !");
			this.proJoueur=this.courJoueur;
			this.courJoueur=this.derJoueur;
			this.derJoueur=this.proJoueur;
			return false;
		}
		else {
			this.cartejoue= this.courJoueur.choisirCarte(this.courJoueur);
			return true;
		}
    }
    
    /**
     * methode donne la liste des joueurs qui n'ont pas encore perdu dans le tour en cours
     * @return
     */
    public ArrayList<Player> getJoueursActives(){
    	ArrayList<Player> arr=new ArrayList<Player>();
    	for(int i=0;i<this.joueurs.size();i++)
    		if(!this.joueurs.get(i).isHorTour()) arr.add(this.joueurs.get(i));
    	return arr;
    }
    /**
     * methode donne la liste des joueurs (sans le joueur p input) qui n'ont pas encore perdu dans le tour en cours
     * @return
     */
    public ArrayList<Player> getJoueursActivesSansP(Player p){
    	ArrayList<Player> arr=getJoueursActives();
    	ArrayList<Player> arrSansP=new ArrayList<Player>();
    	for(int i=0;i<arr.size();i++)
    		if(!arr.get(i).isHorTour()&&(arr.get(i)!=p)) arrSansP.add(arr.get(i));
    	return arrSansP;
    }
    
    /**
     * methode donne la liste des joueurs (sans le joueur p input) qui n'ont pas encore relevé leur identité
     * @return
     */
    public ArrayList<Player> getJoueursActivesI_C_SansP(Player p){
    	ArrayList<Player> arr=getJoueursActives();
    	ArrayList<Player> arrSansP=new ArrayList<Player>();
    	for(int i=0;i<arr.size();i++)
    		if(!arr.get(i).isHorTour()&&(arr.get(i)!=p)&&!arr.get(i).getreleverIdentite()) arrSansP.add(arr.get(i));
    	return arrSansP;
    }
    /**
     * methode donne la liste des joueurs (sans les joueurs p1 et p2) qui n'ont pas encore relevé leur identité
     * @return
     */
    public ArrayList<Player> getJoueursActivesI_C_SansP1P2(Player p1, Player p2){
    	ArrayList<Player> arr=getJoueursActives();
    	ArrayList<Player> arrSansP=new ArrayList<Player>();
    	for(int i=0;i<arr.size();i++)
    		if(!arr.get(i).isHorTour()&&(arr.get(i)!=p1)&&(arr.get(i)!=p2)&&!arr.get(i).getreleverIdentite()) arrSansP.add(arr.get(i));
    	return arrSansP;
    }
    /**
	 * methode pour initialiser tous les joueurs (après chaque tour)
	 */
    public void initialisJoueurs() {
    	for(int i=0;i<this.joueurs.size();i++) this.joueurs.get(i).initialis();
    }
    /**
     * méthode donne la liste des joueurs 
     */
    public String getJoueurString(ArrayList<Player> arr) {
    	int z=arr.size();
    	int i=0;
    	String s="<html>";
    	while( i<z){
    		s += arr.get(i).getId()+" - "+ arr.get(i).getusername()+"<br>";
    		i++;
    		}
    	s +="</html>";
    	return s;
    }
    /**
     * méthode donne la liste des id du joueurs 
     */
    public String[] getJoueursId(ArrayList<Player> arr) {
    	int z=arr.size();
    	String[] s = new String[z];
    	int i=0;
    	while( i<z){
    		s[i]=Integer.toString( arr.get(i).getId());
    		i++;
    	}
    	return s;
    }
    public ArrayList<Carte> getDiscardCarteSansCourJoueur(){
    	ArrayList<Carte> arr_c=new ArrayList<Carte>();
		  for(int i=0;i<this.joueurs.size();i++) {
			  if((this.joueurs.get(i)!=this.courJoueur)&&(!this.joueurs.get(i).getCartesRelever().isEmpty())){
				  for(int j=0;j<this.joueurs.get(i).getCartesRelever().size();j++) {
					  if(!arr_c.contains(this.joueurs.get(i).getCartesRelever().get(j))) {
						  arr_c.add(this.joueurs.get(i).getCartesRelever().get(j));
					  }
				  }
			  }
		  }
		  return arr_c;
    }
    /**
     * méthode donne les carte jouables en effet hunt
     */
    public ArrayList<Carte>  cartesJouableHunt(Player p) {
    	ArrayList<Carte> res=new ArrayList<Carte>();
    	ArrayList<Carte> arr=p.getmain();
    	for(int i=0;i<arr.size();i++) {
			  if(arr.get(i).isJouableHunt(p)){
				  res.add(arr.get(i));
			  }
		  }
    	return arr;
    }
    /**
     * méthode donne les carte jouables en effet witch
     */
    public ArrayList<Carte>  cartesJouableWitch(Player p) {
    	ArrayList<Carte> res=new ArrayList<Carte>();
    	ArrayList<Carte> arr=p.getmain();
    	for(int i=0;i<arr.size();i++) {
			  if(arr.get(i).isJouableWitch(p)){
				  res.add(arr.get(i));
			  }
		  }
    	return arr;
    }
}
