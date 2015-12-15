/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Logical.EventManager;
import Logical.HTMLFactory;
import Logical.HTMLFileCreator;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author bruger
 */
@Path("moderatorservices")
public class ModeratorServices
{
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of ModeratorServices
     */
    public ModeratorServices()
    {
    }
    
    @Path("/gallowchoice")
    @POST
    public Response gallowChoice(@FormParam("gallow") int gallowNumber)
    {
        //TODO: mooderator_gametable.html should be the page where moderators registers scores (So show gametable)
        
        String html = HTMLFactory.getInstance().createGameTableForModerators(gallowNumber);
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
        //NOT YET IMPLEMENTED  //TODO: Implement!
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/WIP.html";
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    
}
