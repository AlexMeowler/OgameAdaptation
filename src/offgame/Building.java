package offgame;

import static java.lang.Math.pow;

public abstract class Building
{
	public Building()
	{
		level = 0;
		base_cost = new double[3];
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
	}
	
	public Building(int level)
	{
		this.level = level;
	}
	
	public double calcGathering()
	{
		return 0;
	}
	
	public double calcGathering(int temp)
	{
		return 0;
	}
	
	public double calcConsuming()
	{
		return 0;
	}
	
	public double calcDifference(int level1, int level2)
	{
		int save_level = level;
		level = level1;
		double first = calcGathering();
		level = level2;
		double second = calcGathering();
		level = save_level;
		return second - first;
	}
	
	public double[] calcBuildingCost() 
	{
		double[] cost = new double[3];
		for(int i = 0; i < 3; i++)
		{
			cost[i] = base_cost[i] * pow(2, level);
		}
		return cost;
	}
	
	public long calcBuildingTime(int robotics_factory, int nanite_factory)
	{
		double[] d = calcBuildingCost();
		return (long)( ((d[0] + d[1]) / 2500) * (1 / (robotics_factory + 1)) * pow(0.5, nanite_factory) * 15 * 60);
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public static Building[] createList()
	{
		Building[] list = new Building[14];
		list[POWER_STATION] = new PowerStation();
		list[METAL_MINES] = new MetalMines();
		list[CRYSTAL_MINES] = new CrystalMines();
		list[DEITERIUM_MINES] = new DeiteriumMines();
		list[ROBOT_FACTORY] = new RobotFactory();
		list[LABORATORY] = new Laboratory();
		list[SPACE_YARD] = new SpaceYard();
		list[METAL_STORAGE] = new MetalStorage();
		list[CRYSTAL_STORAGE] = new CrystalStorage();
		list[DEITERIUM_STORAGE] = new DeiteriumStorage();
		list[NUCLEAR_STATION] = new NuclearStation();
		list[NANITE_FACTORY] = new NaniteFactory();
		list[TERRAFORMER] = new Terraformer();
		list[ROCKET_SHAFT] = new RocketShaft();
		return list;
	}
	
	protected int level;
	protected int code;
	protected double[] base_cost; 
	public static final int POWER_STATION = 0;
	public static final int METAL_MINES = 1;
	public static final int CRYSTAL_MINES = 2;
	public static final int DEITERIUM_MINES = 3;
	public static final int ROBOT_FACTORY = 4;
	public static final int SPACE_YARD = 5;
	public static final int LABORATORY = 6;
	public static final int METAL_STORAGE = 7;
	public static final int CRYSTAL_STORAGE = 8;
	public static final int DEITERIUM_STORAGE = 9;
	public static final int NUCLEAR_STATION = 10;
	public static final int NANITE_FACTORY = 11;
	public static final int TERRAFORMER = 12;
	public static final int ROCKET_SHAFT = 13;
}
