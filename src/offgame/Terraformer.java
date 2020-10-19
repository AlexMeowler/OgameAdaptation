package offgame;

public class Terraformer extends Building
{
	public Terraformer()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
		required_buildings[Building.NANITE_FACTORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
	}
	
	public Terraformer(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
		required_buildings[Building.NANITE_FACTORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
	}
	
	public String generateHeader()
	{
		return "����������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "����������� ����� ��������������� �������� ����������, ����� �� ���������� ��� ���������. ����������� ���������� ����� �� �������.<br>" + super.generateDescription(current_resources);
	}
}
