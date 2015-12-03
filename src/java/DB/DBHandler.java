/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author bruger
 */
public class DBHandler
{
    public void createParticipant(String fName, String lName, String age, String email) throws SQLException
    {
        Connection c = DBConnectionFactory.getInstance().getConnection();
        
        CallableStatement cs = null;
        int rowCount = -1;
        
        try
        {
            cs = c.prepareCall("{call create_customer(?,?,?,?)}");
            cs.setString(3, age);
            cs.setString(4, email);
            
            rowCount = cs.executeUpdate();
            cs.close();
            
        }
        catch (SQLException ex)
        {
            System.out.println("Error when creating Customer in DB!\n" + ex.getLocalizedMessage());
        }
        finally
        {
            try
            {
                c.close();
            }
            catch (SQLException ex)
            {
                System.out.println("Failed to close connection! @DBHandler createCustomer\n" + ex.getLocalizedMessage());
            }
        }
    }
}
