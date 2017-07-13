package avaj.launcher.simulator.vehicles;

class Aircraft
{
	protected long id;
	protected String name; 
	protected Coordinates coordinates;
	
	private static long idCounter = 0;
	
	protected Aircraft(String name_arg, Coordinates coordinates_arg)
	{
		this.id = this.nextId();
		this.name = name_arg;
		this.coordinates = coordinates_arg;
	}
	
	private long nextId()
	{
		return ++idCounter;
	}
	
}
