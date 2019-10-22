package ArenaBot;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.concurrent.ExecutionException;

@ApplicationPath("/")
public class Discord extends Application
{

    public Discord()
    {

        Thread t = new Thread(() ->
        {

            try
            {

                App app = new App();

            }

            catch (InterruptedException | ExecutionException e)
            {

                e.printStackTrace();

            }
        });

        t.start();

    }
}
