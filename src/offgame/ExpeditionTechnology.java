package offgame;

public class ExpeditionTechnology extends Technology 
{
	public ExpeditionTechnology()
	{
		super();
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
	}
	
	public ExpeditionTechnology(int level)
	{
		super(level);
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
	}
	
	public String generateHeader()
	{
		return "Экспедиционная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Позволяет посылать экспедиции в неизведанные дали. Каждый уровень позволяет контролировать большее количество одновременных экспедиций.<br>" + super.generateDescription(current_resources);
	}
}
