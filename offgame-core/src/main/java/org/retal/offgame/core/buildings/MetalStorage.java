package org.retal.offgame.core.buildings;

import static java.lang.Math.*;

import java.text.NumberFormat;
import java.util.Locale;

public class MetalStorage extends Building 
{
	public MetalStorage(String name)
	{
		super(name);
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
		required_buildings[Building.METAL_MINES] = 4;
	}
	
	public MetalStorage(int level, String name)
	{
		super(level, name);
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
		required_buildings[Building.METAL_MINES] = 4;
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
		return "Хранилище для необработанных руд металлов до их дальнейшей переработки.<br>Вместимость на следующем уровне: <font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)calcGathering(level + 1)) + "</font><br>" + super.generateDescription(current_resources);
	}
}
