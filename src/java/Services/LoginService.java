/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author bruger
 */
@Path("loginservice")
public class LoginService
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserServices
     */
    public LoginService()
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
    
    @Path("/login")
    @POST
    public void registerParticipant(@FormParam("username") String userName, @FormParam("pw") String pw)
    {
        //Testing connectivity
        System.out.println(userName + " tried to log in.");
        
        //TODO Validate email, validate password, verify login.
    }
}
