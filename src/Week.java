import java.util.ArrayList;
import java.util.List;

public class Week {
    private int weekNumber;
    private List<Match> matchs = new ArrayList<>();
    private List<Player> waitingPlayer = new ArrayList<>();

    public Week(){
        this.weekNumber = 0;
    }

    public Week(List<Match> matchs, int weekNumber){
        this.weekNumber = weekNumber;
        this.matchs = matchs;
        this.initWaiters();
    }

    public void addMatch(Match match){
        this.matchs.add(match);
        this.initWaiters();
    }

    private void initWaiters(){
        this.waitingPlayer = new ArrayList<>(Param.PLAYERS);
        for (Match match : this.matchs) {
            this.waitingPlayer.remove(match.getHomePlayer());
            this.waitingPlayer.remove(match.getVisitorPlayer());
        }
    }

    public Match getMatchWith(Player homePlayer,Player visitorPlayer){
        for(Match match : this.matchs){
            if((match.getHomePlayer() == homePlayer) && (match.getVisitorPlayer() == visitorPlayer)) return match;
        }
        return null;
    }

    //*** ACCESSEURS ***
    //Retourne le numéro de la journée
    public int getWeekNumber() {return this.weekNumber;}
    //Retourne les joueurs qui attendent
    public List<Player> getWaitingPlayer() {return this.waitingPlayer;}
    //Retoune les matchs de la journées
    public List<Match> getMatchs() {return this.matchs;}

    //*** MUTATEURS ***
    //Modifie le numéro de la journée
    public void setWeekNumber(int weekNumber) {this.weekNumber = weekNumber;}
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
    public void setMatchs(List<Match> matchs) {this.matchs = matchs;}
}
