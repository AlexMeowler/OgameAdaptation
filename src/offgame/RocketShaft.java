package offgame;

public class RocketShaft extends Building
{
	public RocketShaft()
	{
		super();
		base_cost[0] = 20000;
		base_cost[1] = 20000;
		base_cost[2] = 1000;
	}
	
	public RocketShaft(int level)
	{
		super(level);
		base_cost[0] = 20000;
		base_cost[1] = 20000;
		base_cost[2] = 1000;
	}
}
