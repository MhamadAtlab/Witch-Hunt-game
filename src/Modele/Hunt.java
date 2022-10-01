package Modele;

/**
 * 
// * <b>énumération definit une liste de l'effet hunt possible pour toutes les cartes</b>
 *<ul>
 *<li> JES : Jouez en suivant</li>
 *<li>RIA : Révélez l’identité d’un autre joueur</li>
 *<li>CPJ : Choisissez le prochain joueur</li>
 *<li>RSI : Avant leur tour, regardez secrètement son identité</li>
 *<li>RVC : Reprenez une de vos propre carte rumeur révélée dans votre main</li>
 *<li>PCL : Avant leur tour, prenez une carte de leur main</li>
 *<li>RLIOD : Ils doivent révéler leur identité ou défausser une carte</li>
 *<li>RVI : Révélez votre identité</li>
 *<li>AAP : le joueur doit accuser une autre personne que vous si possible</li>
 *<li>ADC : Ajoutez une carte de la défausse dans votre main </li>
 *<li>DVC : défaussez cette carte</li>
 *<li>ACA : Ajoutez dans votre main une carte Rumeur révélée d’un autre joueur</li>
 *</ul>
 */
public enum Hunt {
RIA,CPJ,RSI,RVC,PCL,RLIOD,RVI,AAP,ADC,DVC,ACA,JES
}


