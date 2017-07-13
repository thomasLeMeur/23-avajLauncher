package avaj.launcher.simulator.vehicles;

import java.io.IOException;
import avaj.launcher.simulator.WeatherTower;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

public interface Flyable
{
	public void updateConditions() throws UnknownWeatherException, IOException;
	public void registerTower(WeatherTower weatherTower) throws IOException;
}
