package hr.mlinx.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hr.mlinx.components.ButtonPanel;
import hr.mlinx.components.JMyTextField;
import hr.mlinx.util.Help;

public class FunctionButtonListener implements ActionListener {
	
	private int index;
	private ButtonPanel buttonPanel;
	private JMyTextField textField;
	
	public FunctionButtonListener(int index, ButtonPanel buttonPanel, JMyTextField textField) {
		super();
		this.index = index;
		this.buttonPanel = buttonPanel;
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String currentText = textField.getText();
		String functionText = "";
		String invArcText = "";
		
		if (buttonPanel.isInverse() && index != Help.NAT_LOG && index != Help.LOG)
			invArcText = "arc";
		
		functionText = invArcText + Help.BTN_TEXTS[index] + '(';
		
		if (index == Help.ABSOLUTE)
			functionText = "abs(";
		else if (index == Help.EULER || index == Help.PI)
			functionText = Help.BTN_TEXTS[index];
		else if (buttonPanel.isInverse() && index == Help.NAT_LOG)
			functionText = "e^(";
		else if (buttonPanel.isInverse() && index == Help.LOG)
			functionText = "10^(";
		
		if (currentText.isEmpty()) {
			textField.setText(currentText + functionText);
			return;
		}
		
		char lastChar = currentText.charAt(currentText.length() - 1);
		if (lastChar == '.')
			return;
		String optionalMultiplicationSign = "";
		
		if (lastChar == ')' || lastChar == '!' || lastChar == 'e' || lastChar == 'π' || Character.isDigit(lastChar))
			optionalMultiplicationSign = "*";
		
		textField.setText(currentText + optionalMultiplicationSign + functionText);
	}
	
	
	
}
