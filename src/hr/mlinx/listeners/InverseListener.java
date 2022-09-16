package hr.mlinx.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import hr.mlinx.components.ButtonPanel;
import hr.mlinx.util.Help;

public class InverseListener implements ActionListener {
	
	ButtonPanel buttonPanel;
	
	public InverseListener(ButtonPanel buttonPanel) {
		super();
		this.buttonPanel = buttonPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean selected = ((AbstractButton)e.getSource()).getModel().isSelected();
		if (selected) {
			flip();
			buttonPanel.setInverse(true);
		} else {
			flipOriginals();
			buttonPanel.setInverse(false);
		}
	}
	
	private void flip() {
		change(Help.SINE, "asin");
		change(Help.NAT_LOG, "<html>e<sup>x</sup></html>");
		change(Help.COSINE, "acos");
		change(Help.LOG, "<html>10<sup>x</sup></html>");
		change(Help.TANGENS, "atan");
		change(Help.SQRT, "<html>x<sup>2</sup></html>");
	}
	
	private void change(int index, String text) {
		buttonPanel.button(index).setText(text);
	}
	
	private void flipOriginals() {
		changeOG(Help.SINE);
		changeOG(Help.NAT_LOG);
		changeOG(Help.COSINE);
		changeOG(Help.LOG);
		changeOG(Help.TANGENS);
		changeOG(Help.SQRT);
	}
	
	private void changeOG(int index) {
		buttonPanel.button(index).setText(Help.BTN_TEXTS[index]);
	}

}
