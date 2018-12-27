import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.toIntExact;

public class JsonSimple {

    JsonSimple() {

    }

    /***************/
    /** SAVE DATA **/
    /***************/

    public static void saveData(){
        savePlayersData();
        saveCalendarData();
    }

    //Sauvegarde les données des joueurs dans un JSON
    private static void savePlayersData(){
        JSONObject fileObject = new JSONObject();
        JSONArray list = new JSONArray();
        for(Player player : Param.PLAYERS){
            JSONObject playerObject = new JSONObject();
            playerObject.put("PlayerNumber", player.getPlayerNumber());
            playerObject.put("Name", player.getName());
            playerObject.put("Team", player.getTeam());

            playerObject.put("BetPoints", player.getBetsPoints());

            playerObject.put("GoalsScored", player.getGoalsScored());
            playerObject.put("GoalsTaken", player.getGoalsTaken());

            playerObject.put("NumberVictory", player.getNumberVictory());
            playerObject.put("NumberDraw", player.getNumberDraw());
            playerObject.put("NumberDefeat", player.getNumberDefeat());

            list.add(playerObject);
        }

        fileObject.put("Players",list);

        //Ajout data to JSON
        try (FileWriter file = new FileWriter("players.json")) {

            file.write(fileObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("save players : " + fileObject);

    }

    //Sauvegarde les données du calendrier dans un JSON
    private static void saveCalendarData(){
        JSONObject fileObject = new JSONObject();
        JSONArray list = new JSONArray();

        for(Week week : Calendar.weeks) {
            JSONObject weekObject = new JSONObject();
            weekObject.put("WeekNumber",week.getWeekNumber());

            JSONArray matchsObject = new JSONArray();
            for(Match match : week.getMatchs()){
                matchsObject.add(matchToJSONObject(match));
            }
            weekObject.put("Matchs",matchsObject);

            JSONArray waitingPlayerObject = new JSONArray();
            for(Player player : week.getWaitingPlayer()){
                waitingPlayerObject.add(player.getPlayerNumber());
            }
            weekObject.put("WaitingPlayer",waitingPlayerObject);

            list.add(weekObject);
        }

        fileObject.put("Weeks",list);

        //Ajout data to JSON
        try (FileWriter file = new FileWriter("calendar.json")) {

            file.write(fileObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

            System.out.println("save Calendar  : " + fileObject);
    }

    //Retourne un match sous la forme d'un objet JSON
    private static JSONObject matchToJSONObject(Match match){
        JSONObject matchObject = new JSONObject();
        matchObject.put("HomePlayer",match.getHomePlayer().getPlayerNumber());
        matchObject.put("VisitorPlayer",match.getVisitorPlayer().getPlayerNumber());
        matchObject.put("Result",match.getResult());
        matchObject.put("AlreadyPlayed",match.isAlreadyPlayed());
        matchObject.put("BetPoints",match.getBetPoints());

        JSONArray betWinnersObject = new JSONArray();
        for(Player player : match.getBetWinners()){
            betWinnersObject.add(player.getPlayerNumber());
        }
        matchObject.put("BetWinners",betWinnersObject);

        JSONArray betsObject = new JSONArray();
        for(Bet bet : match.getBets()){
            betsObject.add(betToJSONObject(bet));
        }
        matchObject.put("Bets",betsObject);

        return matchObject;
    }

    //Retourne un pari sous la forme d'un objet JSON
    private static JSONObject betToJSONObject(Bet bet){
        JSONObject betObject = new JSONObject();
        betObject.put("Player",bet.getPlayer().getPlayerNumber());
        betObject.put("Score",bet.getScore());
        betObject.put("HomeScore",bet.getHomeScore());
        betObject.put("VisitorScore",bet.getVisitorScore());
        return betObject;
    }

    /***************/
    /** LOAD DATA **/
    /***************/

    public static void loadData(){
        loadPlayersData();
        loadCalendarData();
    }

    //Récupère les données des joueurs dans le JSON
    public static void loadPlayersData(){
        JSONParser parser = new JSONParser();
        try {
            Object file = parser.parse(new FileReader("players.json"));
            JSONObject jsonObject = (JSONObject) file;
            JSONArray playersListObject = (JSONArray) jsonObject.get("players");

            for(Object object : playersListObject) {
                JSONObject playerObject = (JSONObject) object;
                Player player = new Player(playerObject.get("Name").toString(), playerObject.get("Team").toString());

                player.setPlayerNumber(toIntExact((long) playerObject.get("PlayerNumber")));
                player.setBetsPoints((float) (double) playerObject.get("BetPoints"));
                player.setGoalsScored(toIntExact((long) playerObject.get("GoalsScored")));
                player.setGoalsTaken(toIntExact((long) playerObject.get("GoalsTaken")));
                player.setNumberVictory(toIntExact((long) playerObject.get("NumberVictory")));
                player.setNumberDraw(toIntExact((long) playerObject.get("NumberDraw")));
                player.setNumberDefeat(toIntExact((long) playerObject.get("NumberDefeat")));

                Param.PLAYERS.add(player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Récupère les données du calendrier dans le JSON
    public static void loadCalendarData(){
        JSONParser parser = new JSONParser();
        try {
            Object file = parser.parse(new FileReader("calendar.json"));
            JSONObject fileObject = (JSONObject) file;
            JSONArray weeksListObject = (JSONArray) fileObject.get("Weeks");

            for(Object object : weeksListObject) {
                JSONObject weekObject = (JSONObject) object;
                Week week = new Week();
                week.setWeekNumber((int) (long)weekObject.get("WeekNumber"));

                JSONArray matchsListObject = (JSONArray) weekObject.get("Matchs");
                List<Match> matchs = new ArrayList<>();
                for(Object matchObject : matchsListObject){
                    matchs.add(jsonObjectToMatch((JSONObject) matchObject));
                }
                week.setMatchs(matchs);

                JSONArray waitingPlayerListObject = (JSONArray) weekObject.get("WaitingPlayer");
                List<Player> waitingPlayers = new ArrayList<>();
                for(Object playerObject : waitingPlayerListObject){
                    int playerNumber = (int) (long)playerObject;
                    waitingPlayers.add(Param.getPlayerByNumber(playerNumber));
                }
                week.setWaitingPlayer(waitingPlayers);

                Calendar.weeks.add(week);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Retourne un Match à partir des données JSON
    private static Match jsonObjectToMatch(JSONObject matchObject){
        Match match = new Match();
        match.setHomePlayer(Param.getPlayerByNumber((int) (long)matchObject.get("HomePlayer")));
        match.setVisitorPlayer(Param.getPlayerByNumber((int) (long)matchObject.get("VisitorPlayer")));

        //Paris sur le match
        JSONArray betListObject = (JSONArray) matchObject.get("Bets");
        List<Bet> bets = new ArrayList<>();
        for(Object betObject : betListObject){
            bets.add(jsonObjectToBet((JSONObject) betObject));
        }
        match.setBets(bets);

        //Si match joué
        boolean alreadyPlayed = (boolean) matchObject.get("AlreadyPlayed");
        if(alreadyPlayed){
            match.setResult(matchObject.get("Result").toString());
            match.setBetPoints((float) (double)matchObject.get("BetPoints"));

            //Vainqueurs du pari sur le match
            JSONArray betWinnersListObject = (JSONArray) matchObject.get("BetWinners");
            List<Player> betWinners = new ArrayList<>();
            for(Object betWinnerObject : betWinnersListObject){
                betWinners.add(Param.getPlayerByNumber((int) (long)betWinnerObject));
            }
            match.setBetWinners(betWinners);
        }

        return match;
    }

    //Retourne un Pari à partir des données JSON
    private static Bet jsonObjectToBet(JSONObject betObject){
        Bet bet = new Bet();
        bet.setScore(betObject.get("Score").toString());
        bet.setPlayer(Param.getPlayerByNumber((int) (long)betObject.get("Player")));
        return bet;
    }
}
