package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Currency.KbzTokens;

import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MethodsHandler
{

	public static String part4 = "";

	public static String getToken()
	{

		//When pushing to GitHub, remove this file from pushing.
		
		return "[REDACTED]";

	}

	@SuppressWarnings("resource")
	public static String Read(File f)
	{

		String content = null;

		try
		{

			content = new Scanner(f).useDelimiter("\\Z").next();

		}

		catch (FileNotFoundException ex)
		{

			ex.printStackTrace();

		}

		return content;

	}

	public static void Write(File f, String content)
	{

		FileWriter fileWriter;

		try
		{

			fileWriter = new FileWriter(f);
			try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
			{

				bufferedWriter.write(content);

			}
		}

		catch (IOException ex)
		{

			ex.printStackTrace();

		}
	}

	public static boolean isInteger(String str) 
	{

		try 
		{

			@SuppressWarnings("unused")
			int i = Integer.parseInt(str);

		}

		catch (NumberFormatException nfe) 
		{

			return false;

		}

		return true;

	}

	public static void sendOfflineErrorMessage(TextChannel tChannel)
	{

		MessageBuilder builder = new MessageBuilder()
				.setEmbed(new EmbedBuilder()
						.setTitle("Command Failed.")
						.setDescription("Sorry I am currently **Offline** :(!")
						.setColor(Color.RED));

		builder.send(tChannel);

	}

	public static void sendBotPermissionErrorMessage(TextChannel tChannel)
	{

		MessageBuilder builder = new MessageBuilder()
				.setEmbed(new EmbedBuilder()
						.setTitle("Permission Denied.")
						.setDescription("Sorry I am not allowed to use commands :(!")
						.setColor(Color.RED));

		builder.send(tChannel);

	}

	public static void sendSlotsWinnerMessageBasic(TextChannel tChannel, int wager, MessageAuthor user)
	{

		MessageBuilder builder = new MessageBuilder()
				.setEmbed(new EmbedBuilder()
						.setTitle("**Winner!")
						.setDescription("Congratulations, you won " + wager * 5 + " Kbz Tokens! " + user.asUser().map(User::getMentionTag).get())
						.setColor(Color.GREEN));

		builder.send(tChannel);

		KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + wager * 5);

	}

	public static void sendSlotsWinnerMessageJackpot(TextChannel tChannel, int wager, MessageAuthor user)
	{

		MessageBuilder builder = new MessageBuilder()
				.setEmbed(new EmbedBuilder()
						.setTitle("**Winner!")
						.setDescription("JACKPOT:, you won " + wager * 10 + " Kbz Tokens! " + user.asUser().map(User::getMentionTag).get())
						.setColor(Color.GREEN));

		builder.send(tChannel);

		KbzTokens.Tokens.put(user.getIdAsString(), KbzTokens.Tokens.get(user.getIdAsString()) + wager * 10);

	}

	public static void scanTokenMessages(Scanner sc)
	{
		while(sc.hasNextLine())
		{

			String line = sc.nextLine();

			if(line.contains("#"))
			{

				String[] splitS = line.split("=");
				String[] splitS2 = splitS[0].split(" ");

				String userID = splitS2[1];
				String tokens = splitS[1];

				if(tokens != null && MethodsHandler.isInteger(tokens))
				{

					KbzTokens.Tokens.put(userID, Integer.parseInt(tokens));

				}
			}

			else
			{

				String[] splitS = line.split("=");
				String[] splitS2 = splitS[0].split(" ");

				String userID = splitS2[0];
				String tokens = splitS[1];

				if(tokens != null && MethodsHandler.isInteger(tokens))
				{

					KbzTokens.Tokens.put(userID, Integer.parseInt(tokens));

				}
			}
		}
	}

	public static void scanUserMessages(Scanner sc)
	{

		while(sc.hasNextLine())
		{

			String line = sc.nextLine();

			if(line.contains("#"))
			{

				String[] splitS = line.split("=");
				String[] splitS2 = splitS[0].split(" ");

				String userID = splitS2[1];
				String messages = splitS[1];

				if(messages != null && MethodsHandler.isInteger(messages))
				{

					App.saveUsers.put(userID, Integer.parseInt(messages));

				}
			}

			else
			{

				String[] splitS = line.split("=");
				String[] splitS2 = splitS[0].split(" ");

				String userID = splitS2[0];
				String messages = splitS[1];

				if(messages != null && MethodsHandler.isInteger(messages))
				{

					App.saveUsers.put(userID, Integer.parseInt(messages));

				}
			}
		}
	}

	public static void scanTotalMessages(Scanner sc)
	{

		while(sc.hasNextLine())
		{

			String line = sc.nextLine();

			String[] splitS = line.split(":");

			String messages = splitS[1].replace(" ", "");

			if(MethodsHandler.isInteger(messages))
			{

				App.totalMessages = Integer.parseInt(messages);

			}
		}
	}

	public static void scanValuables(Scanner sc)
	{

		String line = sc.nextLine();

		String[] splitS = line.split("&&");

		String mode = splitS[0].replace("Mode-", "");
		String game = splitS[1].replace("Game-", "");

		App.game = game;
		App.mode= mode;

		if(mode.equalsIgnoreCase("playing"))
		{

			App.api.updateActivity(ActivityType.PLAYING, game);

		}

		if(mode.equalsIgnoreCase("streaming"))
		{

			//App.jdaBot.getPresence().setGame(Game.streaming(game, "https://www.twitch.tv/kbz2001"));
			App.api.updateActivity(ActivityType.STREAMING, game);

		}

		if(mode.equalsIgnoreCase("watching"))
		{

			App.api.updateActivity(ActivityType.WATCHING, game);

		}

		if(mode.equalsIgnoreCase("listening"))
		{

			App.api.updateActivity(ActivityType.LISTENING, game);

		}
	}

	public static void saveUserMessageConfig()
	{

		TextChannel messagesChannel = App.api.getTextChannelById("551824721356783619").get();

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for(User m : App.api.getServerById("336291415908679690").get().getMembers())
		{

			if(App.saveUsers.get(m.getIdAsString()) != null || i < 5)
			{

				i++;

				sb.append("#").append(i).append(" ").append(m.getIdAsString()).append("=").append(App.saveUsers.get(m.getIdAsString())).append("\r\n");

			}
		}

		long lines = sb.chars().filter(x -> x == '\n').count() + 1;

		long splitRegex1 = lines/5;
		long splitRegex2 = splitRegex1*2;
		long splitRegex3 = splitRegex1*3;
		long splitRegex4 = splitRegex1*4;
		long splitRegex5 = splitRegex1*5;

		String[] parts1 = sb.toString().split("#"+ String.valueOf(splitRegex1));
		String[] parts2 = parts1[1].split("#"+ String.valueOf(splitRegex2));
		String[] parts3 = parts2[1].split("#"+ String.valueOf(splitRegex3));
		String[] parts4 = parts3[1].split("#"+ String.valueOf(splitRegex4));
		String[] parts5 = parts4[1].split("#"+ String.valueOf(splitRegex5));

		try
		{

			messagesChannel.getMessageById("551939321184387093").thenAccept(part1 -> part1.edit(parts1[0])).get(0, TimeUnit.SECONDS);
			messagesChannel.getMessageById("551939329728184351").thenAccept(part2 -> part2.edit(parts2[0])).get(1, TimeUnit.SECONDS);
			messagesChannel.getMessageById("551939338137632768").thenAccept(part3 -> part3.edit(parts3[0])).get(2, TimeUnit.SECONDS);
			messagesChannel.getMessageById("551939346752864265").thenAccept(part4 -> part4.edit(parts4[0])).get(3, TimeUnit.SECONDS);
			messagesChannel.getMessageById("551939354671710220").thenAccept(part5 -> part5.edit(parts5[0])).get(4, TimeUnit.SECONDS);
			messagesChannel.getMessageById("551939362997403669").thenAccept(part6 -> part6.edit(parts5[1])).get(5, TimeUnit.SECONDS);

		}

		catch(ArrayIndexOutOfBoundsException | ExecutionException | TimeoutException | InterruptedException ex)
		{

			ex.printStackTrace();

		}
	}

	public static void saveTotalMessageConfig()
	{

		TextChannel messagesChannel = App.api.getTextChannelById("551824721356783619").get();

		messagesChannel.getMessageById("551940047348301845").thenAccept(totalmsgs -> totalmsgs.edit("Total Messages: " + App.totalMessages));

	}

	public static void saveTokenConfig() throws InterruptedException, ExecutionException, TimeoutException {

		TextChannel kbzTokensChannel = App.api.getTextChannelById("551824671293571073").get();

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for(User m : App.api.getServerById("336291415908679690").get().getMembers())
		{

			if(KbzTokens.Tokens.get(m.getIdAsString()) != null || i < 5)
			{

				i++;

				sb.append("#").append(i).append(" ").append(m.getIdAsString()).append("=").append(KbzTokens.Tokens.get(m.getIdAsString())).append("\r\n");

			}
		}

		long lines = sb.chars().filter(x -> x == '\n').count() + 1;

		long splitRegex1 = lines/5;
		long splitRegex2 = splitRegex1*2;
		long splitRegex3 = splitRegex1*3;
		long splitRegex4 = splitRegex1*4;
		long splitRegex5 = splitRegex1*5;

		String[] parts1 = sb.toString().split("#"+ String.valueOf(splitRegex1));
		String[] parts2 = parts1[1].split("#"+ String.valueOf(splitRegex2));
		String[] parts3 = parts2[1].split("#"+ String.valueOf(splitRegex3));
		String[] parts4 = parts3[1].split("#"+ String.valueOf(splitRegex4));
		String[] parts5 = parts4[1].split("#"+ String.valueOf(splitRegex5));

		kbzTokensChannel.getMessageById("551941521897947137").thenAccept(part1 -> part1.edit(parts1[0])).get(0, TimeUnit.SECONDS);
		kbzTokensChannel.getMessageById("551941530068320277").thenAccept(part2 -> part2.edit(parts2[0])).get(1, TimeUnit.SECONDS);
		kbzTokensChannel.getMessageById("551941538763243535").thenAccept(part3 -> part3.edit(parts3[0])).get(2, TimeUnit.SECONDS);
		kbzTokensChannel.getMessageById("551941546946199583").thenAccept(part4 -> part4.edit(parts4[0])).get(3, TimeUnit.SECONDS);
		kbzTokensChannel.getMessageById("551941555523551232").thenAccept(part5 -> part5.edit(parts5[0])).get(4, TimeUnit.SECONDS);
		kbzTokensChannel.getMessageById("551941563908096010").thenAccept(part6 -> part6.edit(parts5[1])).get(5, TimeUnit.SECONDS);

	}

	public static void saveValuables()
	{

		TextChannel valuablesChannel = App.api.getTextChannelById("556847977449390115").get();

		valuablesChannel.getMessageById("556849369316327424").thenAccept(valuablemsg -> valuablemsg.edit("Mode-" + App.mode + " && " + "Game-" + App.game.replace(" ", "")));

	}

	public static void loadMessageConfig() throws ExecutionException, InterruptedException
	{

		TextChannel messagesChannel = App.api.getTextChannelById("551824721356783619").get();

		String msg1 = messagesChannel.getMessageById("551939321184387093").get().getContent();
		String msg2 = messagesChannel.getMessageById("551939329728184351").get().getContent();
		String msg3 = messagesChannel.getMessageById("551939338137632768").get().getContent();
		String msg4 = messagesChannel.getMessageById("551939346752864265").get().getContent();
		String msg5 = messagesChannel.getMessageById("551939354671710220").get().getContent();
		String msg6 = messagesChannel.getMessageById("551939362997403669").get().getContent();
		String totalmsgs = messagesChannel.getMessageById("551940047348301845").get().getContent();

		Scanner sc1 = new Scanner(msg1);
		Scanner sc2 = new Scanner(msg2);
		Scanner sc3 = new Scanner(msg3);
		Scanner sc4 = new Scanner(msg4);
		Scanner sc5 = new Scanner(msg5);
		Scanner sc6 = new Scanner(msg6);
		Scanner sc7 = new Scanner(totalmsgs);

		scanUserMessages(sc1);
		scanUserMessages(sc2);
		scanUserMessages(sc3);
		scanUserMessages(sc4);
		scanUserMessages(sc5);
		scanUserMessages(sc6);

		scanTotalMessages(sc7);

	}
	
	public static void loadTokenConfig() throws ExecutionException, InterruptedException
	{

		TextChannel kbzTokensChannel = App.api.getTextChannelById("551824671293571073").get();

		String msg1 = kbzTokensChannel.getMessageById("551941521897947137").get().getContent();
		String msg2 = kbzTokensChannel.getMessageById("551941530068320277").get().getContent();
		String msg3 = kbzTokensChannel.getMessageById("551941538763243535").get().getContent();
		String msg4 = kbzTokensChannel.getMessageById("551941546946199583").get().getContent();
		String msg5 = kbzTokensChannel.getMessageById("551941555523551232").get().getContent();
		String msg6 = kbzTokensChannel.getMessageById("551941563908096010").get().getContent();

		Scanner sc1 = new Scanner(msg1);
		Scanner sc2 = new Scanner(msg2);
		Scanner sc3 = new Scanner(msg3);
		Scanner sc4 = new Scanner(msg4);
		Scanner sc5 = new Scanner(msg5);
		Scanner sc6 = new Scanner(msg6);

		scanTokenMessages(sc1);
		scanTokenMessages(sc2);
		scanTokenMessages(sc3);
		scanTokenMessages(sc4);
		scanTokenMessages(sc5);
		scanTokenMessages(sc6);

	}

	public static void loadValuables() throws ExecutionException, InterruptedException
	{

		TextChannel valuablesChannel = App.api.getTextChannelById("556847977449390115").get();

		String msg1 = valuablesChannel.getMessageById("556849369316327424").get().getContent();

		Scanner sc1 = new Scanner(msg1);

		scanValuables(sc1);

	}

    public static ArrayList<User> getMembers()
    {

    	ArrayList<User> members = new ArrayList <User>();

        for(User m : App.api.getServerById("336291415908679690").get().getMembers())
        {

            members.add(m);

        }

        return members;

    }

    public static void loadMandemList()
	{

		ArrayList<String> mandemEmotes = WordsHandler.mandemEmotes;

		mandemEmotes.add(":Kbz:336927247363604491");
		mandemEmotes.add(":SmellyDoe:337019229180133376");
		mandemEmotes.add(":Ryan:552385459997638657");
		mandemEmotes.add(":Smellypizza:336993029359665155");
		mandemEmotes.add(":DevinHuey:336629496922767360");
		mandemEmotes.add(":Tanner:396911525744607263");
		mandemEmotes.add(":Viking725:481296354803580928");
		mandemEmotes.add(":Gormley:337031923182469122");
		mandemEmotes.add(":Pekkaguey:551182374449315870");
		mandemEmotes.add(":Mitchellith:337029090710388736");
		mandemEmotes.add(":LadyPiper:336631732079951872");
		mandemEmotes.add(":SnapDoomy:336660661570502656");
		mandemEmotes.add(":Invincitron2000:336680103973093387");
		mandemEmotes.add(":Smoarzified:511750990865760257");
		mandemEmotes.add(":greaneye:521822646279602176");
		mandemEmotes.add(":Cashboys:513889060708941846");
		mandemEmotes.add(":Edupa:338012355227287563");
		mandemEmotes.add(":xLatios:514458844517367808");
		mandemEmotes.add(":Ferozion:570431297005027368");
		mandemEmotes.add(":Squag:550928265011789825");
		mandemEmotes.add(":Pehi:550928246829481985");
		mandemEmotes.add(":Breathyy:340702633050243082");
		mandemEmotes.add(":TheSleb:552661470949802007");
		mandemEmotes.add(":Adsaip:553321070539702314");
		mandemEmotes.add(":Beggar_Douglas:553321265344151552");
		mandemEmotes.add(":HeroIsak:554063281363615774");
		mandemEmotes.add(":Inovity:558775191694606336");
		mandemEmotes.add(":TechoBae:561017980235022338");

	}

	public static String GETArenaRequest() throws IOException
	{
		URL urlForGetRequest = new URL("https://api.hypixel.net/gamecounts?key=bf4ccdca-4c57-4fc3-ba91-30b5c7c4b373");
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		conection.setRequestProperty("ARENA", "a1bcdef");
		int responseCode = conection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));

			StringBuffer response = new StringBuffer();

			while ((readLine = in .readLine()) != null)
			{

				response.append(readLine);

			}

			in.close();

			String enitreGameCount = response.toString();
			String[] parts = enitreGameCount.split("ARENA");
			String part2 = parts[1];
			String[] part3 = part2.split(",");
			part4 = part3[0].replace("\":", "");

		}
		else
		{

			System.out.println("GET NOT WORKED");

		}

		return part4;

	}
	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		return Arrays.stream(str.split("\\s+"))
				.map(t -> t.substring(0, 1).toUpperCase() + t.substring(1).toLowerCase())
				.collect(Collectors.joining(" "));
	}
}
