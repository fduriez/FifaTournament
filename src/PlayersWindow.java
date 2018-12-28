import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlayersWindow extends JFrame implements ActionListener{
    private JTextField nameTextField = new JTextField();
    private JComboBox teamComboBox = new JComboBox();
    private JButton addButton = new JButton("Ajouter");
    private JButton deleteButton = new JButton("Supprimer");
    private JButton finishButton = new JButton("Valider");
    private JTable table;

    public PlayersWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NOM DES PARTICIPANTS");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.finishButton.setEnabled(false);

        //Création d'une table d'1 colonne vide
        String[] title = {"Name","Team"};
        Object[][] data = {};
        this.table = new JTable(new DefaultTableModel(data, title)){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.setFillsViewportHeight(true);
        table.setRowHeight(18);

        //Nos classes internes écoutent nos boutons
        this.addButton.addActionListener(new AddButtonListener());
        this.deleteButton.addActionListener(new DeleteButtonListener());
        this.finishButton.addActionListener(this);

        //Text de fond du nameTextField
        TextPrompt nameTextPrompt = new TextPrompt("Player Name", this.nameTextField);
        nameTextPrompt.setForeground( Color.DARK_GRAY );
        nameTextPrompt.changeAlpha(0.5f);
        nameTextPrompt.changeStyle(Font.BOLD + Font.ITALIC);

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

        this.teamComboBox = new JComboBox(teamNames.toArray(new String[teamNames.size()]));
        this.teamComboBox.setRenderer(new ListRenderer());

        //Gestion des polices d'écriture
        Font fontTable = new Font("rockwell", Font.BOLD,15);
        table.setFont(fontTable);
        table.getTableHeader().setFont(fontTable);
        Font fontButton = new Font("showcard gothic", Font.BOLD,15);
        this.addButton.setFont(fontButton);
        this.deleteButton.setFont(fontButton);
        this.finishButton.setFont(fontButton);
        //Font fontTextField = new Font("cooper black", Font.BOLD,20);
        this.nameTextField.setFont(fontTable);
        this.nameTextField.setHorizontalAlignment(JTextField.CENTER);

        //Positionnement des composants de la fenêtre
        //Positionnement via BoxLayout
        JPanel p11 = new JPanel();
        p11.setLayout(new BoxLayout(p11, BoxLayout.PAGE_AXIS));
        p11.setBorder(BorderFactory.createEmptyBorder(00,00,00,20));
        p11.setMaximumSize(new Dimension( 200, 300 ));
        p11.setMinimumSize(new Dimension( 200, 300 ));
        GridLayout g11 = new GridLayout(3, 1);
        g11.setVgap(15);
        p11.setLayout(g11);
        JPanel p111 = new JPanel();
        GridLayout g111 = new GridLayout(2, 1);
        g111.setVgap(10);
        p111.setLayout(g111);
        p111.add(this.nameTextField);
        p111.add(this.teamComboBox);
        p11.add(p111);
        p11.add(this.addButton);
        p11.add(this.deleteButton);

        //Positionnement à la main
        JPanel p12 = new JPanel();
        p12.setLayout(null);
        p12.setBorder(BorderFactory.createEmptyBorder(00,20,00,00));
        p12.setMaximumSize(new Dimension( 300, 300 ));
        JScrollPane sc = new JScrollPane(table);
        sc.setBounds(20,00,230,150);
        this.finishButton.setBounds(20,170,230,50);
        p12.add(sc);
        p12.add(this.finishButton);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        p1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        p1.setSize(500,300);
        p1.add(p11);
        p1.add(p12);

        this.getContentPane().add(p1);
        this.setVisible(true);
    }

    //Méthode écoutant le bouton "Valider"
    public void actionPerformed(ActionEvent arg0) {
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

        this.dispose();
        GameWindow gameWindow = new GameWindow();
    }

    //Classe écoutant le bouton "Ajouter"
    class AddButtonListener implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
            String teamName = (String)teamComboBox.getSelectedItem();
            if((!nameTextField.getText().isEmpty()) && (!teamName.isEmpty())) {
                String name = nameTextField.getText();
                Param.PLAYERS.add(new Player(name,teamName));

                ((DefaultTableModel) table.getModel()).addRow(new Object[]{name,teamName});

                if(Param.PLAYERS.size() == Param.NB_PLAYER) finishButton.setEnabled(true);
                else finishButton.setEnabled(false);

                nameTextField.setText("");
                //teamComboBox.removeItem(teamName);
                nameTextField.requestFocus();
            }
        }
    }

    //Classe écoutant le bouton "Supprimer"
    class DeleteButtonListener implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
            if(table.getSelectedRow() >= 0) {
                String value = table.getValueAt(table.getSelectedRow(),0).toString();

                int index = 0;
                for(int i = 0; i < Param.PLAYERS.size(); i++){
                    if(Param.PLAYERS.get(i).getName().equals(value)) index = i;
                }
                Param.PLAYERS.remove(index);

                if(Param.PLAYERS.size() == Param.NB_PLAYER) finishButton.setEnabled(true);
                else finishButton.setEnabled(false);

                ((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
                nameTextField.requestFocus();
            }
        }
    }
}