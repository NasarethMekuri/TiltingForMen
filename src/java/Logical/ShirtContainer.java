package Logical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShirtContainer
{
    private List<Shirt> _shirts;
    private Map<String, Integer> countByColor = new HashMap<String, Integer>();
    private List<String> _availableColors;
    
    public ShirtContainer(List<Shirt> shirts)
    {
        _shirts = new ArrayList<Shirt>();
        _availableColors = new ArrayList<String>();
        putList(shirts);
        sort();
    }
    
    public void putList(List<Shirt> shirts)
    {
        for (Shirt s : shirts)
        {
            put(s);
        }
    }
    
    public void put(Shirt shirt)
    {
        if(countByColor.containsKey(shirt.getColor())) 
        {
            int currentAmount = countByColor.get(shirt.getColor());
            countByColor.put(shirt.getColor(), currentAmount++);
        }
        else
        {
            countByColor.put(shirt.getColor(), 1);
            _availableColors.add(shirt.getColor());
        }
        _shirts.add(shirt);
        sort();
    }
    
    public Shirt pullByColor(String color)
    {
        Shirt shirt = null;
        
        for (int i = 0; i < _shirts.size(); i++)
        {
            if(_shirts.get(i).getColor().equals(color))
            {
                shirt = _shirts.get(i);
                _shirts.remove(i);
            }
        }
        if(shirt != null) //if color exists
        {
            int tmp = countByColor.get(color);
            countByColor.put(color, tmp--);
        }
        return shirt;
    }
    
    public int getAmountByColor(String color)
    {
        return countByColor.get(color);
    }
    
    public String getColorByIndex(int index)
    {
        return _shirts.get(index).getColor();
    }
    
    public List<String> getAvailableColors()
    {
        return _availableColors;
    }
    
    /**
     * Sorts shirts by color. The order is not taken into account, it simply divides them in groups.
     * @param shirts The list to be sorted
     */
    private void sort()
    {
        Collections.sort(_shirts, new Comparator<Shirt>()
        {
            @Override
            public int compare(Shirt o1, Shirt o2)
            {
                //TODO: Test
                return o1.getColor().hashCode() - (o2.getColor().hashCode());
            }
        });
    }
}
