package Modele;

/**
 * 
 * <b>énumération definit une liste de condition possible pour toutes les cartes</b>
 *<ul>
 *<li>RAV : Jouable seulement si vous avez été révélé «Villager»</li>
 *<li>RCR : Jouable seulement si vous avez révélé une carte Rumeur</li>
 *<li>DC1 : Tant que vous êtes révélés, vous ne pouvez pas être choisi par Le « Angry Mob 1</li>
 *<li>DC7 : Tant que vous êtes révélés, vous ne pouvez pas être choisi par La « Ducking Stool 7</li>
 *<li>DCA : Pendant leur tour, le joueur doit accuser une autre personne que vous si possible</li>
 *<li>NON : nothing</li>
 *</ul>
 */
public enum Condition {
RAV,RCR,DC1,DC7,NON,DCA
}

