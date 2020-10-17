package offgame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.*;

public class BuildingPanel extends InfoPanel 
{
	public BuildingPanel(String name, Planet planet) throws IOException 
	{
		super(name, planet);
		setOpaque(false);
		// добавить резерв для очереди
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		String text;
		for(int i = 0; i < Planet.MAX_BUILD_QUEUE; i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.gridwidth = 2;
			constraints.gridheight = 1;
			constraints.insets.bottom = 3;
			constraints.insets.right = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.NORTH;
			try
			{
				text = current_planet.getBuildings()[current_planet.getQueueElem(i)].generateHeader();
			}
			catch(IndexOutOfBoundsException e)
			{
				text = "";
			}
			add(new TextLabel(text, "" + constraints.gridx + "." +constraints.gridy, false), constraints);
			constraints.gridx = 2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.insets.right = 0;
			try
			{
				if (i == 0)
				{
					int[] digits = getBuildingTimeArray(current_planet.getQueueElem(i));
					text = String.format("%02d:%02d:%02d:%02d", digits[0], digits[1], digits[2], digits[3]);
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				text = "";
			}
			add(new TextLabel("<div style='text-align: center'>" + text +"</div>",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		}
		constraints.gridx = 0;
		constraints.gridy = Planet.MAX_BUILD_QUEUE;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.bottom = 3;
		constraints.insets.right = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new TextLabel("<div style='text-align: center'>Занятость полей</div>", "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new TextLabel(createFieldInfoString(),  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.insets.right = 0;
		add(new TextLabel("<div style='text-align: center'>" + current_planet.getMinTemperature() + " — " + current_planet.getMaxTemperature() + "°C</div>",  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		x_offset = Planet.MAX_BUILD_QUEUE + 1;
		addRow(Building.POWER_STATION + x_offset);
		addRow(Building.METAL_MINES + x_offset);
		addRow(Building.CRYSTAL_MINES + x_offset);
		addRow(Building.DEITERIUM_MINES + x_offset);
		addRow(Building.ROBOT_FACTORY + x_offset);
		addRow(Building.SPACE_YARD + x_offset);
		addRow(Building.LABORATORY + x_offset);
		addRow(Building.METAL_STORAGE + x_offset);
		addRow(Building.CRYSTAL_STORAGE + x_offset);
		addRow(Building.DEITERIUM_STORAGE + x_offset);
		addRow(Building.NUCLEAR_STATION + x_offset);
		addRow(Building.NANITE_FACTORY + x_offset);
		addRow(Building.TERRAFORMER + x_offset);
		addRow(Building.ROCKET_SHAFT + x_offset);
		
	}
	
	private void addRow(int row) throws IOException
	{
		int code = row - x_offset;
		String header = current_planet.getBuildings()[code].generateHeader() + " " + current_planet.getBuildings()[code].generateEnergyChange();
		double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium()};
		String description = current_planet.getBuildings()[code].generateDescription(resources);
		// добавить остаток ресурсов
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridx = 0;
		constraints.gridy = row;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
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
		add(new TextLabel(generateButtonText(code),  "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		add(Box.createHorizontalStrut(80), constraints);
	}
	
	private int[] getBuildingTimeArray(int code)
	{
		long total = current_planet.getBuildings()[code].calcBuildingTime(current_planet.getBuildings()[Building.ROBOT_FACTORY].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel());
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	// можно вызывать только для строящегося здания 
	private int[] getRemainingTime(int code, Date date)
	{
		long total = (current_planet.getBuildings()[code].getBuildDate().getTime() - date.getTime()) / 1000;
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	
	private String createFieldInfoString()
	{
		int fields = current_planet.getFields();
		int fields_taken = current_planet.getTakenFields();
		String font_opening = "<font color='lime'>";
		String font_ending = "</font>";
		if (fields_taken == fields)
		{
			font_opening = "<font color='red'>";
		}
		return "<div style='text-align: center'>" + font_opening + fields_taken + font_ending + " / <font color='red'>" + fields + "</font> (осталось " + (fields - fields_taken) +" свободных полей)</div>";
	}
	
	private String createTimeString(int code)
	{
		int[] time_digits = getBuildingTimeArray(code);
		return String.format("Время строительства: %02d дн. %02d ч. %02d мин. %02d с.", time_digits[0], time_digits[1], time_digits[2], time_digits[3]);
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
				if(coords[1] >= x_offset)
				{
					String header = current_planet.getBuildings()[coords[1] - x_offset].generateHeader() + " " + current_planet.getBuildings()[coords[1] - x_offset].generateEnergyChange();
					double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium()};
					String description = current_planet.getBuildings()[coords[1] - x_offset].generateDescription(resources);
					switch(coords[0])
					{
						case 1:
							((TextLabel)list[i]).setText(header + "<br>" + description + createTimeString(coords[1] - x_offset));
							break;
						case 2:
							((TextLabel)list[i]).setText(generateButtonText(coords[1] - x_offset));
							break;
					}
				}
				else
				{
					if(coords[1] < Planet.MAX_BUILD_QUEUE)
					{
						try
						{
							switch(coords[0])
							{
								case 0:
									String header = current_planet.getBuildings()[current_planet.getQueueElem(coords[1])].generateHeader();
									int index = header.indexOf("(Уровень");
									int level = current_planet.getBuildings()[current_planet.getQueueElem(coords[1])].getLevel() + 1;
									for(int j = 0; j < coords[1]; j++)
									{
										if(current_planet.getQueueElem(j) == current_planet.getQueueElem(coords[1]))
										{
											level++;
										}
									}
									if(current_planet.getBuildings()[current_planet.getQueueElem(coords[1])].getLevel() != 0)
									{
										((TextLabel)list[i]).setText(header.substring(0, index) + level);
									}
									else
									{
										((TextLabel)list[i]).setText(header + level);
									}
									break;
								case 2:
									if (coords[1] == 0)
									{
										int[] digits = getRemainingTime(current_planet.getQueueElem(coords[1]), new Date());
										((TextLabel)list[i]).setText("<div style='text-align: center'>" + String.format("%02d:%02d:%02d:%02d", digits[0], digits[1], digits[2], digits[3]) + "<br><font color='lime'>" + new SimpleDateFormat("dd/mm kk:mm:ss").format(current_planet.getBuildings()[current_planet.getQueueElem(coords[1])].getBuildDate()) +"</font></div>");
									}
									else
									{
										((TextLabel)list[i]).setText("");
									}
									
									break;
							}
						}
						catch(IndexOutOfBoundsException e)
						{
							((TextLabel)list[i]).setText("");
						}
					}
					else
					{
						if(coords[0] == 1)
						{
							((TextLabel)list[i]).setText(createFieldInfoString());
						}
					}
				}
			}
		}
	}
	
	private String generateButtonText(int row)
	{
		String s = "<div style='text-align: center'>";
		// тут добавить проверку на очередь
		if(current_planet.getQueueSize() == 0)
		{
			if(current_planet.isBuildable(row))
			{
				s += "<font color='lime'><u>Построить<br>следующий<br>уровень " + (current_planet.getBuildings()[row].getLevel() + 1) + "</u></font>";
			}
			else
			{
				s += "<font color='red'>Построить следующий уровень " + (current_planet.getBuildings()[row].getLevel() + 1) + "</font>";
			}
		}
		else
		{
			s += "<font color='lime'><u>Добавить в очередь</u></font>";
		}
		s += "</div>";
		return s;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() instanceof BuildingPanel)
		{
			Component[] list = ((BuildingPanel)e.getSource()).getComponents();
			for(int i = 0; i < list.length; i++)
			{
				if(list[i] instanceof TextLabel)
				{
					String[] s = ((TextLabel)list[i]).getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					if((coords[0] == 2) && (coords[1] > 0))
					{
						if((list[i].contains(new Point(e.getX() - list[i].getX(), e.getY() - list[i].getY()))) /*&& current_planet.isBuildable(coords[1] - x_offset)*/)
						{
							current_planet.addBuildingQueue(coords[1] - x_offset);
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
			//setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/bl/" + img_num + ".gif"))));
			setIcon(new ImageIcon(getClass().getResource("/bl/" + img_num + ".gif")));
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
			super.setText("<html><body width='" + getWidth() + "'><font size=\"3\" color= \"white\">"+ text +"</font></body></html>");
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
	
	private int x_offset;
}
