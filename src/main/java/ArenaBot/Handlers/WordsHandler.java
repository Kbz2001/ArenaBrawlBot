package ArenaBot.Handlers;

import ArenaBot.App;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordsHandler extends ListenerAdapter 
{

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		
		Message msg = e.getMessage();
    	MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();

    	if(App.isOnline && !user.isBot())
		{

			if(msg.getRawContent().equalsIgnoreCase("hello") ||
					msg.getRawContent().equalsIgnoreCase("hi") ||
					msg.getRawContent().equalsIgnoreCase("hey") ||
					msg.getRawContent().equalsIgnoreCase("hai"))
			{

				channel.sendMessage("Hello " + user.getAsMention() + "!").queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("hej"))
			{

				channel.sendMessage("Hej " + user.getAsMention() + "!").queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("goodnight") ||
					msg.getRawContent().equalsIgnoreCase("good night") ||
					msg.getRawContent().equalsIgnoreCase("gn"))
			{

				channel.sendMessage("Goodbye " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();;

			}

			if(msg.getRawContent().equalsIgnoreCase("godnat"))
			{

				channel.sendMessage("Godnat " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();;

			}

			if(msg.getRawContent().toLowerCase().equals("doe"))
			{

				channel.sendMessage("You are a doe " + user.getAsMention()).queue();
				msg.addReaction(":Kbz:336927247363604491").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("railed"))
			{

				msg.addReaction(":Invincitron2000:336680103973093387").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("rily"))
			{

				msg.addReaction(":SnapDoomy:336660661570502656").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("snorp") ||
					msg.getRawContent().toLowerCase().contains("garbaga") ||
					msg.getRawContent().toLowerCase().contains("barbara") ||
					msg.getRawContent().toLowerCase().contains("frick"))
			{


				channel.sendMessage("Please do not say that " + user.getAsMention() + "!").queue();
				msg.addReaction(":Rude:336996831370412032").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("guey"))
			{

				msg.addReaction(":DevinHuey:336629496922767360").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("rau") ||
					msg.getRawContent().toLowerCase().contains("respect all users"))
			{

				msg.addReaction(":RAU:481298032865050625").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("mandem"))
			{

				ArrayList<String> mandemEmotes = new ArrayList <String>();

				mandemEmotes.add(":KbzYouyou:336927247363604491");
				mandemEmotes.add(":SmellyDoe:337019229180133376");
				mandemEmotes.add(":Ryan:341311929517735936");
				mandemEmotes.add(":Smellypizza:336993029359665155");
				mandemEmotes.add(":DevinHuey:336629496922767360");
				mandemEmotes.add(":Fire_Orb:336951598733590528");
				mandemEmotes.add(":Kylaroo:396911525744607263");
				mandemEmotes.add(":Supersheep10:337367069156966400");
				mandemEmotes.add(":TheClassicGamer:396912549049794560");
				mandemEmotes.add(":Viking725:481296354803580928");
				mandemEmotes.add(":Gormley:337031923182469122");
				mandemEmotes.add(":Qwed:337025936706764801");
				mandemEmotes.add(":Pekkaguey:472570497662451745");
				mandemEmotes.add(":lemowel:394963855132065792");
				mandemEmotes.add(":Mitchellith:337029090710388736");
				mandemEmotes.add(":LadyPiper:336631732079951872");
				mandemEmotes.add(":Martinreece:338019688149942272");
				mandemEmotes.add(":SnapDoomy:336660661570502656");
				mandemEmotes.add(":Invincitron2000:336680103973093387");
				mandemEmotes.add(":Smoarzified:511750990865760257");
				mandemEmotes.add(":Beggar_Douglas:512036762923696141");
				mandemEmotes.add(":Edupa:338012355227287563");

				Collections.shuffle(mandemEmotes);

				for(int i = 0; i < 8; i++)
				{

					msg.addReaction(mandemEmotes.get(i)).queue();

				}
			}

			if(msg.getRawContent().toLowerCase().contains("pekka"))
			{

				msg.addReaction(":Pekkaguey:472570497662451745").queue();

			}

			if(msg.getRawContent().toLowerCase().contains("arena brawl"))
			{

				msg.addReaction(":ArenaBrawl:481298350856077322").queue();

			}
		}
	}
}
