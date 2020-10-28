package adaptogame.core;

import java.util.Date;

import static java.lang.Math.*;

import adaptogame.core.units.Unit;

public class Fleet 
{
	public Fleet(Unit[] units, MissionCategory mission, int[] coords_from, int[] coords_to)
	{
		fleet_ships = new Unit[units.length];
		int max_fleet_speed = Integer.MAX_VALUE;
		for(int i = 0; i < fleet_ships.length; i++)
		{
			fleet_ships[i] = units[i].clone();
			int speed_i = fleet_ships[i].getSpeed();
			if(max_fleet_speed > speed_i)
			{
				max_fleet_speed = speed_i;
			}
		}
		this.mission = mission;
		base_coords = coords_from;
		target_coords = coords_to;
		int distance = Fleet.calcDistance(base_coords, target_coords);
		starting_up_date = new Date(new Date().getTime() + calcStartDuration(distance, max_fleet_speed));
		long clean_flight_duration = calcCleanDuration(distance, max_fleet_speed, 100);
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
	
	public static int calcDistance(int coords_from[], int[] coords_to)
	{
		if(coords_from[0] == coords_to[0])
		{
			if(coords_from[1] == coords_to[1])
			{
				return abs(coords_from[2] - coords_to[2]) * 5 + 1000;
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
		return (long)ceil((35000 / 10 * sqrt(distance * 10 / max_fleet_speed) + 10) / FLEET_SPEED_FACTOR * 0.05) * 1000;
	}
	
	public static long calcCleanDuration(int distance, int max_fleet_speed, int speed_percent)
	{
		double duration = ((35000 / (speed_percent / 10)) * sqrt(distance * 10 / max_fleet_speed) + 10) / FLEET_SPEED_FACTOR * 1000;
		if (duration - (long)duration >= 0.5)
		{
			return (long)duration + 1;
		}
		else
		{
			return (long)duration;
		}
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
	
	private int[] target_coords;
	private int[] base_coords;
	private MissionCategory mission;
	private Unit[] fleet_ships;
	private Date starting_up_date;
	private Date to_flight_date;
	private Date from_flight_date;
	public static final int FLEET_SPEED_FACTOR = 2;
}
