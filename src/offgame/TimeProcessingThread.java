package offgame;

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
			for(int i = 0; i < panel.getPlanetAmount(); i++)
			{
				panel.getPlanet(i).updateResources();
				panel.getPlanet(i).updateBuildingsProduction();
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
