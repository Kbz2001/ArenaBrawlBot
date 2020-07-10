package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.ArenaRandomizeHandler;
import ArenaBot.Handlers.FlagGameHandler;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class UserCommands implements MessageCreateListener {

	public static ArrayList<String> listOfEnteredPlayers = new ArrayList<String>();
	public static ArrayList<String> listToRandom = new ArrayList<String>();
	private static HashMap<String, LocalDateTime> someoneCD = new HashMap<>();

	@Override
	public void onMessageCreate(MessageCreateEvent e) {

		Server srv = e.getServer().get();
		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		MessageAuthor author = e.getMessageAuthor();
		User user = author.asUser().get();

		if (msg.getContent().startsWith("%") && author.isBotUser()) {

			MethodsHandler.sendBotPermissionErrorMessage(tChannel);

		} else {

			if (msg.getContent().equalsIgnoreCase("%help")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("***Hello there I am the Arena Discord Bot!***")
								.setDescription(
										"\n"
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
												+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%help utility")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__Utility Commands__**")
								.setDescription(
										"\n"
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
												+ "\n%settokens: Sets the specified user's token count."
												+ "\n"
												+ "\n%warn: Warns a user on the Discord via the Bot in PMs."
												+ "\n"
												+ "\n%cmute: Mutes the entire channel."
												+ "\n"
												+ "\n%cunmute: Unmutes the entire channel."
												+ "\n"
												+ "\n```"
												+ "\n"
												+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%help fungames")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__Fun and Games__**")
								.setDescription(
										"\n"
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
												+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%help message")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__Message Commands__**")
								.setDescription(
										"\n"
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
												+ "\n%allmsgreset: Resets all personal message counts to 0."
												+ "\n"
												+ "\n%msgreset: Resets the specified user's message count."
												+ "\n"
												+ "\n%settotalmsgs: Sets the total message count."
												+ "\n"
												+ "\n%setmsgs: Sets the specified user's message count."
												+ "\n"
												+ "\n%purge: Deletes a specific number messages in the text channel."
												+ "\n"
												+ "\n```"
												+ "\n"
												+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%help bot")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__Bot Commands__**")
								.setDescription(
										"\n"
												+ "\n"
												+ "```css"
												+ "\n"
												+ "\n%toggleonline: Brings me into Online/Offline mode."
												+ "\n"
												+ "\n%shutdown: Shuts me down."
												+ "\n"
												+ "\n%announce: Uses the bot to send an announcement."
												+ "\n"
												+ "\n%quote: Uses the bot to send a quote."
												+ "\n"
												+ "\n%setgame: Sets the Bot's status."
												+ "\n"
												+ "\n```"
												+ "\n"
												+ "\nThis bot was created by Kbz with help from P0ke, Retopia & TheMadMillenium!")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%playlist")) {

				tChannel.sendMessage("https://www.youtube.com/playlist?list=PLY1FiABbZmulmquZXiawOFPdptNsn-Sjd"
						+ "\n"
						+ "https://www.youtube.com/playlist?list=PLY1FiABbZmunI5jR2n9Ysja5CFbUS_lvx");

			}

			if (msg.getContent().equalsIgnoreCase("%ping")) {


				Instant msgCreated = msg.getCreationTimestamp();
				Date date1 = Date.from(msgCreated);
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(date1);
				float secs1 = calendar1.get(Calendar.MILLISECOND);

				try {
					tChannel.sendMessage("***Retrieving Data...***").thenAccept(pongmsg -> {

						Instant pongCreated = pongmsg.getCreationTimestamp();
						Date date2 = Date.from(pongCreated);
						Calendar calendar2 = Calendar.getInstance();
						calendar2.setTime(date2);
						float secs2 = calendar2.get(Calendar.MILLISECOND);

						pongmsg.edit("***Pong!***"
								+ "\n:heart: " + "Ping: " + (secs2 - secs1) + "."
								+ "\n:stopwatch: " + "Connection Delay: " + App.api.getReconnectDelay(900) + "."
								+ "\n:alarm_clock: " + "Status: " + App.api.getStatus() + ".");

					}).get(1, TimeUnit.SECONDS);
				} catch (InterruptedException | ExecutionException | TimeoutException ex) {
					ex.printStackTrace();
				}

			}

			if (msg.getContent().equalsIgnoreCase("%totalmsgs")) {

				tChannel.sendMessage("We have sent " + App.totalMessages + " " + "messages " + msg.getAuthor().getName() + ".");

			}

			if (msg.getContent().equalsIgnoreCase("%mymsgs")) {

				if (App.saveUsers.containsKey(user.getIdAsString())) {

					tChannel.sendMessage("You have sent " + App.saveUsers.get(user.getIdAsString()) + " messages " + user.getMentionTag());
					MethodsHandler.saveUserMessageConfig();

				} else {

					tChannel.sendMessage("You have sent 0 messages " + user.getMentionTag());
					MethodsHandler.saveUserMessageConfig();

				}
			}

			if (msg.getContent().equalsIgnoreCase("%dice")) {

				int random = 1 + (int) (Math.random() * 6);

				tChannel.sendMessage("You rolled a " + random + " " + user.getMentionTag());

			}

			if (msg.getContent().toLowerCase().startsWith("%flip")) {

				String[] flipCmd = msg.getContent().split(" ");
				int heads = 0;
				int tails = 1;
				int random = (int) (Math.random() * 2);

				if (flipCmd.length != 3 || MethodsHandler.isInteger(flipCmd[2]) || !MethodsHandler.isInteger(flipCmd[1])) {

					MessageBuilder builder = new MessageBuilder()
							.setEmbed(new EmbedBuilder()
									.setTitle("**Incorrect command format.**")
									.setDescription("Please used the correct command format " + user.getMentionTag()
											+ "\n %flip <heads/tails> <wager>")
									.setColor(Color.RED));

					builder.send(tChannel);

				} else {

					String choice = flipCmd[1];
					int wager = Integer.parseInt(flipCmd[2]);

					if (wager <= 0 || wager > 50) {

						MessageBuilder builder = new MessageBuilder()
								.setEmbed(new EmbedBuilder()
										.setTitle("**Invalid Wager**")
										.setDescription("Please wager an amount between 1 and 50 " + user.getMentionTag() + ".")
										.setColor(Color.RED));

						builder.send(tChannel);

					}

					if (KbzTokens.Tokens.get(user.getIdAsString()) < wager) {

						MessageBuilder builder = new MessageBuilder()
								.setEmbed(new EmbedBuilder()
										.setTitle("**Invalid Wager**")
										.setDescription("You do not have enough Kbz Tokens to make this wager " + user.getMentionTag() + ".")
										.setColor(Color.RED));

						builder.send(tChannel);

					} else {

						if ((random == heads && choice.toLowerCase().contains("heads")) || (random == tails && choice.toLowerCase().contains("tails"))) {

							tChannel.sendMessage("**Flipping...**").thenAccept(flipmsg -> {
								try {

									String flipped = "";

									if (random == heads) {

										flipped = "heads";

									} else {

										flipped = "tails";

									}

									flipmsg.edit("You chose " + choice + " and" + flipped + "was flipped!"

											+ "\n You won " + wager + " Kbz Tokens!" + user.getMentionTag()).get(1, TimeUnit.SECONDS);

									MethodsHandler.saveTokenConfig();

								} catch (InterruptedException | ExecutionException | TimeoutException ex) {
								}

							});
						}

						if ((random == heads && choice.toLowerCase().contains("tails")) || (random == tails && choice.toLowerCase().contains("heads"))) {

							tChannel.sendMessage("**Flipping...**").thenAccept(flipmsg -> {
								try {

									String flipped = "";

									if (random != heads) {

										flipped = "tails";

									} else {

										flipped = "heads";

									}

									flipmsg.edit("Sorry! You chose " + choice + " but" + flipped + "was flipped!"

											+ "\n You lost " + wager + " Kbz Tokens!" + user.getMentionTag()).get(1, TimeUnit.SECONDS);

									MethodsHandler.saveTokenConfig();

								} catch (InterruptedException | ExecutionException | TimeoutException ex) {
								}

							});
						}
					}
				}
			}

			if (msg.getContent().equalsIgnoreCase("%lenny")) {

				int random = 1 + (int) (Math.random() * 6);

				switch (random) {

					case 1:
						tChannel.sendMessage("̿̿ ̿̿ ̿̿ ̿'̿'̵͇̿з= ( ▀ ͜͞ʖ▀) =ε/̵͇̿/’̿’̿ ̿ ̿̿ ̿̿ ̿̿");
						break;

					case 2:
						tChannel.sendMessage("(ง ͡°╭͜ʖ╮ ͡°)ง");
						break;

					case 3:
						tChannel.sendMessage("(╭☞ ꗞ Ѡ ꗞ )╭☞");
						break;

					case 4:
						tChannel.sendMessage("ლ(☼v☼ლ)");
						break;

					case 5:
						tChannel.sendMessage("ᕙ( > ﹏ > )ᕗ");
						break;

					case 6:
						tChannel.sendMessage("(づ ಠ ヮ ಠ )づ");
						break;

				}
			}

			if (msg.getContent().equalsIgnoreCase("%lb")) {

				StringBuilder sb = new StringBuilder();

				Map<String, Integer> topTen = App.saveUsers.entrySet()
						.stream()
						.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						.limit(10).
								collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

				Set<Entry<String, Integer>> entries = topTen.entrySet();
				int counter = 1;

				for (Entry<String, Integer> ent : entries) {

					try {

						sb.append("**#" + counter + "**" + " : " + App.api.getUserById(ent.getKey()).get().getName() + " " + ent.getValue() + "\n");

					} catch (InterruptedException | ExecutionException ex) {

						ex.printStackTrace();

					}

					counter++;

				}

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__Message Leaderboard__**")
								.setDescription(sb.toString())
								.setColor(Color.PINK));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%tokens")) {

				if (KbzTokens.Tokens.containsKey(user.getIdAsString())) {

					tChannel.sendMessage("You have " + Integer.toString(KbzTokens.Tokens.get(user.getIdAsString())) + " Kbz tokens! " + user.getMentionTag());

				}

				if (!KbzTokens.Tokens.containsKey(user.getIdAsString())) {

					tChannel.sendMessage("You have 0 Kbz Tokens " + user.getMentionTag());

				}
			}

			if (msg.getContent().equalsIgnoreCase("%server-info")) {

				tChannel.sendMessage("-> The Acronym for this Discord is **ABCD**!"
						+ "\n"
						+ "-> This Discord was created on: " + "**2017-7-16**!"
						+ "\n"
						+ "-> There are currently **" + srv.getMembers().size() + "** users in this Discord!");

			}

			if (msg.getContent().equalsIgnoreCase("%random2v2info")) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("\n**__A Guide on how Random2v2 Works!__**")
								.setDescription(
										"\n"
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
												+ "\n```")
								.setColor(Color.BLUE));

				builder.send(tChannel);

			}

			if (msg.getContent().equalsIgnoreCase("%random2v2add")) {

				if (msg.getContent().length() == 13) {

					MessageBuilder builder = new MessageBuilder()
							.setEmbed(new EmbedBuilder()
									.setTitle("\n**__Missing Arguments__**")
									.setDescription("\nCorrect command format: %random2v2add <name>/<names>.")
									.setColor(Color.PINK));

					builder.send(tChannel);

				} else {

					String[] playersEntered = msg.getContent().replace("%random2v2add", "").split(",", 8);
					String playersEnteredString = String.join(",", playersEntered);

					if (playersEntered.length == 1 && listOfEnteredPlayers.size() < 12) {

						String enteredPlayer = playersEntered[0];

						if (!listOfEnteredPlayers.contains(enteredPlayer)) {

							listOfEnteredPlayers.add(enteredPlayer);
							listToRandom.add(enteredPlayer);

							tChannel.sendMessage(enteredPlayer + " has been added to the random2v2 list!");

						} else {

							tChannel.sendMessage(enteredPlayer + " is already on the random2v2 list!");

						}
					}

					if (listOfEnteredPlayers.size() < 12 && playersEntered.length > 1) {

						String enteredPlayer = "";

						for (String s : playersEntered) {

							enteredPlayer = s;

							listOfEnteredPlayers.add(enteredPlayer);
							listToRandom.add(enteredPlayer);

						}

						tChannel.sendMessage(playersEnteredString
								.replace("[", "")
								.replace("]", "")
								+ " have been added to the random2v2 list!");

					}

					if (listOfEnteredPlayers.size() < 12 && playersEntered.length > 1) {

						String enteredPlayer = "";

						for (String s : playersEntered) {

							enteredPlayer = s;

							listOfEnteredPlayers.add(enteredPlayer);
							listToRandom.add(enteredPlayer);

						}

						tChannel.sendMessage(playersEnteredString
								.replace("[", "")
								.replace("]", "")
								+ " have been added to the random2v2 list!");

					}

					if (listOfEnteredPlayers.size() > 12) {

						tChannel.sendMessage("Couldn't add player(s) to list! There are too many players queued up!");

						listOfEnteredPlayers.remove(listOfEnteredPlayers.size());

					}

					if (listOfEnteredPlayers.size() == 12) {

						tChannel.sendMessage("The list is full, no more players can be added!");

					}
				}
			}

			if (msg.getContent().equalsIgnoreCase("%random2v2remove")) {

				if (msg.getContent().length() == 16) {

					MessageBuilder builder = new MessageBuilder()
							.setEmbed(new EmbedBuilder()
									.setTitle("\n**__Missing Arguments__**")
									.setDescription("\nCorrect command format: %random2v2remove <name>/<names>.")
									.setColor(Color.PINK));

					builder.send(tChannel);

				} else {

					String[] playersEntered = msg.getContent().replace("%random2v2remove", "").split(",", 8);
					String playersEnteredString = String.join(",", playersEntered);

					if (playersEntered.length == 1) {

						String enteredPlayer = playersEntered[0];

						if (listOfEnteredPlayers.contains(enteredPlayer)) {

							listOfEnteredPlayers.remove(enteredPlayer);
							listToRandom.remove(enteredPlayer);

							tChannel.sendMessage(enteredPlayer + " has been removed from the random2v2 list!");
						} else {

							tChannel.sendMessage(enteredPlayer + " is not on the random2v2 list!");

						}
					}

					if (listOfEnteredPlayers.size() <= 12 && playersEntered.length > 1) {

						String enteredPlayer = "";

						for (String s : playersEntered) {

							enteredPlayer = s;

							listOfEnteredPlayers.remove(enteredPlayer);
							listToRandom.remove(enteredPlayer);

						}

						tChannel.sendMessage(playersEnteredString + " have been removed from the random2v2 list!");

					}
				}
			}

			if (msg.getContent().equalsIgnoreCase("%random2v2list")) {

				String listToStringEnteredPlayers = String.join(", ", listOfEnteredPlayers);

				tChannel.sendMessage("Currently added players:" + listToStringEnteredPlayers);

			}

			if (msg.getContent().equalsIgnoreCase("%random2v2listclear")) {

				listOfEnteredPlayers.clear();
				listToRandom.clear();

				tChannel.sendMessage("Random 2v2 list has been cleared!");

			}

			if (msg.getContent().equalsIgnoreCase("%random2v2randomize")) {

				ArenaRandomizeHandler.runRandomize(e);

			}

			if (msg.getContent().equalsIgnoreCase("%someone")) {

				Random ran = new Random();

				int max = MethodsHandler.getMembers().size();

				User randomMember = MethodsHandler.getMembers().get(ran.nextInt(max));

				try {
					if (new SimpleDateFormat("MMdd").parse("0401").equals(new Date())) {
						if (!someoneCD.containsKey(user.getIdAsString())) {

							tChannel.sendMessage(randomMember.getMentionTag());

							someoneCD.put(user.getIdAsString(), LocalDateTime.now());

						}

						if (someoneCD.containsKey(user.getIdAsString())) {

							if (someoneCD.get(user.getIdAsString()).plusMinutes(10).isBefore(LocalDateTime.now())) {

								someoneCD.remove(user.getIdAsString());

								tChannel.sendMessage(randomMember.getMentionTag());

							} else {

								tChannel.sendMessage("You are currently on cooldown for 10 minutes " + user.getMentionTag() + "!");

							}
							someoneCD.put(user.getIdAsString(), LocalDateTime.now());
						}
					}
					else{
						tChannel.sendMessage("It is not April Fools! The command is currently disabled!");
					}
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
			}

			if (msg.getContent().equalsIgnoreCase("%abplayers")) {

				try {

					tChannel.sendMessage("Currently " + MethodsHandler.GETArenaRequest() + " people playing Arena!");

				} catch (IOException e1) {

					e1.printStackTrace();

				}
			}

			if (msg.getContent().startsWith("%flaggame")) {

				TextChannel botCmdChannel = App.api.getTextChannelById("352674100176486410").get();

				if (!tChannel.getIdAsString().equals(botCmdChannel.getIdAsString())) {

					tChannel.sendMessage("That command can only be done in " + "<#" + botCmdChannel.getIdAsString() + ">");

				} else {

					String[] flagCmd = msg.getContent().split(" ");

					if (flagCmd.length == 1) {

						MessageBuilder builder = new MessageBuilder()
								.setEmbed(new EmbedBuilder()
										.setTitle("\n**__Missing Arguments__**")
										.setDescription("\nCorrect command format: %flaggame <Start/Stop/Answers>")
										.setColor(Color.RED));

						builder.send(tChannel);

					} else {

						if (flagCmd[1].equalsIgnoreCase("start")) {

							if (!FlagGameHandler.isRunning) {

								FlagGameHandler.startFlagGame(e);

								try {

									FlagGameHandler.runFlagGame(e);


								} catch (InterruptedException | ExecutionException ex) {

									ex.printStackTrace();

								}

							} else {

								tChannel.sendMessage("A Game is currently in session " + user.getMentionTag() + "!");

							}
						}

						if (flagCmd[1].equalsIgnoreCase("stop")) {

							if (FlagGameHandler.isRunning) {

								FlagGameHandler.stopFlagGame(e);

							} else {

								tChannel.sendMessage("There is currently no game running " + user.getMentionTag() + "!");

							}
						}

						if (flagCmd[1].equalsIgnoreCase("answers")) {

							if (FlagGameHandler.isRunning) {

								FlagGameHandler.flagGameAnswers(e);

								FlagGameHandler.stopFlagGame(e);

							} else {

								tChannel.sendMessage("There are no answers to display!");

							}
						}
					}
				}
			}
		}
	}
}
