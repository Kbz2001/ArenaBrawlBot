package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Handlers.RunSlotsHandler;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Optional;

public class SlotsCommand implements MessageCreateListener
{

    @Override
    public void onMessageCreate(MessageCreateEvent e)
    {

        Optional<Server> srv = e.getServer();
        Message msg = e.getMessage();
        TextChannel tChannel = e.getChannel();
        MessageAuthor user = e.getMessageAuthor();

        if(!App.isOnline)
        {

            MethodsHandler.sendOfflineErrorMessage(tChannel);

        }

        if(msg.getContent().startsWith("%") && user.isBotUser())
        {

            MethodsHandler.sendBotPermissionErrorMessage(tChannel);

        }

        else
        {

            if(msg.getContent().equalsIgnoreCase("%slotsinfo"))
            {

                MessageBuilder builder = new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setTitle("***A guide on how the slots work!***")
                                .setDescription("\n"
                                        + "\n```http"
                                        + "\n"
                                        + "Disclaimer: I am not responcible for any gambling habits that develop as a result of this game."
                                        + "\n"
                                        + "```"
                                        + "\n"
                                        + "*__Commands__*"
                                        + "\n```css"
                                        + "\n"
                                        + "\n- %slots <wager>"
                                        + "\nWager must be between 1 to 250."
                                        + "\n```"
                                        + "\n"
                                        + "*__Winning Rolls__*"
                                        + "\n```css"
                                        + "\n"
                                        + "\nThere are 9 total positions. To win you need one or more of the following:"
                                        + "\n1.3 of the same picture on the left diagonal."
                                        + "\n2.3 of the same picture on the right diagonal."
                                        + "\n3.3 of the same picture across the middle row."
                                        + "\nAll emojis are worth the same (5x your wager). The jackpot prize is to get RAU banners."
                                        + "\nIf the jackpot is won, you earn 10x your wager."
                                        + "\n```")

                                .setColor(Color.BLUE));

                builder.send(tChannel);

            }

            if(msg.getContent().toLowerCase().startsWith("%slots"))
            {

                RunSlotsHandler.runSlots(e);

            }
        }
    }
}
