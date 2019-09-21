package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MethodsHandler
{

	public static String part4 = "";

	public static String getToken()
	{

		return "NDk0MzYzMTEzMDMwNjgwNTc2.Dx7CjQ.Fs3BGEbM9JvEv_t2buV6GXFEXUQ";

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

			App.jdaBot.getPresence().setGame(Game.playing(game));

		}

		if(mode.equalsIgnoreCase("streaming"))
		{

			App.jdaBot.getPresence().setGame(Game.streaming(game, "https://www.twitch.tv/kbz2001"));

		}

		if(mode.equalsIgnoreCase("watching"))
		{

			App.jdaBot.getPresence().setGame(Game.watching(game));

		}

		if(mode.equalsIgnoreCase("listening"))
		{

			App.jdaBot.getPresence().setGame(Game.listening(game));

		}
	}

	public static void saveUserMessageConfig()
	{

		TextChannel messagesChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("551824721356783619");

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for(Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
		{

			if(App.saveUsers.get(m.getUser().getId()) != null || i < 5)
			{

				i++;

				sb.append("#").append(i).append(" ").append(m.getUser().getId()).append("=").append(App.saveUsers.get(m.getUser().getId())).append("\r\n");

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

			messagesChannel.editMessageById("551939321184387093",parts1[0]).queueAfter(0, TimeUnit.SECONDS);
			messagesChannel.editMessageById("551939329728184351",parts2[0]).queueAfter(1, TimeUnit.SECONDS);
			messagesChannel.editMessageById("551939338137632768",parts3[0]).queueAfter(2, TimeUnit.SECONDS);
			messagesChannel.editMessageById("551939346752864265",parts4[0]).queueAfter(3, TimeUnit.SECONDS);
			messagesChannel.editMessageById("551939354671710220",parts5[0]).queueAfter(4, TimeUnit.SECONDS);
			messagesChannel.editMessageById("551939362997403669",parts5[1]).queueAfter(5, TimeUnit.SECONDS);

		}

		catch(ArrayIndexOutOfBoundsException ex)
		{

			ex.printStackTrace();

		}
	}

	public static void saveTotalMessageConfig()
	{

		TextChannel messagesChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("551824721356783619");

		messagesChannel.editMessageById("551940047348301845", "Total Messages: " + App.totalMessages).queue();

	}

	public static void saveTokenConfig()
	{

		TextChannel kbzTokensChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("551824671293571073");

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for(Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
		{

			if(KbzTokens.Tokens.get(m.getUser().getId()) != null || i < 5)
			{

				i++;

				sb.append("#").append(i).append(" ").append(m.getUser().getId()).append("=").append(KbzTokens.Tokens.get(m.getUser().getId())).append("\r\n");

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

		kbzTokensChannel.editMessageById("551941521897947137",parts1[0]).queueAfter(0, TimeUnit.SECONDS);
		kbzTokensChannel.editMessageById("551941530068320277",parts2[0]).queueAfter(1, TimeUnit.SECONDS);
		kbzTokensChannel.editMessageById("551941538763243535",parts3[0]).queueAfter(2, TimeUnit.SECONDS);
		kbzTokensChannel.editMessageById("551941546946199583",parts4[0]).queueAfter(3, TimeUnit.SECONDS);
		kbzTokensChannel.editMessageById("551941555523551232",parts5[0]).queueAfter(4, TimeUnit.SECONDS);
		kbzTokensChannel.editMessageById("551941563908096010",parts5[1]).queueAfter(5, TimeUnit.SECONDS);

	}

	public static void saveValuables()
	{

		TextChannel valuablesChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("556847977449390115");

		valuablesChannel.editMessageById("556849369316327424", "Mode-" + App.mode + "&&" + "Game-" + App.game).queue();

	}

	public static void loadMessageConfig() 
	{

		TextChannel messagesChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("551824721356783619");

		String msg1 = messagesChannel.getMessageById("551939321184387093").complete().getContentRaw();
		String msg2 = messagesChannel.getMessageById("551939329728184351").complete().getContentRaw();
		String msg3 = messagesChannel.getMessageById("551939338137632768").complete().getContentRaw();
		String msg4 = messagesChannel.getMessageById("551939346752864265").complete().getContentRaw();
		String msg5 = messagesChannel.getMessageById("551939354671710220").complete().getContentRaw();
		String msg6 = messagesChannel.getMessageById("551939362997403669").complete().getContentRaw();
		String totalmsgs = messagesChannel.getMessageById("551940047348301845").complete().getContentRaw();

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
	
	public static void loadTokenConfig() 
	{

		TextChannel kbzTokensChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("551824671293571073");

		String msg1 = kbzTokensChannel.getMessageById("551941521897947137").complete().getContentRaw();
		String msg2 = kbzTokensChannel.getMessageById("551941530068320277").complete().getContentRaw();
		String msg3 = kbzTokensChannel.getMessageById("551941538763243535").complete().getContentRaw();
		String msg4 = kbzTokensChannel.getMessageById("551941546946199583").complete().getContentRaw();
		String msg5 = kbzTokensChannel.getMessageById("551941555523551232").complete().getContentRaw();
		String msg6 = kbzTokensChannel.getMessageById("551941563908096010").complete().getContentRaw();

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

	public static void loadValuables()
	{

		TextChannel valuablesChannel = App.jdaBot.getGuildById("336291415908679690")
				.getTextChannelById("556847977449390115");

		String msg1 = valuablesChannel.getMessageById("556849369316327424").complete().getContentRaw();

		Scanner sc1 = new Scanner(msg1);

		scanValuables(sc1);

	}

    public static ArrayList<Member> getMembers()
    {

    	ArrayList<Member> members = new ArrayList <Member>();

        for(Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
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

			in .close();

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
}
