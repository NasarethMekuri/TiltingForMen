/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logical;

import Persistence.DBHandler;
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
    private boolean _isGameOver = false;
    
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
            writeToDB(color, minNumber, p.getpID());
            minNumber++;
        }
    }
    
    public String[][] generateGameTableAsStrings(int numberOfRounds)
    {
        int col = 4 + numberOfRounds;
        int row = _participants.size() + 1;
        String[][] result = new String[col][row];
        
        //Table Headline - Mostly hardcoded stuff
        result[0][0] = "NR.";
        result[1][0] = "NAVN";
        //Add amount of colums equal to numberOfRounds
        for(int i = 1; i <= numberOfRounds; i++)
        {
            result[1 + i][0] = "" + i;
        }
        result[2 + numberOfRounds][0] = "Antal Ringe";
        result[3 + numberOfRounds][0] = "Placering";
        
        //Populating the list
        for(int i = 0; i < _participants.size(); i++)
        {
            result[0][i + 1] = "" + _participants.get(i).getNumber();
            result[1][i + 1] = _participants.get(i).getFullName();
        }
        return result;
    }
    
    private void writeToDB(String color, int minNumber, String pID)
    {
        DBHandler.getInstance().updateParticipantColorAndNumber(pID, color, minNumber);
    }

    //Getters
    public List<Participant> getParticipants() { return _participants; }
    public Map<Participant, List<Boolean>> getScores() { return _scores; }
    public String getColor() { return _color; }
    public boolean isIsGameOver() { return _isGameOver; }

    //Setters
    public void setIsGameOver(boolean isGameOver) { _isGameOver = isGameOver; }
}
