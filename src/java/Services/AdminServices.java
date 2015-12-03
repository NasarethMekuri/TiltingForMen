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
@Path("adminservices")
public class AdminServices
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminServices
     */
    public AdminServices()
    {
    }

    /**
     * Retrieves representation of an instance of Services.AdminServices
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
     * PUT method for updating or creating an instance of AdminServices
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content)
    {
    }
}
