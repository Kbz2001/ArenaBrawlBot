package ArenaBot.Handlers;

import ArenaBot.Commands.UserCommands;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Collections;

public class ArenaRandomizeHandler
{

	public static void runRandomize(MessageReceivedEvent e)
	{

		MessageChannel channel = e.getChannel();

		if(UserCommands.listOfEnteredPlayers.size() % 4 != 0 || UserCommands.listOfEnteredPlayers.size() == 0)
		{

			channel.sendMessage("I cannot randomize because I require 4, 8, or 12 players to function!").queue();

			return;

		}

		if(UserCommands.listToRandom.size() == 4)
		{

			Collections.shuffle(UserCommands.listToRandom);

			String team1 = "";
			String team2 = "";

			team1 = UserCommands.listToRandom.get(0) + " " + UserCommands.listToRandom.get(1);
			team2 = UserCommands.listToRandom.get(2) + " " + UserCommands.listToRandom.get(3);

			channel.sendMessage("*__Teams:__*"
					+ "\n"
					+ "**Team 1:** " + team1
					+ "\n"
					+ "**Team 2:** " + team2).queue();

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


			channel.sendMessage("*__Teams:__*"
					+ "\n"
					+ "**Team 1:** " + team1
					+ "\n"
					+ "**Team 2:** " + team2
					+ "\n"
					+ "**Team 3:** " + team3
					+ "\n"
					+ "**Team 4:** " + team4).queue();
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

			channel.sendMessage("*__Teams:__*"
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
					+ "**Team 6:** " + team6).queue();

		}

	}
}
