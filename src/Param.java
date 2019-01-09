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

    //Font
    public static Font fontTitlePanel = new Font("rockwell", Font.BOLD,25);
    public static Font fontBetsButton = new Font("rockwell", Font.BOLD,20);
    public static Font fontTitleCalendar = new Font("MV Boli", Font.BOLD,23);
    public static Font fontCalendar = new Font("MV Boli", Font.PLAIN,20);
    public static Font fontPariCalendar = new Font("MV Boli", Font.PLAIN,16);
    public static Font fontButtonCalendar = new Font("rockwell", Font.BOLD,23);
    public static Font fontRanking = new Font("MV Boli", Font.PLAIN,20);
    public static Font fontTitleRanking = new Font("MV Boli", Font.BOLD,23);
    //public static Font fontDisableItem = new Font("MV Boli", Font.PLAIN,23);

    public static final String littleBlasonPath = "images/Blason/Little/";
    public static final String greatBlasonPath = "images/Blason/Great/";
    public static final String fondPath = "images/Fond/";
    public static List<ImageIcon> fondList = new ArrayList<>();

    public static Color backgroundVictoryBet = new Color(0,180,0);

    //Initialise les équipes et leur blason
    public static void initIconTeam(){
        iconTeam.put("AC Milan", new ImageIcon( littleBlasonPath + "AC Milan.png"));
        iconTeam.put("Allemagne", new ImageIcon(littleBlasonPath + "Allemagne.png"));
        iconTeam.put("Angleterre", new ImageIcon(littleBlasonPath + "Angleterre.png"));
        iconTeam.put("Argentine", new ImageIcon(littleBlasonPath + "Argentine.png"));
        iconTeam.put("Arsenal", new ImageIcon(littleBlasonPath + "Arsenal.png"));
        iconTeam.put("As Roma", new ImageIcon(littleBlasonPath + "As Roma.png"));
        iconTeam.put("Atletico Madrid", new ImageIcon(littleBlasonPath + "Atletico Madrid.png"));
        iconTeam.put("Barcelone", new ImageIcon(littleBlasonPath + "Barcelone.png"));
        iconTeam.put("Bayern Munich", new ImageIcon(littleBlasonPath + "Bayern Munich.png"));
        iconTeam.put("Belgique", new ImageIcon(littleBlasonPath + "Belgique.png"));
        iconTeam.put("Borussia Dortmund", new ImageIcon(littleBlasonPath + "Borussia Dortmund.png"));
        iconTeam.put("Bresil", new ImageIcon(littleBlasonPath + "Bresil.png"));
        iconTeam.put("Chelsea", new ImageIcon(littleBlasonPath + "Chelsea.png"));
        iconTeam.put("Espagne", new ImageIcon(littleBlasonPath + "Espagne.png"));
        iconTeam.put("France", new ImageIcon(littleBlasonPath + "France.png"));
        iconTeam.put("Inter Milan", new ImageIcon(littleBlasonPath + "Inter Milan.png"));
        iconTeam.put("Italie", new ImageIcon(littleBlasonPath + "Italie.png"));
        iconTeam.put("Juventus", new ImageIcon(littleBlasonPath + "Juventus.png"));
        iconTeam.put("Liverpool", new ImageIcon(littleBlasonPath + "Liverpool.png"));
        iconTeam.put("Lyon", new ImageIcon(littleBlasonPath + "Lyon.png"));
        iconTeam.put("Manchester City", new ImageIcon(littleBlasonPath + "Manchester City.png"));
        iconTeam.put("Manchester United", new ImageIcon(littleBlasonPath + "Manchester United.png"));
        iconTeam.put("Naples", new ImageIcon(littleBlasonPath + "Naples.png"));
        iconTeam.put("OM", new ImageIcon(littleBlasonPath + "OM.png"));
        iconTeam.put("PSG", new ImageIcon(littleBlasonPath + "PSG.png"));
        iconTeam.put("Real Madrid", new ImageIcon(littleBlasonPath + "Real Madrid.png"));
        iconTeam.put("Seville", new ImageIcon(littleBlasonPath + "Seville.png"));
        iconTeam.put("Tottenham", new ImageIcon(littleBlasonPath + "Tottenham.png"));
    }

    //Initialise les images de fond
    public static void initFondList(){
        fondList.add(new ImageIcon(Param.fondPath + "championDuMonde.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "champsElysee.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "FIFA19.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "fondLDC.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "fondLDC2.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "fondLDC3.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "fondLDC4.jpg"));
        fondList.add(new ImageIcon(Param.fondPath + "fondLDC5.jpg"));
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

    public static void controlCalendar(){
        /*int numberWaiting;
        int numberHomeMatch;
        int numberAwayMatch;
        for (Player player : Param.PLAYERS) {
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());*/
    }
}