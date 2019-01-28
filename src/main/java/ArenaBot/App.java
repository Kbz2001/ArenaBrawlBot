package ArenaBot;

import ArenaBot.Commands.*;
import ArenaBot.Currency.*;
import ArenaBot.Handlers.*;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.hooks.*;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class App extends ListenerAdapter
{

	public static JDA jdaBot;

	public static int totalMessages = 0;

	public static boolean isOnline = true;
	
	public static HashMap<String, Integer> saveUsers = new HashMap<>();

	public App() throws LoginException
	{

		jdaBot = new JDABuilder(AccountType.BOT)
				.setGame(Game.playing("Doe:Type %help!")).setToken(MethodsHandler.getToken())
				.build();

		jdaBot.addEventListener(
				this,
				new UserCommands(),
				new AdminCommands(),
				new WordsHandler(),
				new MessagesHandler(),
				new KbzTokens(),
				new BotCommands(),
                new SlotsCommand(),
				new FlagGameHandler());

		MethodsHandler.loadMessageConfig();
		MethodsHandler.loadTokenConfig();

	}
}

/*

To deploy Locally:
1. Run ArenaBrawlDiscordBot [Install]
2. Connect to localhost:8080 in Chrome.

To deploy Server - Side:
1. Open cmd prompt in Discord Bot directory - MUST have manifest.yml file in the directory.
2. run C:\Users\Kile\Desktop\Hosting\IBM_Cloud_CLI_0.10.1_windows_amd64\IBM_Cloud_CLI\ibmcloud.exe cf push.

*/