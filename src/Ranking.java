import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private List<Pair> ranking = new ArrayList<>();

    public Ranking(){
        int compteur = 1;
        for(Player player : Param.PLAYERS){
            this.ranking.add(new Pair(compteur,player));
            compteur++;
        }
    }

    public void updateRanking(){
        int index = 1;
        this.ranking = new ArrayList<>();
        for(Player player1 : Param.PLAYERS){
            if(this.ranking.isEmpty()) this.ranking.add(index-1,new Pair(index,player1));
            else{
                for(Pair pair : this.ranking){
                    Player player2 = (Player)pair.getValue();
                    // 1- Différence de points
                    if(player2.getPoints() > player1.getPoints()) index++;
                    // 2 - Différence de buts
                    if((player2.getPoints() == player1.getPoints()) && (player2.getGoalDifference() > player1.getGoalDifference())) index++;
                    // 3 - Différence de buts marqués
                    if((player2.getPoints() == player1.getPoints()) && (player2.getGoalDifference() == player1.getGoalDifference()) && (player2.getGoalsScored() > player1.getGoalsScored())) index++;
                }
                this.ranking.add(index-1,new Pair(index,player1));
            }
            index = 1;
        }
    }

    public void display(){
        System.out.println("********** Ranking **********");

        for(Pair pair : this.ranking){
            Player player = (Player) pair.getValue();
            System.out.println(pair.getKey() + " - " + player.getPoints() + "pts" + " - " + player.getGoalDifference() + "Diff" + " - " + player.getName());
        }

        System.out.println("********** Fin Ranking **********");
    }

    //*** ACCESSEURS ***
    //Retourne le classement des joueurs
    public List<Pair> getRanking(){return this.ranking;}

    //*** MUTATEURS ***
    //Modifie le classement des joueurs
    public void setRanking(List<Pair> ranking){this.ranking=ranking;}
}
