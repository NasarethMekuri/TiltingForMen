/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author bruger
 */
public class DBHandler
{
    private static DBHandler _instance;
    
    private DBHandler() { } //Private constructor
    
    public void createParticipant(String fName, String lName, String email, String color, int number, 
                                  int age, boolean lastMinute, boolean arrived)
    {
        Connection c = null;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try
        {
            c = DBConnectionFactory.getInstance().getConnection();

            CallableStatement cs = null;
            int rowCount = -1;
            String id = fName + lName + email + year;

            cs = c.prepareCall("{call create_participant(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, id);
            cs.setString(2, fName);
            cs.setString(3, lName);
            cs.setString(4, email);
            cs.setString(5, color);
            cs.setInt(6, number);
            cs.setInt(7, age);
            cs.setBoolean(8, lastMinute); //Last Minute
            cs.setBoolean(9, arrived); // Arrived
            cs.setInt(10, year);
            
            rowCount = cs.executeUpdate();
            cs.close();
            
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Failed to close connection! @DBHandler createParticipant\n" + ex.getLocalizedMessage());
            }
        }
    }
    
    public boolean hasParticipant(String fName, String lName, String email)
    {
        Connection c = null;
        String result = "";
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try
        {
            c = DBConnectionFactory.getInstance().getConnection();

            CallableStatement cs = null;
            String id = fName + lName + email + year;

            cs = c.prepareCall("{call select_participant(?)}");
            cs.setString(1, id);
            
            ResultSet rs = cs.executeQuery();

            while(rs.next())
            {
                result += rs.getString(1);
            }
            cs.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Failed to close connection! @DBHandler createParticipant\n" + ex.getLocalizedMessage());
            }
        }
        return result != ""; //empty means no participant thus return false.
    }
    
    public void createUser(String fName, String lName, String email, String phoneNR, int role, String password)
    {
        Connection c = null;
        try
        {
            c = DBConnectionFactory.getInstance().getConnection();

            CallableStatement cs = null;
            int rowCount = -1;
            String id = fName + lName + email;

            cs = c.prepareCall("{call create_participant(?,?,?,?,?,?,?)}");
            cs.setString(1, id); //TODO: Agree on a PK
            cs.setString(2, fName);
            cs.setString(3, lName);
            cs.setString(4, email);
            cs.setString(5, phoneNR);
            cs.setInt(6, role);
            cs.setString(7, password); //TODO: Encrypt Password
            
            rowCount = cs.executeUpdate(); //Return value currently not used?
            cs.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Failed to close connection! @DBHandler createParticipant\n" + ex.getLocalizedMessage());
            }
        }
    }
     
    /**
     * Retrieves a user from the database. Used for logging into the system.
     * @param email The UserID (the email address of the user)
     * @return a String array containing: [0]= role [1]= Password.
     */
    public String[] getUserByEmail(String email)
    {
        Connection c = null;
        String[] result = new String[2];
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try
        {
            
            c = DBConnectionFactory.getInstance().getConnection();

            CallableStatement cs = null;
            

            cs = c.prepareCall("{call select_user_by_email(?)}");
            cs.setString(1, email);
            
            ResultSet rs = cs.executeQuery();

            while(rs.next())
            {
                result[0] = String.valueOf(rs.getInt(6)); //role
                result[1] = rs.getString(7);//password
            }
            cs.close();
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Failed to close connection! @DBHandler getUserByEmail\n" + ex.getLocalizedMessage());
            }
        }
        return result;
    }
    
    public String[][] getParticipantByGallow(int gallowNumber)
    {
        //TODO: Remove Dummy code and make this work!
        int col = 3;
        int row = 5;
        String[][] temp = new String[col][row];
        
        for (int x = 0; x < col; x++)
        {
            for (int y = 0; y < row; y++)
            {
                temp[x][y] = "Galge " + gallowNumber + " " + x + y;
            }
        }
        return temp;
    }

    public static synchronized DBHandler getInstance()
    {
        if(_instance == null)
            _instance = new DBHandler();
        return _instance;
    }

   
}
