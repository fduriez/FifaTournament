import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Week> weeks = new ArrayList<>();
    private int currentWeek = 1;

    public Calendar(){
        this.Lottery();
        this.initCalendar();
    }

    //Associe chaque joueur avec un numéro de joueur
    private void Lottery() {
        System.out.println();
        System.out.println("********** Début lottery **********");

        //Créer une liste de nombre de 1 à NombreDeJoueur
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= Param.PLAYERS.size(); i++) {
            numbers.add(i);
        }

        //Attribut un playerNumber à chaque joueur
        int n = 0;
        for (Player player : Param.PLAYERS) {
            // génération d'un entier >= 1 et < Nombre de Joueurs
            n = (int) (Math.random() * numbers.size());
            player.setPlayerNumber(numbers.get(n));
            numbers.remove(n);
        }

        //Affiche la liste des joueurs
        for (Player player : Param.PLAYERS) {
            player.display();
        }
        System.out.println();
        System.out.println("********** Fin lottery **********");
        System.out.println();
    }

    //Initialisation du calendrier en dur
    private void initCalendar() {
        List<Pair> pairs = new ArrayList<>();
        switch (Param.PLAYERS.size()) {
            case 4:
                pairs = this.calendar4Player();
                break;
            case 5:
                pairs = this.calendar5Player();
                break;
            case 6:
                if (Param.NB_TV == 2) pairs = this.calendar6Player2TV();
                if (Param.NB_TV == 3) pairs = this.calendar6Player3TV();
                break;
            case 7:
                if (Param.NB_TV == 2) pairs = this.calendar7Player2TV();
                if (Param.NB_TV == 3) pairs = this.calendar7Player3TV();
                break;
            case 8:
                if (Param.NB_TV == 2) pairs = this.calendar8Player2TV();
                if (Param.NB_TV == 3) pairs = this.calendar8Player3TV();
                if (Param.NB_TV == 4) pairs = this.calendar8Player4TV();
                break;
        }
        int weekNumber = 0;
        Week week = new Week();
        for (Pair pair : pairs) {
            Match match = new Match(Param.getPlayerByNumber((Integer) pair.getKey()), Param.getPlayerByNumber((Integer) pair.getValue()));
            week.addMatch(match);
            if (week.getMatchs().size() == Param.NB_TV) {
                weekNumber++;
                week.setWeekNumber(weekNumber);
                this.weeks.add(week);
                week = new Week();
            }
        }
        if(!week.getMatchs().isEmpty()){
            this.weeks.add(week);
        }
    }

    //Calendrier pour 4 players
    private List<Pair> calendar4Player() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 5 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        //Journée 2
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(2, 4));
        //Journée 3
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(2, 3));
        //Journée 4
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        //Journée 5
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(4, 2));
        //Journée 6
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(3, 2));

        return pairs;
    }

    //Calendrier pour 5 players
    private List<Pair> calendar5Player() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 5 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        //Journée 2
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(2, 3));
        //Journée 3
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(2, 5));
        //Journée 4
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(5, 4));
        //Journée 5
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(3, 5));
        //Journée 6
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        //Journée 7
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(3, 2));
        //Journée 8
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(5, 2));
        //Journée 9
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(4, 5));
        //Journée 10
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 3));

        return pairs;
    }

    //Calendrier pour 6 players avec 2 TV
    private List<Pair> calendar6Player2TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 6 joueurs avec 2TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        //Journée 2
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(6, 3));
        //Journée 3
        pairs.add(new Pair(2, 6));
        pairs.add(new Pair(4, 5));
        //Journée 4
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(2, 5));
        //Journée 5
        pairs.add(new Pair(6, 4));
        pairs.add(new Pair(3, 2));
        //Journée 6
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(5, 6));
        //Journée 7
        pairs.add(new Pair(3, 5));
        pairs.add(new Pair(2, 4));
        //Journée 8
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(5, 2));
        //Journée 9
        pairs.add(new Pair(3, 6));
        pairs.add(new Pair(1, 4));
        //Journée 10
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(1, 5));
        //Journée 11
        pairs.add(new Pair(4, 3));
        pairs.add(new Pair(6, 2));
        //Journée 12
        pairs.add(new Pair(5, 3));
        pairs.add(new Pair(6, 1));
        //Journée 13
        pairs.add(new Pair(5, 4));
        pairs.add(new Pair(2, 3));
        //Journée 14
        pairs.add(new Pair(4, 6));
        pairs.add(new Pair(2, 1));
        //Journée 15
        pairs.add(new Pair(6, 5));
        pairs.add(new Pair(3, 1));

        return pairs;
    }

    //Calendrier pour 6 players avec 3 TV
    private List<Pair> calendar6Player3TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 6 joueurs avec 2TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        pairs.add(new Pair(5, 6));
        //Journée 2
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(2, 5));
        pairs.add(new Pair(4, 6));
        //Journée 3
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(2, 3));
        pairs.add(new Pair(4, 5));
        //Journée 4
        pairs.add(new Pair(6, 2));
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(3, 5));
        //Journée 5
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(3, 6));
        //Journée 6
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        pairs.add(new Pair(6, 5));
        //Journée 7
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(5, 2));
        pairs.add(new Pair(6, 4));
        //Journée 8
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(3, 2));
        pairs.add(new Pair(5, 4));
        //Journée 9
        pairs.add(new Pair(2, 6));
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(5, 3));
        //Journée 10
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(6, 3));

        return pairs;
    }

    //Calendrier pour 7 players avec 2 TV
    private List<Pair> calendar7Player2TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 7 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        //Journée 2
        pairs.add(new Pair(5, 7));
        pairs.add(new Pair(6, 3));
        //Journée 3
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(2, 5));
        //Journée 4
        pairs.add(new Pair(4, 6));
        pairs.add(new Pair(7, 3));
        //Journée 5
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(2, 6));
        //Journée 6
        pairs.add(new Pair(3, 2));
        pairs.add(new Pair(4, 7));
        //Journée 7
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(5, 6));
        //Journée 8
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(3, 5));
        //Journée 9
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(6, 7));
        //Journée 10
        pairs.add(new Pair(7, 2));
        pairs.add(new Pair(4, 5));
        //Journée 11
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(4, 3));
        //Journée 12
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(2, 7));
        //Journée 13
        pairs.add(new Pair(3, 6));
        pairs.add(new Pair(5, 4));
        //Journée 14
        pairs.add(new Pair(1, 7));
        pairs.add(new Pair(6, 2));
        //Journée 15
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(5, 3));
        //Journée 16
        pairs.add(new Pair(7, 6));
        pairs.add(new Pair(3, 1));
        //Journée 17
        pairs.add(new Pair(5, 2));
        pairs.add(new Pair(7, 4));
        //Journée 18
        pairs.add(new Pair(2, 3));
        pairs.add(new Pair(1, 6));
        //Journée 19
        pairs.add(new Pair(6, 4));
        pairs.add(new Pair(7, 5));
        //Journée 20
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(3, 7));
        //Journée 21
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(6, 5));

        return pairs;
    }

    //Calendrier pour 7 players avec 3 TV
    private List<Pair> calendar7Player3TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 7 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        pairs.add(new Pair(5, 6));
        //Journée 2
        pairs.add(new Pair(2, 3));
        pairs.add(new Pair(4, 5));
        pairs.add(new Pair(6, 7));
        //Journée 3
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 7));
        //Journée 4
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(3, 5));
        pairs.add(new Pair(4, 6));
        //Journée 5
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(7, 2));
        pairs.add(new Pair(3, 6));
        //Journée 6
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(6, 2));
        pairs.add(new Pair(4, 7));
        //Journée 7
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(2, 5));
        pairs.add(new Pair(7, 3));
        //Journée 8
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        pairs.add(new Pair(6, 5));
        //Journée 9
        pairs.add(new Pair(3, 2));
        pairs.add(new Pair(5, 4));
        pairs.add(new Pair(7, 6));
        //Journée 10
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(7, 5));
        //Journée 11
        pairs.add(new Pair(1, 7));
        pairs.add(new Pair(5, 3));
        pairs.add(new Pair(6, 4));
        //Journée 12
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(2, 7));
        pairs.add(new Pair(6, 3));
        //Journée 13
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(2, 6));
        pairs.add(new Pair(7, 4));
        //Journée 14
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(5, 2));
        pairs.add(new Pair(3, 7));

        return pairs;
    }

    //Calendrier pour 8 players avec 2 TV
    private List<Pair> calendar8Player2TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 8 joueurs avec 3 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        //Journée 2
        pairs.add(new Pair(5, 6));
        pairs.add(new Pair(7, 8));
        //Journée 3
        pairs.add(new Pair(8, 1));
        pairs.add(new Pair(2, 3));
        //Journée 4
        pairs.add(new Pair(4, 5));
        pairs.add(new Pair(6, 7));
        //Journée 5
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(8, 2));
        //Journée 6
        pairs.add(new Pair(3, 5));
        pairs.add(new Pair(4, 6));
        //Journée 7
        pairs.add(new Pair(4, 8));
        pairs.add(new Pair(7, 2));
        //Journée 8
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(3, 6));
        //Journée 9
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 8));
        //Journée 10
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(3, 7));
        //Journée 11
        pairs.add(new Pair(4, 7));
        pairs.add(new Pair(2, 5));
        //Journée 12
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(6, 8));
        //Journée 13
        pairs.add(new Pair(5, 7));
        pairs.add(new Pair(2, 6));
        //Journée 14
        pairs.add(new Pair(8, 3));
        pairs.add(new Pair(1, 4));
        //Journée 15
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        //Journée 16
        pairs.add(new Pair(6, 5));
        pairs.add(new Pair(8, 7));
        //Journée 17
        pairs.add(new Pair(1, 8));
        pairs.add(new Pair(3, 2));
        //Journée 18
        pairs.add(new Pair(5, 4));
        pairs.add(new Pair(7, 6));
        //Journée 19
        pairs.add(new Pair(1, 7));
        pairs.add(new Pair(2, 8));
        //Journée 20
        pairs.add(new Pair(5, 3));
        pairs.add(new Pair(6, 4));
        //Journée 21
        pairs.add(new Pair(8, 4));
        pairs.add(new Pair(2, 7));
        //Journée 22
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(6, 3));
        //Journée 23
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(8, 5));
        //Journée 24
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(7, 3));
        //Journée 25
        pairs.add(new Pair(7, 4));
        pairs.add(new Pair(5, 2));
        //Journée 26
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(8, 6));
        //Journée 27
        pairs.add(new Pair(7, 5));
        pairs.add(new Pair(6, 2));
        //Journée 28
        pairs.add(new Pair(3, 8));
        pairs.add(new Pair(4, 1));

        return pairs;
    }

    //Calendrier pour 8 players avec 3 TV
    private List<Pair> calendar8Player3TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 8 joueurs avec 3 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        pairs.add(new Pair(5, 6));
        //Journée 2
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(8, 5));
        pairs.add(new Pair(2, 3));
        //Journée 3
        pairs.add(new Pair(4, 7));
        pairs.add(new Pair(1, 8));
        pairs.add(new Pair(6, 3));
        //Journée 4
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 7));
        pairs.add(new Pair(8, 6));
        //Journée 5
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(4, 5));
        pairs.add(new Pair(7, 2));
        //Journée 6
        pairs.add(new Pair(6, 2));
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(8, 3));
        //Journée 7
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(2, 8));
        pairs.add(new Pair(6, 7));
        //Journée 8
        pairs.add(new Pair(3, 5));
        pairs.add(new Pair(4, 6));
        pairs.add(new Pair(7, 8));
        //Journée 9
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(2, 5));
        pairs.add(new Pair(4, 8));
        //Journée 10
        pairs.add(new Pair(3, 7));
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(2, 6));
        //Journée 11
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(5, 4));
        pairs.add(new Pair(8, 7));
        //Journée 12
        pairs.add(new Pair(3, 2));
        pairs.add(new Pair(6, 8));
        pairs.add(new Pair(7, 5));
        //Journée 13
        pairs.add(new Pair(8, 1));
        pairs.add(new Pair(5, 2));
        pairs.add(new Pair(7, 4));
        //Journée 14
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(5, 3));
        pairs.add(new Pair(2, 7));
        //Journée 15
        pairs.add(new Pair(6, 5));
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(3, 8));
        //Journée 16
        pairs.add(new Pair(1, 7));
        pairs.add(new Pair(8, 4));
        pairs.add(new Pair(3, 6));
        //Journée 17
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        pairs.add(new Pair(5, 8));
        //Journée 18
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(8, 2));
        pairs.add(new Pair(7, 6));
        //Journée 19
        pairs.add(new Pair(6, 4));
        pairs.add(new Pair(7, 3));
        return pairs;
    }

    //Calendrier pour 8 players avec 4 TV
    private List<Pair> calendar8Player4TV() {
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 8 joueurs avec 3 TV
        //Journée 1
        pairs.add(new Pair(1, 2));
        pairs.add(new Pair(3, 4));
        pairs.add(new Pair(5, 6));
        pairs.add(new Pair(7, 8));
        //Journée 2
        pairs.add(new Pair(8, 1));
        pairs.add(new Pair(2, 3));
        pairs.add(new Pair(4, 5));
        pairs.add(new Pair(6, 7));
        //Journée 3
        pairs.add(new Pair(1, 3));
        pairs.add(new Pair(2, 4));
        pairs.add(new Pair(5, 7));
        pairs.add(new Pair(6, 8));
        //Journée 4
        pairs.add(new Pair(7, 1));
        pairs.add(new Pair(8, 2));
        pairs.add(new Pair(3, 5));
        pairs.add(new Pair(4, 6));
        //Journée 5
        pairs.add(new Pair(1, 4));
        pairs.add(new Pair(5, 8));
        pairs.add(new Pair(2, 6));
        pairs.add(new Pair(3, 7));
        //Journée 6
        pairs.add(new Pair(6, 1));
        pairs.add(new Pair(2, 5));
        pairs.add(new Pair(8, 3));
        pairs.add(new Pair(4, 7));
        //Journée 7
        pairs.add(new Pair(1, 5));
        pairs.add(new Pair(7, 2));
        pairs.add(new Pair(3, 6));
        pairs.add(new Pair(4, 8));
        //Journée 8
        pairs.add(new Pair(2, 1));
        pairs.add(new Pair(4, 3));
        pairs.add(new Pair(6, 5));
        pairs.add(new Pair(8, 7));
        //Journée 9
        pairs.add(new Pair(1, 8));
        pairs.add(new Pair(3, 2));
        pairs.add(new Pair(5, 4));
        pairs.add(new Pair(7, 6));
        //Journée 10
        pairs.add(new Pair(3, 1));
        pairs.add(new Pair(4, 2));
        pairs.add(new Pair(7, 5));
        pairs.add(new Pair(8, 6));
        //Journée 11
        pairs.add(new Pair(1, 7));
        pairs.add(new Pair(2, 8));
        pairs.add(new Pair(5, 3));
        pairs.add(new Pair(6, 4));
        //Journée 12
        pairs.add(new Pair(4, 1));
        pairs.add(new Pair(8, 5));
        pairs.add(new Pair(6, 2));
        pairs.add(new Pair(7, 3));
        //Journée 13
        pairs.add(new Pair(1, 6));
        pairs.add(new Pair(5, 2));
        pairs.add(new Pair(3, 8));
        pairs.add(new Pair(7, 4));
        //Journée 14
        pairs.add(new Pair(5, 1));
        pairs.add(new Pair(2, 7));
        pairs.add(new Pair(6, 3));
        pairs.add(new Pair(8, 4));

        return pairs;
    }

    //Affiche le Calendrier
    public void display() {
        System.out.println();
        System.out.println("******************** Calendrier ********************");
        System.out.println();
        for (Week week : this.weeks) {
            System.out.println("*** Journée " + week.getWeekNumber() + " ***");
            for (Match match : week.getMatchs()) {
                System.out.println(match.getHomePlayer().getPlayerNumber() + " - " + match.getVisitorPlayer().getPlayerNumber());
            }
            for (Player player : week.getWaitingPlayer()) {
                System.out.print(player.getPlayerNumber() + " ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("******************** Fin Calendrier ********************");

        for (Player player : Param.PLAYERS) {
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());
        }
    }

    //Retourne La journée en cours
    public Week getCurrentWeek(){
        for(Week week : this.weeks){
            if(week.getCurrentMatch() != null) return week;
        }
        return null;
    }

    //*** ACCESSEURS ***
    //Retourne la liste des journées de championnat
    public List<Week> getWeeks(){return this.weeks;}

    //*** MUTATEURS ***
    //Modifie la liste des journées de championnat
    public void setWeeks(List<Week> weeks){this.weeks = weeks;}
}
