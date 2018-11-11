import java.util.*;

public class Main {

    public static void main(String[] args) {

        //System.out.println("Hello World!");

        /*** Test GameWindow ***/
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Binou","Juv"));
        players.add(new Player("Landou","Belgique"));
        players.add(new Player("Dadou","PSG"));
        players.add(new Player("Ronron","Barca"));
        players.add(new Player("Toto","France"));
        players.add(new Player("Geo","Man U"));

        GameWindow window = new GameWindow(players,2);

        /*** Test full APP ***/
        //FirstWindow window = new FirstWindow();
    }
}