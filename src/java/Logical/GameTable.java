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

    public List<Participant> getParticipants() { return _participants; }
    public Map<Participant, List<Boolean>> getScores() { return _scores; }
}
