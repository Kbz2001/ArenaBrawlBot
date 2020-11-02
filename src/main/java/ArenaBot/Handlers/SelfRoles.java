package ArenaBot.Handlers;

import ArenaBot.App;
import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        String userID = e.getUserIdAsString();

        String emoji = e.getEmoji().getMentionTag()
                .replaceAll(":", "")
                .replace("<", "")
                .replace(">", "").replaceAll("[\\d]", "");

        Role arenaRole = null;
        Role mcRole = null;
        Role brawlhallaRole = null;
        Role rauRole = null;
        Role homeworkRole = null;
        Role coderRole = null;
        Optional<Role> op_arenaRole = App.api.getRoleById("480842311060946984");
        Optional<Role> op_mcRole = App.api.getRoleById("551186530949922826");
        Optional<Role> op_brawlhallaRole = App.api.getRoleById("489295469969670174");
        Optional<Role> op_rauRole = App.api.getRoleById("354767956292665345");
        Optional<Role> op_homeworkRole = App.api.getRoleById("551171545129549834");
        Optional<Role> op_coderRole = App.api.getRoleById("344703514229866497");

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

                        if(op_arenaRole.isPresent()) {
                            arenaRole = op_arenaRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(arenaRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "Creative":

                        if(op_mcRole.isPresent()) {
                            mcRole = op_mcRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(mcRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "⚔":

                        if(op_brawlhallaRole.isPresent()) {
                            brawlhallaRole = op_brawlhallaRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(brawlhallaRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "RAU":

                        if(op_rauRole.isPresent()) {
                            rauRole = op_rauRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(rauRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "\uD83D\uDCDA":

                        if(op_homeworkRole.isPresent()) {
                            homeworkRole = op_homeworkRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(homeworkRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "\uD83D\uDCBB":

                        if(op_coderRole.isPresent()) {
                            coderRole = op_coderRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.addRole(coderRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

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
        String userID = e.getUserIdAsString();

        String emoji = e.getEmoji().getMentionTag()
                .replaceAll(":", "")
                .replace("<", "")
                .replace(">", "").replaceAll("[\\d]", "");

        Role arenaRole = null;
        Role mcRole = null;
        Role brawlhallaRole = null;
        Role rauRole = null;
        Role homeworkRole = null;
        Role coderRole = null;
        Optional<Role> op_arenaRole = App.api.getRoleById("480842311060946984");
        Optional<Role> op_mcRole = App.api.getRoleById("551186530949922826");
        Optional<Role> op_brawlhallaRole = App.api.getRoleById("489295469969670174");
        Optional<Role> op_rauRole = App.api.getRoleById("354767956292665345");
        Optional<Role> op_homeworkRole = App.api.getRoleById("551171545129549834");
        Optional<Role> op_coderRole = App.api.getRoleById("344703514229866497");

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

                        if(op_arenaRole.isPresent()) {
                            arenaRole = op_arenaRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(arenaRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "Creative":

                        if(op_mcRole.isPresent()) {
                            mcRole = op_mcRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(mcRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "⚔":

                        if(op_brawlhallaRole.isPresent()) {
                            brawlhallaRole = op_brawlhallaRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(brawlhallaRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "RAU":

                        if(op_rauRole.isPresent()) {
                            rauRole = op_rauRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(rauRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "\uD83D\uDCDA":

                        if(op_homeworkRole.isPresent()) {
                            homeworkRole = op_homeworkRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(homeworkRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                    case "\uD83D\uDCBB":

                        if(op_coderRole.isPresent()) {
                            coderRole = op_coderRole.get();
                        }

                        try {
                            User user = App.api.getUserById(userID).get();
                            user.removeRole(coderRole);
                        } catch (InterruptedException | ExecutionException ex) {
                            ex.printStackTrace();
                        }

                        break;

                }
            }
        }
    }
}
