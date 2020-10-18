package offgame;

public class EnergyTechnology extends Technology 
{
	public EnergyTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
	}
	
	public EnergyTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
	}
	
	public String generateHeader()
	{
		return "Энергетическая технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Обладание различными видами энергии необходимо для многих новых технологий. Так же каждый уровень увеличивает выработку энергии на Термоядерных электростанциях.<br>" + super.generateDescription(current_resources);
	}
}
