import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main
{
	static JDA jda;
	static final String PREFIX = "^";
	
	public static void main(String[] args)
	{
		try
		{
			jda = JDABuilder.createDefault("")
					.build();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			jda.awaitReady();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			DataHandler.readCommands();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		jda.addEventListener(new CommandAddListener());
		jda.addEventListener(new CommandUseListener());
		jda.addEventListener(new StopListener());
	}
}
