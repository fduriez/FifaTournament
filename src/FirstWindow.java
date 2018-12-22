import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FirstWindow extends JFrame implements ActionListener{
    private JComboBox nbParticipantCombo = new JComboBox();
    private JComboBox nbTVCombo = new JComboBox();
    private JButton validButton = new JButton("Valider");

    public FirstWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NOMBRE DE PARTICIPANTS");
        this.setSize(500, 100);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //Nous ajoutons notre fenêtre à la liste des auditeurs de notre bouton
        this.validButton.addActionListener(this);

        String[] choixNbParticipant = {"4","5","6","7","8","9","10"};
        this.nbParticipantCombo = new JComboBox(choixNbParticipant);

        String[] choixNbTV = {"1","2","3","4","5"};
        this.nbTVCombo = new JComboBox(choixNbTV);

        GridLayout gridParticipant = new GridLayout(2,1);
        GridLayout gridTV = new GridLayout(2,1);
        GridLayout grid = new GridLayout(1,3);

        JLabel label1 = new JLabel("Nombre de participant");
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(0,10,0,20));
        panel1.setLayout(gridParticipant);
        panel1.add(label1);
        panel1.add(this.nbParticipantCombo);

        JLabel label2 = new JLabel("Nombre de télé");
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(0,20,0,40));
        panel2.setLayout(gridTV);
        panel2.add(label2);
        panel2.add(this.nbTVCombo);

        JPanel panelGlobal = new JPanel();
        panelGlobal.setLayout(grid);
        panelGlobal.add(panel1);
        panelGlobal.add(panel2);
        panelGlobal.add(this.validButton);
        panelGlobal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.getContentPane().add(panelGlobal);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        Param.NB_PLAYER = Integer.valueOf((String) this.nbParticipantCombo.getSelectedItem());
        Param.NB_TV = Integer.valueOf((String) this.nbTVCombo.getSelectedItem());
        Param.NB_MATCH = 0;
        for(int i=1; i<Param.NB_PLAYER; i++) {
            Param.NB_MATCH += i;
        }
        Param.NB_MATCH *= 2;

        this.dispose();
        PlayersWindow playerWindow = new PlayersWindow();
    }
}
