package to_do_list_app;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Utilities {

	public void clearFields(JTextField textField, JTextField textField2, JCheckBox checkBox) {
		
		textField.setText("");
		textField2.setText("");
		checkBox.setSelected(false);
		
	}
	
}
