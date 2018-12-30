import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

public class FirstWindow extends JFrame{
    private JComboBox nbParticipantCombo = new JComboBox();
    private JComboBox nbTVCombo = new JComboBox();
    private JButton newGameButton = new JButton("Nouvelle Partie");
    private JComboBox gameSavedCombo = new JComboBox();
    private JButton loadGameButton = new JButton("Charger Partie");

    public FirstWindow(){

        /*****************************/
        /** Paramètre de la fenetre **/
        /*****************************/

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("INITIALISATION TOURNOI FIFA");
        this.setSize(600, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        /*********************************/
        /** Initialisation des ComboBox **/
        /*********************************/

        //Nombre de participant
        String[] choixNbParticipant = {"4","5","6","7","8","9","10"};
        this.nbParticipantCombo = new JComboBox(choixNbParticipant);

        //Nombre de TV
        String[] choixNbTV = {"1","2","3","4","5"};
        this.nbTVCombo = new JComboBox(choixNbTV);

        //Récupère les sauvegardes
        File file = new File("saves");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        this.gameSavedCombo = new JComboBox(directories);

        /***************************/
        /** Panel des Sauvegardes **/
        /***************************/

        this.gameSavedCombo.setPreferredSize(new Dimension(200,30));
        this.loadGameButton.setPreferredSize(new Dimension(200,30));

        JPanel savedComboPanel = new JPanel();
        savedComboPanel.add(this.gameSavedCombo);

        JPanel loadButtonPanel = new JPanel();
        loadButtonPanel.add(this.loadGameButton);

        JPanel loadPanel = new JPanel();
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.PAGE_AXIS));
        loadPanel.setPreferredSize(new Dimension(250,120));
        loadPanel.add(savedComboPanel);
        loadPanel.add(loadButtonPanel);
        loadPanel.setBorder(new TitledBorder("Charger Partie"));

        /******************************/
        /** Panel de nouvelle partie **/
        /******************************/

        JPanel nbPlayerPanel = new JPanel();
        nbPlayerPanel.add(new JLabel("Nombre de participant"));
        nbPlayerPanel.add(this.nbParticipantCombo);

        JPanel nbTVPanel = new JPanel();
        nbTVPanel.add(new JLabel("Nombre de télé"));
        nbTVPanel.add(this.nbTVCombo);

        JPanel newGameButtonPanel = new JPanel();
        newGameButtonPanel.setPreferredSize(new Dimension(200,20));
        newGameButtonPanel.add(this.newGameButton);

        JPanel newGamePanel = new JPanel();
        newGamePanel.setLayout(new BoxLayout(newGamePanel, BoxLayout.PAGE_AXIS));
        newGamePanel.setPreferredSize(new Dimension(250,210));
        newGamePanel.add(nbPlayerPanel);
        newGamePanel.add(nbTVPanel);
        newGamePanel.add(newGameButtonPanel);
        newGamePanel.setBorder(new TitledBorder("Nouvelle Partie"));

        this.nbParticipantCombo.setPreferredSize(new Dimension(200,30));
        this.nbTVCombo.setPreferredSize(new Dimension(200,30));
        this.newGameButton.setPreferredSize(new Dimension(200,30));

        /*********************/
        /** Panel principal **/
        /*********************/

        JPanel space = new JPanel();
        space.setPreferredSize(new Dimension(20,10));

        JPanel mainPanel = new JPanel();
        mainPanel.add(newGamePanel);
        mainPanel.add(space);
        mainPanel.add(loadPanel);

        this.getContentPane().add(mainPanel);
        this.setVisible(true);

        /************************/
        /** Action des Boutons **/
        /************************/

        this.newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Param.NB_PLAYER = Integer.valueOf((String) nbParticipantCombo.getSelectedItem());
                Param.NB_TV = Integer.valueOf((String) nbTVCombo.getSelectedItem());
                Param.NB_MATCH = Param.NB_PLAYER * (Param.NB_PLAYER - 1);

                //Si trop de TV
                if(Param.NB_TV > Param.NB_PLAYER/2){
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Trop de TV par rapport aux joueurs!", "AIE", JOptionPane.ERROR_MESSAGE);
                }
                else if(Param.NB_PLAYER/2 < Param.NB_PLAYER-(Param.NB_TV*2)){
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null, "Pas assez de TV par rapport aux joueurs!", "AIE", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    dispose();
                    PlayersWindow playerWindow = new PlayersWindow();
                }
            }
        });
        this.loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pathSavedGame = (String) gameSavedCombo.getSelectedItem();
                JsonSimple.loadData(pathSavedGame);

                //Initialisation des paramètres
                Param.NB_PLAYER = Param.PLAYERS.size();
                Param.NB_TV = Calendar.weeks.get(0).getMatchs().size();
                Param.NB_MATCH = Param.NB_PLAYER * (Param.NB_PLAYER - 1);

                System.out.println("*** Load Game ***");
                Param.playersDisplay();
                System.out.println("Nombre de joueurs : " + Param.NB_PLAYER);
                System.out.println("Nombre de TV : " + Param.NB_TV);
                System.out.println("Nombre de Match : " + Param.NB_MATCH);

                dispose();
                GameWindow gameWindow = new GameWindow();
            }
        });
    }
}
