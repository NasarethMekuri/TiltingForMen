/*
* Copyright (c) 2015 Cymon343.
* All rights reserved.
*/
package Logical;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Cymon343
 */
public class HTMLElementsHolder
{
    
    public String getPageBeginning(String title, String heading)
    {
        StringBuilder sb = new StringBuilder();
        Scanner scan = null;
        try
        {
            scan = new Scanner(new File(getPath() + "/template.html"));
            
            while (scan.hasNextLine())
            {
                String theLine = scan.nextLine();
                
                if (theLine.contains("<title>"))
                {
                    sb.append("<title>" + title + "</title> ");
                }
                else if (theLine.contains("Heading line"))
                {
                    sb.append("<div class=\"mbr-header mbr-header--center mbr-header--std-padding\"> <h2 class=\"mbr-header__text\">"+ heading +"</h2></div>");
                    break;
                }
                else
                    sb.append(theLine);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("[SYSTEM]: File not found:\n" + ex);
        }
        finally
        {
            if (scan != null)
            {
                scan.close();
            }
        }
        
        return sb.toString();
    }
    
    public String getPageEnd()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("</div></div></div></div></div></section>");
        sb.append("<script src=\"assets/jquery/jquery.min.js\"></script>");
        sb.append("<script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>");
        sb.append("<script src=\"assets/smooth-scroll/SmoothScroll.js\"></script>");
        sb.append("<script src=\"assets/mobirise/js/script.js\"></script>");
        sb.append("</body></html>");
        
        return sb.toString();
    }
    
    /**
     *
     * @param heading
     * @param size 1= Largest, 2= second Largest ... 6= smallest
     * @return A String formatted as a HTML heading.
     */
    public String getHeading(String heading, int size)
    {
        if (size <1)
        {
            size = 1;
        }
        else if (size >6)
            {
                size = 6;
            }
        return "<div class=\"mbr-header mbr-header--center mbr-header--std-padding\"> <h"+ size +" class=\"mbr-header__text\">"+ heading +"</h"+ size +"></div>";
    }
    
    /**
     *
     * @param buttonName The name that will be displayed on the button.
     * @param target the target where a user will be directed when pressing the button.
     * @return A String formatted as a HTML form with nothing but a button.
     */
    public String getButton(String buttonName, String target)
    {
        return "<form action=\""+ target +"\">\n<div class=\"mbr-buttons mbr-buttons--center\">\n<button type=\"submit\" class=\"mbr-buttons__btn btn btn-sm btn-danger\">"+ buttonName +"</button>\n</div>\n</form>";
    }
    
    //**WARNING TODO FIXME : Duplicate code from UserServices!!! **\\
    /**
     * Used for testing on different systems
     * @return The local path for the web directory.
     */
    private String getPath()
    {
        String user = System.getProperty("user.name");
        if (!user.equals("bruger"))
        {
            if (!user.equals("Cymon343"))
            {
                return "C:\\Users\\Simon\\Documents\\GitHub\\TiltingForMen\\web"; //Server @SimonLaptop
            }
            else
            {
                return "C:\\Users\\Cymon343\\Documents\\GitHub\\TiltingForMen\\web"; //Server @SimonDesktop
            }
        }
        else
        {
            return "C:\\Homework\\3.Sem Project\\TiltingForMen\\web"; //Server @MortenLaptop
        }
    }
}
