/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

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
    @Consumes("application/xlx")
    public void registerParticipant(@FormParam("fname") String fName, @FormParam("lname") String lName, 
                                    @FormParam("age") String age, @FormParam("email") String email)
    {
        //Testing connectivity
        System.out.println(fName + "" + lName + " is " + age + " years old");
    }
}
