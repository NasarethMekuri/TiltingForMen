/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

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
    public Response gallowChoice(@FormParam("gallow") String gallowNumber)
    {
        //TODO: @MKJ --> Fix this so it refers to the editable table (make the HTMLFactory handle it...)
        
        String html = HTMLFactory.getInstance().createGallowTableForModerators(Integer.parseInt(gallowNumber));
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/"+ HTMLFileCreator.createHTMLPage("moderator_gametable", html) + ".html";
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/livescore")
    @GET
    public Response liveScore()
    {
        boolean eventIsActive = true; //TODO: Create logic begind this bool - 1. determine if event is active. (ongoing)
        String pageURL = null;
        
        if (!eventIsActive)
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/event_inactive.html";
        }
        else
        {
            //TODO: Determine how many gallows are active. (Admin startEventButton? in adminJava-Client???)
            
            
            String html = HTMLFactory.getInstance().createGallowChoicePage(23); //TODO: un-Hardcode
            HTMLFileCreator.createHTMLPage("gallowchoice", html);
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/moderator_gallowchoice.html";
        }
        //return HTMLFactory.getInstance().createGallowChoicePage(15);
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/enrollment")
    @GET
    public Response enrollment()
    {
        boolean eventIsActive = true; //TODO: Create logic begind this bool - 1. determine if event is active. (ongoing)
        String pageURL = null;
        
        if (!eventIsActive)
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/event_inactive.html";
        }
        else
        {   
            String html = HTMLFactory.getInstance().createGallowChoicePage(23); //TODO: un-Hardcode
            HTMLFileCreator.createHTMLPage("gallowchoice", html);
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/enrollment.html";
        }
        //return HTMLFactory.getInstance().createGallowChoicePage(15);
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    
}
