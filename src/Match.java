public class Match {
    private Player homePlayer;
    private Player visitorPlayer;
    private String result;
    private int homeScore;
    private int visitorScore;

    public Match(){
        this.homePlayer = null;
        this.visitorPlayer = null;
        this.result = "0 - 0";
        this.homeScore = 0;
        this.visitorScore = 0;
    }

    public Match(Player homePlayer,Player visitorPlayer){
        this.homePlayer = homePlayer;
        this.visitorPlayer = visitorPlayer;
        this.result = "0 - 0";
        this.homeScore = 0;
        this.visitorScore = 0;
    }

    public void Display(){
        System.out.println("*** Infos Match ***");
        System.out.println("*" + this.homePlayer.getName() + " " + this.result + " " + this.visitorPlayer.getName() + "*");
    }

    //*** ACCESSEURS ***
    //Retourne l'équipe à domicile
    public Player getHomePlayer() {return homePlayer;}
    //Retourne l'équipe à l'extérieure
    public Player getVisitorPlayer() {return visitorPlayer;}
    //Retourne le résultat
    public String getResult() {return result;}
    //Retourne le score de l'équipe domicile
    public int getHomeScore() {return homeScore;}
    //Retourne le score de l'équipe extérieur
    public int getVisitorScore() {return visitorScore;}

    //*** MUTATEURS ***
    //Modifie l'équipe à domicile
    public void setHomePlayer(Player homeTeam) {this.homePlayer = homeTeam;}
    //Modifie l'équipe à l'extérieure
    public void setVisitorPlayer(Player visitorTeam) {this.visitorPlayer = visitorTeam;}
    //Modifie le résultat
    public void setResult(String result) {this.result = result;}
    //Modifie le score de l'équipe domicile
    public void setHomeScore(int homeScore) {this.homeScore = homeScore;}
    //Modifie le score de l'équipe extérieur
    public void setVisitorScore(int visitorScore) {this.visitorScore = visitorScore;}
}
