package Logical;

import Persistence.DBHandler;
import java.util.ArrayList;
import java.util.Collections;
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
    
    public void populateGallows(Gallow[] gallows, int maxParticipantsPrGallow,  int year)
    {
        List<Participant> participants = DBHandler.getInstance().getParticipantsByYear(year);
        List<Participant> ageGrp1 = new ArrayList<Participant>();
        List<Participant> ageGrp2 = new ArrayList<Participant>();
        List<Participant> ageGrp3 = new ArrayList<Participant>();
        ShirtContainer shirts = new ShirtContainer();
        
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
        int ageGrp = 0;
        //For each sublist in the list. (For each age group)
        for (Iterator<List<Participant>> it1 = theList.iterator(); it1.hasNext();)
        {
            ageGrp++;
            List<Participant> currentAgeGrp = it1.next();
            sortByLastname(currentAgeGrp);
            int participantsPrGallow = currentAgeGrp.size() / gallows.length;
            
            //Participants pr gallow is either participants / gallows, or participants / maxParticipantsPrGallow + 1
            if(participantsPrGallow > maxParticipantsPrGallow)
                participantsPrGallow = currentAgeGrp.size() / maxParticipantsPrGallow + 1;
            
            //Creating games
            List<GameTable> games = new ArrayList<GameTable>();
            int numberOfGames = currentAgeGrp.size() / participantsPrGallow + 1;
            
            for (int i = 0; i < numberOfGames; i++)
            {
                if(currentAgeGrp.size() > participantsPrGallow)
                {
                    games.add(new GameTable(currentAgeGrp.subList(0, participantsPrGallow)));
                    currentAgeGrp.removeAll(currentAgeGrp.subList(0, participantsPrGallow));
                }
                else
                {
                    games.add(new GameTable((currentAgeGrp))); //TODO: Handle risk of adding only one Participant to a GameTable.
                    currentAgeGrp.clear();
                }
            }
            //Assigning Shirts
            for (GameTable game : games)
            {
                int numberOfParticipants = game.getParticipants().size();
                String color = shirts.getColorForAgeGroup(ageGrp, numberOfParticipants);
                int minAvailableNumber = shirts.getFirstAvailableNumberByColor(color, ageGrp);
                shirts.registerUseOfShirts(color, numberOfParticipants, ageGrp);
                game.assignColorAndNumbers(color, minAvailableNumber);
            }
            
            //Adding Games to Gallows
            int index = 0;
            while(games.size() > 0)
            {
                if(gallows[index] == null)
                {
                    gallows[index] = new Gallow();
                }
                gallows[index].getGames().add(games.get(0));
                games.remove(0);
                index++;
                if(index == gallows.length)
                {
                    index = 0;
                }
            } 
        }
    }

    /**
     * Sorts Participants by Lastname as pr. Brians Wishes.
     * @param shirts The list to be sorted
     */
    private void sortByLastname(List<Participant> participants)
    {
        Collections.sort(participants, new Comparator<Participant>() {

            @Override
            public int compare(Participant p1, Participant p2)
            {
                if(p1.getLastName().equalsIgnoreCase(p2.getLastName()))
                    return 0;
                String one = p1.getLastName();
                String two = p2.getLastName();
                while(true)
                {
                    for (int i = 0; i < one.length(); i++)
                    {
                        if(two.length() == i)
                            return 1;
                        if(one.charAt(i) == two.charAt(i))
                            continue;
                        if((int)one.charAt(i) > (int)two.charAt(i))
                            return 1;
                        else
                            return -1;
                    }
                }
            }
        });
    }
}
