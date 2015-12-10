package Logical;

import java.util.ArrayList;
import java.util.List;

public class Gallow
{
    private List<Batch> _batches;

    public Gallow(List<Batch> batches)
    {
        _batches = batches;
    }
    
    public Gallow()
    {
        _batches = new ArrayList<Batch>();
    }

    public List<Batch> getBatches() { return _batches; }
}
