package adaptogame.core.units;

import adaptogame.core.buildings.Building;

public class SolarSatellite extends Unit 
{
	public SolarSatellite(String name) 
	{
		super(name);
		cost[1] = 2000;
		cost[2] = 500;
		structure = 200;
		shields = 10;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
	}
	
	public SolarSatellite(String name, int amount) 
	{
		super(name, amount);
		cost[1] = 2000;
		cost[2] = 500;
		structure = 200;
		shields = 10;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Платформы из солнечных батарей, которые находятся на высокой орбите. Они аккумулируют солнечную энергию. Чем жарче планета тем эффективнее работают солнечные спутники. На текущей планете один спутник генерирует ≈ РЕЗЕРВ энергии.<br>" + super.generateDescription(current_resources);
	}
}
