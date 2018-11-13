package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;

public class MessagesHandler extends ListenerAdapter 
{
	
	public MessagesHandler()
	{
		super();	
		
		if(new File(System.getProperty("user.home") + System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//Total Message Count//message.LadyPiper").exists())
    	{
    	
    		App.totalMessages = Integer.parseInt(MethodsHandler.Read(new File(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//Total Message Count//message.LadyPiper")));
    		
    	}
    
    	else
    	{
    		
    		App.totalMessages = 0;
    		
    	}
    }
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
	
		Message msg = e.getMessage();
		MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();

    	if(App.isOnline && !user.isBot())
		{

			if(msg.getRawContent().length() != 0 && !msg.getRawContent().startsWith("%"))
			{

				App.totalMessages = App.totalMessages +1;

				MethodsHandler.Write(new File(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//Total Message Count//message.LadyPiper"), Integer.toString(App.totalMessages));

			}

			if(msg.getRawContent().length() != 0 && !msg.getRawContent().startsWith("%"))
			{

				if(App.saveUsers.containsKey(user.getId()))
				{

					App.saveUsers.put(user.getId(), App.saveUsers.get(user.getId()) +1);

					MethodsHandler.saveMessageConfig();

				}

				if(!App.saveUsers.containsKey(user.getId()))
				{

					App.saveUsers.put(user.getId(), 1);

					MethodsHandler.saveMessageConfig();

				}
			}
		}
		
    	if(App.totalMessages < 0)
    	{
    		
    		App.totalMessages = 0;
    		
    		channel.sendMessage("We cannot have negative messages!"
    				+ "\nThe message count has been set to 0!").queue();
    		
    	}

		if(!App.isOnline && !msg.getRawContent().equalsIgnoreCase("%toggleonline") && msg.getRawContent().startsWith("%"))
		{

			EmbedBuilder builder = new EmbedBuilder();

			builder.setColor(Color.RED).setDescription("Sorry I am currently **Offline** :(!");

			channel.sendMessage(builder.build()).queue();

		}

	}
}
