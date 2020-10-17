package offgame;

import static java.lang.Math.*;

import java.text.NumberFormat;
import java.util.Locale;

public class MetalStorage extends Building 
{
	public MetalStorage()
	{
		super();
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
	}
	
	public MetalStorage(int level)
	{
		super(level);
		base_cost[0] = 2000;
		base_cost[1] = 0;
		base_cost[2] = 0;
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
	
	public String generateHeader()
	{
		return "Хранилище металла " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Хранилище для необработанных руд металлов до их дальнейшей переработки.<br>Текущая вместимость: <font color='lime'>" + NumberFormat.getNumberInstance(Locale.US).format((int)calcGathering()) + "</font><br>" + super.generateDescription(current_resources);
	}
}
