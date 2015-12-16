package Logical;

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
        for (int y = 0; y < row; y++)
        {
            sb.append("<tr>");
            for (int x = 0; x < col; x++)
            {
                if(y == 0)
                {
                    sb.append("<th width=\"200\"><center>");
                    if(table[x][y] != null)
                        sb.append(table[x][y]);
                    else
                        sb.append("");
                    sb.append("</center></th>");
                }
                else
                {
                    sb.append("<td>");
                    if(table[x][y] != null)
                        sb.append(table[x][y]);
                    else
                        sb.append("");
                    sb.append("</td>");
                }
            }
            sb.append("</tr>");
        }
        sb.append(("</table>"));
        return sb.toString();
    }
    
    /**
     * Dynamically creates a HTML formatted String containg a table for logging scores, based on the parameter given.
     * @param table A two dimentional array containing data to be displayed in a table.
     * @return A HTML formatted String containing the table.
     */
    private String generateScoreTable(String[][] table)
    {
        StringBuilder sb = new StringBuilder();
        int col = table.length;
        int row = table[0].length;

        sb.append("<table border=\"1\">");
        for (int y = 0; y < row; y++)
        {
            sb.append("<tr>");
            for (int x = 0; x < col; x++)
            {
                if(y == 0)
                {
                    sb.append("<th width=\"200\"><center>");
                    if(table[x][y] != null)
                        sb.append(table[x][y]);
                    else
                        sb.append("");
                    sb.append("</center></th>");
                }
                else
                {
                    sb.append("<td>");
                    if(x > 1 && x < col - 2)
                    {
                        sb.append("<center>" + elements.getCheckBox("" + x, "" + x) + "</center>");
                    }
                    else
                    {
                        if(table[x][y] != null)
                            sb.append(table[x][y]);
                        else
                            sb.append("");
                    }
                    sb.append("</td>");
                }
            }
            sb.append("</tr>");
        }
        sb.append(("</table>"));
        return sb.toString();
    }
    
    /**
     * Dynamically creates a HTML formatted String containg a One column table based on the parameter given.
     * @param table A one dimentional array containing data to be displayed in a table.
     * @return A HTML formatted String containing the table.
     */
    private String generateTable(String[] table)
    {
        StringBuilder sb = new StringBuilder();
        int col = 1;
        int row = table.length;

        sb.append("<table border=\"1\">");
        for (int i = 0; i < row; i++)
        {
            sb.append("<tr><td>");
            sb.append(table[i]);
            sb.append("</td></tr>");
        }
        sb.append(("</table>"));
        return sb.toString();
    }
    
    public String createCurrentParticipantsTable()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(elements.getPageBeginning("Deltager Liste ", "Deltager Liste", false));
        String[] pList = EventManager.getInstance().getCurrentParticipantList();
        sb.append(generateTable(pList));
        
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
    /**
     * Unused
     * @param game
     * @return 
     */
    public String createGameTable(GameTable game)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(elements.getPageBeginning("Game Table ", "Game Table", true)); //TODO: Use proper data from gametable
        String[][] gList = game.generateGameTableAsStrings(6);
        sb.append(generateTable(gList));
        
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
    /**
     * Creates a read-only table for a game of Tilting.
     * @param gallowNumber The desired gallow from which to generate the "table" String.
     * @return A String as Text/HTML MIME type.
     */
    public String createGameTable(int gallowNumber)
    {
        gallowNumber --; //Matching 1-based index to 0-based index for List.
        StringBuilder sb = new StringBuilder(); 
        int numberOfRounds = 6; //TODO: Produce logic for this.
        int currentGame = 0; //ToDO: Produce logic - Should be incremented by endgame Button. Alternatively keep at zero, and simply remove game on EndGame button event.
        sb.append(elements.getPageBeginning("Game Table ", "Game Table", false)); //TODO: Use proper data from gametable
        String[][] gList = EventManager.getInstance().getEvent().getGallows()[gallowNumber].getGames().get(currentGame).generateGameTableAsStrings(numberOfRounds);
        sb.append(generateTable(gList));
        
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
     /**
     * Creates an editable table for a game of Tilting.
     * @param gallowNumber The desired gallow from which to generate the "table" String.
     * @return A String as Text/HTML MIME type.
     */
    public String createGameTableForModerators(int gallowNumber)
    {
        gallowNumber --; //Matching 1-based index to 0-based index for List.
        StringBuilder sb = new StringBuilder(); 
        int numberOfRounds = 6; //TODO: Produce logic for this.
        int currentGame = 0; //ToDO: Produce logic - Should be incremented by endgame Button. Alternatively keep at zero, and simply remove game on EndGame button event.
        sb.append(elements.getPageBeginning("Game Table ", "Game Table", false)); //TODO: Use proper data from gametable
        String[][] gList = EventManager.getInstance().getEvent().getGallows()[gallowNumber].getGames().get(currentGame).generateGameTableAsStrings(numberOfRounds);
        sb.append(generateScoreTable(gList));
        
        sb.append(elements.getPageEnd());
        
        return sb.toString();
    }
    
    public String createGallowChoicePage(int numberOfGallows, boolean mod)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(elements.getPageBeginning("Vælg galge", "VÆLG GALGE", false));
        String formDeclaration = mod? "<form action=\"webresources/moderatorservices/gallowchoice\" method=\"post\">\n" : "<form action=\"webresources/userservices/gallowchoice\" method=\"post\">\n";
        sb.append(formDeclaration);
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
    
    public static HTMLFactory getInstance()
    {
        if(_instance == null)
        {
            _instance = new HTMLFactory();
        }
        return _instance;
    }   
}
