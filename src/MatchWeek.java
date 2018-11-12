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
        //Si non vide -> on enlève une attente aux players de la liste d'attente
        if(!this.waitingPlayer.isEmpty()){
            for(Player player : this.waitingPlayer){
                player.setNumberWaiting(player.getNumberWaiting()-1);
            }
        }
        //Ajout des players en param aux player en attente
        this.waitingPlayer = waitingPlayer;
        //Ahout d'une attente aux players dans la liste d'attente
        for(Player player : this.waitingPlayer){
            player.setNumberWaiting(player.getNumberWaiting()+1);
        }
    }
    //Modifie les matchs de la journées
    public void setWeek(List<Match> week) {this.week = week;}
}
