package org.retal.offgame.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import org.retal.offgame.core.*;

public class PlanetMenuContainer extends JPanel 
{
	public PlanetMenuContainer(Player player) throws IOException
	{
		this.player = player;
		setName("planet menu");
		setOpaque(false);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0f;
		constraints.weighty = 0.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.BOTH;
		int size = player.getPlanetAmount();
		current_planet = new PlanetLabel(player.getPlanet(0), 0);
		current_planet.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		add(current_planet, constraints);
		player.setCurrentPlanet(0);
		for(int i = 1; i < size; i++)
		{
			constraints.gridy = i;
			PlanetLabel label = new PlanetLabel(player.getPlanet(i), i);
			label.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			add(label, constraints);
		}
		
	}
	private class PlanetLabel extends JLabel implements MouseListener
	{
		public PlanetLabel(Planet planet, int num)
		{
			this.num = num;
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setIcon(new ImageIcon(planet.getImg().getScaledInstance(dim.height / 10, dim.height / 10, Image.SCALE_SMOOTH)));
			addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) 
		{
			requestFocusInWindow();
			player.setCurrentPlanet(num);
			setBorder(BorderFactory.createLineBorder(Color.GREEN));
			current_planet.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
			current_planet = this;
		}

		public void mousePressed(MouseEvent e) 
		{
			
		}

		public void mouseReleased(MouseEvent e) 
		{
			
		}

		public void mouseEntered(MouseEvent e) 
		{
			
		}

		public void mouseExited(MouseEvent e) 
		{
			
		}
		
		private int num;
	}
	
	private PlanetLabel current_planet;
	private Player player;
}
