package offgame;

public class HyperspaceEngine extends Technology 
{
	public HyperspaceEngine()
	{
		super();
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public HyperspaceEngine(int level)
	{
		super(level);
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public String generateHeader()
	{
		return "��������������������� ��������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��������� ���������������-���������� ������ � ���������������� ��������� ������� ������������ ���������, ��� ������� �������������� ������ ����������. ��� ���� ������ �������������������� ������, ��� ���� ������ ������������, ��������� ���� � ������ ������� �������� �������� ���������� �� 30% (������������ ������� �������� ��������).<br>" + super.generateDescription(current_resources);
	}
}
