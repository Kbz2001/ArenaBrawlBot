package ArenaBot;

import ArenaBot.Commands.*;
import ArenaBot.Currency.*;
import ArenaBot.Handlers.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class App
{

	public static DiscordApi api;

	public static int totalMessages = 0;

	public static boolean isOnline = true;
	
	public static HashMap<String, Integer> saveUsers = new HashMap<>();

	public static String mode = "";
	public static String game = "";

	public App() throws ExecutionException, InterruptedException {

		api = new DiscordApiBuilder().setToken(MethodsHandler.getToken())
				.setAllIntents()
				.login().join();

		api.updateActivity(ActivityType.PLAYING, "Doe: Type %help!");

		api.addListener(new AdminCommands());
		api.addListener(new BotCommands());
		api.addListener(new SlotsCommand());
		api.addListener(new UserCommands());
		api.addListener(new KbzTokens());
		api.addListener(new FlagGameHandler());
		api.addListener(new MessagesHandler());
		api.addListener(new SelfRoles());
		api.addListener(new WordsHandler());

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
4. Update IBM Cloud if certificate error x509 occurs.

*/