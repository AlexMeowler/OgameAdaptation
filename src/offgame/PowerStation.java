package offgame;

import static java.lang.Math.pow;

public class PowerStation extends Building
{
	public PowerStation()
	{
		super();
	}
	
	public PowerStation(int level)
	{
		super(level);
	}
	
	public double calcGathering()
	{
		return 20 * level * pow(1.1, level);
	
	}
	
	public double[] calcBuildingCost() 
	{
		double[] d = new double[4];
		d[0] = 75 * pow(1.5, level);
		d[1] = 30 * pow(1.5, level);
		d[2] = 0;
		d[3] = 0;
		return d;
	}
	
	public String generateEnergyChange()
	{
		return " (<font color='lime'>+" + (int)calcDifference(level, level + 1) + " Ёнерги€</font>)";
	}
	
	public String generateHeader()
	{
		return "—олнечна€ электростанци€ " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "ѕроизводит энергию из солнечных лучей.Ёнерги€ требуетс€ дл€ работы всех строений, производ€щих ресурсы.<br>" + super.generateDescription(current_resources);
	}
}
