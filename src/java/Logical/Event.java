package Logical;

import java.util.List;

public class Event
{
    private Gallow[] _gallows;
    private int _year, _numOfAvailableGallows, _numOfGallowWriters;
    private boolean _isActive;
    private List<Shirt> _unusedShirts;
    
    public Event(int year, int numOfgallowWriters, int numOfAvailableGallows, int maxParticipantsPrGallow, int minTotalBuffer, List<Shirt> shirts)
    {
        _year = year;
        _numOfGallowWriters = numOfgallowWriters;
        _numOfAvailableGallows = numOfAvailableGallows;
        _isActive = true;
        _gallows = new Gallow[_numOfAvailableGallows]; //Initializing gallows.
        populateGallows(maxParticipantsPrGallow, minTotalBuffer, year, _unusedShirts);
        _unusedShirts = shirts;
    }
    private void populateGallows(int maxParticipantsPrGallow, int minTotalBuffer, int year, List<Shirt> shirts)
    {
        PSA.getInstance().populateGallows(_gallows, _numOfGallowWriters, maxParticipantsPrGallow, minTotalBuffer, year, shirts);
        
        for (int i = 0; i < _gallows.length; i++)
        {
            //_gallows[i] = new Gallow();
        }
    }
        
    public Gallow[] getGallows() { return _gallows; }

    
    
    
}