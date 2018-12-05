import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreWindow extends JDialog {
    private JLabel homeLabel = new JLabel("home");
    private JLabel versusLabel = new JLabel("  VS  ");
    private JLabel visitorLabel = new JLabel("visitor");
    private JComboBox homeScore = new JComboBox();
    private JComboBox visitorScore = new JComboBox();
    private JButton validButton = new JButton("Valider");

    private boolean sendData = false;

    public ScoreWindow(JFrame parent, String title, boolean modal, String homePlayerName, String visitorPlayerName){
        super(parent, title, modal);
        this.setSize(300, 120);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.validButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData = true;
                setVisible(false);
            }
        });

        this.homeLabel.setText(homePlayerName);
        this.visitorLabel.setText(visitorPlayerName);

        String[] score = {"0","1","2","3","4","5","6","7","8","9","10"};
        this.homeScore = new JComboBox(score);
        this.visitorScore = new JComboBox(score);

        JPanel firstPanel = new JPanel();
        firstPanel.add(this.homeLabel);
        firstPanel.add(this.homeScore);
        firstPanel.add(this.versusLabel);
        firstPanel.add(this.visitorScore);
        firstPanel.add(this.visitorLabel);

        JPanel secondPanel = new JPanel();
        secondPanel.add(this.validButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        this.getContentPane().add(mainPanel);

        //Enfin on l'affiche
        this.setVisible(true);
    }

    public String getScore(){
        String score = (String) this.homeScore.getSelectedItem();
        score += " - ";
        score += (String) this.visitorScore.getSelectedItem();
        return score;
    }

    public boolean sendData(){ return this.sendData; }
}