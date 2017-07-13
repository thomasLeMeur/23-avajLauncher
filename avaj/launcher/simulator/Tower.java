package avaj.launcher.simulator;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import avaj.launcher.simulator.vehicles.Flyable;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

class Tower
{
	private List<Flyable> observers = new ArrayList<Flyable>();
	private List<Flyable> landeds = new ArrayList<Flyable>();
	
	public void register(Flyable flyable)
	{
		this.observers.add(flyable);
	}
	
	public void unregister(Flyable flyable)
	{
		landeds.add(flyable);
	}
	
	protected void conditionsChanged() throws UnknownWeatherException, IOException
	{
		for (Flyable flyable : this.observers)
			flyable.updateConditions();
		
		for (Flyable flyable : this.landeds)
			this.observers.remove(flyable);
		this.landeds.clear();
	}
}
