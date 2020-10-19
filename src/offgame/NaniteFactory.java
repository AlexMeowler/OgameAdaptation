package offgame;

public class NaniteFactory extends Building 
{
	public NaniteFactory()
	{
		super();
		base_cost[0] = 1000000;
		base_cost[1] = 500000;
		base_cost[2] = 100000;
		required_buildings[Building.ROBOT_FACTORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 10;
	}
	
	public NaniteFactory(int level)
	{
		super(level);
		base_cost[0] = 1000000;
		base_cost[1] = 500000;
		base_cost[2] = 100000;
		required_buildings[Building.ROBOT_FACTORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 10;
	}
	
	public String generateHeader()
	{
		return "Фабрика нанитов " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Фабрика нанитов производит специальных эволюционированных роботов - нанитов. Наниты - это роботы, которые в состоянии выполнять экстраординарные задания путём объединения. Каждый уровень в 2 раза ускоряет постройку зданий, кораблей и обороны.<br>" + super.generateDescription(current_resources);
	}
}
