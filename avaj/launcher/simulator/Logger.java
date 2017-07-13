package avaj.launcher.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Logger
{
	private static Logger logger = new Logger();
	private static BufferedWriter writer;
	
	private Logger()
	{
		logger = this;
	}
	
	public static Logger getLogger()
	{
		return logger;
	}

	public void open() throws IOException
	{
		writer = new BufferedWriter(new FileWriter("simulation.txt"));
	}
	
	public void close() throws IOException
	{
		writer.close();
	}
	
	public void writeLog(String message) throws IOException
	{
		writer.write(message);
		writer.newLine();
	}
	
}
