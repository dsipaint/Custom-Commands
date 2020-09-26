import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandUseListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		if(e.getMessage().getEmbeds().size() > 0)
			return;
		
		if(args[0].charAt(0) == '^' && args[0].length() > 1 
				&& DataHandler.commands.containsKey(args[0].substring(1).toLowerCase()))
			e.getChannel().sendMessage(DataHandler.commands.get(args[0].substring(1))).queue();
	}
}
