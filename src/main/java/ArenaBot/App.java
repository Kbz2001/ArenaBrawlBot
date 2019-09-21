package ArenaBot;

import ArenaBot.Commands.*;
import ArenaBot.Currency.*;
import ArenaBot.Handlers.*;
import discord4j.core.DiscordClient;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.*;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class App extends ListenerAdapter
{

	public static JDA jdaBot;
	public static DiscordClient disClient;

	public static int totalMessages = 0;

	public static boolean isOnline = true;
	
	public static HashMap<String, Integer> saveUsers = new HashMap<>();

	public static Game setGame = null;
	public static net.dv8tion.jda.core.entities.Game.GameType setGameType = null;

	public static String mode = "";
	public static String game = "";

	public App() throws LoginException, InterruptedException
	{

			jdaBot = new JDABuilder(AccountType.BOT)
					.setGame(Game.playing("Doe:Type %help!")).setToken(MethodsHandler.getToken())
					.build().awaitReady();

			jdaBot.addEventListener(
					this,
					new UserCommands(),
					new AdminCommands(),
					new WordsHandler(),
			new MessagesHandler(),
			new KbzTokens(),
			new BotCommands(),
			new SlotsCommand(),
			new FlagGameHandler(),
			new SelfRoles());

			MethodsHandler.loadMessageConfig();
			MethodsHandler.loadTokenConfig();
			MethodsHandler.loadValuables();
			MethodsHandler.loadMandemList();

	}
}

/*

To deploy Locally:
1. Run ArenaBrawlDiscordBot [Install]
2. Connect to localhost:8080 in Chrome.

To deploy Server - Side:
1. Open cmd prompt in Discord Bot directory - MUST have manifest.yml file in the directory.
2. run C:\Users\kilez\OneDrive\Desktop\Hosting\IBM_Cloud_CLI_0.10.1_windows_amd64\IBM_Cloud_CLI\ibmcloud.exe cf push.
3. If API point isn't set do ibmcloud target --cf.

*/