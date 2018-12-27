import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Param  {
    public static final int NB_BETTOR_MINI = 3;
    public static int NB_PLAYER;
    public static int NB_TV;
    public static int NB_MATCH;
    public static List<Player> PLAYERS = new ArrayList<>();

    public static Map<String, ImageIcon> iconTeam = new HashMap<>();

    public static Font fontTitlePanel = new Font("rockwell", Font.BOLD,18);
    public static Font fontBetsButton = new Font("rockwell", Font.BOLD,15);
    public static Font fontCalendar = new Font("MV Boli", Font.PLAIN,15);
//MV Boli

    //Initialise les équipes et leur blason
    public static void initIconTeam(){
        iconTeam.put("AC Milan", new ImageIcon("Blason Fifa/Little/AC Milan.png"));
        iconTeam.put("Allemagne", new ImageIcon("Blason Fifa/Little/Allemagne.png"));
        iconTeam.put("Angleterre", new ImageIcon("Blason Fifa/Little/Angleterre.png"));
        iconTeam.put("Argentine", new ImageIcon("Blason Fifa/Little/Argentine.png"));
        iconTeam.put("Arsenal", new ImageIcon("Blason Fifa/Little/Arsenal.png"));
        iconTeam.put("As Roma", new ImageIcon("Blason Fifa/Little/As Roma.png"));
        iconTeam.put("Atletico Madrid", new ImageIcon("Blason Fifa/Little/Atletico Madrid.png"));
        iconTeam.put("Barcelone", new ImageIcon("Blason Fifa/Little/Barcelone.png"));
        iconTeam.put("Bayern Munich", new ImageIcon("Blason Fifa/Little/Bayern Munich.png"));
        iconTeam.put("Belgique", new ImageIcon("Blason Fifa/Little/Belgique.png"));
        iconTeam.put("Borussia Dortmund", new ImageIcon("Blason Fifa/Little/Borussia Dortmund.png"));
        iconTeam.put("Bresil", new ImageIcon("Blason Fifa/Little/Bresil.png"));
        iconTeam.put("Chelsea", new ImageIcon("Blason Fifa/Little/Chelsea.png"));
        iconTeam.put("Espagne", new ImageIcon("Blason Fifa/Little/Espagne.png"));
        iconTeam.put("France", new ImageIcon("Blason Fifa/Little/France.png"));
        iconTeam.put("Inter Milan", new ImageIcon("Blason Fifa/Little/Inter Milan.png"));
        iconTeam.put("Italie", new ImageIcon("Blason Fifa/Little/Italie.png"));
        iconTeam.put("Juventus", new ImageIcon("Blason Fifa/Little/Juventus.png"));
        iconTeam.put("Liverpool", new ImageIcon("Blason Fifa/Little/Liverpool.png"));
        iconTeam.put("Lyon", new ImageIcon("Blason Fifa/Little/Lyon.png"));
        iconTeam.put("Manchester City", new ImageIcon("Blason Fifa/Little/Manchester City.png"));
        iconTeam.put("Manchester United", new ImageIcon("Blason Fifa/Little/Manchester United.png"));
        iconTeam.put("Naples", new ImageIcon("Blason Fifa/Little/Naples.png"));
        iconTeam.put("OM", new ImageIcon("Blason Fifa/Little/OM.png"));
        iconTeam.put("PSG", new ImageIcon("Blason Fifa/Little/PSG.png"));
        iconTeam.put("Real Madrid", new ImageIcon("Blason Fifa/Little/Real Madrid.png"));
        iconTeam.put("Seville", new ImageIcon("Blason Fifa/Little/Seville.png"));
        iconTeam.put("Tottenham", new ImageIcon("Blason Fifa/Little/Tottenham.png"));
    }

    //Retourne le player lié au number
    public static Player getPlayerByNumber(int number) {
        for (Player player : Param.PLAYERS) {
            if (number == player.getPlayerNumber()) return player;
        }
        return null;
    }

    //Retourne le player lié au nom
    public static Player getPlayerByName(String name) {
        for (Player player : Param.PLAYERS) {
            if (name == player.getName()) return player;
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

    //Associe chaque joueur avec un numéro de joueur
    public static void Lottery() {
        System.out.println();
        System.out.println("********** Début lottery **********");

        //Créer une liste de nombre de 1 à NombreDeJoueur
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= Param.PLAYERS.size(); i++) {
            numbers.add(i);
        }

        //Attribut un playerNumber à chaque joueur
        int n = 0;
        for (Player player : Param.PLAYERS) {
            // génération d'un entier >= 1 et < Nombre de Joueurs
            n = (int) (Math.random() * numbers.size());
            player.setPlayerNumber(numbers.get(n));
            numbers.remove(n);
        }

        //Affiche la liste des joueurs
        for (Player player : Param.PLAYERS) {
            player.display();
        }
        System.out.println();
        System.out.println("********** Fin lottery **********");
        System.out.println();
    }

    public static void a(){
        /*int numberWaiting;
        int numberHomeMatch;
        int numberAwayMatch;
        for (Player player : Param.PLAYERS) {
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());*/
    }
}