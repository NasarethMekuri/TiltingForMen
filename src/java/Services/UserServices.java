/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.DBHandler;
import java.net.URI;
import java.net.URISyntaxException;
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

    /**
     * Retrieves representation of an instance of Services.UserServices
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml()
    {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserServices
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @Consumes("application/xml")
    public void putXml(String content)
    {
    }
    
    @Path("/registerparticipant")
    @POST
    public Response registerParticipant(@FormParam("fname") String fName, @FormParam("lname") String lName, 
                                    @FormParam("age") String age, @FormParam("email") String email)
    {
        //TODO: Do redirect or return succes/fail page here.

        //Testing connectivity
        //System.out.println(fName + " " + lName + " is " + age + " years old");
        
        //Writing to the database.
        if (!DBHandler.getInstance().hasParticipant(fName, lName, email)) 
        {
            DBHandler.getInstance().createParticipant(fName, lName, email, null, -1, Integer.parseInt(age), false, false);
        }
        else
        {
            //TODO: return registration_error page ().
        }
        
        
        URI confirmPage = null;
        try
        {
        confirmPage = new URI("http://127.0.0.1:8080/TiltingForMen/registration_success.html");
        }
        catch (URISyntaxException ex)
        {
        System.out.println("Error:" + ex);
        }
        
        
        return Response.temporaryRedirect(confirmPage).build();

    }
}
