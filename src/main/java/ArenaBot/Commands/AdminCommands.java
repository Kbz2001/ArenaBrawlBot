package ArenaBot.Commands;

import ArenaBot.App;
import ArenaBot.Discord;
import ArenaBot.Handlers.MethodsHandler;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.ChannelManager;

import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.Map.*;
import java.util.concurrent.TimeUnit;

public class AdminCommands extends ListenerAdapter
{

    private static String adminRoleString = "480561808021913610";
    private static String modRoleString = "480558799850176523";

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {

        Message msg = e.getMessage();
        MessageChannel channel = e.getChannel();
        TextChannel tChannel = e.getTextChannel();
        User user = e.getAuthor();
        Member member = e.getMember();

        Role adminRole = e.getJDA().getRoleById(adminRoleString);
        Role modRole = e.getJDA().getRoleById(modRoleString);

        String botID = "494363113030680576";

        if (!user.isBot())
        {

            if (msg.getContentRaw().equalsIgnoreCase("%toggleonline"))
            {

                if (member.getRoles().contains(adminRole))
                {

                    if (App.isOnline)
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.gray).setDescription("***I am now Offline!***");

                        channel.sendMessage(builder.build()).queue();

                        e.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);

                        App.isOnline = false;

                    } else if (!App.isOnline)
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.GREEN).setDescription("***I am now Online!***");

                        channel.sendMessage(builder.build()).queue();

                        e.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);

                        App.isOnline = true;

                    }
                }

                if (!member.getRoles().contains(adminRole))
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if (App.isOnline)
            {

                if (msg.getContentRaw().equalsIgnoreCase("%shutdown"))
                {

                    if (member.getRoles().contains(adminRole) || user.getId().equals("161992742686162944"))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.BLACK).setDescription("***Shutting Down...***");

                        channel.sendMessage(builder.build()).queue();

                        App.jdaBot.shutdown();


                    }

                    if (!member.getRoles().contains(adminRole) || !user.getId().equals("161992742686162944"))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().startsWith("%msgreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        String[] userResetCmd = msg.getContentRaw().split(" ");

                        if (userResetCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format!" + user.getAsMention()
                                    + "\n%msgreset <@Username>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (userResetCmd.length == 2)
                        {

                            User mentionedUser = null;

                            if (userResetCmd[1].startsWith("<@") && !userResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = userResetCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();


                            }

                            if (userResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = userResetCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            if (App.saveUsers.containsKey(mentionedUser.getId()))
                            {

                                App.saveUsers.put(mentionedUser.getId(), 0);

                                MethodsHandler.saveUserMessageConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***" + mentionedUser.getName() + "'s" + " Messages have been reset!***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!userResetCmd[1].startsWith("<@") || !userResetCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format!" + user.getAsMention()
                                        + "\n%msgreset <@Username>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().equalsIgnoreCase("%totalreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        App.totalMessages = 0;

                        MethodsHandler.saveTotalMessageConfig();

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.ORANGE).setDescription("***Total Message Count has been reset!***");

                        channel.sendMessage(builder.build()).queue();

                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().equalsIgnoreCase("%allmsgreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        if (App.saveUsers.containsKey(user.getId()))
                        {

                            for (Entry<String, Integer> entry : App.saveUsers.entrySet())
                            {

                                entry.setValue(0);

                            }

                            MethodsHandler.saveUserMessageConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting all personal message counts!***");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().equalsIgnoreCase("%alltokenreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        if (KbzTokens.Tokens.containsKey(user.getId()))
                        {

                            for (Entry<String, Integer> entry : KbzTokens.Tokens.entrySet()) {

                                entry.setValue(0);

                            }

                            MethodsHandler.saveTokenConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting all personal Kbz Tokens!***");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().equalsIgnoreCase("%lbreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        if (App.saveUsers.containsKey(user.getId()))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("*** Resetting Leaderboard!***");

                            channel.sendMessage(builder.build()).queue();

                            Iterator<Entry<String, Integer>> itr = App.saveUsers.entrySet().iterator();
                            int counter = 1;
                            while (itr.hasNext() && counter <= 10)
                            {

                                Map.Entry<String, Integer> entry = itr.next();
                                counter++;
                                entry.setValue(0);

                            }

                            MethodsHandler.saveUserMessageConfig();

                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().toLowerCase().startsWith("%totaladd"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        String[] addCmd = msg.getContentRaw().split(" ");

                        if (addCmd[1] != null && MethodsHandler.isInteger(addCmd[1]) && !addCmd[1].startsWith("-"))
                        {

                            App.totalMessages = App.totalMessages + Integer.parseInt(addCmd[1]);

                            MethodsHandler.saveTotalMessageConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.MAGENTA).setDescription(Integer.parseInt(addCmd[1]) + " messages have been added to the count!");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (addCmd[1] == null || !MethodsHandler.isInteger(addCmd[1]) || addCmd[1].startsWith("-"))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %totaladd <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().toLowerCase().startsWith("%totalsub"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        String[] subCmd = msg.getContentRaw().split(" ");

                        if(subCmd[1] != null && MethodsHandler.isInteger(subCmd[1]) && !subCmd[1].startsWith("-"))
                        {

                            App.totalMessages = App.totalMessages - Integer.parseInt(subCmd[1]);

                            MethodsHandler.saveTotalMessageConfig();

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

                            MethodsHandler.saveTotalMessageConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("You cannot have negative messages!"
                                    + "\nThe message count has been set to 0!");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getContentRaw().toLowerCase().startsWith("%settotalmsgs"))
                {

                    if(member.getRoles().contains(adminRole))
                    {

                        String[] setTotalCmd = msg.getContentRaw().split(" ");

                        if(setTotalCmd[1] != null && MethodsHandler.isInteger(setTotalCmd[1]) && !setTotalCmd[1].startsWith("-"))
                        {

                            App.totalMessages = Integer.parseInt(setTotalCmd[1]);

                            MethodsHandler.saveTotalMessageConfig();

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.ORANGE).setDescription("Total Message Count has been set to: " + Integer.parseInt(setTotalCmd[1]) + "!");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if(setTotalCmd[1] == null || !MethodsHandler.isInteger(setTotalCmd[1]) || setTotalCmd[1].startsWith("-"))
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %settotalmsgs <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if(!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getContentRaw().toLowerCase().startsWith("%setmsgs"))
                {

                    if(member.getRoles().contains(adminRole))
                    {

                        String[] setUserMsgCmd = msg.getContentRaw().split(" ");

                        if (setUserMsgCmd.length <= 2)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format! " + user.getAsMention()
                                    + "\n%setmsgs <@Username> <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (setUserMsgCmd.length == 3)
                        {

                            User mentionedUser = null;

                            if (setUserMsgCmd[1].startsWith("<@") && !setUserMsgCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = setUserMsgCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            if (setUserMsgCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = setUserMsgCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            String setUserMsgs = setUserMsgCmd[2];

                            if (App.saveUsers.containsKey(mentionedUser.getId()))
                            {

                                App.saveUsers.put(mentionedUser.getId(), Integer.parseInt(setUserMsgs));

                                MethodsHandler.saveUserMessageConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***Set " + mentionedUser.getName() + " messages to " + setUserMsgCmd[2] +  "***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (setUserMsgCmd[2] == null || !MethodsHandler.isInteger(setUserMsgCmd[2]) || setUserMsgCmd[2].startsWith("-"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %setmsgs <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!App.saveUsers.containsKey(mentionedUser.getId()))
                            {

                                App.saveUsers.put(mentionedUser.getId(), Integer.parseInt(setUserMsgs));

                                MethodsHandler.saveUserMessageConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("User was not in the database but has now been added!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!setUserMsgCmd[1].startsWith("<@") || !setUserMsgCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %setmsgs <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if(!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if(msg.getContentRaw().toLowerCase().startsWith("%settokens"))
                {

                    if(member.getRoles().contains(adminRole))
                    {

                        String[] setTokenCmd = msg.getContentRaw().split(" ");

                        if (setTokenCmd.length <= 2)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format! " + user.getAsMention()
                                    + "\n%settokens <@Username> <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (setTokenCmd.length == 3)
                        {

                            User mentionedUser = null;

                            if (setTokenCmd[1].startsWith("<@") && !setTokenCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = setTokenCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            if (setTokenCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = setTokenCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            String setTokens = setTokenCmd[2];

                            if (KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                KbzTokens.Tokens.put(mentionedUser.getId(), Integer.parseInt(setTokens));

                                MethodsHandler.saveTokenConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***Set " + mentionedUser.getName() + " tokens to " + setTokenCmd[2] +  "***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (setTokenCmd[2] == null || !MethodsHandler.isInteger(setTokenCmd[2]) || setTokenCmd[2].startsWith("-"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %settokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                KbzTokens.Tokens.put(mentionedUser.getId(), Integer.parseInt(setTokens));

                                MethodsHandler.saveTokenConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("User was not in the database but was added!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!setTokenCmd[1].startsWith("<@") || !setTokenCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %settokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if(!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().toLowerCase().startsWith("%addtokens"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        String[] tokenAddCmd = msg.getContentRaw().split(" ");

                        if (tokenAddCmd.length <= 2)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format! " + user.getAsMention()
                                    + "\n%addtokens <@Username> <PositiveInteger>");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (tokenAddCmd.length == 3)
                        {

                            User mentionedUser = null;

                            if (tokenAddCmd[1].startsWith("<@") && !tokenAddCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenAddCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            if (tokenAddCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenAddCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
                                mentionedUser = mentionedMember.getUser();

                            }

                            String tokensToAdd = tokenAddCmd[2];

                            if (KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                KbzTokens.Tokens.put(mentionedUser.getId(), KbzTokens.Tokens.get(mentionedUser.getId()) + Integer.parseInt(tokensToAdd));

                                MethodsHandler.saveTokenConfig();

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.ORANGE).setDescription("***Gave " + tokensToAdd + " tokens to " + mentionedUser.getName() + "***");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (tokenAddCmd[2] == null || !MethodsHandler.isInteger(tokenAddCmd[2]) || tokenAddCmd[2].startsWith("-"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %addtokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please type in a valid username!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!tokenAddCmd[1].startsWith("<@") || !tokenAddCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format " + user.getAsMention()
                                        + "\n %addtokens <@Username> <PositiveInteger>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().toLowerCase().startsWith("%tokenreset"))
                {

                    if (member.getRoles().contains(adminRole))
                    {

                        String[] tokenResetCmd = msg.getContentRaw().split(" ");

                        if (tokenResetCmd.length == 2)
                        {

                            User mentionedUser = null;

                            if (tokenResetCmd[1].startsWith("<@") && !tokenResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenResetCmd[1];

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            if (tokenResetCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = tokenResetCmd[1];

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                            }

                            if (KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Resetting " + mentionedUser.getName() + " tokens!");

                                channel.sendMessage(builder.build()).queue();

                                KbzTokens.Tokens.put(mentionedUser.getId(), 0);

                                MethodsHandler.saveTokenConfig();

                            }

                            if (!KbzTokens.Tokens.containsKey(mentionedUser.getId()))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please give a valid username!");

                                channel.sendMessage(builder.build()).queue();

                            }

                            if (!tokenResetCmd[1].startsWith("<@") || !tokenResetCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                        + "\n %tokenreset <@Username>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }

                        if (tokenResetCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %tokenreset <@Username>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                if (msg.getContentRaw().toLowerCase().startsWith("%ban"))
                {

                    if (member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
                    {

                        String[] banCmd = msg.getContentRaw().split(" ");

                        if (banCmd.length >= 2)
                        {

                            User mentionedUser;

                            if (banCmd[1].startsWith("<@") && !banCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = banCmd[1];
                                int DelDays = Integer.parseInt(banCmd[2]);
                                String reason;

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                                StringBuilder sbuilder = new StringBuilder();

                                for (int i = 1; i < banCmd.length; i++)
                                {

                                    sbuilder.append(' ').append(banCmd[i]);

                                }

                                reason = sbuilder.toString();

                                e.getGuild().getController().ban(mentionedUser, DelDays, reason).queue();

                                msg.delete().queue();

                                e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has banned " + mentionedUser.getName() + " for " + reason).queue();

                            }

                            if (banCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = banCmd[1];
                                int DelDays = Integer.parseInt(banCmd[2]);
                                String reason;

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                                StringBuilder sbuilder = new StringBuilder();

                                for (int i = 1; i < banCmd.length; i++)
                                {

                                    sbuilder.append(' ').append(banCmd[i]);

                                }

                                reason = sbuilder.toString();

                                e.getGuild().getController().ban(mentionedUser, DelDays, reason).queue();

                                msg.delete().queue();

                                e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has banned " + mentionedUser.getName() + " for " + reason).queue();

                            }

                            if (!banCmd[1].startsWith("<@") && !banCmd[1].startsWith("<@!"))
                            {

                                EmbedBuilder builder = new EmbedBuilder();

                                builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                        + "\n %ban <@Username> <DelDays> <Reason>");

                                channel.sendMessage(builder.build()).queue();

                            }
                        }

                        if (banCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %ban <@Username> <DelDays> <Reason>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole) && !member.getRoles().contains(modRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

//                if(msg.getContentRaw().startsWith("%unban"))
//                {
//
//                    if(member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
//                    {
//
//                        String[] unbanCmd = msg.getContentRaw().split(" ");
//
//                        if(unbanCmd.length >= 2)
//                        {
//
//                            User mentionedUser;
//
//                            if(unbanCmd[1].startsWith("<@") && !unbanCmd[1].startsWith("<@!"))
//                            {
//
//                                String mentionedID = unbanCmd[1];
//
//                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);
//
//                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
//
//                                mentionedUser = mentionedMember.getUser();
//
//                                e.getGuild().getController().unban(mentionedUser).queue();
//
//                                msg.delete().queue();
//
//                            }
//
//                            if(unbanCmd[1].startsWith("<@!"))
//                            {
//
//                                String mentionedID = unbanCmd[1];
//
//                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);
//
//                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);
//
//                                mentionedUser = mentionedMember.getUser();
//
//                                e.getGuild().getController().unban(mentionedUser).queue();
//
//                                msg.delete().queue();
//
//                            }
//
//                            if(!unbanCmd[1].startsWith("<@") && !unbanCmd[1].startsWith("<@!"))
//                            {
//
//                                EmbedBuilder builder = new EmbedBuilder();
//
//                                builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
//                                        + "\n %unban <@Username>");
//
//                                channel.sendMessage(builder.build()).queue();
//
//                            }
//                        }
//
//                        if(unbanCmd.length <= 1)
//                        {
//
//                            EmbedBuilder builder = new EmbedBuilder();
//
//                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
//                                    + "\n %unban <@Username>");
//
//                            channel.sendMessage(builder.build()).queue();
//
//                        }
//                    }
//
//                    if(!member.getRoles().contains(adminRole) && !member.getRoles().contains(modRole))
//                    {
//
//                        EmbedBuilder builder = new EmbedBuilder();
//
//                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");
//
//                        channel.sendMessage(builder.build()).queue();
//
//                    }
//                }

                if (msg.getContentRaw().toLowerCase().startsWith("%kick"))
                {

                    if (member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
                    {

                        String[] kickCmd = msg.getContentRaw().split(" ");

                        if (kickCmd.length >= 3)
                        {

                            User mentionedUser;

                            if (kickCmd[1].startsWith("<@") && !kickCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = kickCmd[1];
                                String reason;

                                mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                                StringBuilder sbuilder = new StringBuilder();

                                for (int i = 1; i < kickCmd.length; i++)
                                {

                                    sbuilder.append(' ').append(kickCmd[i]);

                                }

                                reason = sbuilder.toString();

                                e.getGuild().getController().kick(mentionedMember, reason).queue();

                                msg.delete().queue();

                                mentionedUser.openPrivateChannel().queue((Pchannel -> Pchannel.sendMessage(reason).queue()));

                                e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has kicked " + mentionedUser.getName() + " for " + reason).queue();

                            }

                            if (kickCmd[1].startsWith("<@!"))
                            {

                                String mentionedID = kickCmd[1];
                                String reason;

                                mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                                Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                                mentionedUser = mentionedMember.getUser();

                                StringBuilder sbuilder = new StringBuilder();

                                for (int i = 1; i < kickCmd.length; i++)
                                {

                                    sbuilder.append(' ').append(kickCmd[i]);

                                }

                                reason = sbuilder.toString();

                                e.getGuild().getController().kick(mentionedMember, reason).queue();

                                msg.delete().queue();

                                mentionedUser.openPrivateChannel().queue((Pchannel -> Pchannel.sendMessage(reason).queue()));

                                e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has kicked " + mentionedUser.getName() + " for " + reason).queue();

                            }
                        }

                        if (kickCmd.length == 2)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please specify a reason " + user.getAsMention() + "!");

                            channel.sendMessage(builder.build()).queue();

                        }

                        if (kickCmd.length <= 1)
                        {

                            EmbedBuilder builder = new EmbedBuilder();

                            builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                    + "\n %kick <@Username> <Reason>");

                            channel.sendMessage(builder.build()).queue();

                        }
                    }

                    if (!member.getRoles().contains(adminRole) && !member.getRoles().contains(modRole))
                    {

                        EmbedBuilder builder = new EmbedBuilder();

                        builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                        channel.sendMessage(builder.build()).queue();

                    }
                }
            }
        }

        if (msg.getContentRaw().toLowerCase().startsWith("%warn"))
        {

            if (member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
            {

                String[] warnCmd = msg.getContentRaw().split(" ");

                if (warnCmd.length >= 3)
                {

                    User mentionedUser;

                    if (warnCmd[1].startsWith("<@") && !warnCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = warnCmd[1];
                        String reason;

                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        StringBuilder sbuilder = new StringBuilder();

                        for (int i = 1; i < warnCmd.length; i++)
                        {

                            sbuilder.append(' ').append(warnCmd[i]);

                        }

                        reason = sbuilder.toString();

                        msg.delete().queue();

                        mentionedUser.openPrivateChannel().queue((Pchannel -> Pchannel.sendMessage(reason).queue()));

                        e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has warned " + mentionedUser.getName() + " for " + reason).queue();

                    }

                    if (warnCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = warnCmd[1];
                        String reason;

                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        StringBuilder sbuilder = new StringBuilder();

                        for (int i = 1; i < warnCmd.length; i++)
                        {

                            sbuilder.append(' ').append(warnCmd[i]);

                        }

                        reason = sbuilder.toString();

                        msg.delete().queue();

                        mentionedUser.openPrivateChannel().queue((Pchannel -> Pchannel.sendMessage(reason).queue()));

                        e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has warned " + mentionedUser.getName() + " for " + reason).queue();

                    }
                }

                if (warnCmd.length == 2)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please specify a reason " + user.getAsMention() + "!");

                    channel.sendMessage(builder.build()).queue();

                }

                if (warnCmd.length <= 1)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %warn <@Username> <Reason>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && !member.getRoles().contains(modRole))
            {

                EmbedBuilder builder = new EmbedBuilder();

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }

        if (msg.getContentRaw().toLowerCase().startsWith("%mute"))
        {

            Role muteRole = e.getGuild().getRoleById("371820966944440322");

            if (member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
            {

                String[] muteCmd = msg.getContentRaw().split(" ");

                if (muteCmd.length >= 2)
                {

                    User mentionedUser;

                    if (muteCmd[1].startsWith("<@") && !muteCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = muteCmd[1];

                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        if (!mentionedMember.getRoles().contains(muteRole))
                        {

                            msg.delete().queue();

                            e.getGuild().getController().addSingleRoleToMember(mentionedMember, muteRole).queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has muted " + mentionedUser.getName()).queue();

                        }

                        if (mentionedMember.getRoles().contains(muteRole))
                        {

                            msg.delete().queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getAsMention() + " That person is already muted!").queue();

                        }
                    }

                    if (muteCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = muteCmd[1];

                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        if (!mentionedMember.getRoles().contains(muteRole))
                        {

                            msg.delete().queue();

                            e.getGuild().getController().addSingleRoleToMember(mentionedMember, muteRole).queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has muted " + mentionedUser.getName()).queue();

                        }

                        if (mentionedMember.getRoles().contains(muteRole))
                        {

                            msg.delete().queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getAsMention() + " That person is already muted!").queue();

                        }
                    }
                }

                if (muteCmd.length <= 1)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %mute <@Username>");

                    channel.sendMessage(builder.build()).queue();

                }
            }
        }

        if (msg.getContentRaw().toLowerCase().startsWith("%unmute"))
        {

            Role muteRole = e.getGuild().getRoleById("371820966944440322");

            if (member.getRoles().contains(adminRole) || member.getRoles().contains(modRole))
            {

                String[] unMuteCmd = msg.getContentRaw().split(" ");

                if (unMuteCmd.length >= 2)
                {

                    User mentionedUser;

                    if (unMuteCmd[1].startsWith("<@") && !unMuteCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = unMuteCmd[1];

                        mentionedID = mentionedID.substring(2, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        if (mentionedMember.getRoles().contains(muteRole)) {

                            msg.delete().queue();

                            e.getGuild().getController().removeSingleRoleFromMember(mentionedMember, muteRole).queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has unmuted " + mentionedUser.getName()).queue();

                        }

                        if (!mentionedMember.getRoles().contains(muteRole))
                        {

                            msg.delete().queue();

                            e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getAsMention() + " That person is not muted!").queue();

                        }
                    }

                    if (unMuteCmd[1].startsWith("<@!"))
                    {

                        String mentionedID = unMuteCmd[1];

                        mentionedID = mentionedID.substring(3, mentionedID.length() - 1);

                        Member mentionedMember = msg.getGuild().getMemberById(mentionedID);

                        mentionedUser = mentionedMember.getUser();

                        msg.delete().queue();

                        e.getGuild().getController().removeSingleRoleFromMember(mentionedMember, muteRole).queue();

                        e.getJDA().getTextChannelById("511679196947546122").sendMessage("> " + user.getName() + " has unmuted " + mentionedUser.getName()).queue();

                    }
                }

                if (unMuteCmd.length <= 1)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %unmute <@Username>");

                    channel.sendMessage(builder.build()).queue();

                }
            }
        }

        if (msg.getContentRaw().toLowerCase().startsWith("%purge"))
        {

            if (member.getRoles().contains(adminRole) || user.getId().equals(botID)) {

                String[] purgeCmd = msg.getContentRaw().split(" ");

                if (purgeCmd.length >= 2 && MethodsHandler.isInteger(purgeCmd[1]))
                {

                    int msgsToPurge = Integer.parseInt(purgeCmd[1]);

                    if (msgsToPurge > 1 || msgsToPurge <= 100)
                    {

                        e.getMessage().delete().queue();

                        MessageHistory messageHistory = new MessageHistory(e.getTextChannel());
                        List<Message> msgs;

                        msgs = messageHistory.retrievePast(msgsToPurge).complete();
                        e.getTextChannel().deleteMessages(msgs).queue();

                    }
                }

                if (purgeCmd.length <= 1 || purgeCmd[1] == null)
                {

                    EmbedBuilder builder = new EmbedBuilder();

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %purge <# of Messages to Delete>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
            {

                EmbedBuilder builder = new EmbedBuilder();

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

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

        if (msg.getContentRaw().equalsIgnoreCase("%setup1"))
        {

//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#1").queueAfter(2, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#2").queueAfter(4, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#3").queueAfter(6, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#4").queueAfter(8, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#5").queueAfter(10, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824671293571073").sendMessage("#6").queueAfter(12, TimeUnit.SECONDS);

        }

        //Message Channel

        if (msg.getContentRaw().equalsIgnoreCase("%setup2"))
        {

//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#1").queueAfter(2, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#2").queueAfter(4, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#3").queueAfter(6, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#4").queueAfter(8, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#5").queueAfter(10, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#6").queueAfter(12, TimeUnit.SECONDS);
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("551824721356783619").sendMessage("#7").queueAfter(14, TimeUnit.SECONDS);

        }

        //Valuables Channel

//        if(msg.getContentRaw().equalsIgnoreCase("%setup3"))
//        {
//
//            App.jdaBot.getGuildById("336291415908679690").getTextChannelById("556847977449390115").sendMessage("#1").queue();
//
//        }

        if (msg.getContentRaw().toLowerCase().startsWith("%announce"))
        {

            String[] announceCmd = msg.getContentRaw().split("%announce");

            EmbedBuilder builder = new EmbedBuilder();

            if (member.getRoles().contains(adminRole) || user.getId().equals(botID))
            {

                try
                {

                    App.jdaBot.getGuildById("336291415908679690").getTextChannelById("336326284617449472").sendMessage(announceCmd[1]).queue();

                    msg.delete().queue();

                    channel.sendMessage("Announcement Sent! <:Agree:554445062528827433>").queue(message -> message.delete().queueAfter(1, TimeUnit.SECONDS));

                    return;

                }

                catch (ArrayIndexOutOfBoundsException ex)
                {

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %announce <Message>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
            {

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }

        if(msg.getContentRaw().toLowerCase().startsWith("%quote"))
        {

            Role quoteRole = App.jdaBot.getRoleById("555873821211623434");

            String[] quoteCmd = msg.getContentRaw().split("%quote");

            EmbedBuilder builder = new EmbedBuilder();

            if ((member.getRoles().contains(adminRole) || member.getRoles().contains(quoteRole)) && !user.getId().equals(botID))
            {

                try
                {

                    App.jdaBot.getGuildById("336291415908679690").getTextChannelById("555875004022259722").sendMessage(quoteCmd[1]).queue();

                    msg.delete().queue();

                    App.jdaBot.getGuildById("336291415908679690").getTextChannelById("511679196947546122").sendMessage("A quote has been submitted by " + user.getAsMention() + "!").queue();

                    channel.sendMessage("Quote Sent! <:Agree:554445062528827433>").queue(message -> message.delete().queueAfter(1, TimeUnit.SECONDS));

                    return;

                }

                catch (ArrayIndexOutOfBoundsException ex)
                {

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %quote <Message>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if((!member.getRoles().contains(adminRole) || !member.getRoles().contains(quoteRole)) && user.getId().equals(botID))
            {

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }

        if(msg.getContentRaw().equalsIgnoreCase("%channelmute") || msg.getContentRaw().equalsIgnoreCase("%cmute"))
        {

            EmbedBuilder builder = new EmbedBuilder();

            if (member.getRoles().contains(adminRole) || user.getId().equals(botID))
            {

                Role everyone = App.jdaBot.getGuildById("336291415908679690").getRoleById("336291415908679690");

                if(tChannel.getPermissionOverride(everyone).getAllowed().contains(Permission.MESSAGE_WRITE))
                {

                    tChannel.getPermissionOverride(everyone).getManager().deny(Permission.MESSAGE_WRITE).queue();

                    channel.sendMessage(tChannel.getAsMention() + " has been muted!" + " <:Agree:554445062528827433>").queue();

                    msg.delete().queue();

                }

                else
                {

                    channel.sendMessage(tChannel.getAsMention() + " is already muted!").queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
            {

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }

        if(msg.getContentRaw().equalsIgnoreCase("%channelunmute") || msg.getContentRaw().equalsIgnoreCase("%cunmute"))
        {

            EmbedBuilder builder = new EmbedBuilder();

            if (member.getRoles().contains(adminRole) || user.getId().equals(botID))
            {

                Role everyone = App.jdaBot.getGuildById("336291415908679690").getRoleById("336291415908679690");

                if(tChannel.getPermissionOverride(everyone).getDenied().contains(Permission.MESSAGE_WRITE))
                {

                    tChannel.getPermissionOverride(everyone).getManager().grant(Permission.MESSAGE_WRITE).queue();

                    channel.sendMessage(tChannel.getAsMention() + " has been unmuted!" + " <:Agree:554445062528827433>").queue();

                    msg.delete().queue();

                }

                else
                {

                    channel.sendMessage(tChannel.getAsMention() + " is already unmuted!").queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
            {

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }

        if(msg.getContentRaw().toLowerCase().startsWith("%setgame"))
        {

            EmbedBuilder builder = new EmbedBuilder();

            if (member.getRoles().contains(adminRole) || user.getId().equals(botID))
            {

                String[] gameCmd = msg.getContentRaw().split("%setgame");

                if(gameCmd.length >= 2)
                {

                    String[] gameCmd2 = gameCmd[1].split(" ");

                    try
                    {

                        App.mode = gameCmd2[1];
                        App.game = gameCmd[1];

                        if(App.game.toLowerCase().contains("playing"))
                        {

                            App.game = App.game.replace("playing", "");

                        }

                        if(App.game.toLowerCase().contains("streaming"))
                        {

                            App.game = App.game.replace("streaming", "");

                        }

                        if(App.game.toLowerCase().contains("watching"))
                        {

                            App.game = App.game.replace("watching", "");

                        }

                        if(App.game.toLowerCase().contains("listening"))
                        {

                            App.game = App.game.replace("listening", "");

                        }

                        if(App.game.contains("Playing"))
                        {

                            App.game = App.game.replace("Playing", "");

                        }

                        if(App.game.contains("Streaming"))
                        {

                            App.game = App.game.replace("Streaming", "");

                        }

                        if(App.game.contains("Watching"))
                        {

                            App.game = App.game.replace("Watching", "");

                        }

                        if(App.game.contains("Listening"))
                        {

                            App.game = App.game.replace("Listening", "");

                        }

                        switch(App.mode)
                        {

                            case "playing":
                            case "Playing":
                            {

                                App.setGame = Game.playing(App.game);
                                App.setGameType = Game.GameType.DEFAULT;

                                App.jdaBot.getPresence().setGame(App.setGame);

                                channel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>").queue();

                                msg.delete().queue();

                                MethodsHandler.saveValuables();

                            }

                            break;

                            case "streaming":
                            case "Streaming":
                            {

                                App.setGame = Game.streaming(App.game,"https://www.twitch.tv/kbz2001");
                                App.setGameType = Game.GameType.STREAMING;

                                App.jdaBot.getPresence().setGame(App.setGame);

                                channel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>").queue();

                                msg.delete().queue();

                                MethodsHandler.saveValuables();

                            }

                            break;

                            case "watching":
                            case "Watching":
                            {

                                App.setGame = Game.watching(App.game);
                                App.setGameType = Game.GameType.WATCHING;

                                App.jdaBot.getPresence().setGame(App.setGame);

                                channel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>").queue();

                                msg.delete().queue();

                                MethodsHandler.saveValuables();

                            }

                            break;

                            case "listening":
                            case "Listening":
                            {

                                App.setGame = Game.listening(App.game);
                                App.setGameType = Game.GameType.LISTENING;

                                App.jdaBot.getPresence().setGame(App.setGame);

                                channel.sendMessage("Status Successfully Updated! " + "<:Agree:554445062528827433>").queue();

                                msg.delete().queue();

                                MethodsHandler.saveValuables();

                            }

                            break;

                            default:

                                builder.setColor(Color.RED).setDescription("Input the correct mode! (Playing, Streaming, Watching, Listening)) " + user.getAsMention() + ".");

                                channel.sendMessage(builder.build()).queue();

                                break;

                        }
                    }

                    catch(ArrayIndexOutOfBoundsException ex)
                    {

                        builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                                + "\n %setgame <Mode> <Game>");

                        channel.sendMessage(builder.build()).queue();

                    }
                }

                else
                {

                    builder.setColor(Color.RED).setDescription("Please follow the command format" + user.getAsMention()
                            + "\n %setgame <Mode> <Game>");

                    channel.sendMessage(builder.build()).queue();

                }
            }

            if (!member.getRoles().contains(adminRole) && user.getId().equals(botID))
            {

                builder.setColor(Color.RED).setDescription("You cannot do this command " + user.getAsMention() + ".");

                channel.sendMessage(builder.build()).queue();

            }
        }
    }
}