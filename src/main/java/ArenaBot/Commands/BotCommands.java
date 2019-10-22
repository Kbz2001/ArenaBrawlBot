package ArenaBot.Commands;

import ArenaBot.App;
import org.javacord.api.entity.auditlog.*;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageDeleteEvent;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.event.server.member.ServerMemberLeaveEvent;
import org.javacord.api.listener.message.MessageDeleteListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;
import org.javacord.api.listener.server.member.ServerMemberLeaveListener;

import java.awt.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class BotCommands implements ServerMemberJoinListener, ServerMemberLeaveListener, MessageDeleteListener
{

	@Override
	public void onServerMemberJoin(ServerMemberJoinEvent e)
	{

		User user = e.getUser();
		Server srv = e.getServer();

		if(srv.getIdAsString().equals("336291415908679690"))
		{

			srv.getTextChannelById("369682886040879104").ifPresent(tChannel -> tChannel.sendMessage(
					user.getMentionTag() + " has dipped into the **Arena Brawl Community Discord!** :tada::hugging: !"));

		}

	}

	@Override
	public void onServerMemberLeave(ServerMemberLeaveEvent e)
	{

		User user = e.getUser();
		Server srv = e.getServer();

		if(srv.getIdAsString().equals("336291415908679690"))
		{

			srv.getTextChannelById("369682886040879104").ifPresent(tChannel -> tChannel.sendMessage(
					user.getName() + "#" + user.getDiscriminator() + " just left the Arena Brawl Community to die. :slight_frown:"));

		}
	}

	@Override
	public void onMessageDelete(MessageDeleteEvent e)
	{

		Server srv = e.getServer().get();
		Optional<Message> msg = e.getMessage();

		if (srv.equals(e.getApi().getServerById("336291415908679690").get()) && !msg.map(Message::getAuthor).map(MessageAuthor::isBotUser).get())
		{

			TextChannel modChannel = e.getApi().getTextChannelById("511679196947546122").get();

			try
			{

				AuditLog msgDelLog = App.api.getServerById("336291415908679690").get().getAuditLog(1, AuditLogActionType.MESSAGE_DELETE).get();

				User actionTaken = msgDelLog.getInvolvedUsers().stream().findFirst().get();
				User actionAgainst = msgDelLog.getInvolvedUsers().stream().skip(1).findFirst().get();

				MessageBuilder builder = new MessageBuilder()
							.setEmbed(new EmbedBuilder()
									.setTitle("**Message Deleted.**")
									.setDescription("↓↓Detailed Analysis Below.↓↓"
											+ "\n"
											+ "---------------------------"
											+ "\n"
											+ "Message Author Information:"
											+ "\n"
											+ "----------------------------"
											+ "\n"
											+ "Author Name: " + msg.map(Message::getAuthor).map(MessageAuthor::getDiscriminatedName).get()
											+ "\n"
											+ "Author Nickname: " + msg.map(Message::getAuthor).map(MessageAuthor::getDisplayName).get()
											+ "\n"
											+ "Author ID: " + msg.map(Message::getAuthor).map(MessageAuthor::getIdAsString).get()
											+ "\n"
											+ "------------"
											+ "\n"
											+ "Channel: <#" + msg.map(Message::getChannel).map(TextChannel::getIdAsString).get() + ">"
											+ "\n"
											+ "------------"
											+ "\n"
											+ "Message ID: " + msg.map(Message::getIdAsString).get()
											+ "\n"
											+ "------------"
											+ "\n"
											+ "Message Content: "+ msg.map(Message::getContent).get()
											+ "\n"
											+ "------------"
											+ "\n"
											+ "Check the audit logs for more information.")
									.setColor(Color.YELLOW));

					builder.send(modChannel);

					/*
					THIS IS FOR CHECKING IF USER != DEL THEIR OWN MSG {WIP}
					 */

//				MessageBuilder builder = new MessageBuilder()
//						.setEmbed(new EmbedBuilder()
//								.setTitle("**Message Deleted.**")
//								.setDescription("↓↓Detailed Analysis Below.↓↓"
//										+ "\n"
//										+ "---------------------------"
//										+ "\n"
//										+ "Message Author Information: (" + actionTaken.getDiscriminatedName() + " has deleted a message by " + actionAgainst.getDiscriminatedName() + ")"
//										+ "\n"
//										+ "----------------------------"
//										+ "\n"
//										+ "Admin/Mod Name: " + actionTaken.getDiscriminatedName()
//										+ "\n"
//										+ "Admin/Mod Nickname: " + actionTaken.getNickname(srv).get()
//										+ "\n"
//										+ "Admin/Mod ID: " + actionTaken.getIdAsString()
//										+ "\n"
//										+ "Author Name: " + msg.map(Message::getAuthor).map(MessageAuthor::getDiscriminatedName).get()
//										+ "\n"
//										+ "Author Nickname: " + msg.map(Message::getAuthor).map(MessageAuthor::getDisplayName).get()
//										+ "\n"
//										+ "Author ID: " + msg.map(Message::getAuthor).map(MessageAuthor::getIdAsString).get()
//										+ "\n"
//										+ "------------"
//										+ "\n"
//										+ "Channel: <#" + msg.map(Message::getChannel).map(TextChannel::getIdAsString).get() + ">"
//										+ "\n"
//										+ "------------"
//										+ "\n"
//										+ "Message ID: " + msg.map(Message::getIdAsString).get()
//										+ "\n"
//										+ "------------"
//										+ "\n"
//										+ "Message Content: "+ msg.map(Message::getContent).get()
//										+ "\n"
//										+ "------------"
//										+ "\n"
//										+ "Check the audit logs for more information.")
//								.setColor(Color.YELLOW));
//
//				builder.send(modChannel);

				/*
				THIS IS FOR CHECKING IF USER DELS THEIR OWN MSG. {WIP}
				 */
//					MessageBuilder builder = new MessageBuilder()
//							.setEmbed(new EmbedBuilder()
//									.setTitle("**Message Deleted.**")
//									.setDescription("↓↓Detailed Analysis Below.↓↓"
//											+ "\n"
//											+ "---------------------------"
//											+ "\n"
//											+ "Message Author Information: (User Deleted Their Own Message.)"
//											+ "\n"
//											+ "----------------------------"
//											+ "\n"
//											+ "Author Name: " + msg.map(Message::getAuthor).map(MessageAuthor::getDiscriminatedName).get()
//											+ "\n"
//											+ "Author Nickname: " + msg.map(Message::getAuthor).map(MessageAuthor::getDisplayName).get()
//											+ "\n"
//											+ "Author ID: " + msg.map(Message::getAuthor).map(MessageAuthor::getIdAsString).get()
//											+ "\n"
//											+ "------------"
//											+ "\n"
//											+ "Channel: <#" + msg.map(Message::getChannel).map(TextChannel::getIdAsString).get() + ">"
//											+ "\n"
//											+ "------------"
//											+ "\n"
//											+ "Message ID: " + msg.map(Message::getIdAsString).get()
//											+ "\n"
//											+ "------------"
//											+ "\n"
//											+ "Message Content: "+ msg.map(Message::getContent).get()
//											+ "\n"
//											+ "------------"
//											+ "\n"
//											+ "Check the audit logs for more information.")
//									.setColor(Color.YELLOW));
//
//					builder.send(modChannel);

			}

			catch (InterruptedException | ExecutionException ex)
			{

				ex.printStackTrace();

			}
		}
	}
}