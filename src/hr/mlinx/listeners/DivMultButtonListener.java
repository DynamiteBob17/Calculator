package hr.mlinx.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hr.mlinx.components.JMyTextField;
import hr.mlinx.util.Help;

public class DivMultButtonListener implements ActionListener {
	
	private int index;
	private JMyTextField textField;
	
	public DivMultButtonListener(int index, JMyTextField textField) {
		super();
		this.index = index;
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String currentText = textField.getText();
		
		if (currentText.isEmpty())
			return;
		
		String actualText = "";
		if (index == Help.MULTIPLY)
			actualText = "*";
		else if (index == Help.DIVISON)
			actualText = "/";
		
		char lastChar = currentText.charAt(currentText.length() - 1);
		
		if (lastChar == '(' || lastChar == '.' || lastChar == '+' || lastChar == '-'
				|| lastChar == '/' || lastChar == '*' || lastChar == '^')
			return;
		
		textField.setText(currentText + actualText);
	}
	
	
	
}
