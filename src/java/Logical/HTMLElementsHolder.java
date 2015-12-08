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
    private String _pageBeginning, _pageEnd, _heading, _formBeginning, _formEnd, _formButton;

    public HTMLElementsHolder()
    {
      
    }

    public String getPageBeginning(String title, String heading)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            Scanner scan = new Scanner(new File(getPath() + "/template.html"));
            
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
            System.out.println("[SYSTEM:] File not found:\n" + ex);
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

    public String getHeading(String heading)
    {
        return "<div class=\"mbr-header mbr-header--center mbr-header--std-padding\"> <h2 class=\"mbr-header__text\">"+ heading +"</h2></div>";
    }
    
    public String getButton(String buttonName)
    {
        return "<button type=\"submit\" class=\"mbr-buttons__btn btn btn-sm btn-danger\">" + buttonName + "</button>";
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
                return "SimonLaptopString";
            }
            else
            {
                return "C:\\Users\\Cymon343\\Documents\\GitHub\\TiltingForMen\\web";
            }
        }
        else
        {
            return "C:\\Homework\\3.Sem Project\\TiltingForMen\\web";
        }
    }
}
