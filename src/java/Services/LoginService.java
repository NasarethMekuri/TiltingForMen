/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Services;

import Persistence.DBHandler;
import java.net.URI;
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
        String tryAgainURL = "http://127.0.0.1:8080/TiltingForMen/login_error.html";
        
        /**
         * //Testing connectivity
         * System.out.println(userName + " tried to log in.");
         */
        
        if (compiledPassword.matches()) //Password regex check
        {            
            //[0]=role [1]=Password.
            String[] verificationData = DBHandler.getInstance().getUserByEmail(userName);
            
            if (pw.equals(verificationData[1]))
            {
                switch (Integer.parseInt(verificationData[0]))
                {
                    case 0:
                        //role 0 = reserved for system owner - atm sysOwner will be redirected to tryagain. 
                        return Response.seeOther(URI.create(tryAgainURL)).build();
                    case 1:
                        //TODO: redirect to admin page -Same rights as moderator? (role built for future?)
                        break;
                        case 2:
                        //TODO: redirect to user/Moderator page (gallowChoice for Mods?)
                        break;
                }
            }
            else
            {
                return Response.seeOther(URI.create(tryAgainURL)).build();
            }
        }
        else
        {
            return Response.seeOther(URI.create(tryAgainURL)).build();
        }
        return Response.seeOther(URI.create(tryAgainURL)).build();
    }
    
}
