package Logical;

public class Shirt
{
    private String _color;
    private int _number;
    
    public Shirt(String color, int number)
    {
        _color = color;
        _number = number;
    }

    public String getColor(){ return _color; }
    public int getNumber() { return _number; }

    public void setColor(String color){_color = color; }
    public void setNumber(int number) { _number = number;} 
    
    
}
