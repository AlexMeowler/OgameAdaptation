package offgame;

public class PlasmaTechnology extends Technology
{
	public PlasmaTechnology()
	{
		super();
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		required_buildings[Building.LABORATORY] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 10;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 8;
		required_technologies[Technology.ION_TECHNOLOGY] = 5;
	}
	
	public PlasmaTechnology(int level)
	{
		super(level);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		required_buildings[Building.LABORATORY] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 10;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 8;
		required_technologies[Technology.ION_TECHNOLOGY] = 5;
	}
	
	public String generateHeader()
	{
		return "���������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���������� �������� ������ ����������, ������� �������� �� ����, � �������������������� ������. ��� ��������� ��������������� �������� ��� ��������� �� �����-���� ������.<br>" + super.generateDescription(current_resources);
	}
}
