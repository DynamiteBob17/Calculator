package hr.mlinx.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import hr.mlinx.listeners.DigitButtonListener;
import hr.mlinx.listeners.DivMultButtonListener;
import hr.mlinx.listeners.FunctionButtonListener;
import hr.mlinx.listeners.PlusMinusButtonListener;
import hr.mlinx.util.Help;
import hr.mlinx.util.Result;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = -1869535166207654639L;
	
	private JToggleButton radiansBtn;
	private JToggleButton degreesBtn;
	private JGradientToggleButton inverseBtn;
	
	private static final Dimension RES = Toolkit.getDefaultToolkit().getScreenSize();
	private float radDegAndLightButtonFontSize = (float) (35.0 * (RES.width / 1920.0));
	private float darkButtonFontSize = (float) (29.0 * (RES.width / 1920.0));
	
	private List<JGradientButton> buttons;
	
	private boolean inverse = false;
	private boolean radians = true;
	
	public ButtonPanel(JMyTextField textField, CalculatorFrame window) {
		super();
		setLayout(new GridLayout(6, 7, 5, 5));
		setBackground(new Color(192, 212, 212)); 
		
		buttons = new LinkedList<>();
		
		add(radiansBtn = new JToggleButton("Rad"));
		add(degreesBtn = new JToggleButton("Deg"));
		
		radiansBtn.setSelected(true);
		radiansBtn.setEnabled(false);
		
		radiansBtn.addActionListener(e -> {
			if (radiansBtn.isEnabled()) {
				radiansBtn.setSelected(true);
				radiansBtn.setEnabled(false);
				degreesBtn.setSelected(false);
				degreesBtn.setEnabled(true);
			}
			radians = true;
		});
		
		degreesBtn.addActionListener(e -> {
			if (degreesBtn.isEnabled()) {
				degreesBtn.setSelected(true);
				degreesBtn.setEnabled(false);
				radiansBtn.setSelected(false);
				radiansBtn.setEnabled(true);
			}
			radians = false;
		});
		
		for (int i = 0; i < (7 - 2); ++i) { 
			add(new JLabel());
		}
		
		for (int i = 0; i < Help.BTN_AMOUNT; ++i) {
			if (i == Help.INVERSE) {
				add(inverseBtn = new JGradientToggleButton("Inv", new Color(25, 25, 60), Color.DARK_GRAY, Color.WHITE, 30f));
				buttons.add(new JGradientButton("USELESS FILLER BUTTON", Color.WHITE, Color.WHITE, Color.WHITE, 0f));
				inverseBtn.setFocusable(false);
				continue;
			}
			
			Color mainColor;
			Color secondaryColor;
			Color fontColor;
			float fontSize;
			if (Help.isDarkButton(i)) {
				mainColor = new Color(25, 25, 60); 
				secondaryColor = Color.DARK_GRAY;
				fontColor = Color.WHITE;
				fontSize = darkButtonFontSize;
			} else {
				mainColor = new Color (100, 255, 255); 
				secondaryColor = Color.WHITE;
				fontColor = Color.DARK_GRAY;
				fontSize = radDegAndLightButtonFontSize;
			}
			
			JGradientButton btn = new JGradientButton(Help.BTN_TEXTS[i], mainColor, secondaryColor, fontColor, fontSize);
			buttons.add(btn);
			add(btn);
			btn.setFocusable(false);
		}
		radiansBtn.setFocusable(false);
		degreesBtn.setFocusable(false);
		radiansBtn.setFont(radiansBtn.getFont().deriveFont(radDegAndLightButtonFontSize));
		degreesBtn.setFont(degreesBtn.getFont().deriveFont(radDegAndLightButtonFontSize));
		
		Color deletionBtnsColor = new Color(255, 100, 0);
		buttons.get(Help.CLEAR).setBackground(deletionBtnsColor);
		buttons.get(Help.AC).setBackground(deletionBtnsColor);
		
		for (int i = 0; i < Help.BTN_AMOUNT; ++i) {
			if (Help.isDigit(i))
				button(i).addActionListener(new DigitButtonListener(i, textField));
			else if (Help.isFunction(i))
				button(i).addActionListener(new FunctionButtonListener(i, this, textField));
			else if (Help.isComplexArithmetic(i))
				button(i).addActionListener(new DivMultButtonListener(i, textField));
			else if (Help.isSimpleArithmetic(i))
				button(i).addActionListener(new PlusMinusButtonListener(i, textField));
		}
		
		button(Help.SQUARE).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty())
				return;
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (Character.isDigit(lastChar) || lastChar == ')' || lastChar == '!' || lastChar == 'e' || lastChar == 'π')
				textField.setText(currentText + "^(2)");
		});
		
		button(Help.OVER_X).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty())
				return;
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (Character.isDigit(lastChar) || lastChar == ')' || lastChar == '!' || lastChar == 'e' || lastChar == 'π')
				textField.setText(currentText + "^(-1)");
		});
		
		button(Help.SQRT).addActionListener(e -> {
			if (isInverse()) {
				String currentText = textField.getText();
				
				if (currentText.isEmpty())
					return;
				
				char lastChar = currentText.charAt(currentText.length() - 1);
				
				if (Character.isDigit(lastChar) || lastChar == ')' || lastChar == '!' || lastChar == 'e' || lastChar == 'π')
					textField.setText(currentText + "^(2)");
			} else {
				String currentText = textField.getText();
				String functionText = "sqrt(";
				
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
		});
		
		button(Help.LEFT_BRACKET).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty()) {
				textField.setText(currentText + '(');
				return;
			}
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (lastChar == '.')
				return;
			
			String optionalMultiplicationSign = "";
			
			if (Character.isDigit(lastChar) || lastChar == '!' || lastChar == ')'
				|| lastChar == 'e' || lastChar == 'π')
				optionalMultiplicationSign = "*";
			
			textField.setText(currentText + optionalMultiplicationSign + '(');
		});
		
		button(Help.RIGHT_BRACKET).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty())
				return;
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (lastChar == '(')
				return;
			
			if (lastChar == '.' || lastChar == '^' || lastChar == '+' || lastChar == '-'
					|| lastChar == '*' || lastChar == '/')
				return;
			
			int leftBrackets = 0;
			int rightBrackets = 0;
			
			for (int i = 0; i < currentText.length(); ++i) {
				char c = currentText.charAt(i);
				if (c == '(')
					++leftBrackets;
				else if (c == ')')
					++rightBrackets;
			}
			
			if (leftBrackets > rightBrackets)
				textField.setText(currentText + ')');
		});
		
		button(Help.EQUALS).addActionListener(e -> {
			String currentText = textField.getText();
			if (currentText.isEmpty())
				return;
			
			String res = Result.calculate(currentText, this.isRadians());
			textField.setText(res);
			window.setPressedEnterOrEquals(true);
			window.setPrevResult(res);
		});
		
		button(Help.POW).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty())
				return;
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (Character.isDigit(lastChar) || lastChar == ')' || lastChar == '!' || lastChar == 'e' || lastChar == 'π')
				textField.setText(currentText + "^(");
		});
		
		button(Help.FACTORIAL).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty())
				return;
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (Character.isDigit(lastChar) || lastChar == '!'
					|| lastChar == ')')
				textField.setText(currentText + '!');
		});
		
		button(Help.ANS).addActionListener(e -> {
			if (window.getPrevResult().isEmpty())
				return;
			
			String currentText = textField.getText();
			
			if (currentText.isEmpty()) {
				textField.setText(currentText + window.getPrevResult());
				return;
			}
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			String optionalMultiplicationSign = "";
			
			if (lastChar == '!' || lastChar == ')' || lastChar == 'e' || lastChar == 'π' || Character.isDigit(lastChar))  {
				if (!window.isPressedEnterOrEquals())
					optionalMultiplicationSign = "*";
			}
			
			textField.setText(currentText + optionalMultiplicationSign + window.getPrevResult());
		});
		
		String[] delStr =  new String[] {"sin(", "cos(", "tan(", "arcsin(", "arccos(", "arctan(",
				"ln(", "log(", "sqrt(", "abs(", "^("};
		
		button(Help.CLEAR).addActionListener(e -> {
			if (textField.getText().isEmpty())
				return;
			
			String currentText = textField.getText();
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (lastChar != '(')
				textField.setText(currentText.substring(0, currentText.length() - 1));
			else {
				for (String s : delStr) {
					if (currentText.endsWith(s)) {
						int lastOccuranceOfS = currentText.lastIndexOf(s);
						textField.setText(currentText.substring(0, lastOccuranceOfS));
						return;
					}
				}
				textField.setText(currentText.substring(0, currentText.length() - 1));
			}
		});
		
		button(Help.AC).addActionListener(e -> {
			textField.setText("");
		});
		
		button(Help.POINT).addActionListener(e -> {
			String currentText = textField.getText();
			
			if (currentText.isEmpty()) {
				return;
			}
			
			int numOfDigitsInBegin = 0;
			char c;
			for (int i = currentText.length() - 1; i >= 0; --i) {
				c = currentText.charAt(i);
				
				if (Character.isDigit(c))
					++numOfDigitsInBegin;
			}
			
			if (numOfDigitsInBegin == currentText.length()) {
				textField.setText(currentText + '.');
				return;
			}
			
			char lastChar = currentText.charAt(currentText.length() - 1);
			
			if (Character.isDigit(lastChar)) {
				for (int i = currentText.length() - 1; i >= 0; --i) {
					c = currentText.charAt(i);
					
					if (!Character.isDigit(c) && c != '.') {
						textField.setText(currentText + '.');
						return;
					} else if (!Character.isDigit(c)){
						return;
					}
				}
			}
		});
	}
	
	public boolean isInverse() {
		return inverse;
	}
	
	public boolean isRadians() {
		return radians;
	}
	
	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}
	
	public JGradientButton button(int index) {
		return buttons.get(index);
	}
	
	public JToggleButton radBtn() {
		return radiansBtn;
	}
	
	public JToggleButton degBtn() {
		return degreesBtn;
	}
	
	public JGradientToggleButton invBtn() {
		return inverseBtn;
	}
	
}
