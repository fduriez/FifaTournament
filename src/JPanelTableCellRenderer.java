import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class JPanelTableCellRenderer extends JPanel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //make multi line where the cell value is String[]
        if (value instanceof JPanel) {
            return (JPanel) value;
        }

        return this;
    }
}