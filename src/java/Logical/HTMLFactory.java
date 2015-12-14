package Logical;

import Persistence.DBHandler;

public class HTMLFactory
{
    private static HTMLFactory _instance;
    private HTMLFactory() {}
    private HTMLElementsHolder elements = new HTMLElementsHolder();
    
    /**
     * Dynamically creates a HTML formatted String containg a table based on the parameter given.
     * @param table A two dimentional array containing data to be displayed in a table.
     * @return A HTML formatted String containing the table.
     */
    private String generateTable(String[][] table)
    {
        StringBuilder sb = new StringBuilder();
        int col = table.length;
        int row = table[0].length;

        sb.append("<table border=\"1\">");
        for (int x = 0; x < col; x++)
        {
            sb.append("<tr>");
            for (int y = 0; y < row; y++)
            {
                sb.append("<td>");
                sb.append(table[x][y]);
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append(("</table>"));
        return sb.toString();
    }
    
    /**
     * Creates a table for a game of Tilting.
     * @param gallowNumber The desired gallow from which to generate the "table" String.
     * @return A String as Text/HTML MIME type.
     */
    public String createGallowTable(int gallowNumber)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(elements.getPageBeginning("Galge " + gallowNumber, "Galge " + gallowNumber));
        
        sb.append(generateTable(DBHandler.getInstance().getParticipantByGallow(gallowNumber))); 
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
     /**
     * Creates a table for a game of Tilting.
     * @param gallowNumber The desired gallow from which to generate the "table" String.
     * @return A String as Text/HTML MIME type.
     */
    public String createGallowTableForModerators(int gallowNumber)
    {
        //TODO: --> This should return the editable version.
        StringBuilder sb = new StringBuilder();
        
        sb.append(elements.getPageBeginning("Galge " + gallowNumber, "Galge " + gallowNumber));
        
        sb.append(generateTable(DBHandler.getInstance().getParticipantByGallow(gallowNumber))); 
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
    public String createGallowChoicePage(int numberOfGallows)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(elements.getPageBeginning("Vælg galge", "VÆLG GALGE"));
        sb.append("<form action=\"webresources/userservices/gallowchoice\" method=\"post\">\n");
        sb.append("<div><center><select name=\"gallow\">");
        
        for (int i = 0; i < numberOfGallows; i++)
        {
            sb.append("<option value=\"").append(i + 1).append("\"> Galge ").append(i + 1).append(" </option>");
        }
        sb.append("</select>"); 
        sb.append(elements.getButton("Vælg Galge"));
        sb.append("</center></div>"); //added </div> ******
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
         
    private String getBeginning(String title)
    {
        return "<!DOCTYPE html>\n" +
        "<html><head><meta charset=\"UTF-8\"><title> " + title + "</title>\n" +
        "</head><body>";
    }
    
    private String insertHeading(String heading)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"mbr-header mbr-header--center mbr-header--std-padding\"><h2 class=\"mbr-header__text\">");
        sb.append(heading);
        sb.append("</h2></div>");
        return sb.toString();
    }
    
    private String getEnd()
    {
        return "</body></html>";
    }
    
    public static HTMLFactory getInstance()
    {
        if(_instance == null)
        {
            _instance = new HTMLFactory();
        }
        return _instance;
    }   
}
