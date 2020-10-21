package adaptogame.core;

import static java.lang.Math.*;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Building
{
	public Building(String name)
	{
		this.name = name;
		level = 0;
		base_cost = new double[4];
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 0;
		required_buildings = new int[14];
		required_technologies = new int[17];
		base = 2;
		build_end_time = null;
	}
	
	public Building(int level, String name)
	{
		this.level = level;
		this.name = name;
		base_cost = new double[4];
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 0;
		required_buildings = new int[14];
		required_technologies = new int[17];
		base = 2;
		build_end_time = null;
	}
	
	public int[] getRequiredBuildings()
	{
		return required_buildings;
	}
	
	public int[] getRequiredTechnologies()
	{
		return required_technologies;
	}
	
	public double calcGathering()
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
		double[] cost = new double[4];
		for(int i = 0; i < 4; i++)
		{
			cost[i] = base_cost[i] * pow(base, level);
		}
		return cost;
	}
	
	public long calcBuildingTime(int robotics_factory, int nanite_factory) // возвращает в секундах
	{
		double[] d = calcBuildingCost();
		//return (long)max(( ((d[0] + d[1]) / 2500) * (1 / (robotics_factory + 1)) * pow(0.5, nanite_factory) * 15 * 60), 1);
		return 2;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void updateLevel()
	{
		level++;
	}
	
	public String generateEnergyChange()
	{
		return "";
	}
	
	public String generateHeader()
	{
		return generateHeaderWithoutLevel() + getCurrentLevelString();
	}
	
	public String generateHeaderWithoutLevel()
	{
		return name;
	}
	
	protected String getCurrentLevelString()
	{
		String s = "";
		if(level != 0)
		{
			s = "(Уровень " + level + ")";
		}
		return s;
	}
	
	
	
	public String generateDescription(double[] current_resources)
	{
		double[] building_cost = calcBuildingCost();
		String font_opening;
		String font_ending = "</font>";
		String[] names = {"Металл: ", "Кристалл: ", "Дейтерий: ", "Энергия: "};
		
		for(int i = 0; i < 4; i++)
		{
			if (current_resources[i] >= building_cost[i])
			{
				font_opening = "<font color='lime'>";
			}
			else
			{
				font_opening = "<font color='red'>";
			}
			if (building_cost[i] != 0)
			{
				names[i] += font_opening + NumberFormat.getNumberInstance(Locale.US).format((int)building_cost[i]) + font_ending;
			}
			else
			{
				names[i] = "";
			}
		}
		
		return "Необходимые ресурсы: " + names[0] + " " + names[1] + " " + names[2] + " " + names[3] + "<br>";
	}
	
	public void startBuilding(int robotics_factory, int nanite_factory)
	{
		build_end_time = new Date(new Date().getTime() + calcBuildingTime(robotics_factory, nanite_factory) * 1000); 
	}
	
	public void stopBuilding()
	{
		build_end_time = null;
	}
	
	public Date getBuildDate()
	{
		return build_end_time;
	}
	
	public static Building[] createListForPlanet(int temperature)
	{
		Building[] list = new Building[14];
		list[POWER_STATION] = new PowerStation("Солнечная электростанция");
		list[METAL_MINES] = new MetalMines("Рудник по добыче металла");
		list[CRYSTAL_MINES] = new CrystalMines("Рудник по добыче кристалла");
		list[DEITERIUM_MINES] = new DeiteriumMines("Синтезатор дейтерия", temperature);
		list[ROBOT_FACTORY] = new RobotFactory("Фабрика роботов");
		list[SPACE_YARD] = new SpaceYard("Верфь");
		list[LABORATORY] = new Laboratory("Исследовательская лаборатория");
		list[METAL_STORAGE] = new MetalStorage("Хранилище металла");
		list[CRYSTAL_STORAGE] = new CrystalStorage("Хранилище кристалла");
		list[DEITERIUM_STORAGE] = new DeiteriumStorage("Емкость для дейтерия");
		list[NUCLEAR_STATION] = new NuclearStation("Термоядерная электростанция");
		list[NANITE_FACTORY] = new NaniteFactory("Фабрика нанитов");
		list[TERRAFORMER] = new Terraformer("Терраформер");
		list[ROCKET_SHAFT] = new RocketShaft("Ракетная шахта");
		return list;
	}
	
	protected int level;
	protected int code;
	protected double[] base_cost; 
	protected Date build_end_time;
	protected double base;
	protected int[] required_buildings;
	protected int[] required_technologies;
	protected String name;
	public static final int RESOURCE_PRODUCTION_MULTIPLIER = 4;
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
