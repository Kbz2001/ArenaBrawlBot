package ArenaBot.Handlers;

import ArenaBot.App;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Collections;

public class WordsHandler implements MessageCreateListener
{

	static ArrayList<String> mandemEmotes = new ArrayList<>();

	@Override
	public void onMessageCreate(MessageCreateEvent e)
	{

		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		MessageAuthor user = e.getMessageAuthor();

		if (!App.isOnline)
		{

			MethodsHandler.sendOfflineErrorMessage(tChannel);

		}

		else
		{

			if (msg.getContent().equalsIgnoreCase("hello") ||
					msg.getContent().equalsIgnoreCase("hi") ||
					msg.getContent().equalsIgnoreCase("hey") ||
					msg.getContent().equalsIgnoreCase("hai") ||
					msg.getContent().equalsIgnoreCase("hej"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction("\uD83D\uDC4B");

				}
			}

			if (msg.getContent().equalsIgnoreCase("goodnight") ||
					msg.getContent().equalsIgnoreCase("good night") ||
					msg.getContent().equalsIgnoreCase("gn") ||
					msg.getContent().equalsIgnoreCase("godnat"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":gamer818:341311893132279818");

				}
			}

			if (msg.getContent().toLowerCase().contains("doe"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":Kbz:336927247363604491");

				}
			}

			if (msg.getContent().toLowerCase().contains("railed"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":Invincitron2000:336680103973093387");

				}
			}

			if (msg.getContent().toLowerCase().contains("rily"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":SnapDoomy:336660661570502656");

				}
			}

			if (msg.getContent().toLowerCase().contains("snorp") ||
					msg.getContent().toLowerCase().contains("garbaga") ||
					msg.getContent().toLowerCase().contains("barbara") ||
					msg.getContent().toLowerCase().contains("frick"))
			{

				if (!user.isBotUser())
				{

					tChannel.sendMessage("Please do not say that " + user.asUser().map(User::getMentionTag).get() + "!");
					msg.addReaction(":Rude:554448338200690689");

				}
			}

			if (msg.getContent().toLowerCase().contains("guey"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":DevinHuey:336629496922767360");

				}
			}

			if (msg.getContent().toLowerCase().contains("rau") ||
					msg.getContent().toLowerCase().contains("respect all users"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":RAU:481298032865050625");

				}
			}

			if (msg.getContent().toLowerCase().contains("mandem"))
			{

				if (!user.isBotUser())
				{

					Collections.shuffle(mandemEmotes);

					for (int i = 0; i < 8; i++)
					{

						msg.addReaction(mandemEmotes.get(i));

					}
				}
			}

			if (msg.getContent().toLowerCase().contains("bloke"))
			{

				if (!user.isBotUser())
				{

					Collections.shuffle(mandemEmotes);

					for (int i = 0; i < 1; i++)
					{

						msg.addReaction(mandemEmotes.get(i));

					}
				}
			}

			if (msg.getContent().toLowerCase().contains("pekka"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":Pekkaguey:551182374449315870");

				}
			}

			if (msg.getContent().toLowerCase().contains("arena brawl"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":ArenaBrawl:481298350856077322");

				}
			}

			if (msg.getContent().toLowerCase().contains("pekka"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":Pekkaguey:551182374449315870");

				}
			}

			if (msg.getContent().toLowerCase().contains("climbin")
					|| msg.getContent().toLowerCase().contains("climbing")
					|| msg.getContent().toLowerCase().contains("climb"))
			{

				if (!user.isBotUser())
				{

					msg.addReaction(":Wat:554447921748246529");

				}
			}

			if (msg.getContent().toLowerCase().contains("yanked"))
			{


				if (!user.isBotUser())
				{

					msg.addReaction(":Ferozion:570431297005027368");

				}
			}
		}
	}
}
