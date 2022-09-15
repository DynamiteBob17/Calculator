package hr.mlinx.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hr.mlinx.components.JMyTextField;
import hr.mlinx.util.Help;

public class PlusMinusButtonListener implements ActionListener {
	
	private int index;
	private JMyTextField textField;
	
	public PlusMinusButtonListener(int index, JMyTextField textField) {
		super();
		this.index = index;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String currentText = textField.getText();
		
		if (currentText.isEmpty()) {
			textField.setText(currentText + Help.BTN_TEXTS[index]);
			return;
		}
		
		char lastChar = currentText.charAt(currentText.length() - 1);
		
		if (lastChar == '+' && index == Help.MINUS) {
			textField.setText(currentText.substring(0, currentText.length() - 1) + '-');
			return;
		} else if (lastChar == '-' && index == Help.PLUS) {
			textField.setText(currentText.substring(0, currentText.length() - 1) + '+');
			return;
		}
		
		
		if (lastChar == '.' || lastChar == '+' || lastChar == '-')
			return;
		
		textField.setText(currentText + Help.BTN_TEXTS[index]);
	}
	
}
