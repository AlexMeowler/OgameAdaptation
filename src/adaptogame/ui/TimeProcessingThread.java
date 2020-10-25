package adaptogame.ui;

import adaptogame.core.Planet;

public class TimeProcessingThread extends Thread 
{
	public TimeProcessingThread(OffGamePanel panel)
	{
		this.panel = panel;
	}
	public void run()
	{
		while(true)
		{
			for(int i = 0; i < panel.getPlayer().getPlanetAmount(); i++)
			{
				Planet planet = panel.getPlayer().getPlanet(i);
				planet.updateResources();
				planet.updateBuildingsProduction();
				planet.updateResearchProcess();
				planet.updateUnitsProduction();
				panel.getCurrentWindow().updatePanelUI();
			}
			panel.updateResourceBar();
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				
			}
		}
	}
	
	private OffGamePanel panel;
}
