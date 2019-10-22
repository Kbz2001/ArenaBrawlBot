package ArenaBot.Currency;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class KbzTokens implements MessageCreateListener
{

	public static HashMap<String, Integer> Tokens = new HashMap<String, Integer>();

	@Override
	public void onMessageCreate(MessageCreateEvent e)
	{

		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		MessageAuthor user = e.getMessageAuthor();

		if(!App.isOnline)
		{

			MethodsHandler.sendOfflineErrorMessage(tChannel);

		}

		if(msg.getContent().startsWith("%") && user.isBotUser())
		{

			MethodsHandler.sendBotPermissionErrorMessage(tChannel);

		}

		else
		{

			if(msg.getContent().length() != 0)
			{

				if(!Tokens.containsKey(user.getIdAsString()) || Tokens.get(user.getIdAsString()) < 0)
				{

					Tokens.put(user.getIdAsString(), 0);
					try {
						MethodsHandler.saveTokenConfig();
					} catch (InterruptedException | TimeoutException | ExecutionException ex) {
						ex.printStackTrace();
					}

				}

				if(Tokens.containsKey(user.getIdAsString()) && App.saveUsers.get(user.getIdAsString()) % 5 == 0)
				{

					Tokens.put(user.getIdAsString(), Tokens.get(user.getIdAsString()) + 1);
					try
					{
						MethodsHandler.saveTokenConfig();
					}
					catch (InterruptedException | ExecutionException | TimeoutException ex)
					{
						ex.printStackTrace();
					}

				}
			}
		}
	}
}
