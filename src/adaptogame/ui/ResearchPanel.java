package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Date;

import javax.swing.*;

import adaptogame.core.EntityCategory;
import adaptogame.core.Player;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class ResearchPanel extends InfoPanel 
{
	public ResearchPanel(String name, Player player) throws IOException
	{
		super(name, player);
		setOpaque(false);
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets.bottom = 3;
		y_offset = 0;
		requirements_panels = new RequirementsPanel[17];
		for(int i = 0; i < Technology.RESEARCHES_AMOUNT; i++)
		{
			addRow(Technology.ESPIONAGE + i + y_offset);
		}
	}
	
	private void addRow(int row) throws IOException
	{
		int code = row;
		String header = player.getTechs()[code].generateHeader();
		double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
		String description = player.getTechs()[code].generateDescription(resources);
		// добавить остаток ресурсов
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = row;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		constraints.insets.right = 3;
		add(new BuildingImg(code), constraints);
		add(Box.createRigidArea(new Dimension(new BuildingImg(code).getIcon().getIconHeight() + 6, new BuildingImg(code).getIcon().getIconHeight() + 6)), constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0f;
		add(new TextLabel(header + "<br>" + description + createTimeString(code), "" + constraints.gridx + "." +constraints.gridy, true), constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		constraints.insets.right = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		//
		constraints.fill = GridBagConstraints.HORIZONTAL;
		int[] buildings_required = player.getTechs()[code].getRequiredBuildings();
		int[] technologies_required = player.getTechs()[code].getRequiredTechnologies();
		requirements_panels[code] = new RequirementsPanel(buildings_required, technologies_required, current_planet.getBuildings(), player.getTechs());
		add(requirements_panels[code], constraints);
		//
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel(generateButtonText(code),  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		add(Box.createHorizontalStrut(80), constraints);
	}
	
	protected int[] getBuildingTimeArray(int code)
	{
		long total = player.getTechs()[code].calcBuildingTime(current_planet.getBuildings()[Building.LABORATORY].getLevel());
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	// можно вызывать только для активного ислледования 
	protected int[] getRemainingTime(int code, Date date)
	{
		long total = (player.getTechs()[code].getBuildDate().getTime() - date.getTime()) / 1000;
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	
	protected String generateButtonText(int row)
	{
		String s = "<div style='text-align: center'>";
		if(current_planet.requirementsMet(EntityCategory.RESEARCH, row))
		{
			int active = player.getActiveResearch();
			if(active == -1)
			{
				if(current_planet.isResearchable(row))
				{
					s += "<font color='lime'><u>Исследовать уровень " + (player.getTechs()[row].getLevel() + 1) + "</u></font>";
				}
				else
				{
					s += "<font color='red'>Исследовать уровень " + (player.getTechs()[row].getLevel() + 1) + "</font>";
				}
			}
			else
			{
				if(row == active)
				{
					int[] digits = getRemainingTime(row, new Date());
					s += String.format("%02d:%02d:%02d:%02d", digits[0], digits[1], digits[2], digits[3]);
				}
				else
				{
					s += "-";
				}
				
			}
		}
		else
		{
			s += "";
		}
		s += "</div>";
		return s;
	}
	
	public void updatePanelUI()
	{
		Component[] list = getComponents();
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] instanceof TextLabel)
			{
				String[] s = ((TextLabel)list[i]).getName().split("\\.");
				int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
				if(coords[1] >= y_offset)
				{
					String header = player.getTechs()[coords[1]].generateHeader() + " " + player.getTechs()[coords[1]].generateEnergyChange();
					double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
					String description = player.getTechs()[coords[1]].generateDescription(resources);
					switch(coords[0])
					{
						case 1:
							((TextLabel)list[i]).setText(header + "<br>" + description + createTimeString(coords[1]));
							break;
						case 2:
							((TextLabel)list[i]).setText(generateButtonText(coords[1] - y_offset));
							break;
					}	
				}
			}
		}
		for(int i = 0; i < requirements_panels.length; i++)
		{
			int[] buildings_required = player.getTechs()[i].getRequiredBuildings();
			int[] technologies_required = player.getTechs()[i].getRequiredTechnologies();
			requirements_panels[i].updatePanelUI(buildings_required, technologies_required, current_planet.getBuildings(), player.getTechs());
			if(current_planet.requirementsMet(EntityCategory.RESEARCH, i))
			{
				requirements_panels[i].setVisible(false);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() instanceof ResearchPanel)
		{
			Component[] list = ((ResearchPanel)e.getSource()).getComponents();
			for(int i = 0; i < list.length; i++)
			{
				if(list[i] instanceof TextLabel)
				{
					String[] s = ((TextLabel)list[i]).getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					if((coords[1] >= y_offset) && (coords[0] == 2) && (!requirements_panels[coords[1] - y_offset].isVisible()))
					{
						if((list[i].contains(new Point(e.getX() - list[i].getX(), e.getY() - list[i].getY()))) && current_planet.isResearchable(coords[1]) && (player.getActiveResearch() == Player.NO_ACTIVE_RESEARCH))
						{
							current_planet.startResearching(coords[1]);
							updatePanelUI();
						}
					}
				}
			}
		}
	}
	
	private class BuildingImg extends JLabel
	{
		public BuildingImg(int img_num) throws IOException
		{
			setIcon(new ImageIcon(getClass().getResource("/rs/" + img_num + ".gif")));
			setBackground(BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, boolean VerticallyAtTop)
		{	
			setName(name);
			addComponentListener(this);
			real_text = text;
			setText(real_text);
			setForeground(Color.WHITE);
			setBackground(BACKGROUND_COLOR);
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
	
	private RequirementsPanel[] requirements_panels;
}
