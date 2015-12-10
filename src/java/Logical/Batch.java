package Logical;

import java.util.ArrayList;
import java.util.List;

public class Batch
{
    private List<GameTable> _games;
    
    public Batch(List<GameTable> games)
    {
        _games = games;
    }
    
    public Batch()
    {
        _games = new ArrayList<GameTable>();
    }

    public List<GameTable> getGames() { return _games; }
}
