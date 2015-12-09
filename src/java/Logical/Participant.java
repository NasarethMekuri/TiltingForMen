package Logical;

import java.util.Calendar;

public class Participant
{
    private String _pID, _firstName, _lastName, _email, _color; 
    private int _age, _year, _number;
    private boolean _lastMinute, _arrived;

    public Participant(String pID, String firstName, String lastName, String email, String color, int number, int age, boolean lastMinute, boolean arrived, int year)
    {
        _pID = pID;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _color = color;
        _number = number;
        _age = age;
        _lastMinute = lastMinute;
        _arrived = arrived;
        _year = year;
    }

    //Getters
    public String getpID() { return _pID; }
    public String getFirstName() { return _firstName; }
    public String getLastName() { return _lastName; }
    public String getEmail() { return _email; }
    public String getColor() { return _color; }
    public int getNumber() { return _number; }
    public int getAge() { return _age; }
    public boolean isLastMinute() { return _lastMinute; }
    public boolean hasArrived() { return _arrived; }
    public int getYear() { return _year; }

    //Setters
    public void setPID(String pID) {_pID = pID; }
    public void setFirstName(String firstName) { _firstName = firstName; }
    public void setLastName(String lastName) { _lastName = lastName; }
    public void setEmail(String email) { _email = email; }
    public void setColor(String color) { _color = color; }
    public void setNumber(int number) { _number = number; }
    public void setAge(int age) { _age = age; }
    public void setLastMinute(boolean lastMinute) { _lastMinute = lastMinute; }
    public void setArrived(boolean arrived) { _arrived = arrived; }
    public void setYear(int year) { _year = year; }
}
