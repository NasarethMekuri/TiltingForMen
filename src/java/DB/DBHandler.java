/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try
        {
            c = DBConnectionFactory.getInstance().getConnection();

            CallableStatement cs = null;
            int rowCount = -1;
            String id = fName + lName + email;

            cs = c.prepareCall("{call create_participant(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, id);
            cs.setString(2, fName);
            cs.setString(3, lName);
            cs.setString(4, email);
            cs.setString(5, color);
            cs.setInt(6, number);
            cs.setInt(7, age);
            cs.setBoolean(8, lastMinute); //Last Minute
            cs.setBoolean(9, arrived); // Arrived
            
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
    
    
    
    public static synchronized DBHandler getInstance()
    {
        if(_instance == null)
            _instance = new DBHandler();
        return _instance;
    }
}
