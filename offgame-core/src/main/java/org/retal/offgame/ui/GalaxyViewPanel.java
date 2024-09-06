package org.retal.offgame.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import org.retal.offgame.GameLauncher;
import org.retal.offgame.core.*;
import org.retal.offgame.core.units.Unit;

public class GalaxyViewPanel extends InfoPanel implements ActionListener
{
	public GalaxyViewPanel(String name, Player player)
	{
		super(name, player);
		setOpaque(false);
		position = Arrays.copyOfRange(current_planet.getCoords(), 0, 2);
		coordinates = new JTextField[2];
		last_time_burned = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0f;
		constraints.weighty = 0.0f;
		constraints.insets.bottom = 3;
		constraints.insets.right = 5;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTH;
		addLocationMenu();
		y_offset = 5;
		addSystemTable();
		y_offset_after = constraints.gridy + 1;
		addPostInfoBlock();
	}
	
	public void updatePanelUI()
	{
		Component[] list = getComponents();
		int alive_planets = 0;
		int line = 0;
		for(int i = 0; i < list.length; i++)
		{
			String[] s = (list[i].getName() != null) && (list[i].getName().indexOf(".") != -1)? list[i].getName().split("\\.") : new String[] {"-1", "-1"};
			int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
			if(coords[1] == 2)
			{
				((ChangeSystemPanel)list[i]).updateLabel();
			}
			if(coords[1] == 3)
			{
				((TextLabel)list[i]).setText("<div style='text-align:center'>Израсходовано <font color='red'>" + (int)last_time_burned + "</font> дейтерия</div>");
			}
			if((coords[1] - (y_offset + 2) >= 0) && (coords[1] < y_offset_after - 1))
			{
				Planet planet = MainPanel.getPlanetByCoordinates(new int[] {position[0], position[1], coords[1] - (y_offset + 1)});
				if((planet != null) && (line != coords[1]))
				{
					alive_planets++;
					line = coords[1];
				}
				String color_opening = "";
				String color_ending = "";
				if(planet == current_planet)
				{
					color_opening = "<font color='lime'>";
					color_ending = "</font>";
				}
				Icon icon = planet != null ? new ImageIcon(planet.getImg().getScaledInstance(PlanetImg.SIZE, PlanetImg.SIZE, Image.SCALE_SMOOTH)) : null;
				String opening = "<div style='text-align:center'>";
				String ending = "</div>";
				
				String name = planet != null ? planet.getName() : "";
				String owner_name = planet != null ?  planet.getOwner().getName() : "";
				switch(coords[0])
				{
					case 1:
						((PlanetImg)list[i]).setIcon(icon);
						break;
					case 2:
						((TextLabel)list[i]).setText(opening + color_opening + name + color_ending + ending);
						break;
					case 3:
						break;
					case 4:
						break;
					case 6:
						((TextLabel)list[i]).setText(opening + owner_name + ending);
						break;
					case 9:
						break;
				}
			}
			if(coords[1] == y_offset_after)
			{
				switch(coords[1] - y_offset_after)
				{
					case 0:
						switch(coords[0])
						{
							case 0:
								((TextLabel)list[i]).setText("( " + alive_planets + " обитаемые планеты )");
								break;
							case 9:
								((TextLabel)list[i]).setText(current_planet.getUnits()[Unit.PROCESSOR].getAmount()  + " переработчиков<br>" + current_planet.getUnits()[Unit.SPY_PROBE].getAmount() + " шпионских зондов");
								break;
						}
						break;
					case 1:
						switch(coords[0])
						{
							case 0:
								//!
								break;
							case 3:
								((TextLabel)list[i]).setText(player.getFleetsSize() + "/" + player.getMaxFleetsAvailable() + " флотов");
								break;
						}
						break;
				}
			}
		}
	}
	
	private void resetPanelUI()
	{
		for(int i = 0; i < 2; i++)
		{
			position[i] = current_planet.getCoords()[i];
			coordinates[i].setText("" + position[i]);
		}
		last_time_burned = 0;
	}
	
	public void updateCurrentPlanet()
	{
		super.updateCurrentPlanet();
		resetPanelUI();
	}
	
	public void mousePressed(MouseEvent e)
	{
		super.mousePressed(e);
		Component[] list = getComponents();
		for(Component c : list)
		{
			if(c.contains(new Point(e.getX() - c.getX(), e.getY() - c.getY())))
			{
				if((c instanceof TextLabel) && ((Integer.parseInt(((TextLabel)c).getName().split("\\.")[0]) ==  0) && ((Integer.parseInt(((TextLabel)c).getName().split("\\.")[1])  - (y_offset + 1) != Planet.UNIVERSE_BOUNDS[2])) || ((Integer.parseInt(((TextLabel)c).getName().split("\\.")[0]) ==  1) && ((Integer.parseInt(((TextLabel)c).getName().split("\\.")[1])  - (y_offset + 1) == Planet.UNIVERSE_BOUNDS[2])))))
				{
					GameLauncher.arguments = new String[] {position[0] + ":" + position[1] + ":" + (Integer.parseInt(((TextLabel)c).getName().split("\\.")[1])  - (y_offset + 1))};
					((MainPanel)SwingUtilities.getAncestorNamed("main_panel", this)).setCurrentWindow(MenuContainer.FLEET);
				}
			}
		}
	}
	
	private void addLocationMenu()
	{
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		constraints.weightx = 0.28f;
		addSelectionBlock("Галактика", "" + current_planet.getCoords()[0], 3);
		addSelectionBlock("Система", "" + current_planet.getCoords()[1], 6);
		// submit panel
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 6;
		constraints.weightx = 0.4f;
		constraints.insets.top = 8;
		constraints.insets.right = 0;
		constraints.ipady = 10;
		add(new ChangeSystemPanel(constraints.gridx + "." + constraints.gridy), constraints);
		constraints.ipady = 0;
		//
		constraints.gridy = 3;
		add(new TextLabel("<div style='text-align:center'>Израсходовано <font color='red'>" + 0 + "</font> дейтерия</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.top = 0;
	}
	
	private void addSelectionBlock(String block_name, String fieldText, int x)
	{
		JButton button_left = createButton("←");
		JButton button_right = createButton("→");
		JTextField field = new JTextField(fieldText);
		field.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
		field.setBackground(new Color(102, 102, 102));
		field.setForeground(Color.WHITE);
		coordinates[x / 3 - 1] = field;
		button_left.setName("" + (x / 3 - 1));
		button_right.setName("" + (x / 3 - 1));
		button_left.addActionListener(this);
		button_right.addActionListener(this);
		constraints.gridx = x;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.2f;
		constraints.weighty = 0.0f;
		constraints.insets.right = x != 6 ? 10 : 0;
		constraints.ipady = 5;
		add(new TextLabel(block_name, constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.right = 5;
		constraints.weightx = 0.05f;
		
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		add(button_left, constraints);
		constraints.gridx++;
		constraints.weightx = 0.1f;
		
		add(field, constraints);
		constraints.gridx++;
		constraints.weightx = 0.05f;
		
		constraints.insets.right = x != 6 ? 10 : 0;
		add(button_right, constraints);
		constraints.ipady = 0;
		constraints.ipadx = 0;
	}
	
	private void addSystemTable()
	{
		constraints.gridx = 0;
		constraints.gridy = y_offset;
		constraints.weighty = 0.0f;
		constraints.weightx = 1.0f;
		constraints.gridwidth = 10;
		constraints.gridheight = 1;
		constraints.insets.right = 0;
		int[] coords = current_planet.getCoords();
		add(new TextLabel("Звездная система " + coords[0] + ":" + coords[1], constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.insets.right = 5;
		constraints.gridy++;
		constraints.weightx = 0.05f;
		constraints.gridwidth = 1;
		add(new TextLabel("<div style='text-align:center'>" + "№" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx++;
		constraints.weightx = 0.08f;
		add(new TextLabel("<div style='text-align:center'>" + "Планета" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx++;
		constraints.weightx = 0.15f;
		add(new TextLabel("<div style='text-align:center'>" + "Название" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx++;
		constraints.weightx = 0.05f;
		add(new TextLabel("<div style='text-align:center'>" + "Луна" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx++;
		constraints.gridwidth = 2;
		constraints.weightx = 0.1f;
		add(new TextLabel("<div style='text-align:center'>" + "Поле обломков" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx += 2;
		constraints.weightx = 0.25f;
		constraints.gridwidth = 3;
		add(new TextLabel("<div style='text-align:center'>" + "Игрок" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridwidth = 1;
		constraints.gridx += 3;
		constraints.weightx = 0.32f;
		constraints.insets.right = 0;
		add(new TextLabel("<div style='text-align:center'>" + "Действия" + "</div>", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.weightx = 0.0f;
		for(int i = 0; i < Planet.UNIVERSE_BOUNDS[2] - 1; i++)
		{
			constraints.gridy++;
			constraints.gridx = 0;
			constraints.insets.right = 5;
			constraints.gridwidth = 1;
			String u_open = i + 1 != Planet.UNIVERSE_BOUNDS[2] ? "<u>" : "";
			String u_close = i + 1 != Planet.UNIVERSE_BOUNDS[2] ? "</u>" : "";
			add(new TextLabel("<div style='text-align:center'>" + u_open + (i + 1) + u_close + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx++;
			//!!!!!
			Planet planet = MainPanel.getPlanetByCoordinates(new int[] {1, 1, i + 1});
			PlanetImg img = new PlanetImg(planet, constraints.gridx + "." + constraints.gridy);
			String name = "";
			String owner_name = "";
			if(planet != null)
			{
				name = planet.getName();
				owner_name = planet.getOwner().getName();
			}
			add(img, constraints);
			constraints.gridx++;
			add(new TextLabel("<div style='text-align:center'>" + name + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx++;
			//MOON
			add(new TextLabel("<div style='text-align:center'>" + "" + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx++;
			constraints.gridwidth = 2;
			//DEBRIS
			add(new TextLabel("<div style='text-align:center'>" + "" + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx += 2;
			constraints.gridwidth = 3;
			add(new TextLabel("<div style='text-align:center'>" + owner_name + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx += 3;
			constraints.gridwidth = 1;
			// дейтствия
			constraints.insets.right = 0;
			add(new TextLabel("<div style='text-align:center'>" + "" + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		}
		constraints.gridy++;
		constraints.gridx = 0;
		constraints.insets.right = 5;
		constraints.gridwidth = 1;
		add(new TextLabel("<div style='text-align:center'>" + Planet.UNIVERSE_BOUNDS[2] + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		constraints.gridx++;
		constraints.gridwidth = 9;
		String u_open = "<u>";
		String u_close = "</u>";
		add(new TextLabel("<div style='text-align:center'>" + u_open + "Отправить экспедицию" + u_close + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		
	}
	
	private void addPostInfoBlock()
	{
		constraints.gridx = 0;
		constraints.gridy = y_offset_after;
		constraints.weighty = 0.0f;
		constraints.weightx = 0.0f;
		constraints.gridwidth = 9;
		constraints.gridheight = 1;
		constraints.insets.right = 5;
		//
		add(new TextLabel("( 0 обитаемые планеты )", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx += 9;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		add(new TextLabel(current_planet.getUnits()[Unit.PROCESSOR].getAmount()  + " переработчиков<br>" + current_planet.getUnits()[Unit.SPY_PROBE].getAmount() + " шпионских зондов", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridy++;
		constraints.gridx = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.ipady = 10;
		// !!!!!
		add(new TextLabel(0 + " межпланетных ракет", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
		constraints.gridx += 3;
		constraints.gridwidth = 6;
		add(new TextLabel(player.getFleetsSize() + "/" + player.getMaxFleetsAvailable() + " флотов", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
	}
	
	private JButton createButton(String name)
	{
		JButton button = new JButton(name);
		button.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
		button.setBackground(new Color(102, 102, 102));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		return button;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		byte[] sign = {0, 0};
		JButton button = ((JButton)e.getSource());
		int gal_start = current_planet.getCoords()[0];
		int sys_start = current_planet.getCoords()[1];
		int gal_end = 0;
		int sys_end = 0;
		double burned = 0;
		switch(button.getText())
		{
			case "←":
				sign[Integer.parseInt(button.getName())] = -1;
				break;
			case "→":
				sign[Integer.parseInt(button.getName())] = 1;
				break;
		}
		try
		{
			gal_end = inTheUniverseBounds(Integer.parseInt(coordinates[0].getText()) + sign[0], 0);
			sys_end = inTheUniverseBounds(Integer.parseInt(coordinates[1].getText()) + sign[1], 1);
			burned = deiteriumBurned(gal_start, sys_start, gal_end, sys_end);
			if(burned <= current_planet.getCurrentDeiterium())
			{
				position[0] = inTheUniverseBounds(Integer.parseInt(coordinates[0].getText()) + sign[0], 0);
				position[1] = inTheUniverseBounds(Integer.parseInt(coordinates[1].getText()) + sign[1], 1);
			}
		}
		catch(NumberFormatException ex)
		{
			gal_end = inTheUniverseBounds(position[0] + sign[0], 0);
			sys_end = inTheUniverseBounds(position[1] + sign[1], 1);
			burned = deiteriumBurned(gal_start, sys_start, gal_end, sys_end);
			if(burned <= current_planet.getCurrentDeiterium())
			{
				position[0] = inTheUniverseBounds(position[0] + sign[0], 0);
				position[1] = inTheUniverseBounds(position[1] + sign[1], 1);
			}
		}
		finally
		{
			coordinates[0].setText("" + position[0]);
			coordinates[1].setText("" + position[1]);
			if(burned <= current_planet.getCurrentDeiterium())
			{
				last_time_burned = burned;
				current_planet.updateResources(new double[] {0, 0, -burned});
			}
			updatePanelUI();
		}
	}
	
	private int inTheUniverseBounds(int value, int celestial_index)
	{
		if(value < 1)
		{
			return 1;
		}
		if(value > Planet.UNIVERSE_BOUNDS[celestial_index])
		{
			return Planet.UNIVERSE_BOUNDS[celestial_index];
		}
		return value;
	}
	
	private double deiteriumBurned(int gal_start, int sys_start, int gal_end, int sys_end)
	{
		double dist = Fleet.calcDistance(new int[] {gal_start, sys_start, 0}, new int[] {gal_end, sys_end, 0});
		return Math.floor((dist / 100) * (1 + Math.floor(dist / 5000)));
	}
	
	private class TextLabel extends JLabel implements ComponentListener
	{
		public TextLabel(String text, String name, Color bgc)
		{
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
		
		protected String real_text;
	}
	
	private class PlanetImg extends JLabel
	{
		public PlanetImg(Planet planet, String name)
		{
			try
			{
				setIcon(new ImageIcon(planet.getImg().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH)));
			}
			catch(NullPointerException e)
			{
				
			}
			setName(name);
			setBackground(BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
		
		private static final int SIZE = 40;
	}
	
	private class ChangeSystemPanel extends JPanel implements ActionListener
	{
		public ChangeSystemPanel(String name)
		{
			setName(name);
			setBackground(BACKGROUND_COLOR);
			setLayout(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weightx = 0.3f;
			constraints.weighty = 0.0f;
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.insets.left = 5;
			JButton button = createButton(" Просмотр ");
			button.addActionListener(this);
			add(button, constraints);
			constraints.insets.left = 0;
			constraints.gridx++;
			constraints.weightx = 0.7f;
			label = new TextLabel("<div style='text-align:right'>" + "Дейтерия на планете: <font color='lime'>" + (int)current_planet.getCurrentDeiterium() + "</font></div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR);
			add(label, constraints);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int gal = Integer.parseInt(coordinates[0].getText());
				int sys = Integer.parseInt(coordinates[1].getText());
				if((gal == position[0]) && (sys == position[1]))
				{
					return ;
				}
				int gal_start = current_planet.getCoords()[0];
				int sys_start = current_planet.getCoords()[1];
				position[0] = gal;
				position[1] = sys;
				int gal_end = position[0];
				int sys_end = position[1];
				double burned = deiteriumBurned(gal_start, sys_start, gal_end, sys_end);
				if(burned <= current_planet.getCurrentDeiterium())
				{
					last_time_burned = burned;
					current_planet.updateResources(new double[] {0, 0, -burned});
				}
				updatePanelUI();
			}
			catch(NumberFormatException ex)
			{
				
			}
		}
		
		private void updateLabel()
		{
			label.setText("<div style='text-align:right'>" + "Дейтерия на планете: <font color='lime'>" + (int)current_planet.getCurrentDeiterium() + "</font></div>");
		}
		
		private TextLabel label;
	}
	
	private int[] position;
	private double last_time_burned;
	private JTextField[] coordinates;
	private int y_offset_after;
}
