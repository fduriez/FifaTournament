import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    private JTable generalRankingTable;
    private JTable matchRankingTable;
    private JTable calendarTable;
    private JButton addScoreButton = new JButton("Ajouter Score");
    private JButton deleteScoreButton = new JButton("Supprimer Score");

    private Calendar calendar = new Calendar();
    private Ranking ranking = new Ranking();

    private List<JButton> betsButton = new ArrayList<>();

    private DecimalFormat decimalFormat = new DecimalFormat();

    public GameWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TOURNOI FIFA");
        //this.setSize(1000, 400);
        this.setSize(1300, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Définit le nombre de décimal affiché
        this.decimalFormat.setMaximumFractionDigits(2);

        /********************************/
        /** Tableau Classement général **/
        /********************************/

        //Création du Tableau d'affichage du classement général
        Object[][] data = {};
        String[] titleRanking = {"Joueur","Equipe","P Total","P Match","P Pari"};
        this.generalRankingTable = new JTable(new DefaultTableModel(data, titleRanking)) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.generalRankingTable.setFillsViewportHeight(true);
        this.generalRankingTable.setRowHeight(20);
        this.generalRankingTable.getColumn("Joueur").setMaxWidth(90);
        this.generalRankingTable.getColumn("Equipe").setMaxWidth(90);
        this.generalRankingTable.getColumn("P Total").setMaxWidth(60);
        this.generalRankingTable.getColumn("P Match").setMaxWidth(60);
        this.generalRankingTable.getColumn("P Pari").setMaxWidth(60);
        JScrollPane scGeneralRanking = new JScrollPane(this.generalRankingTable);
        scGeneralRanking.setPreferredSize(new Dimension(360,Param.NB_PLAYER*this.generalRankingTable.getRowHeight()+23));
        scGeneralRanking.setMaximumSize(new Dimension(360,Param.NB_PLAYER*this.generalRankingTable.getRowHeight()+23));
        scGeneralRanking.setMinimumSize(new Dimension(360,Param.NB_PLAYER*this.generalRankingTable.getRowHeight()+23));

        //Empêche la modification de la taille du calendrier et la sélection des cellules
        this.generalRankingTable.setCellSelectionEnabled(false);
        this.generalRankingTable.setRequestFocusEnabled(false);
        this.generalRankingTable.getTableHeader().setReorderingAllowed(false);
        this.generalRankingTable.getTableHeader().setResizingAllowed(false);

        /**********************************/
        /** Tableau Classement par match **/
        /**********************************/

        //Création du Tableau d'affichage du classement des matchs
        titleRanking = new String[]{"Joueur", "Points", "J", "G", "N", "P", "Bp", "Bc", "Diff"};
        this.matchRankingTable = new JTable(new DefaultTableModel(data, titleRanking)) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.matchRankingTable.setFillsViewportHeight(true);
        this.matchRankingTable.setRowHeight(20);
        this.matchRankingTable.getColumn("Joueur").setMaxWidth(90);
        this.matchRankingTable.getColumn("Points").setMaxWidth(50);
        this.matchRankingTable.getColumn("J").setMaxWidth(40);
        this.matchRankingTable.getColumn("G").setMaxWidth(40);
        this.matchRankingTable.getColumn("N").setMaxWidth(40);
        this.matchRankingTable.getColumn("P").setMaxWidth(40);
        this.matchRankingTable.getColumn("Bp").setMaxWidth(40);
        this.matchRankingTable.getColumn("Bc").setMaxWidth(40);
        this.matchRankingTable.getColumn("Diff").setMaxWidth(40);
        JScrollPane scMatchRanking = new JScrollPane(this.matchRankingTable);
        scMatchRanking.setPreferredSize(new Dimension(420,Param.NB_PLAYER*this.matchRankingTable.getRowHeight()+23));
        scMatchRanking.setMaximumSize(new Dimension(420,Param.NB_PLAYER*this.matchRankingTable.getRowHeight()+23));
        scMatchRanking.setMinimumSize(new Dimension(420,Param.NB_PLAYER*this.matchRankingTable.getRowHeight()+23));

        //Empêche la modification de la taille du calendrier et la sélection des cellules
        this.matchRankingTable.setCellSelectionEnabled(false);
        this.matchRankingTable.setRequestFocusEnabled(false);
        this.matchRankingTable.getTableHeader().setReorderingAllowed(false);
        this.matchRankingTable.getTableHeader().setResizingAllowed(false);

        /************************/
        /** Tableau Calendrier **/
        /************************/

        //Création du Tableau d'affichage du calendrier
        data = initDataCalendarTable();
        String[] titleCalendar = {"Equipe Dom","Score","Equipe Ext","Pari Gagnant","Parieur"};
        this.calendarTable = new JTable(data, titleCalendar) {
            Border weekBorder = new MatteBorder(2, 0, 0, 0, Color.BLUE);
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                JComponent jc = (JComponent)c;
                jc.setBorder(row % Param.NB_TV == 0 ? weekBorder : null);
                return c;
            }
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.calendarTable.setFillsViewportHeight(true);
        this.calendarTable.setRowHeight(18);
        this.calendarTable.getColumn("Equipe Dom").setMaxWidth(100);
        this.calendarTable.getColumn("Score").setMaxWidth(70);
        this.calendarTable.getColumn("Equipe Ext").setMaxWidth(100);
        this.calendarTable.getColumn("Pari Gagnant").setMaxWidth(100);
        this.calendarTable.getColumn("Parieur").setMaxWidth(100);
        JScrollPane scCalendar = new JScrollPane(this.calendarTable);
        scCalendar.setPreferredSize(new Dimension(470,325));
        scCalendar.setMaximumSize(new Dimension(470,325));
        scCalendar.setMinimumSize(new Dimension(470,325));

        //Empêche la modification de la taille du calendrier
        this.calendarTable.getTableHeader().setReorderingAllowed(false);
        this.calendarTable.getTableHeader().setResizingAllowed(false);

        //Organisation des données dans le calendrier
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        this.calendarTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        this.calendarTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        /**************************************************/
        /** Organisation du panel des boutons Calendrier **/
        /**************************************************/

        //Panel pour les boutons d'ajout et de suppression de score
        JPanel panelButtonCalendar = new JPanel();
        this.addScoreButton.setMinimumSize(new Dimension(230,40));
        this.addScoreButton.setPreferredSize(new Dimension(230,40));
        this.deleteScoreButton.setMinimumSize(new Dimension(230,40));
        this.deleteScoreButton.setPreferredSize(new Dimension(230,40));
        panelButtonCalendar.add(this.addScoreButton);
        panelButtonCalendar.add(this.deleteScoreButton);

        /**************************************/
        /** Organisation du panel Calendrier **/
        /**************************************/

        TitledBorder titledBorderCalendar = new TitledBorder("Calendrier");
        titledBorderCalendar.setTitleFont(Param.fontTitlePanel);

        //Panel du Calendrier et de ses boutons
        JPanel panelCalendar = new JPanel();
        panelCalendar.setBorder(new TitledBorder("test"));
        //panelCalendar.setMaximumSize(new Dimension(800,500));
        panelCalendar.setPreferredSize(new Dimension(500,400));
        panelCalendar.setLayout(new BoxLayout(panelCalendar, BoxLayout.PAGE_AXIS));
        panelCalendar.add(scCalendar);
        panelCalendar.add(panelButtonCalendar);
        panelCalendar.setBorder(titledBorderCalendar);

        /***********************************************/
        /** Organisation du panel des boutons de pari **/
        /***********************************************/

        TitledBorder titledBorderBetsButton = new TitledBorder("Boutons des paris");
        titledBorderBetsButton.setTitleFont(Param.fontTitlePanel);

        this.initBetsButton();
        JPanel panelBetsButtons = new JPanel();
        panelBetsButtons.setLayout(new BoxLayout(panelBetsButtons, BoxLayout.PAGE_AXIS));
        panelBetsButtons.setPreferredSize(new Dimension(150,50*Param.NB_PLAYER));
        for (JButton button : this.betsButton){
            button.setMaximumSize(new Dimension(150,50));
            button.setMinimumSize(new Dimension(150,50));
            panelBetsButtons.add(button);
        }
        panelBetsButtons.setBorder(titledBorderBetsButton);

        /*****************************************/
        /** Organisation du panel de classement **/
        /*****************************************/

        TitledBorder titledBorderGeneralRanking = new TitledBorder("Classement Général");
        TitledBorder titledBorderMatchRanking = new TitledBorder("Classement des Matchs");
        titledBorderGeneralRanking.setTitleFont(Param.fontTitlePanel);
        titledBorderMatchRanking.setTitleFont(Param.fontTitlePanel);

        JPanel panelGeneralRanking = new JPanel();
        panelGeneralRanking.add(scGeneralRanking);
        panelGeneralRanking.setPreferredSize(new Dimension(450,25*Param.NB_PLAYER + 25));
        panelGeneralRanking.setBorder(titledBorderGeneralRanking);

        JPanel panelMatchRanking = new JPanel();
        panelMatchRanking.add(scMatchRanking);
        panelMatchRanking.setPreferredSize(new Dimension(450,25*Param.NB_PLAYER + 25));
        panelMatchRanking.setBorder(titledBorderMatchRanking);

        JPanel panelRanking = new JPanel();
        panelRanking.setLayout(new BoxLayout(panelRanking, BoxLayout.PAGE_AXIS));
        //panelRanking.setPreferredSize(new Dimension(500,400));
        panelRanking.add(panelGeneralRanking);
        panelRanking.add(panelMatchRanking);

        /*************************************/
        /** Organisation du panel principal **/
        /*************************************/

        TitledBorder titledBorderMainPanel = new TitledBorder("Fenetre");

        TitledBorder titledBorderSpacePanel = new TitledBorder("Escape");
        JPanel panelSpace1 = new JPanel();
        panelSpace1.setPreferredSize(new Dimension(50,50));
        //panelSpace1.setBorder(titledBorderSpacePanel);
        JPanel panelSpace2 = new JPanel();
        panelSpace2.setPreferredSize(new Dimension(50,50));
        //panelSpace2.setBorder(titledBorderSpacePanel);

        JPanel mainPanel = new JPanel();
        mainPanel.add(panelCalendar);
        mainPanel.add(panelSpace1);
        mainPanel.add(panelBetsButtons);
        mainPanel.add(panelSpace2);
        mainPanel.add(panelRanking);
        mainPanel.setBorder(titledBorderMainPanel);
        this.getContentPane().add(mainPanel);
        this.setVisible(true);

        //initialise le tableau de classement
        updateRankingTable();

        this.calendar.display();
        this.ableBetsButton();

        //Fonction déclanché par les boutons de pari
        for(JButton button : this.betsButton){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Récupération des infos
                    Player player = Param.getPlayerByName(button.getText());
                    Match match = findMatchToBet(player);
                    String homeTeam = match.getHomePlayer().getName();
                    String visitorTeam = match.getVisitorPlayer().getName();

                    //Fenêtre de pari
                    BetWindow betWindow = new BetWindow(null,"Pari " + player.getName(),true, homeTeam, visitorTeam);

                    //Si clic sur "Valider" -> récupération du score
                    if(betWindow.sendData()){
                        String score = betWindow.getScore();

                        //AjouterScore dans Pari
                        match.findBetFor(player).setScore(score);

                        //Rend non cliquable le bouton si tous les pari du joueur sont fait sur cette journée
                        if(calendar.getCurrentWeek().isBetsDoneFor(player)) button.setEnabled(false);

                        if(calendar.getCurrentWeek().isAllBetsDone()) {
                            //Boîte du message d'information
                            JOptionPane jop = new JOptionPane();
                            jop.showMessageDialog(null, "Lancez les matchs!!!", "C'est GOOD", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
        }

        //Fonction déclanché par le addScoreButton
        ActionListener addScoreAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Week week = new Week();
                Match match = new Match();
                String homeTeam = "home";
                String visitorTeam = "visitor";

                //SI pas de match sélectionner -> on prend le premier match non joué
                if (calendarTable.getSelectedRow() == -1) {
                    week = calendar.getCurrentWeek();
                    match = calendar.getCurrentMatch();
                    homeTeam = match.getHomePlayer().getName();
                    visitorTeam = match.getVisitorPlayer().getName();
                }
                //SINON on prend le match sélectionné
                else {
                    homeTeam = calendarTable.getValueAt(calendarTable.getSelectedRow(), 0).toString();
                    visitorTeam = calendarTable.getValueAt(calendarTable.getSelectedRow(), 2).toString();
                    match = calendar.getMatchWith(Param.getPlayerByName(homeTeam), Param.getPlayerByName(visitorTeam));
                    week = calendar.getWeekByMatch(match);
                }

                if(week.getWeekNumber() > calendar.getCurrentWeek().getWeekNumber()){
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Pas la moment pour ce match!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else if((week.getWeekNumber() == calendar.getCurrentWeek().getWeekNumber()) && (!calendar.getCurrentWeek().isAllBetsDone())){
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Tous les paris n'ont pas été fait!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //Desélecctionne la ligne du calendrier
                    calendarTable.clearSelection();

                    ScoreWindow scoreWindow = new ScoreWindow(null, "Score Match", true, homeTeam, visitorTeam);
                    if (scoreWindow.sendData()) {
                        String score = scoreWindow.getScore();
                        match.setResult(score);
                        addScoreToTable(match, score);
                        ranking.updateRanking();
                        updateRankingTable();
                        //Fin de la journée -> on passe à la suivante
                        if ((week.isAllMatchPlayed()) && (week.getWeekNumber() != calendar.getWeeks().size())) {
                            ableBetsButton();
                            JOptionPane jop = new JOptionPane();
                            jop.showMessageDialog(null, "Journée Terminé -> Les paris sont ouverts", "Journée Fini", JOptionPane.INFORMATION_MESSAGE);
                        }
                        //Fin du tournoi -> Affichage du vainqueur
                        else if((week.isAllMatchPlayed()) && (week.getWeekNumber() == calendar.getWeeks().size()) ){
                            JOptionPane jop = new JOptionPane();
                            jop.showMessageDialog(null, "Et la victoire pour " + ranking.getGeneralRanking().get(0).getName(), "Journée Fini", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        };

        //Fonction déclanché par le deleteScoreButton
        ActionListener deleteScoreAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(calendarTable.getSelectedRow() != -1){
                    String homeTeam = calendarTable.getValueAt(calendarTable.getSelectedRow(),0).toString();
                    String visitorTeam = calendarTable.getValueAt(calendarTable.getSelectedRow(),2).toString();
                    Match match = calendar.getMatchWith(Param.getPlayerByName(homeTeam),Param.getPlayerByName(visitorTeam));
                    addScoreToTable(match,"");
                    match.setResult("");
                    ranking.updateRanking();
                    updateRankingTable();
                }
                else {
                    //Boîte du message d'erreur
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Selectionner un match", "Erreur", JOptionPane.WARNING_MESSAGE);
                }
                calendarTable.clearSelection();
            }
        };

        //liaison entre le bouton addScore et sa fonction
        this.addScoreButton.addActionListener(addScoreAction);

        //liaison entre le bouton deleteScore et sa fonction
        this.deleteScoreButton.addActionListener(deleteScoreAction);
    }

    //Initialise le tableau de calendrier
    public Object[][] initDataCalendarTable() {
        Object[][] data = new Object[Param.NB_MATCH][2];
        String homeTeam;
        String visitorTeam;
        int compteur = 0;
        for (Week week : this.calendar.getWeeks()) {
            for (Match match : week.getMatchs()) {
                homeTeam = match.getHomePlayer().getName();
                visitorTeam = match.getVisitorPlayer().getName();
                data[compteur] = new Object[]{homeTeam, "", visitorTeam,"",""};
                compteur++;
            }
        }
        return data;
    }

    //Initialisation du Calendrier
    public void initCalendarFromAlgo() {
        /*int cursor = 0;

        List<Pair> allHomeMatchs = new ArrayList<>();
        for (int team1 = 1; team1 <= Param.PLAYERS.size(); team1++) {
            cursor = 0;
            for (int team2 = team1 + 1; team2 <= Param.PLAYERS.size(); team2++) {
                if (team1 != team2) {
                    if (cursor == Param.PLAYERS.size() / 2) {
                        allHomeMatchs.add(new Pair(team2, team1));
                    } else {
                        allHomeMatchs.add(new Pair(team1, team2));
                        cursor++;
                    }
                }
            }
        }

        int numberHomeMatchs = (allHomeMatchs.size() / 2);

        while (this.calendar.size() != numberHomeMatchs) {
            this.calendar.add(new MatchWeek());
            //System.out.println("Taille calendrier : " + this.calendar.size());
            for (Pair pair1 : new ArrayList<Pair>(allHomeMatchs)) {
                Match match1 = new Match(getPlayerByNumber((Integer) pair1.getKey()), getPlayerByNumber((Integer) pair1.getValue()));
                for (Pair pair2 : new ArrayList<Pair>(allHomeMatchs)) {
                    Match match2 = new Match(getPlayerByNumber((Integer) pair2.getKey()), getPlayerByNumber((Integer) pair2.getValue()));
                    //!!!CONDITIONS!!! 4 Equipes différentes &&
                    if ((match1.getHomePlayer() != match2.getHomePlayer()) && (match1.getHomePlayer() != match2.getVisitorPlayer()) &&
                            (match1.getVisitorPlayer() != match2.getHomePlayer()) && (match1.getVisitorPlayer() != match2.getVisitorPlayer()) &&
                            (this.calendar.get(this.calendar.size() - 1).getWeek().isEmpty())) {
                        List week = new ArrayList<>();
                        week.add(match1);
                        week.add(match2);
                        List<Player> waiters = new ArrayList<>(getWaiters(week));
                        if (this.calendar.size() < 2) {
                            this.calendar.get(this.calendar.size() - 1).setWeek(week);

                            this.calendar.get(this.calendar.size() - 1).setWaitingPlayer(waiters);

                            allHomeMatchs.remove(pair1);
                            allHomeMatchs.remove(pair2);
                        } else {
                            //int allWaitersMustPlay = 0;
                            int allMandatoryWaitersWait = 0;
                            List<Player> mandatoryWaiters = new ArrayList<>(getMandatoryWaiters());
                            List<Player> possibleWaiters = new ArrayList<>(getPossibleWaiters(mandatoryWaiters));

                            //Vérifie si les joueurs devant attendre, attendent
                            for (Player mandatoryWaitersPlayer : mandatoryWaiters) {
                                for (Player waitersPlayer : new ArrayList<>(waiters)) {
                                    if (mandatoryWaitersPlayer.equals(waitersPlayer)) {
                                        allMandatoryWaitersWait++;
                                        waiters.remove(waitersPlayer);
                                    }
                                }
                            }

                            if (allMandatoryWaitersWait == mandatoryWaiters.size()) {
                                for (Player waitersPlayer : new ArrayList<>(waiters)) {
                                    for (Player possibleWaitersPlayer : possibleWaiters) {
                                        if (waitersPlayer.equals(possibleWaitersPlayer)) waiters.remove(waitersPlayer);
                                    }
                                }
                            }

                            if (waiters.isEmpty()) {
                                waiters = getWaiters(week);
                                this.calendar.get(this.calendar.size() - 1).setWeek(week);

                                this.calendar.get(this.calendar.size() - 1).setWaitingPlayer(waiters);

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
                            }
                        }
                    }
                }
            }
        }
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

        for(Player player : this.players){
            System.out.println("Player : " + player.getPlayerNumber());
            System.out.println("nombre de matche a domicile : " + player.getNumberHomeMatch());
            System.out.println("nombre de matche a l'exterieur : " + player.getNumberAwayMatch());
        }

        int day = 0;
        for (MatchWeek week : this.calendar) {
            day++;
            System.out.println();
            System.out.println("*** Journée " + day + " ***");
            for (Match match : week.getWeek()) {
                System.out.println(match.getHomePlayer().getPlayerNumber() + " - " + match.getVisitorPlayer().getPlayerNumber());
            }
            System.out.println("Waiters :");
            for (Player player : week.getWaitingPlayer()) {
                System.out.print(player.getPlayerNumber() + " ");
            }
            System.out.println();
        }
        System.out.println(allHomeMatchs);*/
    }

    //Retourne les waiters possible
    public void getPossibleWaiters(List<Player> mandatoryWaiters) {
/*
        int minimum = 1000;
        List<Player> possibleWaiters = new ArrayList<>();
        List<Player> previousWaiters = new ArrayList<>(this.calendar.getWeeks(this.calendar.size() - 2).getWaitingPlayer());

        for (Player player : possibleWaiters) {
            if (player.getNumberWaiting() < minimum) minimum = player.getNumberWaiting();
        }

        while ((possibleWaiters.size() + mandatoryWaiters.size()) < Param.PLAYERS.size() - (Param.NB_TV * 2)) {
            possibleWaiters = new ArrayList<>(Param.PLAYERS);
            for (Player previousWaiter : previousWaiters) {
                possibleWaiters.remove(previousWaiter);
            }

            for (Player previousWaiter : mandatoryWaiters) {
                possibleWaiters.remove(previousWaiter);
            }

            //System.out.println("mini : " + minimum);
            for (Player player : new ArrayList<>(possibleWaiters)) {
                //System.out.println(player.getPlayerNumber());
                if (player.getNumberWaiting() > minimum) possibleWaiters.remove(player);
            }
            minimum++;
        }
        return possibleWaiters;*/
    }

    public List<Player> getMandatoryWaiters() {
        int minimum = 1000;
        List<Player> mandatoryWaiters = new ArrayList<>(Param.PLAYERS);

        for (Player player : mandatoryWaiters) {
            if (player.getNumberWaiting() < minimum) minimum = player.getNumberWaiting();
        }

        for (Player player : new ArrayList<>(mandatoryWaiters)) {
            if (player.getNumberWaiting() > minimum) mandatoryWaiters.remove(player);
        }

        if (mandatoryWaiters.size() > Param.PLAYERS.size() - (Param.NB_TV * 2)) mandatoryWaiters = new ArrayList<>();

        return mandatoryWaiters;
    }

    //Ajoute le score du match dans la table calendrier
    public void addScoreToTable(Match match, String score){
        //Ajout du score du match
        int row = findRowByMatch(match);
        this.calendarTable.setValueAt(score,row,1);

        //Ajout des gagnants du pari
        String betWinners = "";
        String scoreBetWinner = "";
        for(Player player : match.getBetWinners()){
            if(betWinners != "") betWinners += ",";
            if(scoreBetWinner != "") scoreBetWinner += ",";
            betWinners += player.getName();
            scoreBetWinner += match.findBetFor(player).getScore();
        }
        this.calendarTable.setValueAt(scoreBetWinner,row,3);
        this.calendarTable.setValueAt(betWinners,row,4);
    }

    //MAJ de la table classement
    public void updateRankingTable(){
        DefaultTableModel model = (DefaultTableModel) this.generalRankingTable.getModel();
        int rowCount = model.getRowCount();
        for(int row=rowCount-1; row>=0; row--){
            model.removeRow(row);
        }
        for(Player player : this.ranking.getGeneralRanking()) {
            model.addRow(new Object[]{player.getName(),player.getTeam(),this.decimalFormat.format(player.getTotalPoints()),player.getMatchPoints(),this.decimalFormat.format(player.getBetsPoints())});
        }

        model = (DefaultTableModel) this.matchRankingTable.getModel();
        rowCount = model.getRowCount();
        for(int row=rowCount-1; row>=0; row--){
            model.removeRow(row);
        }
        for(Player player : this.ranking.getMatchRanking()) {
            model.addRow(new Object[]{player.getName(),player.getMatchPoints(),player.getNumberMatchPlayed(),player.getNumberVictory(),player.getNumberDraw(),player.getNumberDefeat(),player.getGoalsScored(),player.getGoalsTaken(),player.getGoalDifference()});
        }
    }

    //Trouve la ligne associé à un match dans la table calendrier
    private int findRowByMatch(Match match1){
        int row = 0;
        for (Week week : this.calendar.getWeeks()){
            for (Match match2 : week.getMatchs()){
                if(match1 == match2) return row;
                row++;
            }
        }
        return row;
    }

    //Initialise la liste des boutons de pari en fonction des joueurs
    private void initBetsButton(){
        for (Player player : Param.PLAYERS){
            this.betsButton.add(new JButton(player.getName()));
        }
    }

    //Rend cliquable ou non les boutons de pari en fonction du joueur et des paris restant sur la journée
    private void ableBetsButton(){
        for(JButton button : this.betsButton){
            button.setEnabled(false);
        }
        for(Player player : Param.PLAYERS){
            if(!this.calendar.getCurrentWeek().isBetsDoneFor(player)) getBetsButtonByName(player.getName()).setEnabled(true);
        }
    }

    //Renvoie le bouton des paris associé au nom envoyé en paramètre
    private JButton getBetsButtonByName(String name){
        for(JButton button : this.betsButton){
            if(button.getText() == name) return button;
        }
        return null;
    }

    //Renvoie un match à parier sur cette journée en fonction du joueur en paramètre
    private Match findMatchToBet(Player player){
        for(Match match : this.calendar.getCurrentWeek().getMatchs()){
            for(Bet bet : match.getBets()){
                if((bet.getPlayer() == player) && (!bet.isDone())) return match;
            }
        }
        return null;
    }
}

