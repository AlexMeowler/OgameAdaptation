package adaptogame.ui;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import adaptogame.core.Planet;

public class PlanetMenuContainer extends JPanel 
{
	public PlanetMenuContainer(Planet[] p) throws IOException
	{
		setName("planet menu");
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(new PlanetLabel(p[0]), constraints);
	}
	private class PlanetLabel extends JLabel
	{
		public PlanetLabel(Planet planet)
		{
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			setIcon(new ImageIcon(planet.getImg().getScaledInstance(dim.height / 10, dim.height / 10, Image.SCALE_SMOOTH)));
		}
	}
}
