/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Logical.HTMLFactory;
import Persistence.DBHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            String html = HTMLFactory.getInstance().createGallowChoicePage(8);

            File f = new File(getPath() + "/gallowchoice.html");
            OutputStreamWriter writer = null;
            
            try
            {
                writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
                writer.write(html);
            } 
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try
                {
                    writer.close();
                } 
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            pageURL = "http://127.0.0.1:8080/TiltingForMen/gallowchoice.html";
        }
        //return HTMLFactory.getInstance().createGallowChoicePage(15);
        return Response.seeOther(URI.create(pageURL)).build();
    }
    
    @Path("/gallowchoice")
    @POST
    //@Produces("Text/HTML")
    public String gallowChoice(@FormParam("gallow") String gallowNumber)
    {
        return HTMLFactory.getInstance().createGallowTable(Integer.parseInt(gallowNumber)); //Redirect to a table with participants for the chosen gallow.
    }
   
    /**
     * Used for testing on different systems
     * @return The local path for the web directory.
     */
    private String getPath()
    {
        String user = System.getProperty("user.name");
        if (!user.equals("bruger"))
            return "SimonsPath";
        else
            return "C:\\Homework\\3.Sem Project\\TiltingForMen\\web";
    }
}
