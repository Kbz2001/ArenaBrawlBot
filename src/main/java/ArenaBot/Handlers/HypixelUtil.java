package ArenaBot.Handlers;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.AbstractReply;

import java.util.UUID;
import java.util.function.BiConsumer;

public class HypixelUtil
{

	private static String apiKey = "bf4ccdca-4c57-4fc3-ba91-30b5c7c4b373";
	public static final HypixelAPI API = new HypixelAPI(UUID.fromString(apiKey));

	public static String getKey()
	{

		return apiKey;

	}

	public static void await()
	{
		while (!Thread.interrupted())
		{
			try
			{

				Thread.sleep(1000);

			}
			catch (InterruptedException e)
			{

				e.printStackTrace();

			}
		}
	}


	public static <T extends AbstractReply> BiConsumer<T, Throwable> getTestConsumer() {
		return (result, throwable) -> {
			if (throwable != null) {
				throwable.printStackTrace();
				return;
			}

			System.out.println(result);

		};
	}
}
