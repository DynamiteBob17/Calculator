package hr.mlinx.components;

import javax.swing.JTextField;


public class JMyTextField extends JTextField {
	private static final long serialVersionUID = 1780448727097272337L;
	
	private int limit;
	
	public JMyTextField(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void setText(String text) {
		int currentLength = getText().length();
		int newLength = text.length() - currentLength;
		
		if (currentLength + newLength <= limit)
			super.setText(text);
	}
	
}
