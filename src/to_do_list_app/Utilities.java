package to_do_list_app;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTable;

public class Utilities {

	public void clearFields(JTextField textField, JTextField textField2, JCheckBox checkBox) {
		textField.setText("");
		textField2.setText("");
		checkBox.setSelected(false);
	}
	
	public void renameColumns(JTable table) {
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Completed");
		table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Task");
		table.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Task Details");
		table.repaint();
	}
	
}
