package ArenaBot.Handlers;

import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Random;

public class RunSlotsHandler
{

	public static void runSlots(MessageReceivedEvent e)
	{

		Message msg = e.getMessage();
		MessageChannel channel = e.getChannel();
		User user = e.getAuthor();

		Random slotPic = new Random();

		String pic1 = "";
		String pic2 = "";
		String pic3 = "";
		String pic4 = "";
		String pic5 = "";
		String pic6 = "";
		String pic7 = "";
		String pic8 = "";
		String pic9 = "";

		String[] slotsCmd = msg.getContentRaw().split(" ");

		if (slotsCmd.length >= 2 && MethodsHandler.isInteger(slotsCmd[1])) {

			int wager = Integer.parseInt(slotsCmd[1]);

			if (wager < 0) {

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.RED).setDescription("Please wager a positive integer " + user.getAsMention()
						+ "!");

				channel.sendMessage(builder.build()).queue();

			}

			if (KbzTokens.Tokens.get(user.getId()) < wager) {

				EmbedBuilder builder = new EmbedBuilder();

				builder.setColor(Color.RED).setDescription("You do not have enough tokens to make that bet!");

				channel.sendMessage(builder.build()).queue();

			}

			if (KbzTokens.Tokens.get(user.getId()) >= wager) {

				int pos1 = slotPic.nextInt(6);
				int pos2 = slotPic.nextInt(6);
				int pos3 = slotPic.nextInt(6);
				int pos4 = slotPic.nextInt(6);
				int pos5 = slotPic.nextInt(6);
				int pos6 = slotPic.nextInt(6);
				int pos7 = slotPic.nextInt(6);
				int pos8 = slotPic.nextInt(6);
				int pos9 = slotPic.nextInt(6);

				switch (pos1) {

					case 0:
						pic1 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic1 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic1 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic1 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic1 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic1 = "<:Bug:346470020013883395>";
						break;


				}

				switch (pos2) {

					case 0:
						pic2 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic2 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic2 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic2 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic2 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic2 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos3) {

					case 0:
						pic3 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic3 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic3 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic3 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic3 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic3 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos4) {

					case 0:
						pic4 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic4 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic4 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic4 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic4 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic4 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos5) {

					case 0:
						pic5 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic5 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic5 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic5 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic5 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic5 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos6) {

					case 0:
						pic6 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic6 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic6 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic6 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic6 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic6 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos7) {

					case 0:
						pic7 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic7 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic7 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic7 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic7 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic7 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos8) {

					case 0:
						pic8 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic8 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic8 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic8 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic8 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic8 = "<:Bug:346470020013883395>";
						break;

				}

				switch (pos9) {

					case 0:
						pic9 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic9 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic9 = "<:HelperLapis:336621217752809473>";
						break;
					case 3:
						pic9 = "<:ModEmerald:336621225927245825>";
						break;
					case 4:
						pic9 = "<:AdminDiamond:336621232738795521>";
						break;
					case 5:
						pic9 = "<:Bug:346470020013883395>";
						break;

				}

				channel.sendMessage(
						":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag:\n\n"
								+
								":moneybag: " + pic1 + " " + pic2 + " " + pic3 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + pic4 + " " + pic5 + " " + pic6 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + pic7 + " " + pic8 + " " + pic9 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: "
				).queue();

				//Left Diagonal
				if (pic1 == pic5 && pic1 == pic9 && pic5 == pic9 && pic1 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Right Diagonal
				else if (pic3 == pic5 && pic3 == pic7 && pic5 == pic7 && pic3 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Top Row
				else if (pic1 == pic2 && pic1 == pic3 && pic2 == pic3 && pic1 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Middle Row
				else if (pic4 == pic5 && pic4 == pic6 && pic5 == pic6 && pic4 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Bottom Row
				else if (pic7 == pic8 && pic7 == pic9 && pic8 == pic9 && pic7 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Left Column
				else if (pic1 == pic4 && pic1 == pic7 && pic4 == pic7 && pic1 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Middle Column
				else if (pic2 == pic5 && pic2 == pic8 && pic5 == pic8 && pic2 != ":RAU:" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Right Column
				else if (pic3 == pic6 && pic3 == pic9 && pic6 == pic9 && pic3 != "<:RAU:481298032865050625>" && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 5);

				}

				//Left Diagonal
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic9 && pic5 == pic9 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Right Diagonal
				else if (pic3 == "<:RAU:481298032865050625>" && pic3 == pic7 && pic5 == pic7 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Top Row
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic3 && pic2 == pic3 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Middle Row
				else if (pic4 == "<:RAU:481298032865050625>" && pic4 == pic6 && pic5 == pic6 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Bottom Row
				else if (pic7 == "<:RAU:481298032865050625>" && pic7 == pic9 && pic8 == pic9 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Left Column
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic7 && pic4 == pic7 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Middle Column
				else if (pic2 == "<:RAU:481298032865050625>" && pic2 == pic8 && pic5 == pic8 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				}

				//Right Column
				else if (pic3 == "<:RAU:481298032865050625>" && pic3 == pic9 && pic6 == pic9 && wager > 0) {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.GREEN).setDescription("JACKPOT: You won " + wager * 10 + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) + wager * 10);

				} else {

					EmbedBuilder builder = new EmbedBuilder();

					builder.setColor(Color.RED).setDescription("Sorry, you lost " + wager + " Kbz Tokens! " + user.getAsMention());

					channel.sendMessage(builder.build()).queue();

					KbzTokens.Tokens.put(user.getId(), KbzTokens.Tokens.get(user.getId()) - wager);

				}
			}
		}

		if (slotsCmd.length <= 1) {

			EmbedBuilder builder = new EmbedBuilder();

			builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
					+ "\n %slots <wager>");

			channel.sendMessage(builder.build()).queue();

		}
	}
}
