package hr.mlinx.components;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import hr.mlinx.listeners.InverseListener;
import hr.mlinx.util.JTextFieldLimit;
import hr.mlinx.util.KeyActions;

public class CalculatorFrame extends JFrame {
	private static final long serialVersionUID = -9212399885368612884L;
	
	private static final int CHAR_LIMIT = 61;
	
	private ButtonPanel buttonPanel;
	private JMyTextField textField;
	
	private String prevResult = "";
	private boolean pressedEnterOrEquals = false;
    
	public CalculatorFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setPreferredSize(new Dimension(width / 2, height / 2));
		setTitle("Calculator");
		
		textField = new JMyTextField(CHAR_LIMIT, this);
		textField.setFont(textField.getFont().deriveFont((float) width / 35));
		textField.setDocument(new JTextFieldLimit(CHAR_LIMIT));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		buttonPanel = new ButtonPanel(textField, this);
		buttonPanel.invBtn().addActionListener(new InverseListener(buttonPanel));
		
		textField.addKeyListener(new KeyActions(this, textField, buttonPanel));
		
		add(textField, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setPrevResult(String prevResult) {
		if (prevResult.contains("NaN") || prevResult.contains("Infinity") || prevResult.isEmpty())
			return;
		
		this.prevResult = prevResult;
	}
	
	public String getPrevResult() {
		return prevResult;
	}

	public boolean isPressedEnterOrEquals() {
		return pressedEnterOrEquals;
	}

	public void setPressedEnterOrEquals(boolean pressedEnterOrEquals) {
		this.pressedEnterOrEquals = pressedEnterOrEquals;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new CalculatorFrame();
		});
	}
	
}
