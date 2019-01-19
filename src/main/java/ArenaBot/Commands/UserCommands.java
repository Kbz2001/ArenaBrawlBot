package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.ArenaRandomizeHandler;
import ArenaBot.Handlers.FlagGameHandler;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UserCommands extends ListenerAdapter
{

    public static ArrayList<String> listOfEnteredPlayers = new ArrayList <String>();
    public static ArrayList<String> listToRandom = new ArrayList <String>();
    private static HashMap<String, LocalDateTime> someoneCD = new HashMap <>();
    private static HashMap<String, LocalDateTime> playerCountCD = new HashMap <>();

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
	
		Message msg = e.getMessage();
    	MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();
    	Guild guild = e.getGuild();

    	if(App.isOnline && !user.isBot())
    	{

    		if(msg.getContentRaw().equalsIgnoreCase("%help"))
    		{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("***Hello there I am the Arena Discord Bot!***"
						+ "\n"
						+ "\n```http"
						+ "\n"
						+ "Here is a list of my commands!"
						+ "\n"
						+ "```"
						+ "\n"
						+ "\n**%help utility**: Gives you a list of utility commands."
						+ "\n"
						+ "\n**%help fungames**: Gives you a list of Fun and Games commands."
						+ "\n"
						+ "\n**%help message**: Gives you a list of Message commands."
						+ "\n"
						+ "\n**%help bot**: Gives you a list of bot commands."
						+ "\n"
						+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%help utility"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("\n**__Utility Commands__**"
						+ "\n"
						+ "\n"
						+ "*__User Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n%help: Shows a list of commands."
						+ "\n"
						+ "\n%playlist: Give you the links to all of the music playlists."
						+ "\n"
						+ "\n%ping: Tells you my ping."
						+ "\n"
						+ "\n%tokens: Tells you how many Kbz Tokens you have."
						+ "\n"
						+ "\n%server-info: Tells you the creation date of the Discord."
						+ "\n"
						+ "\n%random2v2info: A guide on how to use the random2v2 command."
						+ "\n"
						+ "\n%abplayers: Displays the current # of people playing Arena."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "\n*__Admin Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n%addtokens: Gives the specified numbers of tokens to the given player."
						+ "\n"
						+ "\n%tokenreset: Resets the specified user's token count."
						+ "\n"
						+ "\n%alltokenreset: Resets all personal Kbz Token counts to 0."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%help fungames"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("\n**__Fun and Games__**"
						+ "\n"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n%flip: Flips a coin. Heads or Tails. Bet x amount(Between 1 and 50). If you win you earn x, if you lose, you lose x. Good luck!"
						+ "\n"
						+ "\n%dice: Rolls a dice. Numbers are between 1 and 6."
						+ "\n"
						+ "\n%lenny: Gives you a random lenny face!"
						+ "\n"
						+ "\n%slots: Gamble away your Tokens with this command! For more info do %slotinfo."
						+ "\n"
						+ "\n%slotinfo: A guide on how to use the slot command."
						+ "\n"
						+ "\n%someone: Mentions a random person in the Discord!"
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%help message"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("\n**__Message Commands__**"
						+ "\n"
						+ "\n"
						+ "*__User Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n%totalmsgs: Shows you the total amount of messages we have sent."
						+ "\n"
						+ "\n%mymsgs: Shows you the total messages you have sent."
						+ "\n"
						+ "\n%lb: Displays the top 10 message senders."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "\n*__Admin Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n%totalreset: Resets the current number of messages to 0."
						+ "\n"
						+ "\n%totaladd: Adds a certain number to the total message count."
						+ "\n"
						+ "\n%totalsub: Subtracts a certain number to the total message count."
						+ "\n"
						+ "\n%lbreset: Resets the current leaderboard."
						+ "\n"
						+ "\n%alluserreset: Resets all personal message counts to 0."
						+ "\n"
						+ "\n%userreset: Resets the specified user's message count."
						+ "\n"
						+ "\n%purge: Deletes a specific number messages in the text channel."
						+ "\n"
						+ "\n```"
						+ "\n"
						+"\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%help bot"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("**__Bot Commands__**"
						+ "\n"
						+ "\n"
						+ "```css"
						+ "\n"
						+ "\n%toggleonline: Brings me into Online/Offline mode."
						+ "\n"
						+ "\n%shutdown: Shuts me down."
						+ "\n"
						+ "\n```"
						+ "\n"
						+"\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%playlist"))
			{

				channel.sendMessage(
						"https://www.youtube.com/playlist?list=PLY1FiABbZmulmquZXiawOFPdptNsn-Sjd"
								+ "\n"
								+ "https://www.youtube.com/playlist?list=PLY1FiABbZmunI5jR2n9Ysja5CFbUS_lvx"
				).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%ping"))
			{

				if(!user.isBot())
				{

					channel.sendMessage("***Pong!***"
							+ "\n:heart: " + "Ping: " + App.jdaBot.getPing() + "."
							+ "\n:stopwatch: " + "Connection Delay: " + App.jdaBot.getMaxReconnectDelay() + "."
							+ "\n:alarm_clock: " + "Status: " + App.jdaBot.getStatus() + ".").queue();

				}

				else
				{

					channel.sendMessage("***Pong!***"
							+ "\n:heart: " + "Ping: " + App.jdaBot.getPing() + "."
							+ "\n:stopwatch: " + "Connection Delay: " + App.jdaBot.getMaxReconnectDelay() + "."
							+ "\n:alarm_clock " + "Status: " + "DISCONNECTED.").queue();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%totalmsgs"))
			{

				channel.sendMessage("We have sent " + App.totalMessages + " " + "messages " + msg.getAuthor().getName() + ".").queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%mymsgs"))
			{

				if(App.saveUsers.containsKey(user.getId()))
				{

					channel.sendMessage("You have sent " + Integer.toString(App.saveUsers.get(user.getId())) + " messages " + user.getAsMention()).queue();

					MethodsHandler.saveMessageConfig();

				}

				if(!App.saveUsers.containsKey(user.getId()))
				{

					channel.sendMessage("You have sent 0 messages " + user.getAsMention()).queue();

					MethodsHandler.saveMessageConfig();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%dice"))
			{

				if(!user.isBot())
				{
					int random = 1 + (int) (Math.random() * 6);

					channel.sendMessage("You rolled a " + random + " " + user.getAsMention()).queue();

				}
			}

			if(msg.getContentRaw().startsWith("%flip"))
			{

				if(!user.isBot())
				{
					String[] flipCmd = msg.getContentRaw().split(" ");
					int heads = 0;
					int tails = 1;
					int random = (int) (Math.random() * 2);

					if(flipCmd.length >= 3 && MethodsHandler.isInteger(flipCmd[2]) && !MethodsHandler.isInteger(flipCmd[1]))
					{

						String choice = flipCmd[1];
						int wager = Integer.parseInt(flipCmd[2]);

						if(KbzTokens.Tokens.get(user.getId()) < wager)
						{

							EmbedBuilder builder = new EmbedBuilder();

							builder.setColor(Color.RED).setDescription("You do not have enough tokens to make that wager!");

							channel.sendMessage(builder.build()).queue();

						}

						if(random == heads && choice.toLowerCase().contains("heads") && wager > 0 && wager <= 50 && KbzTokens.Tokens.get(user.getId()) >= wager)
						{

							channel.sendMessage("**Flipping...**").queue();
							channel.sendMessage("You chose " + choice + " and heads was flipped!"
									+ "\n You won " + wager + " Kbz Tokens!" + user.getAsMention()).queueAfter(1, TimeUnit.SECONDS);

							KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager);

							MethodsHandler.saveTokenConfig();

						}

						if(random == tails && choice.toLowerCase().contains("tails") && wager > 0 && wager <= 50 && KbzTokens.Tokens.get(user.getId()) >= wager)
						{

							channel.sendMessage("**Flipping...**").queue();
							channel.sendMessage("You chose " + choice + " and tails was flipped!"
									+ "\n You won " + wager + " Kbz Tokens!" + user.getAsMention()).queueAfter(1, TimeUnit.SECONDS);

							KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager);

							MethodsHandler.saveTokenConfig();

						}

						if(random == heads && choice.toLowerCase().contains("tails") && wager > 0 && wager <= 50 && KbzTokens.Tokens.get(user.getId()) >= wager)
						{

							channel.sendMessage("**Flipping...**").queue();
							channel.sendMessage("Sorry, you chose " + choice + " but heads was flipped!"
									+ "\n You lost " + wager + " Kbz Tokens!" + user.getAsMention()).queueAfter(1, TimeUnit.SECONDS);

							KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) - wager);

							MethodsHandler.saveTokenConfig();

						}

						if(random == tails && choice.toLowerCase().contains("heads") && wager > 0 && wager <= 50 && KbzTokens.Tokens.get(user.getId()) >= wager)
						{

							channel.sendMessage("**Flipping...**").queue();
							channel.sendMessage("Sorry, you chose " + choice + " but tails was flipped!"
									+ "\n You lost " + wager + " Kbz Tokens!" + user.getAsMention()).queueAfter(1, TimeUnit.SECONDS);

							KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) - wager);

							MethodsHandler.saveTokenConfig();

						}

						if(wager <= 0 || wager > 50)
						{

							EmbedBuilder builder = new EmbedBuilder();

							builder.setColor(Color.RED).setDescription("Please wager an amount between 1 and 50!");

							channel.sendMessage(builder.build()).queue();

						}
					}

					else
					{

						EmbedBuilder builder = new EmbedBuilder();

						builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
								+ "\n %flip <heads/tails> <wager>");

						channel.sendMessage(builder.build()).queue();

					}
				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%lenny") && App.isOnline)
			{

				if(!user.isBot())
				{

					int random = 1 + (int) (Math.random() * 6);

					switch (random)
					{

						case 1:
							channel.sendMessage("̿̿ ̿̿ ̿̿ ̿'̿'̵͇̿з= ( ▀ ͜͞ʖ▀) =ε/̵͇̿/’̿’̿ ̿ ̿̿ ̿̿ ̿̿").queue();
							break;

						case 2:
							channel.sendMessage("(ง ͡°╭͜ʖ╮ ͡°)ง").queue();
							break;

						case 3:
							channel.sendMessage("(╭☞ ꗞ Ѡ ꗞ )╭☞").queue();
							break;

						case 4:
							channel.sendMessage("ლ(☼v☼ლ)").queue();
							break;

						case 5:
							channel.sendMessage("ᕙ( > ﹏ > )ᕗ").queue();
							break;

						case 6:
							channel.sendMessage("(づ ಠ ヮ ಠ )づ").queue();
							break;

					}
				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%lb"))
			{

				if(!user.isBot())
				{

					StringBuilder sb = new StringBuilder();

					Map <String, Integer> topTen = App.saveUsers.entrySet()
							.stream()
							.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
							.limit(10).
									collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

					Set <Entry <String, Integer>> entries = topTen.entrySet();
					int counter = 1;

					for (Entry <String, Integer> ent : entries)
					{

						sb.append("**#" + counter + "**" + " : " + App.jdaBot.getUserById(ent.getKey()).getName() + " " + ent.getValue() + "\n");
						counter++;

					}

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.PINK).setDescription(
							"__Leaderboard: Top 10 Message Senders!__"
									+ "\n\n"
									+ sb.toString());

					channel.sendMessage(builder.build()).queue();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%tokens"))
			{

				if(KbzTokens.Tokens.containsKey(user.getId()))
				{

					channel.sendMessage("You have " + Integer.toString(KbzTokens.Tokens.get(user.getId())) + " Kbz tokens! " + user.getAsMention()).queue();

				}

				if(!KbzTokens.Tokens.containsKey(user.getId()))
				{

					channel.sendMessage("You have 0 Kbz Tokens " + user.getAsMention()).queue();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%server-info"))
			{

				Channel generalChannel = guild.getTextChannelById("336291415908679690");

				channel.sendMessage("This Discord was created on: " + generalChannel.getCreationTime()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2info"))
			{

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.BLUE).setDescription("***A guide on how Random2v2 works!***"
						+ "\n"
						+ "\n```http"
						+ "\n Welcome to random2v2s!"
						+ "\n"
						+ "```"
						+ "\n"
						+ "*__Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %random2v2add <name>/<names>: Adds player(s) to the random list."
						+ "\n"
						+ "\n- %random2v2remove <name>/<names>: Removes player(s) from the random list."
						+ "\n"
						+ "\n- %random2v2randomize: Shuffles the currently entered players to form teams."
						+ "\n"
						+ "\n- %random2v2list: Displays a list of all the currently entered players."
						+ "\n- %random2v2listclear: Clears the random 2v2 list."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "*__Important Info__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\nYou can shortcut the random2v2 commands by typing multiple players seperated by commas."
						+ "\n\nEx: %random2v2add Kbz, SmellyDoe, Smellypizza, Cashboys"
						+ "\n\n!Remember in order for everyone to get into a game there has to be 4, 8, or 12 players in the queue!"
						+ "\n"
						+ "\n```"
				);

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2add") && msg.getContentRaw().length() == 13)
			{

				channel.sendMessage("Missing arguments!"
						+
						"\nCorrect command format: %random2v2add <name>/<names>.").queue();
			}

			if(msg.getContentRaw().startsWith("%random2v2add") && msg.getContentRaw().length() > 13)
			{

				String[] playersEntered = msg.getContentRaw().replace("%random2v2add", "").split(",", 8);

				String playersEnteredString = String.join(",", playersEntered);

				if(playersEntered.length == 1 && listOfEnteredPlayers.size() < 12)
				{

					String enteredPlayer = playersEntered[0];

					if(!listOfEnteredPlayers.contains(enteredPlayer))
					{

						listOfEnteredPlayers.add(enteredPlayer);
						listToRandom.add(enteredPlayer);

						channel.sendMessage(enteredPlayer + " has been added to the random2v2 list!").queue();

					}

					else
					{

						channel.sendMessage(enteredPlayer + " is already on the random2v2 list!").queue();

					}
				}

				if(listOfEnteredPlayers.size() < 12 && playersEntered.length > 1)
				{

					String enteredPlayer = "";

					for (int i = 0; i < playersEntered.length; i++)
					{

						enteredPlayer = playersEntered[i];

						listOfEnteredPlayers.add(enteredPlayer);
						listToRandom.add(enteredPlayer);

					}

					channel.sendMessage(playersEnteredString
							.replace("[", "")
							.replace("]", "")
							+ " have been added to the random2v2 list!").queue();

				}

				if(listOfEnteredPlayers.size() > 12)
				{

					channel.sendMessage("Couldn't add player(s) to list! There are too many players queued up!").queue();

					listOfEnteredPlayers.remove(listOfEnteredPlayers.size());

				}

				if(listOfEnteredPlayers.size() == 12)
				{

					channel.sendMessage("The list is full, no more players can be added!").queue();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2remove") && msg.getContentRaw().length() == 16)
			{

				channel.sendMessage("Missing arguments!"
						+
						"\nCorrect command format: %random2v2remove <name>/<names>.").queue();

			}

			if(msg.getContentRaw().startsWith("%random2v2remove") && msg.getContentRaw().length() > 16)
			{

				String[] playersEntered = msg.getContentRaw().replace("%random2v2remove", "").split(",", 8);

				String playersEnteredString = String.join(",", playersEntered);

				if(playersEntered.length == 1)
				{

					String enteredPlayer = playersEntered[0];

					if(listOfEnteredPlayers.contains(enteredPlayer))
					{

						listOfEnteredPlayers.remove(enteredPlayer);
						listToRandom.remove(enteredPlayer);

						channel.sendMessage(enteredPlayer + " has been removed from the random2v2 list!").queue();
					}

					else
					{

						channel.sendMessage(enteredPlayer + " is not on the random2v2 list!").queue();

					}
				}

				if(listOfEnteredPlayers.size() <= 12 && playersEntered.length > 1)
				{

					String enteredPlayer = "";

					for (int i = 0; i < playersEntered.length; i++)
					{

						enteredPlayer = playersEntered[i];

						listOfEnteredPlayers.remove(enteredPlayer);
						listToRandom.remove(enteredPlayer);

					}

					channel.sendMessage(playersEnteredString + " have been removed from the random2v2 list!").queue();

				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2list"))
			{

				String listToStringEnteredPlayers = String.join(", ", listOfEnteredPlayers);

				channel.sendMessage("Currently added players:" + listToStringEnteredPlayers).queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2listclear"))
			{

				listOfEnteredPlayers.clear();
				listToRandom.clear();

				channel.sendMessage("Random 2v2 list has been cleared!").queue();

			}

			if(msg.getContentRaw().equalsIgnoreCase("%random2v2randomize"))
			{

				ArenaRandomizeHandler.runRandomize(e);

			}

			if(msg.getContentRaw().equalsIgnoreCase("%someone"))
			{

				Random ran = new Random();

				int max = MethodsHandler.getMembers().size();
				Member randomMember = MethodsHandler.getMembers().get(ran.nextInt(max));

				if(!someoneCD.containsKey(user.getId()))
				{

					channel.sendMessage(randomMember.getAsMention()).queue();

					someoneCD.put(user.getId(), LocalDateTime.now());

				}

				if(someoneCD.containsKey(user.getId()))
				{

					if(someoneCD.get(user.getId()).plusMinutes(10).isBefore(LocalDateTime.now()))
					{

						someoneCD.remove(user.getId());

					}

					else
					{

						channel.sendMessage("You are currently on cooldown for 10 minutes " + user.getAsMention() + "!").queue();

						someoneCD.put(user.getId(), LocalDateTime.now());

					}
				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%abplayers"))
			{

				if(!playerCountCD.containsKey(user.getId()))
				{

					try
					{

						channel.sendMessage("Currently " + MethodsHandler.GETArenaRequest() + " people playing Arena!").queue();

						playerCountCD.put(user.getId(), LocalDateTime.now());

					}
					catch (IOException e1)
					{

						e1.printStackTrace();

					}
				}

				if(playerCountCD.containsKey(user.getId()))
				{

					if(playerCountCD.get(user.getId()).plusMinutes(1).isBefore(LocalDateTime.now()))
					{

						playerCountCD.remove(user.getId());

					}

					else
					{


						channel.sendMessage("You are currently on cooldown for 1 minute " + user.getAsMention() + "!").queue();

						playerCountCD.put(user.getId(), LocalDateTime.now());

					}
				}
			}

			if(msg.getContentRaw().equalsIgnoreCase("%flaggame"))
			{

				FlagGameHandler.startFlagGame(e);
				FlagGameHandler.runFlagGame(e);

			}
		}
	}
}
