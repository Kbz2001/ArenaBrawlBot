package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Currency.KbzTokens;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.Random;

public class RunSlotsHandler
{

	public static void runSlots(MessageCreateEvent e)
	{

		Message msg = e.getMessage();
		TextChannel tChannel = e.getChannel();
		MessageAuthor user = e.getMessageAuthor();

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

		String[] slotsCmd = msg.getContent().split(" ");

		if(!App.isOnline)
		{

			MethodsHandler.sendOfflineErrorMessage(tChannel);

		}

		if(msg.getContent().startsWith("%") && user.isBotUser())
		{

			MethodsHandler.sendBotPermissionErrorMessage(tChannel);

		}

		if (slotsCmd.length != 2 || !MethodsHandler.isInteger(slotsCmd[1]))
		{

			MessageBuilder builder = new MessageBuilder()
					.setEmbed(new EmbedBuilder()
					.setTitle("**Incorrect command format.**")
					.setDescription("Please used the correct command format " + user.asUser().map(User::getMentionTag).get()
					+ "\n %slots <wager>")
					.setColor(Color.RED));

			builder.send(tChannel);

		}

		else
		{
			int wager = Integer.parseInt(slotsCmd[1]);

			if (wager < 0) {

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("**Invalid Wager**")
								.setDescription("Please wager a positive integer " + user.asUser().map(User::getMentionTag).get() + "!")
								.setColor(Color.RED));

				builder.send(tChannel);

			}

			if (KbzTokens.Tokens.get(user.getIdAsString()) < wager)
			{

				MessageBuilder builder = new MessageBuilder()
						.setEmbed(new EmbedBuilder()
								.setTitle("**Invalid Wager**")
								.setDescription("You don't have enough tokens to make that bet " + user.asUser().map(User::getMentionTag).get() + "!")
								.setColor(Color.RED));

				builder.send(tChannel);

			}

			if (KbzTokens.Tokens.get(user.getIdAsString()) >= wager)
			{

				int pos1 = slotPic.nextInt(6);
				int pos2 = slotPic.nextInt(6);
				int pos3 = slotPic.nextInt(6);
				int pos4 = slotPic.nextInt(6);
				int pos5 = slotPic.nextInt(6);
				int pos6 = slotPic.nextInt(6);
				int pos7 = slotPic.nextInt(6);
				int pos8 = slotPic.nextInt(6);
				int pos9 = slotPic.nextInt(6);

				switch (pos1)
				{

					case 0:
						pic1 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic1 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic1 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic1 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic1 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic1 = "<:Bug:554448470409347114>";
						break;


				}

				switch (pos2)
				{

					case 0:
						pic2 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic2 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic2 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic2 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic2 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic2 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos3)
				{

					case 0:
						pic3 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic3 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic3 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic3 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic3 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic3 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos4)
				{

					case 0:
						pic4 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic4 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic4 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic4 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic4 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic4 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos5)
				{

					case 0:
						pic5 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic5 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic5 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic5 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic5 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic5 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos6)
				{

					case 0:
						pic6 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic6 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic6 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic6 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic6 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic6 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos7)
				{

					case 0:
						pic7 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic7 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic7 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic7 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic7 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic7 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos8)
				{

					case 0:
						pic8 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic8 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic8 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic8 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic8 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic8 = "<:Bug:554448470409347114>";
						break;

				}

				switch (pos9)
				{

					case 0:
						pic9 = "<:ArenaBrawl:481298350856077322>";
						break;
					case 1:
						pic9 = "<:RAU:481298032865050625>";
						break;
					case 2:
						pic9 = "<:Useful:554445582715060224>";
						break;
					case 3:
						pic9 = "<:Like:554444088816959497>";
						break;
					case 4:
						pic9 = "<:AdminDiamond:554448167400243200>";
						break;
					case 5:
						pic9 = "<:Bug:554448470409347114>";
						break;

				}

				tChannel.sendMessage(
						":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag:\n\n"
								+
								":moneybag: " + pic1 + " " + pic2 + " " + pic3 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + pic4 + " " + pic5 + " " + pic6 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + pic7 + " " + pic8 + " " + pic9 + " " + ":moneybag:\n\n"
								+
								":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: " + ":moneybag: ");

				//Left Diagonal
				if (pic1 == pic5 && pic1 == pic9 && pic5 == pic9 && pic1 != "<:RAU:481298032865050625>" && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Right Diagonal
				else if (pic3 == pic5 && pic3 == pic7 && pic5 == pic7 && pic3 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Top Row
				else if (pic1 == pic2 && pic1 == pic3 && pic2 == pic3 && pic1 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Middle Row
				else if (pic4 == pic5 && pic4 == pic6 && pic5 == pic6 && pic4 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Bottom Row
				else if (pic7 == pic8 && pic7 == pic9 && pic8 == pic9 && pic7 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Left Column
				else if (pic1 == pic4 && pic1 == pic7 && pic4 == pic7 && pic1 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Middle Column
				else if (pic2 == pic5 && pic2 == pic8 && pic5 == pic8 && pic2 != ":RAU:" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Right Column
				else if (pic3 == pic6 && pic3 == pic9 && pic6 == pic9 && pic3 != "<:RAU:481298032865050625>" && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageBasic(tChannel, wager, user);

				}

				//Left Diagonal
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic9 && pic5 == pic9 && wager > 0) 
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Right Diagonal
				else if (pic3 == "<:RAU:481298032865050625>" && pic3 == pic7 && pic5 == pic7 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Top Row
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic3 && pic2 == pic3 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Middle Row
				else if (pic4 == "<:RAU:481298032865050625>" && pic4 == pic6 && pic5 == pic6 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Bottom Row
				else if (pic7 == "<:RAU:481298032865050625>" && pic7 == pic9 && pic8 == pic9 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Left Column
				else if (pic1 == "<:RAU:481298032865050625>" && pic1 == pic7 && pic4 == pic7 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Middle Column
				else if (pic2 == "<:RAU:481298032865050625>" && pic2 == pic8 && pic5 == pic8 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}

				//Right Column
				else if (pic3 == "<:RAU:481298032865050625>" && pic3 == pic9 && pic6 == pic9 && wager > 0)
				{

					MethodsHandler.sendSlotsWinnerMessageJackpot(tChannel, wager, user);

				}
				else
				{

					MessageBuilder builder = new MessageBuilder()
							.setEmbed(new EmbedBuilder()
							.setTitle("Loser!")
							.setDescription("Sorry, you lost " + wager + " Kbz Tokens!" + user.asUser().map(User::getMentionTag).get())
							.setColor(Color.RED));

					builder.send(tChannel);

				}
			}
		}
	}
}
