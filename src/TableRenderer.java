import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class TableRenderer extends DefaultTableCellRenderer {
    JLabel lbl = new JLabel();

    ImageIcon icon = new ImageIcon();

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        String name = (String) value;
        icon = new ImageIcon(Param.iconTeam.get(name).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        lbl.setHorizontalAlignment(CENTER);
        lbl.setIcon(icon);
        return lbl;
    }
}