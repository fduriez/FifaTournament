import java.util.ArrayList;
import java.util.List;

public class Match {
    private Player homePlayer = new Player();
    private Player visitorPlayer = new Player();
    private String result;
    private int homeScore;
    private int visitorScore;
    private boolean alreadyPlayed = false;
    private List<Bet> bets = new ArrayList<>();
    private List<Player> betWinners = new ArrayList<>();
    private float betPoints;

    public Match(){
        this.result = "";
        this.homeScore = -1;
        this.visitorScore = -1;
        this.betPoints = 0;
    }

    public Match(Player homePlayer,Player visitorPlayer){
        this.homePlayer = homePlayer;
        this.visitorPlayer = visitorPlayer;
        this.result = "";
        this.homeScore = -1;
        this.visitorScore = -1;
        this.betPoints = 0;
    }

    public void display(){
        System.out.println("*** Infos Match ***");
        System.out.println("*" + this.homePlayer.getName() + " " + this.result + " " + this.visitorPlayer.getName() + "*");
    }

    //Retourne le pari d'un joueur sur ce match
    public Bet findBetFor(Player player){
        for(Bet bet : this.bets){
            if(bet.getPlayer().equals(player)) return bet;
        }
        return null;
    }

    //Détermine les gagnants du pari
    private void betsResult(){
        //Recherche de pari EXACT
        for(Bet bet : this.bets){
            if((bet.getHomeScore() == this.homeScore) && (bet.getVisitorScore() == this.visitorScore)) this.betWinners.add(bet.getPlayer());
        }
        if(!this.betWinners.isEmpty())this.betPoints = 2;

        //SI pas de pari EXACT
        if(this.betWinners.isEmpty()){
            this.betPoints = 1;
            int goalDifference = this.homeScore - this.visitorScore;
            int goalSum = this.homeScore - this.visitorScore;

            //Recherche de pari avec le bon écart
            for(Bet bet : this.bets){
                int betGoalDifference = bet.getHomeScore() - bet.getVisitorScore();
                if(goalDifference == betGoalDifference) this.betWinners.add(bet.getPlayer());
            }

            //SI pas de pari avec le bon écart && pas un match nul
            if((this.betWinners.isEmpty()) && (goalDifference != 0)){
                //Recherche du pari le plus proche
                int closestGoalDifference = 10;
                for(Bet bet : this.bets){
                    int betGoalDifference = bet.getHomeScore() - bet.getVisitorScore();
                    if((closestGoalDifference > Math.abs(betGoalDifference-goalDifference)) && (isSameWinner(bet))) closestGoalDifference = Math.abs(betGoalDifference-goalDifference);
                }
                for(Bet bet : this.bets){
                    int betGoalDifference = bet.getHomeScore() - bet.getVisitorScore();
                    if((closestGoalDifference == Math.abs(betGoalDifference-goalDifference)) && (isSameWinner(bet))) this.betWinners.add(bet.getPlayer());
                }
            }

            //Recherche de pari avec le nombre de buts le plus proche parmi les paris sélectionné
            if(this.betWinners.size() > 1){
                int closestGoalSum = 10;
                for(Player player : this.betWinners){
                    int betGoalSum = findBetFor(player).getHomeScore() + findBetFor(player).getVisitorScore();
                    if(closestGoalSum > Math.abs(betGoalSum-goalSum)) {
                        closestGoalSum = Math.abs(betGoalSum-goalSum);
                        System.out.println(closestGoalSum);
                    }
                }
                for(Player player : new ArrayList<>(this.betWinners)){
                    int betGoalSum = findBetFor(player).getHomeScore() + findBetFor(player).getVisitorScore();
                    if(closestGoalSum != Math.abs(betGoalSum-goalSum)) this.betWinners.remove(player);
                }
            }
        }

        this.betPoints /= (float)this.betWinners.size();

        //Ajout des points aux qagnants
        for(Player player : this.betWinners){
            player.setBetsPoints(player.getBetsPoints() + this.betPoints);
        }
    }

    private boolean isSameWinner(Bet bet){
        int goalDifference = this.getHomeScore() - this.getVisitorScore();
        int betGoalDifference = bet.getHomeScore() - bet.getVisitorScore();
        if((goalDifference > 0) && (betGoalDifference > 0)) return true;
        if((goalDifference < 0) && (betGoalDifference < 0)) return true;
        return false;
    }

    //*** ACCESSEURS ***
    //Retourne l'équipe à domicile
    public Player getHomePlayer() {
        return homePlayer;
    }
    //Retourne l'équipe à l'extérieure
    public Player getVisitorPlayer() {
        return visitorPlayer;
    }
    //Retourne le résultat
    public String getResult() {
        return result;
    }
    //Retourne le score de l'équipe domicile
    public int getHomeScore() {
        return homeScore;
    }
    //Retourne le score de l'équipe extérieur
    public int getVisitorScore() {
        return visitorScore;
    }
    //Retourne l'état du match
    public boolean isAlreadyPlayed(){
        return alreadyPlayed;
    }
    //Retourne la liste des paris sur le match
    public List<Bet> getBets(){
        return this.bets;
    }
    //Retourne les gagnants du pari
    public List<Player> getBetWinners(){
        return this.betWinners;
    }
    //Retourne le nombre de point gagné au pari
    public float getBetPoints(){
        return this.betPoints;
    }

    //*** MUTATEURS ***
    //Modifie l'équipe à domicile
    public void setHomePlayer(Player homeTeam) {
        this.homePlayer = homeTeam;
    }
    //Modifie l'équipe à l'extérieure
    public void setVisitorPlayer(Player visitorTeam) {
        this.visitorPlayer = visitorTeam;
    }
    //Modifie le résultat
    public void setResult(String result) {
        //SI score déjà rentré -> Suppression des stats
        if(this.result != ""){
            //Enlève les buts aux joueurs
            this.homePlayer.setGoalsScored(this.homePlayer.getGoalsScored() - this.homeScore);
            this.homePlayer.setGoalsTaken(this.homePlayer.getGoalsTaken() - this.visitorScore);
            this.visitorPlayer.setGoalsScored(this.visitorPlayer.getGoalsScored() - this.visitorScore);
            this.visitorPlayer.setGoalsTaken(this.visitorPlayer.getGoalsTaken() - this.homeScore);

            //Enlève les points aux joueurs
            if(this.homeScore > this.visitorScore){
                this.homePlayer.setNumberVictory(this.homePlayer.getNumberVictory() - 1);
                this.visitorPlayer.setNumberDefeat(this.visitorPlayer.getNumberDefeat() - 1);
            }
            else if (this.homeScore < this.visitorScore){
                this.visitorPlayer.setNumberVictory(this.visitorPlayer.getNumberVictory() - 1);
                this.homePlayer.setNumberDefeat(this.homePlayer.getNumberDefeat() - 1);
            }
            else{
                this.homePlayer.setNumberDraw(this.homePlayer.getNumberDraw() - 1);
                this.visitorPlayer.setNumberDraw(this.visitorPlayer.getNumberDraw() - 1);
            }

            //Enlève les points des paris aux joueurs
            for(Player player : this.betWinners){
                player.setBetsPoints(player.getBetsPoints() - this.betPoints);
            }

            this.result = "";
            this.alreadyPlayed = false;
        }
        if(result != "") {
            this.result = result;
            result = result.replaceAll(" ", "");

            //Ajoute score domicile & extérieur
            String[] buts = result.split("-");
            this.homeScore = Integer.parseInt(buts[0]);
            this.visitorScore = Integer.parseInt(buts[1]);

            //Ajoute les buts aux joueurs
            this.homePlayer.setGoalsScored(this.homePlayer.getGoalsScored() + this.homeScore);
            this.homePlayer.setGoalsTaken(this.homePlayer.getGoalsTaken() + this.visitorScore);
            this.visitorPlayer.setGoalsScored(this.visitorPlayer.getGoalsScored() + this.visitorScore);
            this.visitorPlayer.setGoalsTaken(this.visitorPlayer.getGoalsTaken() + this.homeScore);

            //Ajoute les points aux joueurs
            if (this.homeScore > this.visitorScore) {
                this.homePlayer.setNumberVictory(this.homePlayer.getNumberVictory() + 1);
                this.visitorPlayer.setNumberDefeat(this.visitorPlayer.getNumberDefeat() + 1);
            }
            else if (this.homeScore < this.visitorScore) {
                this.visitorPlayer.setNumberVictory(this.visitorPlayer.getNumberVictory() + 1);
                this.homePlayer.setNumberDefeat(this.homePlayer.getNumberDefeat() + 1);
            }
            else {
                this.homePlayer.setNumberDraw(this.homePlayer.getNumberDraw() + 1);
                this.visitorPlayer.setNumberDraw(this.visitorPlayer.getNumberDraw() + 1);
            }

            this.alreadyPlayed = true;

            this.betsResult();
        }
    }
    //Modifie le score de l'équipe domicile
    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }
    //Modifie le score de l'équipe extérieur
    public void setVisitorScore(int visitorScore) {
        this.visitorScore = visitorScore;
    }
    //Modifie l'état du match
    public void setAlreadyPlayed(boolean alreadyPlayed){
        this.alreadyPlayed = alreadyPlayed;
    }
    //Modifie la liste des paris sur le match
    public void setBets(List<Bet> bets){
        this.bets = bets;
    }
    //Modifie les gagnants du pari
    public void setBetWinners(List<Player> betWinners){
        this.betWinners = betWinners;
    }
    //Modifie le nombre de point gagné au pari
    public void setBetPoints(float betPoints){
        this.betPoints = betPoints;
    }
}
