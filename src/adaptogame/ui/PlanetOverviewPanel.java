package adaptogame.ui;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

import adaptogame.core.Planet;
import adaptogame.core.Player;

public class PlanetOverviewPanel extends InfoPanel
{

	public PlanetOverviewPanel(String name, Player player) 
	{
		super(name, player);
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
		add(new TextLabel("Планета \"" + player.getCurrentPlanet().getName() + "\"", constraints.gridx + "." + constraints.gridy,false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("Почта", constraints.gridx + "." + constraints.gridy, true), constraints);
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
		add(new TextLabel("Уровень", constraints.gridx + "." + constraints.gridy, true), constraints);
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
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", constraints.gridx + "." + constraints.gridy, false), constraints);
		add(Box.createVerticalStrut(5 * STRUT_SIZE), constraints);
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new PlanetImg(player.getCurrentPlanet()), constraints);
		add(Box.createVerticalStrut(new PlanetImg(current_planet).getIcon().getIconHeight() + 30), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("", constraints.gridx + "." + constraints.gridy, false), constraints);
		add(Box.createHorizontalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Диаметр", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format(current_planet.getDiameter()) + " км (" + current_planet.getTakenFields() + " / " + current_planet.getFields() + "Поля )", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Занятость полей", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new FieldProgressBar((int)((double)current_planet.getTakenFields() / current_planet.getFields() * 100), constraints.gridx + "." + constraints.gridy), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Постройка", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		String text = "";
		try
		{
			text = current_planet.getBuildings()[current_planet.getBuildingsQueueElem(0)].generateHeaderWithoutLevel();
			int level = current_planet.getBuildings()[current_planet.getBuildingsQueueElem(0)].getLevel() + 1;
			text += " " + level;
		}
		catch(IndexOutOfBoundsException e)
		{
			text = "";
		}
		add(new TextLabel(text, constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Исследование", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		text = "";
		try
		{
			text = player.getTechs()[player.getActiveResearch()].generateHeaderWithoutLevel();
			int level = player.getTechs()[player.getActiveResearch()].getLevel() + 1;
			text += " " + level;
		}
		catch(IndexOutOfBoundsException e)
		{
			text = "";
		}
		add(new TextLabel(text, constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Верфь", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		try
		{
			text = current_planet.getUnits()[current_planet.getUnitQueueElem(0).getCode()].getName();
		}
		catch(IndexOutOfBoundsException e)
		{
			text = "";
		}
		add(new TextLabel(text, constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Температура", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("от " + player.getCurrentPlanet().getMinTemperature() + "°C до " + player.getCurrentPlanet().getMaxTemperature() + "°C", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Координаты", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("[" + player.getCurrentPlanet().getCoords()[0] + ":" + player.getCurrentPlanet().getCoords()[1] + ":" + player.getCurrentPlanet().getCoords()[2] + "]", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 13;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Поле обломков", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 13;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("reserved", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 5;
		add(new TextLabel("Капитализация", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Постройки:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 15;
		add(new TextLabel("Исследования:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 16;
		add(new TextLabel("Флот:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 17;
		add(new TextLabel("Оборона:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 18;
		add(new TextLabel("Капитализация:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.insets.right = 5;
		add(new TextLabel("Боевые достижения", constraints.gridx + "." + constraints.gridy, true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Всего атак:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 20;
		add(new TextLabel("Побед:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 21;
		add(new TextLabel("Поражений:", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 22;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weighty = 1.0f;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
	}
	
	public void updatePanelUI()
	{
		Component[] list = getComponents();
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] instanceof TextLabel)
			{
				String[] s = list[i].getName().split("\\.");
				int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
				if(coords[1] == 0)
				{
					((TextLabel)list[i]).setText("Планета \"" + player.getCurrentPlanet().getName() + "\"");
				}
				if(coords[0] == 1)
				{
					String text = "";
					switch(coords[1])
					{
						case 6:
							((TextLabel)list[i]).setText("" + NumberFormat.getNumberInstance(Locale.US).format(current_planet.getDiameter()) + " км (" + current_planet.getTakenFields() + " / " + current_planet.getFields() + "Поля )");
							break;
						case 8:
							try
							{
								text = current_planet.getBuildings()[current_planet.getBuildingsQueueElem(0)].generateHeaderWithoutLevel();
								int level = current_planet.getBuildings()[current_planet.getBuildingsQueueElem(0)].getLevel() + 1;
								text += " " + level;
							}
							catch(IndexOutOfBoundsException e)
							{
								text = "";
							}
							((TextLabel)list[i]).setText(text);
							break;
						case 9:
							text = "";
							try
							{
								text = player.getTechs()[player.getActiveResearch()].generateHeaderWithoutLevel();
								int level = player.getTechs()[player.getActiveResearch()].getLevel() + 1;
								text += " " + level;
							}
							catch(IndexOutOfBoundsException e)
							{
								text = "";
							}
							((TextLabel)list[i]).setText(text);
							break;
						case 10:
							try
							{
								text = current_planet.getUnits()[current_planet.getUnitQueueElem(0).getCode()].getName();
							}
							catch(IndexOutOfBoundsException e)
							{
								text = "";
							}
							((TextLabel)list[i]).setText(text);
							break;
					}	
				}
			}
			if(list[i] instanceof FieldProgressBar)
			{
				((FieldProgressBar)list[i]).setValue((int)((double)current_planet.getTakenFields() / current_planet.getFields() * 100));
			}
		}
	}
	
	private class PlanetImg extends JLabel
	{
		public PlanetImg(Planet planet)
		{
			setIcon(new ImageIcon(planet.getImg()));
			setBackground(BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, boolean centered)
		{
			this.centered = centered;
			setName(name);
			real_text = text;
			addComponentListener(this);
			setText(real_text);
			setForeground(Color.WHITE);
			setBackground(BACKGROUND_COLOR);
			setOpaque(true);
			setVisible(true);
		}
		
		public void setText(String text)
		{
			real_text = text;
			String div_opening = "";
			String div_closing = "";
			if(centered)
			{
				div_opening = "<div style='text-align:center;'>";
				div_closing = "</div>";
			}
			super.setText("<html><body width='" + getWidth() + "'>" + div_opening + "<font size=\"4\">" + text +"</font>" + div_closing + "</body></html>");
		}
		
		public void componentResized(ComponentEvent e) 
		{
			setText(real_text);
		}

		public void componentMoved(ComponentEvent e) 
		{
			
		}

		public void componentShown(ComponentEvent e) 
		{
			
		}

		public void componentHidden(ComponentEvent e) 
		{
			
		}
		
		private String real_text;
		private boolean centered;
	}
	
	private class FieldProgressBar extends JProgressBar
	{
		public FieldProgressBar(int value, String name)
		{
			setOpaque(true);
			setName(name);
			setMinimum(0);
			setMaximum(100);
			setValue(value);
			setForeground(new Color(0, 128, 0));
			setBackground(BACKGROUND_COLOR);
			setBorderPainted(false);
			setStringPainted(true);
			setUI(new BasicProgressBarUI() 
			{
				protected Color getSelectionBackground() { return Color.WHITE; }
				protected Color getSelectionForeground() { return Color.WHITE; }
			});
		}
	}
	
	private static final int STRUT_SIZE = 20;
}
