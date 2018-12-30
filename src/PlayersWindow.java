import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayersWindow extends JFrame {
    private JTextField nameTextField = new JTextField();
    private JComboBox teamComboBox = new JComboBox();
    private JButton addButton = new JButton("Ajouter");
    private JButton deleteButton = new JButton("Supprimer");
    private JButton finishButton = new JButton("Valider");
    private JTable playersTable;

    public PlayersWindow() {

        /*****************************/
        /** Paramètre de la fenetre **/
        /*****************************/

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NOM DES JOUEURS");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.finishButton.setEnabled(false);

        /***********************************/
        /** Table d'affichage des joueurs **/
        /***********************************/

        //Création d'une table de 2 colonnes vide
        String[] title = {"Name","Team"};
        Object[][] data = {};
        this.playersTable = new JTable(new DefaultTableModel(data, title)){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.playersTable.setFillsViewportHeight(true);
        this.playersTable.setRowHeight(18);

        this.playersTable.getTableHeader().setReorderingAllowed(false);
        this.playersTable.getTableHeader().setResizingAllowed(false);

        /***********************/
        /** Liste des Equipes **/
        /***********************/

        List<String> teamNames = new ArrayList<String>();
        //teamNames.add("Premier League");
        teamNames.add("Arsenal");
        teamNames.add("Chelsea");
        teamNames.add("Liverpool");
        teamNames.add("Manchester City");
        teamNames.add("Manchester United");
        teamNames.add("Tottenham");

        //teamNames.add("Ligue 1");
        teamNames.add("Lyon");
        teamNames.add("OM");
        teamNames.add("PSG");

        //teamNames.add("Liga");
        teamNames.add("Atletico Madrid");
        teamNames.add("Barcelone");
        teamNames.add("Real Madrid");
        teamNames.add("Seville");

        //teamNames.add("Bundesliga");
        teamNames.add("Bayern Munich");
        teamNames.add("Borussia Dortmund");

        //teamNames.add("Serie A");
        teamNames.add("AC Milan");
        teamNames.add("As Roma");
        teamNames.add("Inter Milan");
        teamNames.add("Juventus");
        teamNames.add("Naples");

        //teamNames.add("Equipe Nationale");
        teamNames.add("Allemagne");
        teamNames.add("Angleterre");
        teamNames.add("Argentine");
        teamNames.add("Belgique");
        teamNames.add("Bresil");
        teamNames.add("Espagne");
        teamNames.add("France");
        teamNames.add("Italie");

        //Ajout des équipes dans la ComboBox
        this.teamComboBox = new JComboBox(teamNames.toArray(new String[teamNames.size()]));
        this.teamComboBox.setRenderer(new TeamListRenderer());

        /************************************/
        /** Gestion des polices d'écriture **/
        /************************************/

        //Text de fond du nameTextField
        TextPrompt nameTextPrompt = new TextPrompt("Player Name", this.nameTextField);
        nameTextPrompt.setForeground( Color.DARK_GRAY );
        nameTextPrompt.changeAlpha(0.5f);
        nameTextPrompt.changeStyle(Font.BOLD + Font.ITALIC);

        //Gestion des polices d'écriture
        Font fontTable = new Font("rockwell", Font.BOLD,15);
        this.playersTable.setFont(fontTable);
        this.playersTable.getTableHeader().setFont(fontTable);
        Font fontButton = new Font("showcard gothic", Font.BOLD,15);
        this.addButton.setFont(fontButton);
        this.deleteButton.setFont(fontButton);
        this.finishButton.setFont(fontButton);
        this.nameTextField.setFont(fontTable);
        this.nameTextField.setHorizontalAlignment(JTextField.CENTER);

        /********************************/
        /** Gestion du panel de gauche **/
        /********************************/

        //Panel des paramètres du joueur
        JPanel playerParamPanel = new JPanel();
        GridLayout g111 = new GridLayout(2, 1);
        g111.setVgap(10);
        playerParamPanel.setLayout(g111);
        playerParamPanel.add(this.nameTextField);
        playerParamPanel.add(this.teamComboBox);

        //Panel de gauche
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createEmptyBorder(00,00,00,20));
        leftPanel.setMaximumSize(new Dimension( 200, 300 ));
        leftPanel.setMinimumSize(new Dimension( 200, 300 ));
        GridLayout g11 = new GridLayout(3, 1);
        g11.setVgap(15);
        leftPanel.setLayout(g11);
        leftPanel.add(playerParamPanel);
        leftPanel.add(this.addButton);
        leftPanel.add(this.deleteButton);

        /********************************/
        /** Gestion du panel de droite **/
        /********************************/

        //Positionnement à la main
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(00,20,00,00));
        rightPanel.setMaximumSize(new Dimension( 300, 300 ));
        JScrollPane sc = new JScrollPane(this.playersTable);
        sc.setBounds(20,00,230,150);
        this.finishButton.setBounds(20,170,230,50);
        rightPanel.add(sc);
        rightPanel.add(this.finishButton);

        /*********************/
        /** Panel principal **/
        /*********************/

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setSize(500,300);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        this.getContentPane().add(mainPanel);
        this.setVisible(true);

        /************************/
        /** Action des Boutons **/
        /************************/

        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = (String)teamComboBox.getSelectedItem();
                if((!nameTextField.getText().isEmpty()) && (!teamName.isEmpty())) {
                    String name = nameTextField.getText();
                    Param.PLAYERS.add(new Player(name,teamName));

                    ((DefaultTableModel) playersTable.getModel()).addRow(new Object[]{name,teamName});

                    if(Param.PLAYERS.size() == Param.NB_PLAYER) finishButton.setEnabled(true);
                    else finishButton.setEnabled(false);

                    nameTextField.setText("");
                    nameTextField.requestFocus();
                }
            }
        });
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playersTable.getSelectedRow() >= 0) {
                    String value = playersTable.getValueAt(playersTable.getSelectedRow(),0).toString();

                    int index = 0;
                    for(int i = 0; i < Param.PLAYERS.size(); i++){
                        if(Param.PLAYERS.get(i).getName().equals(value)) index = i;
                    }
                    Param.PLAYERS.remove(index);

                    if(Param.PLAYERS.size() == Param.NB_PLAYER) finishButton.setEnabled(true);
                    else finishButton.setEnabled(false);

                    ((DefaultTableModel)playersTable.getModel()).removeRow(playersTable.getSelectedRow());
                    nameTextField.requestFocus();
                }
            }
        });
        this.finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("*** Participants ***");
                for(Player player : Param.PLAYERS) {
                    System.out.println(player.getName().toString());
                }
                System.out.println("nombre de Participant : " + Param.NB_PLAYER);
                System.out.println("nombre de TV : " + Param.NB_TV);

                //Initialisation des données
                Param.Lottery();
                Calendar.initCalendar();
                Calendar.display();
                Ranking.initRanking();

                dispose();
                GameWindow gameWindow = new GameWindow();
            }
        });
    }
}