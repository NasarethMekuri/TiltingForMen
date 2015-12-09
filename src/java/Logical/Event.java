package Logical;

public class Event
{
    private Gallow[] _gallows;
    private int _year, _numOfAvailableGallows, _numOfGallowWriters;
    private boolean _isActive;
    
    public Event(int year, int numOfgallowWriters, int numOfAvailableGallows, int maxParticipantsPrGallow, int minTotalBuffer)
    {
        _year = year;
        _numOfGallowWriters = numOfgallowWriters;
        _numOfAvailableGallows = numOfAvailableGallows;
        _isActive = true;
        _gallows = new Gallow[_numOfAvailableGallows];
        populateGallows(maxParticipantsPrGallow, minTotalBuffer, year);
    }
    private void populateGallows(int maxParticipantsPrGallow, int minTotalBuffer, int year)
    {
        PSA.getInstance().populateGallows(_gallows, _numOfGallowWriters, maxParticipantsPrGallow, minTotalBuffer, year);
        
        for (int i = 0; i < _gallows.length; i++)
        {
            //_gallows[i] = new Gallow();
        }
    }
        
    public Gallow[] getGallows() { return _gallows; }

    
    
    
}
