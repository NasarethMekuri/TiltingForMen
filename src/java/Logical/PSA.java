/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logical;

import Persistence.DBHandler;
import java.util.ArrayList;
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
        for (Iterator<List<Participant>> it1 = theList.iterator(); it1.hasNext();)
        {
            List<Participant> subList = it1.next();
            for (Iterator<Participant> it2 = subList.iterator(); it2.hasNext();)
            {
                Participant p = it2.next();
                //TODO: Continue tomorrow (Divide Colors and numbers)
                //TODO2: Colors -Where the hell are they??? :-)
            }
            
        }
    }
}
