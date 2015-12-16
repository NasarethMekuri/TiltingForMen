package Logical;

public class EventManager
{
    private static EventManager _instance;
    private Event _event;
    
    private EventManager(){}
    
    public void startNewEvent(int year, int numOfAvailableGallows, int maxParticipantsPrGallow)
    {
        _event = new Event(year, numOfAvailableGallows, maxParticipantsPrGallow);
    }
    
    public static EventManager getInstance()
    {
        if(_instance == null)
        {
            _instance = new EventManager();
        }
        return _instance;
    }
    
    public String[] getCurrentParticipantList()
    {
        return _event.generateParticipantNameList();
    }
    
    public Event getEvent() { return _event; }
}
