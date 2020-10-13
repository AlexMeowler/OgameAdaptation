package offgame;

public class SpaceYard extends Building 
{
	public SpaceYard()
	{
		super();
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
	}
	
	public SpaceYard(int level)
	{
		super(level);
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
	}
}
