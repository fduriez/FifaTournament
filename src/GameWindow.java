import javafx.util.Pair;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    private JTable rankingTable;
    private JTable calendarTable;
    private JButton validButton = new JButton("Valider");

    private List<Player> players = new ArrayList<>();
    private List<MatchWeek> calendar = new ArrayList<>();
    private List<int[]> playerWaiting = new ArrayList<>();

    private int nbTV;
    private int nbPlayerGaming;
    private int nbPlayerWaiting;

    public GameWindow(List<Player> players, int nbTV) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TOURNOI FIFA");
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.nbTV = nbTV;
        this.players = players;

        //Création du Tableau d'affichage du classement
        Object[][] data = {};
        String[] titleRanking = {"Joueur","Equipe","Points","Diff de buts"};
        this.rankingTable = new JTable(new DefaultTableModel(data, titleRanking)){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.rankingTable.setFillsViewportHeight(true);
        this.rankingTable.setRowHeight(18);
        JScrollPane scRanking = new JScrollPane(this.rankingTable);

        //Création du Tableau d'affichage du calendrier
        String[] titleCalendar = {"Match","Score"};
        this.calendarTable = new JTable(new DefaultTableModel(data, titleCalendar)){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.calendarTable.setFillsViewportHeight(true);
        this.calendarTable.setRowHeight(18);
        JScrollPane scCalendar = new JScrollPane(this.calendarTable);

        JPanel panelCalendar = new JPanel();
        panelCalendar.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelCalendar.add(scCalendar);
        panelCalendar.add(this.validButton);
        JPanel panelRanking = new JPanel();
        panelRanking.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelRanking.add(scRanking);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(panelCalendar);
        mainPanel.add(panelRanking);
        this.getContentPane().add(mainPanel);
        this.setVisible(true);

        Lottery();
        initCalendar();

        //Initialisation du tournoi
        //InitParameters();
        //initCalendarFromAlgo();

        //CreateCalendar();
        //InitCalendarTable();

        calendarDisplay();

        //Fonction déclanché par le validButton
        ActionListener action = new ActionListener() {
            public void actionPerformed (ActionEvent e){
                System.out.println("Reussi");
                //ScoreWindow scoreWindow = new ScoreWindow(calendar.get(0));
            }
        };

        //liaison entre le bouton et sa fonction
        this.validButton.addActionListener(action);
    }

    //Initialise les paramètres
    public void InitParameters(){
        this.nbPlayerGaming = this.nbTV * 2;
        //Vérifie qu'il n'y pas plus de joueur entrain de jouer que de joueur inscrit
        if(this.players.size()<this.nbPlayerGaming) this.nbPlayerGaming = this.players.size();
        //Vérifie que le nombre de joueur entrain de jouer est pair
        if(this.nbPlayerGaming%2 == 1) this.nbPlayerGaming--;

        this.nbPlayerWaiting = this.players.size() - this.nbPlayerGaming;
        if(this.nbPlayerWaiting>this.nbPlayerGaming)System.out.println("Alerte!!! Plus de joueurs qui attendent que de joueurs qui jouent!!!");
    }

    //Associe chaque joueur avec un numéro de joueur
    public void Lottery() {
        System.out.println();
        System.out.println("********** Début lottery **********");

        //Créer une liste de nombre de 1 à NombreDeJoueur
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i=1; i<=this.players.size() ;i++){
            numbers.add(i);
        }

        //Attribut un playerNumber à chaque joueur
        int n = 0;
        for(Player player : this.players){
            // génération d'un entier >= 1 et < Nombre de Joueurs
            n = (int)(Math.random() * numbers.size());
            player.setPlayerNumber(numbers.get(n));
            numbers.remove(n);
        }

        //Affiche la liste des joueurs
        for(Player player : this.players){
            player.Display();
        }
        System.out.println();
        System.out.println("********** Fin lottery **********");
        System.out.println();
    }

    //Rempli le calendrier
    public void CreateCalendar(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 6 joueurs
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));

        pairs.add(new Pair(5,1));
        pairs.add(new Pair(6,3));

        pairs.add(new Pair(2,6));
        pairs.add(new Pair(4,5));

        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,5));

        pairs.add(new Pair(6,4));
        pairs.add(new Pair(3,2));

        pairs.add(new Pair(4,1));
        pairs.add(new Pair(5,6));

        pairs.add(new Pair(3,5));
        pairs.add(new Pair(2,4));

        pairs.add(new Pair(1,6));
        pairs.add(new Pair(5,2));

        pairs.add(new Pair(3,6));
        pairs.add(new Pair(1,4));

        pairs.add(new Pair(4,2));
        pairs.add(new Pair(1,5));

        pairs.add(new Pair(4,3));
        pairs.add(new Pair(6,2));

        pairs.add(new Pair(5,3));
        pairs.add(new Pair(6,1));

        pairs.add(new Pair(5,4));
        pairs.add(new Pair(2,3));

        pairs.add(new Pair(4,6));
        pairs.add(new Pair(2,1));

        pairs.add(new Pair(6,5));
        pairs.add(new Pair(3,1));

        for(Pair pair : pairs) {
            for (Player player1 : this.players) {
                for (Player player2 : this.players) {
                    if ((player1.getPlayerNumber() == (int) pair.getKey()) && (player2.getPlayerNumber() == (int) pair.getValue())) {
                        //this.calendar.add(new Match(player1,player2));
                    }
                }
            }
        }

        //Affiche le calendrier
        /*for(Match match : calendar){
            match.Display();
        }*/
    }

    //Initialise le tableau de calendrier
    public void InitCalendarTable(){
        String matchStr;
        /*for(Match match : this.calendar){
            matchStr = match.getHomePlayer().getName();
            matchStr += " - ";
            matchStr += match.getVisitorPlayer().getName();
            ((DefaultTableModel) this.calendarTable.getModel()).addRow(new Object[]{matchStr,""});
        }*/
    }

    //Initialisation du Calendrier
    public void initCalendarFromAlgo(){
        int cursor = 0;

        List<Pair> allHomeMatchs = new ArrayList<>();
        for(int team1=1; team1<=this.players.size(); team1++){
            cursor = 0;
            for(int team2=team1+1; team2<=this.players.size(); team2++){
                if(team1 != team2) {
                    if(cursor == this.players.size()/2){
                        allHomeMatchs.add(new Pair(team2,team1));
                    }
                    else{
                        allHomeMatchs.add(new Pair(team1,team2));
                        cursor++;
                    }
                }
            }
        }

        int numberHomeMatchs = (allHomeMatchs.size()/2);

        while(this.calendar.size() != numberHomeMatchs){
            this.calendar.add(new MatchWeek());
            //System.out.println("Taille calendrier : " + this.calendar.size());
            for(Pair pair1 : new ArrayList<Pair>(allHomeMatchs)){
                Match match1 = new Match(getPlayerByNumber((Integer) pair1.getKey()), getPlayerByNumber((Integer) pair1.getValue()));
                for(Pair pair2 : new ArrayList<Pair>(allHomeMatchs)){
                    Match match2 = new Match(getPlayerByNumber((Integer) pair2.getKey()), getPlayerByNumber((Integer) pair2.getValue()));
                    //!!!CONDITIONS!!! 4 Equipes différentes &&
                    if ((match1.getHomePlayer() != match2.getHomePlayer()) && (match1.getHomePlayer() != match2.getVisitorPlayer()) &&
                            (match1.getVisitorPlayer() != match2.getHomePlayer()) && (match1.getVisitorPlayer() != match2.getVisitorPlayer()) &&
                            (this.calendar.get(this.calendar.size()-1).getWeek().isEmpty())){
                        List week = new ArrayList<>();
                        week.add(match1);
                        week.add(match2);
                        List<Player> waiters = new ArrayList<>(getWaiters(week));
                        if(this.calendar.size()<2){
                            this.calendar.get(this.calendar.size()-1).setWeek(week);

                            this.calendar.get(this.calendar.size()-1).setWaitingPlayer(waiters);

                            allHomeMatchs.remove(pair1);
                            allHomeMatchs.remove(pair2);
                        }
                        else{
                            //int allWaitersMustPlay = 0;
                            int allMandatoryWaitersWait = 0;
                            List<Player> mandatoryWaiters = new ArrayList<>(getMandatoryWaiters());
                            List<Player> possibleWaiters = new ArrayList<>(getPossibleWaiters(mandatoryWaiters));

                            //Vérifie si les joueurs devant attendre, attendent
                            for(Player mandatoryWaitersPlayer : mandatoryWaiters){
                                for(Player waitersPlayer : new ArrayList<>(waiters)){
                                    if(mandatoryWaitersPlayer.equals(waitersPlayer)){
                                        allMandatoryWaitersWait++;
                                        waiters.remove(waitersPlayer);
                                    }
                                }
                            }

                            if(allMandatoryWaitersWait == mandatoryWaiters.size()){
                                for(Player waitersPlayer : new ArrayList<>(waiters)){
                                    for(Player possibleWaitersPlayer : possibleWaiters){
                                        if(waitersPlayer.equals(possibleWaitersPlayer)) waiters.remove(waitersPlayer);
                                    }
                                }
                            }

                            if(waiters.isEmpty()){
                                waiters = getWaiters(week);
                                this.calendar.get(this.calendar.size()-1).setWeek(week);

                                this.calendar.get(this.calendar.size()-1).setWaitingPlayer(waiters);

                                allHomeMatchs.remove(pair1);
                                allHomeMatchs.remove(pair2);
                                /*
                                System.out.print("Mandatory Waiters : ");
                                for(Player player : mandatoryWaiters){
                                    System.out.print(player.getPlayerNumber() + " ");
                                }
                                System.out.println();
                                System.out.print("Possible Waiters : ");
                                for(Player player : possibleWaiters){
                                    System.out.print(player.getPlayerNumber() + " ");
                                }
                                System.out.println();
                                */
                            }
                                /*for(Player player : possibleWaiters){
                                    if((player.getPlayerNumber() != (int) match1.getKey()) && (player.getPlayerNumber() != (int) match1.getValue()) &&
                                            (player.getPlayerNumber() != (int) match2.getKey()) && (player.getPlayerNumber() != (int) match2.getValue())) allWaitersMustPlay++;
                                }

                                if(allWaitersMustPlay == (players.size() - (this.nbTV*2))){
                                    week.add(new Match(getPlayerByNumber((Integer) match1.getKey()),getPlayerByNumber((Integer) match1.getValue())));
                                    week.add(new Match(getPlayerByNumber((Integer) match2.getKey()),getPlayerByNumber((Integer) match2.getValue())));
                                    this.calendar.get(this.calendar.size()-1).setWeek(week);

                                    this.calendar.get(this.calendar.size()-1).setWaitingPlayer(getWaiters(week));

                                    allHomeMatchs.remove(match1);
                                    allHomeMatchs.remove(match2);
                            }*/
                        }
                    }
                }
            }
        }
        /*
        //Ajout des derniers matchs Aller dans le calendrier si les matchs aller restant ne remplisse pas un week
        if (allHomeMatchs.size() >= this.nbTV){
            System.out.println("Erreur dans la création du calendrier aller");
        }
        else if(allHomeMatchs.size() > 0){
            this.calendar.add(new MatchWeek());
            List week = new ArrayList<>();
            for(Pair pair : new ArrayList<>(allHomeMatchs)){
                week.add(new Match(getPlayerByNumber((Integer) pair.getKey()),getPlayerByNumber((Integer) pair.getValue())));
                allHomeMatchs.remove(pair);
            }
            this.calendar.get(this.calendar.size()-1).setWeek(week);
        }
        */

        /*
        for(Player player : this.players){
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());
        }
        */


        int day = 0;
        for(MatchWeek week : this.calendar){
            day++;
            System.out.println();
            System.out.println("*** Journée " + day + " ***");
            for(Match match : week.getWeek()){
                System.out.println(match.getHomePlayer().getPlayerNumber() + " - " + match.getVisitorPlayer().getPlayerNumber());
            }
            System.out.println("Waiters :");
            for(Player player : week.getWaitingPlayer()){
                System.out.print(player.getPlayerNumber() + " ");
            }
            System.out.println();
        }
        System.out.println(allHomeMatchs);
    }

    //Retourne le player lié au number
    public Player getPlayerByNumber(int number){
        for(Player player : this.players){
            if(number == player.getPlayerNumber()) return player;
        }
        return null;
    }

    //Retourne les waiters en fonction du week
    public List<Player> getWaiters(List<Match> week){
        List<Player> waiters = new ArrayList<>(this.players);
        for(Player player : this.players) {
            for(Match match : week){
                if((player == match.getHomePlayer()) || (player == match.getVisitorPlayer())) waiters.remove(player);
            }
        }
        return waiters;
    }

    //Retourne les waiters possible
    public List<Player> getPossibleWaiters (List<Player> mandatoryWaiters){

        int minimum = 1000;
        List<Player> possibleWaiters = new ArrayList<>();
        List<Player> previousWaiters = new ArrayList<>(this.calendar.get(this.calendar.size()-2).getWaitingPlayer());

        for(Player player : possibleWaiters){
            if(player.getNumberWaiting() < minimum) minimum = player.getNumberWaiting();
        }

        while((possibleWaiters.size() + mandatoryWaiters.size()) < this.players.size()-(this.nbTV*2)){
            possibleWaiters = new ArrayList<>(this.players);
            for(Player previousWaiter : previousWaiters){
                possibleWaiters.remove(previousWaiter);
            }

            for(Player previousWaiter : mandatoryWaiters){
                possibleWaiters.remove(previousWaiter);
            }

            //System.out.println("mini : " + minimum);
            for(Player player : new ArrayList<>(possibleWaiters)){
                //System.out.println(player.getPlayerNumber());
                if(player.getNumberWaiting() > minimum) possibleWaiters.remove(player);
            }
            minimum++;
        }
        return possibleWaiters;
    }

    public List<Player> getMandatoryWaiters (){
        int minimum = 1000;
        List<Player> mandatoryWaiters = new ArrayList<>(this.players);

        for(Player player : mandatoryWaiters){
            if(player.getNumberWaiting() < minimum) minimum = player.getNumberWaiting();
        }

        for(Player player : new ArrayList<>(mandatoryWaiters)){
            if(player.getNumberWaiting() > minimum) mandatoryWaiters.remove(player);
        }

        if(mandatoryWaiters.size() > this.players.size()-(this.nbTV*2)) mandatoryWaiters = new ArrayList<>();

        return mandatoryWaiters;
    }

    //Initialisation du calendrier en dur
    public void initCalendar(){
        List<Pair> pairs = new ArrayList<>();
        switch (this.players.size()){
            case 4:
                pairs=calendar4Player();
                break;
            case 5:
                pairs=calendar5Player();
                break;
            case 6:
                if(this.nbTV == 2) pairs=calendar6Player2TV();
                if(this.nbTV == 3) pairs=calendar6Player3TV();
                break;
            case 7:
                if(this.nbTV == 2) pairs=calendar7Player2TV();
                if(this.nbTV == 3) pairs=calendar7Player3TV();
                break;
            case 8:
                if(this.nbTV == 3) pairs=calendar8Player3TV();
                if(this.nbTV == 4) pairs=calendar8Player4TV();
                break;
        }
        this.calendar.add(new MatchWeek());
        List week = new ArrayList<>();
        for(Pair pair : pairs) {
            Match match = new Match(getPlayerByNumber((Integer) pair.getKey()), getPlayerByNumber((Integer) pair.getValue()));
            week.add(match);
            if(week.size() == this.nbTV){
                this.calendar.get(this.calendar.size()-1).setWeek(week);
                this.calendar.get(this.calendar.size()-1).setWaitingPlayer(getWaiters(week));
                week = new ArrayList<>();
                this.calendar.add(new MatchWeek());
            }
        }
        if(!week.isEmpty()){
            this.calendar.get(this.calendar.size()-1).setWeek(week);
            this.calendar.get(this.calendar.size()-1).setWaitingPlayer(getWaiters(week));
        }
        else this.calendar.remove(this.calendar.size()-1);
    }

    //Calendrier pour 4 players
    public List<Pair> calendar4Player(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 5 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        //Journée 2
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,4));
        //Journée 3
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(2,3));
        //Journée 4
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        //Journée 5
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(4,2));
        //Journée 6
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(3,2));

        return pairs;
    }

    //Calendrier pour 5 players
    public List<Pair> calendar5Player(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 5 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        //Journée 2
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(2,3));
        //Journée 3
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(2,5));
        //Journée 4
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(5,4));
        //Journée 5
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(3,5));
        //Journée 6
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        //Journée 7
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(3,2));
        //Journée 8
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(5,2));
        //Journée 9
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(4,5));
        //Journée 10
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(5,3));

        return pairs;
    }

    //Calendrier pour 6 players avec 2 TV
    public List<Pair> calendar6Player2TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 6 joueurs avec 2TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        //Journée 2
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(6,3));
        //Journée 3
        pairs.add(new Pair(2,6));
        pairs.add(new Pair(4,5));
        //Journée 4
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,5));
        //Journée 5
        pairs.add(new Pair(6,4));
        pairs.add(new Pair(3,2));
        //Journée 6
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(5,6));
        //Journée 7
        pairs.add(new Pair(3,5));
        pairs.add(new Pair(2,4));
        //Journée 8
        pairs.add(new Pair(1,6));
        pairs.add(new Pair(5,2));
        //Journée 9
        pairs.add(new Pair(3,6));
        pairs.add(new Pair(1,4));
        //Journée 10
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(1,5));
        //Journée 11
        pairs.add(new Pair(4,3));
        pairs.add(new Pair(6,2));
        //Journée 12
        pairs.add(new Pair(5,3));
        pairs.add(new Pair(6,1));
        //Journée 13
        pairs.add(new Pair(5,4));
        pairs.add(new Pair(2,3));
        //Journée 14
        pairs.add(new Pair(4,6));
        pairs.add(new Pair(2,1));
        //Journée 15
        pairs.add(new Pair(6,5));
        pairs.add(new Pair(3,1));

        return pairs;
    }

    //Calendrier pour 6 players avec 3 TV
    public List<Pair> calendar6Player3TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 6 joueurs avec 2TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        pairs.add(new Pair(5,6));
        //Journée 2
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,5));
        pairs.add(new Pair(4,6));
        //Journée 3
        pairs.add(new Pair(6,1));
        pairs.add(new Pair(2,3));
        pairs.add(new Pair(4,5));
        //Journée 4
        pairs.add(new Pair(6,2));
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(3,5));
        //Journée 5
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(3,6));
        //Journée 6
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        pairs.add(new Pair(6,5));
        //Journée 7
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(5,2));
        pairs.add(new Pair(6,4));
        //Journée 8
        pairs.add(new Pair(1,6));
        pairs.add(new Pair(3,2));
        pairs.add(new Pair(5,4));
        //Journée 9
        pairs.add(new Pair(2,6));
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(5,3));
        //Journée 10
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(6,3));

        return pairs;
    }

    //Calendrier pour 7 players avec 2 TV
    public List<Pair> calendar7Player2TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 7 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        //Journée 2
        pairs.add(new Pair(5,7));
        pairs.add(new Pair(6,3));
        //Journée 3
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(2,5));
        //Journée 4
        pairs.add(new Pair(4,6));
        pairs.add(new Pair(7,3));
        //Journée 5
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(2,6));
        //Journée 6
        pairs.add(new Pair(3,2));
        pairs.add(new Pair(4,7));
        //Journée 7
        pairs.add(new Pair(7,1));
        pairs.add(new Pair(5,6));
        //Journée 8
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(3,5));
        //Journée 9
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(6,7));
        //Journée 10
        pairs.add(new Pair(7,2));
        pairs.add(new Pair(4,5));
        //Journée 11
        pairs.add(new Pair(6,1));
        pairs.add(new Pair(4,3));
        //Journée 12
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(2,7));
        //Journée 13
        pairs.add(new Pair(3,6));
        pairs.add(new Pair(5,4));
        //Journée 14
        pairs.add(new Pair(1,7));
        pairs.add(new Pair(6,2));
        //Journée 15
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(5,3));
        //Journée 16
        pairs.add(new Pair(7,6));
        pairs.add(new Pair(3,1));
        //Journée 17
        pairs.add(new Pair(5,2));
        pairs.add(new Pair(7,4));
        //Journée 18
        pairs.add(new Pair(2,3));
        pairs.add(new Pair(1,6));
        //Journée 19
        pairs.add(new Pair(6,4));
        pairs.add(new Pair(7,5));
        //Journée 20
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(3,7));
        //Journée 21
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(6,5));

        return pairs;
    }

    //Calendrier pour 7 players avec 3 TV
    public List<Pair> calendar7Player3TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 7 joueurs avec 2 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        pairs.add(new Pair(5,6));
        //Journée 2
        pairs.add(new Pair(2,3));
        pairs.add(new Pair(4,5));
        pairs.add(new Pair(6,7));
        //Journée 3
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(5,7));
        //Journée 4
        pairs.add(new Pair(7,1));
        pairs.add(new Pair(3,5));
        pairs.add(new Pair(4,6));
        //Journée 5
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(7,2));
        pairs.add(new Pair(3,6));
        //Journée 6
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(6,2));
        pairs.add(new Pair(4,7));
        //Journée 7
        pairs.add(new Pair(6,1));
        pairs.add(new Pair(2,5));
        pairs.add(new Pair(7,3));
        //Journée 8
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        pairs.add(new Pair(6,5));
        //Journée 9
        pairs.add(new Pair(3,2));
        pairs.add(new Pair(5,4));
        pairs.add(new Pair(7,6));
        //Journée 10
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(7,5));
        //Journée 11
        pairs.add(new Pair(1,7));
        pairs.add(new Pair(5,3));
        pairs.add(new Pair(6,4));
        //Journée 12
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(2,7));
        pairs.add(new Pair(6,3));
        //Journée 13
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(2,6));
        pairs.add(new Pair(7,4));
        //Journée 14
        pairs.add(new Pair(1,6));
        pairs.add(new Pair(5,2));
        pairs.add(new Pair(3,7));

        return pairs;
    }

    //Calendrier pour 8 players avec 3 TV
    public List<Pair> calendar8Player3TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 8 joueurs avec 3 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        pairs.add(new Pair(5,6));
        //Journée 2
        pairs.add(new Pair(7,1));
        pairs.add(new Pair(8,5));
        pairs.add(new Pair(2,3));
        //Journée 3
        pairs.add(new Pair(4,7));
        pairs.add(new Pair(1,8));
        pairs.add(new Pair(6,3));
        //Journée 4
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(5,7));
        pairs.add(new Pair(8,6));
        //Journée 5
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(4,5));
        pairs.add(new Pair(7,2));
        //Journée 6
        pairs.add(new Pair(6,2));
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(8,3));
        //Journée 7
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(2,8));
        pairs.add(new Pair(6,7));
        //Journée 8
        pairs.add(new Pair(3,5));
        pairs.add(new Pair(4,6));
        pairs.add(new Pair(7,8));
        //Journée 9
        pairs.add(new Pair(1,6));
        pairs.add(new Pair(2,5));
        pairs.add(new Pair(4,8));
        //Journée 10
        pairs.add(new Pair(3,7));
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(2,6));
        //Journée 11
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(5,4));
        pairs.add(new Pair(8,7));
        //Journée 12
        pairs.add(new Pair(3,2));
        pairs.add(new Pair(6,8));
        pairs.add(new Pair(7,5));
        //Journée 13
        pairs.add(new Pair(8,1));
        pairs.add(new Pair(5,2));
        pairs.add(new Pair(7,4));
        //Journée 14
        pairs.add(new Pair(6,1));
        pairs.add(new Pair(5,3));
        pairs.add(new Pair(2,7));
        //Journée 15
        pairs.add(new Pair(6,5));
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(3,8));
        //Journée 16
        pairs.add(new Pair(1,7));
        pairs.add(new Pair(8,4));
        pairs.add(new Pair(3,6));
        //Journée 17
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        pairs.add(new Pair(5,8));
        //Journée 18
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(8,2));
        pairs.add(new Pair(7,6));
        //Journée 19
        pairs.add(new Pair(6,4));
        pairs.add(new Pair(7,3));
        return pairs;
    }

    //Calendrier pour 8 players avec 4 TV
    public List<Pair> calendar8Player4TV(){
        List<Pair> pairs = new ArrayList<Pair>();

        //Calendrier pour 8 joueurs avec 3 TV
        //Journée 1
        pairs.add(new Pair(1,2));
        pairs.add(new Pair(3,4));
        pairs.add(new Pair(5,6));
        pairs.add(new Pair(7,8));
        //Journée 2
        pairs.add(new Pair(8,1));
        pairs.add(new Pair(2,3));
        pairs.add(new Pair(4,5));
        pairs.add(new Pair(6,7));
        //Journée 3
        pairs.add(new Pair(1,3));
        pairs.add(new Pair(2,4));
        pairs.add(new Pair(5,7));
        pairs.add(new Pair(6,8));
        //Journée 4
        pairs.add(new Pair(7,1));
        pairs.add(new Pair(8,2));
        pairs.add(new Pair(3,5));
        pairs.add(new Pair(4,6));
        //Journée 5
        pairs.add(new Pair(1,4));
        pairs.add(new Pair(5,8));
        pairs.add(new Pair(2,6));
        pairs.add(new Pair(3,7));
        //Journée 6
        pairs.add(new Pair(6,1));
        pairs.add(new Pair(2,5));
        pairs.add(new Pair(8,3));
        pairs.add(new Pair(4,7));
        //Journée 7
        pairs.add(new Pair(1,5));
        pairs.add(new Pair(7,2));
        pairs.add(new Pair(3,6));
        pairs.add(new Pair(4,8));
        //Journée 8
        pairs.add(new Pair(2,1));
        pairs.add(new Pair(4,3));
        pairs.add(new Pair(6,5));
        pairs.add(new Pair(8,7));
        //Journée 9
        pairs.add(new Pair(1,8));
        pairs.add(new Pair(3,2));
        pairs.add(new Pair(5,4));
        pairs.add(new Pair(7,6));
        //Journée 10
        pairs.add(new Pair(3,1));
        pairs.add(new Pair(4,2));
        pairs.add(new Pair(7,5));
        pairs.add(new Pair(8,6));
        //Journée 11
        pairs.add(new Pair(1,7));
        pairs.add(new Pair(2,8));
        pairs.add(new Pair(5,3));
        pairs.add(new Pair(6,4));
        //Journée 12
        pairs.add(new Pair(4,1));
        pairs.add(new Pair(8,5));
        pairs.add(new Pair(6,2));
        pairs.add(new Pair(7,3));
        //Journée 13
        pairs.add(new Pair(1,6));
        pairs.add(new Pair(5,2));
        pairs.add(new Pair(3,8));
        pairs.add(new Pair(7,4));
        //Journée 14
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(2,7));
        pairs.add(new Pair(6,3));
        pairs.add(new Pair(8,4));

        return pairs;
    }

    //Affiche le Calendrier
    public void calendarDisplay(){
        int compteur = 0;
        System.out.println();
        System.out.println("******************** Calendrier ********************");
        System.out.println();
        for(MatchWeek week : this.calendar){
            compteur++;
            System.out.println("*** Journée " + compteur + " ***");
            for(Match match : week.getWeek()){
                System.out.println(match.getHomePlayer().getPlayerNumber() + " - " + match.getVisitorPlayer().getPlayerNumber());
            }
            for(Player player : week.getWaitingPlayer()){
                System.out.print(player.getPlayerNumber() + " ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("******************** Fin Calendrier ********************");

        for(Player player : this.players){
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());
        }
    }
}

