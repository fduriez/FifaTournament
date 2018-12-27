import java.util.*;

public class Main {

    public static void main(String[] args) {

        /*** Test GameWindow ***/
        List<Player> players4 = new ArrayList<Player>();
        players4.add(new Player("Binou","Juv"));
        players4.add(new Player("Landou","Belgique"));
        players4.add(new Player("Dadou","PSG"));
        players4.add(new Player("Ronron","Barca"));

        List<Player> players5 = new ArrayList<Player>();
        players5.add(new Player("Binou","Juv"));
        players5.add(new Player("Landou","Belgique"));
        players5.add(new Player("Dadou","PSG"));
        players5.add(new Player("Ronron","Barca"));
        players5.add(new Player("Toto","France"));

        List<Player> players6 = new ArrayList<Player>();
        players6.add(new Player("Binou","Juv"));
        players6.add(new Player("Landou","Belgique"));
        players6.add(new Player("Dadou","PSG"));
        players6.add(new Player("Ronron","Barca"));
        players6.add(new Player("Toto","France"));
        players6.add(new Player("Geo","Man U"));

        List<Player> players7 = new ArrayList<Player>();
        players7.add(new Player("Binou","Juventus"));
        players7.add(new Player("Landou","Belgique"));
        players7.add(new Player("Dadou","PSG"));
        players7.add(new Player("Ronron","Barcelone"));
        players7.add(new Player("Toto","France"));
        players7.add(new Player("Geo","Manchester United"));
        players7.add(new Player("Fafa","Real Madrid"));

        List<Player> players8 = new ArrayList<Player>();
        players8.add(new Player("Binou","Juv"));
        players8.add(new Player("Landou","Belgique"));
        players8.add(new Player("Dadou","PSG"));
        players8.add(new Player("Ronron","Barca"));
        players8.add(new Player("Toto","France"));
        players8.add(new Player("Geo","Man U"));
        players8.add(new Player("Fafa","Real"));
        players8.add(new Player("Jacquou","TsoinTsoin"));

        List<Player> players9 = new ArrayList<Player>();
        players9.add(new Player("Binou","Juv"));
        players9.add(new Player("Landou","Belgique"));
        players9.add(new Player("Dadou","PSG"));
        players9.add(new Player("Ronron","Barca"));
        players9.add(new Player("Toto","France"));
        players9.add(new Player("Geo","Man U"));
        players9.add(new Player("Fafa","Real"));
        players9.add(new Player("Jacquou","TsoinTsoin"));
        players9.add(new Player("Michou","PouetPouet"));

        Param.initIconTeam();

        JsonSimple.loadData();

        Param.playersDisplay();
        Calendar.display();

        /*initParam(players7,2);
        GameWindow window = new GameWindow();*/

        /*** Test full APP ***/
        //FirstWindow window = new FirstWindow();
    }

    private static void initParam(List<Player> players,int nbTV){
        Param.NB_TV = nbTV;
        Param.NB_PLAYER = players.size();
        for(int i=Param.NB_PLAYER-1; i>=0; i--) {
            Param.NB_MATCH += i;
        }
        Param.NB_MATCH *= 2;
        Param.PLAYERS = new ArrayList<>(players);
    }
}