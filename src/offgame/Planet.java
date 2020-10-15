package offgame;

import static java.lang.Math.*;

import java.util.Random;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Planet 
{
	public Planet(String planet_name, int diameter, int temp_min, int temp_max, int gal, int system, int pos, int img_num) throws IOException
	{
		this.planet_name = planet_name;
		this.diameter = diameter;
		this.fields = round((float)(pow((double)this.diameter/1000, 2)));
		this.temperature_min = temp_min;
		this.temperature_max = temp_max;
		coords = new int[3];
		coords[0] = gal;
		coords[1] = system;
		coords[2] = pos;
		img = ImageIO.read(this.getClass().getResourceAsStream("/pl/" + img_num + ".jpg"));
		building_list = Building.createList();
		metal_capacity = (int) building_list[Building.METAL_STORAGE].calcGathering();
		crystal_capacity = (int) building_list[Building.CRYSTAL_STORAGE].calcGathering();
		deiterium_capacity = (int) building_list[Building.DEITERIUM_STORAGE].calcGathering();
		metal_current = 1000;
		crystal_current = 1000;
		deiterium_current = 1000;
		electricity_current = 0;
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
	
	public Building[] getBuildings()
	{
		return building_list; //сделать clone()
	}
	
	public static Planet generateStartPlanet() throws IOException
	{
		Random r = new Random();
		return new Planet("Planet", 12247, -6, 0, 1, 1, 1, r.nextInt(31)+1);
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
	
	public double getCurrentElectricity() 
	{
		return electricity_current;
	}
	
	public void updateResources()
	{
		metal_current = min(metal_current + (METAL_DEFAULT_PRODUCTION + metal_production) / 3600, metal_capacity);
		crystal_current = min(crystal_current + (CRYSTAL_DEFAULT_PRODUCTION + crystal_production) / 3600, crystal_capacity);
		deiterium_current = min(deiterium_current + (DEITERIUM_DEFAULT_PRODUCTION + deiterium_production) / 3600, deiterium_capacity);
	}
	
	public void updateElectricity()
	{
		electricity_current = getBuildings()[Building.POWER_STATION].calcGathering(); // спутники
	}
	
	private int diameter;
	private int fields;
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
	public int[] coords;
	public static final double METAL_DEFAULT_PRODUCTION = 1200;
	public static final double CRYSTAL_DEFAULT_PRODUCTION = 600;
	public static final double DEITERIUM_DEFAULT_PRODUCTION = 300;
}
