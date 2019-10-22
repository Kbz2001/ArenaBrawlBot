package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.*;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.*;
import java.util.Map.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AdminCommands implements MessageCreateListener
{

    @Override
    public void onMessageCreate(MessageCreateEvent e)
    {

        Server srv = e.getServer().get();
        Message msg = e.getMessage();
        TextChannel tChannel = e.getChannel();
        MessageAuthor author = e.getMessageAuthor();
        User user = author.asUser().get();

        String adminRoleString = "480561808021913610";
        Role adminRole = App.api.getRoleById(adminRoleString).get();

        String modRoleString = "480558799850176523";
        Role modRole = App.api.getRoleById(modRoleString).get();

        String botID = "494363113030680576";

        if(msg.getContent().startsWith("%") && author.isBotUser())
        {

            MethodsHandler.sendBotPermissionErrorMessage(tChannel);

        }

        else
        {

            if(msg.getContent().equalsIgnoreCase("%toggleonline"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

               else if(App.isOnline)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***I am now Offline!***")
                                    .setColor(Color.gray));

                    builder.send(tChannel);

                }

                else
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***I am now Online!***")
                                    .setColor(Color.green));

                    builder.send(tChannel);

                }
            }

            if(msg.getContent().equalsIgnoreCase("%shutdown"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(App.isOnline)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Shutting Down...***").setColor(Color.BLACK));

                    builder.send(tChannel);

                    App.api.disconnect();

                }
            }

            if(msg.getContent().toLowerCase().startsWith("%msgreset"))
            {

                String[] userResetCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(userResetCmd.length != 2)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("**Incorrect command format.**")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %msgreset <@Username>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    Optional<User> userToReset = null;

                    if(userResetCmd[1].startsWith("<@") && !userResetCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = userResetCmd[1];
                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        userToReset = App.api.getCachedUserById(mentionedID);

                    }

                    else if(userResetCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = userResetCmd[1];
                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        userToReset = App.api.getCachedUserById(mentionedID);

                    }

                    else if(!userResetCmd[1].startsWith("<@") && !userResetCmd[1].startsWith("<@!"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("**Incorrect command format.**")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %msgreset <@Username>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    if(App.saveUsers.containsKey(userToReset.get().getIdAsString()))
                    {

                        App.saveUsers.put(userToReset.get().getIdAsString(), 0);
                        MethodsHandler.saveUserMessageConfig();

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Messages Reset!***")
                                        .setDescription("**" + userToReset.get().getName() + "'s" + " Messages have been reset!")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }
                }
            }

            if(msg.getContent().equalsIgnoreCase("%totalreset"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    App.totalMessages = 0;
                    MethodsHandler.saveTotalMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Total Messages Reset!***")
                                    .setDescription("**The total message count has been set to 0.")
                                    .setColor(Color.ORANGE));

                    builder.send(tChannel);

                }
            }

            if(msg.getContent().equalsIgnoreCase("%allmsgreset"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(App.saveUsers.containsKey(user.getIdAsString()))
                {

                    for (Entry<String, Integer> entry : App.saveUsers.entrySet())
                    {

                        entry.setValue(0);

                    }

                    MethodsHandler.saveUserMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Personal Messages Reset!***")
                                    .setDescription("**The personal message count for all users have been set to 0.")
                                    .setColor(Color.ORANGE));

                    builder.send(tChannel);


                }
            }

            if(msg.getContent().equalsIgnoreCase("%alltokenreset"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(KbzTokens.Tokens.containsKey(user.getIdAsString()))
                {

                    for (Entry<String, Integer> entry : KbzTokens.Tokens.entrySet())
                    {

                        entry.setValue(0);

                    }

                    MethodsHandler.saveUserMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***User Kbz Tokens Reset!***")
                                    .setDescription("**The Kbz Token balance for all users have been set to 0.")
                                    .setColor(Color.ORANGE));

                    builder.send(tChannel);


                }
            }

            if(msg.getContent().equalsIgnoreCase("%lbreset"))
            {

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(App.saveUsers.containsKey(user.getIdAsString()))
                {

                    Iterator<Entry<String, Integer>> itr = App.saveUsers.entrySet().iterator();
                    int counter = 1;
                    while (itr.hasNext() && counter <= 10)
                    {

                        Map.Entry<String, Integer> entry = itr.next();
                        counter++;
                        entry.setValue(0);

                    }

                    MethodsHandler.saveUserMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Reset Message Leaderboard!***")
                                    .setDescription("**The Message Leaderboard has been wiped.")
                                    .setColor(Color.ORANGE));

                    builder.send(tChannel);

                }
            }

            if(msg.getContent().toLowerCase().startsWith("%totaladd"))
            {

                String[] addCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(addCmd[1] != null && MethodsHandler.isInteger(addCmd[1]) && !addCmd[1].startsWith("-"))
                {

                    App.totalMessages += Integer.parseInt(addCmd[1]);
                    MethodsHandler.saveTotalMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Updating Total Message Count***")
                                    .setDescription("**" + Integer.parseInt(addCmd[1]) + "messages have been added to the total." + "**")
                                    .setColor(Color.MAGENTA));

                    builder.send(tChannel);

                }

                if (addCmd[1] == null || !MethodsHandler.isInteger(addCmd[1]) || addCmd[1].startsWith("-"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %totaladd <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }
            }

            if(msg.getContent().toLowerCase().startsWith("%totalsub"))
            {

                String[] subCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(subCmd[1] != null && MethodsHandler.isInteger(subCmd[1]) && !subCmd[1].startsWith("-"))
                {

                    App.totalMessages -= Integer.parseInt(subCmd[1]);
                    MethodsHandler.saveTotalMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Updating Total Message Count***")
                                    .setDescription("**" + Integer.parseInt(subCmd[1]) + "messages have been subtracted from the total." + "**")
                                    .setColor(Color.ORANGE));

                    builder.send(tChannel);

                }

                if (subCmd[1] == null || !MethodsHandler.isInteger(subCmd[1]) || subCmd[1].startsWith("-"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %totalsub <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                if(App.totalMessages < Integer.parseInt(subCmd[1]))
                {

                    App.totalMessages = 0;
                    MethodsHandler.saveTotalMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Command Error.***")
                                    .setDescription("That would put us at negative messages!"
                                    + "\nThe message count has been set to 0.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);


                }
            }

            if(msg.getContent().toLowerCase().startsWith("%settotalmsgs"))
            {

                String[] setTotalCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(setTotalCmd[1] != null && MethodsHandler.isInteger(setTotalCmd[1]) && !setTotalCmd[1].startsWith("-"))
                {

                    App.totalMessages = Integer.parseInt(setTotalCmd[1]);
                    MethodsHandler.saveTotalMessageConfig();

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Updating Total Message Count***")
                                    .setDescription("Total Message Count has been set to:" + Integer.parseInt(setTotalCmd[1]) + ".")
                                    .setColor(Color.MAGENTA));

                    builder.send(tChannel);

                }

                if(setTotalCmd[1] == null || !MethodsHandler.isInteger(setTotalCmd[1]) || setTotalCmd[1].startsWith("-"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %settotalmsgs <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }
            }

            if(msg.getContent().toLowerCase().startsWith("%setmsgs"))
            {

                String[] setUserMsgCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(setUserMsgCmd.length != 3)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %settotalmsgs <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    Optional<User> userToSet = null;

                    if(setUserMsgCmd[1].startsWith("<@") && !setUserMsgCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = setUserMsgCmd[1];
                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (setUserMsgCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = setUserMsgCmd[1];
                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (setUserMsgCmd[2] == null || !MethodsHandler.isInteger(setUserMsgCmd[2]) || setUserMsgCmd[2].startsWith("-"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %setmsgs <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    String setUserMsgs = setUserMsgCmd[2];

                    if (App.saveUsers.containsKey(userToSet.get().getIdAsString()))
                    {

                        App.saveUsers.put(userToSet.get().getIdAsString(), Integer.parseInt(setUserMsgs));
                        MethodsHandler.saveUserMessageConfig();

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Messages***")
                                        .setDescription("***Set " + userToSet.get().getName() + "'s messages to " + setUserMsgCmd[2] +  "***")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }

                    if (!App.saveUsers.containsKey(userToSet.get().getIdAsString()))
                    {

                        App.saveUsers.put(userToSet.get().getIdAsString(), Integer.parseInt(setUserMsgs));

                        MethodsHandler.saveUserMessageConfig();

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Messages***")
                                        .setDescription("***" + userToSet.get().getName() + " was not in the database but has been added.***")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }

                    if (!setUserMsgCmd[1].startsWith("<@") && !setUserMsgCmd[1].startsWith("<@!"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %setmsgs <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }
                }
            }

            if(msg.getContent().toLowerCase().startsWith("%settokens"))
            {

                String[] setTokenCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(setTokenCmd.length != 3)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %settokens <@Username> <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    Optional<User> userToSet = null;

                    if (setTokenCmd[1].startsWith("<@") && !setTokenCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = setTokenCmd[1];
                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (setTokenCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = setTokenCmd[1];
                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    String setTokens = setTokenCmd[2];

                    if (KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        KbzTokens.Tokens.put(userToSet.get().getIdAsString(), Integer.parseInt(setTokens));
                        try
                        {

                            MethodsHandler.saveTokenConfig();

                        }
                        catch (InterruptedException | TimeoutException | ExecutionException ex)
                        {

                            ex.printStackTrace();

                        }

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Tokens***")
                                        .setDescription("***Set " + userToSet.get().getName() + "'s Kbz Tokens to" + setTokens + "***")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }

                    if (setTokenCmd[2] == null || !MethodsHandler.isInteger(setTokenCmd[2]) || setTokenCmd[2].startsWith("-"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %settokens <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    if (!KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        KbzTokens.Tokens.put(userToSet.get().getIdAsString(), Integer.parseInt(setTokens));
                        try
                        {
                            MethodsHandler.saveTokenConfig();
                        } catch (InterruptedException | ExecutionException | TimeoutException ex)
                        {
                            ex.printStackTrace();
                        }

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Tokens***")
                                        .setDescription("***" + userToSet.get().getName() + " was not in the database but has been added.***")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }

                    if (!setTokenCmd[1].startsWith("<@") || !setTokenCmd[1].startsWith("<@!"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %settokens <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }
                }
            }

            if(msg.getContent().toLowerCase().startsWith("%addtokens"))
            {

                String[] tokenAddCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(tokenAddCmd.length != 3)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %addtokens <@Username> <Positive #>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);


                }

                else
                {

                    Optional<User> userToSet = null;

                    if (tokenAddCmd[1].startsWith("<@") && !tokenAddCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = tokenAddCmd[1];
                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (tokenAddCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = tokenAddCmd[1];
                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    String tokensToAdd = tokenAddCmd[2];

                    if (KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        KbzTokens.Tokens.put(userToSet.get().getIdAsString(), KbzTokens.Tokens.get(userToSet.get().getIdAsString()) + Integer.parseInt(tokensToAdd));
                        try
                        {
                            MethodsHandler.saveTokenConfig();
                        } catch (InterruptedException | ExecutionException | TimeoutException ex)
                        {
                            ex.printStackTrace();
                        }

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Tokens***")
                                        .setDescription("***Gave " + userToSet.get().getName() + tokensToAdd +  " Kbz Tokens!***")
                                        .setColor(Color.MAGENTA));

                        builder.send(tChannel);

                    }

                    if (tokenAddCmd[2] == null || !MethodsHandler.isInteger(tokenAddCmd[2]) || tokenAddCmd[2].startsWith("-"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %addtokens <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    if (!KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Command Error.***")
                                        .setDescription("That user was not found in the database.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    if (!tokenAddCmd[1].startsWith("<@") && !tokenAddCmd[1].startsWith("<@!"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %addtokens <@Username> <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }
                }
            }

            if(msg.getContent().toLowerCase().startsWith("%tokenreset"))
            {

                String[] tokenResetCmd = msg.getContent().split(" ");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(tokenResetCmd.length != 2)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %tokenreset <@Username>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    Optional<User> userToSet = null;

                    if (tokenResetCmd[1].startsWith("<@") && !tokenResetCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = tokenResetCmd[1];
                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (tokenResetCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = tokenResetCmd[1];
                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        userToSet = App.api.getCachedUserById(mentionedID);

                    }

                    if (KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        KbzTokens.Tokens.put(userToSet.get().getIdAsString(), 0);
                        try
                        {
                            MethodsHandler.saveTokenConfig();
                        }
                        catch (InterruptedException | ExecutionException | TimeoutException ex)
                        {
                            ex.printStackTrace();
                        }

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Updating User Tokens***")
                                        .setDescription("***Reset " + userToSet.get().getName() + "'s Tokens!***")
                                        .setColor(Color.ORANGE));

                        builder.send(tChannel);

                    }

                    if (!KbzTokens.Tokens.containsKey(userToSet.get().getIdAsString()))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Command Error.***")
                                        .setDescription("**Could not find user in the database.**")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    if (!tokenResetCmd[1].startsWith("<@") && !tokenResetCmd[1].startsWith("<@!"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %tokenreset <@Username>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }
                }
            }
                if (msg.getContent().toLowerCase().startsWith("%warn"))
                {

                    String[] warnCmd = msg.getContent().split(" ");

                    if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                            !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else if(warnCmd.length != 2) {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %warn <@Username>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }
                    else
                    {

                        Optional<User> userToWarn = null;

                        if (warnCmd[1].startsWith("<@") && !warnCmd[1].startsWith("<@!"))
                        {

                            String mentionedID = warnCmd[1];
                            String reason;
                            mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                            userToWarn = App.api.getCachedUserById(mentionedID);

                            StringBuilder sbuilder = new StringBuilder();

                            for (int i = 1; i < warnCmd.length; i++)
                            {

                                sbuilder.append(' ').append(warnCmd[i]);

                            }

                            reason = sbuilder.toString();

                            msg.delete();

                            userToWarn.ifPresent(pm -> pm.sendMessage("**!You have received a warning!**\n"
                                    + reason));

                            Optional<User> finalUserToWarn = userToWarn;

                            App.api.getTextChannelById("511679196947546122").ifPresent(
                                    textChannel -> textChannel.sendMessage(">" + user.getName() + " has warned " + finalUserToWarn.get().getName() + " for " + reason));

                        }

                        if (warnCmd[1].startsWith("<@!"))
                        {

                            String mentionedID = warnCmd[1];
                            String reason;
                            mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                            userToWarn = App.api.getCachedUserById(mentionedID);

                            StringBuilder sbuilder = new StringBuilder();

                            for (int i = 1; i < warnCmd.length; i++)
                            {

                                sbuilder.append(' ').append(warnCmd[i]);

                            }

                            reason = sbuilder.toString();

                            msg.delete();

                            userToWarn.ifPresent(pm -> pm.sendMessage("**!You have received a warning!**\n"
                                    + reason));

                            Optional<User> finalUserToWarn = userToWarn;

                            App.api.getTextChannelById("511679196947546122").ifPresent(
                                    textChannel -> textChannel.sendMessage(">" + user.getName() + " has warned " + finalUserToWarn.get().getName() + " for " + reason));


                        }
                    }
                }

                if(msg.getContent().toLowerCase().startsWith("%purge"))
                {

                    String[] purgeCmd = msg.getContent().split(" ");

                    if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                            !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else if(purgeCmd.length != 2 || !MethodsHandler.isInteger(purgeCmd[1]) || purgeCmd[1] == null)
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("***Incorrect command format.***")
                                        .setDescription("Please used the correct command format " + user.getMentionTag()
                                                + "\n %purge <Positive #>")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else
                    {

                        int msgsToPurge = Integer.parseInt(purgeCmd[1]);

                        if (msgsToPurge >= 1 && msgsToPurge <= 100)
                        {

                            e.getMessage().delete();

                            CompletableFuture<MessageSet> msgHistory = tChannel.getMessagesBefore(msgsToPurge, msg);

                            try
                            {

                                msgHistory.get().deleteAll();

                            }

                            catch (InterruptedException | ExecutionException ex)
                            {

                                ex.printStackTrace();

                            }
                        }
                    }
                }

                if (msg.getContent().toLowerCase().equalsIgnoreCase("%announce"))
                {

                    String[] announceCmd = msg.getContent().split("%announce");

                    if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                            !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else
                    {

                        try
                        {

                            App.api.getTextChannelById("336326284617449472").ifPresent(textChannel -> textChannel.sendMessage(announceCmd[1]));

                            msg.delete();

                            tChannel.sendMessage("Announcement Sent! <:Agree:554445062528827433>").thenAccept(message ->
                            {
                                try
                                {

                                    message.delete().get(1, TimeUnit.SECONDS);

                                }

                                catch (InterruptedException | ExecutionException | TimeoutException ex)
                                {

                                    ex.printStackTrace();

                                }
                            });
                        }

                        catch (ArrayIndexOutOfBoundsException ex)
                        {

                            MessageBuilder builder = new MessageBuilder()
                                    .setEmbed(new EmbedBuilder()
                                            .setTitle("***Incorrect command format.***")
                                            .setDescription("Please used the correct command format " + user.getMentionTag()
                                                    + "\n %announce <Message>")
                                            .setColor(Color.RED));

                            builder.send(tChannel);

                        }
                    }
                }

                if(msg.getContent().equalsIgnoreCase("%quote"))
                {

                    String quoteRoleString = "555873821211623434";
                    String[] quoteCmd = msg.getContent().split("%quote");

                    if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                            !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else if(!user.getRoles(srv).contains(App.api.getRoleById(quoteRoleString).get()))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have the Quoter role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else
                    {

                        App.api.getTextChannelById("555875004022259722").ifPresent(quote -> quote.sendMessage(quoteCmd[1]));
                        msg.delete();

                        App.api.getTextChannelById("511679196947546122").ifPresent(quote -> quote.sendMessage("A quote has been submitted by " + user.getMentionTag() + "!"));

                        try
                        {

                            tChannel.sendMessage("Quote Sent! <:Agree:554445062528827433>").get(1, TimeUnit.SECONDS);

                        }
                        catch (InterruptedException | ExecutionException | TimeoutException ex)
                        {

                            ex.printStackTrace();

                        }
                    }
                }

                if(msg.getContent().equalsIgnoreCase("%channelmute") || msg.getContent().equalsIgnoreCase("%cmute"))
                {

                    String everyoneRoleString = "336291415908679690";
                    Role everyoneRole = App.api.getRoleById(everyoneRoleString).get();

                    Permissions perms =  tChannel.asServerChannel().get()
                            .getOverwrittenPermissions(everyoneRole);

                    if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                            !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                    {

                        MessageBuilder builder = new MessageBuilder()
                                .setEmbed(new EmbedBuilder()
                                        .setTitle("Permission Denied.")
                                        .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                        .setColor(Color.RED));

                        builder.send(tChannel);

                    }

                    else if(perms.getAllowedPermission().contains(PermissionType.SEND_MESSAGES))
                    {

                        Permissions updatePerms = perms.toBuilder().setDenied(PermissionType.SEND_MESSAGES).build();
                        tChannel.asServerTextChannel().get().createUpdater().addPermissionOverwrite(everyoneRole, updatePerms).update();

                        tChannel.sendMessage("<#" + tChannel.getIdAsString() + ">" + " has been muted!" + " <:Agree:554445062528827433>");

                    }

                    else
                    {

                        tChannel.sendMessage("<#" + tChannel.getIdAsString() + ">" + " is already muted!");

                    }
                }

            if(msg.getContent().equalsIgnoreCase("%channelunmute") || msg.getContent().equalsIgnoreCase("%cunmute"))
            {

                String everyoneRoleString = "336291415908679690";
                Role everyoneRole = App.api.getRoleById(everyoneRoleString).get();

                Permissions perms =  tChannel.asServerChannel().get()
                        .getOverwrittenPermissions(everyoneRole);

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(!perms.getAllowedPermission().contains(PermissionType.SEND_MESSAGES))
                {

                    Permissions updatePerms = perms.toBuilder().setAllowed(PermissionType.SEND_MESSAGES).build();
                    tChannel.asServerTextChannel().get().createUpdater().addPermissionOverwrite(everyoneRole, updatePerms).update();

                    tChannel.sendMessage("<#" + tChannel.getIdAsString() + ">" + " has been unmuted!" + " <:Agree:554445062528827433>");

                }

                else
                {

                    tChannel.sendMessage("<#" + tChannel.getIdAsString() + ">" + " is already unmuted!");

                }
            }

            if(msg.getContent().toLowerCase().startsWith("%setgame"))
            {

                String[] gameCmd = msg.getContent().split("%setgame");

                if((!user.getRoles(srv).contains(App.api.getRoleById(adminRoleString).get()) ||
                        !user.getRoles(srv).contains(App.api.getRoleById(modRoleString).get())) && msg.getContent().startsWith("%"))
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("Permission Denied.")
                                    .setDescription("You must have either the Mod Role or the Admin Role to access this command.")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else if(gameCmd.length != 2)
                {

                    MessageBuilder builder = new MessageBuilder()
                            .setEmbed(new EmbedBuilder()
                                    .setTitle("***Incorrect command format.***")
                                    .setDescription("Please used the correct command format " + user.getMentionTag()
                                            + "\n %setgame <Mode> <Game>")
                                    .setColor(Color.RED));

                    builder.send(tChannel);

                }

                else
                {

                    String[] gameCmd2 = gameCmd[1].split(" ");

                    App.mode = gameCmd2[1];
                    App.game = gameCmd[1];

                    switch(App.mode)
                    {

                        case "playing":
                        case "Playing":
                            {

                                App.game = App.game.replace("playing", "").replace("Playing", "");

                                App.api.updateActivity(ActivityType.PLAYING, App.game);

                                tChannel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>");
                                msg.delete();
                                MethodsHandler.saveValuables();

                                break;

                            }

                        case "streaming":
                        case "Streaming":
                        {

                            App.game = App.game.replace("streaming", "").replace("Streaming", "");

                            App.api.updateActivity(ActivityType.STREAMING, App.game);

                            tChannel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>");
                            msg.delete();
                            MethodsHandler.saveValuables();

                            break;

                        }

                        case "watching":
                        case "Watching":
                        {

                            App.game = App.game.replace("watching", "").replace("Watching", "");

                            App.api.updateActivity(ActivityType.WATCHING, App.game);

                            tChannel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>");
                            msg.delete();
                            MethodsHandler.saveValuables();

                            break;

                        }

                        case "listening":
                        case "Listening":
                        {
                            App.game = App.game.replace("listening", "").replace("Listening", "");

                            App.api.updateActivity(ActivityType.LISTENING, App.game);

                            tChannel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>");
                            msg.delete();
                            MethodsHandler.saveValuables();

                            break;

                        }

                        default:

                            MessageBuilder builder = new MessageBuilder()
                                    .setEmbed(new EmbedBuilder()
                                            .setTitle("***Incorrect command format.***")
                                            .setDescription("Please enter a valid Mode. (Playing, Watching, Streaming, Listening) " + user.getMentionTag())
                                            .setColor(Color.RED));

                            builder.send(tChannel);

                            break;

                    }
                }
            }

        /*

        !!!THIS SETS UP THE SELF ROLES CHANNEL IN CASE THERE ARE MODIFICATIONS!!!

        */

//        if (msg.getContentRaw().equalsIgnoreCase("%selfroles"))
//        {
//
//            if (member.getRoles().contains(adminRole) || user.getId().equals(botID))
//            {
//
//                if (channel.getId().equals("551576804713037824"))
//                {
//
//                    channel.addReactionById("551592395800838154", ":ArenaBrawl:481298350856077322").queue();
//                    channel.addReactionById("551592395800838154", ":Creative:336621208038801410").queueAfter(2, TimeUnit.SECONDS);
//                    channel.addReactionById("551592395800838154", "⚔").queueAfter(4, TimeUnit.SECONDS);
//                    channel.addReactionById("551592395800838154", ":RAU:481298032865050625").queueAfter(6, TimeUnit.SECONDS);
//                    channel.addReactionById("551592395800838154", "\uD83D\uDCDA").queueAfter(8, TimeUnit.SECONDS);
//                    channel.addReactionById("551592395800838154", "\uD83D\uDCBB").queueAfter(10, TimeUnit.SECONDS);
//
//                }
//            }

//            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
//            {
//
//                EmbedBuilder builder = new EmbedBuilder();
//
//                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");
//
//                channel.sendMessage(builder.build()).queue();
//
//            }
//        }

                //Tokens Channel

//                if (msg.getContentRaw().equalsIgnoreCase("%setup1"))
//                {

//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#1").queueAfter(2, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#2").queueAfter(4, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#3").queueAfter(6, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#4").queueAfter(8, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#5").queueAfter(10, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#6").queueAfter(12, TimeUnit.SECONDS);

//                }

                //Message Channel

//                if (msg.getContentRaw().equalsIgnoreCase("%setup2"))
//                {

//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#1").queueAfter(2, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#2").queueAfter(4, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#3").queueAfter(6, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#4").queueAfter(8, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#5").queueAfter(10, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#6").queueAfter(12, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#7").queueAfter(14, TimeUnit.SECONDS);

//                }

                //Valuables Channel

//        if(msg.getContentRaw().equalsIgnoreCase("%setup3"))
//        {
//
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("556847977449390115").sendMessage("#1").queue();
//
//        }


        }
    }
}