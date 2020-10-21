package adaptogame.ui;

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
				panel.getPlayer().getPlanet(i).updateResources();
				panel.getPlayer().getPlanet(i).updateBuildingsProduction();
				panel.getPlayer().getPlanet(i).updateResearchProcess();
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
