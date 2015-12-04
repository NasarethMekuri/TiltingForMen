package DB;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author SBL
 */
public class DBConnectionFactory 
{
    private String _user = System.getProperty("user.name");
    private String _pw;

    private static DBConnectionFactory _instance;
    
    private final String SERVER_NAME = "localhost";//"10.153.0.143"; //"localhost"; 
    private final String DATABASE_INSTANCE = "SQLEXPRESS";
    private final int PORTNO = 1433;//49197; // 58828; //1433;
    private final String DATABASE_NAME = "tiltingDB";
    private final String USERNAME = "sa";
    private final String PASSWORD;

    private DBConnectionFactory() throws SQLServerException, SQLException 
    {
        if (!_user.equals("bruger"))
        {_pw = "Brutalis";}
        else
        {_pw = "offlimit";}
        PASSWORD = _pw;
    }

    /**
     * Returns a new connection
     *
     * @return m_connection
     */
    public Connection getConnection() throws SQLException 
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName(SERVER_NAME);
        ds.setInstanceName(DATABASE_INSTANCE);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setPortNumber(PORTNO);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        
        Connection c = ds.getConnection();
        
        if (!c.isClosed())   {System.out.println("Successfully connected to Database\n");}
        
        return c;
    }

    public static DBConnectionFactory getInstance() throws SQLException
    {
        if (_instance == null)
        {
            _instance = new DBConnectionFactory();
        }
        return _instance;
    }
    
}
