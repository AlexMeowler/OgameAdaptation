package adaptogame.core.buildings;

import static java.lang.Math.E;
import static java.lang.Math.pow;

import java.text.NumberFormat;
import java.util.Locale;

public class DeiteriumStorage extends Building 
{
	public DeiteriumStorage(String name)
	{
		super(name);
		base_cost[0] = 2000;
		base_cost[1] = 2000;
		base_cost[2] = 0;
		required_buildings[Building.DEITERIUM_MINES] = 6;
	}
	
	public DeiteriumStorage(int level, String name)
	{
		super(level, name);
		base_cost[0] = 2000;
		base_cost[1] = 2000;
		base_cost[2] = 0;
		required_buildings[Building.DEITERIUM_MINES] = 6;
	}
	
	public double calcGathering()
	{
		if(level != 0)
		{
			return (int)(2.5 * pow(E, 20 * level / 33)) * 5000;
		}
		else
		{
			return 10000;
		}
	}
	
	public double calcGathering(int level)
	{
		if(level != 0)
		{
			return (int)(2.5 * pow(E, 20 * level / 33)) * 5000;
		}
		else
		{
			return 10000;
		}
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Огромные ёмкости для сберегания добытого дейтерия.<br>Вместимость на следующем уровне: <font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)calcGathering(level + 1)) + "</font><br>" + super.generateDescription(current_resources);
	}
}
