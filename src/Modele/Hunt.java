package Modele;

/**
 * 
// * <b>�num�ration definit une liste de l'effet hunt possible pour toutes les cartes</b>
 *<ul>
 *<li> JES : Jouez en suivant</li>
 *<li>RIA : R�v�lez l�identit� d�un autre joueur</li>
 *<li>CPJ : Choisissez le prochain joueur</li>
 *<li>RSI : Avant leur tour, regardez secr�tement son identit�</li>
 *<li>RVC : Reprenez une de vos propre carte rumeur r�v�l�e dans votre main</li>
 *<li>PCL : Avant leur tour, prenez une carte de leur main</li>
 *<li>RLIOD : Ils doivent r�v�ler leur identit� ou d�fausser une carte</li>
 *<li>RVI : R�v�lez votre identit�</li>
 *<li>AAP : le joueur doit accuser une autre personne que vous si possible</li>
 *<li>ADC : Ajoutez une carte de la d�fausse dans votre main </li>
 *<li>DVC : d�faussez cette carte</li>
 *<li>ACA : Ajoutez dans votre main une carte Rumeur r�v�l�e d�un autre joueur</li>
 *</ul>
 */
public enum Hunt {
RIA,CPJ,RSI,RVC,PCL,RLIOD,RVI,AAP,ADC,DVC,ACA,JES
}


