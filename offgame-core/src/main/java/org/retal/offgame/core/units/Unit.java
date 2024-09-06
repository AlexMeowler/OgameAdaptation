package org.retal.offgame.core.units;

import static java.lang.Math.*;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.retal.offgame.core.EngineCategory;
import org.retal.offgame.core.Fleet;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.defenses.*;
import org.retal.offgame.core.units.fleet.*;

public abstract class Unit implements Cloneable
{
	public Unit(String name)
	{
		this.name = name;
		rapid_fire = new int[SHIPS_AMOUNT + DEFENSE_AMOUNT];
		required_buildings = new int[Building.BUILDINGS_AMOUNT];
		required_technologies = new int[Technology.RESEARCHES_AMOUNT];
		cost = new double[4];
		building_amount = 0;
		build_end_time = null;
		cargo_volume = 0;
		engine_type = EngineCategory.NO_ENGINE;
		speed = 0;
		fuel_consumption = 0;
	}
	
	public Unit(String name, int amount)
	{
		this.name = name;
		this.amount = amount;
		rapid_fire = new int[SHIPS_AMOUNT + DEFENSE_AMOUNT];
		required_buildings = new int[Building.BUILDINGS_AMOUNT];
		required_technologies = new int[Technology.RESEARCHES_AMOUNT];
		cost = new double[4];
		building_amount = 0;
		build_end_time = null;
		cargo_volume = 0;
		engine_type = EngineCategory.NO_ENGINE;
		speed = 0;
		fuel_consumption = 0;
	}
	
	public static Unit[] createFullUnitList(int planet_max_temperature)
	{
		Unit[] list = new Unit[SHIPS_AMOUNT + DEFENSE_AMOUNT];
		list[0] = new SolarSatellite("Солнечный спутник", planet_max_temperature);
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
		list[16] = new RocketLauncher("Ракетная установка");
		list[17] = new LightLaser("Легкий лазер");
		list[18] = new HeavyLaser("Тяжелый лазер");
		list[19] = new GaussCannon("Пушка Гаусса");
		list[20] = new IonCannon("Ионная пушка");
		list[21] = new PlasmaCannon("Плазменная пушка");
		list[22] = new LightPlanetaryShield("Малый щитовой купол");
		list[23] = new HeavyPlanetaryShield("Большой щитовой купол");
		list[24] = new PlanetaryDefense("Планетарная защита");
		return list;
	}
	
	public static Unit[] createFleetList(int[] amounts)
	{
		Unit[] list = new Unit[SHIPS_AMOUNT];
		list[0] = new SolarSatellite("Солнечный спутник", amounts[0], 0);
		list[1] = new SpyProbe("Шпионский зонд", amounts[1]);
		list[2] = new LightFighter("Легкий истребитель", amounts[2]);
		list[3] = new SmallTransport("Малый транспорт", amounts[3]);
		list[4] = new HeavyFighter("Тяжёлый истребитель", amounts[4]);
		list[5] = new Colonizer("Колонизатор", amounts[5]);
		list[6] = new Cruiser("Крейсер", amounts[6]);
		list[7] = new LargeTransport("Большой транспорт", amounts[7]);
		list[8] = new Processor("Переработчик", amounts[8]);
		list[9] = new Battleship("Линкор", amounts[9]);
		list[10] = new Battlecruiser("Линейный крейсер", amounts[10]);
		list[11] = new Bomber("Бомбардировщик", amounts[11]);
		list[12] = new Destroyer("Эсминец", amounts[12]);
		list[13] = new Obliterator("Уничтожитель", amounts[13]);
		list[14] = new DeathStar("Звезда Смерти", amounts[14]);
		list[15] = new Supernova("Сверхновая звезда", amounts[15]);
		return list;
	}
	
	public static int findMaxFleetSpeed(Unit[] list)
	{
		int max_fleet_speed = Integer.MAX_VALUE;
		for(int i = 1; i < list.length; i++)
		{
			try
			{
				if((max_fleet_speed > list[i].getSpeed()) && (list[i].getAmount() > 0))
				{
					max_fleet_speed = list[i].getSpeed();
				}
			}
			catch(NullPointerException e)
			{
				return 0;
			}
		}
		return max_fleet_speed;
	}
	
	public double calcFuelConsumption(long clean_duration, int distance, int speed_percentage)
	{
		clean_duration = (clean_duration * Fleet.FLEET_SPEED_FACTOR) / 1000 - 10 ;
		double spd = 35000.0 / clean_duration * sqrt((double)distance * 10 / ((double)speed * speed_percentage / 100));
		return (double)fuel_consumption * distance / 35000.0 * pow(spd / 10 + 1, 2);
	}
	
	public int[] getRequiredBuildings()
	{
		return required_buildings;
	}
	
	public int[] getRequiredTechnologies()
	{
		return required_technologies;
	}
	
	public double[] getCost()
	{
		return cost;
	}
	
	public void startBuilding(int space_yard, int nanite_factory)
	{
		build_end_time = new Date(new Date().getTime() + calcBuildingTime(space_yard, nanite_factory) * 1000); 
	}
	
	public void stopBuilding()
	{
		build_end_time = null;
	}
	
	public Date getBuildDate()
	{
		return build_end_time;
	}
	
	public void updateAmount()
	{
		amount++;
	}
	
	public void changeAmount(int delta)
	{
		amount += delta;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCargoVolume()
	{
		return cargo_volume;
	}
	
	public long calcBuildingTime(int space_yard, int nanite_factory) // возвращает в секундах
	{
		return (long)max(( ((cost[0] + cost[1]) / 2500) * (1.0 / (space_yard + 1)) * pow(0.5, nanite_factory) * 15 * 60), 1);
		//return 10;
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
	
	public Unit clone()
	{
		
		try 
		{
			Unit u;
			u = getClass().getConstructor(String.class).newInstance(new Object[] {name});
			u.rapid_fire = rapid_fire;
			u.amount = amount;
			u.structure = structure;
			u.shields = shields;
			u.attack_power = attack_power;
			u.cargo_volume = cargo_volume;
			u.engine_type = engine_type;
			u.speed = speed;
			u.fuel_consumption = fuel_consumption;
			u.name = name;
			u.required_buildings = required_buildings;
			u.required_technologies = required_technologies;
			u.cost = cost;
			u.building_amount = building_amount;
			u.build_end_time = build_end_time;
			return u;
		} 
		catch (Exception e)
		{
			return null;
		}
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	protected int[] rapid_fire;
	protected int amount;
	protected int structure;
	protected int shields;
	protected int attack_power;
	protected int cargo_volume = 0;
	protected EngineCategory engine_type = EngineCategory.NO_ENGINE;
	protected int speed = 0;
	protected int fuel_consumption = 0;
	protected String name;
	protected int[] required_buildings;
	protected int[] required_technologies;
	protected double[] cost;
	protected int building_amount;
	protected Date build_end_time;
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
	public static final int PLANETARY_DEFENSE = 24;
	public static final int SHIPS_AMOUNT = 16;
	public static final int DEFENSE_AMOUNT = 9;
}
