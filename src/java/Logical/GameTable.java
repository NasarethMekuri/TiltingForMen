/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bruger
 */
public class GameTable
{
    private List<Participant> _participants;
    private Map<Participant, List<Boolean>> _scores;
    private String _color;
    
    public GameTable(List<Participant> participants)
    {
        _participants = participants;
        _scores = new HashMap<Participant, List<Boolean>>();
    }
    
    public GameTable()
    {
        _participants = new ArrayList<Participant>();
        _scores = new HashMap<Participant, List<Boolean>>();
    }
    
    public void assignColorAndNumbers(String color, int minNumber)
    {
        _color = color;
        
        for (Participant p : _participants)
        {
            p.setColor(color);
            p.setNumber(minNumber);
            minNumber++;
        }
    }
    
    public String[][] generateGameTableAsStrings(int numberOfRounds)
    {
        int cols = 4 + numberOfRounds;
        int rows = _participants.size() + 1;
        String[][] result = new String[cols][rows];
        
        //Table Headline - Mostly hardcoded stuff
        result[0][0] = "NR.";
        result[0][1] = "NAVN";
        //Add amount of colums equal to numberOfRounds
        for(int i = 1; i <= numberOfRounds; i++)
            result[0][1 + i] = "" + i;
        result[0][2 + numberOfRounds] = "Antal Ringe";
        result[0][3 + numberOfRounds] = "Placering";
        
        //Populating the list
        for(int i = 0; i < _participants.size(); i++)
        {
            result[i + 1][0] = "" + _participants.get(i).getNumber();
            result[i + 1][1] = _participants.get(i).getFullName();
        }
        return result;
    }

    public List<Participant> getParticipants() { return _participants; }
    public Map<Participant, List<Boolean>> getScores() { return _scores; }
    public String getColor() { return _color; }
}
