package offgame;

public class ColonizationTechnology extends Technology
{
	public ColonizationTechnology()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public ColonizationTechnology(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public String generateHeader()
	{
		return "��������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��� ���������� �������� ��� ��������� ���� ������� �� 20 �������. ������ ������� ��������� �������������� ��� ���� �������.<br>" + super.generateDescription(current_resources);
	}
}
