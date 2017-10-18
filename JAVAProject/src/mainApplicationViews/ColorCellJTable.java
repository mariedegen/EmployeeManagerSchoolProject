package mainApplicationViews;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Class ColorCellJtable
 * @author Marie DEGEN
 * @see StateDayView
 */
public class ColorCellJTable extends DefaultTableCellRenderer {
	 
	/**
	 * Attributss
	 * @param serialVersionUID for the serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Function that color the cell, has different color according to its state
	 * @return component the cell colored
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 0) {
            component.setBackground(Color.WHITE);
        } else {
        	Color clr = new Color(153, 255, 204);
            component.setBackground(clr);
        }

        Object o = table.getValueAt(column, 2);
        if (o != null && component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getText().contains("[]")) 
            {
                Color clr = new Color(255, 204, 204);
                component.setBackground(clr);
            }
            
        }
        return component;
    }
}