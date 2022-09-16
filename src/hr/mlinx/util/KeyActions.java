package hr.mlinx.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import hr.mlinx.components.ButtonPanel;
import hr.mlinx.components.CalculatorFrame;
import hr.mlinx.components.JMyTextField;

public class KeyActions extends KeyAdapter {
	
	private CalculatorFrame window;
	private JMyTextField textField;
	private ButtonPanel buttonPanel;
	
	public KeyActions(CalculatorFrame window, JMyTextField textField, ButtonPanel buttonPanel) {
		super();
		this.window = window;
		this.textField = textField;
		this.buttonPanel = buttonPanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		
		if (c == '\n' || c == '\r') {
			String currentText = textField.getText();
			if (currentText.isEmpty())
				return;
			String res = Result.calculate(currentText, buttonPanel.isRadians());
			textField.setText(res);
			window.setPrevResult(res);
		}
		
		if (Character.isDigit(c) || Character.isISOControl(c)
				|| c == '-' || c == '+' || c == '*'
				|| c == '/' || c == ',' || c == '.'
				|| c == '(' || c == ')'
				|| c == '!' || c == '^' || c == '='
				|| c == 'e' || c == 'π'
				|| c == 'p'
				|| c == 's' || c == 'i' || c == 'n'
				|| c == 'l' || c == 'c' || c == 'o'
				|| c == 'g' || c == 't' || c == 'a'
				|| c == 'q' || c == 'r' || c == 'b'
				|| Character.isWhitespace(c)) {
		} else {
			window.getToolkit().beep();
			e.consume();
		}
	}
	
}
