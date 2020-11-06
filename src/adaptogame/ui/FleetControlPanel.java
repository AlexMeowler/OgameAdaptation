package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

import adaptogame.core.*;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class FleetControlPanel extends InfoPanel 
{
	public FleetControlPanel(String name, Player player) 
	{
		super(name, player);
		setOpaque(false);
		phase = 0;
		ships = new Unit[Unit.SHIPS_AMOUNT];
		coordinates = new JTextField[3];
		resources_loaded = new JTextField[3];
		mission = null;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		add(new PhasePanel(0), constraints);
		add(new PhasePanel(1), constraints);
		add(new PhasePanel(2), constraints);
	}
	
	public void updateCurrentPlanet()
	{
		super.updateCurrentPlanet();
		resetPhase();
	}

	
	protected int[] getRemainingTime(Date fleet_date)
	{
		long total = (fleet_date.getTime() - new Date().getTime()) / 1000;
		int hours = (int)(total / 3600);
		int minutes = (int)((total % 3600) / 60);
		int seconds = (int)(total % 60);
		int[] answer = {hours, minutes, seconds};
		return answer;
	}
	
	public void updatePanelUI()
	{
		PhasePanel panel = (PhasePanel)getComponent(phase);
		panel.setVisible(true);
		panel.setEnabled(true);
		int y_offset = panel.getYOffset();
		int y_offset_fleet_list = panel.getYOffsetFleetList();
		Component[] list = panel.getComponents();
		int fleets_amount = player.getFleetsSize();
		switch(phase)
		{
			case 0:
				for(int i = 0; i < list.length; i++)
				{
					String[] s = list[i].getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					if(coords[1] == 0)
					{
						if(coords[0] == 0)
						{
							((TextLabel)list[i]).setText("Флоты " + player.getFleetsSize() + " / " + player.getMaxFleetsAvailable());
						}
					}
					if(coords[1] - y_offset < fleets_amount)
					{
						list[i].setVisible(true);
						if(coords[0] == 7)
						{
							int[] digits = getRemainingTime(player.getFleet(coords[1] - y_offset).getFromTargetDate());
							((TextLabel)list[i]).setText("<div style='text-align:center;'><font color='lime'>" + String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]) + "</font></div>");
						}
					}
					else
					{
						if(coords[1] < y_offset_fleet_list)
						{
							list[i].setVisible(!(coords[1] - y_offset < Player.MAX_FLEET_AMOUNT));
						}
					}
					if((coords[1] >= y_offset_fleet_list) && (coords[1] < y_offset_fleet_list + Unit.SHIPS_AMOUNT - 1))
					{
						int amount = current_planet.getUnits()[coords[1] - y_offset_fleet_list + 1].getAmount();
						list[i].setVisible(amount != 0);
						if(coords[0] == 5)
						{
							((TextLabel)list[i]).setText("<div style='text-align:center'>" + amount + "</div>");
						}
					}
				}
				break;
			case 1:
				for(int i = 0; i < list.length; i++)
				{
					String[] s = list[i].getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					switch(coords[1])
					{
						case 1:
						case 2:
						case 3:
						case 4:
							if(coords[0] == 1)
							{
								String text = "";
								switch(coords[1])
								{
									case 1:
										try
										{
											int[] int_coordinates = {Integer.parseInt(coordinates[0].getText()), Integer.parseInt(coordinates[1].getText()), Integer.parseInt(coordinates[2].getText())};
											text = NumberFormat.getNumberInstance(Locale.US).format(Fleet.calcDistance(int_coordinates, current_planet.getCoords()));
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
									case 2:
										try
										{
											int[] int_coordinates = {Integer.parseInt(coordinates[0].getText()), Integer.parseInt(coordinates[1].getText()), Integer.parseInt(coordinates[2].getText())};
											text = NumberFormat.getNumberInstance(Locale.US).format(Fleet.calcTotalFuelConsumption(ships, Fleet.calcDistance(int_coordinates, current_planet.getCoords()), (int) speed_percentage.getSelectedItem()));
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
									case 3:
										text = NumberFormat.getNumberInstance(Locale.US).format(Unit.findMaxFleetSpeed(ships));
										break;
									case 4:
										try
										{
											int[] int_coordinates = {Integer.parseInt(coordinates[0].getText()), Integer.parseInt(coordinates[1].getText()), Integer.parseInt(coordinates[2].getText())};
											capacity_left = Fleet.calcTotalCapacity(ships) - Fleet.calcTotalFuelConsumption(ships, Fleet.calcDistance(int_coordinates, current_planet.getCoords()), (int) speed_percentage.getSelectedItem());
											String opening = "";
											String ending = "";
											if(capacity_left < 0)
											{
												opening = "<font color='red'>";
												ending = "</font>";
											}
											text = opening + NumberFormat.getNumberInstance(Locale.US).format(capacity_left) + ending;
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
								}
								((TextLabel)list[i]).setText(text);
							}
							if((coords[0] == 3) && !(list[i] instanceof SpeedField))
							{
								String text = "";
								switch(coords[1])
								{
									case 3:
										try
										{
											int[] int_coordinates = {Integer.parseInt(coordinates[0].getText()), Integer.parseInt(coordinates[1].getText()), Integer.parseInt(coordinates[2].getText())};
											int[] digits = getRemainingTime(new Date(new Date().getTime() + Fleet.calcStartDuration(Fleet.calcDistance(int_coordinates, current_planet.getCoords()), Unit.findMaxFleetSpeed(ships)) + Fleet.calcCleanDuration(Fleet.calcDistance(int_coordinates, current_planet.getCoords()), Unit.findMaxFleetSpeed(ships), 100)));
											text = "<font color='lime'>" + String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]) + "</font>";
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
									case 4:
										try
										{
											int[] int_coordinates = {Integer.parseInt(coordinates[0].getText()), Integer.parseInt(coordinates[1].getText()), Integer.parseInt(coordinates[2].getText())};
											int[] digits = getRemainingTime(new Date(new Date().getTime() + Fleet.calcStartDuration(Fleet.calcDistance(int_coordinates, current_planet.getCoords()), Unit.findMaxFleetSpeed(ships))));
											text = String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]);
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
								}
								((TextLabel)list[i]).setText(text);
							}
							break;
					}
				}
				break;
			case 2:
				list = ((Container)panel.getComponent(2)).getComponents();
				for(int i = 0; i < list.length; i++)
				{
					
					String[] s;
					try
					{
						s = list[i].getName().split("\\.");
					}
					catch(NullPointerException e)
					{
						s = new String[2];
						s[0] = "-1";
						s[1] = "-1";
					}
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					if((coords[1] == 1) && (coords[0] == 1))
					{
						String text = "";
						try
						{
							int[] resources = textFieldsToArray(resources_loaded);
							int sum = 0;
							for(int j = 0; j < resources.length; j++)
							{
								sum += resources[j];
							}
							String opening = "<div style='text-align:center';><font color='lime'>";
							String ending = "</font>";
							if(capacity_left - sum < 0)
							{
								opening = "<font color='red'>";
								ending = "</font></div>";
							}
							text = opening + NumberFormat.getNumberInstance(Locale.US).format(capacity_left - sum) + ending;
						}
						catch(NumberFormatException e)
						{
							text = "<div style='text-align:center';>NaN</div>";
						}
						((TextLabel)list[i]).setText(text);
					}
				}
				break;
		}
	}
	
	public void resetPhase()
	{
		phase = 0;
		Component[] list = getComponents();
		for(int i = 0; i < 3; i++)
		{
			list[i].setVisible(false);
			list[i].setEnabled(false);
			Component[] list_of_list = ((Container)list[i]).getComponents();
			switch(i)
			{
				case 0:
					for(int j = 0; j < list_of_list.length; j++)
					{
						if(list_of_list[j] instanceof InputField)
						{
							((InputField)list_of_list[j]).amount_field.setText("0");
						}
					}
					break;
				case 1:
					int[] coords = current_planet.getCoords();
					for(int j = 0; j < coordinates.length; j++)
					{
						coordinates[j].setText("" + coords[j]);
					}
					break;
				case 2:
					for(int j = 0; j < resources_loaded.length; j++)
					{
						resources_loaded[j].setText("0");
					}
					break;
			}
			
		}
		list[0].setVisible(true);
		list[0].setEnabled(true);
		updatePanelUI();
		requestFocusInWindow();
	}
	
	private void updatePhase()
	{
		phase++;
		if(phase == 2)
		{
			int[] coords = textFieldsToArray(coordinates);
			((TextLabel)((Container)getComponent(phase)).getComponent(0)).setText(coords[0] + ":" + coords[1] + ":" + coords[2] + " - РЕЗЕРВ_ТИП_ОБЪЕКТА");
			((MissionSelectionPanel)((Container)getComponent(phase)).getComponent(1)).updateOptions();
		}
	}
	
	private int[] textFieldsToArray(JTextField[] fields) throws NumberFormatException
	{
		int[] array = new int[fields.length];
		for(int i = 0; i < fields.length; i++)
		{
			array[i] = Integer.parseInt(fields[i].getText());
		}
		return array;
	}
	
	private class PhasePanel extends JPanel implements ActionListener
	{
		public PhasePanel(int phase)
		{
			setVisible(false);
			setOpaque(false);
			setLayout(new GridBagLayout());
			next_button = new JButton();
			next_button.setText("Далее");
			Font button_font = next_button.getFont();
			next_button.setFont(new Font(button_font.getName(), button_font.getStyle(), button_font.getSize() + 4));
			next_button.addActionListener(this);
			switch(phase)
			{
				case 0:
					drawFleetSelection();
					break;
				case 1:
					drawDestinationSelection();
					break;
				case 2:
					drawMissionSelection();
					break;
			}
		}
		
		private void drawFleetSelection()
		{
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 4;
			constraints.gridheight = 1;
			constraints.insets.bottom = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.weightx = 0.4f;
			constraints.ipady = 0;
			add(new TextLabel(false, 0, "Флоты " + player.getFleetsSize() + " / " + player.getMaxFleetsAvailable(), constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.gridx = 4;
			constraints.gridwidth = 5;
			constraints.weightx = 0.6f;
			add(new TextLabel(false, 0, "Экспедиции " + player.getFleetsSize() + "reserved / " + player.getTechs()[Technology.EXPEDITION_TECHNOLOGY].getLevel(), constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.weightx = 0.003f;
			constraints.insets.right = 3;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>№</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 1;
			constraints.weightx = 0.15f;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Задание</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 2;
			constraints.gridwidth = 1;
			constraints.weightx = 0.1f;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Флот<br>(всего)</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			constraints.gridwidth = 1;
			constraints.weightx = 0.15f;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>База</div<", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 4;
			constraints.gridwidth = 2;
			constraints.weightx = 0.2f;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Назначение<br>(время/место)</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 2;
			constraints.weightx = 0.2f;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Возврат на базу<br>(время/остаток)</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 8;
			constraints.gridwidth = 1;
			constraints.weightx = 0.1f;
			constraints.insets.right = 0;
			//отозвать кнопка
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Приказ</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			y_offset = 2;
			for(int i = 0; i < Player.MAX_FLEET_AMOUNT; i++)
			{
				buildFleetRow(i);
			}
			y_offset_fleet_list = y_offset + Player.MAX_FLEET_AMOUNT + 1;
			constraints.gridx = 0;
			constraints.gridy = y_offset_fleet_list;
			constraints.gridwidth = 9;
			constraints.weightx = 1.0f;
			add(new TextLabel(false, 0, "Новое задание: выбрать корабли", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			y_offset_fleet_list++;
			constraints.gridy = y_offset_fleet_list;
			constraints.weightx = 0.0f;
			constraints.gridx = 0;
			constraints.gridwidth = 4;
			constraints.insets.right = 3;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Тип корабля</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 4;
			constraints.gridwidth = 2;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Количество</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 2;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>-</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 8;
			constraints.gridwidth = 1;
			constraints.insets.right = 0;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>-</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			y_offset_fleet_list++;
			for(int i = 1; i < Unit.SHIPS_AMOUNT; i++)
			{
				buildShipRow(i);
			}
			constraints.gridx = 0;
			constraints.gridy = y_offset_fleet_list + Unit.SHIPS_AMOUNT - 1;
			constraints.gridwidth = 9;
			constraints.weightx = 1.0f;
			constraints.ipady = 5;
			add(new SubmitButton(constraints.gridx + "." + constraints.gridy, next_button), constraints);
		}
		
		private void drawDestinationSelection()
		{
			int[] base_coords = current_planet.getCoords();
			int[] digits;
			int distance = Fleet.calcDistance(base_coords, base_coords);
			int fuel_consumption = Fleet.calcTotalFuelConsumption(ships, distance, 100);
			int max_speed = Unit.findMaxFleetSpeed(ships);
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 4;
			constraints.gridheight = 1;
			constraints.insets.bottom = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.weightx = 1.0f;
			constraints.ipady = 0;
			add(new TextLabel(false, 0, "Отправление флота на", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.weightx = 0.3f;
			constraints.gridy = 1;
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			add(new TextLabel(false, false, 0, "Расстояние", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.insets.right = 3;
			add(new TextLabel(false, false, 0, "" + NumberFormat.getNumberInstance(Locale.US).format(distance), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.insets.right = 0;
			constraints.weightx = 0.4f;
			constraints.gridx = 2;
			constraints.gridwidth = 2;
			constraints.ipady = 10;
			add(new CoordinatesField(constraints.gridx + "." + constraints.gridy), constraints);
			constraints.weightx = 0.0f;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridx = 0;
			add(new TextLabel(false, false, 0, "Потребление топлива", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.insets.right = 3;
			add(new TextLabel(false, false, 0, "" + NumberFormat.getNumberInstance(Locale.US).format(fuel_consumption), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.insets.right = 0;
			constraints.weightx = 0.25f;
			constraints.gridx = 2;
			add(new TextLabel(false, false, 0, "Скорость(в процентах)", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			add(new SpeedField(constraints.gridx + "." + constraints.gridy), constraints);
			constraints.gridy = 3;
			constraints.gridwidth = 1;
			constraints.gridx = 0;
			add(new TextLabel(false, false, 0, "Максимальная скорость", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.insets.right = 3;
			add(new TextLabel(false, false, 0, "" + NumberFormat.getNumberInstance(Locale.US).format(max_speed), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 2;
			constraints.insets.right = 0;
			add(new TextLabel(false, false, 0, "До прибытия к цели", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			digits = getRemainingTime(new Date(new Date().getTime() + Fleet.calcStartDuration(distance, max_speed) + Fleet.calcCleanDuration(distance, max_speed, 100)));
			add(new TextLabel(false, false, 0, "<font color='lime'>" + String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]) + "</font>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridy = 4;
			constraints.gridx = 0;
			add(new TextLabel(false, false, 0, "Грузоподъемность", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.insets.right = 3;
			add(new TextLabel(false, false, 0, "" + NumberFormat.getNumberInstance(Locale.US).format(Fleet.calcTotalCapacity(ships) - fuel_consumption), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 2;
			constraints.insets.right = 0;
			add(new TextLabel(false, false, 0, "Подготовка к вылету", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			digits = getRemainingTime(new Date(new Date().getTime() + Fleet.calcStartDuration(distance, max_speed)));
			add(new TextLabel(false, false, 0, String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridy = 5;
			constraints.gridwidth = 4;
			constraints.gridx = 0;
			constraints.ipady = 0;
			add(new TextLabel(false, 0, "Мои планеты", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.ipady = 10;
			constraints.gridy = 6;
			add(new QuickPlanetSelectionMenu(constraints.gridx + "." + constraints.gridy), constraints);
			constraints.gridy = 7;
			constraints.weightx = 1.0f;
			constraints.ipady = 5;
			add(new SubmitButton(constraints.gridx + "." + constraints.gridy, next_button), constraints);
		}
		
		private void drawMissionSelection()
		{
			int[] coords = textFieldsToArray(coordinates);
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.gridheight = 1;
			constraints.insets.bottom = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.weightx = 1.0f;
			constraints.ipady = 0;
			add(new TextLabel(false, 0, coords[0] + ":" + coords[1] + ":" + coords[2] + " - РЕЗЕРВ_ТИП_ОБЪЕКТА", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.weightx = 0.5f;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.insets.right = 5;
			add(new MissionSelectionPanel(constraints.gridx + "." + constraints.gridy), constraints);
			constraints.insets.right = 0;
			constraints.gridx = 1;
			add(new ResourcesSelectionPanel(constraints.gridx + "." + constraints.gridy), constraints);
			constraints.weightx = 1.0f;
			constraints.ipady = 5;
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			add(new SubmitButton(constraints.gridx + "." + constraints.gridy, next_button), constraints);
		}
		
		private void buildFleetRow(int row)
		{
			Fleet fleet;
			String text;
			try
			{
				fleet = player.getFleet(row);
			}
			catch(IndexOutOfBoundsException e)
			{
				fleet = null;
			}
			constraints.gridy = y_offset + row;
			constraints.weightx = 0.0f;
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.insets.right = 3;
			if(row < player.getFleetsSize())
			{
				text = "<div style='text-align:center;'>" + (row + 1) + "</div>";
			}
			else
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 1;
			try
			{
				text = "<div style='text-align:center;'>" + fleet.getMission().toString() + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 2;
			constraints.gridwidth = 1;
			try
			{
				text = "<div style='text-align:center;'>" + fleet.getShipsTotal() + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			constraints.gridwidth = 1;
			Planet base_planet = null;
			try
			{
				base_planet = OffGamePanel.getPlanetByCoordinates(fleet.getBase());
				text = "<div style='text-align:center;'>" + base_planet.getName() + "<br>" + Planet.coordinatesToString(base_planet.getCoords()) + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 4;
			constraints.gridwidth = 1;
			constraints.weightx = 0.2f;
			try
			{
				text = "<div style='text-align:center;'>" + new SimpleDateFormat("kk:mm:ss'<br>'dd MMMMMMMMM").format(fleet.getToTargetDate()) + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 5;
			constraints.gridwidth = 1;
			Planet target_planet = null;
			try
			{
				target_planet = OffGamePanel.getPlanetByCoordinates(fleet.getTarget());
				text = "<div style='text-align:center;'>" + target_planet.getName() + "<br>" + Planet.coordinatesToString(target_planet.getCoords()) + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 1;
			try
			{
				text = "<div style='text-align:center;'>" + new SimpleDateFormat("kk:mm:ss'<br>'dd MMMMMMMMM").format(fleet.getFromTargetDate()) + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 7;
			constraints.gridwidth = 1;
			try
			{
				int[] digits = getRemainingTime(fleet.getFromTargetDate());
				text = "<div style='text-align:center;'><font color='lime'>" + String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]) + "</font></div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 8;
			constraints.gridwidth = 1;
			constraints.weightx = 0.0f;
			constraints.insets.right = 0;
			if(row < player.getFleetsSize())
			{
				text = "-";
			}
			else
			{
				text = "";
			}
			add(new TextLabel(false, 0, "<div style='text-align:center;'>" + text + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		}
		
		private void buildShipRow(int code)
		{
			constraints.gridy = y_offset_fleet_list + code  - 1;
			constraints.weightx = 0.4f;
			constraints.gridx = 0;
			constraints.gridwidth = 4;
			constraints.insets.right = 3;
			add(new TextLabel(true, code, current_planet.getUnits()[code].generateHeaderWithoutLevel(), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.weightx = 0.2f;
			constraints.gridx = 4;
			constraints.gridwidth = 2;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>" + NumberFormat.getNumberInstance(Locale.US).format(current_planet.getUnits()[code].getAmount()) + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 2;
			add(new TextLabel(false, 0, "<div style='text-align:center;'><u>макс.</u></div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 8;
			constraints.gridwidth = 1;
			add(new InputField(constraints.gridx + "." + constraints.gridy), constraints);
			
		}
		
		public int getYOffset()
		{
			return y_offset;
		}
		
		public int getYOffsetFleetList()
		{
			return y_offset_fleet_list; 
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			Component[] list = getComponents();
			boolean selection_valid = false;
			switch(phase)
			{
				case 0:
					selection_valid = false;
					boolean amount_valid = true;
					int[] amounts = new int[Unit.SHIPS_AMOUNT];
					for(int i = 0; i < list.length; i++)
					{
						if(list[i] instanceof InputField)
						{
							String[] s = list[i].getName().split("\\.");
							int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
							int amount;
							try
							{
								amount = Integer.parseInt(((JTextField)((Container)list[i]).getComponent(0)).getText());
							}
							catch(NumberFormatException ex)
							{
								amount = 0;
							}
							amounts[coords[1] - y_offset_fleet_list + 1] = amount;
							amount_valid &= (amount >= 0) && (amount <= current_planet.getUnits()[coords[1] - y_offset_fleet_list + 1].getAmount());
							selection_valid = (selection_valid || (amount > 0)) && amount_valid && (player.getFleetsSize() < player.getMaxFleetsAvailable()); 
							ships = Unit.createFleetList(amounts);
						}
					}
					break;
				case 1:
					int[] coords = null;
					try
					{
						coords = textFieldsToArray(coordinates);
					}
					catch(NumberFormatException ex)
					{
						selection_valid = false;
						break;
					}
					selection_valid = !Arrays.equals(current_planet.getCoords(), coords) && (capacity_left >= 0) && Planet.checkValidCoordinates(coords);
					break;
				case 2:
					for(Enumeration<AbstractButton> buttons = ((MissionSelectionPanel)getComponent(1)).getButtonGroup().getElements(); buttons.hasMoreElements();)
					{
						AbstractButton button = buttons.nextElement();
						selection_valid |= button.isSelected();
						if(button.isSelected())
						{
							mission = MissionCategory.fromString(button.getText());
							button.setSelected(false);
						}
					}
					int sum = 0;
					int[] res = textFieldsToArray(resources_loaded);
					double[] planet_res = new double[] {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium()};
					boolean resources_loaded_valid = true;
					for(int i = 0; i < res.length; i++)
					{
						sum += res[i];
						resources_loaded_valid &= res[i] < planet_res[i]; 
					}
					resources_loaded_valid &= (mission != MissionCategory.TRANSPORT) || (sum != 0);
					resources_loaded_valid &= ((mission != MissionCategory.ATTACK) && (mission != MissionCategory.ESPIONAGE)) || (sum == 0);
					selection_valid &= (capacity_left - sum >= 0) && resources_loaded_valid;
					break;
			}
			if(selection_valid)
			{
				if(phase < 2)
				{
					requestFocusInWindow();
					setVisible(false);
					setEnabled(false);
					updatePhase();
				}
				else
				{
					player.addNewFleet(ships, mission, current_planet.getCoords(), textFieldsToArray(coordinates), (Integer)speed_percentage.getSelectedItem(), capacity_left, textFieldsToArray(resources_loaded));
					PhasePanel panel = ((PhasePanel)SwingUtilities.getAncestorOfClass(FleetControlPanel.class, this).getComponent(0));
					list = panel.getComponents();
					int y_offset = panel.getYOffset();
					int fleets_amount = player.getFleetsSize();
					Fleet fleet = player.getFleet(fleets_amount - 1);
					Planet planet = null;
					String planet_name = "";
					for(int i = 0; i < list.length; i++)
					{
						String[] s = list[i].getName().split("\\.");
						int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
						if(coords[1] - y_offset == fleets_amount - 1)
						{
							list[i].setVisible(true);
							switch(coords[0])
							{
								case 0:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + fleets_amount + "</div>");
									break;
								case 1:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + mission.toString() + "</div>");
									break;
								case 2:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + fleet.getShipsTotal() + "</div>");
									break;
								case 3:
									planet = OffGamePanel.getPlanetByCoordinates(fleet.getBase());
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + planet.getName() + "<br>" + Planet.coordinatesToString(planet.getCoords()) + "</div>");
									break;
								case 4:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + new SimpleDateFormat("kk:mm:ss'<br>'dd MMMMMMMMM").format(fleet.getToTargetDate()) + "</div>");
									break;
								case 5:
									planet = OffGamePanel.getPlanetByCoordinates(fleet.getTarget());
									if(planet != null)
									{
										planet_name = planet.getName();
									}
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + planet_name + "<br>" + Planet.coordinatesToString(fleet.getTarget()) + "</div>");
									break;
								case 6:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>" + new SimpleDateFormat("kk:mm:ss'<br>'dd MMMMMMMMM").format(fleet.getFromTargetDate()) + "</div>");
									break;
								case 7:
									int[] digits = getRemainingTime(fleet.getFromTargetDate());
									((TextLabel)list[i]).setText("<div style='text-align:center;'><font color='lime'>" + String.format("%02d:%02d:%02d", digits[0], digits[1], digits[2]) + "</font></div>");
									break;
								case 8:
									((TextLabel)list[i]).setText("<div style='text-align:center;'>-</div>");
									break;
							}
						}
					}
					resetPhase();
				}
			}
			
		}
		
		private int y_offset;
		private int y_offset_fleet_list;
		private JButton next_button;
		private GridBagConstraints constraints = new GridBagConstraints();
	}

	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(boolean hasImage, int code, String text, String name, Color bgc)
		{
			if(hasImage)
			{
				setShipIcon(code);
			}
			setName(name);
			addComponentListener(this);
			real_text = text;
			paint_border = false;
			this.wrap_required = true;
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
		
		public TextLabel(boolean hasImage, boolean wrapRequired, int code, String text, String name, Color bgc)
		{
			if(hasImage)
			{
				setShipIcon(code);
			}
			setName(name);
			addComponentListener(this);
			real_text = text;
			this.wrap_required = wrapRequired;
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
		
		public void setShipIcon(int code)
		{
			int param = Image.SCALE_SMOOTH;
			switch(code)
			{
				case Unit.PROCESSOR:
				case Unit.DEATH_STAR:
					param = Image.SCALE_REPLICATE;
					break;
			}
			ImageIcon img = new ImageIcon(getClass().getResource("/un/" + code + ".gif"));
			setIcon(new ImageIcon(img.getImage().getScaledInstance(40, 40, param)));
		}
		
		public void setText(String text)
		{
			real_text = text;
			if((getIcon() == null) && wrap_required)
			{
				super.setText("<html><body width='" + getWidth() + "'><font size=\"3\" color= \"white\">" + text +"</font></body></html>");
			}
			else
			{
				super.setText("<html>" + "<font size=\"3\" color= \"white\">" + text +"</font></html>");
			}
		}
		
		public void setManualBorder()
		{
			paint_border = true;
			repaint();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(new Color(60, 100, 134));
			if(paint_border)
			{
				g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
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
		private boolean wrap_required;
		private boolean paint_border;
	}
	
	private class InputField extends JPanel implements KeyListener, FocusListener
	{
		public InputField(String name) 
		{
			setName(name);
			setOpaque(true);
			paint_border = false;
			setBackground(BACKGROUND_COLOR);
			amount_field = new JTextField("0", 7);
			amount_field.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			amount_field.setBackground(new Color(102, 102, 102));
			amount_field.setForeground(Color.WHITE);
			amount_field.addKeyListener(this);
			amount_field.addFocusListener(this);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.0f;
			constraints.weighty = 0.0f;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.NONE;
			add(amount_field, constraints);
		}
		
		public void setManualBorder()
		{
			paint_border = true;
			repaint();
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(new Color(60, 100, 134));
			if(paint_border)
			{
				g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
		}
		
		public void keyTyped(KeyEvent e) 
		{
			
		}

		public void keyPressed(KeyEvent e) 
		{
			if((e.getKeyCode() == KeyEvent.VK_ESCAPE))
			{
				requestFocusInWindow();
				if(amount_field.getText().equals(""))
				{
					amount_field.setText("0");
				}
			}
		}

		public void keyReleased(KeyEvent e) 
		{
			
		}
		
		public void focusGained(FocusEvent e) 
		{
			if(amount_field.getText().equals("0")) 
			{
				amount_field.setText("");
			}
		}

		public void focusLost(FocusEvent e) 
		{
			if(amount_field.getText().equals(""))
			{
				amount_field.setText("0");
			}
		}
		
		private JTextField amount_field;
		private boolean paint_border;
	}
	
	private class SubmitButton extends JPanel
	{
		public SubmitButton(String name, JButton button) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			button.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			button.setBackground(new Color(102, 102, 102));
			button.setForeground(Color.WHITE);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.3f;
			constraints.weighty = 0.0f;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.VERTICAL;
			add(button, constraints);
		}
	}
	
	private class CoordinatesField extends JPanel implements KeyListener, FocusListener
	{
		public CoordinatesField(String name) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.0f;
			constraints.weighty = 0.0f;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			JTextField field;
			int[] coords = current_planet.getCoords();
			for(int i = 0; i < 3; i++)
			{
				constraints.gridx = i;
				field = new JTextField("" + coords[i], 5);
				field.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
				field.setBackground(new Color(102, 102, 102));
				field.setForeground(Color.WHITE);
				field.addKeyListener(this);
				field.addFocusListener(this);
				coordinates[i] = field;
				add(field, constraints);
			}
			constraints.gridx = 3;
			String[] items = {CelestialBody.PLANET.toString(), CelestialBody.DEBRIS.toString(), CelestialBody.MOON.toString()};
			JComboBox<String> object = new JComboBox<>(items);
			object.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			object.setBackground(new Color(102, 102, 102));
			object.setForeground(Color.WHITE);
			object.setSelectedIndex(0);
			object_type = object;
			add(object, constraints);
		}
		
		public void keyTyped(KeyEvent e) 
		{
			
		}

		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				requestFocusInWindow();
				((JTextField)e.getSource()).setText("0");
			}
		}

		public void keyReleased(KeyEvent e) 
		{
			
		}
		
		public void focusGained(FocusEvent e) 
		{
			if(((JTextField)e.getSource()).getText().equals("0"))
			{
				((JTextField)e.getSource()).setText("");
			}
		}

		public void focusLost(FocusEvent e) 
		{
			if(((JTextField)e.getSource()).getText().equals(""))
			{
				((JTextField)e.getSource()).setText("0");
			}
		}
		
	}
	
	private class SpeedField extends JPanel
	{
		public SpeedField(String name) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.5f;
			constraints.weighty = 0.0f;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.fill = GridBagConstraints.NONE;
			Integer[] items = new Integer[10];
			for(int i = 0; i < items.length; i++)
			{
				items[i] = (items.length - i) * 10;
			}
			JComboBox<Integer> object = new JComboBox<>(items);
			object.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
			object.setBackground(new Color(102, 102, 102));
			object.setForeground(Color.WHITE);
			object.setSelectedIndex(0);
			speed_percentage = object;
			add(object, constraints);
		}
	}
	
	private class QuickPlanetSelectionMenu extends JPanel
	{
		public QuickPlanetSelectionMenu(String name) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.0f;
			constraints.weighty = 0.0f;
			constraints.insets.right = 10;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.NONE;
			for(int i = 0; i < player.getPlanetAmount(); i++)
			{
				if (i == player.getPlanetAmount() - 1)
				{
					constraints.insets.right = 0;
				}
				add(new QuickPlanetSelectionMenuElement(constraints.gridx + "." + constraints.gridy, i), constraints);
				constraints.gridx++;
			}
		}
		
		private class QuickPlanetSelectionMenuElement extends JPanel
		{
			public QuickPlanetSelectionMenuElement(String name, int index)
			{
				setName(name);
				setOpaque(true);
				setBackground(BACKGROUND_COLOR);
				addMouseListener(new MouseAdapter()
						{
							public void mouseClicked(MouseEvent e)
							{
								for(int i = 0; i < coords.length; i++)
								{
									coordinates[i].setText("" + coords[i]);
									updatePanelUI();
								}
							}
						});
				setLayout(new GridBagLayout());
				GridBagConstraints constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = 0;
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.weightx = 0.0f;
				constraints.weighty = 0.0f;
				constraints.anchor = GridBagConstraints.CENTER;
				constraints.fill = GridBagConstraints.NONE;
				JLabel img = new JLabel();
				Planet planet = player.getPlanet(index);
				img.setIcon(new ImageIcon(planet.getImg().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH)));
				add(img, constraints);
				constraints.gridy = 1;
				coords = planet.getCoords();
				JLabel text = new JLabel("<html><font color='#D8F4DD' size='4'><u>" + coords[0] + ":" + coords[1] + ":" + coords[2] + "</u></font></html>");
				add(text, constraints);
			}
			
			private int[] coords;
			private static final int IMAGE_SIZE = 50;
		}
		
	}
	
	private class MissionSelectionPanel extends JPanel
	{
		public MissionSelectionPanel(String name)
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 1.0f;
			constraints.weighty = 0.0f;
			constraints.insets.left = 5;
			constraints.insets.right = 5;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.fill = GridBagConstraints.BOTH;
			add(new TextLabel(false, 0, "Задание", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			group = new ButtonGroup();
			options = new JRadioButton[MAX_BUTTONS_AMOUNT];
			constraints.fill = GridBagConstraints.NONE;
			constraints.gridy++;
			for(int i = 0; i < options.length; i++)
			{
				options[i] = new JRadioButton();
				options[i].setBackground(BACKGROUND_COLOR);
				options[i].setForeground(Color.WHITE);
				Font button_font = options[i].getFont();
				options[i].setFont(new Font(button_font.getName(), button_font.getStyle(), button_font.getSize() + 4));
				options[i].setFocusPainted(false);
				add(options[i], constraints);
				group.add(options[i]);
				constraints.gridy++;
			}
			constraints.weighty = 1.0f;
			add(Box.createVerticalGlue(), constraints);
		}
		
		private void updateOptions()
		{
			int[] coords = textFieldsToArray(coordinates);
			Planet target = OffGamePanel.getPlanetByCoordinates(coords);
			if(target == null)
			{
				options[0].setText("Колонизировать");
				options[0].setVisible(true);
				options[0].setEnabled(true);
				options[1].setVisible(false);
				options[1].setEnabled(false);
				return;
			}
			if(target.getOwner().equals(player))
			{
				options[0].setText("Транспорт");
				options[0].setVisible(true);
				options[0].setEnabled(true);
				options[1].setText("Оставить");
				options[1].setVisible(true);
				options[1].setEnabled(true);
				return;
			}
			if(!target.getOwner().equals(player))
			{
				options[0].setText("Шпионаж");
				options[0].setVisible(true);
				options[0].setEnabled(true);
				options[1].setText("Атаковать");
				options[1].setVisible(true);
				options[1].setEnabled(true);
				boolean unspyable = ships[Unit.SPY_PROBE].getAmount() == 0;
				for(int i = Unit.SPY_PROBE; i <= Unit.SUPERNOVA; i++)
				{
					unspyable |= ships[i].getAmount() != 0;
				}
				if(unspyable)
				{
					options[0].setVisible(false);
					options[0].setEnabled(false);
				}
				return;
			}
			// moons debris
			if(coords[2] == 16)
			{
				options[0].setText("Экспедиция");
				options[0].setVisible(true);
				options[0].setEnabled(true);
				options[1].setVisible(false);
				options[1].setEnabled(false);
			}
		}
		
		private ButtonGroup getButtonGroup()
		{
			return group;
		}
		
		private JRadioButton[] options;
		private ButtonGroup group;
		private static final int MAX_BUTTONS_AMOUNT = 2;
	}
	
	private class ResourcesSelectionPanel extends JPanel
	{
		public ResourcesSelectionPanel(String name)
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			constraints.gridheight = 1;
			constraints.weightx = 1.0f;
			constraints.weighty = 0.0f;
			constraints.insets.left = 5;
			constraints.insets.right = 5;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.fill = GridBagConstraints.BOTH;
			add(new TextLabel(false, 0, "Сырьё", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
			constraints.weightx = 0.3f;
			constraints.gridwidth = 1;
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets.bottom = 3;
			constraints.ipady = 5;
			TextLabel label = new TextLabel(false, 0, "<div style='text-align:center;'>Остаток</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR);
			label.setManualBorder();
			add(label, constraints);
			constraints.insets.left = 0;
			constraints.gridwidth = 2;
			constraints.weightx = 0.7f;
			constraints.gridx = 1;
			label = new TextLabel(false, 0, "<div style='text-align:center;'>-</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR);
			label.setManualBorder();
			add(label, constraints);
			constraints.gridy = 2;
			String[] resource = {"Металл", "Кристалл", "Дейтерий"};
			for(int i = 0; i < 3; i++)
			{
				constraints.weightx = 0.3f;
				constraints.gridwidth = 1;
				constraints.gridx = 0;
				constraints.insets.left = 5;
				label = new TextLabel(false, 0, "<div style='text-align:center;'>" + resource[i] + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR);
				label.setManualBorder();
				add(label, constraints);
				constraints.weightx = 0.0f;
				constraints.gridx = 1;
				constraints.insets.left = 0;
				label = new TextLabel(false, 0, "<div style='text-align:center;'>макс.</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR);
				label.setManualBorder();
				add(label, constraints);
				add(Box.createHorizontalStrut(100), constraints);
				constraints.weightx = 1.0f;
				constraints.gridx = 2;
				InputField input_field = new InputField(constraints.gridx + "." + constraints.gridy);
				resources_loaded[i] = input_field.amount_field;
				input_field.setManualBorder();
				add(input_field, constraints);
				constraints.gridy++;
			}
		}
	}
	
	private int phase;
	private int capacity_left;
	private Unit[] ships;
	private JTextField[] coordinates;
	private JTextField[] resources_loaded;
	private MissionCategory mission;
	private JComboBox<Integer> speed_percentage;
	private JComboBox<String> object_type;
}
