package Logical;

import java.util.List;

public class Gallow
{
    private List<Batch> _batches;

    public Gallow(List<Batch> batches)
    {
        _batches = batches;
    }

    public List<Batch> getBatches() { return _batches; }
}
