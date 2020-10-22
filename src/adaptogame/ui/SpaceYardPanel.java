package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import adaptogame.core.*;
import adaptogame.core.buildings.Building;
import adaptogame.core.units.Unit;

public class SpaceYardPanel extends InfoPanel 
{
	public SpaceYardPanel(String name, Player player) throws IOException 
	{
		super(name, player);
		setOpaque(false);
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets.bottom = 3;
		y_offset = 0;
		requirements_panels = new RequirementsPanel[16];
		for(int i = 0; i < Unit.SHIPS_AMOUNT; i++)
		{
			addRow(Unit.SOLAR_SATELLITE + i + y_offset);
		}
		
	}
	
	private void addRow(int row) throws IOException
	{
		int code = row;
		String header = current_planet.getUnits()[code].generateHeader();
		double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
		String description = current_planet.getUnits()[code].generateDescription(resources);
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
		int[] buildings_required = current_planet.getUnits()[code].getRequiredBuildings();
		int[] technologies_required = current_planet.getUnits()[code].getRequiredTechnologies();
		requirements_panels[code] = new RequirementsPanel(buildings_required, technologies_required, current_planet.getBuildings(), player.getTechs());
		add(requirements_panels[code], constraints);
		//
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("РЕЗЕРВ",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		add(Box.createHorizontalStrut(80), constraints);
	}
	
	protected int[] getBuildingTimeArray(int code)
	{
		long total = current_planet.getUnits()[code].calcBuildingTime(current_planet.getBuildings()[Building.ROBOT_FACTORY].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel());
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	
	private class BuildingImg extends JLabel
	{
		public BuildingImg(int img_num) throws IOException
		{
			setIcon(new ImageIcon(getClass().getResource("/fl/" + img_num + ".gif")));
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
