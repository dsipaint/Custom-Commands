import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandAddListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		String[] args = msg.split(" ");
		
		if(isStaff(e.getMember()))
		{
			//^addcm {name} message goes here
			if(args[0].equalsIgnoreCase(Main.PREFIX + "addcm"))
			{
				//must include name and message
				if(args.length < 3)
					return;
				
				String command_message = "";
				for(int i = 2; i < args.length; i++)
					command_message += args[i] + " ";
				
				command_message = command_message.substring(0, command_message.length() - 1);
				
				DataHandler.commands.put(args[1].toLowerCase(), command_message);
				e.getChannel().sendMessage("Command " + Main.PREFIX + args[1].toLowerCase() + " added with message "
						+ "\"" + command_message + "\"").queue();
				
				return;
			}
			
			if(args[0].equalsIgnoreCase(Main.PREFIX + "removecm"))
			{
				if(args.length == 1)
					return;
				
				DataHandler.commands.remove(args[1].toLowerCase());
				e.getChannel().sendMessage("Command " + Main.PREFIX + args[1].toLowerCase() + " removed.").queue();
				return;
			}
		}
		
		if(args[0].equalsIgnoreCase(Main.PREFIX + "listcm"))
		{
			EmbedBuilder eb = new EmbedBuilder()
					.setTitle("Custom Commands")
					.setAuthor("al~", null, e.getGuild().retrieveMemberById("475859944101380106").complete().getUser().getAvatarUrl())
					.setColor(65280);
			
			for(Object name : DataHandler.commands.keySet().toArray())
				eb = eb.appendDescription("**" + Main.PREFIX + (String) name + ": **" + DataHandler.commands.get(name) + "\n");
			
			eb = eb.appendDescription("\n*Staff can use " + Main.PREFIX + "addcm {commandname} {message} to add a new custom message, and "
					+ Main.PREFIX + "removecm {commandname} to remove it*");
			
			e.getChannel().sendMessage(eb.build()).queue();
		}
	}
	
	//return true if a member has discord mod, admin or is owner
	public static boolean isStaff(Member m)
	{
		//if owner
		if(m.isOwner())
			return true;
		
		//if admin
		if(m.hasPermission(Permission.ADMINISTRATOR))
			return true;
		
		//if discord mod TODO: Make discord mod module for all servers
		switch(m.getGuild().getId())
		{
			case "565623426501443584" : //wilbur's discord
				for(Role r : m.getRoles())
				{
					if(r.getId().equals("565626094917648386")) //wilbur discord mod
						return true;
				}
				break;
				
			case "640254333807755304" : //charlie's server
				for(Role r : m.getRoles())
				{
					if(r.getId().equals("640255355401535499")) //charlie discord mod
						return true;
				}
				break;
		}
		
		return false;
	}
	
	//fuck lambda expressions and their bullshit
	public static void appendEmbedDescription(EmbedBuilder eb, String msg)
	{
		eb = eb.appendDescription(msg);
	}
}
