package offgame;

public class HyperspaceTechnology extends Technology 
{
	public HyperspaceTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 5;
	}
	
	public HyperspaceTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 5;
	}
	
	public String generateHeader()
	{
		return "��������������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���� ��������� 4-�� � 5-�� ��������� ����� ��������� ����������� ����� ����� ��������� � ����������� ���������.<br>" + super.generateDescription(current_resources);
	}
}
