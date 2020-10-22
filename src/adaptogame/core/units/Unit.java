package adaptogame.core.units;

import static java.lang.Math.*;

import java.text.NumberFormat;
import java.util.Locale;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class Unit 
{
	public Unit(String name)
	{
		this.name = name;
		rapid_fire = new int[SHIPS_AMOUNT + DEFENCE_AMOUNT];
		required_buildings = new int[Building.BUILDINGS_AMOUNT];
		required_technologies = new int[Technology.RESEARCHES_AMOUNT];
		cost = new double[4];
	}
	
	public Unit(String name, int amount)
	{
		this.name = name;
		this.amount = amount;
		rapid_fire = new int[SHIPS_AMOUNT + DEFENCE_AMOUNT];
		required_buildings = new int[Building.BUILDINGS_AMOUNT];
		required_technologies = new int[Technology.RESEARCHES_AMOUNT];
		cost = new double[4];
	}
	
	public static Unit[] createList()
	{
		Unit[] list = new Unit[SHIPS_AMOUNT];
		list[0] = new SolarSatellite("Солнечный спутник");
		list[1] = new SpyProbe("Шпионский зонд");
		list[2] = new LightFighter("Легкий истребитель");
		list[3] = new SmallTransport("Малый транспорт");
		list[4] = new HeavyFighter("Тяжёлый истребитель");
		list[5] = new Colonizer("Колонизатор");
		list[6] = new Cruiser("Крейсер");
		list[7] = new LargeTransport("Большой транспорт");
		list[8] = new Processor("Переработчик");
		list[9] = new Battleship("Линкор");
		list[10] = new Battlecruiser("Линейный крейсер");
		list[11] = new Bomber("Бомбардировщик");
		list[12] = new Destroyer("Эсминец");
		list[13] = new Obliterator("Уничтожитель");
		list[14] = new DeathStar("Звезда Смерти");
		list[15] = new Supernova("Сверхновая звезда");
		return list;
	}
	
	public int[] getRequiredBuildings()
	{
		return required_buildings;
	}
	
	public int[] getRequiredTechnologies()
	{
		return required_technologies;
	}
	
	public long calcBuildingTime(int space_yard, int nanite_factory) // возвращает в секундах
	{
		return (long)max(( ((cost[0] + cost[1]) / 2500) * (1 / (space_yard + 1)) * pow(0.5, nanite_factory) * 15 * 60), 1);
		//return 2;
	}
	
	public String generateHeader()
	{
		return generateHeaderWithoutLevel() + " " + getCurrentAmountString();
	}
	
	public String generateHeaderWithoutLevel()
	{
		return name;
	}
	
	protected String getCurrentAmountString()
	{
		String s = "";
		if(amount != 0)
		{
			s = "(Количество: " + amount + ")";
		}
		return s;
	}
	
	public String generateDescription(double[] current_resources)
	{
		String font_opening;
		String font_ending = "</font>";
		String[] names = {"Металл: ", "Кристалл: ", "Дейтерий: ", "Энергия: "};
		
		for(int i = 0; i < 4; i++)
		{
			if (current_resources[i] >= cost[i])
			{
				font_opening = "<font color='lime'>";
			}
			else
			{
				font_opening = "<font color='red'>";
			}
			if (cost[i] != 0)
			{
				names[i] += font_opening + NumberFormat.getNumberInstance(Locale.US).format((int)cost[i]) + font_ending;
			}
			else
			{
				names[i] = "";
			}
		}
		
		return "Необходимые ресурсы: " + names[0] + " " + names[1] + " " + names[2] + " " + names[3] + "<br>";
	}
	
	protected int[] rapid_fire;
	protected int amount;
	protected int structure;
	protected int shields;
	protected int attack_power;
	protected int cargo_volume;
	protected EngineCategory engine_type = EngineCategory.NO_ENGINE;
	protected int speed;
	protected int fuel_consumption;
	protected String name;
	protected int[] required_buildings;
	protected int[] required_technologies;
	protected double[] cost;
	public static final int SOLAR_SATELLITE = 0;
	public static final int SPY_PROBE = 1;
	public static final int LIGHT_FIGHTER = 2;
	public static final int SMALL_TRANSPORT = 3;
	public static final int HEAVY_FIGHTER = 4;
	public static final int COLONIZER = 5;
	public static final int CRUISER = 6;
	public static final int LARGE_TRANSPORT = 7;
	public static final int PROCESSOR = 8;
	public static final int BATTLESHIP = 9;
	public static final int BATTLECRUISER = 10;
	public static final int BOMBER = 11;
	public static final int DESTROYER = 12;
	public static final int OBLITERATOR = 13;
	public static final int DEATH_STAR = 14;
	public static final int SUPERNOVA = 15;
	public static final int ROCKET_LAUNCHER = 16;
	public static final int LIGHT_LASER = 17;
	public static final int HEAVY_LASER = 18;
	public static final int GAUSS_CANNON = 19;
	public static final int ION_CANNON = 20;
	public static final int PLASMA_CANNON = 21;
	public static final int LIGHT_PLANETARY_SHIELD = 22;
	public static final int HEAVY_PLANETARY_SHIELD = 23;
	public static final int PLANETARY_DEFENCE = 24;
	public static final int SHIPS_AMOUNT = 16;
	public static final int DEFENCE_AMOUNT = 9;
}
