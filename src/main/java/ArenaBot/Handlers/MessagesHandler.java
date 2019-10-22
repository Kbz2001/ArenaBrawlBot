package ArenaBot.Handlers;

import ArenaBot.App;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessagesHandler implements MessageCreateListener
{

	@Override
	public void onMessageCreate(MessageCreateEvent e)
	{

		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		MessageAuthor user = e.getMessageAuthor();

		if(App.totalMessages < 0)
		{

			App.totalMessages = 0;

			tChannel.sendMessage("We cannot have negative messages!"
					+ "\nThe message count has been set to 0!");

		}

		if(!App.isOnline && !msg.getContent().equalsIgnoreCase("%toggleonline") && msg.getContent().startsWith("%"))
		{

			MethodsHandler.sendOfflineErrorMessage(tChannel);

		}

		if(msg.getContent().startsWith("%") && user.isBotUser())
		{

			MethodsHandler.sendBotPermissionErrorMessage(tChannel);

		}

		else
		{

			if(msg.getContent().length() != 0 && !msg.getContent().startsWith("%"))
			{

				App.totalMessages += 1;
				MethodsHandler.saveTotalMessageConfig();

				if(App.saveUsers.containsKey(user.getIdAsString()))
				{

					App.saveUsers.put(user.getIdAsString(), App.saveUsers.get(user.getIdAsString()) + 1);
					MethodsHandler.saveUserMessageConfig();

				}

				else
				{

					App.saveUsers.put(user.getIdAsString(), 1);
					MethodsHandler.saveUserMessageConfig();

				}
			}
		}
	}
}
