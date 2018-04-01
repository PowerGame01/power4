package connectionDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {
    // Using properties file for Connection
    /*
    connection URL
    */
    private static String HOST;
    private static String PORT;
    private static String DATABASE;
  
    /*
    Login-Password
    */
    private static String USER;
    private static String PASSWORD;  
    /*
    Connection Object
    */
    private static Connection connection;

    public MySQLConnection() {}
    	
    	public static Connection getInstance() {
    	        try {
    	        	System.out.println("MysqlConn in");
    	            FileReader in = new FileReader("/home/flavien/git/power4/WebContent/WEB-INF/properties/configDB.prop");
    	            Properties configProp = new Properties();
    	            configProp.load(in);
    	            HOST = configProp.getProperty("HOST");
    	            PORT = configProp.getProperty("PORT");
    	            DATABASE = configProp.getProperty("DATABASE");
    	            USER = configProp.getProperty("USER");
    	            PASSWORD = configProp.getProperty("PASSWORD");
    	        } catch (FileNotFoundException ex) {
    	            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
    	        } catch (IOException ex) {
    	            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
    	        }
    	        if (connection == null) {
    	            try{
    	            	connection = DriverManager.getConnection("jdbc:mysql://"+HOST+
    	            			":"+PORT+"/"+DATABASE, USER, PASSWORD);
    	            }catch(SQLException ex){
    	                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null , ex);
    	            }
    	        }
    	    return connection;
    	}
    
    
}
