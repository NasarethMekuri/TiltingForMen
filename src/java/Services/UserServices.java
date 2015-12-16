package Services;

import Logical.EventManager;
import Logical.HTMLFactory;
import Logical.HTMLFileCreator;
import Persistence.DBHandler;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("userservices")
public class UserServices
{
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of UserServices
     */
    public UserServices() {}
    
    @Path("/gallowchoice")
    @POST
    public Response gallowChoice(@FormParam("gallow") int gallowNumber)
    {
        String html = HTMLFactory.getInstance().createGameTable(gallowNumber, false); //Redirect to a table with participants for the chosen gallow.
        String pageName = "gametable";
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/"+ HTMLFileCreator.createHTMLPage(pageName, html) + ".html";
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/livescore")
    @GET
    public Response liveScore()
    {
        boolean eventIsActive = false;
        try
        {
            eventIsActive = EventManager.getInstance().getEvent().isActive();
        }
        catch (NullPointerException npe)
        {
            eventIsActive = false;
        }
        String pageURL = null;
        
        if (!eventIsActive)
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/event_inactive.html";
        }
        else
        {
            int gallows = EventManager.getInstance().getEvent().getGallows().length;
            String html = HTMLFactory.getInstance().createGallowChoicePage(gallows, false);
            HTMLFileCreator.createHTMLPage("gallowchoice", html);
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/gallowchoice.html";
        }
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/signup")
    @GET
    public Response signUp()
    {
        String pageURL = "";
        
        boolean regOpen = AdminServices.isRegOpen();
        
        if (!regOpen)
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/registration_closed.html";
        }
        else
        {
            pageURL = "http://127.0.0.1:8080/TiltingForMen/registration.html";
        }
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/registerparticipant")
    @POST
    public Response registerParticipant(@FormParam("fname") String fName, @FormParam("lname") String lName,
            @FormParam("age") String age, @FormParam("email") String email)
    {
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
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
}
