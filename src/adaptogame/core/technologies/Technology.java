package adaptogame.core.technologies;

import static java.lang.Math.*;

import java.util.Date;

import adaptogame.core.buildings.Building;

public abstract class Technology extends Building
{
	public Technology(String name)
	{
		super(name);
	}
	
	public Technology(int level, String name)
	{
		super(level, name);
	}
	
	public long calcBuildingTime(int laboratory) // возвращает в секундах
	{
		double[] d = calcBuildingCost();
		return (long)max(((d[0] + d[1]) / (1000.0 * (laboratory + 1)) * 15 * 60), 1);
	}
	
	public void startBuilding(int laboratory)
	{
		build_end_time = new Date(new Date().getTime() + calcBuildingTime(laboratory) * 1000); 
	}
	
	public static Technology[] createList()
	{
		Technology[] list = new Technology[RESEARCHES_AMOUNT];
		list[ESPIONAGE] = new Espionage("Шпионаж");
		list[ENERGY_TECHNOLOGY] = new EnergyTechnology("Энергетическая технология");
		list[REACTIVE_ENGINE] = new ReactiveEngine("Реактивный двигатель");
		list[IMPULSE_ENGINE] = new ImpulseEngine("Импульсный двигатель");
		list[COMPUTER_TECHNOLOGY] = new ComputerTechnology("Компьютерная технология");
		list[WEAPON_TECHNOLOGY] = new WeaponTechnology("Оружейная технология");
		list[SHIELD_TECHNOLOGY] = new ShieldTechnology("Щитовая технология");
		list[METALLURGY] = new Metallurgy("Металлургия");
		list[COLONIZATION_TECHNOLOGY] = new ColonizationTechnology("Колонизационная технология");
		list[EXPEDITION_TECHNOLOGY] = new ExpeditionTechnology("Экспедиционная технология");
		list[HYPERSPACE_TECHNOLOGY] = new HyperspaceTechnology("Гиперпространственная технология");
		list[HYPERSPACE_ENGINE] = new HyperspaceEngine("Гиперпространственный двигатель");
		list[LASER_TECHNOLOGY] = new LaserTechnology("Лазерная технология");
		list[ION_TECHNOLOGY] = new IonTechnology("Ионная технология");
		list[PLASMA_TECHNOLOGY] = new PlasmaTechnology("Плазменная технология");
		list[INTEGERGALACTICAL_RESEARCH_NETWORK] = new IntergalacticalResearchNetwork("Межгалактическая исследовательская сеть");
		list[GRAVITY_TECHNOLOGY] = new GravitationalTechnology("Гравитационная технология");
		return list;
	}
	
	public static final int ESPIONAGE = 0;
	public static final int ENERGY_TECHNOLOGY = 1;
	public static final int REACTIVE_ENGINE = 2;
	public static final int IMPULSE_ENGINE = 3;
	public static final int COMPUTER_TECHNOLOGY = 4;
	public static final int WEAPON_TECHNOLOGY = 5;
	public static final int SHIELD_TECHNOLOGY = 6;
	public static final int METALLURGY = 7;
	public static final int COLONIZATION_TECHNOLOGY = 8;
	public static final int EXPEDITION_TECHNOLOGY = 9;
	public static final int HYPERSPACE_TECHNOLOGY = 10;
	public static final int HYPERSPACE_ENGINE = 11;
	public static final int LASER_TECHNOLOGY = 12;
	public static final int ION_TECHNOLOGY = 13;
	public static final int PLASMA_TECHNOLOGY = 14;
	public static final int INTEGERGALACTICAL_RESEARCH_NETWORK = 15;
	public static final int GRAVITY_TECHNOLOGY = 16;
	public static final int RESEARCHES_AMOUNT = 17;
}
