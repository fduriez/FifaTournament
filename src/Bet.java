public class Bet {
    Player player;
    String score = "";
    private int homeScore;
    private int visitorScore;
    boolean done = false;

    public Bet(){
        this.player = new Player();
    }

    public Bet(Player player){
        this.player = player;
    }

    public Bet(Player player, String score){
        this.player = player;
        this.score = score;
        this.done = true;
    }

    //*** ACCESSEURS ***
    //Retourne le player
    public Player getPlayer(){
        return this.player;
    }
    //Retourne le score
    public String getScore(){
        return this.score;
    }
    //Retourne le score de l'équipe domicile
    public int getHomeScore(){
        return this.homeScore;
    }
    //Retourne le score de l'équipe visiteur
    public int getVisitorScore(){
        return this.visitorScore;
    }
    //Retourne si le pari en valider
    public boolean isDone(){
        return this.done;
    }

    //*** MUTATEURS ***
    //Modifie le player
    public void setPlayer(Player player){
        this.player = player;
    }
    //Modifie le score
    public void setScore(String score){
        this.score = score;
        if(!score.isEmpty()) {
            this.done = true;

            System.out.println("score : " + score);

            //Ajoute score domicile & extérieur
            score = score.replaceAll(" ", "");
            String[] buts = score.split("-");
            this.homeScore = Integer.parseInt(buts[0]);
            this.visitorScore = Integer.parseInt(buts[1]);
        }
        else {
            this.done = false;
            this.homeScore = -1;
            this.visitorScore = -1;
        }
    }
    //Modifie le score de l'équipe domicile
    public void setHomeScore(int homeScore){
        this.homeScore = homeScore;
    }
    //Modifie le score de l'équipe visiteur
    public void setVisitorScore(int visitorScore){
        this.visitorScore = visitorScore;
    }
    //Modifie l'état du pari
    public void setDone(boolean done){
        this.done = done;
    }
}
