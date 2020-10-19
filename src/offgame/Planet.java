package offgame;

import static java.lang.Math.*;

import java.util.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Planet 
{
	public Planet(String planet_name, int diameter, int temp_min, int temp_max, int gal, int system, int pos, int img_num, Player owner) throws IOException
	{
		this.planet_name = planet_name;
		this.diameter = diameter;
		this.temperature_min = temp_min;
		this.temperature_max = temp_max;
		this.owner = owner;
		fields = round((float)(pow((double)this.diameter/1000, 2)));
		fields_taken = 0;
		coords = new int[3];
		coords[0] = gal;
		coords[1] = system;
		coords[2] = pos;
		img = ImageIO.read(this.getClass().getResourceAsStream("/pl/" + img_num + ".jpg"));
		building_list = Building.createListForPlanet();
		metal_capacity = (int) building_list[Building.METAL_STORAGE].calcGathering();
		crystal_capacity = (int) building_list[Building.CRYSTAL_STORAGE].calcGathering();
		deiterium_capacity = (int) building_list[Building.DEITERIUM_STORAGE].calcGathering();
		metal_current = 1000000;
		crystal_current = 1000000;
		deiterium_current = 1000000;
		electricity_current = 0;
		building_queue = new ArrayList<>();
	}
	
	public Image getImg()
	{
		return img;
	}
	
	public int getDiameter()
	{
		return diameter;
	}
	
	public int[] getCoords()
	{
		return coords.clone();
	}
	
	public int getMinTemperature()
	{
		return temperature_min;
	}
	
	public int getMaxTemperature()
	{
		return temperature_max;
	}
	
	public int getFields()
	{
		return fields;
	}
	
	public int getTakenFields()
	{
		return fields_taken;
	}
	
	public Building[] getBuildings()
	{
		return building_list; //сделать clone()
	}
	
	public int getQueueElem(int i)
	{
		return building_queue.get(i);
	}
	
	public int getQueueSize()
	{
		return building_queue.size();
	}
	
	public static Planet generateStartPlanet(Player owner) throws IOException
	{
		Random r = new Random();
		return new Planet("Planet", 12247, -6, 0, 1, 1, 1, r.nextInt(31)+1, owner);
	}
	
	public String getName()
	{
		return planet_name;
	}
	
	public double getCurrentMetal()
	{
		return metal_current;
	}
	
	public double getCurrentCrystal()
	{
		return crystal_current;
	}
	
	public double getCurrentDeiterium()
	{
		return deiterium_current;
	}
	
	public double getCurrentEnergy()
	{
		return electricity_current;
	}
	
	public double getMetalCapacity()
	{
		return metal_capacity;
	}
	
	public double getCrystalCapacity()
	{
		return crystal_capacity;
	}
	
	public double getDeiteriumCapacity()
	{
		return deiterium_capacity;
	}
	
	public double getCurrentElectricity() 
	{
		return electricity_current;
	}
	
	public void updateResources()
	{
		if(metal_current < metal_capacity)
		{
			metal_current = metal_current + (METAL_DEFAULT_PRODUCTION + metal_production) / 3600;
		}
		if(crystal_current < crystal_capacity)
		{
			crystal_current = crystal_current + (CRYSTAL_DEFAULT_PRODUCTION + crystal_production) / 3600;
		}
		if(deiterium_current < deiterium_capacity)
		{
			deiterium_current = deiterium_current + (DEITERIUM_DEFAULT_PRODUCTION + deiterium_production) / 3600;
		}
	}
	
	public void updateElectricity()
	{
		electricity_current = getBuildings()[Building.POWER_STATION].calcGathering() + getBuildings()[Building.NUCLEAR_STATION].calcGathering() - getBuildings()[Building.METAL_MINES].calcConsuming() - getBuildings()[Building.CRYSTAL_MINES].calcConsuming() - getBuildings()[Building.DEITERIUM_MINES].calcConsuming(); // еще спутники
	}
	
	public boolean isBuildable(int code)
	{
		double[] current = {metal_current, crystal_current, deiterium_current};
		double[] required = building_list[code].calcBuildingCost();
		boolean f = true;
		for(int i = 0; i < 3; i++)
		{
			f = f && (current[i] > required[i]);
		}
		return f && (fields_taken < fields);
	}
	
	public boolean isResearchable(int code)
	{
		double[] current = {metal_current, crystal_current, deiterium_current, electricity_current};
		double[] required = owner.getTechs()[code].calcBuildingCost();
		boolean f = true;
		for(int i = 0; i < 3; i++)
		{
			f = f && (current[i] >= required[i]);
		}
		f = f && ((required[3] == 0) || (current[3] >= required[3]));
		return f;
	}
	
	public boolean requirementsMet(EntityCategory type, int code)
	{
		boolean f = true;
		int[] buildings_required;
		int[] technologires_required;
		switch(type)
		{
			case BUILDING:
				buildings_required = building_list[code].getRequiredBuildings();
				technologires_required = building_list[code].getRequiredTechnologies();
				for(int i = 0; i < buildings_required.length; i++)
				{
					f = f && (building_list[i].getLevel() >= buildings_required[i]);
				}
				for(int i = 0; i < technologires_required.length; i++)
				{
					f = f && (owner.getTechs()[i].getLevel() >= technologires_required[i]);
				}
				break;
			case RESEARCH:
				buildings_required = owner.getTechs()[code].getRequiredBuildings();
				technologires_required = owner.getTechs()[code].getRequiredTechnologies();
				for(int i = 0; i < buildings_required.length; i++)
				{
					f = f && (building_list[i].getLevel() >= buildings_required[i]);
				}
				for(int i = 0; i < technologires_required.length; i++)
				{
					f = f && (owner.getTechs()[i].getLevel() >= technologires_required[i]);
				}
				break;
			case FLEET:
				break;
			case DEFENSE:
				break;
		}
		return f;
	}
	
	public void updateBuildingsProduction()
	{
		try
		{
			Date now = new Date();
			int code = building_queue.get(0);
			if (!building_list[code].getBuildDate().after(now))
			{
				building_list[code].stopBuilding();
				building_list[code].updateLevel();
				fields_taken++;
				switch(code)
				{
					case Building.POWER_STATION:
					case Building.NUCLEAR_STATION:
					case Building.METAL_MINES:
					case Building.CRYSTAL_MINES:
					case Building.DEITERIUM_MINES:
						updateElectricity();
						break;
				}
				building_queue.remove(0);
				while(building_queue.size() > 0)
				{
					if(isBuildable(building_queue.get(0)))
					{
						startBuilding(building_queue.get(0));
						break;
					}
					else
					{
						building_queue.remove(0);
					}
				}
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void updateResearchProcess()
	{
		Date now = new Date();
		int code = owner.getActiveResearch();
		try
		{
			if (!owner.getTechs()[code].getBuildDate().after(now))
			{
				owner.getTechs()[code].stopBuilding();
				owner.getTechs()[code].updateLevel();
				owner.setActiveResearch(Player.NO_ACTIVE_RESEARCH);
			}
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
	}
	
	public void addBuildingQueue(int code)
	{
		int size = building_queue.size();
		if(size < MAX_BUILD_QUEUE)
		{
			building_queue.add(code);
			if (size == 0)
			{
				startBuilding(code);
			}
		}
	}
	
	public void startBuilding(int code)
	{
		double[] cost = getBuildings()[code].calcBuildingCost();
		metal_current -= cost[0];
		crystal_current -= cost[1];
		deiterium_current -= cost[2];
		getBuildings()[code].startBuilding(getBuildings()[Building.ROBOT_FACTORY].getLevel(), getBuildings()[Building.NANITE_FACTORY].getLevel());	
	}
	
	public void startResearching(int code)
	{
		double[] cost = owner.getTechs()[code].calcBuildingCost();
		metal_current -= cost[0];
		crystal_current -= cost[1];
		deiterium_current -= cost[2];
		owner.setActiveResearch(code);
		owner.getTechs()[code].startBuilding(getBuildings()[Building.LABORATORY].getLevel());	
	}
	
	private int diameter;
	private int fields;
	private int fields_taken;
	private int temperature_min;
	private int temperature_max;
	private int metal_capacity;
	private int crystal_capacity;
	private int deiterium_capacity;
	private double metal_current;
	private double crystal_current;
	private double deiterium_current;
	private double metal_production;
	private double crystal_production;
	private double deiterium_production;
	private double electricity_current;
	private String planet_name;
	private BufferedImage img;
	private Building[] building_list;
	private ArrayList<Integer> building_queue;
	private Player owner;
	public int[] coords;
	public static final double METAL_DEFAULT_PRODUCTION = 1200;
	public static final double CRYSTAL_DEFAULT_PRODUCTION = 600;
	public static final double DEITERIUM_DEFAULT_PRODUCTION = 300;
	public static final int MAX_BUILD_QUEUE = 5;
	public static final int BUILDINGS = 0;
	public static final int TECHNOLOGIES = 0;
}
