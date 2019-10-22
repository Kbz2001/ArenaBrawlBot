package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Commands.UserCommands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;

public class ArenaRandomizeHandler
{

	public static void runRandomize(MessageCreateEvent e)
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

		if(UserCommands.listOfEnteredPlayers.size() % 4 != 0 || UserCommands.listOfEnteredPlayers.size() == 0)
		{

			tChannel.sendMessage("I cannot randomize because I require 4, 8, or 12 players to function!");

			return;

		}

		if(UserCommands.listToRandom.size() == 4)
		{

			Collections.shuffle(UserCommands.listToRandom);

			String team1 = "";
			String team2 = "";

			team1 = UserCommands.listToRandom.get(0) + " " + UserCommands.listToRandom.get(1);
			team2 = UserCommands.listToRandom.get(2) + " " + UserCommands.listToRandom.get(3);

			tChannel.sendMessage("*__Teams:__*"
					+ "\n"
					+ "**Team 1:** " + team1
					+ "\n"
					+ "**Team 2:** " + team2);

		}

		if(UserCommands.listOfEnteredPlayers.size() == 8)
		{

			Collections.shuffle(UserCommands.listToRandom);

			String team1 = "";
			String team2 = "";
			String team3 = "";
			String team4 = "";

			team1 = UserCommands.listToRandom.get(0) + " " + UserCommands.listToRandom.get(1);
			team2 = UserCommands.listToRandom.get(2) + " " + UserCommands.listToRandom.get(3);
			team3 = UserCommands.listToRandom.get(4) + " " + UserCommands.listToRandom.get(5);
			team4 = UserCommands.listToRandom.get(6) + " " + UserCommands.listToRandom.get(7);


			tChannel.sendMessage("*__Teams:__*"
					+ "\n"
					+ "**Team 1:** " + team1
					+ "\n"
					+ "**Team 2:** " + team2
					+ "\n"
					+ "**Team 3:** " + team3
					+ "\n"
					+ "**Team 4:** " + team4);
		}

		if(UserCommands.listOfEnteredPlayers.size() == 12)
		{

			Collections.shuffle(UserCommands.listToRandom);

			String team1 = "";
			String team2 = "";
			String team3 = "";
			String team4 = "";
			String team5 = "";
			String team6 = "";

			team1 = UserCommands.listToRandom.get(0) + " " + UserCommands.listToRandom.get(1);
			team2 = UserCommands.listToRandom.get(2) + " " + UserCommands.listToRandom.get(3);
			team3 = UserCommands.listToRandom.get(4) + " " + UserCommands.listToRandom.get(5);
			team4 = UserCommands.listToRandom.get(6) + " " + UserCommands.listToRandom.get(7);
			team5 = UserCommands.listToRandom.get(8) + " " + UserCommands.listToRandom.get(9);
			team6 = UserCommands.listToRandom.get(10) + " " + UserCommands.listToRandom.get(11);

			tChannel.sendMessage("*__Teams:__*"
					+ "\n"
					+ "**Team 1:** " + team1
					+ "\n"
					+ "**Team 2:** " + team2
					+ "\n"
					+ "**Team 3:** " + team3
					+ "\n"
					+ "**Team 4:** " + team4
					+ "\n"
					+ "**Team 5:** " + team5
					+ "\n"
					+ "**Team 6:** " + team6);

		}
	}
}
