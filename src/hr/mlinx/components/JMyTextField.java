package hr.mlinx.components;

import javax.swing.JTextField;


public class JMyTextField extends JTextField {
	private static final long serialVersionUID = 1780448727097272337L;
	
	private int limit;
	private CalculatorFrame window;
	
	public JMyTextField(int limit, CalculatorFrame window) {
		super();
		this.limit = limit;
		this.window = window;
	}

	@Override
	public void setText(String text) {
		int currentLength = getText().length();
		int newLength = text.length() - currentLength;
		try {
			if (currentLength + newLength <= limit) {
				if (window.isPressedEnterOrEquals()) {
					if (text.contains("NaN")) {
						if (text.contains("N"))
							text = text.substring(text.lastIndexOf("N") + 1);
							if (text.length() > 0 && text.charAt(0) == '*')
								text = text.substring(1, text.length());
					} else if (text.contains("Infinity")) {
						if (text.contains("y"))
							text = text.substring(text.lastIndexOf("y") + 1);
							if (text.length() > 0 && text.charAt(0) == '*')
								text = text.substring(1, text.length());
					} else {
						if (text.contains(window.getPrevResult()))
						text = text.substring(text.indexOf(window.getPrevResult()) + window.getPrevResult().length());
						if (text.length() > 0 && text.charAt(0) == '*')
							text = text.substring(1, text.length());
					}
					window.setPressedEnterOrEquals(false);
				} {
				super.setText(text);
				window.setPressedEnterOrEquals(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
