import javax.swing.*;
import java.awt.*;

public class TeamListRenderer extends DefaultListCellRenderer {
    private Color background = new Color(0, 100, 255, 15);
    private Color defaultBackground = (Color) UIManager.get("List.background");

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        String name = (String) value;
        this.setText(name);

        if(Param.iconTeam.get(name) != null){
            ImageIcon icon = new ImageIcon(Param.iconTeam.get(name).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            this.setIcon(icon);
        }
        else {
            //this.setFont();
            this.setForeground(Color.GRAY);
        }


        if (!isSelected) {
            this.setBackground(index % 2 == 0 ? background : defaultBackground);
        }
        return this;
    }
}