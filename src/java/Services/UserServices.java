/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import DB.DBHandler;
import java.io.IOException;
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
@Path("userservices")
public class UserServices
{
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of UserServices
     */
    public UserServices()
    {
    }
    
    
    @Path("/registerparticipant")
    @POST
    public Response registerParticipant(@FormParam("fname") String fName, @FormParam("lname") String lName,
            @FormParam("age") String age, @FormParam("email") String email)
    {
        //Testing connectivity
        //System.out.println(fName + " " + lName + " is " + age + " years old");
        
        boolean hasParticipant = DBHandler.getInstance().hasParticipant(fName, lName, email);
        
        String pageURL = "";
        
        if (!hasParticipant)
        {
            //Writing to the database.
            DBHandler.getInstance().createParticipant(fName, lName, email, null, -1, Integer.parseInt(age), false, false);
            pageURL = "http://127.0.0.1:8080/TiltingForMen/registration_success.html";
        }
        else
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/registration_fail.html";
        }
        
        return Response.temporaryRedirect(URI.create(pageURL)).build();
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
            //TODO:
            /*
            2. determine how many gallows are active.
            3. display available gallows for the user. (manipulate html file...)
            */
            
            try
            {
                updateLiveScorePage(); //Params should be: int activeGallows... (p.t. hardcoded to 3 in livescore.html)
            }
            catch (IOException ex)
            {
                System.out.println("[System]: Error building liveScorePage: " + ex);
            }
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/livescore.html";
        }
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/gallowchoice")
    @POST
    public Response gallowChoice(@FormParam("gallow") String gallowNumber)
    {
        //Testing connectivity -
        //SBL: TEST conducted with following code: Warning:   GRIZZLY0206: Exception occurred during body skip java.io.IOException
        //SBL: Might be because of return null; ? -
        //SBL: correct values are read from form!!
        //System.out.println("Gallow number " + gallowNumber + " was chosen");
        
        return null;
    }
    
    private void updateLiveScorePage() throws IOException
    {
    }
}
