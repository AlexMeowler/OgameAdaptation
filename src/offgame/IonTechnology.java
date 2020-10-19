package offgame;

public class IonTechnology extends Technology
{
	public IonTechnology()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 300; 
		base_cost[2] = 100;
		required_buildings[Building.LABORATORY] = 4;
		required_technologies[Technology.LASER_TECHNOLOGY] = 5;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 4;
	}
	
	public IonTechnology(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 300; 
		base_cost[2] = 100;
		required_buildings[Building.LABORATORY] = 4;
		required_technologies[Technology.LASER_TECHNOLOGY] = 5;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 4;
	}
	
	public String generateHeader()
	{
		return "Ионная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Поистине смертоносный наводимый луч из ускоренных ионов. При попадании на какой-либо объект они наносят огромный ущерб.<br>" + super.generateDescription(current_resources);
	}
}
