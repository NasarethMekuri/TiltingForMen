/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logical;

/**
 *
 * @author bruger
 */
public class EventManager
{
    private static EventManager _instance;
    private Event _event;
    
    private EventManager(){}
    
    public void startNewEvent(int year, int numOfgallowWriters, int numOfAvailableGallows, int maxParticipantsPrGallow, int minTotalBuffer)
    {
        _event = new Event(year, numOfgallowWriters, numOfAvailableGallows, maxParticipantsPrGallow, minTotalBuffer);
        
    }
    
    public static EventManager getInstance()
    {
        if(_instance == null)
        {
            _instance = new EventManager();
        }
        return _instance;
    }
}
