package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.entities.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MethodsHandler
{

	private static String token = "NDk0MzYzMTEzMDMwNjgwNTc2.DoycMw.CN89oPPx5MKKJnGCLF7-lzR9fOM";

	public static String getToken()
	{

		return token;

	}

	@SuppressWarnings("resource")
	public static String Read(File f)
	{

		String content = null;

		try
		{

			content = new Scanner(f).useDelimiter("\\Z").next();

		}

		catch (FileNotFoundException ex)
		{

			ex.printStackTrace();

		}

		return content;

	}

	public static void Write(File f, String content)
	{
		FileWriter fileWriter;

		try
		{

			fileWriter = new FileWriter(f);
			try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
			{

				bufferedWriter.write(content);

			}
		}

		catch (IOException ex)
		{

			ex.printStackTrace();

		}
	}

	public static boolean isInteger(String str) 
	{

		try 
		{

			@SuppressWarnings("unused")
			int i = Integer.parseInt(str);

		}

		catch (NumberFormatException nfe) 
		{

			return false;

		}

		return true;

	}

	public static void saveMessageConfig() 
	{

	    File messageFile = new File(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Message Count//message.LadyPiper");

        FileWriter fileWriter;
        
        try 
        {

            messageFile.mkdirs();

            fileWriter = new FileWriter(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Message Count//message.LadyPiper");
            
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
            {

                bufferedWriter.write(m.getUser().getId() + "=" + App.saveUsers.get(m.getUser().getId()) + "\r\n");
               
            }
            
            bufferedWriter.close();
        } 
        catch (IOException ex) 
        {
        	
            ex.printStackTrace();
            
        }  
	}

	public static void saveTokenConfig()
	{

		File tokenFile = new File(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Tokens//tokens.LadyPiper");

		FileWriter fileWriter;
        
        try 
        {

            tokenFile.mkdirs();

            fileWriter = new FileWriter(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Tokens//tokens.LadyPiper");
            
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
            {

                bufferedWriter.write(m.getUser().getId() + "=" + KbzTokens.Tokens.get(m.getUser().getId()) + "\r\n");
               
            }
            
            bufferedWriter.close();
        } 
        catch (IOException ex) 
        {
        	
            ex.printStackTrace();
            
        }  
	}
	
	public static void loadMessageConfig() 
	{

		try 
		{
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new FileReader(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Message Count//message.LadyPiper"));
			
			while(scanner.hasNextLine())
			{
				
				String[] columns = scanner.nextLine().split("=");
				
				if(isInteger(columns[1]))
				{
				
				App.saveUsers.put(columns[0], Integer.parseInt(columns[1]));
				
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
			
		}
	}
	
	public static void loadTokenConfig() 
	{
	
		try 
		{
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new FileReader(System.getProperty("user.home") + "//Desktop//Discord Related//DiscordBot//Saves//User Tokens//tokens.LadyPiper"));
			
			while(scanner.hasNextLine())
			{
				
				String[] columns = scanner.nextLine().split("=");
				
				if(isInteger(columns[1]))
				{
				
				KbzTokens.Tokens.put(columns[0], Integer.parseInt(columns[1]));
				
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
			
		}
	}

    public static ArrayList<Member> getMembers()
    {

    	ArrayList<Member> members = new ArrayList <Member>();

        for(Member m : App.jdaBot.getGuildById("336291415908679690").getMembers())
        {

            members.add(m);

        }

        return members;

    }
}
