package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.*;

import javax.swing.*;

import adaptogame.core.*;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class FleetControlPanel extends InfoPanel 
{
	public FleetControlPanel(String name, Player player, OffGamePanel main_panel) 
	{
		super(name, player);
		setOpaque(false);
		owner = main_panel;
		phase = 0;
		ships = new Unit[Unit.SHIPS_AMOUNT];
		coordinates = new JTextField[3];
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
		//add(new PhasePanel(2), constraints);
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
		int y_offset = panel.getYOffset();
		int y_offset_fleet_list = panel.getYOffsetFleetList();
		Component[] list = panel.getComponents();
		switch(phase)
		{
			case 0:
				for(int i = 0; i < list.length; i++)
				{
					String[] s = list[i].getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					int fleets_amount = player.getFleetsSize();
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
						list[i].setVisible(current_planet.getUnits()[coords[1] - y_offset_fleet_list + 1].getAmount() != 0);
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
											text = NumberFormat.getNumberInstance(Locale.US).format(Fleet.calcTotalFuelConsumption(ships, Fleet.calcDistance(int_coordinates, current_planet.getCoords()), 100));
										}
										catch(NumberFormatException e)
										{
											text = "NaN";
										}
										break;
									case 3:
										break;
									case 4:
										break;
								}
								((TextLabel)list[i]).setText(text);
							}
							break;
					}
				}
				break;
			case 2:
				break;
		}
	}
	
	private class PhasePanel extends JPanel implements ActionListener
	{
		public PhasePanel(int phase)
		{
			setVisible(false);
			setOpaque(false);
			setLayout(new GridBagLayout());
			next_button = new JButton();
			switch(phase)
			{
				case 0:
					drawFleetSelection();
					break;
				case 1:
					drawDestinationSelection();
					break;
				case 2:
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
			constraints.gridwidth = 5;
			constraints.insets.right = 3;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Тип корабля</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 5;
			constraints.gridwidth = 1;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>Количество</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 1;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>-</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 7;
			constraints.gridwidth = 2;
			constraints.insets.right = 0;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>-</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			y_offset_fleet_list++;
			for(int i = 1; i < Unit.SHIPS_AMOUNT; i++)
			{
				buildShipRow(i);
			}
			next_button.setText("Далее");
			Font button_font = next_button.getFont();
			next_button.setFont(new Font(button_font.getName(), button_font.getStyle(), button_font.getSize() + 4));
			next_button.addActionListener(this);
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
			int distance = Fleet.calcDistance(base_coords, base_coords);
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
			add(new CoordinatesField(constraints.gridx + "." + constraints.gridy, next_button), constraints);
			constraints.weightx = 0.0f;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridx = 0;
			add(new TextLabel(false, false, 0, "Потребление топлива", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 1;
			constraints.insets.right = 3;
			add(new TextLabel(false, false, 0, "" + NumberFormat.getNumberInstance(Locale.US).format(Fleet.calcTotalFuelConsumption(ships, distance, 100)), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.insets.right = 0;
			constraints.weightx = 0.25f;
			constraints.gridx = 2;
			add(new TextLabel(false, false, 0, "Скорость", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 3;
			add(new SpeedField(constraints.gridx + "." + constraints.gridy), constraints);
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
				base_planet = owner.getPlanetByCoordinates(fleet.getBase());
				text = "<div style='text-align:center;'>" + base_planet.getName() + "<br>" + Planet.coordinatesToString(base_planet.getCoords()) + "</div>";
			}
			catch(NullPointerException e)
			{
				text = "";
			}
			add(new TextLabel(false, 0, text, constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 4;
			constraints.gridwidth = 1;
			constraints.weightx = 0.5f;
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
				target_planet = owner.getPlanetByCoordinates(fleet.getTarget());
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
			constraints.weightx = 0.0f;
			constraints.gridx = 0;
			constraints.gridwidth = 5;
			constraints.insets.right = 3;
			add(new TextLabel(true, code, current_planet.getUnits()[code].generateHeaderWithoutLevel(), constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 5;
			constraints.gridwidth = 1;
			add(new TextLabel(false, 0, "<div style='text-align:center;'>" + NumberFormat.getNumberInstance(Locale.US).format(current_planet.getUnits()[code].getAmount()) + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 6;
			constraints.gridwidth = 1;
			add(new TextLabel(false, 0, "<div style='text-align:center;'><u>макс.</u></div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx = 7;
			constraints.gridwidth = 2;
			InputField amount_field_panel = new InputField(constraints.gridx + "." + constraints.gridy, next_button);
			add(amount_field_panel, constraints);
			
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
							amounts[coords[1] - y_offset_fleet_list + 1] = min(amount, current_planet.getUnits()[coords[1] - y_offset_fleet_list + 1].getAmount());
							selection_valid |= amount != 0; 
							ships = Unit.createFleetList(amounts);
						}
					}
					break;
				case 1:
					break;
				case 2:
					break;
			}
			if(selection_valid)
			{
				if(phase < 2)
				{
					requestFocusInWindow();
					setVisible(false);
					phase++;
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
	}
	
	private class InputField extends JPanel implements KeyListener, FocusListener
	{
		public InputField(String name, JButton linked_button) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			this.linked_button = linked_button;
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
		
		
		public void keyTyped(KeyEvent e) 
		{
			
		}

		public void keyPressed(KeyEvent e) 
		{
			if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && (amount_field.getText().equals("0")))
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
			if(amount_field.getText().equals("0")) 
			{
				amount_field.setText("");
			}
		}

		public void focusLost(FocusEvent e) 
		{
			if(!linked_button.isRequestFocusEnabled())
			{
				amount_field.setText("0");
			}
		}
		
		private JButton linked_button;
		private JTextField amount_field;
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
		public CoordinatesField(String name, JButton linked_button) 
		{
			setName(name);
			setOpaque(true);
			setBackground(BACKGROUND_COLOR);
			this.linked_button = linked_button;
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
			if(!linked_button.isRequestFocusEnabled() && ((JTextField)e.getSource()).getText().equals("0"))
			{
				((JTextField)e.getSource()).setText("0");
			}
		}
		
		private JButton linked_button;
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
			constraints.weightx = 1.0f;
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
	
	private int phase;
	private Unit[] ships;
	private OffGamePanel owner;
	private JTextField[] coordinates;
	private JComboBox<Integer> speed_percentage;
	private JComboBox<String> object_type;
}