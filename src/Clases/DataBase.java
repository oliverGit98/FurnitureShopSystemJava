
package Clases;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataBase {
    
    private static final String servername = "localhost";
    private static final String username = "root";
    private static final String dbName = "furniture_shop_system";
    private static final int portNbr = 3306;
    private static final String password = "";
    
    public static Connection getConnection()
    {
        Connection connection = null;
        
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(username);
        datasource.setDatabaseName(dbName);
        datasource.setPort(portNbr);
        datasource.setPassword(password);
        
        try {
            
            connection = datasource.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
}
