package adaptogame.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

import adaptogame.core.Planet;
import adaptogame.core.Player;
import adaptogame.core.units.Unit;

public class GalaxyViewPanel extends InfoPanel 
{
	public GalaxyViewPanel(String name, Player player)
	{
		super(name, player);
		setOpaque(false);
		position = Arrays.copyOfRange(current_planet.getCoords(), 0, 2);
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
		add(new ChangeSystemPanel(), constraints);
		constraints.ipady = 0;
		//
		constraints.gridy = 3;
		add(new TextLabel("<div style='text-align:center'>Израсходовано <font color='red'>" + 0 + "</font> дейтерия</div>", constraints.gridx + "." + constraints.gridy, new Color(0, 0 , 0 ,0)), constraints);
		constraints.insets.top = 0;
	}
	
	private void addSelectionBlock(String block_name, String fieldText, int x)
	{
		JButton button_left = createButton("←");
		JButton button_right = createButton("→");
		JTextField field = new JTextField(fieldText);
		button_left.addActionListener(e -> field.setText("" + Math.max(1, (Integer.parseInt(field.getText()) - 1))));
		button_right.addActionListener(e -> field.setText("" + Math.min(Planet.UNIVERSE_BOUNDS[1], (Integer.parseInt(field.getText()) + 1))));
		field.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 134)));
		field.setBackground(new Color(102, 102, 102));
		field.setForeground(Color.WHITE);
		constraints.gridx = x;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.2f;
		constraints.weighty = 0.0f;
		constraints.insets.right = x != 6 ? 10 : 0;
		constraints.ipady = 5;
		//constraints.ipadx = 5;
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
			add(new TextLabel("<div style='text-align:center'>" + (i + 1) + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
			constraints.gridx++;
			//!!!!!
			Planet planet = OffGamePanel.getPlanetByCoordinates(new int[] {1, 1, i + 1});
			PlanetImg img = new PlanetImg(planet);
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
		add(new TextLabel("<div style='text-align:center'>" + "Отправить экспедицию" + "</div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		
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
		add(new TextLabel("(0 обитаемые планеты )", constraints.gridx + "." + constraints.gridy, CATEGORY_BACKGROUND_COLOR), constraints);
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
		public PlanetImg(Planet planet)
		{
			try
			{
				setIcon(new ImageIcon(planet.getImg().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH)));
			}
			catch(NullPointerException e)
			{
				
			}
			setBackground(BACKGROUND_COLOR);
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true);
		}
		
		private static final int SIZE = 40;
	}
	
	private class ChangeSystemPanel extends JPanel
	{
		public ChangeSystemPanel()
		{
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
			add(button, constraints);
			constraints.insets.left = 0;
			constraints.gridx++;
			constraints.weightx = 0.7f;
			add(new TextLabel("<div style='text-align:left'>" + "Дейтерия на планете: <font color='lime'>" + (int)current_planet.getCurrentDeiterium() + "</font></div>", constraints.gridx + "." + constraints.gridy, BACKGROUND_COLOR), constraints);
		}
	}
	
	private int[] position;
	private int y_offset_after;
}
