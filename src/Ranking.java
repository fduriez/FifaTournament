import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Player> generalRanking = new ArrayList<>();
    private List<Player> matchRanking = new ArrayList<>();
    private List<Pair> betRanking = new ArrayList<>();

    public Ranking(){
        for(Player player : Param.PLAYERS){
            this.generalRanking.add(player);
            this.matchRanking.add(player);
        }
    }

    public void updateRanking(){
        int index = 0;

        //MAJ classement des matchs
        this.matchRanking = new ArrayList<>();
        for(Player player : Param.PLAYERS){
            index = 0;
            if(this.matchRanking.isEmpty()) this.matchRanking.add(player);
            else{
                for(Player player2 : this.matchRanking){
                    // 1- Nombre de points
                    if(player2.getMatchPoints() > player.getMatchPoints()) index++;
                    // 2 - Différence de buts
                    if((player2.getMatchPoints() == player.getMatchPoints()) && (player2.getGoalDifference() > player.getGoalDifference())) index++;
                    // 3 - Nombre de buts marqués
                    if((player2.getMatchPoints() == player.getMatchPoints()) && (player2.getGoalDifference() == player.getGoalDifference()) && (player2.getGoalsScored() > player.getGoalsScored())) index++;
                }
                this.matchRanking.add(index,player);
            }
        }

        //MAJ classement général
        this.generalRanking = new ArrayList<>();
        for(Player player : Param.PLAYERS){
            index = 0;
            if(this.generalRanking.isEmpty()) this.generalRanking.add(player);
            else{
                for(Player player2 : this.generalRanking){
                    // 1- Nombre de points Total
                    if(player2.getTotalPoints() > player.getTotalPoints()) index++;
                    // 2 - Classement en match
                    if((player2.getTotalPoints() == player.getTotalPoints()) && (this.matchRanking.indexOf(player2) < this.matchRanking.indexOf(player))) index++;
                }
                this.generalRanking.add(index,player);
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
    public List<Player> getGeneralRanking(){
        return this.generalRanking;
    }
    //Retourne le classement en match des joueurs
    public List<Player> getMatchRanking(){
        return this.matchRanking;
    }
    //Retourne le classement en pari des joueurs
    public List<Pair> getBetRanking(){
        return this.betRanking;
    }

    //*** MUTATEURS ***
    //Modifie le classement général des joueurs
    public void setGeneralRanking(List<Player> generalRanking){
        this.generalRanking = generalRanking;
    }
    //Modifie le classement en match des joueurs
    public void setMatchRanking(List<Player> matchRanking){
        this.matchRanking = matchRanking;
    }
    //Modifie le classement en pari des joueurs
    public void setBetRanking(List<Pair> betRanking){
        this.betRanking = betRanking;
    }
}
