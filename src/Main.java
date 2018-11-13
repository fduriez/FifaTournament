import java.util.*;

public class Main {

    public static void main(String[] args) {

        //System.out.println("Hello World!");

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
        players7.add(new Player("Binou","Juv"));
        players7.add(new Player("Landou","Belgique"));
        players7.add(new Player("Dadou","PSG"));
        players7.add(new Player("Ronron","Barca"));
        players7.add(new Player("Toto","France"));
        players7.add(new Player("Geo","Man U"));
        players7.add(new Player("Fafa","Real"));

        List<Player> players8 = new ArrayList<Player>();
        players8.add(new Player("Binou","Juv"));
        players8.add(new Player("Landou","Belgique"));
        players8.add(new Player("Dadou","PSG"));
        players8.add(new Player("Ronron","Barca"));
        players8.add(new Player("Toto","France"));
        players8.add(new Player("Geo","Man U"));
        players8.add(new Player("Fafa","Real"));
        players8.add(new Player("Jacquou","TsoinTsoin"));

        GameWindow window = new GameWindow(players8,4);

        /*** Test full APP ***/
        //FirstWindow window = new FirstWindow();
    }
}