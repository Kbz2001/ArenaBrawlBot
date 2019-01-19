package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.RunSlotsHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class SlotsCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {

        Message msg = e.getMessage();
        MessageChannel channel = e.getChannel();
        User user = e.getAuthor();

        if (App.isOnline && !user.isBot()) {

            if (msg.getContentRaw().equalsIgnoreCase("%slotinfo")) {

                EmbedBuilder builder = new EmbedBuilder();

                builder.setColor(Color.BLUE).setDescription("***A guide on how the slots work!***"
                        + "\n"
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
                        + "\n```"
                );

                channel.sendMessage(builder.build()).queue();

            }

            if (msg.getContentRaw().startsWith("%slots")) {

                RunSlotsHandler.runSlots(e);

            }
        }
    }
}
