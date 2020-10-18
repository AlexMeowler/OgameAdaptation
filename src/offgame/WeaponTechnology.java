package offgame;

public class WeaponTechnology extends Technology 
{
	public WeaponTechnology()
	{
		super();
		base_cost[0] = 800;
		base_cost[1] = 200; 
		base_cost[2] = 0;
	}
	
	public WeaponTechnology(int level)
	{
		super(level);
		base_cost[0] = 800;
		base_cost[1] = 200; 
		base_cost[2] = 0;
	}
	
	public String generateHeader()
	{
		return "Оружейная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Оружейная технология делает системы вооружения эффективней. Каждый уровень увеличивает мощность орудий флота и обороны на 10%.<br>" + super.generateDescription(current_resources);
	}
}
