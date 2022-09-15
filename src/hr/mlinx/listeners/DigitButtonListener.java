package hr.mlinx.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hr.mlinx.components.JMyTextField;
import hr.mlinx.util.Help;

public class DigitButtonListener implements ActionListener {
	
	private int index;
	private JMyTextField textField;
	
	public DigitButtonListener(int index, JMyTextField textField) {
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
		String optionalMultiplicationSign = "";
		
		if (index == Help.ZERO) {
			if (lastChar == '0') {
				if (currentText.length() == 1)
					return;
				else {
					char secondToLastChar = currentText.charAt(currentText.length() - 1 - 1);
					if (!Character.isDigit(secondToLastChar) && secondToLastChar != '.')
						return;
				}
			}
		} else {
			if (lastChar == '0') {
				if (currentText.length() == 1) {
					textField.setText("");
					currentText = "";
				} else {
					char secondToLastChar = currentText.charAt(currentText.length() - 1 - 1);
					if (!Character.isDigit(secondToLastChar) && secondToLastChar != '.') {
						textField.setText(currentText.substring(0, currentText.length() - 1));
						currentText = textField.getText();
					}
				}
			}
		}
		
		if (lastChar == '!' || lastChar == ')' || lastChar == 'e' || lastChar == 'π') 
			optionalMultiplicationSign = "*";
		
		textField.setText(currentText + optionalMultiplicationSign + Help.BTN_TEXTS[index]);
	}

}
