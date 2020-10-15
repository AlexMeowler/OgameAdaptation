package offgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OffGamePanel extends JPanel 
{
	public OffGamePanel() throws IOException
	{
		setVisible(false);
		setName("main_panel");
		current_planet = 0;
		background = ImageIO.read(this.getClass().getResourceAsStream("/bg_elite.jpg"));
		planets = new Planet[1];
		planets[0] = Planet.generateStartPlanet();
		windows = new ArrayList<>();
		scrolls = new ArrayList<>();
		windows.add(new InfoPanel("tech_tree", planets[current_planet]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.TECH_TREE)));
		prepareScrollBar(scrolls.get(MenuContainer.TECH_TREE));
		windows.add(new OverviewPanel("overview", planets[current_planet]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.OVERVIEW)));
		prepareScrollBar(scrolls.get(MenuContainer.OVERVIEW));
		windows.add(new InfoPanel("resources", planets[current_planet]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.RESOURCES)));
		prepareScrollBar(scrolls.get(MenuContainer.RESOURCES));
		windows.add(new BuildingPanel("buildings", planets[current_planet]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.BUILDINGS)));
		prepareScrollBar(scrolls.get(MenuContainer.BUILDINGS));
		currentActiveWindow = MenuContainer.OVERVIEW;
		setWindowActive(scrolls.get(MenuContainer.OVERVIEW));
		createUI();
		clock = new TimeProcessingThread(this);
		clock.start();
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
		constraints.weightx = 0.5f;
		constraints.weighty = 0.0f;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.ipady = topRowHeight;
		constraints.ipadx = topRowCellWidth;
		constraints.anchor = GridBagConstraints.PAGE_START;
		add(Box.createVerticalStrut(topRowHeight  * constraints.gridheight), constraints);
		constraints.weightx = 0.3f;
		constraints.gridx = 7;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		add(Box.createVerticalStrut(dim.height), constraints);
		constraints.ipadx = 0;
		constraints.ipady = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipady = topRowHeight;
		constraints.weightx = 0.4f;
		metal.setTextAsNum((int)planets[current_planet].getCurrentMetal());
		add(metal, constraints);
		constraints.gridx = 3;
		constraints.gridy = 0;
		crystal.setTextAsNum((int)planets[current_planet].getCurrentCrystal());
		add(crystal, constraints);
		constraints.gridx = 4;
		constraints.gridy = 0;
		deiterium.setTextAsNum((int)planets[current_planet].getCurrentDeiterium());
		add(deiterium, constraints);
		constraints.gridx = 5;
		constraints.gridy = 0;
		electricity.setTextAsNum((int)planets[current_planet].getCurrentElectricity());
		add(electricity, constraints);
		constraints.gridx = 6;
		constraints.gridy = 0;
		mail.setTextAsMail(0);
		add(mail, constraints);
		//
		constraints.weightx = 0.0f;
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
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		add(new PlanetMenuContainer(planets), constraints);
		//
		constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 5;
		constraints.gridheight = 2;
		constraints.insets.left = 0;
		for(int i = 0; i < scrolls.size(); i++)
		{
			add(scrolls.get(i), constraints);
		}
		//

	}
	
	public int getCurrentPlanet()
	{
		return current_planet;
	}
	
	public Planet getPlanet(int i)
	{
		return planets[i];
	}
	
	public int getPlanetAmount()
	{
		return planets.length;
	}
	
	public void updateResourceBar()
	{
		metal.setTextAsNum((int)planets[0].getCurrentMetal()); //current plant
		crystal.setTextAsNum((int)planets[0].getCurrentCrystal());
		deiterium.setTextAsNum((int)planets[0].getCurrentDeiterium());
	}
	
	public void killClock()
	{
		clock.interrupt();
	}
	
	public void setCurrentWindow(int i)
	{
		setWindowNotActive(scrolls.get(currentActiveWindow));
		setWindowActive(scrolls.get(i));
		currentActiveWindow = i;
		revalidate();
	}
	
	private void prepareScrollBar(JScrollPane p)
	{
		p.setOpaque(false);
		p.getViewport().setOpaque(false);
		p.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		p.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p.setBorder(BorderFactory.createEmptyBorder());
		p.setWheelScrollingEnabled(false);
		setWindowNotActive(p);
		
	}
	
	private void setWindowNotActive(JScrollPane p)
	{
		p.setVisible(false);
		p.setEnabled(false);
		//setComponentZOrder(p, 1);
		((InfoPanel)((JViewport) p.getComponents()[0]).getView()).removeMouseWheelListener((InfoPanel)((JViewport) p.getComponents()[0]).getView());
		((InfoPanel)((JViewport) p.getComponents()[0]).getView()).removeMouseListener((InfoPanel)((JViewport) p.getComponents()[0]).getView());
	}
	
	private void setWindowActive(JScrollPane p)
	{
		p.setVisible(true);
		p.setEnabled(true);
		//setComponentZOrder(p, 0);
		((InfoPanel)((JViewport) p.getComponents()[0]).getView()).addMouseWheelListener((InfoPanel)((JViewport) p.getComponents()[0]).getView());
		((InfoPanel)((JViewport) p.getComponents()[0]).getView()).addMouseListener((InfoPanel)((JViewport) p.getComponents()[0]).getView());
	}
	
	private BufferedImage background;
	private ResourcePanel metal = new ResourcePanel("m.png");
	private ResourcePanel crystal = new ResourcePanel("k.png");
	private ResourcePanel deiterium = new ResourcePanel("d.png");
	private ResourcePanel electricity = new ResourcePanel("e.png");
	private ResourcePanel mail = new ResourcePanel("ml.png");
	public ArrayList<InfoPanel> windows;
	private ArrayList<JScrollPane> scrolls;
	private Planet[] planets;
	private int current_planet;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private int topRowHeight = (int)(dim.height * 0.02);
	private int topRowCellWidth = 130;
	private int currentActiveWindow;
	private TimeProcessingThread clock;
}
