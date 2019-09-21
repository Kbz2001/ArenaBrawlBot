package ArenaBot.Handlers;

import ArenaBot.App;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class WordsHandler extends ListenerAdapter 
{

	static ArrayList<String> mandemEmotes = new ArrayList<>();

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		
		Message msg = e.getMessage();
    	MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();

    	if(App.isOnline && !user.isBot())
		{

			if(msg.getContentRaw().equalsIgnoreCase("hello") ||
					msg.getContentRaw().equalsIgnoreCase("hi") ||
					msg.getContentRaw().equalsIgnoreCase("hey") ||
					msg.getContentRaw().equalsIgnoreCase("hai"))
			{

				channel.sendMessage("Hello " + user.getAsMention() + "!").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("hej"))
			{

				channel.sendMessage("Hej " + user.getAsMention() + "!").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("goodnight") ||
					msg.getContentRaw().toLowerCase().contains("good night") ||
					msg.getContentRaw().toLowerCase().contains("gn"))
			{

				channel.sendMessage("Goodbye " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("godnat"))
			{

				channel.sendMessage("Godnat " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("doe"))
			{

				msg.addReaction(":Kbz:336927247363604491").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("railed"))
			{

				msg.addReaction(":Invincitron2000:336680103973093387").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("rily") && !msg.getContentRaw().equalsIgnoreCase("narily"))
			{

				msg.addReaction(":SnapDoomy:336660661570502656").queue();

			}

//			if(msg.getContentRaw().toLowerCase().contains("narily") && !msg.getContentRaw().equalsIgnoreCase("rily"))
//			{
//
//				msg.addReaction(":Natily:539196345966264371").queue();
//
//			}

			if(msg.getContentRaw().toLowerCase().contains("snorp") ||
					msg.getContentRaw().toLowerCase().contains("garbaga") ||
					msg.getContentRaw().toLowerCase().contains("barbara") ||
					msg.getContentRaw().toLowerCase().contains("frick"))
			{


				channel.sendMessage("Please do not say that " + user.getAsMention() + "!").queue();
				msg.addReaction(":Rude:554448338200690689").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("guey"))
			{

				msg.addReaction(":DevinHuey:336629496922767360").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("rau") ||
					msg.getContentRaw().toLowerCase().contains("respect all users"))
			{

				msg.addReaction(":RAU:481298032865050625").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("mandem"))
			{

				Collections.shuffle(mandemEmotes);

				for(int i = 0; i < 8; i++)
				{

					msg.addReaction(mandemEmotes.get(i)).queue();

				}
			}

			if(msg.getContentRaw().toLowerCase().contains("bloke"))
			{

				Collections.shuffle(mandemEmotes);

				for(int i = 0; i < 1; i++)
				{

					msg.addReaction(mandemEmotes.get(i)).queue();

				}
			}

			if(msg.getContentRaw().toLowerCase().contains("pekka"))
			{

				msg.addReaction(":Pekkaguey:551182374449315870").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("arena brawl"))
			{

				msg.addReaction(":ArenaBrawl:481298350856077322").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("climbin")
					|| msg.getContentRaw().toLowerCase().contains("climbing")
					|| msg.getContentRaw().toLowerCase().contains("climb"))

			{

				msg.addReaction(":Wat:554447921748246529").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("yanked"))
			{

				msg.addReaction(":Ferozion:550861421718798338").queue();

			}
		}
	}
}
