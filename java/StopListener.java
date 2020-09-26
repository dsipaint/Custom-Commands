import java.io.IOException;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StopListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		if(e.getMember() != null && CommandAddListener.isStaff(e.getMember()))
		{
			String msg = e.getMessage().getContentRaw();
			String[] args = msg.split(" ");
			
			if(args.length > 1 && args[0].equalsIgnoreCase(Main.PREFIX + "disable") && args[1].equalsIgnoreCase("custommessages"))
			{
				try
				{
					DataHandler.writeCommands();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				e.getChannel().sendMessage("*disabling al's custommessages code...*").complete();
				e.getJDA().shutdown();
				System.exit(0);
			}
			
			if(args[0].equalsIgnoreCase(Main.PREFIX + "shutdown"))
			{
				try
				{
					DataHandler.writeCommands();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
				e.getJDA().shutdownNow();
				System.exit(0);
			}
		}
	}
}
