package Logical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShirtsWrapper
{
    private List<Shirts> _shirtsAgeGroupOne;
    private List<Shirts> _shirtsAgeGroupTwo;
    private List<Shirts> _shirtsAgeGroupThree;
    
    public ShirtsWrapper()
    {
        _shirtsAgeGroupOne = new ArrayList<Shirts>();
        _shirtsAgeGroupOne.add(new Shirts("Pink", 24));
        _shirtsAgeGroupOne.add(new Shirts("Dark Blue", 24));
        _shirtsAgeGroupOne.add(new Shirts("Black", 24));
        _shirtsAgeGroupOne.add(new Shirts("Orange", 24));
        _shirtsAgeGroupTwo = new ArrayList<Shirts>();
        _shirtsAgeGroupTwo.add(new Shirts("Red", 24));
        _shirtsAgeGroupTwo.add(new Shirts("Turquoise", 24));
        _shirtsAgeGroupTwo.add(new Shirts("White", 24));
        _shirtsAgeGroupThree = new ArrayList<Shirts>();
        _shirtsAgeGroupThree.add(new Shirts("Green", 24));
        _shirtsAgeGroupThree.add(new Shirts("Grey", 24));
        _shirtsAgeGroupThree.add(new Shirts("Yellow", 24));
        
    }
    
    public String getColorForAgeGroup(int ageGroup, int amount)
    {
        List<Shirts> tmp = getListByAgeGroup(ageGroup);
        
        for (Shirts s : tmp)
        {
            if(s.getAmount() >= amount)
                return s.getColor();
        }
        return "No color";
    }
    
    public int getFirstAvailableNumberByColor(String color, int ageGrp)
    {
        List<Shirts> tmp = getListByAgeGroup(ageGrp);
        
        for (Shirts s : tmp)
        {
            if(s.getColor().equals(color))
                return 25 - s.getAmount();
        }
        return -1;
    }
    
    public void registerUseOfShirts(String color, int amount, int ageGrp)
    {
        List<Shirts> tmp = getListByAgeGroup(ageGrp);
        for (Shirts s : tmp)
        {
            if(s.getColor().equals(color))
                s.setAmount(s.getAmount() - amount);
        }
    }
    
    private List<Shirts> getListByAgeGroup(int ageGroup)
    {
        List<Shirts> tmp = null;
        if(ageGroup == 1)
            tmp = _shirtsAgeGroupOne;
        else if (ageGroup == 2)
            tmp = _shirtsAgeGroupThree;
        else
            tmp = _shirtsAgeGroupThree;
        return tmp;
    }
}
