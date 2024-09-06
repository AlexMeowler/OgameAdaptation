package adaptogame.core.technologies;

import adaptogame.core.buildings.Building;

public class IntergalacticalResearchNetwork extends Technology 
{ // сделать чтобы работало
	public IntergalacticalResearchNetwork(String name)
	{
		super(name);
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
		required_buildings[Building.LABORATORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
	}
	
	public IntergalacticalResearchNetwork(int level, String name)
	{
		super(level, name);
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
		required_buildings[Building.LABORATORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Эта сеть делает возможным общение учёных, работающих в исследовательских лабораториях разных планет. Каждый новый уровень позволяет присоединить к сети дополнительную лабораторию, что позволяет ускорить исследования.<br>" + super.generateDescription(current_resources);
	}
}
