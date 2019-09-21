package ArenaBot.Currency;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;

public class KbzTokens extends ListenerAdapter 
{

	public static HashMap<String, Integer> Tokens = new HashMap<String, Integer>();
		
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{

		Message msg = e.getMessage();
		User user = e.getAuthor();

		if(App.isOnline && !user.isBot())
		{

			if(msg.getContentRaw().length() != 0 && !msg.getContentRaw().startsWith("%"))
			{

				if(!KbzTokens.Tokens.containsKey(user.getId()) || KbzTokens.Tokens.get(user.getId()) < 0)
				{

					KbzTokens.Tokens.put(user.getId(), 0);

					MethodsHandler.saveTokenConfig();

				}

				if(KbzTokens.Tokens.containsKey(user.getId()) && App.saveUsers.get(user.getId()) % 5 == 0)
				{

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + 1);

					MethodsHandler.saveTokenConfig();

				}
			}
		}
	}
}
