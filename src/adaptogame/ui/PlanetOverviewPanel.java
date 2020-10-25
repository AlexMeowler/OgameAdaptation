package adaptogame.ui;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

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
		add(new TextLabel("Планета \"" + player.getCurrentPlanet().getName() + "\"", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTH;
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
		constraints.insets.right = 5;
		add(new PlanetImg(player.getCurrentPlanet()), constraints);
		add(Box.createVerticalStrut(new PlanetImg(player.getCurrentPlanet()).getIcon().getIconHeight() + 30), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		add(Box.createHorizontalStrut(STRUT_SIZE), constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Диаметр", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format(player.getCurrentPlanet().getDiameter()) + " км", true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Занятость полей", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Исследование", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Верфь", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("", false), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Температура", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 11;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("от " + player.getCurrentPlanet().getMinTemperature() + "°C до " + player.getCurrentPlanet().getMaxTemperature() + "°C", true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 12;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Координаты", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("[" + player.getCurrentPlanet().getCoords()[0] + ":" + player.getCurrentPlanet().getCoords()[1] + ":" + player.getCurrentPlanet().getCoords()[2] + "]", true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 13;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Поле обломков", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 13;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		add(new TextLabel("reserved", true), constraints);
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 5;
		add(new TextLabel("Капитализация", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 14;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Постройки:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 15;
		add(new TextLabel("Исследования:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 16;
		add(new TextLabel("Флот:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 17;
		add(new TextLabel("Оборона:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 18;
		add(new TextLabel("Капитализация:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		constraints.insets.right = 5;
		add(new TextLabel("Боевые достижения", true), constraints);
		constraints.insets.right = 0;
		constraints.gridx = 1;
		constraints.gridy = 19;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("Всего атак:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 20;
		add(new TextLabel("Побед:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 1;
		constraints.gridy = 21;
		add(new TextLabel("Поражений:", false), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Резерв", false), constraints);
		constraints.gridx = 0;
		constraints.gridy = 22;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weighty = 1.0f;
		add(Box.createVerticalStrut(STRUT_SIZE), constraints);
	}
	
	private class PlanetImg extends JLabel
	{
		public PlanetImg(Planet planet)
		{
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
	
	private static final int STRUT_SIZE = 20;
}
