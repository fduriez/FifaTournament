public class Player {
    private int playerNumber;
    private String name;
    private String team;

    private float betsPoints;

    private int goalsScored;
    private int goalsTaken;

    private int numberVictory;
    private int numberDraw;
    private int numberDefeat;

    //Constructeur par défault
    public Player() {
        this.name = "Jacquou";
        this.team = "Pontu En Stock";

        this.betsPoints = 0;

        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.playerNumber = 0;

        this.numberVictory = 0;
        this.numberDraw = 0;
        this.numberDefeat = 0;
    }

    //Constructeur avec param (nom, equipe)
    public Player(String name, String team) {
        this.name = name;
        this.team = team;

        this.betsPoints = 0;

        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.playerNumber = 0;

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

        System.out.println("Points Total : " + this.getTotalPoints());
        System.out.println("Points en match : " + this.getMatchPoints());
        System.out.println("Points en pari : " + this.betsPoints);

        System.out.println("Buts Marqués: " + this.goalsScored);
        System.out.println("Buts Pris: " + this.goalsTaken);
        System.out.println("Différence de Buts: " + this.getGoalDifference());
        System.out.println("Numero de joueur: " + this.playerNumber);

        System.out.println("Nombre de match jouer: " + this.getNumberMatchPlayed());
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
        return this.getMatchPoints() + this.betsPoints;
    }
    //Retourne les points gagnés en match
    public int getMatchPoints(){
        return (this.numberVictory*3) + this.numberDraw;
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
        return this.goalsScored - this.goalsTaken;
    }
    //Retourne le numéro de joueur
    public int getPlayerNumber() {
        return playerNumber;
    }
    //Retourne le nom de l'équipe
    public String getTeam() {
        return team;
    }
    //Retourne le nombre de match joué
    public int getNumberMatchPlayed() {
        return this.numberVictory + this.numberDraw + this.numberDefeat;
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
    //Modifie les points gagnés aux paris
    public void setBetsPoints(float betsPoints){
        this.betsPoints = betsPoints;
    }
    //Modifie les buts marqués
    public void setGoalsScored(int goalsScored){
        this.goalsScored = goalsScored;
    }
    //Modifie les buts pris
    public void setGoalsTaken(int goalsTaken){
        this.goalsTaken = goalsTaken;
    }
    //Modifie le numéro de joueur
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    //Modifie le nom de l'équipe
    public void setTeam(String team) {
        this.team = team;
    }
    //Modifie le nombre de match joué
    public void setNumberVictory(int numberVictory) {
        this.numberVictory = numberVictory;
    }
    //Modifie le nombre de match joué
    public void setNumberDraw(int numberDraw) {
        this.numberDraw = numberDraw;
    }
    //Modifie le nombre de match joué
    public void setNumberDefeat(int numberDefeat) {
        this.numberDefeat = numberDefeat;
    }
}
