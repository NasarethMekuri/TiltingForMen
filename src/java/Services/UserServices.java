/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Logical.HTMLFactory;
import Logical.HTMLFileCreator;
import Persistence.DBHandler;
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
        boolean regOpen = AdminServices.isRegOpen();
        String pageURL = "";
        
        if (!hasParticipant && regOpen)
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
            //TODO: Determine how many gallows are active. (Admin startEventButton? in adminJava-Client???)
            
            
            String html = HTMLFactory.getInstance().createGallowChoicePage(23);
            HTMLFileCreator.createHTMLPage("gallowchoice", html);
            
            pageURL = "http://127.0.0.1:8080/TiltingForMen/gallowchoice.html";
        }
        //return HTMLFactory.getInstance().createGallowChoicePage(15);
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/gallowchoice")
    @POST
    public Response gallowChoice(@FormParam("gallow") String gallowNumber)
    {
        String html = HTMLFactory.getInstance().createGallowTable(Integer.parseInt(gallowNumber)); //Redirect to a table with participants for the chosen gallow.
        String pageName = "gametable";
        String pageURL = "http://127.0.0.1:8080/TiltingForMen/"+ HTMLFileCreator.createHTMLPage(pageName, html) + ".html";
        
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
  
    
}
