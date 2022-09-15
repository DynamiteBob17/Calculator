package hr.mlinx.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

public class JGradientButton extends JButton { 
	private static final long serialVersionUID = 4564747408707478454L;
	
	private Color secondaryColor; 
	
	public JGradientButton(String text, Color mainColor, Color secondaryColor, Color fontColor, float fontSize){
        super(text);
        setContentAreaFilled(false);
        setBackground(mainColor);
        setForeground(fontColor);
        this.secondaryColor = secondaryColor;
        setFont(getFont().deriveFont(fontSize));
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                getBackground(), 
                new Point(0, getHeight()/3), 
                secondaryColor));
        g2.fillRect(0, 0, getWidth(), getHeight()/3);
        g2.setPaint(new GradientPaint(
                new Point(0, getHeight()/3), 
                secondaryColor, 
                new Point(0, getHeight()), 
                getBackground()));
        g2.fillRect(0, getHeight()/3, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
	
}
