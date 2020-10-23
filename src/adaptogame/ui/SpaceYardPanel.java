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
		
		constraints.insets.bottom = 3;
		y_offset = 0;
		requirements_panels = new RequirementsPanel[16];
		for(int i = 0; i < Unit.SHIPS_AMOUNT; i++)
		{
			addRow(Unit.SOLAR_SATELLITE + i + y_offset);
		}
		y_offset_after = y_offset + Unit.SHIPS_AMOUNT;
		constraints.gridx = 1;
		constraints.gridy = y_offset_after;
		constraints.fill = GridBagConstraints.VERTICAL;
		
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
		
		add(new BuildAndInputField("" + constraints.gridx + "." +constraints.gridy), constraints);
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
	
	public void updatePanelUI()
	{
		Component[] list = getComponents();
		for(int i = 0; i < list.length; i++)
		{
			if(list[i] instanceof TextLabel)
			{
				String[] s = list[i].getName().split("\\.");
				int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
				if(coords[1] >= y_offset)
				{
					String header = current_planet.getUnits()[coords[1] - y_offset].generateHeader();
					double[] resources = {current_planet.getCurrentMetal(), current_planet.getCurrentCrystal(), current_planet.getCurrentDeiterium(), current_planet.getCurrentEnergy()};
					String description = current_planet.getUnits()[coords[1] - y_offset].generateDescription(resources);
					if(coords[0] == 1)
					{
						((TextLabel)list[i]).setText(header + "<br>" + description + createTimeString(coords[1] - y_offset));
					}
				}
			}
			else
			{
				if(list[i] instanceof BuildAndInputField)
				{
					String[] s = list[i].getName().split("\\.");
					int[] coords = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
					Component[] components = ((BuildAndInputField)list[i]).getComponents();
					boolean flag = current_planet.requirementsMet(EntityCategory.FLEET, coords[1]);
					for(int j = 0; j < components.length; j++)
					{
						components[j].setVisible(flag);
						components[j].setEnabled(flag);
					}
				}
			}
		}
		for(int i = 0; i < requirements_panels.length; i++)
		{
			int[] buildings_required = current_planet.getUnits()[i].getRequiredBuildings();
			int[] technologies_required = current_planet.getUnits()[i].getRequiredTechnologies();
			requirements_panels[i].updatePanelUI(buildings_required, technologies_required, current_planet.getBuildings(), player.getTechs());
			if(current_planet.requirementsMet(EntityCategory.FLEET, i))
			{
				requirements_panels[i].setVisible(false);
			}
		}
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
	
	private class BuildAndInputField extends JPanel implements ActionListener, KeyListener
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
			amount_field.addKeyListener(this);;
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
			int i = 0;
			i++;
		}
		
		public void keyTyped(KeyEvent e) 
		{
			
		}

		public void keyPressed(KeyEvent e) 
		{
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				requestFocusInWindow();
			}
		}

		public void keyReleased(KeyEvent e) 
		{
			
		}
		
		private JButton build_button;
		private JTextField amount_field;
	}
	
	private RequirementsPanel[] requirements_panels;
	private int y_offset_after;
	private JTextArea build_queue;
	private TextLabel current_building;
}
