public class Player {
    private String name;
    private String team;
    private int points;
    private int goalsScored;
    private int goalsTaken;
    private int goalDifference;
    private int playerNumber;
    private int numberWaiting;
    private int numberHomeMatch;
    private int numberAwayMatch;

    //Constructeur par défault
    public Player() {
        this.name = "Jacquou";
        this.team = "Pontu En Stock";
        this.points = 0;
        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.goalDifference = 0;
        this.playerNumber = 0;
    }

    //Constructeur avec param (nom)
    public Player(String name, String team) {
        this.name = name;
        this.team = team;
        this.points = 0;
        this.goalsScored = 0;
        this.goalsTaken = 0;
        this.goalDifference = 0;
        this.playerNumber = 0;
    }

    //Affichage des variables de la classe
    public void Display() {
        System.out.println();
        System.out.println("*** Infos Player ***");
        System.out.println("Nom: " + this.name);
        System.out.println("Equipe: " + this.team);
        System.out.println("Points: " + this.points);
        System.out.println("Buts Marqués: " + this.goalsScored);
        System.out.println("Buts Pris: " + this.goalsTaken);
        System.out.println("Différence de Buts: " + this.goalDifference);
        System.out.println("Numero de joueur: " + this.playerNumber);
    }

    //*** ACCESSEURS ***
    //Retourne le nom
    public String getName(){return this.name;}
    //Retourne les points
    public int getPoints(){return this.points;}
    //Retourne les buts marqués
    public int getGoalsScored(){return this.goalsScored;}
    //Retourne les buts pris
    public int getGoalsTaken(){return this.goalsTaken;}
    //Retourne la différence de buts
    public int getGoalDifference(){return this.goalDifference;}
    //Retourne le numéro de joueur
    public int getPlayerNumber() {return playerNumber;}
    //Retourne le nom de l'équipe
    public String getTeam() {return team;}
    //Retourne le nombre d'attente
    public int getNumberWaiting() {return numberWaiting;}
    //Retourne le nombre de match à domicile
    public int getNumberHomeMatch() {return numberHomeMatch;}
    //Retourne le nombre de match à l'exterieur
    public int getNumberAwayMatch() {return numberAwayMatch;}

    //*** MUTATEURS ***
    //Modifie le nom
    public void setName(String name){this.name = name;}
    //Modifie les points
    public void setPoints(int points){this.points = points;}
    //Modifie les buts marqués
    public void setGoalsScored(int goalsScored){this.goalsScored = goalsScored;}
    //Modifie les buts pris
    public void setGoalsTaken(int goalsTaken){this.goalsTaken = goalsTaken;}
    //Modifie la différence de buts
    public void setGoalDifference(int goalDifference){this.goalDifference = goalDifference;}
    //Modifie le numéro de joueur
    public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
    //Modifie le nom de l'équipe
    public void setTeam(String team) {this.team = team;}
    //Modifie le nombre d'attente
    public void setNumberWaiting(int numberWaiting) {this.numberWaiting = numberWaiting;}
    //Modifie le nombre de match à domicile
    public void setNumberHomeMatch(int numberHomeMatch) {this.numberHomeMatch = numberHomeMatch;}
    //Modifie le nombre de match à l'exterieur
    public void setNumberAwayMatch(int numberAwayMatch) {this.numberAwayMatch = numberAwayMatch;}
}
