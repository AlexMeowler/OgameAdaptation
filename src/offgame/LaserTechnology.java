package offgame;

public class LaserTechnology extends Technology
{
	public LaserTechnology()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
	}
	
	public LaserTechnology(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
	}
	
	public String generateHeader()
	{
		return "Лазерная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Благодаря фокусированию света возникает луч, который при попадании на объект наносит ему повреждения.<br>" + super.generateDescription(current_resources);
	}
}
