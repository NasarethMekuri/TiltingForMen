package Logical;

public class Shirts
{
    private String _color;
    private int _amount;
    
    public Shirts(String color, int number)
    {
        _color = color;
        _amount = number;
    }

    public String getColor(){ return _color; }
    public int getAmount() { return _amount; }

    public void setColor(String color){_color = color; }
    public void setAmount(int number) { _amount = number;}
}
