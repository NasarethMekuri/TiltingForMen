/*
 * Copyright (c) 2015 Cymon343.
 * All rights reserved.
 */
package Logical;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Cymon343
 */
public class HTMLFileCreator
{
    
     public static String createHTMLPage(String pageName, String HTML)
    {
        String path = getPath() + "/"+ pageName +".html";
        File f = new File(path);
            OutputStreamWriter writer = null;
            
            try
            {
                writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
                writer.write(HTML);
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try
                {
                    writer.close();
                }
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            return pageName;
    }
     
       /**
     * Used for testing on different systems
     * @return The local path for the web directory.
     */
     public static String getPath()
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
