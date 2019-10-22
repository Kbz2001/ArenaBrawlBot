package ArenaBot.Handlers;

import ArenaBot.App;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

import java.util.Optional;

public class SelfRoles implements ReactionAddListener, ReactionRemoveListener
{

    @Override
    public void onReactionAdd(ReactionAddEvent e)
    {

        String rolesChannelID = "551576804713037824";
        String msgID = "551592395800838154";

        Optional<Message> msg = e.getMessage();
        String getMsgID = String.valueOf(e.getMessageId());
        TextChannel tChannel = e.getChannel();
        User user = e.getUser();
        String emoji = e.getEmoji().getMentionTag()
                .replaceAll(":", "")
                .replace("<", "")
                .replace(">", "").replaceAll("[\\d]", "");

        Optional<Role> arenaRole = App.api.getRoleById("480842311060946984");
        Optional<Role> mcRole = App.api.getRoleById("551186530949922826");
        Optional<Role> brawlhallaRole = App.api.getRoleById("489295469969670174");
        Optional<Role> rauRole = App.api.getRoleById("354767956292665345");
        Optional<Role> homeworkRole = App.api.getRoleById("551171545129549834");
        Optional<Role> coderRole = App.api.getRoleById("344703514229866497");

        if(!App.isOnline)
        {

            MethodsHandler.sendOfflineErrorMessage(tChannel);

        }

        else if(tChannel.getIdAsString().equals(rolesChannelID))
        {

            if(getMsgID.equals(msgID))
            {

                switch(emoji)
                {

                    case "ArenaBrawl":

                        arenaRole.ifPresent(user::addRole);

                        break;

                    case "Creative":

                        mcRole.ifPresent(user::addRole);

                        break;

                    case "⚔":

                        brawlhallaRole.ifPresent(user::addRole);

                        break;

                    case "RAU":

                        rauRole.ifPresent(user::addRole);

                        break;

                    case "\uD83D\uDCDA":

                        homeworkRole.ifPresent(user::addRole);

                        break;

                    case "\uD83D\uDCBB":

                        coderRole.ifPresent(user::addRole);

                        break;

                }
            }
        }
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent e)
    {

        String rolesChannelID = "551576804713037824";
        String msgID = "551592395800838154";

        Optional<Message> msg = e.getMessage();
        String getMsgID = String.valueOf(e.getMessageId());
        TextChannel tChannel = e.getChannel();
        User user = e.getUser();
        String emoji = e.getEmoji().getMentionTag()
                .replaceAll(":", "")
                .replace("<", "")
                .replace(">", "").replaceAll("[\\d]", "");

        Optional<Role> arenaRole = App.api.getRoleById("480842311060946984");
        Optional<Role> mcRole = App.api.getRoleById("551186530949922826");
        Optional<Role> brawlhallaRole = App.api.getRoleById("489295469969670174");
        Optional<Role> rauRole = App.api.getRoleById("354767956292665345");
        Optional<Role> homeworkRole = App.api.getRoleById("551171545129549834");
        Optional<Role> coderRole = App.api.getRoleById("344703514229866497");

        if(!App.isOnline)
        {

            MethodsHandler.sendOfflineErrorMessage(tChannel);

        }

        else if(tChannel.getIdAsString().equals(rolesChannelID))
        {

            if(getMsgID.equals(msgID))
            {

                switch(emoji)
                {

                    case "ArenaBrawl":

                        arenaRole.ifPresent(user::addRole);

                        break;

                    case "Creative":

                        mcRole.ifPresent(user::addRole);

                        break;

                    case "⚔":

                        brawlhallaRole.ifPresent(user::addRole);

                        break;

                    case "RAU":

                        rauRole.ifPresent(user::addRole);

                        break;

                    case "\uD83D\uDCDA":

                        homeworkRole.ifPresent(user::addRole);

                        break;

                    case "\uD83D\uDCBB":

                        coderRole.ifPresent(user::addRole);

                        break;

                }
            }
        }
    }
}
