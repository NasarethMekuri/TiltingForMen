
package Services;

import Logical.EventManager;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("adminservices")
public class AdminServices 
{
    private static boolean regOpen = true;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminServices
     */
    public AdminServices() {}
    
    @Path("/startevent")
    @POST
    public Response startEvent(@FormParam("year") int year, @FormParam("availgallows") int numOfAvailableGallows, @FormParam("maxparprgallow") int maxParticipantsPrGallow)
    {
        System.out.println("[SYSTEM]: Admin started the event for " + year);
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/admin_event_started.html";
        
        EventManager.getInstance().startNewEvent(year, numOfAvailableGallows, maxParticipantsPrGallow);
        
        return Response.seeOther(URI.create(pageURL)).build();
    }

    @Path("/flipreg")
    @GET
    public Response flipReg()
    {
        regOpen = !regOpen;
        
        String pageURL = regOpen ? "http://127.0.0.1:8080/TiltingForMen/admin_reg_open.html" : "http://127.0.0.1:8080/TiltingForMen/admin_reg_closed.html";
        
        return Response.temporaryRedirect(URI.create(pageURL)).build();
    }
    
    @Path("/createmoderator")
    @POST
    public Response createModerator()
    {
        //TODO: Implement
        return null;
    }
    
    @Path("/retrievemoderator")
    @GET
    public Response retrieveModerator()
    {
        //TODO: Implement
        return null;
    }
    
    @Path("/updatemoderator")
    @POST
    public Response updateModerator()
    {
        //TODO: Implement
        return null;
    }
    @Path("/deletemoderator")
    @POST
    public Response deleteModerator()
    {
        //TODO: Implement
        return null;
    }

    public static boolean isRegOpen()  {return regOpen;}
}
