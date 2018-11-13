package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Map.*;

import static ArenaBot.App.adminIds;

public class AdminCommands extends ListenerAdapter 
{

    @Override
    public void onMessageReceived(MessageReceivedEvent e) 
    {

        Message msg = e.getMessage();
        MessageChannel channel = e.getChannel();
        User user = e.getAuthor();

        String kbzID = "168490710666838017";
        String botID = "494363113030680576";

        adminIds.add("161992742686162944");
        adminIds.add("133619053435682816");
        adminIds.add("168490710666838017");
        adminIds.add("241713709708214275");
        adminIds.add("237727981164953600");

        if(!user.isBot())
        {

            if(msg.getRawContent().equalsIgnoreCase("%toggleonline"))
            {

                if(user.getId().equals(kbzID))
                {

                    if(App.isOnline)
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.gray).setDescription("***I am now Offline!***");

                        channel.sendMessage(builder.build()).queue();
                        App.isOnline = false;

                    }

                    else if(!App.isOnline)
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.GREEN).setDescription("***I am now Online!***");

                        channel.sendMessage(builder.build()).queue();
                        App.isOnline = true;

                    }
                }

                if(!user.getId().equals(kbzID))
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if(App.isOnline) 
            {

                if(msg.getRawContent().equalsIgnoreCase("%shutdown"))
                {

                    if(user.getId().equals(kbzID) || user.getId().equals("161992742686162944"))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.BLACK).setDescription("***Shutting Down...***");

                        channel.sendMessage(builder.build()).queue();

                        App.jdaBot.shutdown();


                    }

                    if(!user.getId().equals(kbzID) || !user.getId().equals("161992742686162944"))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%userreset"))
                {

                    if(adminIds.contains(user.getId()))
                    {

                        String[] userResetCmd = msg.getRawContent().split(" ");

                        if(userResetCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format!" + user.getAsMention()
                                    + "\n%userreset <@Username>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(userResetCmd.length >= 2)
                        {

                            User mentionedUser = null;

                            if(userResetCmd[1].startsWith("<@") && !userResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = userResetCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();


                            }

                            if(userResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = userResetCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            if(App.saveUsers.containsKey(mentionedUser.getId()))
                            {

                                App.saveUsers.put(mentionedUser.getId(), 0);

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***" + mentionedUser.getName() + "'s" + " Messages have been reset!***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if(!userResetCmd[1].startsWith("<@") || !userResetCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format!" + user.getAsMention()
                                        + "\n%userreset <@Username>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if(!adminIds.contains(user.getId()))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().equalsIgnoreCase("%totalreset"))
                {

                    if(user.getId().equals(kbzID))
                    {

                        App.totalMessages = 0;

                        MethodsHandler.Write(new File(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//Total Message Count//message.LadyPiper"), Integer.toString(App.totalMessages));

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.ORANGE).setDescription("***Total Message Count has been reset!***");

                        channel.sendMessage(builder.build()).queue();

                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().equalsIgnoreCase("%alluserreset"))
                {

                    if(user.getId().equals(kbzID))
                    {

                        if(App.saveUsers.containsKey(user.getId()))
                        {

                            for (Entry <String, Integer> entry : App.saveUsers.entrySet())
                            {

                                entry.setValue(0);

                            }

                            MethodsHandler.saveMessageConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting all personal message counts!***");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().equalsIgnoreCase("%alltokenreset"))
                {

                    if(user.getId().equals(kbzID))
                    {

                        if(KbzTokens.Tokens.containsKey(user.getId()))
                        {

                            for (Entry <String, Integer> entry : KbzTokens.Tokens.entrySet())
                            {

                                entry.setValue(0);

                            }

                            MethodsHandler.saveTokenConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting all personal Kbz Tokens!***");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().equalsIgnoreCase("%lbreset"))
                {

                    if(user.getId().equals(kbzID))
                    {

                        if(App.saveUsers.containsKey(user.getId()))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting Leaderboard!***");

                            channel.sendMessage(builder.build()).queue();

                            Iterator <Entry <String, Integer>> itr = App.saveUsers.entrySet().iterator();
                            int counter = 1;
                            while (itr.hasNext() && counter <= 10)
                            {

                                Map.Entry <String, Integer> entry = itr.next();
                                counter++;
                                entry.setValue(0);

                            }

                            MethodsHandler.saveMessageConfig();

                        }
                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%totaladd"))
                {

                    if(adminIds.contains(user.getId()))
                    {

                        String[] addCmd = msg.getRawContent().split(" ");

                        if(addCmd[1] != null && MethodsHandler.isInteger(addCmd[1]) && !addCmd[1].startsWith("-"))
                        {

                            App.totalMessages = App.totalMessages + Integer.parseInt(addCmd[1]);

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.MAGENTA).setDescription(Integer.parseInt(addCmd[1]) + " messages have been added to the count!");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(addCmd[1] == null || !MethodsHandler.isInteger(addCmd[1]) || addCmd[1].startsWith("-"))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %totaladd <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%totalsub"))
                {

                    if(user.getId().equals(kbzID))
                    {

                        String[] subCmd = msg.getRawContent().split(" ");

                        if(subCmd[1] != null && MethodsHandler.isInteger(subCmd[1]) && !subCmd[1].startsWith("-"))
                        {

                            App.totalMessages = App.totalMessages - Integer.parseInt(subCmd[1]);

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription(Integer.parseInt(subCmd[1]) + " messages have been removed from the count!");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(subCmd[1] == null || !MethodsHandler.isInteger(subCmd[1]) || subCmd[1].startsWith("-"))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %totalsub <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(App.totalMessages < Integer.parseInt(subCmd[1]))
                        {

                            App.totalMessages = 0;

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("You cannot have negative messages!"
                                    + "\nThe message count has been set to 0!");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!user.getId().equals(kbzID))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%addtokens"))
                {

                    if(adminIds.contains(user.getId()))
                    {

                        String[] tokenAddCmd = msg.getRawContent().split(" ");

                        if(tokenAddCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format! " + user.getAsMention()
                                    + "\n%addtokens <@Username> <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(tokenAddCmd.length >= 3)
                        {

                            User mentionedUser = null;

                            if(tokenAddCmd[1].startsWith("<@") && !tokenAddCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenAddCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                               mentionedUser = mentionedMember.getUser();

                            }

                            if(tokenAddCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenAddCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            String tokensToAdd = tokenAddCmd[2];

                            if(KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                KbzTokens.Tokens.put(mentionedUser.getId(), KbzTokens.Tokens.get(mentionedUser.getId()) + Integer.parseInt(tokensToAdd));

                                MethodsHandler.saveTokenConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***Gave " + tokensToAdd + " tokens to " + mentionedUser.getName() + "***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if(tokenAddCmd[2] == null || !MethodsHandler.isInteger(tokenAddCmd[2]) || tokenAddCmd[2].startsWith("-"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %addtokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if(!KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {
                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please type in a valid username!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if(!tokenAddCmd[1].startsWith("<@") || !tokenAddCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %addtokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if(!adminIds.contains(user.getId()))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%tokenreset"))
                {

                    if(adminIds.contains(user.getId()))
                    {

                        String[] tokenResetCmd = msg.getRawContent().split(" ");

                        if(tokenResetCmd.length >= 2)
                        {

                            User mentionedUser = null;

                            if(tokenResetCmd[1].startsWith("<@") && !tokenResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenResetCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            if(tokenResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenResetCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            if(KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Resetting " + mentionedUser.getName() + " tokens!");

                                channel.sendMessage(builder.build()).queue();

                                KbzTokens.Tokens.put(mentionedUser.getId(), 0);

                                MethodsHandler.saveTokenConfig();

                            }

                            if(!KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please give a valid username!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if(!tokenResetCmd[1].startsWith("<@") || !tokenResetCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                        + "\n %tokenreset <@Username>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }

                        if(tokenResetCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %tokenreset <@Username>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!adminIds.contains(user.getId()))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%ban"))
                {

                   if(adminIds.contains(user.getId()))
                   {

                       String[] banCmd = msg.getRawContent().split(" ");

                       if(banCmd.length >= 2)
                       {

                           User mentionedUser = null;

                           if(banCmd[1].startsWith("<@") && !banCmd[1].startsWith("<@!"))
                           {

                               String mentionedID = banCmd[1];

                               mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                               Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                               mentionedUser = mentionedMember.getUser();

                           }

                           if(banCmd[1].startsWith("<@!"))
                           {

                               String mentionedID = banCmd[1];

                               mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                               Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                               mentionedUser = mentionedMember.getUser();

                           }

                           int banDays = Integer.parseInt(banCmd[2]);

                           StringBuilder sbuilder = new StringBuilder();

                           for (int i = 1; i < banCmd.length; i++)
                           {

                               sbuilder.append(' ').append(banCmd[i + 3]);

                           }

                           e.getGuild().getController().ban(mentionedUser, banDays, sbuilder.toString());


                           if(!banCmd[1].startsWith("<@") || !banCmd[1].startsWith("<@!"))
                           {

                               EmbedBuilder builder = new EmbedBuilder();

                               builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                       + "\n %ban <@Username> <Days> <Reason>");

                               channel.sendMessage(builder.build()).queue();

                           }
                       }

                       if(banCmd.length <= 1)
                       {

                           EmbedBuilder builder = new EmbedBuilder();

                           builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                   + "\n %ban <@Username> <Days> <Reason>");

                           channel.sendMessage(builder.build()).queue();

                       }
                   }

                    if(!adminIds.contains(user.getId()))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getRawContent().startsWith("%unban"))
                {

                    if(adminIds.contains(user.getId()))
                    {

                        String[] unbanCmd = msg.getRawContent().split(" ");

                        if(unbanCmd.length >= 2)
                        {

                            User mentionedUser = null;

                            if(unbanCmd[1].startsWith("<@") && !unbanCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = unbanCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            if(unbanCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = unbanCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            e.getGuild().getController().unban(mentionedUser);

                            if(!unbanCmd[1].startsWith("<@") || !unbanCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                        + "\n %unban <@Username>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }

                        if(unbanCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %unban <@Username>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!adminIds.contains(user.getId()))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }
            }
        }

        if(msg.getRawContent().startsWith("%purge"))
        {

            if(user.getId().equals(kbzID) || user.getId().equals(botID))
            {

                String[] purgeCmd = msg.getRawContent().split(" ");

                if(purgeCmd.length >= 2 && MethodsHandler.isInteger(purgeCmd[1]))
                {

                    int msgsToPurge = Integer.parseInt(purgeCmd[1]);

                    if(msgsToPurge > 1 || msgsToPurge <= 100)
                    {

                        e.getMessage().delete().queue();

                        MessageHistory messageHistory = new MessageHistory(e.getTextChannel());
                        List<Message> msgs;

                        msgs = messageHistory.retrievePast(msgsToPurge).complete();
                        e.getTextChannel().deleteMessages(msgs).queue();

                    }
                }

                if(purgeCmd.length <= 1 || purgeCmd[1] == null)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %purge <# of Messages to Delete>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if(!user.getId().equals(kbzID) && user.getId().equals(botID))
            {

                EmbedBuilder builder = new EmbedBuilder();

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }
    }
}