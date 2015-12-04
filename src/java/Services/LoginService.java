/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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
    
    @Path("/login")
    @POST
    public Response loginUser(@FormParam("username") String userName, @FormParam("pw") String pw)
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9]\\w{5,50}"); //Special characters are not allowed (including æøå ÆØÅ)
        Matcher compiledPassword = p.matcher(pw);
        URI tryagainPage = null;
        try
        {
            tryagainPage = new URI("http://127.0.0.1:8080/TiltingForMen/login_error.html"); //THA ... help!
        }
        catch (URISyntaxException ex)
        {
            System.out.println("Error:" + ex);
        }
                
        
        /**
        //Testing connectivity
        System.out.println(userName + " tried to log in.");
        */
        
        /*
                TODO Validate password, 
                verify login 
                    -by email and password
                    -by Role. (role 1 = admin | role 2 = user, role 0 = reserved for system owner.)
        */
        
        if (compiledPassword.matches())
        {
            //TODO: We still need to figure out how this works.
            return null;
        }
        else
        {
            //TODO: Redirect...
            return Response.temporaryRedirect(tryagainPage).build();
        }
        
        
        
    }
}
