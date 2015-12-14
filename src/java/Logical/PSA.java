package Logical;

import Persistence.DBHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author bruger
 */
public class PSA
{
    private static PSA _instance;
    
    private PSA(){}
    
    public static PSA getInstance()
    {
        if(_instance == null)
        {
            _instance = new PSA();
        }     
        return _instance;
    }
    
    public void populateGallows(Gallow[] gallows, int numberOfWriters, int maxParticipantsPrGallow, int minTotalBuffer, int year)
    {
        List<Participant> participants = DBHandler.getInstance().getParticipantsByYear(year);
        List<Participant> ageGrp1 = new ArrayList<Participant>();
        List<Participant> ageGrp2 = new ArrayList<Participant>();
        List<Participant> ageGrp3 = new ArrayList<Participant>();
        //Map<String, Integer> colorTracker = new HashMap<String, Integer>();
        //List<String> availableColors = new ArrayList<String>();
        
        //Divide Participants into age groups.
        for (Iterator<Participant> it = participants.iterator(); it.hasNext();)
        {
            Participant next = it.next();
            switch (next.getAge())
            {
                case 1:
                    ageGrp1.add(next);
                    break;
                case 2:
                    ageGrp2.add(next);
                    break;
                case 3:
                    ageGrp3.add(next);
                    break;
            }
        }
        
        List<List<Participant>> theList = new ArrayList<List<Participant>>();
        //For each sublist in the list. (For each age group)
        for (Iterator<List<Participant>> it1 = theList.iterator(); it1.hasNext();)
        {
            List<Participant> currentAgeGrp = it1.next();
            List<Batch> batches = new ArrayList<Batch>();
            int maxParticipantsPrGame = maxParticipantsPrGallow - (minTotalBuffer / gallows.length);
            int numberOfGames = currentAgeGrp.size() / maxParticipantsPrGame + 1;
            List<GameTable> games = new ArrayList<GameTable>();
            
            //Creating games
            for (int i = 0; i < numberOfGames; i++)
            {
                if(currentAgeGrp.size() > maxParticipantsPrGame)
                {
                    games.add(new GameTable(currentAgeGrp.subList(0, maxParticipantsPrGame)));
                    currentAgeGrp.removeAll(currentAgeGrp.subList(0, maxParticipantsPrGame));
                }
                else
                {
                    games.add(new GameTable((currentAgeGrp))); //TODO: Handle risk of adding only one Participant to a GameTable.
                    currentAgeGrp.clear();
                }
            }
            
            //Creating Batches
            int numberOfBatches = numberOfGames / gallows.length + 1; // equals number of lists of games.
            int gamesPrBatch = numberOfGames / numberOfBatches + 1;
                        
            for (int i = 0; i < numberOfBatches; i++) //Creating batches
            {
                batches.add(new Batch());
                
                for (int j = 0; j < gamesPrBatch; j++) //Filling batches with GameTables
                {
                    batches.get(i).getGames().add(games.get(0));
                    games.remove(0);
                }
            }
            
            //Adding Bathes to Gallows
            int index = 0;
            while(batches.size() > 0)
            {
                if(gallows[index] == null)
                {
                    gallows[index] = new Gallow();
                }
                gallows[index].getBatches().add(batches.get(0));
                batches.remove(0);
                index++;
                if(index >= gallows.length)
                {
                    index = 0;
                }
            }
            
            //Assigning Shirts
            for (Gallow gallow : gallows)
            {
                for (Batch batch : gallow.getBatches())
                {
                    for (GameTable game : batch.getGames())
                    {
                        int currentColorIndex = 0;
                        //String currentColor = shirtContainer.getColorByIndex(currentColorIndex); //The color of the first Shirt.
                        //int amount = shirtContainer.getAmountByColor(currentColor); //Amount of THAT color.
                        //if(amount >= game.getParticipants().size())
                        {
                           // String color = shirtContainer.getAvailableColors().get(currentColorIndex);
                            //assignShirts(game.getParticipants(), color);
                            //TODO: Get list from container based on Color. Or fix inside assing shirts.. probably the latter.
                        }
                    }
                }
            }
            
            
        }
    }
        
    private void assignShirts(List<Participant> participants, String color)
    {
        for (Participant p : participants)
        {
            
            
        }
        
    }
    
    private List<Shirt> getColor(String color, List<Shirt> allShirts)
    {
        List<Shirt> coloredShirts = new ArrayList<Shirt>() {};
        
        for (int i = 0; i < allShirts.size(); i++)
        {
            if(allShirts.get(i).getColor().equals(color))
            {
                coloredShirts.add(allShirts.get(i));
                allShirts.remove(i);
                i--;
            }
        }
        return coloredShirts;
    }

    /**
     * Sorts shirts by color. The order is not taken into account, it simply divides them in groups.
     * @param shirts The list to be sorted
     */
    private void divideShirtsIntoColors(List<Shirt> shirts)
    {
        Shirt[] shirtArr = (Shirt[])shirts.toArray();
        
        Arrays.sort(shirtArr, new Comparator<Shirt>()
        {
            @Override
            public int compare(Shirt o1, Shirt o2)
            {
                return o1.getColor().hashCode() - o2.getColor().hashCode();
            }
        });
    }
}
