package avaj.launcher.simulator.vehicles;

public class Coordinates
{
	private int longitude;
	private int latitude;
	private int height;
	
	Coordinates(int longitude_arg, int latitude_arg, int height_arg)
	{
		this.longitude = longitude_arg;
		this.latitude = latitude_arg;
		this.height = height_arg;
	}
	
	public int getLongitude()
	{
		return this.longitude;
	}
	
	public int getLatitude()
	{
		return this.latitude;
	}
	
	public int getHeight()
	{
		return this.height;
	}
}
