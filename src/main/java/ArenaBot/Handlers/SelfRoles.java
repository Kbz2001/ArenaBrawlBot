package ArenaBot.Handlers;

import ArenaBot.App;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SelfRoles extends ListenerAdapter
{

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e)
    {

        MessageChannel channel = e.getTextChannel();
        MessageReaction.ReactionEmote reaction = e.getReactionEmote();
        Member member = e.getMember();
        User user = e.getUser();

        String rolesChannelID = "551576804713037824";
        String getMsgID = e.getMessageId();
        String msgID = "551592395800838154";

        Role arenaRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("480842311060946984");
        Role mcRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("551186530949922826");
        Role brawlhallaRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("489295469969670174");
        Role rauRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("354767956292665345");
        Role homeworkRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("551171545129549834");
        Role coderRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("344703514229866497");

        if(channel.getId().equals(rolesChannelID) && !user.isBot())
        {

            if(getMsgID.equals(msgID))
            {

                switch(reaction.getName())
                {

                    case "ArenaBrawl":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, arenaRole).queue();

                        break;

                    case "Creative":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, mcRole).queue();

                        break;

                    case "⚔":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, brawlhallaRole).queue();

                        break;

                    case "RAU":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, rauRole).queue();

                        break;

                    case "\uD83D\uDCDA":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, homeworkRole).queue();

                        break;

                    case "\uD83D\uDCBB":

                        App.jdaBot.getGuildById("336291415908679690").getController().addSingleRoleToMember(member, coderRole).queue();

                        break;

                }
            }
        }
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent e)
    {

        super.onMessageReactionRemove(e);

        MessageChannel channel = e.getTextChannel();
        MessageReaction.ReactionEmote reaction = e.getReactionEmote();
        Member member = e.getMember();
        User user = e.getUser();

        String rolesChannelID = "551576804713037824";
        String getMsgID = e.getMessageId();
        String msgID = "551592395800838154";

        Role arenaRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("480842311060946984");
        Role mcRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("551186530949922826");
        Role brawlhallaRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("489295469969670174");
        Role rauRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("354767956292665345");
        Role homeworkRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("551171545129549834");
        Role coderRole = App.jdaBot.getGuildById("336291415908679690").getRoleById("344703514229866497");

        if(channel.getId().equals(rolesChannelID) && !user.isBot())
        {

            if(getMsgID.equals(msgID))
            {

                switch(reaction.getName())
                {

                    case "ArenaBrawl":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, arenaRole).queue();

                        break;

                    case "Creative":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, mcRole).queue();

                        break;

                    case "⚔":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, brawlhallaRole).queue();

                        break;

                    case "RAU":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, rauRole).queue();

                        break;

                    case "\uD83D\uDCDA":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, homeworkRole).queue();

                        break;

                    case "\uD83D\uDCBB":

                        App.jdaBot.getGuildById("336291415908679690").getController().removeSingleRoleFromMember(member, coderRole).queue();

                        break;

                }
            }
        }
    }
}
