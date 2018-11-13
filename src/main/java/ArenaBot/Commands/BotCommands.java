package ArenaBot.Commands;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotCommands extends ListenerAdapter
{
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e)
	{
		
		Guild guild = e.getGuild();
		User user = e.getUser();

		if(guild.getId().equals("336291415908679690"))
		{

			guild.getTextChannelById("369682886040879104").sendMessage(user.getAsMention() + " has dipped into the **Arena Brawl Community Discord!** :tada::hugging: !").queue();
			
		}		
	}
	
	@Override
	public void onGuildMemberLeave(GuildMemberLeaveEvent e)
	{
		
		Guild guild = e.getGuild();
		User user = e.getUser();
		
		if(guild.getId().equals("336291415908679690"))
		{
			
			guild.getTextChannelById("369682886040879104").sendMessage(user.getName() + "#" + user.getDiscriminator() + " just left the Arena Brawl Community to die. :slight_frown:").queue();
			
		}		
	}
}
