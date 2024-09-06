package org.retal.offgame.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.retal.offgame.core.EntityCategory;
import org.retal.offgame.core.Player;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class TechTreePanel extends InfoPanel 
{
	public TechTreePanel(String name, Player player)
	{
		super(name, player);
		setOpaque(false);
		EntityCategory type = EntityCategory.NO_CATEGORY;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.weightx = 0.4f;
		add(new TextLabel(type, false, -1, "Постройки", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.weightx = 0.6f;
		constraints.gridx = 1;
		constraints.insets.right = 0;
		add(new TextLabel(type, false, -1, "Требования", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		y_offset_buildings = 1;
		type = EntityCategory.BUILDING;
		for(int i = 0; i < current_planet.getBuildings().length; i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i + y_offset_buildings;
			constraints.weightx = 0.4f;
			constraints.insets.right = 3;
			add(new TextLabel(type, true, i, current_planet.getBuildings()[i].generateHeaderWithoutLevel(), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
			constraints.gridx = 1;
			constraints.weightx = 0.6f;
			constraints.insets.right = 0;
			add(new TextLabel(type, false, i, generateRequirementsText(type, i), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
		}
		constraints.gridx = 0;
		constraints.gridy = y_offset_buildings + current_planet.getBuildings().length;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.weightx = 0.4f;
		type = EntityCategory.NO_CATEGORY;
		add(new TextLabel(type, false, -1, "Исследования", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.weightx = 0.6f;
		constraints.gridx = 1;
		constraints.insets.right = 0;
		add(new TextLabel(type, false, -1, "Требования", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		y_offset_research = y_offset_buildings + current_planet.getBuildings().length + 1;
		type = EntityCategory.RESEARCH;
		for(int i = 0; i < player.getTechs().length; i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i + y_offset_research;
			constraints.weightx = 0.4f;
			constraints.insets.right = 3;
			add(new TextLabel(type, true, i, player.getTechs()[i].generateHeaderWithoutLevel(), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
			constraints.gridx = 1;
			constraints.weightx = 0.6f;
			constraints.insets.right = 0;
			add(new TextLabel(type, false, i, generateRequirementsText(type, i), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
		}
		constraints.gridx = 0;
		constraints.gridy = y_offset_research + player.getTechs().length;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.weightx = 0.4f;
		type = EntityCategory.NO_CATEGORY;
		add(new TextLabel(type, false, -1, "Флот", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.weightx = 0.6f;
		constraints.gridx = 1;
		constraints.insets.right = 0;
		add(new TextLabel(type, false, -1, "Требования", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		y_offset_fleet = y_offset_research + player.getTechs().length + 1;
		type = EntityCategory.FLEET;
		for(int i = 0; i < Unit.SHIPS_AMOUNT; i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i + y_offset_fleet;
			constraints.weightx = 0.4f;
			constraints.insets.right = 3;
			add(new TextLabel(type, true, i, current_planet.getUnits()[i].generateHeaderWithoutLevel(), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
			constraints.gridx = 1;
			constraints.weightx = 0.6f;
			constraints.insets.right = 0;
			add(new TextLabel(type, false, i, generateRequirementsText(type, i), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
		}
		constraints.gridx = 0;
		constraints.gridy = y_offset_fleet + Unit.SHIPS_AMOUNT;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.weightx = 0.4f;
		type = EntityCategory.NO_CATEGORY;
		add(new TextLabel(type, false, -1, "Оборона", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.weightx = 0.6f;
		constraints.gridx = 1;
		constraints.insets.right = 0;
		add(new TextLabel(type, false, -1, "Требования", "" + constraints.gridx + "." +constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		y_offset_defense = y_offset_fleet + Unit.SHIPS_AMOUNT + 1;
		type = EntityCategory.DEFENSE;
		for(int i = 0; i < Unit.DEFENSE_AMOUNT; i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i + y_offset_defense;
			constraints.weightx = 0.4f;
			constraints.insets.right = 3;
			add(new TextLabel(type, true, Unit.ROCKET_LAUNCHER + i, current_planet.getUnits()[Unit.ROCKET_LAUNCHER + i].generateHeaderWithoutLevel(), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
			constraints.gridx = 1;
			constraints.weightx = 0.6f;
			constraints.insets.right = 0;
			add(new TextLabel(type, false, Unit.ROCKET_LAUNCHER + i, generateRequirementsText(type, Unit.ROCKET_LAUNCHER + i), "" + constraints.gridx + "." +constraints.gridy, null), constraints);
		}
	}
	
	private String generateRequirementsText(EntityCategory type, int index)
	{
		String text = "<div style=\"padding-left:5px;\">";
		int[] required_buildings = null;
		int[] required_techs = null;
		switch(type)
		{
			case BUILDING:
				required_buildings = current_planet.getBuildings()[index].getRequiredBuildings();
				required_techs = current_planet.getBuildings()[index].getRequiredTechnologies();
				break;
			case RESEARCH:
				required_buildings = player.getTechs()[index].getRequiredBuildings();
				required_techs = player.getTechs()[index].getRequiredTechnologies();
				break;
			case FLEET:
			case DEFENSE:
				required_buildings = current_planet.getUnits()[index].getRequiredBuildings();
				required_techs = current_planet.getUnits()[index].getRequiredTechnologies();
				break;
			case NO_CATEGORY:
				break;
		}
		for(int j = 0; j < required_buildings.length; j++)
		{
			if(required_buildings[j] != 0)
			{
				String color = "";
				if(current_planet.getBuildings()[j].getLevel() >= required_buildings[j])
				{
					color = "lime";
				}
				else
				{
					color = "red";
				}
				text += "<font color='" + color + "'>" + current_planet.getBuildings()[j].generateHeaderWithoutLevel() + " (уровень " + required_buildings[j] + "). Ваш уровень: " + current_planet.getBuildings()[j].getLevel() + "</font><br>";
			}
		}
		for(int j = 0; j < required_techs.length; j++)
		{
			if(required_techs[j] != 0)
			{
				String color = "";
				if(player.getTechs()[j].getLevel() >= required_techs[j])
				{
					color = "lime";
				}
				else
				{
					color = "red";
				}
				text += "<font color='" + color + "'>" + player.getTechs()[j].generateHeaderWithoutLevel() + " (уровень " + required_techs[j] + "). Ваш уровень: " + player.getTechs()[j].getLevel() + "</font><br>";
			}
		}
		text += "</div>";
		return text;
	}
	
	public void updatePanelUI()
	{
		Component[] list = getComponents();
		for(int i = 0; i < list.length; i++)
		{
			String[] s = list[i].getName().split("\\.");
			int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
			if((coords[0] == 1) && (coords[1] >= y_offset_buildings) && (coords[1] < y_offset_research - 1))
			{
				((TextLabel)list[i]).setText(generateRequirementsText(EntityCategory.BUILDING, coords[1] - y_offset_buildings));
			}
			if((coords[0] == 1) && (coords[1] >= y_offset_research) && (coords[1] < y_offset_fleet - 1))
			{
				((TextLabel)list[i]).setText(generateRequirementsText(EntityCategory.RESEARCH, coords[1] - y_offset_research));
			}
			if((coords[0] == 1) && (coords[1] >= y_offset_fleet) && (coords[1] < y_offset_defense - 1))
			{
				((TextLabel)list[i]).setText(generateRequirementsText(EntityCategory.FLEET, coords[1] - y_offset_fleet));
			}
			if((coords[0] == 1) && (coords[1] >= y_offset_defense))
			{
				((TextLabel)list[i]).setText(generateRequirementsText(EntityCategory.DEFENSE, Unit.ROCKET_LAUNCHER + coords[1] - y_offset_defense));
			}
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(EntityCategory type, boolean hasImage, int code, String text, String name, Color bgc)
		{
			String folder = "/";
			int param = Image.SCALE_SMOOTH;
			switch(type)
			{
				case BUILDING:
					folder += "bl/";
					switch(code)
					{
						case Building.TERRAFORMER:
							param = Image.SCALE_REPLICATE;
							break;
					}
					break;
				case RESEARCH:
					folder += "rs/";
					switch(code)
					{
						case Technology.EXPEDITION_TECHNOLOGY:
						case Technology.INTEGERGALACTICAL_RESEARCH_NETWORK:
						case Technology.GRAVITY_TECHNOLOGY:
							param = Image.SCALE_REPLICATE;
							break;
					}
					break;
				case FLEET:
				case DEFENSE:
					folder += "un/";
					switch(code)
					{
						case Unit.PROCESSOR:
						case Unit.DEATH_STAR:
						case Unit.PLASMA_CANNON:
						case Unit.LIGHT_PLANETARY_SHIELD:
						case Unit.HEAVY_PLANETARY_SHIELD:
							param = Image.SCALE_REPLICATE;
							break;
					}
					break;
				default:
					break;
			}
			if(hasImage)
			{
				ImageIcon img = new ImageIcon(getClass().getResource(folder + code + ".gif"));
				setIcon(new ImageIcon(img.getImage().getScaledInstance(40, 40, param)));
			}
			setName(name);
			addComponentListener(this);
			real_text = text;
			setText(real_text);
			setForeground(Color.WHITE);
			if(bgc == null)
			{
				bgc = BACKGROUND_COLOR;
			}
			setBackground(bgc);
			setOpaque(true);
			setVisible(true);
		}
		
		public void setText(String text)
		{
			real_text = text;
			if(getIcon() == null)
			{
				super.setText("<html><body width='" + getWidth() + "'><font size=\"3\" color= \"white\">" + text +"</font></body></html>");
			}
			else
			{
				super.setText("<html>" + "<font size=\"3\" color= \"white\">" + text +"</font></html>");
			}
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
	
	private int y_offset_buildings;
	private int y_offset_research;
	private int y_offset_fleet;
	private int y_offset_defense;
}
