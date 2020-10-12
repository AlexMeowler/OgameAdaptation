package offgame;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OverviewPanel extends InfoPanel 
{

	public OverviewPanel(String name, Planet planet) 
	{
		super(name);
		setOpaque(false);
		constraints.weightx = 1.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		add(new TextLabel("Планета", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		//constraints.ipady = 10;
		add(new TextLabel("Почта", true), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Уровень", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Рейтинг", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", false), constraints);
		add(Box.createVerticalStrut(5 * STRUT_SIZE), constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(new PlanetImg(planet), constraints);
		//add(Box.createHorizontalStrut(200), constraints);
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", false), constraints);
		add(Box.createHorizontalStrut(STRUT_SIZE), constraints);
	}
	
	private class PlanetImg extends JLabel
	{
		public PlanetImg(Planet planet)
		{
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setIcon(new ImageIcon(planet.getImg()));
			setBackground(new Color(42, 69, 112));
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel
	{
		public TextLabel(String text, boolean centered)
		{
			setText("<html><font size=\"4\">" + text +"</font></html>");
			setForeground(Color.WHITE);
			setBackground(new Color(42, 69, 112));
			setOpaque(true);
			setVisible(true);
			if(centered)
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		
	}
	private static final int STRUT_SIZE = 30;
}
