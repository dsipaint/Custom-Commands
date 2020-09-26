import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.yaml.snakeyaml.Yaml;

public class DataHandler
{
	static final String FILE_LOC = "commands.yml";
	static HashMap<String, String> commands;
	
	public static void readCommands() throws FileNotFoundException
	{
		HashMap<String, Object> values = new HashMap<String, Object>();
		
		Yaml yaml = new Yaml();
		values = yaml.load(new BufferedReader(new FileReader(new File(FILE_LOC))));
		
		//ugly but works
		commands = new HashMap<String, String>();
		values.forEach((name, object) ->
		{
			commands.put(name, object.toString());
		});
	}
	
	public static void writeCommands() throws IOException
	{
		Yaml yaml = new Yaml();
		yaml.dump(commands, new BufferedWriter(new FileWriter(new File(FILE_LOC))));
	}
}
