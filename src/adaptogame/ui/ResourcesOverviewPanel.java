package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

import adaptogame.core.Building;
import adaptogame.core.Planet;
import adaptogame.core.Player;

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
		String opening = "<font color='lime'>";
		if(current_planet.getCurrentEnergy() < 0)
		{
			opening = "<font color='red'>";
		}
		add(new TextLabel(opening + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getCurrentEnergy()) + "</font>", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
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
		color_status = generateColor(percentage, true);
		add(new TextLabel("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getMetalCapacity() - current_planet.getCurrentMetal()) / (current_planet.getMetalProduction() / 3600))) + "</font> Металл", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.weightx = 0.3f;
		constraints.gridx = 2;
		add(new TextLabel("" + percentage + "%", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		//
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy, true), constraints);
		//
		percentage = (int)(current_planet.getCurrentCrystal() / current_planet.getCrystalCapacity() * 100);
		color_status = generateColor(percentage, true);
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
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy, true), constraints);
		//
		percentage = (int)(current_planet.getCurrentDeiterium() / current_planet.getDeiteriumCapacity() * 100);
		color_status = generateColor(percentage, true);
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
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy, true), constraints);
		//
		percentage = (int)(current_planet.getProductionEfficiency() * 100);
		color_status = generateColor(percentage, false);
		constraints.gridx = 0;
		constraints.gridy = 4 + offset;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		add(new TextLabel("Эффективность добычи ресурсов шахтами", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 2;
		add(new TextLabel("" + percentage + "%", constraints.gridx + "." + constraints.gridy, false, true, null), constraints);
		constraints.gridx = 4;
		constraints.insets.right = 0;
		//
		add(new StorageProgressBar(percentage, constraints.gridx + "." + constraints.gridy, false), constraints);
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
		Component[] list = getComponents();
		int amount;
		int percentage;
		Color color_status;
		boolean isRightCell;
		for(int i = 0; i < list.length; i++)
		{
			amount = 0;
			if(list[i] instanceof TextLabel)
			{
				String[] s = ((TextLabel)list[i]).getName().split("\\.");
				int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
				switch(coords[1])
				{
					case 3:
					case 4:
					case 5:
						switch(coords[0])
						{
							case 1:
							case 2:
							case 3:
								if(coords[1] - coords[0] == 2)
								{
									((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[coords[0]].calcGathering()));
								}
								break;
							case 4:
								((TextLabel)list[i]).setCenteredText("<font color='red'>-" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[coords[1] - 2].calcConsuming()) + "</font>");
								break;
						}
						break;
					case 6:
					case 7:
						if(coords[0] == 4)
						{
							int index;
							if (coords[1] == 6)
							{
								index = Building.POWER_STATION;
							}
							else
							{
								index = Building.NUCLEAR_STATION;
							}
							((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getBuildings()[index].calcGathering()) + "</font>");
						}
						break;
					case 8:
						switch(coords[0])
						{
							case 1:
								((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getMetalProduction()));
								break;
							case 2:
								((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getCrystalProduction()));
								break;
							case 3:
								((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getDeiteriumProduction()));
								break;
							case 4:
								String opening = "<font color='lime'>";
								if(current_planet.getCurrentEnergy() < 0)
								{
									opening = "<font color='red'>";
								}
								((TextLabel)list[i]).setCenteredText(opening + NumberFormat.getNumberInstance(Locale.US).format((int)current_planet.getCurrentEnergy()) + "</font>");
								break;
						}
						break;
					case 11:
						isRightCell = false;
						switch(coords[0])
						{
							case 2:
								amount = (int)(current_planet.getMetalProduction() * 24);
								isRightCell = true;
								break;
							case 3:
								amount = (int)(current_planet.getMetalProduction() * 24 * 7);
								isRightCell = true;
								break;
							case 4:
								amount = (int)(current_planet.getMetalProduction() * 24 * 7 * 30);
								isRightCell = true;
								break;
						}
						if(isRightCell)
						{
							((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format(amount) + "</font>");
						}
						break;
					case 12:
						isRightCell = false;
						switch(coords[0])
						{
							case 2:
								amount = (int)(current_planet.getCrystalProduction() * 24);
								isRightCell = true;
								break;
							case 3:
								amount = (int)(current_planet.getCrystalProduction() * 24 * 7);
								isRightCell = true;
								break;
							case 4:
								amount = (int)(current_planet.getCrystalProduction() * 24 * 7 * 30);
								isRightCell = true;
								break;
						}
						if(isRightCell)
						{
							((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format(amount) + "</font>");
						}
						break;
					case 13:
						isRightCell = false;
						switch(coords[0])
						{
							case 2:
								amount = (int)(current_planet.getDeiteriumProduction() * 24);
								isRightCell = true;
								break;
							case 3:
								amount = (int)(current_planet.getDeiteriumProduction() * 24 * 7);
								isRightCell = true;
								break;
							case 4:
								amount = (int)(current_planet.getDeiteriumProduction() * 24 * 7 * 30);
								isRightCell = true;
								break;
						}
						if(isRightCell)
						{
							((TextLabel)list[i]).setCenteredText("<font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format(amount) + "</font>");
						}
						break;
					case 15:
						percentage = (int)(current_planet.getCurrentMetal() / current_planet.getMetalCapacity() * 100);
						color_status = generateColor(percentage, true);
						switch(coords[0])
						{
							case 0:
								((TextLabel)list[i]).setCenteredText("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getMetalCapacity() - current_planet.getCurrentMetal()) / (current_planet.getMetalProduction() / 3600))) + "</font> Металл");
								break;
							case 2:
								((TextLabel)list[i]).setCenteredText("" + percentage + "%");
								break;
						}
						break;
					case 16:
						percentage = (int)(current_planet.getCurrentCrystal() / current_planet.getCrystalCapacity() * 100);
						color_status = generateColor(percentage, true);
						switch(coords[0])
						{
							case 0:
								((TextLabel)list[i]).setCenteredText("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getCrystalCapacity() - current_planet.getCurrentCrystal()) / (current_planet.getCrystalProduction() / 3600))) + "</font> Кристалл");
								break;
							case 2:
								((TextLabel)list[i]).setCenteredText("" + percentage + "%");
								break;
						}
						break;
					case 17:
						percentage = (int)(current_planet.getCurrentDeiterium() / current_planet.getDeiteriumCapacity() * 100);
						color_status = generateColor(percentage, true);
						switch(coords[0])
						{
							case 0:
								((TextLabel)list[i]).setCenteredText("<font color=\"#" + getHexColor(color_status) + "\">" + createTimeString((long)((current_planet.getDeiteriumCapacity() - current_planet.getCurrentDeiterium()) / (current_planet.getDeiteriumProduction() / 3600))) + "</font> Дейтерий");
								break;
							case 2:
								((TextLabel)list[i]).setCenteredText("" + percentage + "%");
								break;
						}
						break;
					case 18:
						if(coords[0] == 2)
						{
							percentage = (int)(current_planet.getProductionEfficiency() * 100);
							color_status = generateColor(percentage, false);
							((TextLabel)list[i]).setCenteredText("" + percentage + "%");
						}
						break;
				}
				
			}
			if(list[i] instanceof StorageProgressBar)
			{
				String[] s = ((StorageProgressBar)list[i]).getName().split("\\.");
				int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
				switch(coords[1])
				{
					case 15:
						percentage = (int)(current_planet.getCurrentMetal() / current_planet.getMetalCapacity() * 100);
						color_status = generateColor(percentage, true);
						if(coords[0] == 4)
						{
							((StorageProgressBar)list[i]).setValue(percentage);
						}
						break;
					case 16:
						percentage = (int)(current_planet.getCurrentCrystal() / current_planet.getCrystalCapacity() * 100);
						color_status = generateColor(percentage, true);
						if(coords[0] == 4)
						{
							((StorageProgressBar)list[i]).setValue(percentage);
						}
						break;
					case 17:
						percentage = (int)(current_planet.getCurrentDeiterium() / current_planet.getDeiteriumCapacity() * 100);
						color_status = generateColor(percentage, true);
						if(coords[0] == 4)
						{
							((StorageProgressBar)list[i]).setValue(percentage);
						}
						break;
					case 18:
						percentage = (int)(current_planet.getProductionEfficiency() * 100);
						color_status = generateColor(percentage, false);
						if(coords[0] == 4)
						{
							((StorageProgressBar)list[i]).setValue(percentage);
						}
						break;
				}
			}
		}
	}
	
	public static Color generateColor(int value, boolean isFromGreenToRed)
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
		if(!isFromGreenToRed)
		{
			red = end.getRed();
			green = end.getGreen();
			blue = end.getBlue();
			delta_red = (double)(start.getRed() - red) / 100;
			delta_green = (double)(start.getGreen() - green) / 100;
			delta_blue = (double)(start.getBlue() - blue) / 100;
		}
		Color answer = new Color(red + (int)(delta_red * value), green + (int)(delta_green * value), blue + (int)(delta_blue * value));
		return answer;
	}
	
	private class StorageProgressBar extends JProgressBar
	{
		public StorageProgressBar(int value, String name, boolean isFromGreenToRed)
		{
			setOpaque(true);
			setName(name);
			setMinimum(0);
			setMaximum(100);
			setValue(value);
			setForeground(generateColor(value, isFromGreenToRed));
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
				setCenteredText(real_text);
			}
			else
			{
				setText(real_text);
			}
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
		
		public void setCenteredText(String text)
		{
			setText("<div style='text-align:center;'>" + text + "</div>");
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
