package Modele;

/**
 *  <b>SettingManage est la classe qui détermine le nombre max et min de joueurs et le score gagnant</b>
 * @author mhamad
 *
 */
public class SettingManage {
    private static int nbrJoueurMax=6;
    private static int nbrJoueurMin=3;

    private static int scoreGagne=5;

    public static int getNbrJoueurMax() {
    	return nbrJoueurMax;
    }
    public static int getNbrJoueurMin() {
    	return nbrJoueurMin;
    }

    public static int getScoreGagne() {
    	return scoreGagne;
    }
    public static void mettreAjour(int x, int y,int z) {
        nbrJoueurMax=y;
        scoreGagne=z;
        nbrJoueurMin=x;
    }

}
