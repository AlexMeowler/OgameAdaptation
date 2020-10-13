package offgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BuildingPanel extends InfoPanel 
{

	public BuildingPanel(String name, Planet planet) throws IOException 
	{
		super(name);
		//setOpaque(false);
		current_planet = planet;
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("Занятость полей", "" + constraints.gridx + "." +constraints.gridy, true, false), constraints);
		constraints.weightx = 0.0f;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Резерв / " + planet.getFields() + " (осталось РЕЗЕРВ)",  "" + constraints.gridx + "." +constraints.gridy, true, false), constraints);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		add(new TextLabel("" + planet.getMinTemperature() + " — " + planet.getMaxTemperature() + "°C",  "" + constraints.gridx + "." +constraints.gridy, true, false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new BuildingImg(Building.POWER_STATION), constraints);
		add(Box.createRigidArea(new Dimension(new BuildingImg(Building.POWER_STATION).getIcon().getIconHeight() + 6, new BuildingImg(Building.POWER_STATION).getIcon().getIconHeight() + 6)), constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		// что то не так с этим компонентом
		// проверить с меньшим текстом
		add(new TextLabel("Солнечная электростанция (<font color='lime'>+" + (int)current_planet.getBuildings()[Building.POWER_STATION].calcDifference(current_planet.getBuildings()[Building.POWER_STATION].getLevel(), current_planet.getBuildings()[Building.POWER_STATION].getLevel() + 1) + " Энергия</font>)<br> Производит энергию из солнечных лучей.<br>Энергия требуется для работы всех строений производящих ресурсы.<br>Необходимые ресурсы: РЕЗЕРВ<br>Время строительства: РЕЗЕРВ",  "" + constraints.gridx + "." +constraints.gridy, false, true), constraints);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("<font color='lime'><u>Построить</u></font>",  "" + constraints.gridx + "." +constraints.gridy, true, false), constraints);
	}
	
	private class BuildingImg extends JLabel
	{
		public BuildingImg(int img_num) throws IOException
		{
			setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/bl/" + img_num + ".gif"))));
			setBackground(new Color(42, 69, 112));
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel
	{
		public TextLabel(String text, String name, boolean centeredHorizontally, boolean VerticallyAtTop)
		{	
			setName(name);
			setText("<html><font size=\"3\">" + text +"</font></html>");
			setForeground(Color.WHITE);
			setBackground(new Color(42, 69, 112));
			setOpaque(true);
			setVisible(true);
			if(centeredHorizontally)
			{
				setHorizontalAlignment(SwingConstants.CENTER);
			}
			if(VerticallyAtTop)
			{
				setVerticalAlignment(SwingConstants.NORTH);
			}
		}
		
	}
	
	private Planet current_planet;
}
