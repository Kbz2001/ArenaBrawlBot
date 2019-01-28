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

			if(msg.getContentRaw().equalsIgnoreCase("hello") ||
					msg.getContentRaw().equalsIgnoreCase("hi") ||
					msg.getContentRaw().equalsIgnoreCase("hey") ||
					msg.getContentRaw().equalsIgnoreCase("hai"))
			{

				channel.sendMessage("Hello " + user.getAsMention() + "!").queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("hej"))
			{

				channel.sendMessage("Hej " + user.getAsMention() + "!").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("goodnight") ||
					msg.getContentRaw().toLowerCase().contains("good night") ||
					msg.getContentRaw().toLowerCase().contains("gn"))
			{

				channel.sendMessage("Goodbye " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();;

			}

			if(msg.getContentRaw().toLowerCase().contains("godnat"))
			{

				channel.sendMessage("Godnat " + user.getAsMention() + "!").queue();
				msg.addReaction(":gamer818:341311893132279818").queue();;

			}

			if(msg.getContentRaw().toLowerCase().contains("doe"))
			{

				channel.sendMessage("You are a doe " + user.getAsMention()).queue();
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

			if(msg.getContentRaw().toLowerCase().contains("narily") && !msg.getContentRaw().equalsIgnoreCase("rily"))
			{

				msg.addReaction(":Natily:539196345966264371").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("snorp") ||
					msg.getContentRaw().toLowerCase().contains("garbaga") ||
					msg.getContentRaw().toLowerCase().contains("barbara") ||
					msg.getContentRaw().toLowerCase().contains("frick"))
			{


				channel.sendMessage("Please do not say that " + user.getAsMention() + "!").queue();
				msg.addReaction(":Rude:336996831370412032").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("guey"))
			{

				msg.addReaction(":DevinHuey:336629496922767360").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("rau") ||
					msg.getContentRaw().toLowerCase().contains("respect all users"))
			{

				msg.addReaction(":RAU:481298032865050625").queue();

				channel.sendMessage("https://cdn.discordapp.com/attachments/336291415908679690/533711630668660746/RAU.png").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("mandem"))
			{

				ArrayList<String> mandemEmotes = new ArrayList <String>();

				mandemEmotes.add(":Kbz:336927247363604491");
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
				mandemEmotes.add(":greaneye:521822646279602176");
				mandemEmotes.add(":Cashboys:513889060708941846");
				mandemEmotes.add(":Edupa:338012355227287563");
				mandemEmotes.add(":xLatios:514458844517367808");
				mandemEmotes.add(":Natily:539196345966264371");

				Collections.shuffle(mandemEmotes);

				for(int i = 0; i < 8; i++)
				{

					msg.addReaction(mandemEmotes.get(i)).queue();

				}
			}

			if(msg.getContentRaw().toLowerCase().contains("bloke"))
			{

				ArrayList<String> mandemEmotes = new ArrayList <String>();

				mandemEmotes.add(":Kbz:336927247363604491");
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
				mandemEmotes.add(":greaneye:521822646279602176");
				mandemEmotes.add(":Cashboys:513889060708941846");
				mandemEmotes.add(":Edupa:338012355227287563");
				mandemEmotes.add(":xLatios:514458844517367808");
				mandemEmotes.add(":Natily:539196345966264371");

				Collections.shuffle(mandemEmotes);

				for(int i = 0; i < 1; i++)
				{

					msg.addReaction(mandemEmotes.get(i)).queue();

				}
			}

			if(msg.getContentRaw().toLowerCase().contains("pekka"))
			{

				msg.addReaction(":Pekkaguey:472570497662451745").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("arena brawl"))
			{

				msg.addReaction(":ArenaBrawl:481298350856077322").queue();

			}

			if(msg.getContentRaw().toLowerCase().contains("climbin") || msg.getContentRaw().toLowerCase().contains("climbing"))
			{

				msg.addReaction(":Wat:336621239256875010").queue();

			}

		}
	}
}
