package Modele;
/**
 * 
 * �num�ration definit une liste de l'effet witch possible pour toutes les cartes
 *<ul>
 *<li>JES : Jouez en suivant</li>
 *<li>DCV : D�faussez une carte de votre main</li>
 *<li>RVC : Reprenez une de vos propre carte rumeur r�v�l�e dans votre main</li>
 *<li>PCA : Prenez une carte de la main de celui qui vous a accus�</li>
 *<li>CPJ : Choisissez le prochain joueur</li>
 *<li>JAD : Le joueur qui vous a accus� d�fausse une carte al�atoire de sa main</li>
 *<li>AAP : le joueur doit accuser une autre personne que vous si possible</li>
 *</ul>
 */
public enum Witch {
	JES,DCV,RVC,PCA,CPJ,JAD,AAP
}