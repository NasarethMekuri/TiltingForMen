package Logical;

import java.util.ArrayList;
import java.util.List;

public class Event
{
    private Gallow[] _gallows;
    private int _year, _numOfAvailableGallows, _numOfGallowWriters;
    private boolean _isActive;
    
    public Event(int year, int numOfAvailableGallows, int maxParticipantsPrGallow)
    {
        _year = year;
        _numOfAvailableGallows = numOfAvailableGallows;
        _isActive = true;
        _gallows = new Gallow[_numOfAvailableGallows]; //Initializing gallows.
        populateGallows(maxParticipantsPrGallow, year);
    }
    
    private void populateGallows(int maxParticipantsPrGallow,  int year)
    {
        PSA.getInstance().populateGallows(_gallows, maxParticipantsPrGallow,  year);
    }
    
    public String[] generateParticipantNameList()
    {
        List<String> result = new ArrayList<String>();
        for (Gallow g : _gallows)
        {
            for (GameTable game : g.getGames())
            {
                String colorLine = game.getColor();
                result.add(colorLine.toUpperCase());
                for (Participant p : game.getParticipants())
                {
                    result.add(p.getFullName());
                }
            }
        }
        return (String[])result.toArray();
    }
    
    //Getters
    public Gallow[] getGallows() { return _gallows; }
    public boolean isActive() { return _isActive; }
}
