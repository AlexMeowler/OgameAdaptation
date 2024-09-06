package org.retal.offgame.core;

import org.retal.offgame.ui.MainPanel;

public class TimeProcessingThread extends Thread 
{
	public TimeProcessingThread(MainPanel panel)
	{
		this.panel = panel;
	}
	public void run()
	{
		Player player = panel.getPlayer();
		while(true)
		{
			player.processFleets();
			for(int i = 0; i < player.getPlanetAmount(); i++)
			{
				Planet planet = player.getPlanet(i);
				planet.updateResources();
				planet.updateBuildingsProduction();
				planet.updateResearchProcess();
				planet.updateUnitsProduction();
			}
			panel.getCurrentWindow().updatePanelUI();
			panel.updateResourceBar();
			panel.checkPlanetChange();
			try 
			{
				Thread.sleep(SLEEP_DURATION);
			} 
			catch (InterruptedException e) 
			{
				
			}
		}
	}
	
	public static final int SLEEP_DURATION = 300;
	private MainPanel panel;
}
