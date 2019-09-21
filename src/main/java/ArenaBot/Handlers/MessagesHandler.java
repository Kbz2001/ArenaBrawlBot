package ArenaBot.Handlers;

import ArenaBot.App;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class MessagesHandler extends ListenerAdapter 
{

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
	
		Message msg = e.getMessage();
		MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();

    	if(App.isOnline && !user.isBot())
		{

			if(msg.getContentRaw().length() != 0 && !msg.getContentRaw().startsWith("%"))
			{

				App.totalMessages = App.totalMessages +1;

				MethodsHandler.saveTotalMessageConfig();

			}

			if(msg.getContentRaw().length() != 0 && !msg.getContentRaw().startsWith("%"))
			{

				if(App.saveUsers.containsKey(user.getId()))
				{

					App.saveUsers.put(user.getId(), App.saveUsers.get(user.getId()) +1);

					MethodsHandler.saveUserMessageConfig();

				}

				if(!App.saveUsers.containsKey(user.getId()))
				{

					App.saveUsers.put(user.getId(), 1);

					MethodsHandler.saveUserMessageConfig();

				}
			}
		}
		
    	if(App.totalMessages < 0)
    	{
    		
    		App.totalMessages = 0;
    		
    		channel.sendMessage("We cannot have negative messages!"
    				+ "\nThe message count has been set to 0!").queue();
    		
    	}

		if(!App.isOnline && !msg.getContentRaw().equalsIgnoreCase("%toggleonline") && msg.getContentRaw().startsWith("%"))
		{

			EmbedBuilder builder = new EmbedBuilder();

			builder.setColor(Color.RED).setDescription("Sorry I am currently **Offline** :(!");

			channel.sendMessage(builder.build()).queue();

		}
	}
}
