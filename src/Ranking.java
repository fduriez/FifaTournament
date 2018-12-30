import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public abstract class Ranking {
    private static List<Player> generalRanking = new ArrayList<>();
    private static List<Player> matchRanking = new ArrayList<>();
    private static List<Pair> betRanking = new ArrayList<>();

    public static void initRanking(){
        for(Player player : Param.PLAYERS){
            generalRanking.add(player);
            matchRanking.add(player);
        }
    }

    public static void updateRanking(){
        int index = 0;

        //MAJ classement des matchs
        matchRanking = new ArrayList<>();
        for(Player player : Param.PLAYERS){
            index = 0;
            if(matchRanking.isEmpty()) matchRanking.add(player);
            else{
                for(Player player2 : matchRanking){
                    // 1- Nombre de points
                    if(player2.getMatchPoints() > player.getMatchPoints()) index++;
                    // 2 - Différence de buts
                    if((player2.getMatchPoints() == player.getMatchPoints()) && (player2.getGoalDifference() > player.getGoalDifference())) index++;
                    // 3 - Nombre de buts marqués
                    if((player2.getMatchPoints() == player.getMatchPoints()) && (player2.getGoalDifference() == player.getGoalDifference()) && (player2.getGoalsScored() > player.getGoalsScored())) index++;
                }
                matchRanking.add(index,player);
            }
        }

        //MAJ classement général
        generalRanking = new ArrayList<>();
        for(Player player : Param.PLAYERS){
            index = 0;
            if(generalRanking.isEmpty()) generalRanking.add(player);
            else{
                for(Player player2 : generalRanking){
                    // 1- Nombre de points Total
                    if(player2.getTotalPoints() > player.getTotalPoints()) index++;
                    // 2 - Classement en match
                    if((player2.getTotalPoints() == player.getTotalPoints()) && (matchRanking.indexOf(player2) < matchRanking.indexOf(player))) index++;
                }
                generalRanking.add(index,player);
            }
        }
    }

    public void display(){
        System.out.println("********** Ranking **********");

        for(Player player : this.generalRanking){
            System.out.println((this.generalRanking.indexOf(player)+1) + " - " + player.getTotalPoints() + "pts" + " - " + player.getGoalDifference() + "Diff" + " - " + player.getName());
        }

        System.out.println("********** Fin Ranking **********");
    }

    //*** ACCESSEURS ***
    //Retourne le classement général des joueurs
    public static List<Player> getGeneralRanking(){
        return generalRanking;
    }
    //Retourne le classement en match des joueurs
    public static List<Player> getMatchRanking(){
        return matchRanking;
    }
    //Retourne le classement en pari des joueurs
    public static List<Pair> getBetRanking(){
        return betRanking;
    }

    //*** MUTATEURS ***
    //Modifie le classement général des joueurs
    public static void setGeneralRanking(List<Player> newGeneralRanking){
        generalRanking = newGeneralRanking;
    }
    //Modifie le classement en match des joueurs
    public static void setMatchRanking(List<Player> newMatchRanking){
        matchRanking = newMatchRanking;
    }
    //Modifie le classement en pari des joueurs
    public static void setBetRanking(List<Pair> newBetRanking){
        betRanking = newBetRanking;
    }
}
