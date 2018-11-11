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
        //Initialisation du tournoi
        //InitParameters();
        initCalendar();

        //CreateCalendar();
        //InitCalendarTable();

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
    public void initCalendar(){
        int loop = 0;

        List<Pair> allHomeMatchs = new ArrayList<>();
        for(int team1=1; team1<=this.players.size(); team1++){
            for(int team2=team1+1; team2<=this.players.size(); team2++){
                if(team1 != team2) allHomeMatchs.add(new Pair(team1,team2));
            }
        }

        int numberHomeMatchs = (allHomeMatchs.size()/2);

        while(this.calendar.size() != numberHomeMatchs){
            this.calendar.add(new MatchWeek());
            loop++;
            System.out.println("loop : " + loop);
            //System.out.println("Taille calendrier : " + this.calendar.size());
            for(Pair match1 : new ArrayList<Pair>(allHomeMatchs)){

                for(Pair match2 : new ArrayList<Pair>(allHomeMatchs)){

                    //!!!CONDITIONS!!! 4 Equipes différentes &&
                    if ((match1.getKey() != match2.getKey()) && (match1.getKey() != match2.getValue()) &&
                            (match1.getValue() != match2.getKey()) && (match1.getValue() != match2.getValue()) &&
                            (this.calendar.get(this.calendar.size()-1).getWeek().isEmpty())){
                        if(this.calendar.size()<2){
                            List week = new ArrayList<>();
                            week.add(new Match(getPlayerByNumber((Integer) match1.getKey()),getPlayerByNumber((Integer) match1.getValue())));
                            week.add(new Match(getPlayerByNumber((Integer) match2.getKey()),getPlayerByNumber((Integer) match2.getValue())));
                            this.calendar.get(this.calendar.size()-1).setWeek(week);

                            this.calendar.get(this.calendar.size()-1).setWaitingPlayer(getWaiters(week));

                            allHomeMatchs.remove(match1);
                            allHomeMatchs.remove(match2);
                        }
                        else{
                            int allWaitersMustPlay = 0;
                            List<Player> possibleWaiters = new ArrayList<>(getPossibleWaiter());
                            //System.out.println("Taille waiters : " + waiters.size());
                            for(Player player : possibleWaiters){
                                if((player.getPlayerNumber() != (int) match1.getKey()) && (player.getPlayerNumber() != (int) match1.getValue()) &&
                                        (player.getPlayerNumber() != (int) match2.getKey()) && (player.getPlayerNumber() != (int) match2.getValue())) allWaitersMustPlay++;
                            }
                            if(allWaitersMustPlay == (players.size() - (this.nbTV*2))){
                                List week = new ArrayList<>();
                                week.add(new Match(getPlayerByNumber((Integer) match1.getKey()),getPlayerByNumber((Integer) match1.getValue())));
                                week.add(new Match(getPlayerByNumber((Integer) match2.getKey()),getPlayerByNumber((Integer) match2.getValue())));
                                this.calendar.get(this.calendar.size()-1).setWeek(week);

                                this.calendar.get(this.calendar.size()-1).setWaitingPlayer(getWaiters(week));

                                allHomeMatchs.remove(match1);
                                allHomeMatchs.remove(match2);
                            }
                        }
                    }
                }
            }
        }
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

    public Player getPlayerByNumber(int number){
        for(Player player : this.players){
            if(number == player.getPlayerNumber()) return player;
        }
        return null;
    }

    public List<Player> getWaiters(List<Match> week){
        List<Player> waiters = new ArrayList<>(this.players);
        for(Player player : this.players) {
            for(Match match : week){
                if((player == match.getHomePlayer()) || (player == match.getVisitorPlayer())) waiters.remove(player);
            }
        }
        return waiters;
    }

    public List<Player> getPossibleWaiter (){
        int minimum = 1000;
        List<Player> possibleWaiters = new ArrayList<>(this.players);
        List<Player> previousWaiters = new ArrayList<>(this.calendar.get(this.calendar.size()-2).getWaitingPlayer());
        for(Player previousWaiter : previousWaiters){
            possibleWaiters.remove(previousWaiter);
        }
        for(Player player : possibleWaiters){
            if(player.getNumberWaiting() < minimum) minimum = player.getNumberWaiting();
        }
        System.out.println("mini : " + minimum);
        for(Player player : new ArrayList<>(possibleWaiters)){
            System.out.println(player.getPlayerNumber());
            if(player.getNumberWaiting() > minimum) possibleWaiters.remove(player);
        }
        return possibleWaiters;
    }
}

