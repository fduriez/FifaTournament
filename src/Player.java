public class Player {
    private String name;
    private String team;

    private float totalPoints;
    private int matchPoints;
    private float betsPoints;

    private int goalsScored;
    private int goalsTaken;
    private int goalDifference;
    private int playerNumber;
    private int numberWaiting;
    private int numberHomeMatch;
    private int numberAwayMatch;

    private int numberMatchPlayed;
    private int numberVictory;
    private int numberDraw;
    private int numberDefeat;

    //Constructeur par défault
    public Player() {
        this.name = "Jacquou";
        this.team = "Pontu En Stock";

        this.totalPoints = 0;
        this.matchPoints = 0;
        this.betsPoints = 0;

        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.goalDifference = 0;
        this.playerNumber = 0;

        this.numberMatchPlayed = 0;
        this.numberVictory = 0;
        this.numberDraw = 0;
        this.numberDefeat = 0;
    }

    //Constructeur avec param (nom, equipe)
    public Player(String name, String team) {
        this.name = name;
        this.team = team;

        this.totalPoints = 0;
        this.matchPoints = 0;
        this.betsPoints = 0;

        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.goalDifference = 0;
        this.playerNumber = 0;

        this.numberMatchPlayed = 0;
        this.numberVictory = 0;
        this.numberDraw = 0;
        this.numberDefeat = 0;
    }

    //Affichage des variables de la classe
    public void display() {
        System.out.println();
        System.out.println("*** Infos Player ***");

        System.out.println("Nom: " + this.name);
        System.out.println("Equipe: " + this.team);

        System.out.println("Points Total : " + this.totalPoints);
        System.out.println("Points en match : " + this.matchPoints);
        System.out.println("Points en pari : " + this.betsPoints);

        System.out.println("Buts Marqués: " + this.goalsScored);
        System.out.println("Buts Pris: " + this.goalsTaken);
        System.out.println("Différence de Buts: " + this.goalDifference);
        System.out.println("Numero de joueur: " + this.playerNumber);

        System.out.println("Nombre de match jouer: " + this.numberMatchPlayed);
        System.out.println("Nombre de match gagné: " + this.numberVictory);
        System.out.println("Nombre de match nul: " + this.numberDraw);
        System.out.println("Nombre de match perdu: " + this.numberDefeat);

    }

    //*** ACCESSEURS ***
    //Retourne le nom
    public String getName(){
        return this.name;
    }
    //Retourne le total de points
    public float getTotalPoints(){
        return this.totalPoints;
    }
    //Retourne les points gagnés en match
    public int getMatchPoints(){
        return this.matchPoints;
    }
    //Retourne les points gagnés aux paris
    public float getBetsPoints(){
        return this.betsPoints;
    }
    //Retourne les buts marqués
    public int getGoalsScored(){
        return this.goalsScored;
    }
    //Retourne les buts pris
    public int getGoalsTaken(){
        return this.goalsTaken;
    }
    //Retourne la différence de buts
    public int getGoalDifference(){
        return this.goalDifference;
    }
    //Retourne le numéro de joueur
    public int getPlayerNumber() {
        return playerNumber;
    }
    //Retourne le nom de l'équipe
    public String getTeam() {
        return team;
    }
    //Retourne le nombre d'attente
    public int getNumberWaiting() {
        return numberWaiting;
    }
    //Retourne le nombre de match à domicile
    public int getNumberHomeMatch() {
        return numberHomeMatch;
    }
    //Retourne le nombre de match à l'exterieur
    public int getNumberAwayMatch() {
        return numberAwayMatch;
    }
    //Retourne le nombre de match joué
    public int getNumberMatchPlayed() {
        return this.numberMatchPlayed;
    }
    //Retourne le nombre de match joué
    public int getNumberVictory() {
        return this.numberVictory;
    }
    //Retourne le nombre de match joué
    public int getNumberDraw() {
        return this.numberDraw;
    }
    //Retourne le nombre de match joué
    public int getNumberDefeat() {
        return this.numberDefeat;
    }

    //*** MUTATEURS ***
    //Modifie le nom
    public void setName(String name){
        this.name = name;
    }
    //Modifie le total de points
    public void setTotalPoints(float totalPoints){
        this.totalPoints = totalPoints;
    }
    //Modifie les points gagnés en match
    public void setMatchPoints(int matchPoints){
        this.matchPoints = matchPoints;
        this.totalPoints = this.matchPoints + this.betsPoints;
    }
    //Modifie les points gagnés aux paris
    public void setBetsPoints(float betsPoints){
        this.betsPoints = betsPoints;
        this.totalPoints = this.matchPoints + this.betsPoints;
    }
    //Modifie les buts marqués
    public void setGoalsScored(int goalsScored){
        this.goalsScored = goalsScored;
        this.goalDifference = this.goalsScored - this.goalsTaken;
    }
    //Modifie les buts pris
    public void setGoalsTaken(int goalsTaken){
        this.goalsTaken = goalsTaken;
        this.goalDifference = this.goalsScored - this.goalsTaken;
    }
    //Modifie la différence de buts
    public void setGoalDifference(int goalDifference){
        this.goalDifference = goalDifference;
    }
    //Modifie le numéro de joueur
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    //Modifie le nom de l'équipe
    public void setTeam(String team) {
        this.team = team;
    }
    //Modifie le nombre d'attente
    public void setNumberWaiting(int numberWaiting) {
        this.numberWaiting = numberWaiting;
    }
    //Modifie le nombre de match à domicile
    public void setNumberHomeMatch(int numberHomeMatch) {
        this.numberHomeMatch = numberHomeMatch;
    }
    //Modifie le nombre de match à l'exterieur
    public void setNumberAwayMatch(int numberAwayMatch) {
        this.numberAwayMatch = numberAwayMatch;
    }
    //Modifie le nombre de match joué
    public void setNumberMatchPlayed(int numberMatchPlayed) {
        this.numberMatchPlayed = numberMatchPlayed;
    }
    //Modifie le nombre de match joué
    public void setNumberVictory(int numberVictory) {
        this.numberVictory = numberVictory;
        this.setMatchPoints((this.numberVictory * 3) + (this.numberDraw));
        this.numberMatchPlayed = this.numberVictory + this.numberDraw + this.numberDefeat;
    }
    //Modifie le nombre de match joué
    public void setNumberDraw(int numberDraw) {
        this.numberDraw = numberDraw;
        this.setMatchPoints((this.numberVictory * 3) + (this.numberDraw));
        this.numberMatchPlayed = this.numberVictory + this.numberDraw + this.numberDefeat;
    }
    //Modifie le nombre de match joué
    public void setNumberDefeat(int numberDefeat) {
        this.numberDefeat = numberDefeat;
        this.setMatchPoints((this.numberVictory * 3) + (this.numberDraw));
        this.numberMatchPlayed = this.numberVictory + this.numberDraw + this.numberDefeat;
    }
}
