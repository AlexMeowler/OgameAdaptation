package offgame;

import static java.lang.Math.*;

public class MetalStorage extends Building 
{
	public MetalStorage()
	{
		super();
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
	}
	
	public MetalStorage(int level)
	{
		super(level);
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
	}
	
	public double calcGathering()
	{
		if(level != 0)
		{
			return (int)(2.5 * pow(E, 20 * level / 33)) * 5000;
		}
		else
		{
			return 10000;
		}
	}
}
