/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logical;

import java.util.List;

/**
 *
 * @author bruger
 */
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
}
