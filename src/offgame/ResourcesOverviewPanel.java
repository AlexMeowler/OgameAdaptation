package offgame;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class ResourcesOverviewPanel extends InfoPanel 
{
	public ResourcesOverviewPanel(String name, Player player)
	{
		super(name, player);
		setOpaque(false);
		constraints.insets.top = 0;
		addPlanetaryProductionUI(0);
		constraints.insets.top = CELL_HEIGHT;
		addProductionInfoUI(9);
		constraints.insets.top = CELL_HEIGHT;
		addStorageInfo(14);
	}
	
	private void addPlanetaryProductionUI(int offset)
	{
		constraints.ipady = 0;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0 + offset;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.weightx = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("Производство на планете \"" + current_planet.getName() + "\"", constraints.gridx + "." + constraints.gridy, false, false, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.top = 0;
		constraints.ipady = CELL_HEIGHT;
		constraints.weightx = 0.6f;
		constraints.insets.right = 5;
		constraints.gridx = 0;
		constraints.gridy = 1 + offset;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel("", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.weightx = 0.1f;
		constraints.gridx = 1;
		add(new TextLabel("Металл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("Кристалл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("Дейтерий", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("Энергия", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2 + offset;
		constraints.insets.right = 5;
		add(new TextLabel("Естественное производство", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format((int)Planet.METAL_DEFAULT_PRODUCTION), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format((int)Planet.CRYSTAL_DEFAULT_PRODUCTION), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("" + NumberFormat.getNumberInstance(Locale.US).format((int)Planet.DEITERIUM_DEFAULT_PRODUCTION), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3 + offset;
		constraints.insets.right = 5;
		add(new TextLabel(current_planet.getBuildings()[Building.METAL_MINES].generateHeader(), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.METAL_MINES].calcGathering()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='red'>-" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.METAL_MINES].calcConsuming()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4 + offset;
		constraints.insets.right = 5;
		add(new TextLabel(current_planet.getBuildings()[Building.CRYSTAL_MINES].generateHeader(), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.CRYSTAL_MINES].calcGathering()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='red'>-" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.CRYSTAL_MINES].calcConsuming()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 5 + offset;
		constraints.insets.right = 5;
		add(new TextLabel(current_planet.getBuildings()[Building.DEITERIUM_MINES].generateHeader(), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.DEITERIUM_MINES].calcGathering()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='red'>-" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.DEITERIUM_MINES].calcConsuming()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 6 + offset;
		constraints.insets.right = 5;
		add(new TextLabel(current_planet.getBuildings()[Building.POWER_STATION].generateHeader(), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.POWER_STATION].calcGathering()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 7 + offset;
		constraints.insets.right = 5;
		add(new TextLabel(current_planet.getBuildings()[Building.NUCLEAR_STATION].generateHeader(), constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("0", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[Building.NUCLEAR_STATION].calcGathering()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 8 + offset;
		constraints.insets.right = 5;
		add(new TextLabel("Общая выработка <font color='lime'>в час</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 1;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getMetalProduction()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getCrystalProduction()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getDeiteriumProduction()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getCurrentEnergy()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
	}
	
	private void addProductionInfoUI(int offset)
	{
		constraints.ipady = 0;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0 + offset;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.weightx = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("Информация о производстве", constraints.gridx + "." + constraints.gridy, false, false, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.top = 0;
		constraints.weightx = 0.4f;
		constraints.gridx = 0;
		constraints.gridy = 1 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 5;
		constraints.ipady = CELL_HEIGHT;
		add(new TextLabel("", constraints.gridx + "." + constraints.gridy, false, false, null), constraints);
		constraints.weightx = 0.2f;
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		add(new TextLabel("За сутки", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("За неделю", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("За месяц", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 2 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 5;
		add(new TextLabel("Металл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getMetalProduction() * 24)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getMetalProduction() * 24 * 7)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getMetalProduction() * 24 * 7 * 30)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 3 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Кристалл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getCrystalProduction() * 24)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getCrystalProduction() * 24 * 7)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getCrystalProduction() * 24 * 7 * 30)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 0;
		constraints.gridy = 4 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Дейтерий", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridwidth = 1;
		constraints.gridx = 2;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getDeiteriumProduction() * 24)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 3;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getDeiteriumProduction() * 24 * 7)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		add(new TextLabel("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)(current_planet.getDeiteriumProduction() * 24 * 7 * 30)) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
	}
	
	private void addStorageInfo(int offset)
	{
		int percentage;
		Color color_status;
		constraints.ipady = 0;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0 + offset;
		constraints.gridwidth = 5;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.weightx = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("Статус хранилища", constraints.gridx + "." + constraints.gridy, false, false, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.top = 0;
		constraints.weightx = 0.4f;
		constraints.gridx = 0;
		constraints.gridy = 1 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 5;
		constraints.ipady = CELL_HEIGHT;
		//добавить цвета 
		percentage = (int)(current_planet.getCurrentMetal() / current_planet.getMetalCapacity() * 100);
		color_status = generateColor(percentage);
		add(new TextLabel("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getMetalCapacity() - current_planet.getCurrentMetal()) / (current_planet.getMetalProduction() / 3600))) + "</font> Металл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.weightx = 0.3f;
		constraints.gridx = 2;
		add(new TextLabel("" + percentage + "%", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		//
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy), constraints);
		//
		percentage = (int)(current_planet.getCurrentCrystal() / current_planet.getCrystalCapacity() * 100);
		color_status = generateColor(percentage);
		constraints.gridx = 0;
		constraints.gridy = 2 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getCrystalCapacity() - current_planet.getCurrentCrystal()) / (current_planet.getCrystalProduction() / 3600))) + "</font> Кристалл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("" + percentage + "%", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		//
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy), constraints);
		//
		percentage = (int)(current_planet.getCurrentDeiterium() / current_planet.getDeiteriumCapacity() * 100);
		color_status = generateColor(percentage);
		constraints.gridx = 0;
		constraints.gridy = 3 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getDeiteriumCapacity() - current_planet.getCurrentDeiterium()) / (current_planet.getDeiteriumProduction() / 3600))) + "</font> Дейтерий", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("" + percentage + "%", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		//
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy), constraints);
		//
	}
	
	private String getHexColor(Color color)
	{
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());
		if(red.length() < 2)
		{
			red = "0" + red;
		}
		if(green.length() < 2)
		{
			green = "0" + green;
		}
		if(blue.length() < 2)
		{
			blue = "0" + blue;
		}
		return red + green + blue;
	}
	protected String createTimeString(long seconds_total)
	{
		if (seconds_total < 0)
		{
			return "Хранилище переполнено!";
		}
		int day = (int)(seconds_total / 86400);
		int hours = (int)((seconds_total % 86400) / 3600);
		int minutes = (int)((seconds_total % 3600) / 60);
		int seconds = (int)(seconds_total % 60);
		int[] time_digits = {day, hours, minutes, seconds};
		return String.format("%02d:%02d:%02d:%02d", time_digits[0], time_digits[1], time_digits[2], time_digits[3]);
	}
	
	public void updatePanelUI()
	{

	}
	
	public static Color generateColor(int value)
	{
		if(value > 100)
		{
			value = 100;
		}
		if(value < 0)
		{
			value = 0;
		}
		Color start = new Color(0, 192, 0);
		Color end = new Color(192, 0, 0);
		int red = start.getRed();
		int green = start.getGreen();
		int blue = start.getBlue();
		double delta_red = (double)(end.getRed() - red) / 100;
		double delta_green = (double)(end.getGreen() - green) / 100;
		double delta_blue = (double)(end.getBlue() - blue) / 100;
		Color answer = new Color(red + (int)(delta_red * value), green + (int)(delta_green * value), blue + (int)(delta_blue * value));
		return answer;
	}
	
	private class StorageProgressBar extends JProgressBar
	{
		public StorageProgressBar(int value, String name)
		{
			setOpaque(true);
			setName(name);
			setMinimum(0);
			setMaximum(100);
			setValue(50);
			setForeground(generateColor(value));
			setBackground(BACKGROUND_COLOR);
			setBorderPainted(false);
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, boolean VerticallyAtTop, boolean isCentered, Color bgc)
		{
			setName(name);
			addComponentListener(this);
			real_text = text;
			if(isCentered)
			{
				real_text = "<div style='text-align:center;'>" + real_text + "</div>";
			}
			setText(real_text);
			setForeground(Color.WHITE);
			if(bgc == null)
			{
				bgc = BACKGROUND_COLOR;
			}
			setBackground(bgc);
			setOpaque(true);
			setVisible(true);
			if(VerticallyAtTop)
			{
				setVerticalAlignment(SwingConstants.NORTH);
			}
		}
		
		public void setText(String text)
		{
			real_text = text;
			super.setText("<html><body width='" + getWidth() + "'><font size=\"3\" color= \"white\">" + text +"</font></body></html>");
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
	}
	public static final int CELL_HEIGHT = 10;
}
