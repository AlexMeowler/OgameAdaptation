package adaptogame.core;

public class WeaponTechnology extends Technology 
{
	public WeaponTechnology(String name)
	{
		super(name);
		base_cost[0] = 800;
		base_cost[1] = 200; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 4;
	}
	
	public WeaponTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 800;
		base_cost[1] = 200; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 4;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Оружейная технология делает системы вооружения эффективней. Каждый уровень увеличивает мощность орудий флота и обороны на 10%.<br>" + super.generateDescription(current_resources);
	}
}
