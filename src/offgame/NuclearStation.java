package offgame;

import static java.lang.Math.pow;

public class NuclearStation extends Building 
{
	public NuclearStation()
	{
		super();
		required_buildings[Building.DEITERIUM_MINES] = 5;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public NuclearStation(int level)
	{
		super(level);
		required_buildings[Building.DEITERIUM_MINES] = 5;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public double calcGathering(int temp)
	{
		return 30 * level * pow(1.05 + temp * 0.01, level);
	
	}
	public double calcConsuming()
	{
		return 10 * level * pow(1.1, level);
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
		return " (<font color='lime'>+" + (int)calcDifference(level, level + 1) + " �������</font>)";
	}
	
	public String generateHeader()
	{
		return "������������ �������������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "�������� ������� �� �������� ����������� ����� ����� ����� ������� ������� ��������. ���������� ��������.<br>" + super.generateDescription(current_resources);
	}
}
