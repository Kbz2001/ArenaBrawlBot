package ArenaBot;

import javax.security.auth.login.LoginException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

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

            catch (LoginException | InterruptedException e)
            {

                e.printStackTrace();

            }
        });

        t.start();

    }
}
