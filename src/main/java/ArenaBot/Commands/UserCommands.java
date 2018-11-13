package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UserCommands extends ListenerAdapter
{

    public static ArrayList<String> listOfEnteredPlayers = new ArrayList <String>();
    public static ArrayList<String> listToRandom = new ArrayList <String>();
    public static HashMap<String, LocalDateTime> someoneCD = new HashMap<String, LocalDateTime>();

	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
	
		Message msg = e.getMessage();
    	MessageChannel channel = e.getChannel();
    	User user = e.getAuthor();
    	Guild guild = e.getGuild();

    	if(App.isOnline && !user.isBot())
    	{

    		if(msg.getRawContent().equalsIgnoreCase("%help"))
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
						+ "**__User Commands__**"
						+ "\n"
						+ "\n*__Utility Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %help: Shows a list of commands."
						+ "\n- %playlist: Give you the links to all of the music playlists."
						+ "\n- %ping: Tells you my ping."
						+ "\n- %tokens: Tells you how many Kbz Tokens you have."
						+ "\n- %server-info: Tells you the creation date of the Discord."
						+ "\n- %random2v2info: A guide on how to use the random2v2 command."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "*__Fun and Games__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %flip: Flips a coin. Heads or Tails. Bet x amount(Between 1 and 50). If you win you earn x, if you lose, you lose x. Good luck!"
						+ "\n- %dice: Rolls a dice. Numbers are between 1 and 6."
						+ "\n- %lenny: Gives you a random lenny face!"
						+ "\n- %slots: Gamble away your Tokens with this command! For more info do %slotinfo."
						+ "\n- %slotinfo: A guide on how to use the slot command."
						+ "\n- %someone: Mentions a random person in the Discord!"
						+ "\n```"
						+ "\n"
						+ "*__Message Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %totalmsgs: Shows you the total amount of messages we have sent."
						+ "\n- %mymsgs: Shows you the total messages you have sent."
						+ "\n- %lb: Displays the top 10 message senders."
						+ "\n```"
						+ "\n"
						+ "**__Admin Commands__**"
						+ "\n"
						+ "\n"
						+ "\n*__Utility Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %addtokens: Gives the specified numbers of tokens to the given player."
						+ "\n- %tokenreset: Resets the specified user's token count."
						+ "\n- %alltokenreset: Resets all personal Kbz Token counts to 0."
						+ "\n```"
						+ "\n"
						+ "*__Bot Commands__*"
						+ "\n"
						+ "\n"
						+ "```css"
						+ "\n"
						+ "\n- %toggleonline: Brings me into Online/Offline mode."
						+ "\n- %shutdown: Shuts me down."
						+ "\n"
						+ "\n```"
						+ "\n"
						+ "*__Message Commands__*"
						+ "\n"
						+ "\n```css"
						+ "\n"
						+ "\n- %totalreset: Resets the current number of messages to 0."
						+ "\n- %totaladd: Adds a certain number to the total message count."
						+ "\n- %totalsub: Subtracts a certain number to the total message count."
						+ "\n- %lbreset: Resets the current leaderboard."
						+ "\n- %alluserreset: Resets all personal message counts to 0."
						+ "\n- %userreset: Resets the specified user's message count."
						+ "\n- %purge: Deletes a specific number messages in the text channel."
						+ "```"
						+ "\n"
						+ "\n__Credits:__"
						+ "\n"
						+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!");

				channel.sendMessage(builder.build()).queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%playlist"))
			{

				channel.sendMessage(
						"https://www.youtube.com/playlist?list=PLY1FiABbZmulmquZXiawOFPdptNsn-Sjd"
								+ "\n"
								+ "https://www.youtube.com/playlist?list=PLY1FiABbZmunI5jR2n9Ysja5CFbUS_lvx"
				).queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%ping"))
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

			if(msg.getRawContent().equalsIgnoreCase("%totalmsgs"))
			{

				channel.sendMessage("We have sent " + App.totalMessages + " " + "messages " + msg.getAuthor().getName() + ".").queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%mymsgs"))
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

			if(msg.getRawContent().equalsIgnoreCase("%dice"))
			{

				if(!user.isBot())
				{
					int random = 1 + (int) (Math.random() * 6);

					channel.sendMessage("You rolled a " + random + " " + user.getAsMention()).queue();

				}
			}

			if(msg.getRawContent().startsWith("%flip"))
			{

				if(!user.isBot())
				{
					String[] flipCmd = msg.getRawContent().split(" ");
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

			if(msg.getRawContent().equalsIgnoreCase("%lenny") && App.isOnline)
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

			if(msg.getRawContent().equalsIgnoreCase("%lb"))
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

			if(msg.getRawContent().equalsIgnoreCase("%tokens"))
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

			if(msg.getRawContent().equalsIgnoreCase("%server-info"))
			{

				Channel generalChannel = guild.getTextChannelById("336291415908679690");

				channel.sendMessage("This Discord was created on: " + generalChannel.getCreationTime()).queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%random2v2info"))
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

			if(msg.getRawContent().equalsIgnoreCase("%random2v2add") && msg.getRawContent().length() == 13)
			{

				channel.sendMessage("Missing arguments!"
						+
						"\nCorrect command format: %random2v2add <name>/<names>.").queue();
			}

			if(msg.getRawContent().startsWith("%random2v2add") && msg.getRawContent().length() > 13)
			{

				String[] playersEntered = msg.getRawContent().replace("%random2v2add", "").split(",", 8);

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

			if(msg.getRawContent().equalsIgnoreCase("%random2v2remove") && msg.getRawContent().length() == 16)
			{

				channel.sendMessage("Missing arguments!"
						+
						"\nCorrect command format: %random2v2remove <name>/<names>.").queue();

			}

			if(msg.getRawContent().startsWith("%random2v2remove") && msg.getRawContent().length() > 16)
			{

				String[] playersEntered = msg.getRawContent().replace("%random2v2remove", "").split(",", 8);

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

			if(msg.getRawContent().equalsIgnoreCase("%random2v2list"))
			{

				String listToStringEnteredPlayers = String.join(", ", listOfEnteredPlayers);

				channel.sendMessage("Currently added players:" + listToStringEnteredPlayers).queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%random2v2listclear"))
			{

				listOfEnteredPlayers.clear();
				listToRandom.clear();

				channel.sendMessage("Random 2v2 list has been cleared!").queue();

			}

			if(msg.getRawContent().equalsIgnoreCase("%random2v2randomize"))
			{

				if(listOfEnteredPlayers.size() % 4 != 0 || listOfEnteredPlayers.size() == 0)
				{

					channel.sendMessage("I cannot randomize because I require 4, 8, or 12 players to function!").queue();

					return;

				}

				if(listToRandom.size() == 4)
				{

					Collections.shuffle(listToRandom);

					String team1 = "";
					String team2 = "";

					team1 = listToRandom.get(0) + " " + listToRandom.get(1);
					team2 = listToRandom.get(2) + " " + listToRandom.get(3);

					channel.sendMessage("*__Teams:__*"
							+ "\n"
							+ "**Team 1:** " + team1
							+ "\n"
							+ "**Team 2:** " + team2).queue();

				}

				if(listOfEnteredPlayers.size() == 8)
				{

					Collections.shuffle(listToRandom);

					String team1 = "";
					String team2 = "";
					String team3 = "";
					String team4 = "";

					team1 = listToRandom.get(0) + " " + listToRandom.get(1);
					team2 = listToRandom.get(2) + " " + listToRandom.get(3);
					team3 = listToRandom.get(4) + " " + listToRandom.get(5);
					team4 = listToRandom.get(6) + " " + listToRandom.get(7);


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

				if(listOfEnteredPlayers.size() == 12)
				{

					Collections.shuffle(listToRandom);

					String team1 = "";
					String team2 = "";
					String team3 = "";
					String team4 = "";
					String team5 = "";
					String team6 = "";

					team1 = listToRandom.get(0) + " " + listToRandom.get(1);
					team2 = listToRandom.get(2) + " " + listToRandom.get(3);
					team3 = listToRandom.get(4) + " " + listToRandom.get(5);
					team4 = listToRandom.get(6) + " " + listToRandom.get(7);
					team5 = listToRandom.get(8) + " " + listToRandom.get(9);
					team6 = listToRandom.get(10) + " " + listToRandom.get(11);

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

			if(msg.getRawContent().equalsIgnoreCase("%someone"))
			{

				if(!someoneCD.containsKey(user.getId()))
				{

					Random ran = new Random();

					int max = MethodsHandler.getMembers().size();
					Member randomMember = MethodsHandler.getMembers().get(ran.nextInt(max));

					channel.sendMessage(randomMember.getAsMention()).queue();

					someoneCD.put(user.getId(), LocalDateTime.now());

				}

				if(someoneCD.containsKey(user.getId()))
				{

					if(someoneCD.get(user.getId()).plusMinutes(1).isBefore(LocalDateTime.now()))
					{

						someoneCD.remove(user.getId());

					}

					else
					{

						someoneCD.put(user.getId(), LocalDateTime.now());

						channel.sendMessage("You are currently on cooldown " + user.getAsMention() + " for one minute!").queueAfter((long)0.75, TimeUnit.SECONDS);

					}
				}
			}
		}
	}
}
