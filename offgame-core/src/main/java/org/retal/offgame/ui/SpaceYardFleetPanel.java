package org.retal.offgame.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Date;

import javax.swing.*;

import org.retal.offgame.core.*;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.units.Unit;
import org.retal.offgame.core.units.UnitBuildQueueElement;

public class SpaceYardFleetPanel extends InfoPanel 
{
	public SpaceYardFleetPanel(String name, Player player) throws IOException 
	{
		super(name, player);
		setOpaque(false);
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets.bottom = 3;
		add(new TextLabel("<div style='text-align:center;'><font color='red'>Нельзя использовать верфь, пока на ней ведутся строительные работы, или идет подготовка к ним!</font></div>", constraints.gridx + "." + constraints.gridy, false), constraints);
		constraints.weightx = 0.0f;
		y_offset = 1;
		requirements_panels = new RequirementsPanel[Unit.SHIPS_AMOUNT];
		for(int i = 0; i < Unit.SHIPS_AMOUNT; i++)
		{
			addRow(Unit.SOLAR_SATELLITE + i + y_offset);
		}
		y_offset_after = y_offset + Unit.SHIPS_AMOUNT;
		constraints.gridx = 1;
		constraints.gridy = y_offset_after;
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", "" + constraints.gridx + "." +constraints.gridy, false), constraints);
		constraints.gridy = y_offset_after + 1;
		constraints.fill = GridBagConstraints.VERTICAL;
		add(new BuildQueueList(5, 25), constraints);
		// кнопка отмены
		constraints.gridx = 1;
		constraints.gridy = y_offset_after + 3;
		constraints.fill = GridBagConstraints.BOTH;
		add(new TextLabel("", "" + constraints.gridx + "." +constraints.gridy, false), constraints);
	}
	
	private void addRow(int row) throws IOException
	{
		int code = row - y_offset;
		String header = current_planet.getUnits()[code].generateHeader();
		double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
		String description = current_planet.getUnits()[code].generateDescription(resources);
		// добавить остаток ресурсов
		constraints.fill = GridBagConstraints.BOTH;
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
		
		add(new BuildAndInputField("" + constraints.gridx + "." +constraints.gridy), constraints);
		add(Box.createHorizontalStrut(80), constraints);
	}
	
	protected int[] getBuildingTimeArray(int code)
	{
		long total = current_planet.getUnits()[code].calcBuildingTime(current_planet.getBuildings()[Building.SPACE_YARD].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel());
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	
	// можно вызывать только для строящегося юнита 
	protected int[] getRemainingTime(int code, Date date)
	{
		long total = (current_planet.getUnits()[code].getBuildDate().getTime() - date.getTime()) / 1000;
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
	}
	
	// возвращает суммарное время строительства всей очереди
	// нельзя вызывать, не проверив, есть ли в очереди хотя бы один элемент
	protected int[] getFullRemainingTime(Date date)
	{
		long total = (current_planet.getUnits()[current_planet.getUnitQueueElem(0).getCode()].getBuildDate().getTime() - date.getTime()) / 1000 + current_planet.getUnits()[current_planet.getUnitQueueElem(0).getCode()].calcBuildingTime(current_planet.getBuildings()[Building.SPACE_YARD].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel()) * (current_planet.getUnitQueueElem(0).getAmount() - 1);
		int size = current_planet.getUnitQueueSize();
		for(int i = 1; i < size; i++)
		{
			total += current_planet.getUnits()[current_planet.getUnitQueueElem(i).getCode()].calcBuildingTime(current_planet.getBuildings()[Building.SPACE_YARD].getLevel(), current_planet.getBuildings()[Building.NANITE_FACTORY].getLevel()) * current_planet.getUnitQueueElem(i).getAmount();
		}
		int day = (int)(total / 86400);
		int hours = (int)((total % 86400) / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {day, hours, minutes, seconds};
		return answer;
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
					list[i].setVisible(current_planet.isBuildingInBuildingQueue(Building.SPACE_YARD));
				}
				if((coords[1] >= y_offset) && (coords[1] < y_offset_after))
				{
					String header = current_planet.getUnits()[coords[1] - y_offset].generateHeader();
					double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
					String description = current_planet.getUnits()[coords[1] - y_offset].generateDescription(resources);
					if(coords[0] == 1)
					{
						((TextLabel)list[i]).setText(header + "<br>" + description + createTimeString(coords[1] - y_offset));
					}
				}
				if(coords[1] >= y_offset_after)
				{
					String text = "";
					switch(coords[1] - y_offset_after)
					{
						case 0:
							if(current_planet.getUnitQueueSize() > 0)
							{
								int[] digits = getRemainingTime(current_planet.getUnitQueueElem(0).getCode(), new Date());
								text = "<div style='text-align:center;'>Сейчас производится:<br>" + current_planet.getUnits()[current_planet.getUnitQueueElem(0).getCode()].generateHeaderWithoutLevel() + " " + String.format("%02d:%02d:%02d:%02d", digits[0], digits[1], digits[2], digits[3]) + "</div>";
								
							}
							break;
						case 3:
							if(current_planet.getUnitQueueSize() > 0)
							{
								int[] digits = getFullRemainingTime(new Date());
								text = "<div style='text-align:center;'>Оставшееся время " + String.format("%02d дн. %02d ч. %02d мин. %02d сек.", digits[0], digits[1], digits[2], digits[3]) + "</div>";
							}
							break;
					}
					((TextLabel)list[i]).setText(text);
				}
			}
			else
			{
				if(list[i] instanceof BuildAndInputField)
				{
					String[] s = list[i].getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					Component[] components = ((BuildAndInputField)list[i]).getComponents();
					boolean requirements_flag = current_planet.requirementsMet(EntityCategory.FLEET, coords[1] - y_offset);
					boolean construction_flag = current_planet.isBuildingInBuildingQueue(Building.SPACE_YARD);
					for(int j = 0; j < components.length; j++)
					{
						components[j].setVisible(requirements_flag && !construction_flag);
						components[j].setEnabled(requirements_flag && !construction_flag);
					}
				}
			}
			if(list[i] instanceof BuildQueueList)
			{
				int length = current_planet.getUnitQueueSize();
				String text = "";
				for(int j = 0; j < length; j++)
				{
					UnitBuildQueueElement elem = current_planet.getUnitQueueElem(j);
					text += elem.getAmount() + " \"" + current_planet.getUnits()[elem.getCode()].generateHeaderWithoutLevel() + "\"";
					if(j != 0)
					{
						text += "\n";
					}
					else
					{
						text += " (Производится)\n";
					}
				}
				((JTextArea)((BuildQueueList)list[i]).getViewport().getComponent(0)).setText(text);
				list[i].setVisible(length != 0);
			}
		}
		for(int i = 0; i < requirements_panels.length; i++)
		{
			int[] buildings_required = current_planet.getUnits()[i].getRequiredBuildings();
			int[] technologies_required = current_planet.getUnits()[i].getRequiredTechnologies();
			requirements_panels[i].updatePanelUI(buildings_required, technologies_required, current_planet.getBuildings(), player.getTechs());
			requirements_panels[i].setVisible(!current_planet.requirementsMet(EntityCategory.FLEET, i));
		}
	}
	
	private class BuildingImg extends JLabel
	{
		public BuildingImg(int img_num) throws IOException
		{
			setIcon(new ImageIcon(getClass().getResource("/un/" + img_num + ".gif")));
			setBackground(BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, boolean verticallyAtTop)
		{	
			setName(name);
			addComponentListener(this);
			real_text = text;
			setText(real_text);
			setForeground(Color.WHITE);
			setBackground(BACKGROUND_COLOR);
			setOpaque(true);
			setVisible(true);
			if(verticallyAtTop)
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
		
		protected String real_text;
	}
	
	private class BuildAndInputField extends JPanel implements ActionListener, KeyListener, FocusListener
	{
		public BuildAndInputField(String name) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			build_button = new JButton("Заказ");
			build_button.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			build_button.setBackground(new Color(102, 102, 102));
			build_button.setForeground(Color.WHITE);
			build_button.addActionListener(this);
			amount_field = new JTextField("0", 7);
			amount_field.setHorizontalAlignment(JTextField.CENTER);
			amount_field.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			amount_field.setBackground(new Color(102, 102, 102));
			amount_field.setForeground(Color.WHITE);
			amount_field.addKeyListener(this);
			amount_field.addFocusListener(this);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.0f;
			constraints.weighty = 0.5f;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.NONE;
			add(build_button, constraints);
			constraints.gridy = 1;
			add(amount_field, constraints);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String[] s = getName().split("\\.");
			int code = Integer.parseInt(s[1]) - y_offset;
			try
			{
				int amount = Integer.parseInt(amount_field.getText());
				current_planet.addUnitBuildingQueue(code, amount);
			}
			catch(NumberFormatException ex)
			{
				
			}
			amount_field.setText("0");
			requestFocusInWindow();
		}
		
		public void keyTyped(KeyEvent e) 
		{
			
		}

		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				requestFocusInWindow();
				amount_field.setText("0");
			}
		}

		public void keyReleased(KeyEvent e) 
		{
			
		}
		
		public void focusGained(FocusEvent e) 
		{
			amount_field.setText("");
		}

		public void focusLost(FocusEvent e) 
		{
			if(!build_button.isRequestFocusEnabled())
			{
				amount_field.setText("0");
			}
		}
		
		private JButton build_button;
		private JTextField amount_field;
	}
	
	private class BuildQueueList extends JScrollPane
	{
		public BuildQueueList(int rows, int columns)
		{
			JTextArea area = new JTextArea(rows, columns);
			setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
			setWheelScrollingEnabled(false);
			area.setBackground(new Color(102, 102, 102));
			area.setForeground(Color.WHITE);
			area.setFocusable(false);
			area.setEditable(false);
			add(area);
			setViewportView(area);
		}
	}
	
	private RequirementsPanel[] requirements_panels;
	private int y_offset_after;
}
