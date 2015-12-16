package Services;

import Logical.EventManager;
import Logical.HTMLFactory;
import Logical.HTMLFileCreator;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("moderatorservices")
public class ModeratorServices
{
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of ModeratorServices
     */
    public ModeratorServices() {}
    
    @Path("/gallowchoice")
    @POST
    public Response gallowChoice(@FormParam("gallow") int gallowNumber)
    {
        String html = HTMLFactory.getInstance().createGameTable(gallowNumber, true);
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/"+ HTMLFileCreator.createHTMLPage("moderator_gametable", html) + ".html";
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/livescore")
    @GET
    public Response liveScore()
    {
        boolean eventIsActive = EventManager.getInstance().getEvent().isActive();
        String pageURL = null;
        
        if (!eventIsActive)
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/event_inactive.html";
        }
        else
        {
            int gallows = EventManager.getInstance().getEvent().getGallows().length;
            String html = HTMLFactory.getInstance().createGallowChoicePage(gallows, true);
            HTMLFileCreator.createHTMLPage("moderator_gallowchoice", html);
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/moderator_gallowchoice.html";
        }
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/enrollment")
    @GET
    public Response enrollment()
    {
        //TODO: Implement enrollment!
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/WIP.html";
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    
}
