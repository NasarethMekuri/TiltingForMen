package Logical;

import java.util.ArrayList;
import java.util.List;

public class Gallow
{
    private List<GameTable> _games;

    public Gallow()
    {
        _games = new ArrayList<GameTable>();
    }
    
    public Gallow(List<GameTable> games)
    {
        _games = games;
    }

    public List<GameTable> getGames() { return _games; }
}
