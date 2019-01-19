package ArenaBot.Handlers;

import ArenaBot.App;
import ArenaBot.Currency.KbzTokens;
import net.dv8tion.jda.core.entities.Member;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MethodsHandler
{

	private static String token = "NDk0MzYzMTEzMDMwNjgwNTc2.Dx7CjQ.Fs3BGEbM9JvEv_t2buV6GXFEXUQ";
	public static String part4 = "";

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

	public static String GETArenaRequest() throws IOException
	{
		URL urlForGetRequest = new URL("https://api.hypixel.net/gamecounts?key=bf4ccdca-4c57-4fc3-ba91-30b5c7c4b373");
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		conection.setRequestProperty("ARENA", "a1bcdef");
		int responseCode = conection.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK)
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));

			StringBuffer response = new StringBuffer();

			while ((readLine = in .readLine()) != null)
			{

				response.append(readLine);

			}

			in .close();

			String enitreGameCount = response.toString();
			String[] parts = enitreGameCount.split("ARENA");
			String part2 = parts[1];
			String[] part3 = part2.split(",");
			part4 = part3[0].replace("\":", "");

		}
		else
		{

			System.out.println("GET NOT WORKED");

		}

		return part4;

	}
}
