package ArenaBot.Handlers;

import ArenaBot.Resources.Flags;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlagGameHandler extends ListenerAdapter
{

	private static HashMap<Integer, String> store5Flags = new HashMap <>();
	private static LinkedHashMap<String, String> storeMessageIDs = new LinkedHashMap <>();

	private static boolean guessedOne = false;
	private static boolean guessedTwo = false;
	private static boolean guessedThree = false;
	private static boolean guessedFour = false;
	private static boolean guessedFive = false;
	public static boolean isRunning = false;

	private static int editCounter = 0;

	public static void startFlagGame(MessageReceivedEvent e)
	{

		MessageChannel channel = e.getChannel();

		EmbedBuilder builder1 = new EmbedBuilder();

		builder1.setColor(Color.CYAN).setDescription("**A New Flag Game has Started!**"
				+ "\n"
				+ "------------------------------------");

		channel.sendMessage(builder1.build()).queue();

		isRunning = true;

	}

	public static void runFlagGame(MessageReceivedEvent e)
	{

		MessageChannel channel = e.getChannel();

		HashMap<String, String> flags = Flags.getFlags();

		List<Map.Entry <String, String>> randomFlags = new ArrayList <>(flags.entrySet());

		Map.Entry <String, String> flagToPlace;

		Collections.shuffle(randomFlags);

		for(int i = 0; i <= 4; i++)
		{

			flagToPlace = randomFlags.get(i);

			Map.Entry <String, String> finalFlagToPlace = flagToPlace;

			store5Flags.put(i, flagToPlace.getValue());

			channel.sendMessage("#" + String.valueOf(i+1) + " Generating please wait...").queueAfter(1, TimeUnit.SECONDS, message -> message.editMessage(finalFlagToPlace.getKey()).queueAfter(2, TimeUnit.SECONDS));

		}
	}

	public static void stopFlagGame(MessageReceivedEvent e)
	{

		User user = e.getAuthor();
		MessageChannel channel = e.getChannel();

		store5Flags.clear();
		storeMessageIDs.clear();

		editCounter = 0;

		isRunning = false;

		guessedOne = false;
		guessedTwo = false;
		guessedThree = false;
		guessedFour = false;
		guessedFive = false;

		channel.sendMessage("The game has been stopped by " + user.getAsMention() + " !").queue();

	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{

		Message msg = e.getMessage();
		MessageChannel channel = e.getTextChannel();
		User user = e.getAuthor();

		if(user.isBot() && user.getId().equals("494363113030680576"))
		{

			if(msg.getContentRaw().startsWith("#1") ||
					msg.getContentRaw().startsWith("#2") ||
					msg.getContentRaw().startsWith("#3") ||
					msg.getContentRaw().startsWith("#4") ||
					msg.getContentRaw().startsWith("#5"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				switch(msg.getContentRaw())
				{


					case "#1 Generating please wait...":
						storeMessageIDs.put(store5Flags.get(0), msg.getId());

						break;

					case "#2 Generating please wait...":
						storeMessageIDs.put(store5Flags.get(1), msg.getId());

						break;

					case "#3 Generating please wait...":
						storeMessageIDs.put(store5Flags.get(2), msg.getId());

						break;

					case "#4 Generating please wait...":

						storeMessageIDs.put(store5Flags.get(3), msg.getId());

						break;

					case "#5 Generating please wait...":

						storeMessageIDs.put(store5Flags.get(4), msg.getId());

						builder.setColor(Color.CYAN).setDescription("------------------------------------");
						channel.sendMessage(builder.build()).queue();

						break;

				}
			}
		}

		if(msg.getContentRaw().equalsIgnoreCase(store5Flags.get(0)))
		{

			if(!guessedOne)
			{

				if(editCounter != 5)
				{

					guessedOne = true;

					channel.sendMessage(user.getAsMention() + " has guessed a flag correctly! (" + store5Flags.get(0) + ")" ).queue();

					editCounter++;

					channel.editMessageById(storeMessageIDs.get(msg.getContentRaw()), msg.getContentRaw() + " has been claimed by: " + user.getAsMention() + " <:Agree:336621149243047938>").queue();

				}
			}

			else
			{

				channel.sendMessage("That flag has already been guessed " + user.getAsMention() + "!").queue();

			}
		}

		if(msg.getContentRaw().equalsIgnoreCase(store5Flags.get(1)))
		{

			if(!guessedTwo)
			{

				if(editCounter != 5)
				{

					guessedTwo = true;

					channel.sendMessage(user.getAsMention() + " has guessed a flag correctly! (" + store5Flags.get(1) + ")" ).queue();

					editCounter++;

					channel.editMessageById(storeMessageIDs.get(msg.getContentRaw()), msg.getContentRaw() + " has been claimed by: " + user.getAsMention() + " <:Agree:336621149243047938>").queue();

				}
			}

			else
			{

				channel.sendMessage("That flag has already been guessed " + user.getAsMention() + "!").queue();

			}
		}

		if(msg.getContentRaw().equalsIgnoreCase(store5Flags.get(2)))

		{

			if(!guessedThree)
			{

				if(editCounter != 5)
				{

					guessedThree = true;

					channel.sendMessage(user.getAsMention() + " has guessed a flag correctly! (" + store5Flags.get(2) + ")" ).queue();

					editCounter++;

					channel.editMessageById(storeMessageIDs.get(msg.getContentRaw()), msg.getContentRaw() + " has been claimed by: " + user.getAsMention() + " <:Agree:336621149243047938>").queue();

				}
			}

			else
			{

				channel.sendMessage("That flag has already been guessed " + user.getAsMention() + "!").queue();


			}
		}

		if(msg.getContentRaw().equalsIgnoreCase(store5Flags.get(3)))

		{

			if(!guessedFour)
			{

				if(editCounter != 5)
				{

					guessedFour = true;

					channel.sendMessage(user.getAsMention() + " has guessed a flag correctly! (" + store5Flags.get(3) + ")" ).queue();

					editCounter++;

					channel.editMessageById(storeMessageIDs.get(msg.getContentRaw()), msg.getContentRaw() + " has been claimed by: " + user.getAsMention() + " <:Agree:336621149243047938>").queue();

				}
			}

			else
			{

				channel.sendMessage("That flag has already been guessed " + user.getAsMention() + "!").queue();


			}
		}

		if(msg.getContentRaw().equalsIgnoreCase(store5Flags.get(4)))
		{

			if(!guessedFive)
			{

				if(editCounter != 5)
				{

					guessedFive = true;

					channel.sendMessage(user.getAsMention() + " has guessed a flag correctly! (" + store5Flags.get(4) + ")" ).queue();

					editCounter++;

					channel.editMessageById(storeMessageIDs.get(msg.getContentRaw()), msg.getContentRaw() + " has been claimed by: " + user.getAsMention() + " <:Agree:336621149243047938>").queue();

				}
			}

			else
			{

				channel.sendMessage("That flag has already been guessed " + user.getAsMention() + "!").queue();


			}
		}

		else if(editCounter == 5)
		{

			editCounter = 0;

			guessedOne = false;
			guessedTwo = false;
			guessedThree = false;
			guessedFour = false;
			guessedFive = false;
			isRunning = false;

			for(int i = 0; i < 5; i++)
			{

				store5Flags.remove(i);

			}

			channel.sendMessage("The flag game has ended! The person with the most correct guesses wins!").queue();

		}
	}
}
