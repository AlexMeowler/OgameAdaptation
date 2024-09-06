package org.retal.offgame.core;

import java.util.Date;

import static java.lang.Math.*;

import org.retal.offgame.core.units.Unit;

public class Fleet 
{
	public Fleet(Unit[] units, MissionCategory mission, int[] coords_from, int[] coords_to, int speed_percent, int capacity_left, int[] resources_loaded)
	{
		fleet_ships = new Unit[units.length];
		max_fleet_speed = Integer.MAX_VALUE;
		for(int i = 0; i < fleet_ships.length; i++)
		{
			fleet_ships[i] = units[i].clone();
			int speed_i = fleet_ships[i].getSpeed();
			if((max_fleet_speed > speed_i) && (fleet_ships[i].getAmount() != 0))
			{
				max_fleet_speed = speed_i;
			}
		}
		this.mission = mission;
		this.capacity_left = capacity_left;
		this.resources_loaded = resources_loaded;
		base_coords = coords_from;
		target_coords = coords_to;
		on_way_back = false;
		int distance = Fleet.calcDistance(base_coords, target_coords);
		starting_up_date = new Date(new Date().getTime() + calcStartDuration(distance, max_fleet_speed));
		long clean_flight_duration = calcCleanDuration(distance, max_fleet_speed, speed_percent);
		to_flight_date = new Date(starting_up_date.getTime() + clean_flight_duration);
		from_flight_date = new Date(starting_up_date.getTime() + 2 * clean_flight_duration);
	}
	
	public MissionCategory getMission()
	{
		return mission;
	}
	
	public int getShipsTotal()
	{
		int sum = 0;
		for(int i = 0; i < fleet_ships.length; i++)
		{
			sum += fleet_ships[i].getAmount();
		}
		return sum;
	}
	
	public Unit[] getShips()
	{
		Unit[] ships = new Unit[fleet_ships.length];
		for(int i = 0; i < ships.length; i++)
		{
			ships[i] = fleet_ships[i].clone();
		}
		return ships;
	}
	
	public int[] getResourcesLoaded()
	{
		return resources_loaded.clone();
	}
	
	public static int calcDistance(int coords_from[], int[] coords_to)
	{
		if(coords_from[0] == coords_to[0])
		{
			if(coords_from[1] == coords_to[1])
			{
				if(coords_from[2] == coords_to[2])
				{
					return 5;
				}
				else 
				{
					return abs(coords_from[2] - coords_to[2]) * 5 + 1000;
					
				}
			}
			else
			{
				return abs(coords_from[1] - coords_to[1]) * 5 * 19 + 2700;
			}
		}
		else
		{
			return abs(coords_from[0] - coords_to[0]) * 10000;
		}
	}
	
	public static long calcStartDuration(int distance, int max_fleet_speed)
	{
		return (long)ceil((35000.0 / 10 * sqrt((double)distance * 10 / max_fleet_speed) + 10) / FLEET_SPEED_FACTOR * 0.05) * 1000;
	}
	
	public static long calcCleanDuration(int distance, int max_fleet_speed, int speed_percent)
	{
		double duration = ((35000.0 / (speed_percent / 10)) * sqrt((double)distance * 10 / max_fleet_speed) + 10) / FLEET_SPEED_FACTOR;
		if (duration - (long)duration >= 0.5)
		{
			return ((long)duration + 1) * 1000;
		}
		else
		{
			return (long)duration * 1000;
		}
	}
	
	public static int calcTotalFuelConsumption(Unit[] ships, int distance, int speed_percent)
	{
		int cons = 0;
		for(int i = 1; i < ships.length; i++)
		{
			try
			{
				cons += ships[i].getAmount() * ceil(ships[i].calcFuelConsumption(calcCleanDuration(distance, Unit.findMaxFleetSpeed(ships), speed_percent), distance, speed_percent));
			}
			catch(NullPointerException e)
			{
				return 0;
			}
		}
		return max(cons, 1);
	}
	
	public static int calcTotalCapacity(Unit[] ships)
	{
		int capacity = 0;
		for(int i = 0; i < ships.length; i++)
		{
			try
			{
				capacity += ships[i].getAmount() * ships[i].getCargoVolume();
			}
			catch(NullPointerException e)
			{
				return 0;
			}
		}
		return capacity;
	}
	
	public int[] getTarget()
	{
		return target_coords;
	}
	
	public int[] getBase()
	{
		return base_coords;
	}
	// clone?
	public Date getStartingUpDate()
	{
		return starting_up_date;
	}
	
	public Date getToTargetDate()
	{
		return to_flight_date;
	}
	
	public Date getFromTargetDate()
	{
		return from_flight_date;
	}
	
	public boolean isGoingHome()
	{
		return on_way_back;
	}
	
	public void goHome()
	{
		on_way_back = true;
	}
	
	private int[] target_coords;
	private int[] base_coords;
	private int max_fleet_speed;
	private MissionCategory mission;
	private Unit[] fleet_ships;
	private Date starting_up_date;
	private Date to_flight_date;
	private Date from_flight_date;
	private int capacity_left;
	private int[] resources_loaded;
	private boolean on_way_back;
	public static final int FLEET_SPEED_FACTOR = 2;
}
