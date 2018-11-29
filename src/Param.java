import java.util.ArrayList;
import java.util.List;

public final class Param  {
    public static int NB_PLAYER;
    public static int NB_TV;
    public static int NB_MATCH;
    public static List<Player> PLAYERS = new ArrayList<>();

    //Retourne le player lié au number
    public static Player getPlayerByNumber(int number) {
        for (Player player : Param.PLAYERS) {
            if (number == player.getPlayerNumber()) return player;
        }
        return null;
    }

    //Retourne le player lié au number
    public static void playersDisplay() {
        System.out.println();
        System.out.println("********** PLAYERS **********");
        for (Player player : Param.PLAYERS) {
            player.display();
        }
        System.out.println();
        System.out.println("********** Fin PLAYERS **********");
    }
}