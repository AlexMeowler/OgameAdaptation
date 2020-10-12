package offgame;

import static java.lang.Math.*;

import java.util.Random;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Planet 
{
	public Planet(int diameter, int temp_min, int temp_max, int gal, int system, int pos, int img_num) throws IOException
	{
		this.diameter = diameter;
		this.fields = round((float)(pow(this.diameter/1000, 2)));
		this.temperature_min = temp_min;
		this.temperature_max = temp_max;
		coords = new int[3];
		coords[0] = gal;
		coords[1] = system;
		coords[2] = pos;
		img = ImageIO.read(this.getClass().getResourceAsStream("/pl/" + img_num + ".jpg"));
	}
	
	public Image getImg()
	{
		return img;
	}
	
	public static Planet generateStartPlanet() throws IOException
	{
		Random r = new Random();
		return new Planet(12247, -6, 0, 1, 1, 1, r.nextInt(31)+1);
	}
	private int diameter;
	private int fields;
	private int temperature_min;
	private int temperature_max;
	private BufferedImage img;
	public int[] coords;
}
