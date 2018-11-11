import java.util.ArrayList;
import java.util.List;

public class MatchWeek {
    private List<Match> week = new ArrayList<>();
    private List<Player> waitingPlayer = new ArrayList<>();

    public MatchWeek(){
    }

    public MatchWeek(List<Match> week){
        this.week = week;
    }

    //*** ACCESSEURS ***
    //Retourne les joueurs qui attendent
    public List<Player> getWaitingPlayer() {return waitingPlayer;}
    //Retoune les matchs de la journées
    public List<Match> getWeek() {return week;}

    //*** MUTATEURS ***
    //Modifie les joueurs qui attendent
    public void setWaitingPlayer(List<Player> waitingPlayer) {
        this.waitingPlayer = waitingPlayer;
        for(Player player : this.waitingPlayer){
            player.setNumberWaiting(player.getNumberWaiting()+1);
        }
    }
    //Modifie les matchs de la journées
    public void setWeek(List<Match> week) {this.week = week;}
}
