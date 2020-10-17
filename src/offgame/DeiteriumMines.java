package offgame;

import static java.lang.Math.pow;

public class DeiteriumMines extends Building
{
	public DeiteriumMines()
	{
		super();
	}
	
	public DeiteriumMines(int level)
	{
		super(level);
	}
	
	public double calcGathering(int temp)
	{
		return 10 * level * pow(1.1, level) * (-0.002 * temp + 1.28);
	}
	
	public double calcConsuming()
	{
		return 20 * level * pow(1.1, level);
	}
	
	public double calcDifference(int level1, int level2)
	{
		int save_level = level;
		level = level1;
		double first = calcConsuming();
		level = level2;
		double second = calcConsuming();
		level = save_level;
		return second - first;
	}
	
	public double calcDifference(int level1, int level2, int temp)
	{
		int save_level = level;
		level = level1;
		double first = calcGathering(temp);
		level = level2;
		double second = calcGathering(temp);
		level = save_level;
		return second - first;
	}
	
	public double[] calcBuildingCost() 
	{
		double[] d = new double[3];
		d[0] = 225 * pow(1.5, level);
		d[1] = 75 * pow(1.5, level);
		d[2] = 0;
		return d;
	}
	
	public String generateEnergyChange()
	{
		return " (<font color='red'>-" + (int)calcDifference(level, level + 1) + " �������</font>)";
	}
	
	public String generateHeader()
	{
		return "���������� �������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��������� �� ���� �� ������� �������������� ���� ��������.<br>" + super.generateDescription(current_resources);
	}
}
