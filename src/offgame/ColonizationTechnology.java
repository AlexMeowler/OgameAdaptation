package offgame;

public class ColonizationTechnology extends Technology
{
	public ColonizationTechnology()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
	}
	
	public ColonizationTechnology(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
	}
	
	public String generateHeader()
	{
		return "Колонизационная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Эта технология позволит вам расширить вашу империю до 20 колоний. Каждый уровень позволяет контролировать ещё одну колонию.<br>" + super.generateDescription(current_resources);
	}
}
