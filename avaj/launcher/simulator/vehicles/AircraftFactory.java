package avaj.launcher.simulator.vehicles;

import avaj.launcher.simulator.exceptions.*;

public class AircraftFactory
{
	public static Flyable newAircaft(String type, String name, int longitude, int latitude, int height) throws EmptyFlyableNameException, LatitudeLongitudeValueException, HeightValueException, UnknownFlyableTypeException
	{
		Flyable newVehicle = null;
		if (name == "")
			throw new EmptyFlyableNameException();
		else if (longitude < 0 || latitude < 0)
			throw new LatitudeLongitudeValueException();
		else if (height < 0 || height > 100)
			throw new HeightValueException();
		else
		{
			Coordinates coordinates = new Coordinates(longitude, latitude, height);
			if (type.equals("Helicopter"))
				newVehicle = new Helicopter(name, coordinates);
			else if (type.equals("JetPlane"))
				newVehicle = new JetPlane(name, coordinates);
			else if (type.equals("Baloon"))
				newVehicle = new Baloon(name, coordinates);
			else
			{
				System.out.println(type + " Baloon " + (type.equals("Baloon")));
				throw new UnknownFlyableTypeException(type);
			}
		}
		return newVehicle;
	}
}
