package offgame;

import static java.lang.Math.*;

import java.util.Date;

public abstract class Technology extends Building
{
	public Technology()
	{
		super();
	}
	
	public Technology(int level)
	{
		super(level);
	}
	
	public long calcBuildingTime(int laboratory) // ���������� � ��������
	{
		double[] d = calcBuildingCost();
		return (long)max(((d[0] + d[1]) / (1000 * (laboratory + 1)) * 15 * 60), 1);
	}
	
	public void startBuilding(int laboratory)
	{
		build_end_time = new Date(new Date().getTime() + calcBuildingTime(laboratory) * 1000); 
	}
	
	public static Technology[] createList()
	{
		Technology[] list = new Technology[17];
		list[ESPIONAGE] = new Espionage();
		list[ENERGY_TECHNOLOGY] = new EnergyTechnology();
		list[REACTIVE_ENGINE] = new ReactiveEngine();
		list[IMPULSE_ENGINE] = new ImpulseEngine();
		list[COMPUTER_TECHNOLOGY] = new ComputerTechnology();
		list[WEAPON_TECHNOLOGY] = new WeaponTechnology();
		list[SHIELD_TECHNOLOGY] = new ShieldTechnology();
		list[METALLURGY] = new Metallurgy();
		list[COLONIZATION_TECHNOLOGY] = new ColonizationTechnology();
		list[EXPEDITION_TECHNOLOGY] = new ExpeditionTechnology();
		list[HYPERSPACE_TECHNOLOGY] = new HyperspaceTechnology();
		list[HYPERSPACE_ENGINE] = new HyperspaceEngine();
		list[LASER_TECHNOLOGY] = new LaserTechnology();
		list[ION_TECHNOLOGY] = new IonTechnology();
		list[PLASMA_TECHNOLOGY] = new PlasmaTechnology();
		list[INTEGERGALACTICAL_RESEARCH_NETWORK] = new IntergalacticalResearchNetwork();
		list[GRAVITY_TECHNOLOGY] = new GravityTechnology();
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
}
