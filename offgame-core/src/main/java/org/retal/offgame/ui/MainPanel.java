package org.retal.offgame.ui;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.retal.offgame.core.Planet;
import org.retal.offgame.core.Player;
import org.retal.offgame.core.TimeProcessingThread;

public class MainPanel extends JPanel 
{
	public MainPanel() throws IOException
	{
		setVisible(false);
		setName("main_panel");
		
		background = ImageIO.read(this.getClass().getResourceAsStream("/bg_elite.jpg"));
		//
		players = new Player[2];
		players[0] = new Player("Игрок(Вы)", 1);
		players[1] = new Player("Enemy", 3);
		//
		UIManager.put("ToolTip.background", InfoPanel.BACKGROUND_COLOR);
		UIManager.put("ToolTip.foreground", Color.WHITE);
		setResourcesToolTips();
		//
		windows = new ArrayList<>();
		scrolls = new ArrayList<>();
		windows.add(new TechTreePanel("tech_tree", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.TECH_TREE)));
		prepareScrollName(scrolls.get(MenuContainer.TECH_TREE));
		windows.add(new PlanetOverviewPanel("overview", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.OVERVIEW)));
		prepareScrollName(scrolls.get(MenuContainer.OVERVIEW));
		windows.add(new ResourcesOverviewPanel("resources", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.RESOURCES)));
		prepareScrollName(scrolls.get(MenuContainer.RESOURCES));
		windows.add(new BuildingPanel("buildings", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.BUILDINGS)));
		prepareScrollName(scrolls.get(MenuContainer.BUILDINGS));
		windows.add(new ResearchPanel("research", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.RESEARCH)));
		prepareScrollName(scrolls.get(MenuContainer.RESEARCH));
		windows.add(new SpaceYardFleetPanel("space_yard", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.SPACE_YARD)));
		prepareScrollName(scrolls.get(MenuContainer.SPACE_YARD));
		windows.add(new SpaceYardDefensePanel("space_yard_defence", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.DEFENCE)));
		prepareScrollName(scrolls.get(MenuContainer.DEFENCE));
		windows.add(new FleetControlPanel("fleet_control", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.FLEET)));
		prepareScrollName(scrolls.get(MenuContainer.FLEET));
		windows.add(new GalaxyViewPanel("galaxy", players[0]));
		scrolls.add(new JScrollPane(windows.get(MenuContainer.GALAXY)));
		prepareScrollName(scrolls.get(MenuContainer.GALAXY));
		//
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
		//constraints.ipady = topRowHeight;
		//constraints.ipadx = topRowCellWidth;
		constraints.anchor = GridBagConstraints.PAGE_START;
		//add(Box.createVerticalStrut(31), constraints);
		constraints.weightx = 0.3f;
		constraints.weighty = 1.0f;
		constraints.gridx = 7;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		add(Box.createVerticalStrut(dim.height), constraints);
		constraints.gridheight = 1;
		constraints.ipadx = 0;
		constraints.ipady = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.ipady = topRowPadding;
		constraints.weightx = 0.2f;
		constraints.weighty = 0.0f;
		constraints.insets.left = INFO_PANELS_LEFT_INSET;
		add(metal, constraints);
		metal.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentMetal());
		constraints.insets.left = 0;
		constraints.gridx = 3;
		constraints.gridy = 0;
		add(crystal, constraints);
		crystal.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentCrystal());
		constraints.gridx = 4;
		constraints.gridy = 0;
		add(deiterium, constraints);
		deiterium.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentDeiterium());
		constraints.gridx = 5;
		constraints.gridy = 0;
		electricity.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentEnergy());
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
		add(new PlanetMenuContainer(players[0]), constraints);
		//
		constraints.weighty = 1.0f;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 2; 
		constraints.gridy = 1;
		constraints.gridwidth = 5;
		constraints.gridheight = 2;
		constraints.insets.left = INFO_PANELS_LEFT_INSET;
		for(int i = 0; i < scrolls.size(); i++)
		{
			add(scrolls.get(i), constraints);
		}
		//
		constraints.insets.left = 0;
	}
	
	public Player getPlayer()
	{
		return players[0];
	}
	
	public void updateResourceBar()
	{
		metal.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentMetal()); //current planet
		crystal.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentCrystal());
		deiterium.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentDeiterium());
		electricity.setTextAsNum((int)players[0].getCurrentPlanet().getCurrentEnergy());
		setResourcesToolTips();
	}
	
	private void setResourcesToolTips()
	{
		metal.setToolTipText("<html><font size='4'>Металл<br>Вместимость: " + NumberFormat.getNumberInstance(Locale.US).format((int)players[0].getCurrentPlanet().getMetalCapacity()) + "</font></html>");
		crystal.setToolTipText("<html><font size='4'>Кристалл<br>Вместимость: " + NumberFormat.getNumberInstance(Locale.US).format((int)players[0].getCurrentPlanet().getCrystalCapacity()) + "</font></html>");
		deiterium.setToolTipText("<html><font size='4'>Дейтерий<br>Вместимость: " + NumberFormat.getNumberInstance(Locale.US).format((int)players[0].getCurrentPlanet().getDeiteriumCapacity()) + "</font></html>");
	}
	
	public void killClock()
	{
		clock.interrupt();
	}
	
	public InfoPanel getCurrentWindow()
	{
		return windows.get(currentActiveWindow);
	}
	
	public void setCurrentWindow(int i)
	{
		if((i == MenuContainer.FLEET)|| (i == MenuContainer.GALAXY))
		{
			windows.get(i).updateCurrentPlanet();
		}
		setWindowNotActive(scrolls.get(currentActiveWindow));
		windows.get(i).updatePanelUI();
		setWindowActive(scrolls.get(i));
		currentActiveWindow = i;
	}
	
	private void prepareScrollName(JScrollPane p)
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
	
	public void checkPlanetChange()
	{
		if(players[0].isPlanetChanged())
		{
			int size = windows.size();
			for(int i = 0; i < size; i++)
			{
				InfoPanel p = windows.get(i);
				p.updateCurrentPlanet();
				p.updatePanelUI();
			}
			players[0].planetChangeHandled();
		}
	}
	
	public static Planet getPlanetByCoordinates(int[] coords)
	{
		for(int i = 0; i < players.length; i++)
		{
			for(int j = 0; j < players[i].getPlanetAmount(); j++)
			{
				if(Arrays.equals(players[i].getPlanet(j).getCoords(), coords))
				{
					return players[i].getPlanet(j);
				}
			}
		}
		return null;
	}
	
	private BufferedImage background;
	private ResourcePanel metal = new ResourcePanel("m.png");
	private ResourcePanel crystal = new ResourcePanel("k.png");
	private ResourcePanel deiterium = new ResourcePanel("d.png");
	private ResourcePanel electricity = new ResourcePanel("e.png");
	private ResourcePanel mail = new ResourcePanel("ml.png");
	private ArrayList<InfoPanel> windows;
	private ArrayList<JScrollPane> scrolls;
	private static Player[] players;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private int topRowPadding = 15;
	private int currentActiveWindow;
	private TimeProcessingThread clock;
	private static final int INFO_PANELS_LEFT_INSET = 50; 
}
