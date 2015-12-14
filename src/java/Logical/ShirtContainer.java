package Logical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShirtContainer
{
    private List<Shirt> _shirtsAgeGroupOne;
    private List<Shirt> _shirtsAgeGroupTwo;
    private List<Shirt> _shirtsAgeGroupThree;
    
    public ShirtContainer()
    {
        _shirtsAgeGroupOne = new ArrayList<Shirt>();
        _shirtsAgeGroupOne.add(new Shirt("Pink", 24));
        _shirtsAgeGroupOne.add(new Shirt("Dark Blue", 24));
        _shirtsAgeGroupOne.add(new Shirt("Black", 24));
        _shirtsAgeGroupOne.add(new Shirt("Orange", 24));
        _shirtsAgeGroupTwo = new ArrayList<Shirt>();
        _shirtsAgeGroupTwo.add(new Shirt("Red", 24));
        _shirtsAgeGroupTwo.add(new Shirt("Turquoise", 24));
        _shirtsAgeGroupTwo.add(new Shirt("White", 24));
        _shirtsAgeGroupThree = new ArrayList<Shirt>();
        _shirtsAgeGroupThree.add(new Shirt("Green", 24));
        _shirtsAgeGroupThree.add(new Shirt("Grey", 24));
        _shirtsAgeGroupThree.add(new Shirt("Yellow", 24));
        
    }
    
    public String getColorForAgeGroup(int ageGroup, int amount)
    {
        List<Shirt> tmp = getListByAgeGroup(ageGroup);
        
        for (Shirt s : tmp)
        {
            if(s.getAmount() >= amount)
                return s.getColor();
        }
        return "No color";
    }
    
    public int getFirstAvailableNumberByColor(String color, int ageGrp)
    {
        List<Shirt> tmp = getListByAgeGroup(ageGrp);
        
        for (Shirt s : tmp)
        {
            if(s.getColor().equals(color))
                return 25 - s.getAmount();
        }
        return -1;
    }
    
    public void registerUseOfShirts(String color, int amount, int ageGrp)
    {
        List<Shirt> tmp = getListByAgeGroup(ageGrp);
        for (Shirt s : tmp)
        {
            if(s.getColor().equals(color))
                s.setAmount(s.getAmount() - amount);
        }
    }
    
    private List<Shirt> getListByAgeGroup(int ageGroup)
    {
        List<Shirt> tmp = null;
        if(ageGroup == 1)
            tmp = _shirtsAgeGroupOne;
        else if (ageGroup == 2)
            tmp = _shirtsAgeGroupThree;
        else
            tmp = _shirtsAgeGroupThree;
        return tmp;
    }
}
