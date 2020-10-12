package offgame;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OffGamePanel extends JPanel
{
	public OffGamePanel() throws IOException
	{
		setName("main_panel");
		background = ImageIO.read(this.getClass().getResourceAsStream("/bg_elite.jpg"));
		
		planets = new Planet[1];
		planets[0] = Planet.generateStartPlanet();
		windows = new ArrayList<>();
		windows.add(new OverviewPanel("overview", planets[0]));
		createUI();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, null);
	}
	
	public void createUI() throws IOException
	{
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0f;
		constraints.weighty = 0.005f;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.ipady = topRowHeight;
		constraints.ipadx = topRowCellWidth;
		constraints.anchor = GridBagConstraints.PAGE_START;
		add(Box.createVerticalStrut(topRowHeight  * constraints.gridheight), constraints);
		constraints.gridx = 6;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		add(Box.createVerticalStrut(topRowHeight  * constraints.gridheight), constraints);
		constraints.ipadx = 0;
		constraints.ipady = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipady = topRowHeight;
		metal.setText("1000");
		add(metal, constraints);
		constraints.gridx = 3;
		constraints.gridy = 0;
		crystal.setText("1000");
		add(crystal, constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		deuterium.setText("1000");
		add(deuterium, constraints);
		constraints.gridx = 5;
		constraints.gridy = 0;
		mail.setText("[0]");
		add(mail, constraints);
		//
		//constraints.weightx = 0.3f;
		//constraints.weighty = 1.0f;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipady = 0;
		constraints.insets.left = 5;
		add(new MenuContainer(), constraints);
		//
		//constraints.weightx = 1.0f;
		//constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		add(new PlanetMenuContainer(planets), constraints);
		//
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		add(Box.createGlue(), constraints);
		//
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		add(windows.get(0), constraints);
		//

		
	}
	
	public Planet getPlanet(int i)
	{
		return planets[i];
	}
	private BufferedImage background;
	private ResourcePanel metal = new ResourcePanel("m.png");
	private ResourcePanel crystal = new ResourcePanel("k.png");
	private ResourcePanel deuterium = new ResourcePanel("d.png");
	private ResourcePanel mail = new ResourcePanel("ml.png");
	private ArrayList<InfoPanel> windows;
	private Planet[] planets;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private int topRowHeight = (int)(dim.height * 0.02);
	private int topRowCellWidth = 130;
}
