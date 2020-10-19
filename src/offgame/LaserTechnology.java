package offgame;

public class LaserTechnology extends Technology
{
	public LaserTechnology()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public LaserTechnology(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public String generateHeader()
	{
		return "�������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��������� ������������� ����� ��������� ���, ������� ��� ��������� �� ������ ������� ��� �����������.<br>" + super.generateDescription(current_resources);
	}
}
