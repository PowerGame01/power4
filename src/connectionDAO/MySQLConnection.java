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

    //MySQLConnection constructor
    public MySQLConnection() {}
    	//Creating a connection instance
    	public static Connection getInstance() {
    	        try {
    	        	//reading properties file
    	        	System.out.println("MysqlConn in");
    	            FileReader in = new FileReader("/home/stag/git/power4/WebContent/WEB-INF/properties/configDB.prop");
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
    	            			":"+PORT+"/"+DATABASE + "?autoReconnect=true&useSSL=false", USER, PASSWORD);
    	            	//tables creation if not exists
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS player(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        name    Varchar (50) NOT NULL ,\n" + 
    	            			"        waiting Bool NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id ) ,\n" + 
    	            			"        UNIQUE (name )\n" + 
    	            			")ENGINE=InnoDB;");
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS gridPosition(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        row       Varchar (2) NOT NULL ,\n" + 
    	            			"        col       Varchar (2) NOT NULL ,\n" + 
    	            			"        id_player Int NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id )\n" + 
    	            			")ENGINE=InnoDB;");
    	            	connection.createStatement().execute("CREATE TABLE IF NOT EXISTS game(id int (11) Auto_increment  NOT NULL ,\n" + 
    	            			"        id_player   Int NOT NULL ,\n" + 
    	            			"        id_player_1 Int NOT NULL ,\n" + 
    	            			"        PRIMARY KEY (id )\n" + 
    	            			")ENGINE=InnoDB;");
    	            	
//    	            	connection.createStatement().execute("ALTER TABLE gridPosition ADD CONSTRAINT FK_gridPosition_id_player FOREIGN KEY (id_player) REFERENCES player(id);");
//    	            	connection.createStatement().execute("ALTER TABLE game ADD CONSTRAINT FK_game_id_player FOREIGN KEY (id_player) REFERENCES player(id);");
//    	            	connection.createStatement().execute("ALTER TABLE game ADD CONSTRAINT FK_game_id_player_1 FOREIGN KEY (id_player_1) REFERENCES player(id);");
    	            
    	            }catch(SQLException ex){
    	                Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null , ex);
    	            }
    	        }
    	        //return connection
    	    return connection;
    	}
    
    
}
