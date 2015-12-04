/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.DBHandler;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    public void registerParticipant(@FormParam("fname") String fName, @FormParam("lname") String lName, 
                                    @FormParam("age") String age, @FormParam("email") String email)
    {
        //Testing connectivity
        System.out.println(fName + " " + lName + " is " + age + " years old");
        
        //TODO: Write to database here.
        DBHandler.getInstance().createParticipant(fName, lName, email, null, -1, Integer.parseInt(age), false, false);

        //TODO: Do redirect or return succes/fail page here.
    }
}
