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
        String html = HTMLFactory.getInstance().createGallowTable(Integer.parseInt(gallowNumber)); 
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/"+ HTMLFileCreator.createHTMLPage("gametable", html) + ".html";
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    
}
