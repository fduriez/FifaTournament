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

    public void initWaiters(){
        this.waitingPlayer = new ArrayList<>(Param.PLAYERS);
        for (Match match : this.matchs) {
            this.waitingPlayer.remove(match.getHomePlayer());
            this.waitingPlayer.remove(match.getVisitorPlayer());
        }
    }

    public void initBettor(){
        for(Match match : matchs){
            for (Player player : this.waitingPlayer){
                Bet bet = new Bet(player);
                match.getBets().add(bet);
            }
        }
        if(this.waitingPlayer.size() < Param.NB_BETTOR_MINI){
            if (this.matchs.size() == 2){
                this.matchs.get(0).getBets().add(new Bet(this.matchs.get(1).getHomePlayer()));
                this.matchs.get(0).getBets().add(new Bet(this.matchs.get(1).getVisitorPlayer()));
                this.matchs.get(1).getBets().add(new Bet(this.matchs.get(0).getHomePlayer()));
                this.matchs.get(1).getBets().add(new Bet(this.matchs.get(0).getVisitorPlayer()));
            }
            else if (this.matchs.size() == 3){
                this.matchs.get(0).getBets().add(new Bet(this.matchs.get(1).getHomePlayer()));
                this.matchs.get(0).getBets().add(new Bet(this.matchs.get(1).getVisitorPlayer()));
                this.matchs.get(1).getBets().add(new Bet(this.matchs.get(2).getHomePlayer()));
                this.matchs.get(1).getBets().add(new Bet(this.matchs.get(2).getVisitorPlayer()));
                this.matchs.get(2).getBets().add(new Bet(this.matchs.get(0).getHomePlayer()));
                this.matchs.get(2).getBets().add(new Bet(this.matchs.get(0).getVisitorPlayer()));
            }
        }
    }

    public Match getMatchWith(Player homePlayer,Player visitorPlayer){
        for(Match match : this.matchs){
            if((match.getHomePlayer() == homePlayer) && (match.getVisitorPlayer() == visitorPlayer)) return match;
        }
        return null;
    }

    public boolean isBetsDoneFor(Player player){
        for(Match match : this.matchs){
            for(Bet bet : match.getBets()){
                if((bet.getPlayer() == player) && (!bet.isDone())) return false;
            }
        }
        return true;
    }

    public boolean isAllBetsDone(){
        for(Match match : this.matchs){
            for(Bet bet : match.getBets()){
                if(!bet.isDone()) return false;
            }
        }
        return true;
    }

    public boolean isAllMatchPlayed(){
        for(Match match : this.matchs){
            if(!match.isAlreadyPlayed()) return false;
        }
        return true;
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
        //Ajout d'une attente aux players dans la liste d'attente
        for(Player player : this.waitingPlayer){
            player.setNumberWaiting(player.getNumberWaiting()+1);
        }
    }
    //Modifie les matchs de la journées
    public void setMatchs(List<Match> matchs) {this.matchs = matchs;}
}
