package offgame;

public class HyperspaceTechnology extends Technology 
{
	public HyperspaceTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 5;
	}
	
	public HyperspaceTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 5;
	}
	
	public String generateHeader()
	{
		return "Гиперпространственная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Путём сплетения 4-го и 5-го измерения стало возможным исследовать новый более экономный и эффективный двигатель.<br>" + super.generateDescription(current_resources);
	}
}
