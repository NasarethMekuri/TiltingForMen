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

   
}
