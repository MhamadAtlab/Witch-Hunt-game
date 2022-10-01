package Modele;

/**
 * 
 * <b>�num�ration definit une liste de condition possible pour toutes les cartes</b>
 *<ul>
 *<li>RAV : Jouable seulement si vous avez �t� r�v�l� �Villager�</li>
 *<li>RCR : Jouable seulement si vous avez r�v�l� une carte Rumeur</li>
 *<li>DC1 : Tant que vous �tes r�v�l�s, vous ne pouvez pas �tre choisi par Le � Angry Mob 1</li>
 *<li>DC7 : Tant que vous �tes r�v�l�s, vous ne pouvez pas �tre choisi par La � Ducking Stool 7</li>
 *<li>DCA : Pendant leur tour, le joueur doit accuser une autre personne que vous si possible</li>
 *<li>NON : nothing</li>
 *</ul>
 */
public enum Condition {
RAV,RCR,DC1,DC7,NON,DCA
}

