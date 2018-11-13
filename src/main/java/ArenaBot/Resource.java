package ArenaBot;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Resource
{

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get()
    {

        return "";

    }
}
