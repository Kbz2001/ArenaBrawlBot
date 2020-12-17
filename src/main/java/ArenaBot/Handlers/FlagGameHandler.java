package ArenaBot.Handlers;

import ArenaBot.Currency.KbzTokens;
import ArenaBot.Resources.Flags;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class FlagGameHandler implements MessageCreateListener
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

	public static void startFlagGame(MessageCreateEvent e)
	{

		 TextChannel tChannel = e.getChannel();

		MessageBuilder builder = new MessageBuilder()
				.setEmbed(new EmbedBuilder()
						.setTitle("**A New Flag Game has Started!")
						.setDescription("The person who guesses the most right wins.")
						.setColor(Color.CYAN));

		builder.send(tChannel);

		isRunning = true;

	}

	public static void runFlagGame(MessageCreateEvent e) throws InterruptedException, ExecutionException
	{

		TextChannel tChannel = e.getChannel();
		HashMap<String, String> flags = Flags.getFlags();
		List<Map.Entry <String, String>> randomFlags = new ArrayList <>(flags.entrySet());
		Map.Entry <String, String> flagToPlace;

		Collections.shuffle(randomFlags);

		for(int i = 0; i <= 4; i++)
		{

			flagToPlace = randomFlags.get(i);

			Map.Entry <String, String> finalFlagToPlace = flagToPlace;

			store5Flags.put(i, flagToPlace.getValue());

			int finalI = i;

			tChannel.sendMessage("#" + (i + 1) + " Generating please wait...").thenAccept(msg -> {

				storeMessageIDs.put(store5Flags.get(finalI), msg.getIdAsString());
				msg.edit(finalFlagToPlace.getKey());

			});
		}
	}

	public static void stopFlagGame(MessageCreateEvent e)
	{

		User user = e.getMessageAuthor().asUser().get();
		TextChannel tChannel = e.getChannel();

		store5Flags.clear();
		storeMessageIDs.clear();

		editCounter = 0;

		isRunning = false;

		guessedOne = false;
		guessedTwo = false;
		guessedThree = false;
		guessedFour = false;
		guessedFive = false;

		tChannel.sendMessage("The game has been stopped by " + user.getMentionTag() + " !");

	}

	public static void flagGameAnswers(MessageCreateEvent e)
	{

		TextChannel tChannel = e.getChannel();

		tChannel.sendMessage("The answers to this round are:");

		for(int i = 0; i <= 4; i++)
		{

			tChannel.sendMessage("#" + (i + 1) + " = " + store5Flags.get(i));

		}
	}

	@Override
	public void onMessageCreate(MessageCreateEvent e)
	{

		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		User user = e.getMessageAuthor().asUser().get();

		if(msg.getContent().equalsIgnoreCase(store5Flags.get(0)))
		{

			if(!guessedOne)
			{

				if(editCounter != 5)
				{

					guessedOne = true;

					tChannel.sendMessage(user.getMentionTag() + " has guessed a flag correctly! (" + store5Flags.get(0) + ")" + " +20 Kbz Tokens");

					editCounter++;

					KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + 20);

					try
					{

						tChannel.getMessageById(storeMessageIDs.get(MethodsHandler.capitalize(msg.getContent()))).get().edit(MethodsHandler.capitalize(msg.getContent()) + " has been claimed by: " + user.getMentionTag() + " <:Agree:554445062528827433>");

					}

					catch (InterruptedException | ExecutionException ex)
					{

						ex.printStackTrace();

					}
				}
			}

			else
			{

				tChannel.sendMessage("That flag has already been guessed " + user.getMentionTag() + "!");

			}
		}

		if(msg.getContent().equalsIgnoreCase(store5Flags.get(1)))
		{

			if(!guessedTwo)
			{

				if(editCounter != 5)
				{

					guessedTwo = true;

					tChannel.sendMessage(user.getMentionTag() + " has guessed a flag correctly! (" + store5Flags.get(1) + ")" + " +20 Kbz Tokens");

					editCounter++;

					KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + 20);

					try
					{

						tChannel.getMessageById(storeMessageIDs.get(MethodsHandler.capitalize(msg.getContent()))).get().edit(MethodsHandler.capitalize(msg.getContent()) + " has been claimed by: " + user.getMentionTag() + " <:Agree:554445062528827433>");

					}

					catch (InterruptedException | ExecutionException ex)
					{

						ex.printStackTrace();

					}
				}
			}

			else
			{

				tChannel.sendMessage("That flag has already been guessed " + user.getMentionTag() + "!");

			}
		}

		if(msg.getContent().equalsIgnoreCase(store5Flags.get(2)))
		{

			if(!guessedThree)
			{

				if(editCounter != 5)
				{

					guessedThree = true;

					tChannel.sendMessage(user.getMentionTag() + " has guessed a flag correctly! (" + store5Flags.get(2) + ")" + " +20 Kbz Tokens");

					editCounter++;

					KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + 20);

					try
					{

						tChannel.getMessageById(storeMessageIDs.get(MethodsHandler.capitalize(msg.getContent()))).get().edit(MethodsHandler.capitalize(msg.getContent()) + " has been claimed by: " + user.getMentionTag() + " <:Agree:554445062528827433>");

					}

					catch (InterruptedException | ExecutionException ex)
					{

						ex.printStackTrace();

					}
				}
			}

			else
			{

				tChannel.sendMessage("That flag has already been guessed " + user.getMentionTag() + "!");

			}
		}

		if(msg.getContent().equalsIgnoreCase(store5Flags.get(3)))
		{

			if(!guessedFour)
			{

				if(editCounter != 5)
				{

					guessedFour = true;

					tChannel.sendMessage(user.getMentionTag() + " has guessed a flag correctly! (" + store5Flags.get(3) + ")" + " +20 Kbz Tokens");

					editCounter++;

					KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + 20);

					try
					{

						tChannel.getMessageById(storeMessageIDs.get(MethodsHandler.capitalize(msg.getContent()))).get().edit(MethodsHandler.capitalize(msg.getContent()) + " has been claimed by: " + user.getMentionTag() + " <:Agree:554445062528827433>");

					}

					catch (InterruptedException | ExecutionException ex)
					{

						ex.printStackTrace();

					}
				}
			}

			else
			{

				tChannel.sendMessage("That flag has already been guessed " + user.getMentionTag() + "!");

			}
		}

		if(msg.getContent().equalsIgnoreCase(store5Flags.get(4)))
		{

			if(!guessedFive)
			{

				if(editCounter != 5)
				{

					guessedFive = true;

					tChannel.sendMessage(user.getMentionTag() + " has guessed a flag correctly! (" + store5Flags.get(4) + ")" + " +20 Kbz Tokens");

					editCounter++;

					KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + 20);

					try
					{

						tChannel.getMessageById(storeMessageIDs.get(MethodsHandler.capitalize(msg.getContent()))).get().edit(MethodsHandler.capitalize(msg.getContent()) + " has been claimed by: " + user.getMentionTag() + " <:Agree:554445062528827433>");

					}

					catch (InterruptedException | ExecutionException ex)
					{

						ex.printStackTrace();

					}
				}
			}

			else
			{

				tChannel.sendMessage("That flag has already been guessed " + user.getMentionTag() + "!");

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

			tChannel.sendMessage("The flag game has ended! The person with the most correct guesses wins!");

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
