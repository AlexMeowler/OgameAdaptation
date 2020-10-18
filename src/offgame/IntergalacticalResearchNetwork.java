package offgame;

public class IntergalacticalResearchNetwork extends Technology 
{ // сделать чтобы работало
	public IntergalacticalResearchNetwork()
	{
		super();
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
	}
	
	public IntergalacticalResearchNetwork(int level)
	{
		super(level);
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
	}
	
	public String generateHeader()
	{
		return "Межгалактическая исследовательская сеть " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Эта сеть делает возможным общение учёных, работающих в исследовательских лабораториях разных планет. Каждый новый уровень позволяет присоединить к сети дополнительную лабораторию, что позволяет ускорить исследования.<br>" + super.generateDescription(current_resources);
	}
}
