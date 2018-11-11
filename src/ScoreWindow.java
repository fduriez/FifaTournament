import javax.swing.*;

public class ScoreWindow extends JFrame {
    private JLabel homeLabel = new JLabel("home");
    private JLabel versusLabel = new JLabel("  VS  ");
    private JLabel visitorLabel = new JLabel("visitor");
    private JComboBox homeScore = new JComboBox();
    private JComboBox visitorScore = new JComboBox();
    public ScoreWindow(Match match) {
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SCORE MATCH");
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        String[] score = {"0","1","2","3","4","5","6","7","8","9","10"};
        this.homeScore = new JComboBox(score);
        this.visitorScore = new JComboBox(score);

        JPanel mainPanel = new JPanel();
        mainPanel.add(this.homeLabel);
        mainPanel.add(this.homeScore);
        mainPanel.add(this.versusLabel);
        mainPanel.add(this.visitorScore);
        mainPanel.add(this.visitorLabel);
        this.getContentPane().add(mainPanel);



        this.homeLabel.setText(match.getHomePlayer().getName());
        this.visitorLabel.setText(match.getVisitorPlayer().getName());

        this.setVisible(true);
    }
}
